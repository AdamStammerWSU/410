package edu.wsu.se;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {

	// create the processor (static so GUI can access it)
	static Processor processor = null;

	public static void main(String[] args) {

		// Display the window
		GUI results = new GUI();
		results.setLocationRelativeTo(null);
		results.setSize(800, 500);
		results.setVisible(true);

	}

	// starts the processing of the file
	static void process(String file) {
		// get the file name/location
		String data = readInputFile(file).trim();

		// create a place to store the numerical data
		ArrayList<ArrayList<Integer>> parsedData = new ArrayList<ArrayList<Integer>>();

		String[] lines = data.split("\n"); // split the lines apart
		for (int i = 0; i < lines.length; i++) { // for each line
			// process each line into a number array
			ArrayList<Integer> thisLine = new ArrayList<Integer>();
			String[] l = lines[i].split(" ");
			for (int j = 0; j < l.length; j++) {
				thisLine.add(new Integer(l[j]));
			}
			// store this number arraylist with the rest of the processed lines
			parsedData.add(thisLine);
		}

		String rawInput = "";
		for (int i = 0; i < parsedData.size(); i++) {// loop through each line of data
			for (int j = 0; j < parsedData.get(i).size(); j++) { // and each number in each line
				rawInput += parsedData.get(i).get(j) + " "; // build a string of that data
			}
			rawInput += "\n"; // new line to separate lines
		}
		processor = new Processor(parsedData); // create a processor with the data read from file
	}

	// reads an entire text file and returns its contents as a String
	static String readInputFile(String fileLocation) {
		File file = new File(fileLocation); // create file
		BufferedReader reader = null; // create reader
		try {
			reader = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			System.out.println("File not found (" + fileLocation + ")");
			exit();
		}

		String dataLine = ""; // place to store each line of data
		String data = ""; // place to store all data
		try {
			// read in data line by line until there are not more lines to read
			while ((dataLine = reader.readLine()) != null) {
				data += dataLine + "\n";
			}
		} catch (IOException e) {
			System.out.println("Failed to read from the file");
			exit();
		}

		return data;
	}

	static void exit() {
		// prompt exit and then end the program
		System.out.println("Exiting...");
		System.exit(0);
	}

}
