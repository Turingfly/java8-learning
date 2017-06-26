package concurrency;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;

/**
 * 
 * @author chengfeili 
 * Jun 24, 2017 4:00:17 PM
 *
 */
public class ManagingConcurrentProcesses {

	/**
	 * The CyclicBarrier takes in its constructors a limit value, indicating the
	 * number of threads to wait for. As each thread nishes, it calls the
	 * await() method on the cyclic barrier. Once the speci ed number of threads
	 * have each called await(), the barrier is released and all threads can
	 * continue.
	 */
	public void creatingACyclicBarrier() {

	}

	/**
	 * When a task gets too complicated, we can split the task into multiple
	 * other tasks using the fork/join framework.
	 * 
	 * The fork/join framework relies on the concept of recursion to solve
	 * complex tasks.
	 * 
	 * Applying the fork/join framework requires us to perform three steps:
	 * Create a ForkJoinTask. Create the ForkJoinPool. Start the ForkJoinTask.
	 * 
	 */
	public void applyingTheForkJoinFramework() {

	}

	public void workingWithARecursiveTask() {

	}
}

class LionPenManager {
	private void removeAnimals() {
		System.out.println("Removing animals");
	}

	private void cleanPen() {
		System.out.println("Cleaning the pen");
	}

	private void addAnimals() {
		System.out.println("Adding animals");
	}

	public void performTask(CyclicBarrier c1, CyclicBarrier c2) {
		try {
			removeAnimals();
			c1.await();
			cleanPen();
			c2.await();
			addAnimals();
		} catch (InterruptedException | BrokenBarrierException e) {
			// Handle checked exceptions here }
		}
	}

	public void test() {
		ExecutorService service = null;
		try {
			service = Executors.newFixedThreadPool(4);
			LionPenManager manager = new LionPenManager();
			CyclicBarrier c1 = new CyclicBarrier(4);
			CyclicBarrier c2 = new CyclicBarrier(4, () -> System.out.println("*** Pen Cleaned!"));
			for (int i = 0; i < 4; i++)
				service.submit(() -> manager.performTask(c1, c2));
		} finally {
			if (service != null)
				service.shutdown();
		}
	}
}

/**
 * Removing animals Removing animals Removing animals Removing animals Cleaning
 * the pen Cleaning the pen Cleaning the pen Cleaning the pen *** Pen Cleaned!
 * Adding animals Adding animals Adding animals Adding animals
 */

class WeighAnimalAction extends RecursiveAction {
	private int start;
	private int end;
	private Double[] weights;

	public WeighAnimalAction(Double[] weights, int start, int end) {
		this.start = start;
		this.end = end;
		this.weights = weights;
	}

	protected void compute() {
		if (end - start <= 3)
			for (int i = start; i < end; i++) {
				weights[i] = (double) new Random().nextInt(100);
				System.out.println("Animal Weighed: " + i);
			}
		else {
			int middle = start + ((end - start) / 2);
			System.out.println("[start=" + start + ",middle=" + middle + ",end=" + end + "]");
			invokeAll(new WeighAnimalAction(weights, start, middle), new WeighAnimalAction(weights, middle, end));
		}
	}
}

class WeighAnimalTask extends RecursiveTask<Double> {
	private int start;
	private int end;
	private Double[] weights;

	public WeighAnimalTask(Double[] weights, int start, int end) {
		this.start = start;
		this.end = end;
		this.weights = weights;
	}

	protected Double compute() {
		if (end - start <= 3) {
			double sum = 0;
			for (int i = start; i < end; i++) {
				weights[i] = (double) new Random().nextInt(100);
				System.out.println("Animal Weighed: " + i);
				sum += weights[i];
			}
			return sum;
		} else {
			int middle = start + ((end - start) / 2);
			System.out.println("[start=" + start + ",middle=" + middle + ",end=" + end + "]");
			RecursiveTask<Double> otherTask = new WeighAnimalTask(weights, start, middle);
			otherTask.fork();
			return new WeighAnimalTask(weights, middle, end).compute() + otherTask.join();
		}
	}
}