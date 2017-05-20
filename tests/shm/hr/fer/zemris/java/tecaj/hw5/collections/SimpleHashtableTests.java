package hr.fer.zemris.java.tecaj.hw5.collections;

import static org.junit.Assert.assertEquals;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

import org.junit.Test;

import hr.fer.zemris.java.tecaj.hw5.collections.SimpleHashtable.TableEntry;

public class SimpleHashtableTests {
	
	@Test
	public void testDefaultConstructor(){
		SimpleHashtable<String, Integer> sht = new SimpleHashtable<>();
		sht.put("Ana", 3);
		assertEquals("", 1, sht.size());
	}
	
	@Test
	public void testPutAndConstructorWithArgument(){
		SimpleHashtable<String, Integer> sht = new SimpleHashtable<>(3);
		sht.put("Ana", 4);
		sht.put("Iva", 5);
		assertEquals("", 2, sht.size());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testConstructorWithNegativeArgument(){
		SimpleHashtable<String, Integer> sht = new SimpleHashtable<>(-1);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testPutWithInvalidArguments(){
		SimpleHashtable<String, Integer> sht = new SimpleHashtable<>(2);
		sht.put(null, 5);
	}
	
	@Test
	public void testGet(){
		SimpleHashtable<String, Integer> sht = new SimpleHashtable<>(2);
		sht.put("Ana", 5);
		sht.put("Iva", 4);
		assertEquals("", new Integer(4), sht.get("Iva"));
		assertEquals("", null, sht.get("Josip"));
	}
	
	@Test
	public void testContainsKeyAndContainsValue(){
		SimpleHashtable<String, Integer> sht = new SimpleHashtable<>(2);
		sht.put("Ana", 5);
		sht.put("Iva", 4);
		assertEquals("", true, sht.containsKey("Iva"));
		assertEquals("", true, sht.containsValue(4));
		assertEquals("", false, sht.containsKey("Josip"));
		assertEquals("", false, sht.containsValue("Josip"));
		assertEquals("", false, sht.containsValue(1));
	}

	@Test
	public void testRemove(){
		SimpleHashtable<String, Integer> sht = new SimpleHashtable<>(2);
		sht.put("Ana", 5);
		sht.put("Iva", 4);
		sht.remove("Iva");
		assertEquals("", 1, sht.size());
	}	
	
	@Test
	public void testIsEmpty(){
		SimpleHashtable<String, Integer> sht = new SimpleHashtable<>(2);
		sht.put("Ana", 5);
		assertEquals("", false, sht.isEmpty());
	}	
	
	@Test
	public void testClear(){
		SimpleHashtable<String, Integer> sht = new SimpleHashtable<>(2);
		sht.put("Ana", 5);
		sht.put("Iva", 4);
		sht.clear();
		assertEquals("", 0, sht.size());
	}	
	
	@Test
	public void testIteratorNext(){
		// create collection:
		SimpleHashtable<String,Integer> examMarks = new SimpleHashtable<>(2);
		// fill data:
		examMarks.put("Ivana", 2);
		examMarks.put("Ante", 2);
		examMarks.put("Jasna", 2);
		examMarks.put("Kristina", 5);
		examMarks.put("Ivana", 5); // overwrites old grade for Ivana
		
		Iterator<SimpleHashtable.TableEntry<String,Integer>> it = examMarks.iterator(); 
		TableEntry<String, Integer> te1 = it.next();
		TableEntry<String, Integer> te2 = it.next();
		
		assertEquals("", "Ante", te1.getKey());
		assertEquals("", "Ivana", te2.getKey());
	}
	
	@Test
	public void testIteratorRemove(){
		// create collection:
		SimpleHashtable<String,Integer> examMarks = new SimpleHashtable<>(2);
		// fill data:
		examMarks.put("Ivana", 2);
		examMarks.put("Ante", 2);
		examMarks.put("Jasna", 2);
		examMarks.put("Kristina", 5);
		examMarks.put("Ivana", 5); // overwrites old grade for Ivana
		
		Iterator<SimpleHashtable.TableEntry<String,Integer>> it = examMarks.iterator(); 
		TableEntry<String, Integer> te1 = it.next();
		it.remove();
		
		assertEquals("", "Ante", te1.getKey());
		assertEquals("", 3, examMarks.size());
	}
	
	@Test(expected=IllegalStateException.class)
	public void testIteratorRemoveMoreThanOnceOnSameElement(){
		// create collection:
		SimpleHashtable<String,Integer> examMarks = new SimpleHashtable<>(2);
		// fill data:
		examMarks.put("Ivana", 2);
		examMarks.put("Ante", 2);
		examMarks.put("Jasna", 2);
		examMarks.put("Kristina", 5);
		examMarks.put("Ivana", 5); // overwrites old grade for Ivana
		
		Iterator<SimpleHashtable.TableEntry<String, Integer>> iter = examMarks.iterator();
		while (iter.hasNext()) {
			SimpleHashtable.TableEntry<String, Integer> pair = iter.next();
			if (pair.getKey().equals("Ivana")) {
				iter.remove();
				iter.remove(); //throws exception
			}
		}
	}
	
	@Test(expected=ConcurrentModificationException.class)
	public void testIteratorWithRemoveFromOutsideWhileIterating(){
		// create collection:
		SimpleHashtable<String,Integer> examMarks = new SimpleHashtable<>(2);
		// fill data:
		examMarks.put("Ivana", 2);
		examMarks.put("Ante", 2);
		examMarks.put("Jasna", 2);
		examMarks.put("Kristina", 5);
		examMarks.put("Ivana", 5); // overwrites old grade for Ivana
		
		Iterator<SimpleHashtable.TableEntry<String, Integer>> iter = examMarks.iterator();
		while (iter.hasNext()) { //throws exception
			SimpleHashtable.TableEntry<String, Integer> pair = iter.next();
			if (pair.getKey().equals("Ivana")) {
				examMarks.remove("Ivana");
			}
		}
	}
	
	@Test(expected=ConcurrentModificationException.class)
	public void testIteratorWithPutWhileIterating(){
		// create collection:
		SimpleHashtable<String,Integer> examMarks = new SimpleHashtable<>(2);
		// fill data:
		examMarks.put("Ivana", 2);
		examMarks.put("Ante", 2);
		examMarks.put("Jasna", 2);
		examMarks.put("Kristina", 5);
		examMarks.put("Ivana", 5); // overwrites old grade for Ivana
		
		Iterator<SimpleHashtable.TableEntry<String, Integer>> iter = examMarks.iterator();
		while (iter.hasNext()) { //throws exception
			SimpleHashtable.TableEntry<String, Integer> pair = iter.next();
			if (pair.getKey().equals("Ivana")) {
				examMarks.put("Ana", 4);
			}
		}
	}
	
	@Test(expected=ConcurrentModificationException.class)
	public void testIteratorWithClearWhileIterating(){
		// create collection:
		SimpleHashtable<String,Integer> examMarks = new SimpleHashtable<>(2);
		// fill data:
		examMarks.put("Ivana", 2);
		examMarks.put("Ante", 2);
		examMarks.put("Jasna", 2);
		examMarks.put("Kristina", 5);
		examMarks.put("Ivana", 5); // overwrites old grade for Ivana
		
		Iterator<SimpleHashtable.TableEntry<String, Integer>> iter = examMarks.iterator();
		while (iter.hasNext()) { //throws exception
			SimpleHashtable.TableEntry<String, Integer> pair = iter.next();
			if (pair.getKey().equals("Ivana")) {
				examMarks.clear();
			}
		}
	}
}
