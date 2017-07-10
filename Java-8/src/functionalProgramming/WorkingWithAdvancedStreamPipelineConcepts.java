package functionalProgramming;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 
 * @author chengfeili 
 * Jun 8, 2017 11:04:59 PM
 *
 */
public class WorkingWithAdvancedStreamPipelineConcepts {
	public void linkingStreamsToTheUnderlyingData() {
		List<String> cats = new ArrayList<>();
		cats.add("Annie");
		cats.add("Ripley");
		Stream<String> stream = cats.stream();
		cats.add("KC");
		System.out.println(stream.count()); // 3
	}

	public void collectingResults() {
		// basic collectors
		Stream<String> ohMy = Stream.of("lions", "tigers", "bears");
		String result = ohMy.collect(Collectors.joining(", "));
		System.out.println(result); // lions, tigers, bears

		// collecting into Maps
		Stream<String> ohMy1 = Stream.of("lions", "tigers", "bears");
		Map<String, Integer> map = ohMy1.collect(Collectors.toMap(s -> s, String::length));
		System.out.println(map); // {lions=5, bears=5, tigers=6}

		Stream<String> ohMy2 = Stream.of("lions", "tigers", "bears");
		Map<Integer, String> map2 = ohMy2.collect(Collectors.toMap(String::length, k -> k, (s1, s2) -> s1 + "," + s2));
		System.out.println(map2); // {5=lions,bears, 6=tigers}
		System.out.println(map2.getClass()); // class. java.util.HashMap

		// collecting using grouping
		Stream<String> ohMy3 = Stream.of("lions", "tigers", "bears");
		Map<Integer, List<String>> map3 = ohMy3.collect(Collectors.groupingBy(String::length));
		System.out.println(map3); // {5=[lions, bears], 6=[tigers]}

		Stream<String> ohMy4 = Stream.of("lions", "tigers", "bears");
		Map<Integer, Set<String>> map4 = ohMy4.collect(Collectors.groupingBy(String::length, Collectors.toSet()));
		System.out.println(map4); // {5=[lions, bears], 6=[tigers]}

		// collecting using partitioning
		Stream<String> ohMy5 = Stream.of("lions", "tigers", "bears");
		Map<Boolean, List<String>> map5 = ohMy5.collect(Collectors.partitioningBy(s -> s.length() <= 5));
		System.out.println(map5); // {false=[tigers], true=[lions, bears]}
	}

	public static void main(String[] args) {
		WorkingWithAdvancedStreamPipelineConcepts w = new WorkingWithAdvancedStreamPipelineConcepts();
		w.linkingStreamsToTheUnderlyingData();
		w.collectingResults();
	}
}
