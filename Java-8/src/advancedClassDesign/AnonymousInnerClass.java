package advancedClassDesign;

/**
 * 
 * @author chengfeili 
 * Jun 2, 2017 10:21:31 PM
 *
 *         An anonymous inner class is a local inner class that does not have a
 *         name. It is declared and instantiated all in on statement using the
 *         new keyword. Anonymous inner classes are required to extend an
 *         existing class or implement an existing interface.
 *         
 *         You can define them right where they are needed,
 *         even if that is an argument to another method.
 */
public class AnonymousInnerClass {
	// abstract class
	abstract class SaleTodayOnly {
		abstract int dollarOff();
	}

	public int admission(int basePrice) {
		SaleTodayOnly sale = new SaleTodayOnly() {

			@Override
			int dollarOff() {
				// TODO Auto-generated method stub
				return 3;
			}
		};
		return basePrice - sale.dollarOff();
	}
	
	// interface
	interface SaleTodayOnly2 {
		int dollarOff();
	}
	public int admission2(int basePrice) {
		SaleTodayOnly2 sale = new SaleTodayOnly2() {
			
			// interface requires public mehtods
			@Override
			public int dollarOff() {
				// TODO Auto-generated method stub
				return 0;
			}
		};
		return basePrice - sale.dollarOff();
	}
}
