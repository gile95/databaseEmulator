package hr.fer.zemris.java.tecaj.hw5.db;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Simulates a system that can work with a database. Two commands are available: <br>
 * 		- indexquery jmbag = "wantedJmbag" - returns student record whose jmbag equals to wantedJmbag.
 * 				- Here is an example : indexquerry jmbag = "0000000048"<br>
 * 		- query conditional expression and conditional expression and ...
 * 			- Valid operators are less(<), greater(>), lessEquals(<=), greaterEquals(>=), equals(=),
 * 			 different(!=), like(like)
 * 			- Like operator uses char '*' to replace part of a string 
 * 				- Here are some examples : query lastName > "K"; query firstName = "Ivan" AND lastName LIKE "B*"
 * When command "exit" is written, the program terminates.
 * 
 * @author Mislav Gillinger
 * @version 1.0
 */

public class StudentDB {
	
	/** Length of a jmbag. */
	private static final int JMBAG_LENGTH = 10;

	/**
	 * Program execution starts with this method.
	 * @param args Command line arguments.
	 */
	public static void main(String[] args) {	
		
		StudentDatabase database = DatabaseLoader.getDatabase();
		
		
		System.out.print("> ");
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String line;
		
		try {
			while(!(line = reader.readLine()).trim().equals("exit")){
				
				//indexguery
				if(line.startsWith("indexquery ")){
					line = line.trim();
					line = line.substring(11);
					
					try{
						checkIndexQuery(line);
					}catch(QueryException e){
						System.out.println(e.getMessage());
						System.out.println("> ");
						continue;
					}
					
					processIndexQuery(line, database);
				}
				//query
				else if(line.startsWith("query ")){
					line = line.trim();
					line = line.substring(6);
					
					List<StudentRecord> filtered = null;
					try{
						QueryFilter qf = new QueryFilter(line);
						filtered = database.filter(qf);
					}catch(QueryException e){
						System.out.println(e.getMessage());
						System.out.print("> ");
						continue;
					}
						
					if(filtered.size() == 0){
						System.out.println("Records selected: 0");
						System.out.print("> ");
						continue;
					}
					
					printList(filtered, database);
					System.out.println("Records selected: " + filtered.size());
					System.out.print("> ");
				}
				//command invalid
				else{
					System.out.println("Invalid command!");
					System.out.print("> ");
					continue;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("Goodbye!");
	}

	/**
	 * Searches for student with a given jmbag and returns a student record representation of it.
	 * @param line Inputed command containing jmbag.
	 * @param database Database where the given jmbag is searched for.
	 * @return StudentRecord representation of a student with a given jmbag.
	 */
	public static StudentRecord processIndexQuery(String line, StudentDatabase database) {
		String[] elements = line.trim().split("=");
		elements[1] = elements[1].trim();
		StudentRecord wanted = database.forJMBAG(elements[1].substring(1, elements[1].length() - 1)); 
		
		if(wanted == null){
			System.out.println("There is no student with the wanted jmbag!");
			System.out.print("> ");
		}
		else{
			List<StudentRecord> chosen = new ArrayList<>();
			chosen.add(wanted);
			
			System.out.println("Using index for record retrieval.");
			printList(chosen, database);
			System.out.println("Records selected: 1");
			System.out.print("> ");
		}
		
		return wanted;
	}

	/**
	 * Checks whether the given command has valid syntax.
	 * @param line Inputed command.
	 * @throws QueryException If command is invalid.
	 */
	public static void checkIndexQuery(String line) throws QueryException {
		String[] elements = line.trim().split("=");
		if(elements.length != 2){
			throw new QueryException("Indexquerry command has invalid syntax!");
		}
		
		elements[1] = elements[1].trim();
		elements[1] = elements[1].substring(1, elements[1].length() - 1);
		if(!elements[0].trim().equals("jmbag")){
			throw new QueryException("Indexquerry command has invalid syntax!");
		}
		if(elements[1].length() != JMBAG_LENGTH){
			throw new QueryException("Jmbag in indexquerry command is invalid!");
		}
		try{
			Integer.parseInt(elements[1]);
		}catch(NumberFormatException e){
			throw new QueryException("Jmbag in indexquerry command is invalid!");
		}
	}

	/**
	 * Prints a list of students on standard output.
	 * @param filtered List of students which will be printed out.
	 * @param database Database from which the list of students was composed.
	 */
	private static void printList(List<StudentRecord> filtered, StudentDatabase database) {
		
		StringBuilder upAndDownSide = new StringBuilder();
		
		upAndDownSide.append("+");
		for(int i = 0; i < 12; i++){
			upAndDownSide.append("=");
		}
		upAndDownSide.append("+");
		for(int i = 0; i < database.getLongestLastName() + 2; i++){
			upAndDownSide.append("=");
		}
		upAndDownSide.append("+");
		for(int i = 0; i < database.getLongestFirstName() + 2; i++){
			upAndDownSide.append("=");
		}
		upAndDownSide.append("+");
		for(int i = 0; i < 3; i++){
			upAndDownSide.append("=");
		}
		upAndDownSide.append("+");
		
		System.out.println(upAndDownSide.toString());
		
		for(StudentRecord student : filtered){
			
			System.out.format("| %s | %-" + database.getLongestLastName() + "s "
					+ "| %-" + database.getLongestFirstName() + "s | %d |%n", 
					student.getJmbag(), student.getLastName(), student.getFirstName(), student.getFinalGrade());
		}
		
		System.out.println(upAndDownSide.toString());
	}
}
