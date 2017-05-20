package hr.fer.zemris.java.tecaj.hw5.db.getters;

import hr.fer.zemris.java.tecaj.hw5.db.StudentRecord;

/**
 * Homework of this interface is to fetch field value of an attribute.
 * @author Mislav Gillinger
 * @version 1.0
 */
public interface IFieldValueGetter {

	/**
	 * Fetches a field value of an attribute.
	 * @param record Record whose attribute value will be returned.
	 * @return Value of the given record's attribute.
	 */
	public String get(StudentRecord record);
}
