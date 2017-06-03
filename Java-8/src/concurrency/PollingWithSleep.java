package concurrency;

/**
 * 
 * @author chengfeili
 * Jun 2, 2017 11:33:11 AM
 *
 */
public class PollingWithSleep {
	private static int counter = 0;

	public static void main(String args[]) throws InterruptedException{
		new Thread(() -> {
			for (int i = 0; i < 50000; i++) {
				PollingWithSleep.counter++;
			}
		}).start();
		while (PollingWithSleep.counter < 10000) {
			System.out.println("Not reached yet");
			Thread.sleep(1000);
		}
		System.out.println("Reached!");
	}
}
