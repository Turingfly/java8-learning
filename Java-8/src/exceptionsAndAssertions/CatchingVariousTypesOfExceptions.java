package exceptionsAndAssertions;

/**
 * 
 * @author chengfeili 
 * Jun 10, 2017 7:49:39 AM
 *
 * 1. recognize if the exception is a checked or an unchecked exception.
 * 2. determine if any of the exceptions are subclasses of the others.
 */
class A extends RuntimeException {

}

class B extends RuntimeException {

}

class C extends B {

}

public class CatchingVariousTypesOfExceptions {

	/**
	 * If test() doesn't throw an exception, nothing is printed out. If catch A,
	 * print "A". If catch B, print "B".
	 * 
	 * A and B don't inherit from each other. So the order of the catch blocks
	 * could be reversed.
	 */
	public void notSub() {
		try {
			test();
		} catch (A e) {
			System.out.print("A");
		} catch (B e) {
			System.out.print("B");
		}
	}

	/**
	 * C inherits from B.
	 * 
	 * If the more specific C exception is thrown, the first catch block runs.
	 * If not, Java checks if the superclass B exception is thrown and catches
	 * it. This time, the order of the catch blocks does matter. The reverse
	 * does not work and does not compile.
	 */
	public void sub() {
		try {
			test();
		} catch (C e) {
			System.out.print("C");
		} catch (B e) {
			System.out.print("B");
		}
	}

	private void test() {
	}
}
