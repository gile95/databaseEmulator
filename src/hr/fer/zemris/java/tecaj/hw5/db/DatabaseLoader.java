package hr.fer.zemris.java.tecaj.hw5.db;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * Class which loads data from the given file into a {@link StudentDatabase}.
 * @author Mislav Gillinger
 * @version 1.0
 */
public class DatabaseLoader {

	/** Database where the data will be stored. */
	private static StudentDatabase database;
	
	static {
		List<String> lines = null;
		
		try {
			lines = Files.readAllLines(
					Paths.get("./database.txt"),
					StandardCharsets.UTF_8
					);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		database = new StudentDatabase(lines);
	}
	
	/**
	 * Returns database which was made.
	 * @return Database which was made.
	 */
	public static StudentDatabase getDatabase(){
		return database;
	}
}
