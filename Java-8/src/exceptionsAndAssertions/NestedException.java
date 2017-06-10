package exceptionsAndAssertions;

import java.io.FileReader;
import java.io.IOException;

/**
 * 
 * @author chengfeili 
 * Jun 10, 2017 8:04:26 AM
 *
 */
public class NestedException {

	public void test() {
		FileReader reader = null;
		try {
			reader = read();
		} catch (IOException e) {
			try {
				if (reader != null)
					reader.close();
			} catch (IOException inner) {
			}
		}
	}

	private FileReader read() throws IOException {
		// CODE GOES HERE
		return null;
	}

	/**
	 * Line 46 throws an exception, which is caught on line 47. The catch block
	 * then throws an exception on line 48. If there were no finally block, the
	 * exception from line 48 would be thrown. However, the finally block runs
	 * after the try block.
	 * 
	 * Since the finally block throws an exception of its own on line 50, this
	 * one gets thrown. The exception from the catch block gets forgotten about.
	 * This is why you often see another try / catch inside a finally block—to
	 * make sure it doesn’t mask the exception from the catch block.
	 * 
	 */
	public void mask() throws Exception {
		try {
			throw new RuntimeException();
		} catch (RuntimeException e) {
			throw new RuntimeException();
		} finally {
			throw new Exception();
		}
	}

	public String exceptions() {
		String result = "";
		String v = null;
		try {
			try {
				result += "before ";
				v.length();
				result += "after ";
			} catch (NullPointerException e) {
				result += "catch ";
				throw new RuntimeException();
			} finally {
				result += "finally ";
				throw new Exception();
			}
		} catch (Exception e) {
			result += "done ";
		}
		return result;
	}

	public static void main(String[] args) {
		NestedException ne = new NestedException();
		ne.test();
		System.out.println(ne.exceptions()); // before catch finally done 
	}
}
