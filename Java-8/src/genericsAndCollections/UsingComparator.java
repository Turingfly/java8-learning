package genericsAndCollections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 
 * @author chengfeili 
 * Jun 7, 2017 9:14:20 PM
 *
 */
public class UsingComparator implements Comparable<UsingComparator> {
	private String name;
	private int weight;

	public UsingComparator(String name, int weight) {
		this.name = name;
		this.weight = weight;
	}

	public String getName() {
		return name;
	}

	public int getWeight() {
		return weight;
	}

	public String toString() {
		return name;
	}

	public int compareTo(UsingComparator d) {
		return name.compareTo(d.name);
	}

	public static void main(String[] args) {
		Comparator<UsingComparator> byWeight = new Comparator<UsingComparator>() {
			public int compare(UsingComparator d1, UsingComparator d2) {
				return d1.getWeight() - d2.getWeight();
			}
		};

		/**
		 * Comparator is a functional interface since there is only one abstract
		 * method to implement. This means that we can rewrite the comparator
		 * in the previous example as any of the following:
		 * 
		 * Comparator<UsingComparator> comp = (a, b) -> a.getWeight() - b.getWeight();
		 * 
		 */
		List<UsingComparator> ducks = new ArrayList<>();
		ducks.add(new UsingComparator("Quack", 7));
		ducks.add(new UsingComparator("Puddles", 10));
		// Using Comparable
		Collections.sort(ducks);
		System.out.println(ducks); // [Puddles, Quack]
		// Using Comparator
		Collections.sort(ducks, byWeight);
		System.out.println(ducks); // [Quack, Puddles]
	}
}