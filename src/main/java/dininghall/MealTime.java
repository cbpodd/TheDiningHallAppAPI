package main.java.dininghall;

import java.util.Collection;
import java.util.HashSet;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

class MealTime {
	private String name;
	private Collection<Kitchen> kitchens;

	MealTime(String n, Collection<Kitchen> kits) {
		this.name = n;
		this.kitchens = kits;
	}

	MealTime(String n) {
		this.name = n;
		this.kitchens = new HashSet<Kitchen>();
	}

	void addKitchen(Kitchen k) {
		this.kitchens.add(k);
	}

	Collection<Kitchen> getKitchens(){
		return this.kitchens;
	}

	String getName() {
		return this.name;
	}

	JSONObject toJSON() {
		JSONObject json = new JSONObject();
		json.put("name", this.name);
		JSONArray ks = new JSONArray();
		for (Kitchen k : this.kitchens) {
			ks.add(k.toJSON());
		}
		json.put("kitchens", ks);
		return json;
	}
}
