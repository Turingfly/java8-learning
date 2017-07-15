
package exceptionsAndAssertions;

/**
 * 
 * @author chengfeili
 * Jun 10, 2017 10:57:52 AM
 *
 */
class checked extends Exception{
	
}
public class CallingMethodsThatThrowExceptions {
	// checked is a checked exception.
	// it must be handled or declared.
	
	// method1: declare exception
	/*	
	 * public static void main(String[] args) throws checked {
		eat();
	}*/
	
	// method2: handle exception
	public static void main(String[] args) {
		try {
			eat();
		} catch (checked e) {
			System.out.println("handle");
		}
		
		// print an exception
		try {
			hop();
		} catch (Exception e) {
			System.out.println(e);
			System.out.println(e.getMessage());
			e.printStackTrace();
			/*
			 * java.lang.RuntimeException: cannot hop 
			 * cannot hop
			 * java.lang.RuntimeException: cannot hop
			 * at trycatch.Handling.hop(Handling.java:15) 
			 * at trycatch.Handling.main(Handling.java:7)
			*/
		}
	}

	private static void hop() {
		throw new RuntimeException("cannot hop");
	}

	private static void eat() throws checked {

	}

	/**
	public void bad() {
		try {
			eatCarrot();
		} catch (checked e) { // DOES NOT COMPILE
			System.out.print("sad rabbit");
		}
	}
	*/

	public void good() throws checked {
		eatCarrot();
	}

	private static void eatCarrot() {
	}
	
	/**
	 * Java knows that eatCarrot() can’t throw a checked exception—which means
	 * there’s no way for the catch block in bad() to be reached. In comparison,
	 * good() is free to declare other exceptions.
	 */
	
	// subclasses
	/**
	 * When a class overrides a method from a superclass or implements a method
	 * from an interface, it’s not allowed to add new checked exceptions to the
	 * method signature
	 * 
	 * A subclass is allowed to declare fewer exceptions than the superclass or
	 * interface. This is legal because callers are already handling them.
	 * 
	 * declare new runtime exceptions in a subclass method is that the
	 * declaration is redundant. Methods are free to throw any runtime
	 * exceptions they want without mentioning them in the method declaration.
	 */

}
