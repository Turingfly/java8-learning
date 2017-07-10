package functionalProgramming;

import java.util.IntSummaryStatistics;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;


/**
 * 
 * @author chengfeili 
 * Jun 8, 2017 10:52:06 PM
 *
 */
public class StreamWorkingWithPrimitives {
	public void test() {
		DoubleStream oneValue = DoubleStream.of(3.14);
		DoubleStream varargs = DoubleStream.of(1.0, 1.1, 1.2);
		oneValue.forEach(System.out::println);
		System.out.println();
		varargs.forEach(System.out::println);

		IntStream range = IntStream.range(1, 6);
		range.forEach(System.out::println); // 1, 2, 3, 4, 5

		IntStream rangeClosed = IntStream.rangeClosed(1, 5);
		rangeClosed.forEach(System.out::println); // 1, 2, 3, 4, 5

		Stream<String> objStream = Stream.of("penguin", "fish");
		IntStream intStream = objStream.mapToInt(s -> s.length());
		intStream.forEach(System.out::print); // 74
	}

	public void range() {
		IntStream range = IntStream.range(1, 6); // 1, 2, 3, 4, 5
		IntSummaryStatistics stats = range.summaryStatistics();
		if (stats.getCount() == 0)
			throw new RuntimeException();
		int res = stats.getMax() - stats.getMin();
		System.out.println("range: " + res); // 5 - 1 = 4
	}

	public static void main(String[] args) {
		StreamWorkingWithPrimitives s = new StreamWorkingWithPrimitives();
		s.test();
		s.range();
	}
}
