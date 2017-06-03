package concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 
 * @author chengfeili Jun 2, 2017 1:18:04 PM
 *
 */
public class ZooInfo {
	public static void main(String[] args) {
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
}