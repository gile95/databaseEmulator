package hr.fer.zemris.java.tecaj.hw5.db;

/**
 * Represents a filter for type {@link StudentRecord}.
 * @author Mislav Gillinger
 * @version 1.0
 */
public interface IFilter {
	
	/**
	 * Accepts a given record if it satisfies this filter.
	 * @param record {@link StudentRecord} which will be checked.
 	 * @return True if a record satisfies this filer, false otherwise.
	 * @throws QueryException If a query command has invalid syntax.
	 */
	public boolean accepts(StudentRecord record) throws QueryException;
}
