package genericsAndCollections;

import java.util.Arrays;
import java.util.List;

/**
 * 
 * @author chengfeili 
 * Jun 7, 2017 5:06:24 PM
 * 
 *         An ArrayList cannot contain primitives.
 *         An Array can contain objects and primitives.
 */
public class ArrayAndArrayList {
	public void test() {
		String[] array = { "a", "b" }; // [a, b]
		// convert an Array to a List
		List<String> list = Arrays.asList(array); // [a, b]
		// You can change the element in either the array or the list.
		// Changes are reflected in both, since they are backed by the same data.
		array[0] = "c";
		// convert a List to an Array
		String[] array2 = (String[]) list.toArray(); // [c, b]
		
		// list.remove(1); throws UnsupportedOperationException
		// list is not resizable because it is backed by the underlying array.
	}
}
