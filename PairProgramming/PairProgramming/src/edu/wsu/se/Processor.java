package edu.wsu.se;

import java.util.ArrayList;

class Processor {

	ArrayList<ArrayList<Integer>> parsedData = null;
	int largestValue = -Integer.MAX_VALUE, smallestValue = Integer.MAX_VALUE;
	ArrayList<Integer> largestValueLines = null, smallestValueLines = null;

	public Processor(ArrayList<ArrayList<Integer>> parsedData) {
		this.parsedData = parsedData;
		processData();
	}

	public void processData() {

	}

	public int numberOfLines() {
		return parsedData.size();
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

	public static int[] convertIntegers(ArrayList<Integer> integers) {
		int[] ret = new int[integers.size()];
		for (int i = 0; i < ret.length; i++) {
			ret[i] = integers.get(i).intValue();
		}
		return ret;
	}

	class DataLine {
		ArrayList<Integer> line = null;

		int lineNumber;
		int lineLargestValue = -Integer.MAX_VALUE;
		int lineSmallestValue = Integer.MAX_VALUE;
		boolean largestAlreadySeen = false;
		boolean smallestAlreadySeen = false;
		String sumString = "BREAKEVEN";

		public DataLine(int lineNumber, ArrayList<Integer> line) {
			this.line = line;
			this.lineNumber = lineNumber;
		}

		public void process() {

			int lineSum = 0;
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

			if (Main.DEBUG) {
				// test print out the collected data
				System.out.println("Line " + lineNumber + ":");
				System.out.println("\tContains " + line.size() + " numbers");
				System.out.print("\tSum: " + lineSum);

				System.out.println(" (" + sumString + ")");
				System.out.println("\tLargest in the line: " + lineLargestValue + " Repeated: " + largestAlreadySeen);
				System.out
						.println("\tSmallest in the line: " + lineSmallestValue + " Repeated: " + smallestAlreadySeen);
			}
		}
	}
}
