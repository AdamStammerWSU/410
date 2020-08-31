package edu.wsu.se;

import java.util.ArrayList;
import java.util.Arrays;

class Processor {

	ArrayList<DataLine> data = null;
	int largestValue = -Integer.MAX_VALUE, smallestValue = Integer.MAX_VALUE, sum = 0;
	ArrayList<Integer> largestValueLines = null, smallestValueLines = null;

	String lineDataOutput = "";
	String sumString = "BREAKEVEN";

	public Processor(ArrayList<ArrayList<Integer>> parsedData) {
		data = new ArrayList<DataLine>();
		for (int i = 0; i < parsedData.size(); i++) {
			data.add(new DataLine(i, parsedData.get(i)));
		}
		largestValueLines = new ArrayList<Integer>();
		smallestValueLines = new ArrayList<Integer>();
		processData();
	}

	public void processData() {

		for (int i = 0; i < data.size(); i++) {
			DataLine tempLine = data.get(i);

			// process lines
			tempLine.process();
			lineDataOutput += data.get(i).dataPrint() + "\n";

			// process file
			sum += tempLine.sum();

			if (tempLine.largestValue() == largestValue) {
				largestValueLines.add(tempLine.lineNumber());
			} else if (tempLine.largestValue() > largestValue) {
				largestValue = tempLine.largestValue();
				largestValueLines = new ArrayList<Integer>();
				largestValueLines.add(tempLine.lineNumber());
			}

			if (tempLine.smallestValue() == smallestValue) {
				smallestValueLines.add(tempLine.lineNumber());
			} else if (tempLine.smallestValue() < smallestValue) {
				smallestValue = tempLine.smallestValue();
				smallestValueLines = new ArrayList<Integer>();
				smallestValueLines.add(tempLine.lineNumber());
			}
		}

	}

	public int numberOfLines() {
		return data.size();
	}

	public int largestValue() {
		return largestValue;
	}

	public int largestValueCount() {
		return largestValueLines.size();
	}

	public int[] largestValueLines() {
		return convertIntegers(largestValueLines);
	}

	public int smallestValue() {
		return smallestValue;
	}

	public int smallestValueCount() {
		return smallestValueLines.size();
	}

	public int[] smallestValueLines() {
		return convertIntegers(smallestValueLines);
	}

	public int sum() {
		return sum;
	}
	
	public String rawDataOutput() {
		String s = "";
		for (int i =0 ;i < data.size();i++) {
			s += data.get(i).rawString() + "\n";
		}
		
		return s;
	}

	public String lineDataOutput() {
		return lineDataOutput;
	}

	public String fileDataOutput() {
		String s = "";
		s += "Number Of Lines Of Data: " + numberOfLines() + "\n";
		s += "Largest Number In File: " + largestValue() + "\n";
		s += "Largest Number Occurred On Line(s): " + Arrays.toString(largestValueLines()) + "\n";
		s += "Smallest Number In File: " + smallestValue() + "\n";
		s += "Smallest Number Occurred On Line(s): " + Arrays.toString(smallestValueLines()) + "\n";
		s += "Sum Of All Numbers: " + sum() + "\n";

		sumString = "BREAKEVEN";
		if (sum != 0)
			sumString = (sum > 0) ? "PROFIT" : "-LOSS";
		return s;
	}

	public static int[] convertIntegers(ArrayList<Integer> integers) {
		int[] ret = new int[integers.size()];
		for (int i = 0; i < ret.length; i++) {
			ret[i] = integers.get(i).intValue();
		}
		return ret;
	}

	class DataLine {
		ArrayList<Integer> line = null;

		private int lineNumber;
		private int lineSum = 0;
		private int lineLargestValue = -Integer.MAX_VALUE;
		private int lineSmallestValue = Integer.MAX_VALUE;
		private boolean largestAlreadySeen = false;
		private boolean smallestAlreadySeen = false;
		private String sumString = "BREAKEVEN";

		public DataLine(int lineNumber, ArrayList<Integer> line) {
			this.line = line;
			this.lineNumber = lineNumber;
		}

		public void process() {
			for (int j = 0; j < line.size(); j++) {
				// for each element in the line
				int x = line.get(j);
				lineSum += x;
				if (x == lineLargestValue)
					largestAlreadySeen = true;
				if (x == lineSmallestValue)
					smallestAlreadySeen = true;
				if (x > lineLargestValue) {
					// new largest
					largestAlreadySeen = false;
					lineLargestValue = x;
				}
				if (x < lineSmallestValue) {
					// new smallest
					smallestAlreadySeen = false;
					lineSmallestValue = x;
				}
			}
			sumString = "BREAKEVEN";
			if (lineSum != 0)
				sumString = (lineSum > 0) ? "PROFIT" : "-LOSS";
		}

		public int largestValue() {
			return lineLargestValue;
		}

		public int smallestValue() {
			return lineSmallestValue;
		}

		public int lineNumber() {
			return lineNumber;
		}

		public int sum() {
			return lineSum;
		}
		
		public String rawString() {
			String s = Arrays.toString(convertIntegers(line));
			return s.substring(1, s.length()-1);
		}

		public String dataPrint() {
			String s = "";
			s += "Line " + lineNumber + ": " + Arrays.toString(convertIntegers(line)) + "\n";
			s += "\tContains " + line.size() + " numbers\n";
			s += "\tSum: " + lineSum;
			s += " (" + sumString + ")\n";
			s += "\tLargest in the line: " + lineLargestValue + " Repeated: " + largestAlreadySeen + "\n";
			s += "\tSmallest in the line: " + lineSmallestValue + " Repeated: " + smallestAlreadySeen + "\n";
			return s;
		}
	}
}
