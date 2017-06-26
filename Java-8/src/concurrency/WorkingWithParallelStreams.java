package concurrency;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 
 * @author chengfeili 
 * Jun 24, 2017 3:59:48 PM
 *
 *         A serial stream is a stream in which the results are ordered, with
 *         only one entry being processed at a time.
 * 
 *         A parallel stream is a stream that is capable of processing results
 *         concurrently, using multiple threads.
 */
public class WorkingWithParallelStreams {
	public void creaingParallelStreams() {
		// create a parallel stream is from an existing stream.
		Stream<Integer> stream = Arrays.asList(1, 2, 3, 4, 5, 6).stream();
		Stream<Integer> parallelStream = stream.parallel();
		System.out.println(parallelStream.isParallel());

		Stream<Integer> parallelStream2 = Arrays.asList(1, 2, 3, 4, 5, 6).parallelStream();
		System.out.println(parallelStream2.isParallel());
	}

	public void processingTasksInParalles() {
		// the result is not ordered
		Arrays.asList(1, 2, 3, 4, 5, 6).parallelStream().forEach(s -> System.out.print(s + " "));
		// the result is ordered
		Arrays.asList(1, 2, 3, 4, 5, 6).parallelStream().forEachOrdered(s -> System.out.print(s + " "));
	}

	/**
	 * By independent operations, we mean that the results of an operation on
	 * one element of a stream do not require or impact the results of another
	 * element of the stream.
	 */
	public void independentOperations() {
		Arrays.asList("jackal", "kangaroo", "lemur").parallelStream().map(s -> s.toUpperCase())
				.forEach(System.out::println);
	}

	/**
	 * Side effects can also appear in parallel streams if your lambda
	 * expressions are stateful. A stateful lambda expression is one whose
	 * result depends on any state that might change during the execution of a
	 * pipeline. On the other hand, a stateless lambda expression is one whose
	 * result does not depend on any state that might change during the
	 * execution of a pipeline.
	 */
	public void avoidingStatefulOperations() {
		List<Integer> data = Collections.synchronizedList(new ArrayList<>());
		Arrays.asList(1, 2, 3, 4, 5, 6).parallelStream().map(i -> {
			data.add(i);
			return i;
		}).forEachOrdered(i -> System.out.print(i + " "));
		// AVOID STATEFUL LAMBDA EXPRESSIONS!
		System.out.println();
		for (Integer e : data) {
			System.out.print(e + " ");
		}
		// out put might be 123456 243561
	}

	/**
	 * Reduction operations on parallel streams are referred to as parallel
	 * reductions
	 */
	public void processingParallelReductions() {

	}

	/**
	 * The first parameter to the reduce() method is called the identity, the
	 * second parameter is called the accumulator, and the third parameter is
	 * called the combiner.
	 * 
	 * Requirements for reduce() Arguments
	 * 
	 * The identity must be defined such that for all elements in the stream u,
	 * combiner.apply(identity, u) is equal to u.
	 * 
	 * The accumulator operator op must be associative and stateless such that
	 * (a op b) op c isequaltoa op (b op c).
	 * 
	 * The combiner operator must also be associative and stateless and
	 * compatible with the identity, such that for all u and t
	 * combiner.apply(u,accumulator.apply(identity,t)) is equal to
	 * accumulator.apply(u,t).
	 * 
	 * If you follow these principles when building your reduce() arguments,
	 * then the operations can be performed using a parallel stream and the
	 * results will be ordered as they would be with a serial stream.
	 */
	public void combiningResultsWithReduce() {
		// The variable c is interpreted as a char, whereas s1, s2, and s3 are
		// String values.
		System.out
				.println(Arrays.asList('w', 'o', 'l', 'f').stream().reduce("", (c, s1) -> c + s1, (s2, s3) -> s2 + s3));
	}

	/**
	 * Requirements for Parallel Reduction with collect()
	 * 
	 * The stream is parallel.
	 * 
	 * The parameter of the collect operation has the
	 * Collector.Characteristics.CONCURRENT characteristic.
	 * 
	 * Either the stream is unordered, or the collector has the characteristic
	 * Collector.Characteristics.UNORDERED.
	 */
	public void combiningResultsWithCollect() {
		Stream<String> stream = Stream.of("w", "o", "l", "f").parallel();
		SortedSet<String> set = stream.collect(ConcurrentSkipListSet::new, Set::add, Set::addAll);
		System.out.println(set); // [f, l, o, w]

		Stream<String> ohMy = Stream.of("lions", "tigers", "bears").parallel();
		ConcurrentMap<Integer, String> map = ohMy
				.collect(Collectors.toConcurrentMap(String::length, k -> k, (s1, s2) -> s1 + "," + s2));
		System.out.println(map); // {5=lions,bears, 6=tigers}
									// System.out.println(map.getClass()); //
									// java.util.concurrent.ConcurrentHashMap

		Stream<String> ohMy1 = Stream.of("lions", "tigers", "bears").parallel();
		ConcurrentMap<Integer, List<String>> map1 = ohMy1.collect(Collectors.groupingByConcurrent(String::length));
		System.out.println(map1); // {5=[lions, bears], 6=[tigers]}
	}
}
