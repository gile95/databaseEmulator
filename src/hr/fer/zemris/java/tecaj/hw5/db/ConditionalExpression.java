package hr.fer.zemris.java.tecaj.hw5.db;

import hr.fer.zemris.java.tecaj.hw5.db.getters.FirstNameFieldGetter;
import hr.fer.zemris.java.tecaj.hw5.db.getters.IFieldValueGetter;
import hr.fer.zemris.java.tecaj.hw5.db.getters.JmbagFieldGetter;
import hr.fer.zemris.java.tecaj.hw5.db.getters.LastNameFieldGetter;
import hr.fer.zemris.java.tecaj.hw5.db.operators.IComparisonOperator;

/**
 * Represents a conditional expression. It has a left side assignment which is one of database attributes.
 * In a middle there is an one of operators, and the right side assignment is value of an attribute.
 * @author Mislav Gillinger
 * @version 1.0
 */
public class ConditionalExpression {

	/** Instance of an object that implements an interface {@link IFieldValueGetter}. */
	private IFieldValueGetter fieldGetter;
	/** String which represents a right side assingment of a conditional expression. */
	private String literal;
	/** Instance of an object that implements an interface {@link IComparisonOperator}.*/
	private IComparisonOperator comparisonOperator;
	
	/**
	 * Creates a new {@link ConditionalExpression}.
	 * @param fieldGetter Instance of an object that implements an interface {@link IFieldValueGetter}.
	 * @param literal String which represents a right side assingment of a conditional expression.
	 * @param comparisonOperator Instance of an object that implements an interface {@link IComparisonOperator}.
	 * @throws QueryException If one of given arguments violates the syntax of command query.
	 */
	public ConditionalExpression(IFieldValueGetter fieldGetter, String literal, IComparisonOperator comparisonOperator) throws QueryException{
		
		if(fieldGetter instanceof FirstNameFieldGetter || fieldGetter instanceof LastNameFieldGetter){
			if(!literal.matches("[A-Za-zČčĆćŠšĐđŽž*]+")){
				throw new QueryException("Literal " + literal + " must consist of letters only!");
			}	
		}
		else if(fieldGetter instanceof JmbagFieldGetter){
			if(!literal.matches("[0-9*]+")){
				throw new QueryException("Literal " + literal + " must consist of numbers only!");
			}
		}
		
		
		this.fieldGetter = fieldGetter;
		this.literal = literal;
		this.comparisonOperator = comparisonOperator;
	}
	
	/**
	 * Returns {@link #fieldGetter}.
	 * @return {@link #fieldGetter}.
	 */
	public IFieldValueGetter getFieldGetter() {
		return fieldGetter;
	}
	
	/**
	 * Returns {@link #literal}.
	 * @return {@link #literal}.
	 */
	public String getLiteral() {
		return literal;
	}
	
	/**
	 * Returns {@link #comparisonOperator}.
	 * @return {@link #comparisonOperator}.
	 */
	public IComparisonOperator getComparisonOperator(){
		return comparisonOperator;
	}
}
