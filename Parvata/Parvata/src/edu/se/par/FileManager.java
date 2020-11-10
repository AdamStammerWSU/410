package edu.se.par;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;

import javax.imageio.ImageIO;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.tools.PDFToImage;
import org.apache.pdfbox.tools.imageio.ImageIOUtil;




class FileManager {

	//Image Save
	static void saveImage(BufferedImage page, String fileLocation) {
		//unused
		page = null;
		//unused
		
		
		
		
		
	}
	//Image Load
	static BufferedImage loadImage(String fileLocation) throws IOException {
		
		
			
		//PDImageXObject pdImage = PDImageXObject.createFromFile(fileLocation, document);
		//contentStream.drawImage(pdImage, 20f, 20f); 
		//unused
		BufferedImage page = null;
		return page;
		//unused
	}
	
	//Image Conversions
	static void PNGtoPDF() {
		
	}
	
	static void PDFtoPNG(String fileLocation) throws IOException {
		
		PDDocument document = PDDocument.load(new File(fileLocation));
		PDFRenderer pdfRenderer = new PDFRenderer(document);
		
		for (int page = 0; page < document.getNumberOfPages(); ++page)
		{ 
		    BufferedImage buffimage = pdfRenderer.renderImageWithDPI(page, 300, ImageType.RGB);
		    ImageIOUtil.writeImage(buffimage, "file-" + (page+1) + ".png", 300);
		}
		document.close();
		
		/*
		//Works inside terminal
		//ImageMagik
		//convert -density 300 file:///Users/kd7933mc/Desktop/th.PDF -resize 25% a.png
		//
		//CONVERT file:///Users/kd7933mc/Desktop/th.PDF -quality 100 F:\th4.PNG
		
		String command = "CONVERT file:///Users/kd7933mc/Desktop/th.PDF -quality 100 F:\th4.PNG";
		Runtime r = Runtime.getRuntime(); 
        r.exec(command); 
        */
	}
	
	
	//Layout Methods
	static void saveLayout() {
			
	}
	
	static void loadLayout() {
			
	}
	
}
