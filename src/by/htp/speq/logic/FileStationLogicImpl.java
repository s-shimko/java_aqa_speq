package by.htp.speq.logic;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import by.htp.speq.entity.Equipment;
import by.htp.speq.entity.RentUnit;
import by.htp.speq.station.Catalog;
import by.htp.speq.station.RentedCatalog;

public class FileStationLogicImpl implements StationLogic {

	private static final String FILE_PATH_STATION_INFO = "resources/station_info.txt";
	private static final String FILE_PATH_RENTED_UNITS = "resources/rented_units.txt";
	private static final int DEFAULT_CAPACITY = 10;
	private static final String DELIMETER = ",";

	@Override
	public Catalog readCatalog() throws FileNotFoundException {

		Catalog catalog = new Catalog();
		String[] lines = readData(FILE_PATH_STATION_INFO);

		for (String line : lines) {
			RentUnit unit = createRentUnit(line);
			catalog.addRentUnit(unit);
		}

		return catalog;
	}
	
	@Override
	public RentedCatalog readRentedCatalog() throws FileNotFoundException {
		RentedCatalog rentedCatalog = new RentedCatalog();
		String[] lines = readData(FILE_PATH_RENTED_UNITS);
		
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

	private String[] readData(String file) {
		// FileInputStream fis = new FileInputStream(file);
		// BufferedInputStream bis = new BufferedInputStream(fis);

		// try {
		// int b;
		// while((b = fis.read()) != -1)
		// System.out.print((char)b);
		// } catch (IOException e) {
		// e.printStackTrace();
		// }
		BufferedReader br = null;
		int lastLineIndex = 0;
		String[] lines = new String[DEFAULT_CAPACITY];
		try {

			FileReader fr = new FileReader(file);
			br = new BufferedReader(fr);

			String line;

			while ((line = br.readLine()) != null) {
				if (lastLineIndex >= lines.length) {
					lines = increaseCapacity(lines);
				}
				lines[lastLineIndex] = line;
				lastLineIndex++;
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
		return trimDataToSize(lines, lastLineIndex);

	}

	private String[] increaseCapacity(String[] lines) {
		int currentLine = 0;
		String[] newLines = new String[lines.length + DEFAULT_CAPACITY];
		for (String line : lines) {
			newLines[currentLine] = line;
			currentLine++;
		}
		return newLines;
	}

	private String[] trimDataToSize(String[] lines, int linesCount) {
		int currentLine = 0;
		String[] trimedLines = new String[linesCount];

		for (String line : lines) {
			if (line != null) {
				trimedLines[currentLine] = line;
				currentLine++;
			}
		}
		return trimedLines;
	}



}
