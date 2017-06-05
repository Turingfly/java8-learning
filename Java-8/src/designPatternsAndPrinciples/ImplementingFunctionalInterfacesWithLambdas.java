package designPatternsAndPrinciples;

import java.util.function.Predicate;

/**
 * 
 * @author chengfeili 
 * Jun 4, 2017 10:18:55 PM
 *
 *
 *	---------Valid lambda syntax:------------------
 *	() -> new Duck()
 *	d -> {return d.quack();}
 *	(Duck d) -> d.quack()
 *	(Animal a, Duck d) -> d.quack()
 *	
 *	() - true
 *	a -> {return a.startsWith("a");}
 *	(String a) -> a.startsWith("a)
 *	(int x) -> {}
 *	(int y) -> {return;}
 *	(a, b) -> a.startsWith("a)
 *	
 *
 *	-----------Invalid lambda syntax: ---------------
 *	Duck d -> d.quack()      	 	==> (Duck d) -> d.quack()
 *	a,d -> d.quack()                ==> (a, d) -> d.quack()
 *	Animal a, Duck d -> d.quack()   ==> (Animal a, Duck d) -> d.quack()
 *
 *	a, b - a.startsWith("a")        ==> (a, b) -> a.startsWith("a")
 *
 *	(when one parameter has a data type listed, though, 
 *	all parameters must provide a data type)
 *	(int y, z) -> {int x = 1; return y + 10;}  
 * 	
 *	(a, b) -> {int a = 0; return 5;}  (a is redeclared)
 */
class Animal {
	private String species;
	private boolean canHop;
	private boolean canSwim;

	public Animal(String species, boolean canHop, boolean canSwim) {
		this.species = species;
		this.canHop = canHop;
		this.canSwim = canSwim;
	}

	public boolean canHop() {
		return canHop;
	}

	public boolean canSwim() {
		return canSwim;
	}

	public String toString() {
		return species;
	}
}

interface CheckTrait {
	public boolean test(Animal a);
}

public class ImplementingFunctionalInterfacesWithLambdas {

	private static void print(Animal animal, Predicate<Animal> trait) {
		if (trait.test(animal))
			System.out.println(animal);
	}

	public static void main(String[] args) {

		// Java relies on context when  figuring out what lambda expressions mean
		// a -> a.canHop()    ==>   (Animal a) -> {return a.canHop();}
		print(new Animal("fish", false, true), a -> a.canHop());
		print(new Animal("kangaroo", true, false), a -> a.canHop());
	}
}
