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

package gool.ast.core;

import gool.generator.GoolGeneratorController;
import java.util.List;
/**
 * This captures the if statements of the intermediate language. Hence it is an
 * OOTStatement. Notice the type checking achieved through generics.
 */
public class Case extends Statement {

	/**
	 * The condition expression.
	 */
	private Expression condition;
	/**
	 * The statement that is evaluated when the condition is true.
	 */
	private List<Statement> Statements;

	/**
	 * @param condition
	 * @param statements
	 */
	public Case(Expression condition, List<Statement> Statements) {
		this.condition = condition;
		this.Statements = Statements;

	}

	public Expression getCondition() {
		return condition;
	}

	public List<Statement> getStatements() {
		return Statements;
	}


	@Override
	public String callGetCode() {
		return GoolGeneratorController.generator().getCode(this);
	}

}
