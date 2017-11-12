package by.htp.speq.station;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import by.htp.speq.entity.Equipment;
import by.htp.speq.entity.RentUnit;

public class CatalogTest {

	private Catalog catalog;

	@Before
	public void initCatalog() {
		catalog = new Catalog();
	}

	@Test
	public void testCatalogRentUnitNotNull() {
		RentUnit[] units = catalog.getUnits();
		assertNotNull("RentUnit was not initialized", units);
	}

	@Test
	public void testAddedRentUnit() {
		Equipment eq = new Equipment("Equipment1");

		RentUnit unit = new RentUnit();
		unit.setEquipment(eq);
		unit.setHourRate(2.5);

		catalog.addRentUnit(unit);

		RentUnit[] units = catalog.getUnits();
		RentUnit lastItem = catalog.getLastRentUnit();

		assertEquals("RentUnit wasn't added", unit, lastItem);
	}

}
