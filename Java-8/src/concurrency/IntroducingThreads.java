package concurrency;

/**
 * 
 * @author chengfeili 
 * Jun 23, 2017 8:56:14 AM
 *
 *         A thread is the smallest unit of execution that can be scheduled by
 *         the operating system. A process is a group of associated threads that
 *         execute in the same, shared environment.
 * 
 *         By shared environment, we mean that the threads in the same process
 *         share the same memory space and can communicate directly with one
 *         another.
 * 
 *         A task is a single unit of work performed by a thread. A thread can
 *         complete multiple independent tasks but only one task at a time.
 * 
 *         The difference between extending the Thread class and implementing
 *         Runnable.
 * 
 *         1. If you need to define your own Thread rules upon which multiple
 *         tasks will rely, such as a priority Thread, extending Thread may be
 *         preferable.
 * 
 *         2. Since Java doesn't support multiple inheritance, extending Thread
 *         class does not allow you to extend any other class, whereas
 *         implementing Runnable lets you extend another class.
 * 
 *         3. Implementing Runnable is often a better object-oriented design
 *         practice since it separates the task being performed from the Thread
 *         object performing it.
 * 
 *         4. Implementing Runnable allows the class to be used by numerous
 *         Concurrency API classes.
 * 
 *         5. Use the ExecutorService to perform thread tasks without having to
 *         create Thread objects directly.
 * 
 */
public class IntroducingThreads {

	/**
	 * system threads and user-defined threads.
	 * 
	 * A system thread is created by the JVM and runs in the background of the
	 * application.
	 * 
	 * When a system-defined thread encounters a problem and cannot recover,
	 * such as running out of memory, it generates a Java Error, as opposed to
	 * an Exception.
	 */
	public void distinguishThread() {

	}

	/**
	 * The property of executing multiple threads and processes at the same time
	 * is referred to as concurrency.
	 * 
	 * Operating systems use a thread scheduler to determine which threads
	 * should be currently executing.
	 * 
	 * When a thread’s allotted time is complete but the thread has not finished
	 * processing, a context switch occurs. A context switch is the process of
	 * storing a thread’s current state and later restoring the state of the
	 * thread to continue execution.
	 * 
	 * A thread priority is a numeric value associated with a thread that is
	 * taken into consideration by the thread scheduler when determining which
	 * threads should currently be executing.
	 * 
	 * Thread.MIN_PRIORITY 1 Thread.NORM_PRIORITY 5 Thread.MAX_PRIORITY 10
	 * 
	 * These scheduling algorithms allowed users to experience the illusion that
	 * multiple tasks were being performed at the exact same time within a
	 * single-CPU system
	 * 
	 */
	public void understandingThreadConcurrency() {

	}

	/**
	 * java.lang.Runnable, or Runnable for short, is a functional interface that
	 * takes no arguments and returns no data.
	 * 
	 * The Runnable interface is commonly used to define the work a thread will
	 * execute, separate from the main application thread.
	 * 
	 * 
	 */
	public void introducingRunnable() {
		/**
		class CalculateAverages implements Runnable {
			private double[] scores;

			public CalculateAverages(double[] scores) {
				this.scores = scores;
			}

			public void run() { // Define work here that uses the scores object
			}
		}
		*/
	}

	/**
	 * Java does not provide any guarantees about the order in which a thread
	 * will be processed once it is started. It may be executed immediately or
	 * delayed for a significant amount of time.
	 * 
	 * Defining the task, or work, that a Thread instance will execute can be
	 * done two ways in Java: Provide a Runnable object or lambda expression to
	 * the Thread constructor. Create a class that extends Thread and overrides
	 * the run() method.
	 */
	public void createAThread() {

	}

	/**
	 * Polling is the process of intermittently checking data at some fixed
	 * interval.
	 */
	private static int counter = 0;

	/**
	 * When used inside the body of the main() method, the thread associated
	 * with the main() method will pause, while the separate thread will
	 * continue to run.
	 * 
	 */
	public void pollingWithSleep() throws InterruptedException {
		new Thread(() -> {
			for (int i = 0; i < 500; i++)
				IntroducingThreads.counter++;
		}).start();
		while (IntroducingThreads.counter < 100) {
			System.out.println("Not reached yet");
			Thread.sleep(1000); // 1 SECOND
		}
		System.out.println("Reached!");
	}

	public static void main(String[] args) {
		/**
		 * Any time you create a Thread instance, make sure that you remember to
		 * start the task with the Thread.start() method. This starts the task
		 * in a separate operating system thread
		 * 
		 * The output is unknown until runtime.
		 * 
		 * While the order of thread execution once the threads have been
		 * started is indeterminate, the order within a single thread is still
		 * linear.
		 */
		System.out.println("begin");
		(new ReadInventoryThread()).start();
		(new Thread(new PrintData())).start();
		(new ReadInventoryThread()).start();
		System.out.println("end");

		/**
		 * While the following code snippet will compile, none will actually
		 * execute a task on a separate processing thread. Instead, the thread
		 * that made the call will be used to execute the task, causing the
		 * thread to wait until each run() method is complete before moving on
		 * to the next line.
		 * 
		 * In general, you should extend the Thread class only under very
		 * specific circumstances, such as when you are creating your own
		 * priority-based thread. In most situations, you should implement the
		 * Runnable interface rather than extend the Thread class.
		 * 
		 */
		new PrintData().run();
		(new Thread(new PrintData())).run();
		(new ReadInventoryThread()).run();
	}
}

class PrintData implements Runnable {
	public void run() {
		for (int i = 0; i < 3; i++)
			System.out.println("Printing record: " + i);
	}
}

class ReadInventoryThread extends Thread {
	public void run() {
		System.out.println("Printing zoo inventory");
	}
}