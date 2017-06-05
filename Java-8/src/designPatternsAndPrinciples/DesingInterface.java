package designPatternsAndPrinciples;

/**
 * 
 * @author chengfeili 
 * Jun 4, 2017 9:50:52 PM
 *
 */
interface Fly {
	public int getWingSpan() throws Exception;

	public static final int MAX_SPEED = 100;

	// default method is optionally overridden in the subclass
	public default void land() {
		System.out.println("Animal is landing");
	}
	
	// it is available without an instance of the interface
	public static double calculateSpeed(float distance, double time) {
		return distance / time;
	}
}

public class DesingInterface implements Fly {

	@Override
	public int getWingSpan() throws Exception {
		// TODO Auto-generated method stub
		return 15;
	}

	public void land() {
		System.out.println("Eagle is diving fast");
	}
}
