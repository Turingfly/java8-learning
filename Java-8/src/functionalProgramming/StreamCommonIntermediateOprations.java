package functionalProgramming;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

/**
 * 
 * @author chengfeili 
 * Jun 8, 2017 10:36:14 PM
 *
 *         Unlike a terminal operation, intermediate operations deal with
 *         infinite streams simply by returning an infinite stream
 */
public class StreamCommonIntermediateOprations {

	public void test() {
		// filter()
		Stream<String> s = Stream.of("monkey", "gorilla", "bonobo");
		s.filter(x -> x.startsWith("m")).forEach(System.out::print); // monkey

		// distinct()
		Stream<String> sDistinct = Stream.of("duck", "duck", "duck", "goose");
		sDistinct.distinct().forEach(System.out::print); // duckgoose

		// limit() and skip()
		Stream<Integer> sSkipLimit = Stream.iterate(1, n -> n + 1);
		sSkipLimit.skip(5).limit(2).forEach(System.out::print); // 67

		// map()
		Stream<String> sMap = Stream.of("monkey", "gorilla", "bonobo");
		sMap.map(String::length).forEach(System.out::print); // 676

		// faltMap()
		List<String> zero = Arrays.asList();
		List<String> one = Arrays.asList("Bonobo");
		List<String> two = Arrays.asList("Mama Gorilla", "Baby Gorilla");
		Stream<List<String>> animals = Stream.of(zero, one, two);
		animals.flatMap(l -> l.stream()).forEach(System.out::println);

		// sorted()
		Stream<String> sSorted = Stream.of("brown-", "bear-");
		sSorted.sorted().forEach(System.out::print); // bear-brown-

		Stream<String> sSorted2 = Stream.of("brown bear-", "grizzly-");
		sSorted2.sorted(Comparator.reverseOrder()).forEach(System.out::print); // grizzly-brow-bear-

		// peek()
		Stream<String> stream = Stream.of("black bear", "brown bear", "grizzly");
		long count = stream.filter(st -> st.startsWith("g")).peek(System.out::println).count();
		System.out.println(count); // 1

		List<String> list = Arrays.asList("Toby", "Anna", "Leroy", "Alex");
		list.stream()
			.filter(n -> n.length() == 4)
			.sorted()
			.limit(2)
			.forEach(System.out::println);
		
		/**
		 *  1. stream() sends Toby to filter() . filter() sees that the length is good and sends Toby to sorted()
		 *  . sorted() can’t sort yet because it needs all of the data, so it holds Toby.
		 *  2. stream() sends Anna to filter() . filter() sees that the length is good and sends Anna to sorted() 
		 *  . sorted() can’t sort yet because it needs all of the data, so it holds Anna.
		 *  3. stream() sends Leroy to filter() . filter() sees that the length is not a match, and it takes Leroy out of the assembly line processing.
		 *  4.stream() sends Alex to filter() . filter() sees that the length is good and sends Alex to sorted() 
		 *  . sorted() can’t sort yet because it needs all of the data, so it holds Alex. It
		 *  turns out sorted() does have all of the required data, but it doesn’t know it yet.
		 *  5. The foreman lets sorted() know that it is time to sort and the sort occurs.
		 *  6. sorted() sends Alex to limit() . limit() remembers that it has seen one element and
		 *  sends Alex to forEach() , printing Alex .
		 *  7. sorted() sends Anna to limit() . limit() remembers that it has seen two elements
		 *  and sends Anna to forEach() , printing Anna .
		 *  8. limit() has now seen all of the elements that are needed and tells the foreman. The
		 *  foreman stops the line, and no more processing occurs in the pipeline.
		 */
	}

	public static void main(String[] args) {
		StreamCommonIntermediateOprations s = new StreamCommonIntermediateOprations();
		s.test();
	}
}