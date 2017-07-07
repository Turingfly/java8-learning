package designPatternsAndPrinciples;

/**
 * 
 * @author chengfeili 
 * Jun 4, 2017 9:59:54 PM
 *
 */
interface Walk {
	boolean isQuadruped();

	abstract double getMaxSpeed();
}

interface Run extends Walk {
	public abstract boolean canHuntWhileRunning();

	abstract double getMaxSpeed();
}

public class ExtendInterface implements Run {
	@Override
	public boolean isQuadruped() {
		return true;
	}

	@Override
	public boolean canHuntWhileRunning() {
		return true;
	}

	@Override
	public double getMaxSpeed() {
		return 100;
	}
}
