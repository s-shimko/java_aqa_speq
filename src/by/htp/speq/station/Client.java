package by.htp.speq.station;

import java.util.ArrayList;

import by.htp.speq.entity.RentUnit;

public class Client extends Catalog {
	
	private ArrayList<RentUnit> unitsInRent;

	public ArrayList<RentUnit> getUnitsInRent() {
		return unitsInRent;
	}

	public void setUnitsInRent(ArrayList<RentUnit> unitsInRent) {
		this.unitsInRent = unitsInRent;
	}

	@Override
	public String toString() {
		return "Client [unitsInRent=" + unitsInRent + ", getUnits()=" + getUnits()
				+ ", toString()=" + super.toString() + "]";
	}
	
	
	
}
