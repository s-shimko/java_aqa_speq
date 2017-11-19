package by.htp.speq.command.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import by.htp.speq.command.StationAction;
import by.htp.speq.entity.RentUnit;
import by.htp.speq.logic.FileStationLogicImpl;
import by.htp.speq.logic.StationLogic;
import by.htp.speq.station.Catalog;
import by.htp.speq.station.Client;
import by.htp.speq.view.ConsoleMenu;

public class TakeCatalogItemInRentActionImpl implements StationAction {

	private StationLogic logic;
	{
		logic = new FileStationLogicImpl();
	}

	@Override
	public void performAction() throws FileNotFoundException {
		System.out.println("Choose which item you want take in rent:");

		Catalog catalog = logic.readCatalog();
		ArrayList<RentUnit> units = (ArrayList<RentUnit>) catalog.getUnits();

		for (RentUnit unit : units) {
			if (unit != null) {
				System.out.println(unit);
			}
		}
		
		logic.takeItemInRent();
	}

}
