package dininghall;

import java.util.Collection;

import org.json.simple.JSONObject;

class Kitchen {
	private String name;
	private Collection<Dish> dishes;

	Kitchen(String n, Collection<Dish> d) {
		this.name = n;
		this.dishes = d;
	}

	JSONObject toJSON() {
		return null;
	}
}