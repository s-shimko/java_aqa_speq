package by.htp.speq.station;

import static org.junit.Assert.*;

import java.util.ArrayList;

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
		ArrayList<RentUnit> units = (ArrayList<RentUnit>) catalog.getUnits();
		assertNotNull("RentUnit was not initialized", units);
	}

	@Test
	public void testAddedRentUnit() {
		Equipment eq = new Equipment("Equipment1");

		RentUnit unit = new RentUnit();
		unit.setEquipment(eq);
		unit.setHourRate(2.5);

		catalog.addRentUnit(unit);

		ArrayList<RentUnit> units = (ArrayList<RentUnit>) catalog.getUnits();
		assertEquals(units.get(0), unit);
	}

}
