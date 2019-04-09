package importer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class DiningHallImporter implements Runnable {
	private LocalDate date;
	private JSONArray res = null;
	private static final String scraperURI = "https://us-central1-menuscraper.cloudfunctions.net/scrape_date?date=";

	public DiningHallImporter(LocalDate d) {
		this.date = d;
	}

	private JSONArray getFromScraper() {
		JSONArray json = null;

		StringBuilder sb = new StringBuilder(scraperURI);
		try {
			sb.append(date.getYear());
			sb.append(date.getMonthValue());
			sb.append(date.getDayOfMonth());
			URL url = new URL(sb.toString());
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.connect();
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String line;
			sb = new StringBuilder();
			while ((line = in.readLine()) != null) {
			    sb.append(line);
			}
			in.close();
			con.disconnect();
			
			JSONParser p = new JSONParser();
			String jsonString = sb.toString();
			json = (JSONArray)p.parse(jsonString);
		} catch (MalformedURLException me) {
			me.printStackTrace();
		} catch (IOException ie) {
			ie.printStackTrace();
		} catch (ParseException pe) {
			pe.printStackTrace();
		}
		return json;
	}

	public void run() {
		this.res = getFromScraper();
		System.out.println(this.res.get(0));
		this.importToDatabase();
	}

	private void importToDatabase() {
		Connection conn = getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;

		for (int i = 0; i < this.res.size(); i++) {
			JSONObject json = (JSONObject)this.res.get(i);
			int dhID = getdhID((String)json.get("dining_hall"), conn, ps, rs);
			int mID = getmID((String)json.get("meal"), conn, ps, rs);
		}

		closeConnection(conn, ps, rs);
	}

	private int getdhID(String dhName, Connection conn, PreparedStatement ps, ResultSet rs) {
		int id = -1;
		try {
			String sql = "SELECT diningHallID FROM DiningHalls WHERE name=?;";
			ps = conn.prepareStatement(sql);
			ps.setString(0, dhName);

			rs = ps.executeQuery();

			if (rs.next()) {
				return rs.getInt("diningHallID");
			}
			sql = "INSERT INTO DiningHalls (name) VALUES (?);";

			ps = conn.prepareStatement(sql);

			ps.setString(0, dhName);

			ps.execute();

			sql = "SELECT diningHallID FROM DiningHalls WHERE name=?;";
			ps = conn.prepareStatement(sql);
			ps.setString(0, dhName);

			rs = ps.executeQuery();

			if (rs.next()) {
				return rs.getInt("diningHallID");
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return id;
	}

	private int getmID(String mName, Connection conn, PreparedStatement ps, ResultSet rs) {
		int id = -1;
		try {
			String sql = "SELECT mealID FROM Meals WHERE name=?;";
			ps = conn.prepareStatement(sql);
			ps.setString(0, mName);

			rs = ps.executeQuery();

			if (rs.next()) {
				return rs.getInt("mealID");
			}
			sql = "INSERT INTO Meals (name) VALUES (?);";

			ps = conn.prepareStatement(sql);

			ps.setString(0, mName);

			ps.execute();

			sql = "SELECT mealID FROM Meals WHERE name=?;";
			ps = conn.prepareStatement(sql);
			ps.setString(0, mName);

			rs = ps.executeQuery();

			if (rs.next()) {
				return rs.getInt("mealID");
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return id;
	}

	private Connection getConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			// Local
			return DriverManager.getConnection("jdbc:mysql://localhost:3306/dining_app?user=root&password=root");
			// GCP
			//return DriverManager.getConnection("jdbc:mysql://35.185.251.221:3306/dining_app?user=root&password=root");
		} catch (SQLException sqle) {
			System.out.println("sqle: " + sqle.getMessage());
		} catch (ClassNotFoundException cnfe) {
			System.out.println("cnfe: " + cnfe.getMessage());
		}
		return null;
	}

	private void closeConnection(Connection conn, PreparedStatement ps, ResultSet rs) {
		try {
			if (rs != null) { rs.close(); }
			if (ps != null) { ps.close(); }
			if (conn != null) { conn.close(); }
		}
		catch (SQLException sqle) {
			System.out.println("sqle: " + sqle.getMessage());
		}
	}
}