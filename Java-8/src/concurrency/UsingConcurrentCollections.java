package concurrency;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

/**
 * 
 * @author chengfeili 
 * Jun 24, 2017 3:59:18 PM
 *
 */
public class UsingConcurrentCollections {

	/**
	 * the ConcurrentHashMap is ordering read/write access such that all access
	 * to the class is consistent.
	 * 
	 * The concurrent classes were created to help avoid common issues in which
	 * multiple threads are adding and removing objects from the same
	 * collections. At any given instance, all threads should have the same
	 * consistent view of the structure of the collection.
	 */
	public void understandingMemoryConsistencyErrors() {
		Map<String, Object> foodData = new ConcurrentHashMap<String, Object>();
		foodData.put("penguin", 1);
		foodData.put("flamingo", 2);
		for (String key : foodData.keySet())
			foodData.remove(key);
	}

	/**
	 * You should use a concurrent collection class anytime that you are going
	 * to have multiple threads modify a collections object outside a
	 * synchronized block or method, even if you don’t expect a concurrency
	 * problem.
	 * 
	 * It is considered a good practice to instantiate a concurrent collection
	 * but pass it around using a non-concurrent interface whenever possible
	 */
	public void workingWithConcurrentClasses() {

	}

	public void blockingQueues() {
		try {
			BlockingDeque<Integer> blockingDeque = new LinkedBlockingDeque<>();
			blockingDeque.offer(91);
			blockingDeque.offerFirst(5, 2, TimeUnit.MINUTES);
			blockingDeque.offerLast(47, 100, TimeUnit.MICROSECONDS);
			blockingDeque.offer(3, 4, TimeUnit.SECONDS);
			System.out.println(blockingDeque.poll());
			System.out.println(blockingDeque.poll(950, TimeUnit.MILLISECONDS));
			System.out.println(blockingDeque.pollFirst(200, TimeUnit.NANOSECONDS));
			System.out.println(blockingDeque.pollLast(1, TimeUnit.SECONDS));
		} catch (InterruptedException e) { // Handle interruption
		}

		try {
			BlockingDeque<Integer> blockingDeque = new LinkedBlockingDeque<>();
			blockingDeque.offer(91);
			blockingDeque.offerFirst(5, 2, TimeUnit.MINUTES);
			blockingDeque.offerLast(47, 100, TimeUnit.MICROSECONDS);
			blockingDeque.offer(3, 4, TimeUnit.SECONDS);
			System.out.println(blockingDeque.poll());
			System.out.println(blockingDeque.poll(950, TimeUnit.MILLISECONDS));
			System.out.println(blockingDeque.pollFirst(200, TimeUnit.NANOSECONDS));
			System.out.println(blockingDeque.pollLast(1, TimeUnit.SECONDS));
		} catch (InterruptedException e) { // Handle interruption
		}
	}

	/**
	 * The SkipList classes, ConcurrentSkipListSet and ConcurrentSkipListMap,
	 * are concurrent versions of their sorted counterparts, TreeSet and TreeMap
	 */
	public void skipListCollections() {
		List<Integer> list = new CopyOnWriteArrayList<>(Arrays.asList(4, 3, 52));
		for (Integer item : list) {
			System.out.print(item + " ");
			list.add(9);
		}
		System.out.println();
		System.out.println("Size: " + list.size());
		// output 4 3 52 Size: 6
	}

	public void obtainingSynchronizedCollections() {
		List<Integer> list = Collections.synchronizedList(new ArrayList<>(Arrays.asList(4, 3, 52)));
		synchronized (list) {
			for (int data : list)
				System.out.print(data + " ");
		}
	}
}
