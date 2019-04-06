package importer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class DiningHallImporter implements Runnable {
	private LocalDate date;
	private JSONObject res = null;
	private static final String scraperURI = "https://us-central1-menuscraper.cloudfunctions.net/scrape_date?date=";

	public DiningHallImporter(LocalDate d) {
		this.date = d;
	}

	private JSONObject getFromScraper() {
		JSONObject json = null;

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
			jsonString = jsonString.replaceAll("[\\n\\t]", "");
			json = (JSONObject)p.parse(jsonString);
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
		System.out.println(this.res);
	}
}