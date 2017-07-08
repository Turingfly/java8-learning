package genericsAndCollections;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author chengfeili 
 * Jun 7, 2017 6:12:41 PM
 *
 *         A bounded parameter type is a generic type that specifies a bound for
 *         the generic. A wildcard generic type is an unknown generic type
 *         represented with a question mark ( ? ).
 * 
 *         class A {} 
 *         class B extends A { } 
 *         class C extends B { }
 * 
 *         List<?> list1 = new ArrayList<A>(); 
 *         List<? extends A> list2 = new ArrayList<A>(); 
 *         List<? super A> list3 = new ArrayList<A>(); 
 *         List<? extends B> list4 = new ArrayList<A>(); It has an upper-bounded wildcard that allows ArrayList<B> or ArrayList<C> to be referenced.
 *         List<? super B> list5 = new ArrayList<A>(); 
 *         List<?> list6 = new ArrayList<? extends A>(); The problem is that you need to know what
 *         that type will be when instantiating the ArrayList.
 * 
 */
public class GenericBound {
	/*
	 * We can’t write List<Object> l = new ArrayList<String>(); because Java is
	 * trying to protect us from a runtime exception.
	 */
	public static void printList(List<Object> list) {
		for (Object x : list)
			System.out.println(x);
	}

	// 1. Unbounded Wildcards
	public static void printList1(List<?> list) {
		for (Object x : list)
			System.out.println(x);
	}

	/**
	 * 2. Upper-Bounded Wildcards
	 * The upper-bounded wildcard says that any class
	 * that extends Number or Number itself can be used as the formal parameter type:
	 * 
	 * static class Sparrow extends Bird { } 
	 * static class Bird { } 
	 * public static void main(String[] args) {
	 * List<? extends Bird> birds = new ArrayList <Bird>(); 
	 * birds.add(new Sparrow()); // DOES NOT COMPILE birds.add(new
	 * Bird()); // DOES NOT COMPILE
	 * 
	 * The problem stems from the fact that Java doesn’t know what type List<?
	 * extends Bird> really is. It could be List<Bird> or List<Sparrow> or some
	 * other generic type that hasn’t even been written yet. Line 7 doesn’t
	 * compile because we can’t add a Sparrow to List<Bird> , and line 8 doesn’t
	 * compile because we can’t add a Bird to List<Sparrow> .
	 * 
	 * private void groupOfFlyers(List<? extends Flyer> flyer) {}Note that we
	 * used the keyword extends rather than implements .
	 */
	public static long total(List<? extends Number> list) {
		// ArrayList<Number> list = new ArrayList<Integer>(); // DOES NOT
		// COMPILE
		long count = 0;
		for (Number number : list)
			count += number.longValue();
		return count;
	}

	// 3. Lower-Bounded Wildcards
	public static void addSound(List<? super String> list) {
		list.add("quack");
	}

	public static void main(String[] args) {
		List<String> keywords = new ArrayList<>();
		keywords.add("java");
		// printList(keywords); Does not compile
		// List<String> cannot be assigned to List<Object>
		printList1(keywords);
	}
}
