package hr.fer.zemris.java.tecaj.hw5.db.operators;

import hr.fer.zemris.java.tecaj.hw5.db.QueryException;

/**
 * Checks whether the first string contains part of a second string which is replaced with character '*', 
 * and the surrounding parts.
 * @author Mislav Gillinger
 * @version 1.0
 */
public class Like implements IComparisonOperator{

	@Override
	public boolean satisfied(String value1, String value2) throws QueryException {
		if(!value2.contains("*")){
			throw new QueryException("Second argument in operation LIKE must contain '*' !");
		}
		if(value2.indexOf("*") != value2.lastIndexOf("*")){
			throw new QueryException("Characther '*' can show up only once!");
		}
		
		if(value2.startsWith("*")){
			value2 = value2.substring(1, value2.length());
			if(value1.endsWith(value2)){
				return true;
			}
			else{
				return false;
			}
		}
		else if(value2.endsWith("*")){
			value2 = value2.substring(0, value2.length() - 1);
			if(value1.startsWith(value2)){
				return true;
			}
			else{
				return false;
			}
		}
		else{
			String[] elements = value2.trim().split("\\*");
			if(!value1.contains(elements[0])){
				return false;
			}
			else if(!value1.contains(elements[1])){
				return false;
			}
			else{
				if(value1.startsWith(elements[0]) && value2.endsWith(elements[1])){
					return true;
				}
				else{
					return false;
				}
			}
		}
	}
}
