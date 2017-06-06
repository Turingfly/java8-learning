package designPatternsAndPrinciples;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 
 * @author chengfeili 
 * Jun 5, 2017 9:47:23 PM
 * 
 *         The immutable object pattern is a creational pattern based on the
 *         idea of creating objects whose state does not change after they are
 *         created and can be easily shared across multiple classes.
 * 
 *         1. Use a constructor to set all properties of the object. 
 *         2. Mark all of the instance variables private and final. 
 *         3. Don’t define any setter methods. 
 *         4. Don’t allow referenced mutable objects to be modified or accessed directly. 
 *         5. Prevent methods from being overridden.
 *         
 */
public class ImmutableObject {
	private final String species;
	private final int age;
	private final List<String> favoriteFoods;

	public ImmutableObject(String species, int age, List<String> favoriteFoods) {
		this.species = species;
		this.age = age;
		if (favoriteFoods == null) {
			throw new RuntimeException("favoriteFoods is required");
		}
		// User can modify the items in the List.
		// this.favoriteFoods = favoriteFoods;
		this.favoriteFoods = new ArrayList<String>(favoriteFoods);
	}

	public String getSpecies() {
		return species;
	}

	public int getAge() {
		return age;
	}

	public int getFavoriteFoodsCount() {
		return favoriteFoods.size();
	}

	public String getFavoriteFood(int index) {
		return favoriteFoods.get(index);
	}
}

/*
 * How do we modify immutable objects if they are inherently unmodifiable? The
 * answer is, we can’t! Alternatively, we can create new immutable objects that
 * contain all of the same information as the original object plus whatever we
 * wanted to change.
 */
class ModifyImmutableObject {
	ImmutableObject lion = new ImmutableObject("lion", 5, Arrays.asList("meat", "more meat"));
	// Create a new Animal instance using data from the first instance
	List<String> favoriteFoods = new ArrayList<String>();
	{
		for (int i = 0; i < lion.getFavoriteFoodsCount(); i++) {
			favoriteFoods.add(lion.getFavoriteFood(i));
		}
	}
}
