package io;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.util.Arrays;

/**
 * 
 * @author chengfeili 
 * Jun 14, 2017 3:18:58 PM
 *
 *         The Console class is a singleton.
 * 
 *         This is a bug #122429 of eclipse
 * 
 */
public class IteractingWithUsers {

	public void readLineTest() throws IOException {
		Console console = System.console();
		if (console == null) {
			throw new RuntimeException("Console not available");
		} else {
			console.writer().print("How excited are you about your trip today? ");
		    /**
		     * Flushes the console and forces any buffered output to be written
		     * immediately .
		     */
			console.flush();
			String excitementAnswer = console.readLine();
			String name = console.readLine("Please enter your name: ");
			Integer age = null;
			console.writer().print("What is your age? ");
			console.flush();
			BufferedReader reader = new BufferedReader(console.reader());
			String value = reader.readLine();
			age = Integer.valueOf(value);
			console.writer().println();
			console.format("Your name is " + name);
			console.writer().println();
			console.format("Your age is " + age);
			console.printf("Your excitement level is: " + excitementAnswer);
		}
	}

	/**
	 * By disabling echoing, the user does not see the text they are typing.
	 * 
	 * The readPassword() method returns an array of characters instead of a
	 * String. String values are added to a shared memory pool for performance
	 * reasons in Java. This means that if a password that a user typed in were
	 * to be returned to the process as a String , it might be available in the
	 * String pool long after the user entered it.
	 */
	public void readPasswordTest() {
		Console console = System.console();
		if (console == null) {
			throw new RuntimeException("Console not available");
		} else {
			char[] password = console.readPassword("Enter your password: ");
			console.format("Enter your password again: ");
			console.flush();
			char[] verify = console.readPassword();
			boolean match = Arrays.equals(password, verify);
			// Immediately clear passwords from memory
			// Arrays.fill(password, 'x');
			for (int i = 0; i < password.length; i++) {
				password[i] = 'x';
			}
			for (int i = 0; i < verify.length; i++) {
				verify[i] = 'x';
			}
			console.format("Your password was " + (match ? "correct" : "incorrect"));
		}
	}

	public static void main(String[] args) {

	}
}
