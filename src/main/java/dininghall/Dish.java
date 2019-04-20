package main.java.dininghall;

import java.util.Collection;
import java.util.HashSet;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

class Dish {
	private String name;
	private Collection<String> allergies;

	Dish(String n, Collection<String> a) {
		this.name = n;
		this.allergies = a;
	}

	Dish(String n) {
		this.name = n;
		this.allergies = new HashSet<String>();
	}

	void addAllergy(String allergy) {
		this.allergies.add(allergy);
	}

	String getName(){
		return this.name;
	}

	JSONArray getAllergens() {
		JSONArray allergens = new JSONArray();
		return allergens;
	}

	JSONObject toJSON() {
		JSONObject json = new JSONObject();
		json.put("name", this.name);
		JSONArray allergens = new JSONArray();
		for (String allergen : this.allergies) {
			allergens.add(allergen);
		}
		json.put("allergies", allergens);
		return json;
	}
}