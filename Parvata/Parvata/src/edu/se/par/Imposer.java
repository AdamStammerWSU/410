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
		// FileManager.PDFtoPNG("");

		// create the signature
		Signature sig = new Signature(layout, 0, 0);

		// impose the signature
		sig.impose();

		// compile pdf
//		try {
//			FileManager.PNGtoPDF("");
//		} catch (IOException | InterruptedException e) {
//			gui.PROMPT_ERROR("Failed to compile pdf");
//		}
	}

}
