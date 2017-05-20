package hr.fer.zemris.java.tecaj.hw5.db;

/**
 * Exception which is thrown while parsing and evaluating database commands, if command syntax is invalid.
 * @author Mislav Gillinger 
 * @version 1.0
 */
public class QueryException extends Exception {

	/**
	 * SerialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Creates a new QueryException, with a given message which describes an error.
	 * @param message String which describes an error.
	 */
	public QueryException(String message){
		super(message);
	}
}
