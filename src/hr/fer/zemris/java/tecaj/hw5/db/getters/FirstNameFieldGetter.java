package hr.fer.zemris.java.tecaj.hw5.db.getters;

import hr.fer.zemris.java.tecaj.hw5.db.StudentRecord;

/**
 * Getter for value of attribute first name.
 * @author Mislav Gillinger
 * @version 1.0
 */
public class FirstNameFieldGetter implements IFieldValueGetter{

	@Override
	public String get(StudentRecord record) {
		return record.getFirstName();
	}

}
