package genericsAndCollections;

import java.util.HashSet;
import java.util.NavigableSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * 
 * @author chengfeili 
 * Jun 7, 2017 7:37:24 PM
 *
 *         Comparing Set Implementations A HashSet stores its elements in a hash
 *         table. This means that it uses the hashCode() method of the objects
 *         to retrieve them more efficiently.
 *         The main benefit is that adding elements and checking if an element is in the
 *         set both have constant time. The tradeoff is that you lose the order
 *         in which you inserted the elements.
 *         A TreeSet stores its elements in a sorted tree structure. The
 *         main benefit is that the set is always in sorted order. The tradeoff
 *         is that adding and checking if an element is present are both O(log
 *         n).
 * 
 *         Time Complexity: add() O(1); contains() O(1)
 */
public class UsingSet {
	public static void testSet() {
		Set<String> set = new HashSet<>();
		set.add(new String("a"));
		set.add(new String("a"));
		System.out.println(set); // [a]
		
		Set<StringBuilder> set1 = new HashSet<>();
		set1.add(new StringBuilder("a"));
		set1.add(new StringBuilder("a"));
		System.out.println(set1); // [a, a]
	}
	
	/**
	 * E lower(E e) Returns greatest element that is < e, or null if no such element
	 * E floor(E e) Returns greatest element that is <= e, or null if no such element
	 * E ceiling(E e) Returns smallest element that is >= e, or null if no such element
	 * E higher(E e) Returns smallest element that is > e, or null if no such element
	 */
	public static void testTreeSet() {
		NavigableSet<Integer> set = new TreeSet<>();
		for (int i = 1; i <= 20; i++) {
			set.add(i);
		}
		System.out.println(set.lower(10)); // 9
		System.out.println(set.floor(10)); // 10
		System.out.println(set.ceiling(20)); // 20
		System.out.println(set.higher(20)); // null
	}
	public static void main(String[] args) {
		testSet();
		testTreeSet();
	}
}
