package advancedClassDesign;

/**
 * 
 * @author chengfeili 
 * Jun 2, 2017 9:54:47 PM 
 * 		   Local inner class is a nested class defined within a method.
 * 
 *         Properties: 
 *         1. They do not have an access specifier 
 *         2. They cannot be declared static and cannot declare static fields or methods 
 *         3. They have access to all fields and methods of the enclosing class 
 *         4. They do not have access to local variables of a method unless those
 *         variables are final or effectively final(only set in the line in which it is declared).
 */
public class LocalInnerClass {
	private int length = 5;

	public void calculate() {
		final int width = 20;
		class Inner {
			public void multily() {
				System.out.println(length * width);
			}
		}
		Inner inner = new Inner();
		inner.multily();
	}

	public static void main(String[] args) {
		LocalInnerClass outer = new LocalInnerClass();
		outer.calculate();
	}
}
