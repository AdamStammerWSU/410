package edu.se.par;

public class Layout {

	int inputPageCount;
	int outputPageCount;
	int outputCols;
	int outputRows;
	String layoutText;
	String layoutArray[][];

	public Layout(String layoutText) {
		this.layoutText = layoutText;
		// process the layoutText String
		try {
			String[] lines = layoutText.split("\n");
			outputCols = Integer.parseInt(lines[0].split(" ")[0]);
			outputRows = Integer.parseInt(lines[0].split(" ")[1]);
			layoutArray = new String[lines.length - 1][lines[1].split(" ").length];
			for (int i = 1; i < lines.length; i++) {
				layoutArray[i - 1] = lines[i].split(" ");
			}

			inputPageCount = layoutArray.length * layoutArray[0].length;
			outputPageCount = layoutArray.length;
		} catch (Exception e) {
			System.out.println("Bad Layout File");
			layoutText = "hee hee";
			//load in default layout
		}
	}

	public int getInputPageCount() {
		return inputPageCount;
	}

	public int getOutputPageCount() {
		return outputPageCount;
	}

	public String[][] getLayout() {
		return layoutArray;
	}

	public String getLayoutString() {
		return layoutText;
	}

	public int getOutputCols() {
		return outputCols;
	}

	public int getOutputRows() {
		return outputRows;
	}

	public void saveLayout() {

	}
}