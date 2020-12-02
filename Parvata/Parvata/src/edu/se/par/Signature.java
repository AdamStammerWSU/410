package edu.se.par;

import java.awt.Graphics;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

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
		//initialize all the input pages by name
		String numberString = "";
		for (int i = 0; i < layout.getInputPageCount(); i++) {
			numberString = String.format("%04d", i + inputPageOffset);
			inputPages[i] = new InputPage("input-" + numberString + ".png");
		}
		//load the first page so that we know the size of the pages
		inputPages[0].load();
	}

	public void impose() {
		String numberString = "";

		for (int i = 0; i < layout.getLayout().length; i++) {
			// for each output page

			// initialize the output page
			numberString = String.format("%04d", i+outputPageOffset);
			outputPages[i] = new OutputPage("output-" + numberString + ".png", layout.getOutputCols() * inputPages[0].getWidth(),
					layout.getOutputRows() * inputPages[0].getHeight());
			Graphics g = outputPages[i].pageImage.getGraphics(); //get the graphics of the output image

			for (int x = 0; x < layout.getOutputRows(); x++) {
				// for each row
				for (int z = 0; z < layout.getOutputCols(); z++) {
					// for each column
					String iPage = layout.getLayout()[i][x * layout.getOutputRows() + z];
					//check to see if the page needs to be rotated
					float rotation = 0;
					if (iPage.substring(iPage.length() - 1).compareTo("u") == 0) {
						// the page needs to be upside-down
						rotation = 180;
						iPage = iPage.substring(0, iPage.length() - 1);
					}
					//process the input page index
					int pageIndex = Integer.parseInt(iPage) - 1;
					// load the input page
					inputPages[pageIndex].load();
					
					//create blank page if necessary
					if(inputPages[pageIndex].pageImage == null) {
						inputPages[pageIndex].pageImage = new BufferedImage(inputPages[0].getWidth(), inputPages[0].getHeight(), BufferedImage.TYPE_INT_ARGB);
					}

					// flip image if necessary
					if (rotation == 180)
						inputPages[pageIndex].flipImage((byte) 0b11);

					// render the input page to the output page
					g.drawImage(inputPages[pageIndex].pageImage, inputPages[pageIndex].getWidth() * z, inputPages[pageIndex].getHeight() * x, null);
					
					//unload the input page
					inputPages[pageIndex].cleanup();
				}
			}

//			}
			// render the output page
			FileManager.saveImage(outputPages[i].pageImage, outputPages[i].fileLocation);

			// unload the output page
			outputPages[i].unload();
		}
	}

	class InputPage {

		BufferedImage pageImage = null;
		String fileLocation = "";

		public InputPage(String loc) {
			this.fileLocation = loc;
		}

		public void load() {
			try {
				pageImage = FileManager.loadImage(fileLocation);
			} catch (Exception e) {
				//flag for blank page
				pageImage = null;
			}
		}

		public void cleanup() {
			pageImage = null;
			// free the image memory
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

		public int getHeight() {
			return pageImage.getHeight();
		}

		public int getWidth() {
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
