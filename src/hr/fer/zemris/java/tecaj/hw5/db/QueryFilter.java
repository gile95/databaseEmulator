package hr.fer.zemris.java.tecaj.hw5.db;

/**
 * Implementation of interface {@link IFilter}. Uses {@link Lexer} and {@link Parser} get all arguments
 * of a query command in form of {@link ConditionalExpression}s. Every conditional expression is 
 * checked whether it is true or false. If particular {@link StudentRecord} satisfies all conditional
 * expressions, method {@link #accepts(StudentRecord)} returns true for it.
 * @author Mislav Gillinger
 * @version 1.0
 */
public class QueryFilter implements IFilter{

	/** Arguments of a query command. */
	private String input;
	/** Parser which will make a list of conditional expressions. */
	private Parser parser;
	
	/**
	 * Instantiates objects of classes {@link Lexer} and {@link Parser}. 
	 * @param input Arguments of a query command.
	 * @throws QueryException If query command has invalid syntax.
	 */
	public QueryFilter(String input) throws QueryException{
		this.input = input;	
		Lexer lexer = new Lexer(this.input);
		this.parser = new Parser(lexer);
	}

	@Override
	public boolean accepts(StudentRecord record) throws QueryException {
		for(ConditionalExpression expr : parser.getConditionalExpressions()){
			boolean recordSatisfies = expr.getComparisonOperator().satisfied(
					expr.getFieldGetter().get(record),
					expr.getLiteral()
			);
			if(recordSatisfies == false){
				return false;
			}
		}
		return true;
	}
}
