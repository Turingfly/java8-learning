package exceptionsAndAssertions;

/**
 * 
 * @author chengfeili
 * Jun 9, 2017 10:27:23 PM
 * 
 * 	java.lang.Object -> java.lang.throwable -> (
 * 	(java.lang.Exception -> java.lang.RuntimeException) + java.lang.Error)
 *
 *	1. RuntimeException(Unchecked)
 *
 *	2. Checked Exception: includes Exception and all subclasses that 
 *	do not extend RuntimeException.
 *	For checked exceptions, Java requires the code either handle them
 *	or declare them in the method signature.
 *
 *	3. Error 
 */
public class ExceptionIntro {
	/**
	 * Throw tells Java that you want to throw an Exception. throws simply
	 * declares that the method might throw an Exception . It also might not
	 */
	public void fall() throws Exception {
		throw new Exception();
	}
	
	/**
	 * If any of the statements throw an exception that can be caught by the
	 * exception type listed in the catch block, the try block stops running
	 * and execution goes to the catch statement.
	 */
	public void TryStatement() {
		try{
			fall1();
			System.out.println("never get here");
		} catch (RuntimeException e) {
			getUp();
		} finally {
			/*
			 * The finally block always executes, whether or not an exception
			 * occurs in the try block.
			 * 
			 * When System.exit is called in the try or catch block, finally
			 * does not run.
			 */
			getUp();
		}
	}
	
	private void getUp() {
	}

	public void fall1() {
		throw new RuntimeException();
	}
}
