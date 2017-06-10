package exceptionsAndAssertions;

import java.io.FileNotFoundException;
import java.text.NumberFormat;

/**
 * 
 * @author chengfeili 
 * Jun 10, 2017 8:29:12 AM
 *
 */
public class CommonExceptionTypes {

	/**
	 * Runtime Exception (Unchecked Exception) It is unexpected, doesn't have to
	 * be handled or declared. It can be thrown by the programmer or by the JVM.
	 * 
	 */
	public void runtimeException() {
		// ArithmeticException
		int n = 11 / 0;

		// ArrayIndexOutOfBoundsException
		int[] count = new int[3];
		System.out.println(count[-1]);

		// ClassCastException
		// String type = "a";
		// Integer num = (Integer) type; // Does not compile
		String type = "a";
		Object obj = type;
		Integer num = (Integer) obj;

		// NullPointerException
		String name = null;
		System.out.println(name.length());

		// NumberFormatException
		Integer.parseInt("abc");

		// IllegalArgumentException
		throw new IllegalArgumentException("must not be negative");
	}

	public void checkedException() {
		// FileNotFoundException // subclass of IOExcepption

		// IOException;
	}

	public void errors() {
		/**
		 * ExceptionInInitializerError Thrown by the JVM when a static
		 * initializer throws an exception and doesn’t handle it .
		 */

		/**
		 * StackOverflowError Thrown by the JVM when a method calls itself too
		 * many times (this is called infinite recursion because the method
		 * typically calls itself without end)
		 */

		/**
		 * NoClassDefFoundError Thrown by the JVM when a class that the code
		 * uses is available at compile time but not runtime
		 */
	}
}
