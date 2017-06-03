package advancedClassDesign;

/**
 * 
 * @author chengfeili 
 * Jun 2, 2017 10:50:49 PM 
 * 		   A static nested class is a static
 *         class defined at the member level. It can be instantiated without an
 *         object of the enclosing class, so it can't access the instance
 *         variables without an explicit object of the enclosing class. 
 *         1. The nesting creates a name space because the enclosing class name must be
 *         used to refer to it 
 *         2. It can be made private or use one of the other
 *         access modifiers to encapsulate it. 
 *         3. The enclosing class can refer
 *         to the fields and methods of the static nested class.
 */
public class StaticNestedClass {
	static class Nested {
		private int price = 6;
	}

	public static void main(String[] args) {
		Nested nested = new Nested();
		System.out.println(nested.price);
	}
}
