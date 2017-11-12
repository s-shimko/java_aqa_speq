package by.htp.speq.command;

import by.htp.speq.command.impl.DefaultActionImpl;
import by.htp.speq.command.impl.ViewCatalogActionImpl;
import by.htp.speq.command.impl.ViewRentedItemsActionImpl;

public class ActionHelper {

	public static StationAction defineAction(int input) {
		
		StationAction action = new DefaultActionImpl();
		
		switch (input) {
		case 1:
			action = new ViewCatalogActionImpl();
			break;
		case 2:
			action = new ViewRentedItemsActionImpl();
		default:
		}
		return action;
	}
}
