package concurrency;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * 
 * @author chengfeili Jun 23, 2017 11:07:33 AM
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

	public static void main(String[] args) {
		CreatingThreadsWithTheExecutorService c = new CreatingThreadsWithTheExecutorService();
		c.singleThreadExecutor();
	}
}
