package edu.se.par;

import java.awt.Graphics;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
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
		/////////////////
		layout.layoutArray = new String[][] { { "5u", "12u", "4", "3" }, { "11u", "6u", "14", "3" },
				{ "7u", "10u", "2", "15" }, { "9u", "8u", "16", "1" } };
		layout.inputPageCount = 16;
		layout.outputPageCount = 4;
		/////////////////
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
		String numberString = "";

		for (int i = 0; i < layout.getLayout().length; i++) {
			// for each output page

			// initialize the output page
			numberString = String.format("%04d", i);
			outputPages[i] = new OutputPage("output" + numberString, outputCols * inputPages[i].getWidth(),
					outputRows * inputPages[i].getHeight());
			Graphics g = outputPages[i].pageImage.getGraphics();
			for (int j = 0; j < layout.getLayout()[i].length; j++) {
				// for each input page
				
				//get the index of the next input page
				String iPage = layout.getLayout()[i][j];
				float rotation = 0;
				if(iPage.substring(iPage.length() - 1) == "u") {
					//the page needs to be upsidedown
					rotation = 180;
					iPage = iPage.substring(0, iPage.length() - 1);
				}
				int pageIndex = Integer.parseInt(iPage) - 1;
				
				// load the input page
				inputPages[pageIndex].load();

				// render the input page
				g.drawImage(inputPages[pageIndex].pageImage, inputPages[pageIndex].getWidth() * (i / outputCols), inputPages[pageIndex].getHeight() * (i / outputRows), null);

				// unload the input page
				inputPages[pageIndex].cleanup();

			}
			//render the output page
			FileManager.saveImage(outputPages[i].pageImage, outputPages[i].fileLocation);
			
			//unload the output page
			outputPages[i].unload();
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
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			loaded = true;
		}

		public void cleanup() {
			pageImage = null;
			loaded = false;
			// delete the image file
			// todo
		}
		
		public void flipImage(byte axis) {
			// 0 -> no flip
			// 1 -> vertical flip
			// 2 -> horizontal flip
			// 3 -> both flip (equal to 180 degree rotation)

			int scaleHo = (((axis >> 1) % 2) == 1) ? -1 : 1;
			int scaleV = ((axis % 2) == 1) ? -1 : 1;

			int scaleWidth = -pageImage.getWidth() * ((axis >> 1) % 2);
			int scaleHeight = -pageImage.getHeight() * (axis % 2);

			AffineTransform tx = AffineTransform.getScaleInstance(scaleHo, scaleV);
			tx.translate(scaleWidth, scaleHeight);
			AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
			pageImage = op.filter(pageImage, null);
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
			pageImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		}

		public void renderPage() {
			FileManager.saveImage(pageImage, fileLocation);
		}

		public void unload() {
			pageImage = null;
		}
	}
}
