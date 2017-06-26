package concurrency;

/**
 * 
 * @author chengfeili 
 * Jun 24, 2017 4:00:42 PM
 * 
 *         Liveness is the ability of an application to be able to execute in a
 *         timely manner.
 * 
 *         there are three types of liveness issues with which you should be
 *         familiar: deadlock, starvation, and livelock.
 * 
 */
public class IdentifyingThreadingProblems {
	/**
	 * 
	 * service.submit(() -> foxy.eatAndDrink(food,water));
	 * 
	 * service.submit(() -> tails.drinkAndEat(food,water));
	 */
	public void deadlock() {

	}

	/**
	 * Starvation occurs when a single thread is perpetually denied access to a
	 * shared resource or lock. The thread is still active, but it is unable to
	 * complete its work as a result of other threads constantly taking the
	 * resource that they trying to access.
	 */
	public void starvation() {

	}

	/**
	 * Livelock occurs when two or more threads are conceptually blocked
	 * forever, although they are each still active and trying to complete their
	 * task
	 * 
	 * Livelock is often a result of two threads trying to resolve a deadlock
	 * 
	 * If Foxy and Tails continue this process forever, it is referred to as
	 * livelock
	 */
	public void livelock() {

	}

	/**
	 * A race condition is an undesirable result that occurs when two tasks,
	 * which should be completed sequentially, are completed at the same time.
	 */
	public void raceCondition() {

	}
}
