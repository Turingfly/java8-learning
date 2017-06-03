package advancedClassDesign;

/**
 * 
 * @author chengfeili 
 * Jun 2, 2017 9:18:54 PM
 *
 *         Member Inner Classes: It is defined at the member level of a
 *         class(the same level as the methods, instance variables, and
 *         constructors).
 * 
 *         Properties: 
 *         1. Can be declared public, protected, default and private
 *         2. Can extend any class and implement interfaces 
 *         3. Can be abstract or final 
 *         4. Cannot declare static fields or methods 
 *         5. Can access
 *         members of the outer class including private members
 * 
 */
public class MemberInnerClass {
	private String greeting = "Outer";

	protected class Inner {
		public int repeat = 3;
		private String greeting = "Inner";

		public void go() {
			for (int i = 0; i < repeat; i++)
				System.out.println(greeting);
		}
		
		public void sameName() {
			System.out.println(greeting); // Inner
			System.out.println(this.greeting); // Inner
			System.out.println(MemberInnerClass.this.greeting); // Outer
		}
	}

	public void callInner() {
		Inner inner = new Inner();
		inner.go();
	}

	public static void main(String[] args) {
		MemberInnerClass outer = new MemberInnerClass();
		outer.callInner();
		/**
		 * another way to instantiate inner We need an instance of Outer in
		 * order to create Inner. We can't just call new Inner() because Java
		 * won't know which instance of Outer it is associated.
		 */
		MemberInnerClass outer2 = new MemberInnerClass();
		Inner inner = outer2.new Inner();
		inner.go();
		inner.sameName();
	}
}
