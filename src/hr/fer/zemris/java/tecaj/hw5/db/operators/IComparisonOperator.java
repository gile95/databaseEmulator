package hr.fer.zemris.java.tecaj.hw5.db.operators;

import hr.fer.zemris.java.tecaj.hw5.db.QueryException;

/**
 * Homework of this interface is to check if the two given parameters, as part of a conditional
 * expression, satisfy certain operator.
 * @author Mislav Gillinger
 * @version 1.0
 */
public interface IComparisonOperator {

	/**
	 * Checks if the two given parameters, as part of a conditional expression, satisfy certain operator.
	 * @param value1 First argument in conditional expression.
	 * @param value2 Second argument in conditional expression.
	 * @return True if the arguments satisfy the conditional expression.
	 * @throws QueryException If a query command has invalid syntax.
	 */
	public boolean satisfied(String value1, String value2) throws QueryException;
}
