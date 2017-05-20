package hr.fer.zemris.java.tecaj.hw5.collections;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * This is a simple hash table, which supports basic commands to manipulate with inputting and removing data in and 
 * from it. Hash method determines in which slot an entry will be saved. One row of a table can have more entries.
 * @author Mislav Gillinger
 * @version 1.0
 * @param <K> Represents a key of entry stored in this {@link SimpleHashtable}.
 * @param <V> Represents a value of entry stored in this {@link SimpleHashtable}.
 */
public class SimpleHashtable<K,V> implements Iterable<SimpleHashtable.TableEntry<K,V>>{

	/** When this coefficient is reached, the table is expanded on double of its old size. */
	private static final double MARGINAL_CAPACITY = 0.75;
	/** Number of entries stored in this table. */
	private int size;
	/** Storage of all entries. */
	private TableEntry<K,V>[] table;
	/** Counter taking care of table modification while iterating. */
	private int modificationCount;
	
	/**
	 * Creates a new {@link SimpleHashtable} with 16 slots.
	 */
	@SuppressWarnings("unchecked")
	public SimpleHashtable(){
		size = 0;
		table = (TableEntry<K,V>[]) new TableEntry[16];
	}
	
	/**
	 * Creates a new {@link SimpleHashtable} with the number of slots which is the first potency of number 2
	 * greater or equals to the given number.
	 * @param capacity  Number of slots will be the first potency of number 2 greater of equals to this number.
	 */
	@SuppressWarnings("unchecked")
	public SimpleHashtable(int capacity){
		if(capacity < 1) {
			throw new IllegalArgumentException("Wanted capacity has to be greater than 1, but the one "
					+ "given is: " + capacity);
		}
		
		size = 0;
		int numberOfSlots = (int) Math.ceil(Math.log(capacity) / Math.log(2));
		table = (TableEntry<K,V>[]) new TableEntry[numberOfSlots];
	}
	
	/**
	 * Represents a single entry of a {@link SimpleHashtable}.
	 * @author Mislav Gillinger
	 * @version 1.0
	 * @param <K> Represents a key of entry stored in this {@link SimpleHashtable}.
	 * @param <V> Represents a value of entry stored in this {@link SimpleHashtable}.
	 */
	public static class TableEntry<K,V>{
		/** Represents a key of entry stored in this {@link SimpleHashtable}. */
		private K key; 
		/** Represents a value of entry stored in this {@link SimpleHashtable}. */
		private V value;
		/** Reference to a next TableEntry which is in same slot as the previous one. */
		private TableEntry<K,V> next;
		
		/**
		 * Creates a new TableEntry.
		 * @param key Represents a key of entry stored in this {@link SimpleHashtable}.
		 * @param value Represents a value of entry stored in this {@link SimpleHashtable}.
		 * @param next Reference to a next TableEntry which is in same slot as the previous one.
		 */
		public TableEntry(K key, V value, TableEntry<K,V> next){
			if(key == null){
				throw new IllegalArgumentException("Key must not be null!");
			}
			
			this.key = key;
			this.value = value;
			this.next = next;
		}
		
		/**
		 * Fetches the {@link #key}.
		 * @return The {@link #key}.
		 */
		public K getKey(){
			return this.key;
		}
		
		/**
		 * Fetches the {@link #value}.
		 * @return The {@link #value}.
		 */
		public V getValue(){
			return this.value;
		}
		
		/**
		 * Sets the {@link #value}.
		 * @param value New {@link #value}.
		 */
		public void setValue(V value){
			this.value = value;
		}
		
		@Override
		public String toString(){
			return key + "=" + value;
		}
	}
	
	/**
	 * Assigns the given value if a key already exists in a table, otherwise creates a new TableEntry from
	 * the given key and value.
	 * @param key Represents a key of entry that will be stored in this {@link SimpleHashtable}.
	 * @param value Represents a value of entry that will be stored in this {@link SimpleHashtable}.
	 */
	public void put(K key, V value){
		if(key == null){
			throw new IllegalArgumentException("Key must not be null!");
		}
		
		int slot = Math.abs(key.hashCode()) % table.length;
		
		if(containsKey(key)){
			for(TableEntry<K,V> current = table[slot]; current != null; current = current.next){
				if(current.key.equals(key)){
					current.value = value;
					break;
				}
			}
		}	
		else{
			TableEntry<K,V> newEntry = new TableEntry<K,V>(key, value, null);
			
			if(table[slot] == null){
				table[slot] = newEntry;
				size++;
				modificationCount++;
				checkEfficiency();
				return;
			}
	
			TableEntry<K,V> current;
			for(current = table[slot]; current.next != null; current = current.next);
			current.next = newEntry;
			size++;
			modificationCount++;
			checkEfficiency();
		}
	}	
	
	/**
	 * Searches for a value assigned to the given key.
	 * @param key Searches for a value assigned to this key.
	 * @return Value Value which is assigned to the given key.
	 */
	public V get(Object key){
		if(!containsKey(key)){
			return null;
		}
		else{
			int slot = Math.abs(key.hashCode()) % table.length;
			for(TableEntry<K,V> current = table[slot]; current != null; current = current.next){
				if(current.key.equals(key)){
					if(current.value != null){
						return current.value;
						}
					else{
						return null;
					}
				}
			}
		}
		return null;
	}
	
	/**
	 * Returns a number of elements stored in this table.
	 * @return A number of elements stored in this table.
	 */
	public int size(){
		return size;
	}
	
	/**
	 * Searches a table for the given key.
	 * @param key Key to be searched for in the table.
	 * @return True if a table contains the given key, false otherwise.
	 */
	public boolean containsKey(Object key){
		if(key == null){
			return false;
		}
		
		int slot = Math.abs(key.hashCode()) % table.length;
		for(TableEntry<K,V> current = table[slot]; current != null; current = current.next){
			if(current.key.equals(key)){
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Searches a table for the given value.
	 * @param value Value to be searched for in the table.
	 * @return True if a table contains the given value, false otherwise.
	 */
	public boolean containsValue(Object value){
		for(int i = 0; i < table.length; i++){
			if(table[i] != null){
				for(TableEntry<K,V> current = table[i]; current != null; current = current.next){
					if(current.value.equals(value)){
						return true;
					}
				}
			}
		}
		return false;
	}
	
	/**
	 * Removes the table entry which key is equals to the given one from the table.
	 * @param key Removes a table entry with this key from the table.
	 */
	public void remove(Object key){
		if(key == null || !containsKey(key)){
			return;
		}
		
		int slot = Math.abs(key.hashCode()) % table.length;
		
		if(table[slot].key.equals(key)){
			table[slot] = table[slot].next;
			size--;
			modificationCount++;
			return;
		}
		
		for(TableEntry<K,V>previous = table[slot], current = table[slot].next;
			current != null;
			previous = previous.next, current = current.next){
				if(current.key.equals(key)){
					previous.next = current.next;
					size--;
					modificationCount++;
				}
		}
	}
	
	/**
	 * Checks if a table is empty.
	 * @return True if a table is empty, false otherwise.
	 */
	public boolean isEmpty(){
		return size == 0;
	}
	
	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		
		sb.append("[");
		
		for(int i = 0; i < table.length; i++){
			if(table[i] != null){
				for(TableEntry<K,V> current = table[i]; current != null; current = current.next){
					sb.append(current.toString());
					sb.append(", ");
				}
			}
		}
		
		String retString = sb.toString().substring(0, sb.toString().length() - 2);
		
		return retString + "]";
	}
	
	/**
	 * Clears the table, after calling this method table's size becomes zero.
	 */
	public void clear(){
		for(int i = 0; i < table.length; i++){
			if(table[i] != null){
				table[i] = null;
			}
		}
		size = 0;
		modificationCount++;
	}
	
	/**
	 * Checks if {@link #MARGINAL_CAPACITY} of a table is used, and if it is, doubles the table.
	 */
	@SuppressWarnings("unchecked")
	private void checkEfficiency(){
		if(size >= MARGINAL_CAPACITY * table.length){
			
			TableEntry<K,V>[] assistantTable = table;
			table = (TableEntry<K,V>[]) new TableEntry[assistantTable.length * 2];
			
			size = 0;
			
			for(int i = 0; i < assistantTable.length; i++){
				if(assistantTable[i] != null){
					for(TableEntry<K,V> current = assistantTable[i]; current != null; current = current.next){
						put(current.key, current.value);  //in method put, modificationCount increases
					}
				}
			}
		}
	}
	
	@Override
	public Iterator<SimpleHashtable.TableEntry<K,V>> iterator(){
		return new IteratorImpl();
	}
	
	/**
	 * Class which implements an interface {@link Iterator}.
	 * @author Mislav Gillinger
	 * @version 1.0
	 */
	private class IteratorImpl implements Iterator<SimpleHashtable.TableEntry<K, V>>{

		/** Number of elements left to iterate. */
		int elementsLeft = size;
		/** Current slot of the iteration. */
		int currentSlot = 0;
		/** Current element of the iteration. */
		TableEntry<K,V> currentElement = null;
		/** Last returned element. */
		TableEntry<K,V> lastReturned;
		/** Counter taking care of table modification while iterating. */
		int currentModificationCount = modificationCount;
		
		@Override
		public boolean hasNext() {
			if(currentModificationCount != modificationCount){
				throw new ConcurrentModificationException("While iterating, collection was changed, but "
						+ "not by iterator!");
			}
			
			return elementsLeft != 0;
		}

		@Override
		public SimpleHashtable.TableEntry<K,V> next() {
			if(hasNext()){				
				while (currentElement == null){
					currentElement = table[currentSlot++];
				}
				
				lastReturned = currentElement;
				currentElement = currentElement.next;
				elementsLeft--;
				return lastReturned;
			}
			else{
				throw new NoSuchElementException();
			}
		}
		
		public void remove(){
			if(currentModificationCount != modificationCount){
				throw new ConcurrentModificationException("While iterating, collection was changed, but "
						+ "not by iterator!");
			}
			if(!SimpleHashtable.this.containsKey(lastReturned.key)){
				throw new IllegalStateException("This element was already removed!");
			}
			
			//currentModificationCount++;
			SimpleHashtable.this.remove(lastReturned.key);
			currentModificationCount = modificationCount;
		}
	}
}
