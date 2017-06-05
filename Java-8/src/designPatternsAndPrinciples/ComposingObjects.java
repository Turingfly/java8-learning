package designPatternsAndPrinciples;

/**
 * 
 * @author chengfeili Jun 4, 2017 11:12:31 PM
 *
 *         In object‐oriented design, we refer to object composition as the
 *         property of constructing a class using references to other classes in
 *         order to reuse the functionality of the other classes.
 */

class Flippers {
	public void flap() {
		System.out.println("The flippers flap back and forth");
	}
}

class WebbedFeet {
	public void kick() {
		System.out.println("The webbed feet kick to and fro");
	}
}

public class ComposingObjects {
	private final Flippers flippers;
	private final WebbedFeet webbedFeet;

	public ComposingObjects() {
		this.flippers = new Flippers();
		this.webbedFeet = new WebbedFeet();
	}

	public void flap() {
		this.flippers.flap();
	}

	public void kick() {

		this.webbedFeet.kick();
	}
}
