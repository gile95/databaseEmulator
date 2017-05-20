package hr.fer.zemris.java.tecaj.hw5.db.operators;

import java.text.Collator;
import java.util.Locale;

/**
 * Checks if the first string is lexicographically less or equal to second string.
 * @author Mislav Gillinger
 * @version 1.0
 */
public class LessEquals implements IComparisonOperator{

	@Override
	public boolean satisfied(String value1, String value2) {
		
		Collator hrCollator = Collator.getInstance(new Locale("hr", "HR"));
		
		if(hrCollator.compare(value1, value2) <= 0){
			return true;
		}
		return false;
	}
}
