package hr.fer.zemris.java.tecaj.hw5.db;

/**
 * Represents a student in form jmbag, lastName, firstName, finalGrade.
 * @author Mislav Gillinger
 * @version 1.0
 */
public class StudentRecord {

	/** Student's jmbag. */
	private String jmbag;
	/** Student's last name. */
	private String lastName;
	/** Student's first name. */
	private String firstName;
	/** Student's final grade. */
	private int finalGrade;
	
	/**
	 * Creates new StudentRecord.
	 * @param jmbag Student's jmbag.
	 * @param lastName Student's last name.
	 * @param firstName Student's first name.
	 * @param finalGrade Student's final grade.
	 */
	public StudentRecord(String jmbag, String lastName, String firstName, int finalGrade) {
		this.jmbag = jmbag;
		this.lastName = lastName;
		this.firstName = firstName;
		this.finalGrade = finalGrade;
	}

	/**
	 * Fetches final grade.
	 * @return Final grade. 
	 */
	public int getFinalGrade() {
		return finalGrade;
	}

	/**
	 * Sets final grade.
	 * @param finalGrade New final grade.
	 */
	public void setFinalGrade(int finalGrade) {
		this.finalGrade = finalGrade;
	}

	/**
	 * Fetches jmbag.
	 * @return Jmbag.
	 */
	public String getJmbag() {
		return jmbag;
	}

	/**
	 * Fetches last name.
	 * @return Last name.
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Fetches first name.
	 * @return First name.
	 */
	public String getFirstName() {
		return firstName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((jmbag == null) ? 0 : jmbag.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StudentRecord other = (StudentRecord) obj;
		if (jmbag == null) {
			if (other.jmbag != null)
				return false;
		} else if (!jmbag.equals(other.jmbag))
			return false;
		return true;
	}
	
	
}
