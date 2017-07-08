package genericsAndCollections;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * 
 * @author chengfeili
 * Jun 7, 2017 8:36:41 PM
 * 
 * void clear()      				Removes all keys and values from the map.
 * boolean isEmpty() 				Returns whether the map is empty.
 * int size() 		 				Returns the number of entries (key/value pairs) in the map.
 * V get(Object key) 				Returns the value mapped by key or null if none is mapped.
 * V put(K key, V value) 			Adds or replaces key/value pair. Returns previous value or null .
 * V remove(Object key) 			Removes and returns value mapped to key. Returns null if none. 
 * boolean containsKey(Object key) 	Returns whether key is in map.
 * boolean containsValue(Object) 	Returns whether value is in map.
 * Set<K> keySet() 					Returns set of all keys.
 * Collection<V> values() 			Returns Collection of all values.
 */
public class UsingMap {
	public static void testHashMap() {
		Map<String, String> map = new HashMap<>();
		map.put("koala", "2");
		map.put("lion", "1");
		map.put("giraffe", "3");
		for (String key: map.keySet())
		System.out.print(key + ",");  // koala,giraffe,lion,
		System.out.print(map.containsKey("lion"));
		System.out.print(map.containsValue("3"));
	}
	/**
	 * TreeMap —no null keys
	 * Hashtable —no null keys or values142
	 * TreeSet —no null elements
	 * ArrayDeque —no null elements
	 */
	public static void testTreeMap() {
		Map<String, String> map = new TreeMap<>();
		map.put("koala", "2");
		map.put("lion", "1");
		map.put("giraffe", "3");
		map.putIfAbsent("a", "4");
		for (String key: map.keySet())
		System.out.print(key + ","); // giraffe,koala,lion,
	}
	public static void main(String[] args) {
		testHashMap();
		System.out.println();
		testTreeMap();
	}
}
