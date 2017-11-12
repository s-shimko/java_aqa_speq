package by.htp.speq.station;

import by.htp.speq.entity.RentUnit;

public class Catalog {

	private static final int DEFAULT_CAPACITY = 10;

	private RentUnit[] units;
	private int lastIndex;

	public Catalog() {
		units = new RentUnit[DEFAULT_CAPACITY];
		lastIndex = 0;
	}

	public int getLastIndex() {
		return lastIndex;
	}

	public RentUnit[] getUnits() {
		return units;
	}
	
	public RentUnit getLastRentUnit() {
		return units[lastIndex-1];
	}

	public void addRentUnit(RentUnit unit) {
		units[lastIndex] = unit;
		lastIndex++;
	}
	
	
}
