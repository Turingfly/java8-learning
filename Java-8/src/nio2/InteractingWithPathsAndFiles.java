package nio2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author chengfeili 
 * Jun 20, 2017 9:57:52 PM
 * 
 *         Path Object VS Actual File A Path object is not a file but a
 *         representation of a location with the file system. Most operations
 *         available in the Path and Paths classes can be accomplished
 *         regardless of whether the underlying file that the path object
 *         references actually exists.
 * 
 *         Java is fond of singular names for container classes and plural names
 *         for factory and helper classes. I
 * 
 *         the Files class operates on Path instances, not File instances. Keep
 *         in mind that File belongs to the legacy java.io API, while Files
 *         belongs to the NIO.2 API.
 */
public class InteractingWithPathsAndFiles {

	public void testFile() {
		Files.exists(Paths.get("/home/zoo"));
		Files.exists(Paths.get("/home/zoo/dog.jpg"));

		/**
		 * The isSameFile() method first checks if the Path objects are equal in
		 * terms of equal(), and if so, it automatically returns true without
		 * checking to see if either files exists. If the Path object equals()
		 * comparison returns false, then it locates each file to which the path
		 * refers in the file system and determines if they are the same,
		 * throwing a checked IOException if either file does not exist.
		 */
		try {
			// cobra is a symbolic link to the snake
			System.out.println(Files.isSameFile(Paths.get("/user/home/cobra"), Paths.get("/user/home/snake"))); // true
			System.out.println(Files.isSameFile(Paths.get("/user/tree/../monkey"), Paths.get("/user/monkey"))); // true
			System.out.println(Files.isSameFile(Paths.get("/leaves/./giraffe.exe"), Paths.get("/leaves/giraffe.exe"))); // false
			System.out.println(Files.isSameFile(Paths.get("/flamingo/tail.data"), Paths.get("/cardinal/tail.data")));
		} catch (IOException e) {
			// Handle file I/O exception...
		}

		/**
		 * 
		 * The example creates a new directory, field, in the directory /bison,
		 * assuming /bison exists; or else an exception is thrown. Contrast this
		 * with the second example that creates the directory green along with
		 * any of the following parent directories if they do not already exist,
		 * such as /bison, /bison/field, or /bison/pasture.
		 */
		try {
			Files.createDirectory(Paths.get("/bison/field"));
			Files.createDirectories(Paths.get("/bison/field/pasture/green"));
		} catch (IOException e) {
			// Handle file I/O exception...
		}

		/**
		 * Duplicating File Contents with copy()
		 * 
		 * The copy() method throws the checked IOException, such as when the
		 * file or directory does not exist or cannot be read. Directory copies
		 * are shallow rather than deep, meaning that files and sub directories
		 * within the directory are not copied. T
		 */
		try {
			Files.copy(Paths.get("/panda"), Paths.get("/panda-save"));

			Files.copy(Paths.get("/panda/bamboo.txt"), Paths.get("/panda-save/bamboo.txt"));
		} catch (IOException e) {
			// Handle file I/O exception...
		}

		try (InputStream is = new FileInputStream("source-data.txt");
				OutputStream out = new FileOutputStream("output-data.txt")) {
			// Copy stream data to file
			Files.copy(is, Paths.get("c:\\mammals\\wolf.txt"));
			// Copy file data to stream
			Files.copy(Paths.get("c:\\fish\\clown.xsl"), out);
		} catch (IOException e) {
			// Handle file I/O exception... }
		}
		// Changing a File Location with move()
		try {
			Files.move(Paths.get("c:\\zoo"), Paths.get("c:\\zoo-new"));
			Files.move(Paths.get("c:\\user\\addresses.txt"), Paths.get("c:\\zoo-new\\addresses.txt"));
		} catch (IOException e) {
			// Handle file I/O exception...
		}

		/**
		 * Removing a File with delete() and deleteIfExists()
		 * 
		 * The first example deletes the features.txt file in the vulture
		 * directory, and it throws a NoSuchFileException if the file or
		 * directory does not exist. The second example deletes the pigeon
		 * directory assuming it is empty. If the pigeon directory does not
		 * exist, then the second line will not throw an exception.
		 */
		try {
			Files.delete(Paths.get("/vulture/feathers.txt"));
			Files.deleteIfExists(Paths.get("/pigeon"));
		} catch (IOException e) {
			// Handle file I/O exception...
		}
		/**
		 * 
		 * Reading and Writing File Data with newBufferedReader() and
		 * newBufferedWriter()
		 */

		Path path = Paths.get("/animals/gopher.txt");
		try (BufferedReader reader = Files.newBufferedReader(path, Charset.forName("US-ASCII"))) {
			// Read from the stream
			String currentLine = null;
			while ((currentLine = reader.readLine()) != null)
				System.out.println(currentLine);
		} catch (IOException e) {
			// Handle file I/O exception... }
		}

		try (BufferedWriter writer = Files.newBufferedWriter(path, Charset.forName("UTF-16"))) {
			writer.write("Hello World");
		} catch (IOException e) {
			// Handle file I/O exception... }
		}

		/**
		 * Reading Files with readAllLines()
		 */
		try {
			final List<String> lines = Files.readAllLines(path);
			for (String line : lines) {
				System.out.println(line);
			}
		} catch (IOException e) {
			// Handle file I/O exception...
		}
	}
}