package by.htp.speq.command.impl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import by.htp.speq.command.StationAction;
import by.htp.speq.entity.RentUnit;
import by.htp.speq.logic.ConsoleStationLogicImpl;
import by.htp.speq.logic.FileStationLogicImpl;
import by.htp.speq.logic.StationLogic;
import by.htp.speq.logic.DaoStationLogicImpl;
import by.htp.speq.station.Catalog;
import by.htp.speq.view.ConsoleMenu;

public class ViewCatalogActionImpl implements StationAction {

	private StationLogic logic;
	{
//		logic = new ConsoleStationLogicImpl();
//		logic = new FileStationLogicImpl();
//		logic = new DataBaseStationLogicImpl();
		logic = new DaoStationLogicImpl();
		
	}

	@Override
	public void performAction() throws FileNotFoundException {

		Catalog catalog = logic.readCatalog();
		ArrayList<RentUnit> units = (ArrayList<RentUnit>) catalog.getUnits();

		for (RentUnit unit : units) {
			if (unit != null) {
				System.out.println(unit);
			}
		}

	}

}
