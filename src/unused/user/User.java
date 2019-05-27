package user;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.simple.JSONArray;

import data.MySQL;

public class User {
	private int userID;

	public User(String uname, String pword) {
		MySQL mysql = new MySQL();
		this.userID = mysql.getUser(uname, pword);
		mysql.closeConnection();
	}

	public static void addUser(String uname, String pword) {
		MySQL mysql = new MySQL();

		mysql.addUser(uname, pword);

		mysql.closeConnection();
	}

	public static void deleteUser(int userID) {
		MySQL mysql = new MySQL();

		mysql.deleteUser(userID);

		mysql.closeConnection();
	}
	
	public void addDish(String dName) {
		MySQL mysql = new MySQL();
		mysql.addFavorite(this.userID, dName);
		mysql.closeConnection();
	}

	public void addAllergen(String aName) {
		MySQL mysql = new MySQL();
		mysql.addAllergen(userID, aName);
		mysql.closeConnection();
	}

	public JSONArray getAllergens() {
		MySQL mysql = new MySQL();
		JSONArray json = new JSONArray();

		ResultSet rs = mysql.getAllergens(this.userID);
		try {
		while (rs.next()) {
			json.add(rs.getString("name"));
		}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		mysql.closeConnection();
		return json;
	}

	public JSONArray getDishes() {
		MySQL mysql = new MySQL();
		JSONArray json = new JSONArray();
		ResultSet rs = mysql.getFavorites(this.userID);
		try {
		while (rs.next()) {
			json.add(rs.getString("name"));
		}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		mysql.closeConnection();
		return json;		
	}
}