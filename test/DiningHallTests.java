import static org.junit.Assert.assertTrue;
import java.time.LocalDate;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.Test;

import dininghall.DiningHall;

public class DiningHallTests {
		
		@Test
		public void hasMealTimes() {
			JSONArray json = new JSONArray();
			LocalDate d = LocalDate.now();
			json.add(new DiningHall("Everybody's Kitchen", d).toJSON());
			json.add(new DiningHall("USC Village Dining Hall", d).toJSON());
			json.add(new DiningHall("Parkside Restaurant & Grill", d).toJSON());
			for (Object j : json) {
				JSONObject js = (JSONObject)j;
				System.out.println(js);
				JSONArray mt = (JSONArray)js.get("mealtimes");
				for (Object m : mt) {
					JSONObject mtj = (JSONObject)m;
					System.out.println(js.get("hall"));
					System.out.println(mtj.get("mealtime"));
				}
				assertTrue("Mealtimes is not empty", ((JSONArray)(js.get("mealtimes"))).size() > 0);	
			}		
		}
}