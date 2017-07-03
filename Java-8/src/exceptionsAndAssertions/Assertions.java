package exceptionsAndAssertions;

/**
 * 
 * @author chengfeili 
 * Jun 11, 2017 11:25:30 AM
 * 
 *         An assertion is a Boolean expression that you place at a point in
 *         your code where you expect something to be true.
 * 
 *         assert boolean_expression; assert boolean_expression: error_message;
 * 
 *         If assertions are disabled, Java skips the assertion and goes on in
 *         the code. If assertions are enabled and the boolean expression is
 *         true, then our assertion has been validated and nothing happens. The
 *         program continues to execute in its normal manner. If assertions are
 *         enabled and the boolean expression is false, then our assertion is
 *         invalid and a java.lang.AssertionError is thrown.
 * 
 *         1. Internal Invariants You assert that a value is within a certain
 *         constraint. assert x < 0 is an example of an internal invariant. 2.
 *         Class Invariants You assert the validity of an object’s state. Class
 *         invariants are typically private methods within the class that return
 *         a boolean. The upcoming Rectangle class demonstrates a class
 *         invariant. 3. Control Flow Invariants You assert that a line of code
 *         you assume is unreachable is never reached. The upcoming TestSeasons
 *         class demonstrates a control ow invariant. 4. Preconditions You
 *         assert that certain conditions are met before a method is invoked. 5.
 *         Post Conditions You assert that certain conditions are met after a
 *         method executes successfully.
 */
public class Assertions {
	public static void main(String[] args) {
		int numGuests = -5;
		// if booleanExpression evaluates to False, an exception of type
		// java.lang.AssertonError is thrown.
		assert numGuests > 0;
		if (!(numGuests > 0)) {
			throw new AssertionError();
		}
		assert numGuests > 0 : "Message";
		if (!(numGuests > 0)) {
			throw new AssertionError("Message");
		}
		System.out.println(numGuests);
	}
}
