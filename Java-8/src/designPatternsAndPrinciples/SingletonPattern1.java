package designPatternsAndPrinciples;

/**
 * 
 * @author chengfeili 
 * Jun 5, 2017 9:14:26 PM
 * 
 *         The single pattern is a creational pattern focused on creating only
 *         one instance of an object in memory within an application, sharable
 *         by all classes and threads with the application. The globally
 *         available object created by the singleton pattern is referred to as a
 *         singleton.
 * 
 *         Singletons are used in situations where we need access to a single
 *         set of data throughout an application. For example, application
 *         configuration data and reusable data caches are commonly implemented
 *         using singletons. Singletons may also be used to coordinate access to
 *         shared resources, such as coordinating write access to a file
 */

// Method 1: Instantiate the singleton object directly in the definition of the instance reference.
public class SingletonPattern1 {
	private int quantity = 0;

	private SingletonPattern1() {
	}

	private static final SingletonPattern1 instance = new SingletonPattern1();

	public static SingletonPattern1 getInstance() {
		return instance;
	}

	public synchronized void addHay(int amount) {
		quantity += amount;
	}

	public synchronized boolean removeHay(int amount) {
		if (quantity < amount)
			return false;
		quantity -= amount;
		return true;
	}

	public synchronized int getHayQuantity() {
		return quantity;
	}
}

class LlamaTrainer {
	public boolean feedLlamas(int numberOfLlamas) {
		int amountNeeded = 5 * numberOfLlamas;
		SingletonPattern1 hayStorage = SingletonPattern1.getInstance();
		if (hayStorage.getHayQuantity() < amountNeeded) {
			hayStorage.addHay(amountNeeded + 10);
		}
		boolean fed = hayStorage.removeHay(amountNeeded);
		if (fed)
			System.out.println("Llamas have been fed");
		return fed;
	}
}
