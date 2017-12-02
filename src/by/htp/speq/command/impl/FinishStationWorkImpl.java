package by.htp.speq.command.impl;

import java.io.FileNotFoundException;

import by.htp.speq.command.StationAction;
import by.htp.speq.station.StationData;

public class FinishStationWorkImpl implements StationAction {

	@Override
	public void performAction() throws FileNotFoundException {
		System.out.println("Finish Rent station work. Common penalty: " + StationData.penaltyForItems);

	}

}
