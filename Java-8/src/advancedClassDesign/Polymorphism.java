package advancedClassDesign;

/**
 * 
 * @author chengfeili 
 * Jun 27, 2017 6:11:11 PM
 *
 *         Polymorphism is the ability for an object to vary its behavior based
 *         on its type
 * 
 *         Even though HumanBeing is used, the JVM decides at runtime which
 *         method to call based on the type of the object assigned, not the
 *         variable's reference type.
 * 
 *         This is called virtual method invocation, a fancy name for
 *         overriding.
 * 
 *         Overriding is also known as dynamic polymorphism because the type of
 *         the object is decided at RUN time.
 * 
 *         In contrast, overloading is also called static polymorphism because
 *         it's resolved at COMPILE time.
 */
public class Polymorphism {
	public static void main(String[] args) {
		HumanBeing[] someHumans = new HumanBeing[3];
		someHumans[0] = new Man();
		someHumans[1] = new Woman();
		someHumans[2] = new Baby();
		for (int i = 0; i < someHumans.length; i++) {
			someHumans[i].dress();
			System.out.println();
		}
	}
}

abstract class HumanBeing {
	public abstract void dress();
}

class Man extends HumanBeing {
	public void dress() {
		System.out.println("Man");
	}
}

class Woman extends HumanBeing {
	public void dress() {
		System.out.println("Woman");
	}
}

class Baby extends HumanBeing {
	public void dress() {
		System.out.println("Baby");
	}
}