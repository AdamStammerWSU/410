package edu.se.par;

import java.io.IOException;

public class Imposer {

	Layout layout;
	GUI gui;

	public Imposer(GUI gui, Layout layout) {
		this.layout = layout;
		this.gui = gui;
	}

	public void impose() {
		// generate images
		try {
			FileManager.PDFtoPNG(gui.getOpenFilePath());
		} catch (IOException | InterruptedException e1) {

		}
		// create the signature
		Signature sig = new Signature(layout, 0, 0);

		// impose the signature
		sig.impose();

		// compile pdf
		FileManager.PNGtoPDF("output-", gui.getSaveFilePath());
	}

}
