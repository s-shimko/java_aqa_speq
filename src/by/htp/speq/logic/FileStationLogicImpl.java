package by.htp.speq.logic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import by.htp.speq.entity.Accessory;
import by.htp.speq.entity.Equipment;
import by.htp.speq.entity.RentUnit;
import by.htp.speq.entity.RentedUnit;
import by.htp.speq.station.Catalog;
import by.htp.speq.station.Client;
import by.htp.speq.station.RentedCatalog;
import by.htp.speq.station.StationData;
import by.htp.speq.view.ConsoleMenu;

public class FileStationLogicImpl implements StationLogic {

	private static final String FILE_PATH_STATION_INFO = "resources/station_info.txt";
	private static final String FILE_PATH_RENTED_UNITS = "resources/rented_units.txt";
	private static final String DELIMETER = ",";
	public static String RENT_DAY = getDateToday();
	
	static String getDateToday() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		Date date = new Date();
		return dateFormat.format(date);
	}

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
			RentedUnit unit = createRentedUnit(line);
			rentedCatalog.addRentedUnit(unit);
		}
		return rentedCatalog;
	}

	private RentUnit createRentUnit(String line) {

		String[] values = getRentUnitValues(line);

		RentUnit unit = null;
		if (values[0].equals("Equipment")) {
			Equipment eq = new Equipment();
			eq.setType(values[0].trim());
			eq.setTitle(values[1].trim());
			eq.setCategory(values[3].trim());

			unit = new RentUnit();
			unit.setEquipment(eq);
			unit.setHourRate(Double.parseDouble(values[2].trim()));
		} else {
			Accessory acc = new Accessory();
			acc.setType(values[0].trim());
			acc.setTitle(values[1].trim());
			acc.setCategory(values[3].trim());
			acc.setBelongTo(values[4].trim());

			unit = new RentUnit();
			unit.setEquipment(acc);
			unit.setHourRate(Double.parseDouble(values[2].trim()));
		}

		return unit;
	}

	private RentedUnit createRentedUnit(String line) {

		String[] values = getRentUnitValues(line);

		RentedUnit unit = null;
		if (values[0].equals("Equipment")) {
			Equipment eq = new Equipment();
			eq.setType(values[0].trim());
			eq.setTitle(values[1].trim());
			eq.setCategory(values[3].trim());

			unit = new RentedUnit();
			unit.setEquipment(eq);
			unit.setHourRate(Double.parseDouble(values[2].trim()));
			unit.setTakenInRentDate(values[4].trim());
		} else {
			Accessory acc = new Accessory();
			acc.setType(values[0].trim());
			acc.setTitle(values[1].trim());
			acc.setCategory(values[3].trim());
			acc.setBelongTo(values[4].trim());

			unit = new RentedUnit();
			unit.setEquipment(acc);
			unit.setHourRate(Double.parseDouble(values[2].trim()));
			unit.setTakenInRentDate(values[5].trim());
		}

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

	public void addItemToRentWriteFile(int index) {
		ArrayList<String> unitsForRent = readData(FILE_PATH_STATION_INFO);
		String takenItem = unitsForRent.get(index - 1) + ", " + RENT_DAY;
		unitsForRent.remove(index - 1);

		ArrayList<String> unitsInRent = readData(FILE_PATH_RENTED_UNITS);
		unitsInRent.add(takenItem);

		writeData(unitsForRent, FILE_PATH_STATION_INFO);
		writeData(unitsInRent, FILE_PATH_RENTED_UNITS);
	}

	@Override
	public Client takeItemInRent() throws FileNotFoundException {
		int input = 0;
		try {
			input = ConsoleMenu.readUserInput();
		} catch (IOException e) {
			e.printStackTrace();
		}

		addItemToRentWriteFile(input);
		return null;
	}

	@Override
	public Client backItemFromRent() throws FileNotFoundException {
		int input = 0;
		try {
			input = ConsoleMenu.readUserInput();
		} catch (IOException e) {
			e.printStackTrace();
		}

		returnItemFromRentWriteFile(input);
		return null;
	}

	public void returnItemFromRentWriteFile(int index) {
		ArrayList<String> unitsInRent = readData(FILE_PATH_RENTED_UNITS);
		String takenItem = unitsInRent.get(index - 1);
		unitsInRent.remove(index - 1);

		// remove date, calculate penalty and plus to StationData value
		String[] values = getRentUnitValues(takenItem);
		String takenInRentDate = values[values.length - 1];
		double price = Double.parseDouble(values[2]);
		double penaltyForItem = RentedUnit.calculatePenalty(takenInRentDate, RentedUnit.CHECK_DATE, price);
		StationData.penaltyForItems = StationData.penaltyForItems + penaltyForItem;
	
		String[] newValues = removeElement(values, values.length - 1);
		takenItem = String.join(",", newValues);

		ArrayList<String> unitsForRent = readData(FILE_PATH_STATION_INFO);
		unitsForRent.add(takenItem);

		writeData(unitsForRent, FILE_PATH_STATION_INFO);
		writeData(unitsInRent, FILE_PATH_RENTED_UNITS);
	}

	public static String[] removeElement(String[] values, int i) {
		String[] result = new String[values.length - 1];
		System.arraycopy(values, 0, result, 0, i);
		System.arraycopy(values, i + 1, result, i, result.length - i);
		return result;
	}
	
}
