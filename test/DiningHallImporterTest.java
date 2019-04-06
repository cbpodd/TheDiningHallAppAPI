import static org.junit.Assert.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import importer.DiningHallImporter;

public class DiningHallImporterTest {
    @Test
    public void createDHI() {
		LocalDate d = LocalDate.now();
		DiningHallImporter dhi = new DiningHallImporter(d);
		dhi.run();
		assertTrue("First test case", true);
    }
}