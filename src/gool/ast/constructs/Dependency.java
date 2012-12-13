package gool.ast.constructs;

import gool.generator.GoolGeneratorController;


/**
 * A dependency is an external class/type or an external package.
 */
public abstract class Dependency extends Node{

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Dependency) || !(obj instanceof String)) {
			return false;
		} else if (obj instanceof String) {
			return toString().equals(obj.toString());
		}
		return toString().equals(((Dependency) obj).toString());
	}

	@Override
	public int hashCode() {
		return toString().hashCode();
	}
}
