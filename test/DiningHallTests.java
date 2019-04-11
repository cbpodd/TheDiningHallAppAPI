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
				System.out.println(j);
				System.out.println();
				System.out.println();
				assertTrue("Mealtimes is not empty", ((JSONArray)(js.get("mealtimes"))).size() > 0);	
			}		
		}
}