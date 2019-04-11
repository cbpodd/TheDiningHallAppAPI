import static org.junit.Assert.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import importer.DiningHallImporter;

public class DiningHallImporterTest {
		
		@Test
		public void runDHI() {
			LocalDate d = LocalDate.now();
			System.out.println((java.sql.Date.valueOf(d)).toString());
			DiningHallImporter dhi = new DiningHallImporter(d);
			dhi.run();
			assertTrue("DHI ran sucessfully", true);			
		}
}