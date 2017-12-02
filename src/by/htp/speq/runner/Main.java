package by.htp.speq.runner;

import java.io.IOException;

import by.htp.speq.command.StationAction;
import by.htp.speq.command.ActionHelper;
import by.htp.speq.view.ConsoleMenu;

public class Main {

	public static void main(String[] args) throws IOException {

		int input = 0;
		
		do {
			ConsoleMenu.printMenu();
			input = ConsoleMenu.readUserInput();

			StationAction action = ActionHelper.defineAction(input);
			action.performAction();

		} while (input != 6);
	}

}
