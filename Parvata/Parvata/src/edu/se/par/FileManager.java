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
		BufferedImage page = null;
		return page;
		//unused
	}
	
	//Image Conversions
	static void PNGtoPDF() {
		
	}
	
	static void PDFtoPNG(String fileLocation) throws IOException {
		
		//Works inside terminal
		//CONVERT file:///Users/kd7933mc/Desktop/th.PDF -quality 100 F:\th2.PNG
		String command = "CONVERT file:///Users/kd7933mc/Desktop/th.PDF -quality 100 F:\th4.PNG";
		Runtime r = Runtime.getRuntime(); 
        r.exec(command); 
	}
	
	
	//Layout Methods
	static void saveLayout() {
			
	}
	
	static void loadLayout() {
			
	}
	
}
