package exceptionsAndAssertions;

/**
 * 
 * @author chengfeili
 * Jun 11, 2017 10:42:54 AM
 *
 *	extend Exception(for checked)
 *	extend RuntimeException(for unchecked)
 *
 */

public class CreatingCustomExceptions extends Exception {
	public CreatingCustomExceptions() {
		super();
	}

	public CreatingCustomExceptions(Exception e) {
		super(e);
	}

	public CreatingCustomExceptions(String message) {
		super(message);
	}
}