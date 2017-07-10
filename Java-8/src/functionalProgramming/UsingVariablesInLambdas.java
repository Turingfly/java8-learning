package functionalProgramming;

/**
 * 
 * @author chengfeili 
 * Jun 8, 2017 8:19:19 AM
 *
 *         If you could add the final modifier to a local variable, it was
 *         “effectively final.” Lambdas use the same access rules as inner
 *         classes.
 *         Lambda expressions can access static variables, instance
 *         variables, effectively final method parameters, and effectively final
 *         local variables.
 */
interface Gorilla {
	String move();
}

public class UsingVariablesInLambdas {
	String walk = "walk";

	void everyonePlay(boolean baby) {
		String approach = "amble";
		// approach = "run";
		// instance variable
		play(() -> walk);
		// method parameter. It is effectively final since there are no
		// reassignments to that variable.
		play(() -> baby ? "hitch a ride" : "run");
		/*
		 * effectively final local variable If we uncomment line 6, there will
		 * be a reassignment and the variable will no longer be effectively
		 * final. This would cause a compiler error.
		 */
		play(() -> approach);
	}

	void play(Gorilla g) {
		System.out.println(g.move());
	}

	public static void main(String[] args) {
		UsingVariablesInLambdas u = new UsingVariablesInLambdas();
		u.everyonePlay(true);
	}
}
