package by.htp.speq.command.impl;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import by.htp.speq.command.StationAction;
import by.htp.speq.entity.RentUnit;
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
		ArrayList<RentUnit> units = (ArrayList<RentUnit>) rentedCatalog.getUnits();

		for (RentUnit unit : units) {
			if (unit != null) {
				System.out.println(rentedCatalog.getRenter() + ": " + unit);
			}
		}

	}

}
