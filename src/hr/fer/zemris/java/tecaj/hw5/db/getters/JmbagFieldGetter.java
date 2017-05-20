package hr.fer.zemris.java.tecaj.hw5.db.getters;

import hr.fer.zemris.java.tecaj.hw5.db.StudentRecord;

/**
 * Getter for value of attribute jmbag.
 * @author Mislav Gillinger
 * @version 1.0
 */
public class JmbagFieldGetter implements IFieldValueGetter{

	@Override
	public String get(StudentRecord record) {
		return record.getJmbag();
	}
}
