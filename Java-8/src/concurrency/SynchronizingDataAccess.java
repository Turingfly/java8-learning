package concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 
 * @author chengfeili 
 * Jun 24, 2017 8:17:32 PM
 *
 *         thread safety is the property of an object that guarantees safe
 *         execution by multiple threads at the same time. Now that we have
 *         multiple threads capable of accessing the same objects in memory, we
 *         have to make sure to organize our access to this data such that we
 *         don’t end up with invalid or unexpected results. Since threads run in
 *         a shared environment and memory space, how do we prevent two threads
 *         from interfering with each other?
 */
public class SynchronizingDataAccess {

	/**
	 * Atomic is the property of an operation to be carried out as a single unit
	 * of execution without any interference by another thread.
	 *
	 * Unlike our previous sample output, the numbers 1 through 10 will always
	 * be output.
	 * 
	 * 2 3 1 4 5 6 7 8 9 10
	 * 
	 * 1 4 3 2 5 6 7 8 9 10
	 * 
	 * 1 4 3 5 6 2 7 8 10 9
	 */

	private AtomicInteger sheepCount = new AtomicInteger(0);

	private void incrementAndReport() {
		System.out.print(sheepCount.incrementAndGet() + " ");
	}

	/**
	 * Synchronizing Methods
	 * 
	 * We can add the synchronized modifier to any instance method to
	 * synchronize automatically on the object itself
	 * the following two method definitions are equivalent:
	 */

	/**
	private void incrementAndReport1() {
		synchronized (this) {
			System.out.print((++sheepCount) + " ");
		}
	}

	private synchronized void incrementAndReport2() {
		System.out.print((++sheepCount) + " ");
	}
	*/
	
	/**
	public static void printDaysWork() {
		synchronized (SheepManager.class) {
			System.out.print("Finished work");
		}
	}

	public static synchronized void printDaysWork() {
		System.out.print("Finished work");
	}
	*/
	
	/**
	 * While multi-threaded programming is about doing multiple things at the
	 * same time, synchronization is about taking multiple threads and making
	 * them perform in a more single-threaded manner.
	 * 
	 * Synchronization is about protecting data integrity at the cost of performance. 
	 */
	public void costOfSynchronization() {
		
	}
}

/**
 * 
 * A problem occurs when two threads both execute the right side of the
 * expression, reading the “old” value before either thread writes the “new”
 * value of the variable
 * 
 * the unexpected result of two tasks executing at the same time is referred to
 * as a race condition.
 * 
 * 1 2 2 3 4 5 6 7 8 9
 * 
 * 2 4 5 6 7 8 1 9 10 3
 * 
 * 2 1 3 4 5 6 7 8 9 10
 *
 * 
 */
class SheepManager {
	private int sheepCount = 0;

	void incrementAndReport() {
		System.out.print((++sheepCount) + " ");
	}

	public static void main(String[] args) {
		ExecutorService service = null;
		try {
			service = Executors.newFixedThreadPool(20);
			SheepManager manager = new SheepManager();
			for (int i = 0; i < 10; i++)
				service.submit(() -> manager.incrementAndReport());
		} finally {
			if (service != null)
				service.shutdown();
		}
	}
}

/**
 * Improving Access with Synchronized Blocks
 * 
 * The most common technique is to use a monitor, also called a lock, to
 * synchronize access. A monitor is a structure that supports mutual exclusion
 * or the property that at most one thread is executing a particular segment of
 * code at a given time.
 * 
 * 
 * This example is referred to as a synchronized block. Each thread that arrives
 * will first check if any threads are in the block.
 * 
 * 1 2 3 4 5 6 7 8 9 10
 *
 */
class SheepManager2 {
	private int sheepCount = 0;

	private void incrementAndReport() {
		synchronized (this) {
			System.out.print((++sheepCount) + " ");
		}
	}

	public static void main(String[] args) {
		ExecutorService service = null;
		try {
			service = Executors.newFixedThreadPool(20);
			SheepManager2 manager = new SheepManager2();
			for (int i = 0; i < 10; i++)
				service.submit(() -> manager.incrementAndReport());
		} finally {
			if (service != null)
				service.shutdown();
		}
	}
}