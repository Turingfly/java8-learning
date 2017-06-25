package concurrency;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * 
 * @author chengfeili 
 * Jun 23, 2017 11:07:33 AM
 * 
 *         With the announcement of the Concurrency API, Java introduced the
 *         ExecutorService, which creates and manages threads for you. You first
 *         obtain an instance of an ExecutorService interface, and then you send
 *         the service tasks to be processed.
 */
public class CreatingThreadsWithTheExecutorService {
	/**
	 * begin Printing zoo inventory Printing record: 0 Printing record: 1 end
	 * Printing record: 2 Printing zoo inventory
	 */
	public void singleThreadExecutor() {
		ExecutorService service = null;
		try {
			service = Executors.newSingleThreadExecutor();
			System.out.println("begin");
			service.execute(() -> System.out.println("Printing zoo inventory"));
			service.execute(() -> {
				for (int i = 0; i < 3; i++)
					System.out.println("Printing record: " + i);
			});
			service.execute(() -> System.out.println("Printing zoo inventory"));
			System.out.println("end");
		} finally {
			if (service != null)
				service.shutdown();
		}
	}

	/**
	 * Active --> shutting down --> shut down
	 * 
	 * shutdown() does not actually stop any tasks that have already been
	 * submitted to the thread executor.
	 * 
	 * shutdownNow(): cancel all running and upcoming tasks?
	 */
	public void shuttingDownAThreadExecutor() {

	}

	/**
	 * In practice, using the submit() method is quite similar to using the
	 * execute() method, except that the submit() method return a Future object
	 * that can be used to determine whether or not the task has completed
	 * execution.
	 */
	public void submittingTasks() {

	}

	private static int counter = 0;

	public void waitingForResults() throws InterruptedException, ExecutionException {
		ExecutorService service = null;
		try {
			service = Executors.newSingleThreadExecutor();
			Future<?> result = service.submit(() -> {
				for (int i = 0; i < 500; i++)
					CreatingThreadsWithTheExecutorService.counter++;
			});
			result.get(10, TimeUnit.SECONDS);
			System.out.println("Reached!");
		} catch (TimeoutException e) {
			System.out.println("Not reached in time");
		} finally {
			if (service != null)
				service.shutdown();
		}
	}

	/**
	 * similar to Runnable except that its call() method returns a value and can
	 * throw a checked exception. As you may remember from the definition of
	 * Runnable, the run() method returns void and cannot throw any checked
	 * exceptions.
	 * 
	 * the Callable functional interface strongly resembles the Supplier
	 * functional interface, in that they both take no arguments and return a
	 * generic type. One difference is that the method in Callable can throw a
	 * checked Exception.
	 */
	public void introducingCallable() throws InterruptedException, ExecutionException {
		ExecutorService service = null;
		try {
			service = Executors.newSingleThreadExecutor();
			Future<Integer> result = service.submit(() -> 30 + 11);
			System.out.println(result.get());
		} finally {
			if (service != null)
				service.shutdown();
		}
	}

	public void waitingForAllTasksToFinish() throws InterruptedException {
		ExecutorService service = null;
		try {
			service = Executors.newSingleThreadExecutor();
			// Add tasks to the thread executor
		} finally {
			if (service != null)
				service.shutdown();
		}
		if (service != null) {
			service.awaitTermination(1, TimeUnit.MINUTES);
			// Check whether all tasks are finished
			if (service.isTerminated())
				System.out.println("All tasks finished");
			else
				System.out.println("At least one task is still running");
		}
	}

	/**
	 * we need to schedule a task to happen at some future time. We might even
	 * need to schedule the task to happen repeatedly, at some set interval.
	 * 
	 * The ScheduledExecutorService, which is a subinterface of ExecutorService,
	 * can be used for just such a task.
	 */
	public void schedulingTasks() {
		ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();

		Runnable task1 = () -> System.out.println("Hello Zoo");
		Callable<String> task2 = () -> "Monkey";
		Future<?> result1 = service.schedule(task1, 10, TimeUnit.SECONDS);
		Future<?> result2 = service.schedule(task2, 8, TimeUnit.MINUTES);
	}

	/**
	 * A thread pool is a group of pre-instantiated reusable threads that are
	 * available to perform a set of arbitrary tasks.
	 * 
	 * The difference between a single-thread and a pooled-thread executor is
	 * what happens when a task is already running. While a single-thread
	 * executor will wait for an available thread to become available before
	 * running the next task, a pooled-thread executor can execute the next task
	 * concurrently. If the pool runs out of available threads, the task will be
	 * queued by the thread executor and wait to be completed.
	 */
	public void increasingConcurrencyWithPools() {
		ScheduledExecutorService service = Executors.newScheduledThreadPool(10);
		// service.scheduleAtFixedRate(command, 3, 1, TimeUnit.MINUTE);
	}

	public static void main(String[] args) {
		CreatingThreadsWithTheExecutorService c = new CreatingThreadsWithTheExecutorService();
		c.singleThreadExecutor();
	}
}