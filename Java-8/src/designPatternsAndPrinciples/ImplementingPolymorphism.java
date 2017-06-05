package designPatternsAndPrinciples;

/**
 * 
 * @author chengfeili 
 * Jun 4, 2017 10:45:20 PM 
 * 
 * Polymorphism is the ability of a single interface to support 
 * multiple underlying forms. In Java, this allows multiple types
 * of objects to be passed to a single method or class.
 */
interface LivesInOcean {
	public void makeSound();
}

class Dolphin implements LivesInOcean {
	public void makeSound() {
		System.out.println("whistle");
	}
}

class Whale implements LivesInOcean {
	public void makeSound() {
		System.out.println("sing");
	}
}

public class ImplementingPolymorphism {
	public void checkSound(LivesInOcean animal) {
		animal.makeSound();
	}

	public static void main(String[] args) {
		ImplementingPolymorphism o = new ImplementingPolymorphism();
		o.checkSound(new Dolphin()); // whistle
		o.checkSound(new Whale()); // sing
	}
}
