package hr.fer.zemris.java.tecaj.hw5.collections;

import java.util.Iterator;

/**
 * Demo class which represents how SimpleHashtable works.
 * @author Mislav Gillinger
 * @version 1.0
 */
public class SimpleHashtableDemo {

	/**
	 * Program execution starts with this method.
	 * @param args Command line arguments.
	 */
	/*public static void main(String[] args) {
		// create collection:
		SimpleHashtable<String, Integer> examMarks = new SimpleHashtable<>(2);
		// fill data:
		
		examMarks.put("Ivana", 2);
		examMarks.put("Ante", 2);
		examMarks.put("Jasna", 2);
		examMarks.put("Kristina", 5);
		examMarks.put("Ivana", 5); // overwrites old grade for Ivana
		
		examMarks.remove("Jasna");
		
		System.out.println("There is a grade 3: " + examMarks.containsValue(3));
		System.out.println("There is someone named Ante: " + examMarks.containsKey("Ante"));
		// query collection:
		Integer kristinaGrade = examMarks.get("Kristina");
		System.out.println("Kristina's exam grade is: " + kristinaGrade); // writes: 5
		// What is collection's size? Must be four!
		System.out.println("Number of stored pairs: " + examMarks.size()); // writes: 3
		
		System.out.println(examMarks.toString());
	}*/
	
	/* ____________________________________________________________________________________ */
	
	/**
	 * Program execution starts with this method.
	 * @param args Command line arguments.
	 */
	public static void main(String[] args) {
		// create collection:
		SimpleHashtable<String,Integer> examMarks = new SimpleHashtable<>(2);
		// fill data:
		examMarks.put("Ivana", 2);
		examMarks.put("Ante", 2);
		examMarks.put("Jasna", 2);
		examMarks.put("Kristina", 5);
		examMarks.put("Ivana", 5); // overwrites old grade for Ivana
		
		/*for(SimpleHashtable.TableEntry<String,Integer> pair : examMarks) {
			System.out.printf("%s => %d%n", pair.getKey(), pair.getValue());
		}*/
		
		/* ___________________________________________________________________ */
		
		/*for(SimpleHashtable.TableEntry<String,Integer> pair1 : examMarks) {
			for(SimpleHashtable.TableEntry<String,Integer> pair2 : examMarks) {
				System.out.printf(
						"(%s => %d) - (%s => %d)%n",
						pair1.getKey(), pair1.getValue(),
						pair2.getKey(), pair2.getValue()
					);
			}
		}*/
		
		/* _______________________________________________________________ */
		
		Iterator<SimpleHashtable.TableEntry<String,Integer>> it = examMarks.iterator(); 
		while(it.hasNext()){
			System.out.println(it.next());
		}
		
		/* _________________________________________________________________ */
		
		/*Iterator<SimpleHashtable.TableEntry<String, Integer>> iter = examMarks.iterator();
		while (iter.hasNext()) {
			SimpleHashtable.TableEntry<String, Integer> pair = iter.next();
			if (pair.getKey().equals("Ivana")) {
				iter.remove(); // sam iterator kontrolirano uklanja trenutni
								// element
			}
		}
		
		System.out.println(examMarks.containsKey("Ivana"));*/
		
		/* _________________________________________________________________ */
		
		/*Iterator<SimpleHashtable.TableEntry<String, Integer>> iter = examMarks.iterator();
		while (iter.hasNext()) {
			SimpleHashtable.TableEntry<String, Integer> pair = iter.next();
			if (pair.getKey().equals("Ivana")) {
				iter.remove();
				iter.remove(); //throws exception
			}
		}*/
		
		/* _________________________________________________________________ */
		
		/*Iterator<SimpleHashtable.TableEntry<String, Integer>> iter = examMarks.iterator();
		while (iter.hasNext()) { //throws exception
			SimpleHashtable.TableEntry<String, Integer> pair = iter.next();
			if (pair.getKey().equals("Ivana")) {
				examMarks.remove("Ivana");
			}
		}*/
	}

}
