package nio2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.nio.file.attribute.UserPrincipal;

/**
 * 
 * @author chengfeili 
 * Jun 21, 2017 9:07:47 PM
 * 
 *         The Files class also provides numerous methods accessing file and
 *         directory meta data, referred to as file attributes. Put simply,
 *         meta data is data that describes other data.
 */
public class UnderstandingFileAttributes {
	public void basicFileAttribute() {
		/**
		 * The first example returns true if fur.jpg is a directory or a
		 * symbolic link to a directory and false otherwise.
		 * 
		 * You may notice when browsing the Files API that isDirectory(),
		 * isRegularFile(), and isSymbolicLink() do not throw an exception if
		 * the path does not exist,
		 * 
		 */
		Files.isDirectory(Paths.get("/canine/coyote/fur.jpg"));
		Files.isRegularFile(Paths.get("/canine/types.txt"));
		Files.isSymbolicLink(Paths.get("/canine/coyote"));

		try {
			System.out.println(Files.isHidden(Paths.get("/walrus.txt")));
		} catch (IOException e) {
			// Handle file I/O exception...
		}

		/**
		 * This is important in file systems where the filename can be viewed
		 * within a directory, but the user may not have permission to read the
		 * contents of the file or execute it.
		 * 
		 * the isReadable() and isExecutable() methods do not throw exceptions
		 * if the file does not exist but instead return false.
		 */
		System.out.println(Files.isReadable(Paths.get("/seal/baby.png")));
		System.out.println(Files.isExecutable(Paths.get("/seal/baby.png")));

		/**
		 * The example outputs the number of bytes in the file
		 */
		try {
			System.out.println(Files.size(Paths.get("/zoo/c/animals.txt")));
		} catch (IOException e) {
			// Handle file I/O exception...
		}

		try {
			final Path path = Paths.get("/rabbit/food.jpg");
			System.out.println(Files.getLastModifiedTime(path).toMillis());
			Files.setLastModifiedTime(path, FileTime.fromMillis(System.currentTimeMillis()));
			System.out.println(Files.getLastModifiedTime(path).toMillis());
		} catch (IOException e) {
			// Handle file I/O exception...
		}

		/**
		 * Managing Ownership with getOwner() and setOwner()
		 */
		try {
			// Read owner of file
			Path path = Paths.get("/chicken/feathers.txt");
			System.out.println(Files.getOwner(path).getName());
			// Change owner of file
			UserPrincipal owner = path.getFileSystem().getUserPrincipalLookupService().lookupPrincipalByName("jane");
			Files.setOwner(path, owner);
			// Output the updated owner information
			System.out.println(Files.getOwner(path).getName());
		} catch (IOException e) {
			// Handle file I/O exception...
		}
	}

	/**
	 * A view is a group of related attributes for a particular file system
	 * type. A file may support multiple views, allowing you to retrieve and
	 * update various sets of information about the file.
	 */
	public void improvingAccessWithViews() throws IOException {
		// Reading attributes
		Path path = Paths.get("/turtles/sea.txt");
		BasicFileAttributes data = Files.readAttributes(path, BasicFileAttributes.class);
		System.out.println("Is path a directory? " + data.isDirectory());
		System.out.println("Is path a regular file? " + data.isRegularFile());
		System.out.println("Is path a symbolic link? " + data.isSymbolicLink());
		System.out.println("Path not a file, directory, nor symbolic link? " + data.isOther());
		System.out.println("Size (in bytes): " + data.size());
		System.out.println("Creation date/time: " + data.creationTime());
		System.out.println("Last modified date/time: " + data.lastModifiedTime());
		System.out.println("Last accessed date/time: " + data.lastAccessTime());
		System.out.println("Unique file identifier (if available): " + data.fileKey());

		// Modifying Attributes
		BasicFileAttributeView view = Files.getFileAttributeView(path, BasicFileAttributeView.class);
		BasicFileAttributes data2 = view.readAttributes();
		FileTime lastModifiedTime = FileTime.fromMillis(data2.lastModifiedTime().toMillis() + 10_000);
		view.setTimes(lastModifiedTime, null, null);
	}
}
