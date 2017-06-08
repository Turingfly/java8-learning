package genericsAndCollections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 
 * @author chengfeili
 * Jun 7, 2017 5:19:13 PM
 *
 *
 */
public class SearchingAndSorting {
	static class A {
		int id;
	}
	public void test() {
		int[] nums = {6, 9, 1, 8};
		Arrays.sort(nums); // [1, 6, 8, 9]
		System.out.println(Arrays.binarySearch(nums, 6)); // 1
		System.out.println(Arrays.binarySearch(nums, 3)); // -2  ( -1 -1 )
	}
	public void test2() {
		List<A> list = new ArrayList<>();
		list.add(new A());
		// Collections.sort(list); Does not compile
		Comparator<A> c = (a, b) -> a.id - b.id;
		Collections.sort(list, c);
		
	}
}
