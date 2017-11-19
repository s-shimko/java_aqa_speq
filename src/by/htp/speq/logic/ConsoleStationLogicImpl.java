package by.htp.speq.logic;

import java.io.FileNotFoundException;

import by.htp.speq.entity.Equipment;
import by.htp.speq.entity.RentUnit;
import by.htp.speq.station.Catalog;
import by.htp.speq.station.Client;
import by.htp.speq.station.RentedCatalog;

public class ConsoleStationLogicImpl implements StationLogic {

	@Override
	public Catalog readCatalog() {
		
		Equipment eq1 = new Equipment("Eq1");
		Equipment eq2 = new Equipment("Eq2");
		Equipment eq3 = new Equipment("Eq3");
		
		RentUnit re1 = new RentUnit(eq1, 2.5);
		RentUnit re2 = new RentUnit(eq2, 2.5);
		RentUnit re3 = new RentUnit(eq3, 2.5);
		
		Catalog catalog = new Catalog();

		return catalog;
	}

	@Override
	public RentedCatalog readRentedCatalog() {
		RentedCatalog rentedCatalog = new RentedCatalog();
		return rentedCatalog;
	}

	@Override
	public Client takeItemInRent() throws FileNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
