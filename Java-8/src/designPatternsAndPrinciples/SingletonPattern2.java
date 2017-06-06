package designPatternsAndPrinciples;

/**
 * 
 * @author chengfeili 
 * Jun 5, 2017 9:21:24 PM
 *
 */

// Method 2: Instantiation using a static block
public class SingletonPattern2 {
	private static final SingletonPattern2 instance;
	static {
		instance = new SingletonPattern2();
		// Perform additional steps
	}

	private SingletonPattern2() {
	}

	public static SingletonPattern2 getInstance() {
		return instance;
	}
	// Data access methods
}
