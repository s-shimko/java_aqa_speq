package by.htp.speq.command.impl;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import by.htp.speq.command.StationAction;
import by.htp.speq.entity.RentedUnit;
import by.htp.speq.logic.FileStationLogicImpl;
import by.htp.speq.logic.StationLogic;
import by.htp.speq.station.RentedCatalog;

public class ViewRentedItemsActionImpl implements StationAction {

	private StationLogic logic;
	{
		logic = new FileStationLogicImpl();
	}

	@Override
	public void performAction() throws FileNotFoundException {
		RentedCatalog rentedCatalog = logic.readRentedCatalog();
		ArrayList<RentedUnit> units = (ArrayList<RentedUnit>) rentedCatalog.getRentedUnits();

		for (RentedUnit unit : units) {
			if (unit != null) {
				System.out.println(unit);
			}
		}

	}

}
