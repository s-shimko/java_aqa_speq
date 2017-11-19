package by.htp.speq.station;

import java.util.Arrays;

import by.htp.speq.entity.RentUnit;

public class RentedCatalog extends Catalog {

	private String renter = "Default renter";
	
	public String getRenter() {
		return renter;
	}

	public void setRenter(String renter) {
		this.renter = renter;
	}

	@Override
	public String toString() {
		return "renter=" + renter + ", getUnits()=" + getUnits() + "]";
	}
	

}
