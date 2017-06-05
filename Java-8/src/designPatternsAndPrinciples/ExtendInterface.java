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
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean canHuntWhileRunning() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public double getMaxSpeed() {
		// TODO Auto-generated method stub
		return 100;
	}
}
