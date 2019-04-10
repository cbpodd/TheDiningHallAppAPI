package dininghall;

import java.util.Collection;

import org.json.simple.JSONObject;

class Dish {
	private String name;
	private Collection<String> allergies;

	Dish(String n, Collection<String> a) {
		this.name = n;
		this.allergies = a;
	}

	JSONObject toJSON() {
		return null;
	}
}