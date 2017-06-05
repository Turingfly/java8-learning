package designPatternsAndPrinciples;

/**
 * 
 * @author chengfeili Jun 4, 2017 10:10:08 PM
 *
 */
@FunctionalInterface
public interface DefineFunctionalInterface {
	public void sprint(String s);
}

// 1. valid, extends from DefineFunctionalInterface
interface Run1 extends DefineFunctionalInterface {
}

// 2. valid, override

interface SprintFaster extends DefineFunctionalInterface {
	public void sprint(String s);
}

// 3. valid, neither default and static is abstract

interface Skip extends DefineFunctionalInterface {
	public default int getHopCount(String s) {
		return 10;
	}

	public static void skip(int speed) {

	}
}