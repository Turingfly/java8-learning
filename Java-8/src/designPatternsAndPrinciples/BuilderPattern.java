package designPatternsAndPrinciples;

import java.util.List;
import java.util.Arrays;

/**
 * 
 * @author chengfeili 
 * Jun 5, 2017 10:06:52 PM
 * 
 *         The builder pattern is a creational pattern in which parameters are
 *         passed to a builder object, often through method chaining, and an
 *         object is generated with a final build call.It is often used with
 *         immutable objects, since immutable objects do not have setter methods
 *         and must be created with all of their parameters set, although it can
 *         be used with mutable objects as well.
 */
public class BuilderPattern {
	private String species;
	private int age;
	private List<String> favoriteFoods;

	public BuilderPattern setAge(int age) {
		this.age = age;
		return this;
	}

	public BuilderPattern setSpecies(String species) {
		this.species = species;
		return this;
	}

	public BuilderPattern setFavoriteFoods(List<String> favoriteFoods) {
		this.favoriteFoods = favoriteFoods;
		return this;
	}

	public Animal build() {
		return new Animal(species, age, favoriteFoods);
	}
}

class BuilderPatternTest {
	Animal flamingo = new BuilderPattern().setFavoriteFoods(Arrays.asList("algae", "insects")).setSpecies("flamingo")
			.build();
}
