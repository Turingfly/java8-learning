package designPatternsAndPrinciples;

/**
 * 
 * @author chengfeili 
 * Jun 5, 2017 9:26:12 PM
 * 
 *         Creating a reusable object the first time it is requested is a
 *         software design pattern known as lazy instantiation.
 */
// Method 3: Lazy Instantiation. Delay creation of the singleton until the first
// time the getInstance() method is called:

public class SingletonPattern3 {
	private static SingletonPattern3 instance;

	private SingletonPattern3() {
	}

	// 1. NOT THREAD-SAFE
	public static SingletonPattern3 getInstance() {
		if (instance == null) {
			instance = new SingletonPattern3();
		}
		return instance;
	}

	/* 2. THREAD-SAFE
	 * 
	 * has the problem that every single call to this method will require
	 * synchronization. In practice, this can be costly and can impact
	 * performance. Synchronization is only needed the first time that the
	 * object is created.
	 */
	public static synchronized SingletonPattern3 getInstance2() {
		if (instance == null) {
			instance = new SingletonPattern3();
		}
		return instance;
	}

	/* 3. Double-Checked Locking
	 * 
	 * The solution is to use double‐checked locking, a design pattern in which
	 * we first test if synchronization is needed before actually acquiring any
	 * locks.
	 * 
	 * Volatile prevents a subtle case where the compiler tries to optimize the
	 * code such that that the object is accessed before it is finished being
	 * constructed.
	 */
	private static volatile SingletonPattern3 instance3;

	public static SingletonPattern3 getInstance3() {
		if (instance3 == null) {
			synchronized (SingletonPattern3.class) {
				if (instance3 == null) {
					instance3 = new SingletonPattern3();
				}
			}
		}
		return instance;
	}
	// Data access methods
}
