package edu.se.par;

import java.awt.image.BufferedImage;

public class Signature {

	Layout layout;
	int inputPageOffset, outputPageOffset;
	InputPage inputPages[];
	OutputPage outputPage;
	int test = 0;

	public Signature(Layout layout, int inputPageOffset, int outputPageOffset) {
		this.layout = layout;
		this.inputPageOffset = inputPageOffset;
		this.outputPageOffset = outputPageOffset;
		String numberString = "";
		for (int i = 0; i < layout.getInputPageCount(); i++) {
			numberString = String.format("%04d", i + inputPageOffset);
			inputPages[i] = new InputPage("/input" + numberString);
		}
	}

	public void impose() {

	}

	class InputPage {

		BufferedImage page = null;
		String fileLocation = "";
		boolean loaded = false;

		public InputPage(String loc) {
			this.fileLocation = loc;
		}

		public void load() {
			page = FileManager.loadImage(fileLocation);
			loaded = true;
		}

		public void unload() {
			page = null;
			loaded = false;
		}

		public boolean isLoaded() {
			return loaded;
		}
	}

	class OutputPage {

		InputPage[] inputPages;
		BufferedImage page = null;
		String fileLocation = "";

		public OutputPage(String loc, int width, int height, int inputPageCount, int pageNumber) {
			this.fileLocation = loc;
		}

		public void addPage(int x, int y, float rotation) {
			
		}

		public void renderPage() {
			FileManager.saveImage(page, fileLocation);
		}

		public void unload() {
			page = null;
		}
	}
}
