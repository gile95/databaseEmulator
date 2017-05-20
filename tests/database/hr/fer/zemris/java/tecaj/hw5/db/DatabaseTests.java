package hr.fer.zemris.java.tecaj.hw5.db;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

public class DatabaseTests {

	@Test
	public void testIndexQuery() throws QueryException{
		
		StudentDatabase database = DatabaseLoader.getDatabase();
		
		StudentDB.checkIndexQuery("jmbag=\"0000000001\"");
		StudentRecord student = StudentDB.processIndexQuery("jmbag=\"0000000001\"", database);
		
		assertEquals("", "Marin", student.getFirstName());
	}
	
	@Test(expected=QueryException.class)
	public void testIndexQueryWithInvalidAttribute() throws QueryException{
		
		StudentDB.checkIndexQuery("firstName=\"Gabriel\"");
	}
	
	@Test(expected=QueryException.class)
	public void testIndexQueryWithInvalidOperator() throws QueryException{
		
		//StudentDatabase database = DatabaseLoader.getDatabase();
		
		StudentDB.checkIndexQuery("jmbag>\"0000000001\"");
		//StudentRecord student = StudentDB.processIndexQuery("jmbag>\"0000000001\"", database);
	}
	
	@Test(expected=QueryException.class)
	public void testIndexQueryWithInvalidJmbag() throws QueryException{
		
		//StudentDatabase database = DatabaseLoader.getDatabase();
		
		StudentDB.checkIndexQuery("jmbag=\"0000AB\"");
		//StudentRecord student = StudentDB.processIndexQuery("jmbag=\"0000AB\"", database);
	}
	
	@Test(expected=QueryException.class)
	public void testIndexQueryWithInvalidJmbagLength() throws QueryException{
		
		//StudentDatabase database = DatabaseLoader.getDatabase();
		
		StudentDB.checkIndexQuery("jmbag=\"000001\"");
		//StudentRecord student = StudentDB.processIndexQuery("jmbag=\"000001\"", database);
	}
	
	@Test
	public void testQueryEqualsOperator() throws QueryException{
		
		StudentDatabase database = DatabaseLoader.getDatabase();
		
		QueryFilter qf = new QueryFilter("firstName = \"Ivan\"");
		List<StudentRecord> filtered = database.filter(qf);		
		
		assertEquals("", 5, filtered.size());
	}
	
	@Test
	public void testQueryLikeOperator() throws QueryException{
		
		StudentDatabase database = DatabaseLoader.getDatabase();
		
		QueryFilter qf = new QueryFilter("lastName LIKE \"B*\"");
		List<StudentRecord> filtered = database.filter(qf);		
		
		assertEquals("", 4, filtered.size());
	}
	
	@Test
	public void testQueryLikeOperator2() throws QueryException{
		
		StudentDatabase database = DatabaseLoader.getDatabase();
		
		QueryFilter qf = new QueryFilter("lastName LIKE \"Be*\"");
		List<StudentRecord> filtered = database.filter(qf);		
		
		assertEquals("", 0, filtered.size());
	}
	
	@Test
	public void testQueryDifferentOperator() throws QueryException{
		
		StudentDatabase database = DatabaseLoader.getDatabase();
		
		QueryFilter qf = new QueryFilter("lastName != \"Nemaovogprezimena\"");
		List<StudentRecord> filtered = database.filter(qf);		
		
		assertEquals("All last names, 63 of them.", 63, filtered.size());
	}
	
	@Test
	public void testQueryGreaterThanOperator() throws QueryException{
		
		StudentDatabase database = DatabaseLoader.getDatabase();
		
		QueryFilter qf = new QueryFilter("lastName > \"Ž\"");
		List<StudentRecord> filtered = database.filter(qf);		
		
		assertEquals("There is only one last name starting with 'Ž'.", 1, filtered.size());
	}
	
	@Test(expected=QueryException.class)
	public void testQueryUnknownOperator() throws QueryException{
		
		StudentDatabase database = DatabaseLoader.getDatabase();
		
		QueryFilter qf = new QueryFilter("lastName !> \"Barić\"");
		List<StudentRecord> filtered = database.filter(qf);		
	}
	
	@Test(expected=QueryException.class)
	public void testQueryUnknownAttribute() throws QueryException{
		
		StudentDatabase database = DatabaseLoader.getDatabase();
		
		QueryFilter qf = new QueryFilter("faculty = \"FER\"");
		List<StudentRecord> filtered = database.filter(qf);		
	}
	
	@Test(expected=QueryException.class)
	public void testQueryJmbagWithLetters() throws QueryException{
		
		StudentDatabase database = DatabaseLoader.getDatabase();
		
		QueryFilter qf = new QueryFilter("jmbag = \"00254BA\"");
		List<StudentRecord> filtered = database.filter(qf);		
	}
}
