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
		return String.format("%s %s (%s)",meth.getType(), meth.getName(),StringUtils.join(meth.getParams(), ", "));
	}
	
	@Override
	public String getCode(ClassNew classNew) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCode(EnhancedForLoop enhancedForLoop) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCode(EqualsCall equalsCall) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCode(ListAddCall lac) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCode(ListContainsCall lcc) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCode(ListGetCall lgc) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCode(ListGetIteratorCall lgic) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCode(ListIsEmptyCall liec) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCode(ListRemoveAtCall lrc) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCode(ListRemoveCall lrc) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCode(ListSizeCall lsc) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCode(MainMeth mainMeth) {
		return "main()";
	}

	@Override
	public String getCode(MapContainsKeyCall mapContainsKeyCall) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCode(MapEntryGetKeyCall mapEntryGetKeyCall) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCode(MapEntryGetValueCall mapEntryGetKeyCall) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCode(MapGetCall mapGetCall) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCode(MapGetIteratorCall mapGetIteratorCall) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCode(MapIsEmptyCall mapIsEmptyCall) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCode(MapPutCall mapPutCall) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCode(MapRemoveCall mapRemoveCall) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCode(MapSizeCall mapSizeCall) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCode(ParentCall parentCall) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCode(SystemOutDependency systemOutDependency) {
		return "test";
	}

	@Override
	public String getCode(SystemOutPrintCall systemOutPrintCall) {
		return String.format("process.stdout.write(%s)",systemOutPrintCall.getParameters().get(0));
	}

	@Override
	public String getCode(ToStringCall tsc) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCode(TypeBool typeBool) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCode(TypeDecimal typeReal) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCode(TypeChar typeChar) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCode(TypeEntry typeEntry) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCode(TypeInt typeInt) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCode(TypeList typeList) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCode(TypeMap typeMap) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCode(TypeObject typeObject) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCode(TypeString typeString) {
		return "String";
	}

	@Override
	public String getCode(CustomDependency customDependency) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCode(SystemCommandDependency systemCommandDependency) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCode(Throw throwStatement) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCode(Catch catchStatement) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCode(Try tryStatement) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCode(TypeException typeException) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCode(RecognizedDependency recognizedDependency) {
		// TODO Auto-generated method stub
		return null;
	}
	//@Override
	public String printClass(ClassDef classDef) {
			//TODO refaire toute la partie commenté 
			StringBuilder sb = new StringBuilder(String.format(
					"// Platform: %s\n\n", classDef.getPlatform()));
			/*
			// print the package containing the class
			if (classDef.getPpackage() != null)
				sb = sb.append(String.format("package %s;\n\n",
						classDef.getPackageName()));
			// print the includes
			Set<String> dependencies = GeneratorHelper.printDependencies(classDef);
			if (!dependencies.isEmpty()) {
				for (String dependency : dependencies) {
					if (dependency != "noprint" && dependency.contains("."))
						sb = sb.append(String.format("import %s;\n", dependency));
				}

				sb = sb.append("\n");
			}
			// print the class prototype
			sb = sb.append(String.format("%s %s %s",
					StringUtils.join(classDef.getModifiers(), ' '),
					classDef.isInterface() ? "interface" : "class",
					classDef.getName()));
			if (classDef.getParentClass() != null)
				sb = sb.append(String.format(" extends %s",
						classDef.getParentClass()));
			if (!classDef.getInterfaces().isEmpty())
				sb = sb.append(String.format(" interfaces %s",
						StringUtils.join(classDef.getInterfaces(), ", ")));
			sb = sb.append(" {\n\n");*/
			sb.append("<script>");
			// print the fields
			for (Field field : classDef.getFields())
				sb = sb.append(formatIndented("%-1%s;\n\n", field));
			// print the methods
			for (Meth meth : classDef.getMethods()) {
				if (classDef.isInterface()) {
					sb = sb.append(formatIndented("%-1%s;\n\n", meth.getHeader()));
				} else {
					if (meth.isConstructor()) {
						sb = sb.append(formatIndented("%-1%s {\n%-2%s;%2%-1}\n\n",meth.getHeader(), ((Constructor) meth).getInitCalls().get(0), meth.getBlock()));
					} else {
						sb = sb.append(formatIndented("%-1%s {%2%-1}\n\n",meth.getHeader(), meth.getBlock()));
					}
				}
			}

			return sb.toString() + "</script>";
			
		}

}
