package dininghall;

import java.time.LocalDate;
import java.util.Collection;

public class DiningHall {
	private Collection<MealTime> mtimes;
	private LocalDate date;
	private String name;

	public DiningHall(String n, LocalDate d) {
		this.name = n;
		this.date = d;
		this.mtimes = getMealTimes(this.name, this.date);
	}

	public JSONObject toJSON() {
		
	}

	private Collection<MealTime> getMealTimes(String name, LocalDate date) {
		return null;
	}
}