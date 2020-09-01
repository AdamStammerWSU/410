package edu.wsu.se;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {

	static boolean DEBUG = true;
	static Processor processor = null;

	public static void main(String[] args) {

		// After all the reading is done
		// Display the results window
		GUI results = new GUI();
		results.setLocationRelativeTo(null);
		results.setSize(800, 500);
		results.setVisible(true);

	}

	static void process(String file) {
		String data = readInputFile(file).trim();

		ArrayList<ArrayList<Integer>> parsedData = new ArrayList<ArrayList<Integer>>();

		String[] lines = data.split("\n"); // split the lines apart
		for (int i = 0; i < lines.length; i++) { // for each line
			// process each line into a number array
			ArrayList<Integer> thisLine = new ArrayList<Integer>();
			String[] l = lines[i].split(" ");
			for (int j = 0; j < l.length; j++) {
				thisLine.add(new Integer(l[j]));
			}
			parsedData.add(thisLine);
		}

		String rawInput = "";
		for (int i = 0; i < parsedData.size(); i++) {
			for (int j = 0; j < parsedData.get(i).size(); j++) {
				rawInput += parsedData.get(i).get(j) + " ";
			}
			rawInput += "\n";
		}
		processor = new Processor(parsedData);
		System.out.println(rawInput);
		System.out.println(processor.lineDataOutput());
		System.out.println("\n\n" + processor.fileDataOutput());

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
