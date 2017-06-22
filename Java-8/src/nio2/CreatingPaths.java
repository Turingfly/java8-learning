package nio2;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 
 * @author chengfeili 
 * Jun 20, 2017 9:31:31 AM
 *
 *         Path is a direct replacement for the legacy java.io.File class, and
 *         conceptually it contains many of the same properties. For example,
 *         both File and Path objects may refer to a file or a directory. Both
 *         also may refer to an absolute path or relative path within the file
 *         system.
 */
public class CreatingPaths {
	public void path() {
		Path path1 = Paths.get("/home/zoo");
		Path path2 = Paths.get("/", "home", "zoo");
		// Use URI values for both local and remote paths
		// URIs must reference absolute paths at runtime;
		Path path3 = Paths.get("file:///home/zoo");
		URI uri = path3.toUri();
		try {
			FileSystem fileSystem = FileSystems.getFileSystem(new URI("http://www.selikoff.net"));
			Path path4 = fileSystem.getPath("duck.txt");
		} catch (URISyntaxException e) {

		}
		// Legacy File Instances
		File file = new File("/home/zoo");
		Path path5 = file.toPath();
		File file2 = path5.toFile();
	}
}
