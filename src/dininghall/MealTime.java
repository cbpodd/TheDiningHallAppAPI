package dininghall;

import java.util.Collection;
import java.util.HashSet;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

class MealTime {
	private String name;
	private Collection<Kitchen> kitchens;

	private String [] allAllergens = {"Dairy", "Eggs", "Fish",
	"Food Not Analyzed for Allergens", "Peanuts", "Pork", "Sesame",
	"Shellfish", "Soy", "Tree Nuts", "Vegan", "Vegetarian", "Wheat / Gluten"};

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
		JSONArray dishes = new JSONArray();
		json.put("mealtime", this.name);
		for(Kitchen kt : this.kitchens) {
			for(Dish d : kt.getDishes()){
				JSONObject dish = new JSONObject();
				for(String allergens : allAllergens){
					if(d.getAllergens().contains(allergens)){
						dish.put(allergens, true);
					} else {
						dish.put(allergens, false);
					}
				}
				dish.put("name", d.getName());
				dish.put("hall", this.name);
				dishes.add(dish);
			}
		}
		json.put("dishes", dishes);
		return json;
	}
}
