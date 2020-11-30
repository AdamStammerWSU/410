package edu.se.par;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;

public class Imposer {

	Layout layout;
	GUI gui;
	int numberOfSignatures = 0;

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

		// count pages to determine how many signatures are necessary
		File folder = new File(".");
		//String[] filess = folder.list();
		//for (int i = 0; i < filess.length; i++) {
		//	System.out.println(filess[i]);
		//}
		final String[] files = folder.list(new FilenameFilter() {
			@Override
			public boolean accept(final File dir, final String name) {
				return name.matches("input-.....png");
			}
		});

		System.out.println(files.toString() + " \n" + files.length);

		numberOfSignatures = (int) Math.ceil(files.length / (layout.inputPageCount + 0.0f));
		System.out.println(files.length + " input pages...." + numberOfSignatures + " signatures required");

		for (int i = 0; i < numberOfSignatures; i++) {
			// create the signature
			Signature sig = new Signature(layout, layout.inputPageCount * i, layout.outputPageCount * i);
			// impose the signature
			sig.impose();
		}

		// compile pdf
		FileManager.PNGtoPDF("output-", gui.getSaveFilePath());
		
		FileManager.cleanupTempFiles();
	}

}