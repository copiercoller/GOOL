/*
 * Copyright 2010 Pablo Arrighi, Alex Concha, Miguel Lezama for version 1.
 * Copyright 2013 Pablo Arrighi, Miguel Lezama, Kevin Mazet for version 2.    
 *
 * This file is part of GOOL.
 *
 * GOOL is free software: you can redistribute it and/or modify it under the terms of the GNU
 * General Public License as published by the Free Software Foundation, version 3.
 *
 * GOOL is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License version 3 for more details.
 *
 * You should have received a copy of the GNU General Public License along with GOOL,
 * in the file COPYING.txt.  If not, see <http://www.gnu.org/licenses/>.
 */

package gool.generator.js;

import gool.ast.core.Assign;
import gool.ast.core.BinaryOperation;
import gool.ast.core.Break;
import gool.ast.core.Case;
import gool.ast.core.Catch;
import gool.ast.core.ClassDef;
import gool.ast.core.ClassNew;
import gool.ast.core.Constant;
import gool.ast.core.Constructor;
import gool.ast.core.CustomDependency;
import gool.ast.core.Dependency;
import gool.ast.core.EnhancedForLoop;
import gool.ast.core.EqualsCall;
import gool.ast.core.Field;
import gool.ast.core.Finally;
import gool.ast.core.MainMeth;
import gool.ast.core.Meth;
import gool.ast.core.Modifier;
import gool.ast.core.Operator;
import gool.ast.core.ParentCall;
import gool.ast.core.RecognizedDependency;
import gool.ast.core.Switch;
import gool.ast.core.Throw;
import gool.ast.core.ToStringCall;
import gool.ast.core.Try;
import gool.ast.core.TypeDependency;
import gool.ast.core.VarDeclaration;
import gool.ast.list.ListAddCall;
import gool.ast.list.ListContainsCall;
import gool.ast.list.ListGetCall;
import gool.ast.list.ListGetIteratorCall;
import gool.ast.list.ListIsEmptyCall;
import gool.ast.list.ListRemoveAtCall;
import gool.ast.list.ListRemoveCall;
import gool.ast.list.ListSizeCall;
import gool.ast.map.MapContainsKeyCall;
import gool.ast.map.MapEntryGetKeyCall;
import gool.ast.map.MapEntryGetValueCall;
import gool.ast.map.MapGetCall;
import gool.ast.map.MapGetIteratorCall;
import gool.ast.map.MapIsEmptyCall;
import gool.ast.map.MapPutCall;
import gool.ast.map.MapRemoveCall;
import gool.ast.map.MapSizeCall;
import gool.ast.system.SystemCommandDependency;
import gool.ast.system.SystemOutDependency;
import gool.ast.system.SystemOutPrintCall;
import gool.ast.type.IType;
import gool.ast.type.TypeBool;
import gool.ast.type.TypeChar;
import gool.ast.type.TypeDecimal;
import gool.ast.type.TypeEntry;
import gool.ast.type.TypeException;
import gool.ast.type.TypeInt;
import gool.ast.type.TypeList;
import gool.ast.type.TypeMap;
import gool.ast.type.TypeObject;
import gool.ast.type.TypeString;
import gool.generator.GeneratorHelper;
import gool.generator.common.CodeGeneratorNoVelocity;
import gool.generator.common.CommonCodeGenerator;
import gool.generator.common.GeneratorMatcher;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

public class JsGenerator extends CommonCodeGenerator /*
														 * implementsCodeGeneratorNoVelocity
														 * 
														 */{

	private static Map<String, Dependency> customDependencies = new HashMap<String, Dependency>();
	private static Logger logger = Logger.getLogger(JsGenerator.class
			.getName());

	public void addCustomDependency(String key, Dependency value) {
		customDependencies.put(key, value);
	}

	
	@Override
	public String getCode(Meth meth) {
		String out= String.format(" %s (", meth.getName() );
		boolean f = true;
		for(VarDeclaration var : meth.getParams() )
		{
			if ( f)
				f=false;
			else
				out+=",";
			out+=var.getName();
		}
		out+=")";
		return out;
	}
	
	@Override
	public String getCode(ClassNew classNew) {
		return String.format("new %s(%s)", classNew.getType(),
				StringUtils.join(classNew.getParameters(), ", "));
	}


	@Override
	public String getCode(VarDeclaration varDec) {
		String initialValue = "";
		if (varDec.getInitialValue() != null) {
			initialValue = " = " + varDec.getInitialValue();
		}
		return String.format("var %s%s", varDec.getName(),initialValue);
	}
	
	
	@Override
	public String getCode(EnhancedForLoop enhancedForLoop) {
		return formatIndented( "for (KeyOnForeach in %s){ \n\t %s = %s[KeyOnForeach]; %1}",
				enhancedForLoop.getExpression(),enhancedForLoop.getVarDec(),enhancedForLoop.getExpression(),
				enhancedForLoop.getStatements());
	}

	@Override
	public String getCode(EqualsCall equalsCall) {
		// TODO Auto-generated method stub
		return "not yet implemented";
	}

	@Override
	public String getCode(ListAddCall lac) {
		return String.format("%s.push(%s)", lac.getExpression(),
				StringUtils.join(lac.getParameters(), ", "));
	}

	@Override
	public String getCode(ListContainsCall lcc) {
		return String.format("%s.indexOf(%s)==-1?false:true", lcc.getExpression(),
				StringUtils.join(lcc.getParameters(), ", "));
	}

	@Override
	public String getCode(ListGetCall lgc) {
		return String.format("%s[%s]", lgc.getExpression(),
				StringUtils.join(lgc.getParameters(), ", "));
	}

	@Override
	public String getCode(ListGetIteratorCall lgic) {
		// TODO Auto-generated method stub
		return "not yet implemented";
	}

	@Override
	public String getCode(ListIsEmptyCall liec) {
		return String.format("%s.length==0?true:false", liec.getExpression());

	}

	public String getCode(ListRemoveAtCall lrc) {
		return String.format("%s.splice(%s,1)", lrc.getExpression(),
				StringUtils.join(lrc.getParameters(), ", "));
	}

	public String getCode(ListRemoveCall lrc) {
		return String.format("%s.splice(%s.indexOf(%s),1)", lrc.getExpression(),lrc.getExpression(),
				StringUtils.join(lrc.getParameters(), ", "));
	}

	@Override
	public String getCode(ListSizeCall lsc) {
		return String.format("%s.length", lsc.getExpression());
	}

	@Override
	public String getCode(MainMeth mainMeth) {
		return "main()";
	}

	@Override
	public String getCode(MapContainsKeyCall mapContainsKeyCall) {
		return String.format("%s.indexOf(%s)==-1?false:true", mapContainsKeyCall.getExpression(),
				StringUtils.join(mapContainsKeyCall.getParameters(), ", "));
	}

	@Override
	public String getCode(MapEntryGetKeyCall mapEntryGetKeyCall) {
		// TODO Auto-generated method stub
		return "not yet implemented";
	}

	@Override
	public String getCode(MapEntryGetValueCall mapEntryGetKeyCall) {
		// TODO Auto-generated method stub
		return "not yet implemented";
	}

	@Override
	public String getCode(MapGetCall mapGetCall) {
		return String.format("%s[%s]", mapGetCall.getExpression(),
				StringUtils.join(mapGetCall.getParameters(), ", "));
	}

	@Override
	public String getCode(MapGetIteratorCall mapGetIteratorCall) {
		// TODO Auto-generated method stub
		return "not yet implemented";
	}

	@Override
	public String getCode(MapIsEmptyCall mapIsEmptyCall) {
		return String.format("%s.length==0?true:false", mapIsEmptyCall.getExpression());
	}

	@Override
	public String getCode(MapPutCall mapPutCall) {
		return String.format("%s[%s]=(%s)",mapPutCall.getExpression(),
				mapPutCall.getParameters().get(0), mapPutCall.getParameters().get(1));
	}

	@Override
	public String getCode(MapRemoveCall mapRemoveCall) {
		return String.format("%s.splice(%s.indexOf(%s),1)", mapRemoveCall.getExpression(),mapRemoveCall.getExpression(),
				StringUtils.join(mapRemoveCall.getParameters(), ", "));
	}

	@Override
	public String getCode(MapSizeCall mapSizeCall) {
		// TODO Auto-generated method stub
		return String.format("%s.length", mapSizeCall.getExpression());
	}

	@Override
	public String getCode(ParentCall parentCall) {
		// TODO Auto-generated method stub
		return "not yet implemented";
	}

	@Override
	public String getCode(SystemOutDependency systemOutDependency) {
		return "not yet implemented";
	}

	@Override
	public String getCode(SystemOutPrintCall systemOutPrintCall) {
		return String.format("process.stdout.write(String(%s))",systemOutPrintCall.getParameters().get(0));
	}

	@Override
	public String getCode(ToStringCall tsc) {
		// TODO Auto-generated method stub à tester
		return String.format("String(%s)",tsc.getTarget());
	}

	@Override
	public String getCode(TypeBool typeBool) {
		return "Boolean";
	}

	@Override
	public String getCode(TypeDecimal typeReal) {
		return "Number";
	}

	@Override
	public String getCode(TypeChar typeChar) {
		return "Char";
	}

	@Override
	public String getCode(TypeEntry typeEntry) {
		return "var";
	}

	@Override
	public String getCode(TypeInt typeInt) {
		return "Number";
	}

	@Override
	public String getCode(TypeList typeList) {
		return "Array";
	}

	@Override
	public String getCode(TypeMap typeMap) {
		return "Array";
	}

	@Override
	public String getCode(TypeObject typeObject) {
		return "Null";
	}

	@Override
	public String getCode(TypeString typeString) {
		return "String";
	}

	@Override
	public String getCode(CustomDependency customDependency) {
		// TODO Auto-generated method stub
		return "not yet implemented";
	}

	@Override
	public String getCode(SystemCommandDependency systemCommandDependency) {
		// TODO Auto-generated method stub
		return "not yet implemented";
	}

	@Override
	public String getCode(Throw throwStatement) {
		// TODO Auto-generated method stub
		return "not yet implemented";
	}

	@Override
	public String getCode(Catch catchStatement) {
		// TODO Auto-generated method stub
		return "not yet implemented";
	}

	@Override
	public String getCode(Try tryStatement) {
		// TODO Auto-generated method stub
		return "not yet implemented";
	}

	@Override
	public String getCode(TypeException typeException) {
		// TODO Auto-generated method stub
		return "not yet implemented";
	}

	@Override
	public String getCode(RecognizedDependency recognizedDependency) {
		// TODO Auto-generated method stub
		return "not yet implemented";
	}
	//@Override
	public String printClass(ClassDef classDef) {
			//TODO refaire toute la partie commenté 
			StringBuilder sb = new StringBuilder(String.format(
					"// Platform: %s\n\n", classDef.getPlatform()));
			// print the class prototype
			boolean c = false;
			for (Meth meth : classDef.getMethods()) {
				if (meth.isConstructor()) {
					sb = sb.append(String.format("function %-1%s {\n%-2%s;%2%-1\n\n",
							meth.getHeader(), 
							((Constructor) meth).getInitCalls().get(0), 
							meth.getBlock()));
					c= true;
					break;
				} 
			}
			if(!c){
				sb = sb.append(String.format("function %s() {\n\n",classDef.getName()));
			}
				
				if (classDef.getParentClass() != null)
					sb = sb.append(String.format(" %s.apply(this,arguments);",
							classDef.getParentClass()));

			// print the fields
			for (Field field : classDef.getFields())
				sb = sb.append(formatIndented("%-1%s;\n\n", field));
			// print the methods
			for (Meth meth : classDef.getMethods()) {{
					if (!meth.isConstructor()) {
						sb = sb.append(formatIndented("this.%-1%s = function(",meth.getName()));
						c=true;
						for( VarDeclaration varDec : meth.getParams()  ){//
							if(!c){ 
								sb = sb.append(", ");
								c = false;
							}
							sb = sb.append(varDec.getName());
						}
						
						sb = sb.append(formatIndented(") {%2%-1}\n\n",meth.getBlock()));
					}
				}
			}

			return sb.toString();
			
		}

	@Override
	public String getCode(Field field) {
		String out = String.format(" var %s", field.getName());
		if (field.getType().toString().equals("noprint"))
			return "";
		if (field.getDefaultValue() != null) {
			// Notice that this will call a toString() on the field.defaultValue
			// Which will become a JavaGenerator.getCode(defaultValue)
			// Hence this seemingly simple statement
			// Is in fact a recursive descent on the abstract GOOL tree.
			out = String.format("%s = %s", out, field.getDefaultValue());
		}
		return out;
	}
}
