package exceptionsAndAssertions;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * 
 * @author chengfeili
 * Jun 11, 2017 10:45:57 AM
 *
 */
public class UsingMultiCatch {
	public static void main(String[] args) {
		try {
			Path path = Paths.get("a.txt");
			String text = new String(Files.readAllBytes(path));
			LocalDate date = LocalDate.parse(text);
			System.out.println(date);
		} catch (DateTimeParseException | IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
		/**
		 *
		try {
			throw new IOException();
			} catch(IOException | RuntimeException e) {
			e = new RuntimeException(); // DOES NOT COMPILE
			}
		*/
	}
}
