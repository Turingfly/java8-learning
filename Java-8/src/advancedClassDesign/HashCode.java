package advancedClassDesign;

/**
 * 
 * @author chengfeili 
 * Jun 3, 2017 1:30:00 PM
 * 
 *         Whenerver you overfride equals(), 
 *         you are also exprected to override hashCode()
 *         
 *         1. Whithin the same program, the result of hashCode() must not change.
 *         this means that you sholdn't include variables that change in figuring 
 *         out the hash code.
 *         2. If equals() returns true when called with two objects, calling
 *         hashCode() on each of those objects must return the same result.
 *         This means hashCode() can use a subset of the variables that equals uses.
 *		   3. If equals returns false when called with two objects, calling hashCode() on each of 
 *		   those objects does not have to return a different result. This means hashCode() results 
 *		   do not need to be unique when called on unequal objects.
 */
public class HashCode {
	private String rank;
	private String suit;

	public HashCode(String r, String s) {
		if (r == null || s == null) {
			throw new IllegalArgumentException();
		}
		rank = r;
		suit = s;
	}

	public boolean equals(Object obj) {
		if (!(obj instanceof HashCode)) {
			return false;
		}
		HashCode card = (HashCode) obj;
		return rank.equals(card.rank) && suit.equals(card.suit);
	}

	public int hashCode() {
		return rank.hashCode();
	}
}
