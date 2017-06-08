package genericsAndCollections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 
 * @author chengfeili 
 * Jun 7, 2017 8:56:49 PM
 *
 */
public class UsingComparable implements Comparable<UsingComparable> {

	private String name;

	public UsingComparable(String name) {
		// TODO Auto-generated constructor stub
		this.name = name;
	}

	public String toString() {
		return name;
	}

	@Override
	public int compareTo(UsingComparable o) {
		// TODO Auto-generated method stub
		return name.compareTo(o.name); // call String's compareTo
	}

	public static void main(String[] args) {
		List<UsingComparable> list = new ArrayList<>();
		list.add(new UsingComparable("b"));
		list.add(new UsingComparable("a"));
		Collections.sort(list); // sort by name
		System.out.println(list); // [a, b]
	}
}
