package edu.wsu.se;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Main {

	public static void main(String[] args) {
		
		// For testing purposes
		//GUI results = new GUI();
		//results.setLocationRelativeTo(null);
		//results.setSize(500, 200);
		//results.setVisible(true);
		
		if (args.length > 1) {
			System.out.println("Too many arguements specified. Use --help for details of use.");
			exit();
		} else if (args.length < 1) {
			System.out.println("Not enough arguments specified. Use --help for details of use.");
			exit();
		} else { // only one argument
			if (args[0].equalsIgnoreCase("--help")) {
				// print help message
				System.out.println("Please specify the input file and nothing more.");
				System.out.println("Example Call:\n\t\tjava -jar pair.jar test.txt");
				System.out
						.println("\t\t\tThis would use test.txt in the same folder as pair.jar as the data input file");
			} else { // assume a text file has been specified
				System.out.println(readInputFile(args[0]));
				
				// After all the reading is done
				// Display the results window
				GUI results = new GUI();
				results.setLocationRelativeTo(null);
				results.setSize(500, 500);
				results.setVisible(true);
				
			}
		}
	}

	static String readInputFile(String fileLocation) {
		File file = new File(fileLocation);
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			System.out.println("File not found (" + fileLocation + ")");
			exit();
			e.printStackTrace();
		}

		String dataLine = "";
		String data = "";
		try {
			while ((dataLine = reader.readLine()) != null) {
				data += dataLine + "\n";
			}
		} catch (IOException e) {
			System.out.println("Failed to read from the file");
			exit();
			e.printStackTrace();
		}

		return data;
	}

	static void exit() {
		System.out.println("Exiting...");
		System.exit(0);
	}

}
