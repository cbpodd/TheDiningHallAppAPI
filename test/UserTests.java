import org.json.simple.JSONArray;
import org.junit.jupiter.api.Test;

import data.MySQL;
import user.User;

public class UserTests {
	
	@Test
	public void addUser() {
		User.addUser("testUser", "tester");
	}

	@Test
	public void getUser() {
		User u = new User("testUser", "tester");
	}

	@Test
	public void addAllergen() {
		User u = new User("testUser", "tester");
		u.addAllergen("Vegan");
	}

	@Test
	public void getAllergens() {
		User u = new User("testUser", "tester");
		JSONArray all = u.getAllergens();
		for (int i = 0; i < all.size(); i++) {
			System.out.println(all.get(i));
		}
	}

	@Test
	public void addFavorite() {
		User u = new User("testUser", "tester");
		u.addDish("Waffle Bar");
	}

	@Test
	public void getFavorites() {
		User u = new User("testUser", "tester");
		JSONArray all = u.getDishes();
		for (int i = 0; i < all.size(); i++) {
			System.out.println(all.get(i));
		}
	}
	
	@Test
	public void deleteUser() {
		MySQL mysql = new MySQL();
		int userID = mysql.getUser("testUser", "tester");
		mysql.closeConnection();
		User.deleteUser(userID);
	}
}