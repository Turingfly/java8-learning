package exceptionsAndAssertions;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * 
 * @author chengfeili 
 * Jun 11, 2017 11:21:42 AM
 *
 *         Remember that only a try-with-resources statement is permitted to
 *         omit both the catch and finally blocks. A traditional try statement
 *         must have either or both.
 */
public class TryWithResources {
	/**
	 * 
	 * The new try-with-resources statement automatically closes all
	 * resources opened in the try clause. This feature is also
	 * known as automatic resource management, because Java
	 * automatically takes care of the closing.
	 *             
	 * The resources created in the try clause are only in scope within the try block. 
	 */
	public void newApproach(Path path1, Path path2) throws IOException {
		try (BufferedReader in = Files.newBufferedReader(path1); 
				BufferedWriter out = Files.newBufferedWriter(path2)) {
			out.write(in.readLine());
		}
	}
}

/**
 * AutoCloseable
 * 
 * You can’t just put any random class in a try-with-resources statement. Java
 * commits to closing automatically any resources opened in the try clause.
 *
 */
class TurkeyCage implements AutoCloseable {
	public void close() {
		System.out.println("Close gate");
	}

	public static void main(String[] args) {
		try (TurkeyCage t = new TurkeyCage()) {
			System.out.println("put turkeys in");
		}
	}
}

/**
 * 
 * What happens if the try block also throws an exception? Java 7 added a way to
 * accumulate exceptions. When multiple exceptions are thrown, all but the first
 * are called suppressed exceptions.
 *
 */
class JammedTurkeyCage implements AutoCloseable {
	public void close() throws IllegalStateException {
		throw new IllegalStateException("Cage door does not close");
	}
	// print 
	// caught: turkeys ran off 
	// Cage door does not close
	public static void main(String[] args) {
		try (JammedTurkeyCage t = new JammedTurkeyCage()) {
			throw new IllegalStateException("turkeys ran off");
		} catch (IllegalStateException e) {
			System.out.println("caught: " + e.getMessage());
			for (Throwable t : e.getSuppressed())
				System.out.println(t.getMessage());
		}
	}
}

/**
 * 
 * Resources are closed after the try clause ends and before any catch/finally clauses.
 * Resources are closed in the reverse order from which they were
 * created.
 *
 * Print: 
 * Close: 2 
 * Close: 1 
 * ex 
 * finally
 */
class Auto implements AutoCloseable {
	int num;

	Auto(int num) {
		this.num = num;
	}

	public void close() {
		System.out.println("Close: " + num);
	}

	public static void main(String[] args) {
		try (Auto a1 = new Auto(1); Auto a2 = new Auto(2)) {
			throw new RuntimeException();
		} catch (Exception e) {
			System.out.println("ex");
		} finally {
			System.out.println("finally");
		}
	}
}
