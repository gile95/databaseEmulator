package hr.fer.zemris.java.tecaj.hw5.db;

import java.util.ArrayList;
import java.util.List;

import hr.fer.zemris.java.tecaj.hw5.db.getters.FirstNameFieldGetter;
import hr.fer.zemris.java.tecaj.hw5.db.getters.IFieldValueGetter;
import hr.fer.zemris.java.tecaj.hw5.db.getters.JmbagFieldGetter;
import hr.fer.zemris.java.tecaj.hw5.db.getters.LastNameFieldGetter;
import hr.fer.zemris.java.tecaj.hw5.db.operators.DifferentThan;
import hr.fer.zemris.java.tecaj.hw5.db.operators.Equals;
import hr.fer.zemris.java.tecaj.hw5.db.operators.GreaterEquals;
import hr.fer.zemris.java.tecaj.hw5.db.operators.GreaterThan;
import hr.fer.zemris.java.tecaj.hw5.db.operators.IComparisonOperator;
import hr.fer.zemris.java.tecaj.hw5.db.operators.LessEquals;
import hr.fer.zemris.java.tecaj.hw5.db.operators.LessThan;
import hr.fer.zemris.java.tecaj.hw5.db.operators.Like;

/**
 * Parser which gets tokens from {@link Lexer} and creates a list of {@link ConditionalExpression}s.
 * @author Mislav Gillinger
 * @version 1.0
 */
public class Parser {

	/** Lexer which provides tokens. */
	private Lexer lexer;
	/** List which contains conditional expressions. */
	List<ConditionalExpression> conditionalExpressions;
	
	/**
	 * Uses tokens gotten from Lexer to create conditional expressions.
	 * @param lexer Lexer which provides tokens.
	 * @throws QueryException If a query command has invalid syntax.
	 */
	public Parser(Lexer lexer) throws QueryException{
		this.lexer = lexer;
		parse();
		
	}
	
	/**
	 * Uses tokens gotten from Lexer to create a list of conditional expressions.
	 * @throws QueryException If query command has invalid syntax.
	 */
	public void parse() throws QueryException{
	
		conditionalExpressions = new ArrayList<>();
		Token token;
		
		while((token = lexer.nextToken()) != null){
			IComparisonOperator operator = determineOperator(token);
			IFieldValueGetter attribute;
			
			if(token.getAttribute().equals("firstName")){
				attribute = new FirstNameFieldGetter();
			}
			else if (token.getAttribute().equals("lastName")){
				attribute = new LastNameFieldGetter();
			}
			else if(token.getAttribute().equals("jmbag")){
				attribute = new JmbagFieldGetter();
			}
			else{
				throw new QueryException("Unknown attribute!");
			}
			
			conditionalExpressions.add(new ConditionalExpression(attribute, token.getValue(), operator));
		}
	}
	
	/**
	 * Returns a list of conditional expressions.
	 * @return A list of conditional expressions.
	 */
	public List<ConditionalExpression> getConditionalExpressions(){
		return conditionalExpressions;
	}
	
	/**
	 * Determines which operator is used based on given token.
	 * @param token Token which represents a meaningful part of query argument.
	 * @return Instance of a class which implements an interface {@link IComparisonOperator}.
	 * @throws QueryException If query command has invalid syntax.
	 */
	public IComparisonOperator determineOperator(Token token) throws QueryException{
		if(token.getType().equals(TokenType.LESS)){
			return new LessThan();
		}
		if(token.getType().equals(TokenType.GREATER)){
			return new GreaterThan();
		}
		if(token.getType().equals(TokenType.LESS_EQUALS)){
			return new LessEquals();
		}
		if(token.getType().equals(TokenType.GREATER_EQUALS)){
			return new GreaterEquals();
		}
		if(token.getType().equals(TokenType.EQUALS)){
			return new Equals();
		}
		if(token.getType().equals(TokenType.DIFFERENT)){
			return new DifferentThan();
		}
		if(token.getType().equals(TokenType.LIKE)){
			return new Like();
		}
		throw new QueryException("Unknown operator!");
	}
}
