package hr.fer.zemris.java.tecaj.hw5.db.operators;

/**
 * Checks if two strings are lexicographically equal.
 * @author Mislav Gillinger
 * @version 1.0
 */
public class Equals implements IComparisonOperator{

	@Override
	public boolean satisfied(String value1, String value2) {
		if(value1.equals(value2)){
			return true;
		}
		return false;
	}
}
