package endpoints;

import dininghall.*;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import java.time.LocalDate;

import org.json.simple.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class AsyncAPI {

	public static final String[] HALLS = { "Everybody's Kitchen", "USC Village Dining Hall",
			"Parkside Restaurant & Grill" };
	public static final String[] halls = { "everybody", "village", "parkside" };

	@Autowired
	private ImportService service;

	@RequestMapping(value = "importdate", method = RequestMethod.GET)
	public void importDate(int year, int month, int day) throws InterruptedException, ExecutionException {
		System.out.println("importing date to system");
		LocalDate date = LocalDate.of(year, month, day);
		CompletableFuture<Boolean> menus = service.importDate(date);
		CompletableFuture.allOf(menus).join();
	}

	@RequestMapping(value = "/testpull", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public JSONObject getDiningHallDate(int year, int month, int day) throws InterruptedException, ExecutionException {
		LocalDate d = LocalDate.of(year, month, day);
		String dininghall = "USC Village Dining Hall";
		DiningHall dh = new DiningHall(dininghall, d);
		if (dh.noData()) {
			CompletableFuture<Boolean> menus = service.importDate(d);
			CompletableFuture.allOf(menus).join();
			DiningHall dht = new DiningHall(dininghall, d);
			return dht.toSimplifiedJSON();
		}
		return dh.toSimplifiedJSON();
	}

	@RequestMapping(value = "/testpulldate", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public CompletableFuture<JSONObject> pullData(int year, int month, int day)
			throws InterruptedException, ExecutionException {
		LocalDate d = LocalDate.of(year, month, day);
		CompletableFuture<Object> obj =  service.importSupplier(d);
		return (CompletableFuture<JSONObject>) obj.get();
		
		// String dininghall = "USC Village Dining Hall";
		// DiningHall dh = new DiningHall(dininghall, d);
		// if (dh.noData()) {
		// 	System.out.println("importing date to system");
		// 	LocalDate date = LocalDate.of(year, month, day);
		// 	CompletableFuture<Boolean> menus = service.importDate(date);
		// 	CompletableFuture.allOf(menus).join();
		// 	dh = null;
		// 	dh = new DiningHall(dininghall, d);
		// 	return dh.toSimplifiedJSON();
		// }

		// return dh.toSimplifiedJSON();
	}
}
