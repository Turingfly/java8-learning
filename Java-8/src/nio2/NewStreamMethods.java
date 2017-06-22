package nio2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 
 * @author chengfeili 
 * Jun 22, 2017 2:24:36 PM
 *
 */
public class NewStreamMethods {
	/**
	 * The Files.walk(path) method returns a Stream<Path> object that traverses
	 * the directory in a depth-first, lazy manner. By lazy, we mean the set of
	 * elements is built and read while the directory is being traversed.
	 * 
	 * Avoiding Circular Paths. the walk() method will not traverse symbolic
	 * links by default.
	 */
	public void walkingADirectory() {
		Path path = Paths.get("/bigcats");
		try {
			Files.walk(path).filter(p -> p.toString().endsWith(".java")).forEach(System.out::println);
		} catch (IOException e) {
			// Handle file I/O exception...
		}
	}

	public void searchingADirectory() {
		Path path = Paths.get("/bigcats");
		long dateFilter = 1420070400000l;
		try {
			Stream<Path> stream = Files.find(path, 10,
					(p, a) -> p.toString().endsWith(".java") && a.lastModifiedTime().toMillis() > dateFilter);
			stream.forEach(System.out::println);
		} catch (Exception e) {
			// Handle file I/O exception...
		}
	}

	/**
	 * return a list of File objects representing the contents of the directory
	 * that are direct children of the parent.
	 */
	public void listingDirectoryContents() {
		try {
			Path path = Paths.get("ducks");
			Files.list(path).filter(p -> !Files.isDirectory(p)).map(p -> p.toAbsolutePath())
					.forEach(System.out::println);
		} catch (IOException e) {
			// Handle file I/O exception...
		}
	}

	public void printingFileContents() throws IOException {
		Path path = Paths.get("/fish/sharks.log");
		try {
			System.out.println(Files.lines(path).filter(s -> s.startsWith("WARN ")).map(s -> s.substring(5))
					.collect(Collectors.toList()));
		} catch (IOException e) {
			// Handle file I/O exception...
		}

		Files.readAllLines(Paths.get("birds.txt")).forEach(System.out::println);
		Files.lines(Paths.get("birds.txt")).forEach(System.out::println);
		/**
		 * The first code snippet reads the entire file into memory and then
		 * performs a print operation on the resulting object. The second code
		 * snippet reads the lines lazily and prints them as they are being
		 * read. The advantage of the second code snippet is that it does not
		 * require the entire file to be stored in memory as it is being read.
		 */

		// Files.readAllLines(path).filter(s -> s.length() > 2).forEach(System.out::println);
		Files.lines(path).filter(s -> s.length() > 2).forEach(System.out::println);
		/**
		 * The first line does not compile because the filter() operation cannot
		 * be applied to a Collection without first converting it to a Stream
		 * using the stream() method.
		 */
	}
}
