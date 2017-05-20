package hr.fer.zemris.java.tecaj.hw5.db;

/**
 * Lexer which evaluates the given command. Its method {@link #nextToken()} returns instances of
 * class {@link Token}
 * @author Mislav Gillinger
 * @version 1.0
 */
public class Lexer {
	
	/** String to be evaluated. Arguments of command query. */
	private String input;
	/** Index of last processed argument of a command query. */
	private int lastProcessed;
	/** List of query command's arguments.*/
	private String[] expressions;
	
	/**
	 * Fills {@link #expressions} with command query's arguments.
	 * @param input String to be evaluated.
	 */
	public Lexer(String input){
		this.input = input;
		
		expressions = this.input.split("[a || A][n || N][d || D]");
	}
	
	/**
	 * Fetches the next token and returns it.
	 * @return Next token.
	 * @throws QueryException If syntax of command query is invalid.
	 */
	public Token nextToken() throws QueryException{
		
		if(lastProcessed == expressions.length){
			return null;
		}
	
		String expression = expressions[lastProcessed++];
		expression = expression.trim();
		
		if(expression.startsWith("firstName")){
			expression = expression.substring(9).trim();
			TokenType operator = determineOperator(expression);
			
			if(!expression.contains("\"") || !expression.endsWith("\"")){
				throw new QueryException("Literal must be surrounded with \" and \"");
			}
			
			int index = expression.indexOf("\"");
			expression = expression.substring(index + 1, expression.length() - 1);
			return new Token(operator, "firstName", expression);
		}
		
		else if(expression.startsWith("lastName")){
			expression = expression.substring(8).trim();
			TokenType operator = determineOperator(expression);
			
			if(!expression.contains("\"") || !expression.endsWith("\"")){
				throw new QueryException("Literal must be surrounded with \" and \"");
			}
			
			int index = expression.indexOf("\"");
			expression = expression.substring(index + 1, expression.length() - 1);
			return new Token(operator, "lastName", expression);
		}
		
		else if(expression.startsWith("jmbag")){
			expression = expression.substring(5).trim();
			TokenType operator = determineOperator(expression);
			
			if(!expression.contains("\"") || !expression.endsWith("\"")){
				throw new QueryException("Literal must be surrounded with \" and \"");
			}
			
			int index = expression.indexOf("\"");
			expression = expression.substring(index + 1, expression.length() - 1);
			return new Token(operator, "jmbag", expression);
		}
		
		else{
			throw new QueryException("Unknown attribute!");
		}
	}

	/**
	 * Determines which operator is used based on a part of an input string argument.
	 * @param expression Part of a string argument of a command query. 
	 * @return One of {@link TokenType}s
	 * @throws QueryException If query command has invalid syntax.
	 */
	private TokenType determineOperator(String expression) throws QueryException {
		if(expression.startsWith(">=")){
			return TokenType.GREATER_EQUALS;
		}
		if(expression.startsWith("<=")){
			return TokenType.LESS_EQUALS;
		}
		if(expression.startsWith(">")){
			return TokenType.GREATER;
		}
		if(expression.startsWith("<")){
			return TokenType.LESS;
		}
		if(expression.startsWith("=")){
			return TokenType.EQUALS;
		}
		if(expression.startsWith("!=")){
			return TokenType.DIFFERENT;
		}
		if(expression.startsWith("LIKE")){
			return TokenType.LIKE;
		}
		throw new QueryException("Unknown operator!");
	}
}
