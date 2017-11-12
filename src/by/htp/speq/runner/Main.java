package by.htp.speq.runner;

import java.io.IOException;

import by.htp.speq.command.StationAction;
import by.htp.speq.command.ActionHelper;
import by.htp.speq.view.ConsoleMenu;

public class Main {

	public static void main(String[] args) throws IOException {

		ConsoleMenu.printMenu();
		int input = ConsoleMenu.readUserInput();
		
		StationAction action = ActionHelper.defineAction(input);
		action.performAction();
	}
	
	
}
