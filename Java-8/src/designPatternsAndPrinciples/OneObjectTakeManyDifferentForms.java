package designPatternsAndPrinciples;

/**
 * 
 * @author chengfeili 
 * Jun 4, 2017 10:52:45 PM
 * 
 *         If you use a variable to refer to an object, then only the methods or
 *         variables that are part of the variable’s reference type can be
 *         called without an explicit
 * 
 *         --------Object and reference-------- 
 *         In Java, all objects are
 *         accessed by reference, so as a developer you never have direct access
 *         to the memory of the object itself.
 * 
 *         1. The type of the object determines which properties exist within
 *         the object in memory. 
 *         2. The type of the reference to the object
 *         determines which methods and variables are accessible to the Java
 *         program.
 * 
 *         --------Casting Object reference--------
 *         1. Casting an object from a subclass to a superclass doesn’t require
 *         an explicit cast. 
 *         2. Casting an object from a superclass to a
 *         subclass requires an explicit cast. 
 *         3. The compiler will not allow
 *         casts to unrelated types. 
 *         4. Even when the code compiles without
 *         issue, an exception may be thrown at runtime if the object being cast
 *         is not actually an instance of that class.
 */

class Primate {
	public boolean hasHair() {
		return true;
	}
}

@FunctionalInterface
interface HasTail {
	public boolean isTailStriped();
}

public class OneObjectTakeManyDifferentForms extends Primate implements HasTail {
	public int age = 10;

	public boolean isTailStriped() {
		return false;
	}

	public static void main(String[] args) {
		OneObjectTakeManyDifferentForms lemur = new OneObjectTakeManyDifferentForms();
		System.out.println(lemur.age); // 10

		HasTail hasTail = lemur;
		System.out.println(hasTail.isTailStriped()); // false
		// System.out.println(hasTail.hasHair()); // Does not compile

		Primate primate = lemur;
		// OneObjectTakeManyDifferentForms lemur2 = primate; // Does not compile
		// OneObjectTakeManyDifferentForms lemur2 = (OneObjectTakeManyDifferentForms)primate;  // explicit cast
		System.out.println(primate.hasHair()); // true

	}
}