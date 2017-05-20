package hr.fer.zemris.java.tecaj.hw5.db;

import java.util.ArrayList;
import java.util.List;

import hr.fer.zemris.java.tecaj.hw5.collections.SimpleHashtable;

/**
 * Represents a storage of data for student records, or a simple database.
 * @author Mislav Gillinger
 * @version 1.0
 */
public class StudentDatabase {

	/** Map of students in form jmbag, studentRecord. */
	private SimpleHashtable<String, StudentRecord> studentsSht;
	/** List of student records. */
	private List<StudentRecord> studentsList;
	/** Length of the longest first name. */
	private int longestFirstName = 0;
	/** Length of the longest last name. */
	private int longestLastName = 0;
	
	/**
	 * Creates a new student database. Fills {@link #studentsSht} and {@link #studentsList} with students.}
	 * @param lines List of string lines which will be transformed into student records.
	 */
	public StudentDatabase(List<String> lines){
		studentsSht = new SimpleHashtable<>();
		studentsList = new ArrayList<>();
		
		for(String line : lines){
			String elements[] = line.trim().split("\t");
			studentsSht.put(elements[0],
					new StudentRecord(elements[0], elements[1], elements[2], Integer.parseInt(elements[3])));
			studentsList.add(new StudentRecord(elements[0], elements[1], elements[2], Integer.parseInt(elements[3])));
		}
	}
	
	/**
	 * Returns student record based on the given jmbag. Complexity is O(1).
	 * @param jmbag Student record will be made out of this jmbag.
	 * @return Student record based on given jmbag.
	 */
	public StudentRecord forJMBAG(String jmbag){
		
		longestFirstName = 0;
		longestLastName = 0;
		
		if(!studentsSht.containsKey(jmbag)){
			return null;
		}
		
		StudentRecord wanted = studentsSht.get(jmbag);
		
		longestFirstName = wanted.getFirstName().length();
		longestLastName = wanted.getLastName().length();
		
		return wanted;
	}
	
	/**
	 * Makes a list of student records which satisfy a given filter.
	 * @param filter Filter which a student record has to satisfy in order to be put on the filtered list.
	 * @return  List of student records which satisfied a given filter.
	 * @throws QueryException If a command is invalid.
	 */
	public List<StudentRecord> filter(IFilter filter) throws QueryException{
		
		longestFirstName = 0;
		longestLastName = 0;
		
		List<StudentRecord> retList = new ArrayList<>();
		
		for(StudentRecord student : studentsList){
			if(filter.accepts(student)){
				if(student.getFirstName().length() > longestFirstName){
					longestFirstName = student.getFirstName().length();
				}
				if(student.getLastName().length() > longestLastName){
					longestLastName = student.getLastName().length();
				}
				retList.add(student);
			}
		}
		
		return retList;
	}
	
	/**
	 * Returns a length of the longest first name.
	 * @return A length of the longest first name.
	 */
	public int getLongestFirstName(){
		return longestFirstName;
	}
	
	/**
	 * Returns a length of the longest last name.
	 * @return A length of the longest last name.
	 */
	public int getLongestLastName(){
		return longestLastName;
	}
}
