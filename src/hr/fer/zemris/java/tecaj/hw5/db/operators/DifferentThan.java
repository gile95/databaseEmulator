package hr.fer.zemris.java.tecaj.hw5.db.operators;

/**
 * Checks if two strings are lexicographically different.
 * @author Mislav Gillinger
 * @version 1.0
 */
public class DifferentThan implements IComparisonOperator{

	@Override
	public boolean satisfied(String value1, String value2) {
		if(value1.compareTo(value2) != 0){
			return true;
		}
		return false;
	}
}
