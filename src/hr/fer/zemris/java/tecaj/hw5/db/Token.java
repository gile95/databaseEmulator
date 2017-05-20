package hr.fer.zemris.java.tecaj.hw5.db;

/**
 * Represents a meaningful part of query command argument. It is a {@link ConditionalExpression}.
 * @author Mislav Gillinger
 * @version 1.0
 */
public class Token {

	/** Type of a operator used in conditional expression. */
	private TokenType type;
	/** Attribute which is a left side assignment in conditional expression. */
	private String attribute;
	/** Value of an attribute. Right side assignment in conditional expression. Surrounded with " and " */
	private String value;
	
	/**
	 * Creates a new {@link Token}
	 * @param type Type of a operator used in conditional expression.
	 * @param attribute Attribute which is a left side assignment in conditional expression.
	 * @param value Value of an attribute. Right side assignment in conditional expression. Surrounded with " and "
	 */
	public Token(TokenType type, String attribute, String value){
		this.type = type;
		this.attribute = attribute;
		this.value = value;
	}
	
	/**
	 * Returns token {@link #type}.
	 * @return Token {@link #type}.
	 */
	public TokenType getType(){
		return type;
	}
	
	/**
	 * Returns token {@link #attribute}.
	 * @return token {@link #attribute}.
	 */
	public String getAttribute(){
		return attribute;
	}
	
	/**
	 * Returns token {@link #value}.
	 * @return token {@link #value}.
	 */
	public String getValue(){
		return value;
	}
}
