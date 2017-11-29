package by.htp.speq.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleMenu {
	
	public static void printMenu() {
		System.out.println("1. View catalog");
		System.out.println("2. View equipment in rent");
		System.out.println("3. Take equipment in rent");
		System.out.println("4. Back equipment from rent");
		System.out.println("5. View equipment out of rent");
		System.out.println("6. Finish station work - report 24hrs");
	}

	public static int readUserInput() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = br.readLine();
		return convertInputString(input);
	} 
	
	private static int convertInputString(String input) {
		int value = Integer.parseInt(input);
		return value;
	}

}
