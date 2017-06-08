package genericsAndCollections;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 
 * @author chengfeili 
 * Jun 7, 2017 7:23:40 PM
 *
 *         An ordered collection that can contain duplicate entries. Vector does
 *         the same thing as ArrayList except more slowly. The benefit to that
 *         decrease in speed is that it is thread-safe
 *         
 *         Time Complexity: add() O(1); get() O(1); remove() O(N)
 */
public class UsingList {
	public static void test() {
		List<String> list = new ArrayList<>();
		list.add("a");
		list.add("b");
		list.add(0, "a");
		list.set(2, "c");
		System.out.println(list); // [a, a, c]
		System.out.println(list.get(0)); // [a]
		// Returns first matching index or -1 if not found
		System.out.println(list.indexOf("a")); // 0
		System.out.println(list.lastIndexOf("a")); // 1
		list.remove(0);
		// Removing Conditionally
		list.removeIf(s -> s.startsWith("A"));
		// looping through a List
		Iterator<String> iter = list.iterator();
		while (iter.hasNext()) {
			System.out.println(iter.next());
		}
	}

	public static void main(String[] args) {
		test();
	}
}
