package by.htp.speq.logic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import by.htp.speq.entity.Equipment;
import by.htp.speq.entity.RentUnit;
import by.htp.speq.station.Catalog;
import by.htp.speq.station.Client;
import by.htp.speq.station.RentedCatalog;
import by.htp.speq.view.ConsoleMenu;

public class FileStationLogicImpl implements StationLogic {

	private static final String FILE_PATH_STATION_INFO = "resources/station_info.txt";
	private static final String FILE_PATH_RENTED_UNITS = "resources/rented_units.txt";
	private static final String DELIMETER = ",";

	@Override
	public Catalog readCatalog() throws FileNotFoundException {

		Catalog catalog = new Catalog();
		ArrayList<String> lines = readData(FILE_PATH_STATION_INFO);

		for (String line : lines) {
			RentUnit unit = createRentUnit(line);
			catalog.addRentUnit(unit);
		}

		return catalog;
	}

	@Override
	public RentedCatalog readRentedCatalog() throws FileNotFoundException {
		RentedCatalog rentedCatalog = new RentedCatalog();
		ArrayList<String> lines = readData(FILE_PATH_RENTED_UNITS);

		for (String line : lines) {
			RentUnit unit = createRentUnit(line);
			rentedCatalog.addRentUnit(unit);
		}
		return rentedCatalog;
	}

	private RentUnit createRentUnit(String line) {

		String[] values = getRentUnitValues(line);

		Equipment eq = new Equipment();
		eq.setTitle(values[0].trim());

		RentUnit unit = new RentUnit();
		unit.setEquipment(eq);
		unit.setHourRate(Double.parseDouble(values[1].trim()));

		return unit;
	}

	private String[] getRentUnitValues(String line) {
		String[] values = line.split(DELIMETER);
		return values;
	}

	private ArrayList<String> readData(String file) {
		BufferedReader br = null;
		ArrayList<String> lines = new ArrayList<String>();

		try {

			FileReader fr = new FileReader(file);
			br = new BufferedReader(fr);

			String line;

			while ((line = br.readLine()) != null) {
				lines.add(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return lines;

	}

	private void writeData(ArrayList<String> arrList, String file) {
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new FileWriter(file));
			for (String unit : arrList) {
				writer.write(unit + "\n");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				writer.close();
			} catch (Exception e) {
			}
		}
	}

	public void addItemToRent(int index) {
		ArrayList<String> unitsForRent = readData(FILE_PATH_STATION_INFO);
		String takenItem = unitsForRent.get(index - 1);
		unitsForRent.remove(index - 1);
		
		ArrayList<String> unitsInRent = readData(FILE_PATH_RENTED_UNITS);
		unitsInRent.add(takenItem);		
		
		writeData(unitsForRent, FILE_PATH_STATION_INFO);
		writeData(unitsInRent, FILE_PATH_RENTED_UNITS);
	}

	@Override
	public Client takeItemInRent() throws FileNotFoundException {
		String toWrite = "";
		ConsoleMenu.printMenu();
		int input = 0;
		try {
			input = ConsoleMenu.readUserInput();
			// toWrite = "string" + Integer.toString(input);
		} catch (IOException e) {
			e.printStackTrace();
		}

		addItemToRent(input);
		return null;
	}

}
