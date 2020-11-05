package edu.se.par;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;




class FileManager {

	//Image Save
	static void saveImage(BufferedImage page, String fileLocation) {
		//unused
		page = null;
		//unused
		
		
		
		
		
	}
	//Image Load
	static BufferedImage loadImage(String fileLocation) throws IOException {
		
		
		
		//unused
		BufferedImage image = null;
		return image;
		//unused
	}
	
	//Image Conversions
	static void PNGtoPDF() {
		
	}
	
	static void PDFtoPNG(String fileLocation) throws IOException {
		
		Runtime rt = Runtime.getRuntime();
		Process pr = rt.exec("CONVERT file:///Users/kd7933mc/Desktop/th.PDF -quality 100 file:///Users/kd7933mc/Desktop/th.PNG");
		
		
		
		
		/*File file = new File(fileLocation);
		PDDocument document = PDDocument.load(file);
		PDFRenderer renderer = new PDFRenderer(document);
		BufferedImage image = renderer.renderImage(0);		//index
	    ImageIO.write(image, "JPEG", new File(fileLocation));
	    document.close();*/
		
	}
	
	
	//Layout Methods
	static void saveLayout() {
			
	}
	
	static void loadLayout() {
			
	}
	
}
