package edu.se.par;

import java.awt.image.BufferedImage;
import java.io.IOException;

public class Signature {

	Layout layout;
	int inputPageOffset, outputPageOffset;
	InputPage inputPages[];
	OutputPage[] outputPages;
	int test = 0;

	public Signature(Layout layout, int inputPageOffset, int outputPageOffset) {
		this.layout = layout;
		this.inputPageOffset = inputPageOffset;
		this.outputPageOffset = outputPageOffset;
		inputPages = new InputPage[layout.getLayout().length * layout.getLayout()[0].length];
		outputPages = new OutputPage[layout.getInputPageCount()];
		String numberString = "";
		for (int i = 0; i < layout.getInputPageCount(); i++) {
			numberString = String.format("%04d", i + inputPageOffset);
			inputPages[i] = new InputPage("/input" + numberString);
		}
	}

	public void impose() {

		int inputPagesPerOutputPages = layout.getLayout()[0].length;
		// int outputRows = layout.getOutputPageRows();
		// int outputCols = layout.getOutputPageCols();
		int outputRows = 2;
		int outputCols = 2;

		for (int i = 0; i < layout.getLayout().length; i++) {
			// for each output page

			// initialize the output page
			outputPages[i] = new OutputPage("output", outputCols * inputPages[i].getWidth(),
					outputRows * inputPages[i].getHeight());
			for (int j = 0; j < layout.getLayout()[i].length; j++) {
				// for each input page

				// load the input page
				inputPages[i * inputPagesPerOutputPages + j].load();

				// render the input page
				outputPages[i].addPage(0, 0, 0);

				// unload the input page
				inputPages[i].unload();

			}
		}
	}

	class InputPage {

		BufferedImage pageImage = null;
		String fileLocation = "";
		boolean loaded = false;

		public InputPage(String loc) {
			this.fileLocation = loc;
		}

		public void load() {
			try {
				pageImage = FileManager.loadImage(fileLocation);
			} catch (IOException e) {
				e.printStackTrace();
			}
			loaded = true;
		}

		public void unload() {
			pageImage = null;
			loaded = false;
		}

		public boolean isLoaded() {
			return loaded;
		}

		public int getHeight() {
			if (!loaded) {
				return 0;
			}
			return pageImage.getHeight();
		}

		public int getWidth() {
			if (!loaded) {
				return 0;
			}
			return pageImage.getWidth();
		}
	}

	class OutputPage {
		BufferedImage pageImage = null;
		String fileLocation = "";

		public OutputPage(String loc, int width, int height) {
			this.fileLocation = loc;
		}

		public void addPage(int x, int y, float rotation) {

		}

		public void renderPage() {
			FileManager.saveImage(pageImage, fileLocation);
		}

		public void unload() {
			pageImage = null;
		}
	}
}
