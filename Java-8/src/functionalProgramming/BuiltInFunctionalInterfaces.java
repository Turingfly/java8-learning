package functionalProgramming;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

/**
 * 
 * @author chengfeili 
 * Jun 8, 2017 10:31:11 AM
 *
 *         A functional interface has exactly one abstract method. This doesn't
 *         mean that they have only one method. It can still contain default
 *         methods or static methods
 *         
 */
public class BuiltInFunctionalInterfaces {

	/**
	 * A Supplier is used when you want to generate or supply values without
	 * taking any input. A Supplier is often used when constructing new objects.
	 */
	public void supplier() {
		Supplier<LocalDate> s1 = LocalDate::now;
		Supplier<LocalDate> s2 = () -> LocalDate.now();
		LocalDate d1 = s1.get();
		LocalDate d2 = s2.get();
		System.out.println(d1);
		System.out.println(d2);
	}

	/**
	 * You use a Consumer when you want to do something with a parameter but not
	 * return any- thing. BiConsumer does the same thing except that it takes
	 * two parameters.
	 */
	public void consumerAndBiconsumer() {
		// Consumer
		Consumer<String> c1 = System.out::println;
		Consumer<String> c2 = x -> System.out.println(x);
		c1.accept("a");
		c2.accept("a");

		// BiConsumer
		Map<String, Integer> map = new HashMap<>();
		BiConsumer<String, Integer> b1 = map::put;
		BiConsumer<String, Integer> b2 = (k, v) -> map.put(k, v);
		b1.accept("chicken", 7);
		b2.accept("chick", 1);
		System.out.println(map);
	}

	/**
	 * Predicate is often used when filtering or matching
	 */
	public void predicateAndBiPredicate() {
		// Predicate
		Predicate<String> p1 = String::isEmpty;
		Predicate<String> p2 = x -> x.isEmpty();
		System.out.println(p1.test(""));
		System.out.println(p2.test(""));

		// BiPredicate
		BiPredicate<String, String> b1 = String::startsWith;
		BiPredicate<String, String> b2 = (string, prefix) -> string.startsWith(prefix);
		System.out.println(b1.test("chicken", "chick"));
		System.out.println(b2.test("chicken", "chick"));

		// default method
		Predicate<String> egg = s -> s.contains("egg");
		Predicate<String> brown = s -> s.contains("brown");
		Predicate<String> brownEggs = egg.and(brown);
		Predicate<String> otherEggs = egg.and(brown.negate());
		System.out.println(brownEggs.test("egg brown"));
		System.out.println(otherEggs.test("egg"));
	}

	/**
	 * A Function is responsible for turning one parameter into a value of a
	 * potentially different type and returning it. Similarly, a BiFunction is
	 * responsible for turning two parameters into a value and returning it.
	 */
	public void functionAndBiFunction() {
		// Function
		Function<String, Integer> f1 = String::length;
		Function<String, Integer> f2 = x -> x.length();
		System.out.println(f1.apply("cluck")); // 5
		System.out.println(f2.apply("cluck")); // 5

		// BiFunction
		BiFunction<String, String, String> b1 = String::concat;
		BiFunction<String, String, String> b2 = (string, toAdd) -> string.concat(toAdd);
		System.out.println(b1.apply("baby ", "chick")); // baby chick
		System.out.println(b2.apply("baby ", "chick")); // baby chick

		// Creating your own Functional Interface
		// interface TriFunction<T,U,V,R>{R apply(T t,U u,V v);}
	}

	/**
	 * 
	 * UnaryOperator and BinaryOperator are a special case of a function. They
	 * require all type parameters to be the same type.
	 */
	public void unaryOperatorAndBinaryOperator() {
		// UnaryOperator
		UnaryOperator<String> u1 = String::toUpperCase;
		UnaryOperator<String> u2 = x -> x.toUpperCase();
		System.out.println(u1.apply("chirp"));
		System.out.println(u2.apply("chirp"));

		// BuiltInFunctionalInterfaces
		BinaryOperator<String> b1 = String::concat;
		BinaryOperator<String> b2 = (string, toAdd) -> string.concat(toAdd);
		System.out.println(b1.apply("baby ", "chick")); // baby chick
		System.out.println(b2.apply("baby ", "chick")); // baby chick
	}

	public static void main(String[] args) {
		BuiltInFunctionalInterfaces b = new BuiltInFunctionalInterfaces();
		b.supplier();
		b.consumerAndBiconsumer();
		b.predicateAndBiPredicate();
	}
}
