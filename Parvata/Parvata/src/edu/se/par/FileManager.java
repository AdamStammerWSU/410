package edu.se.par;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

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

//////////////////////////////////////////////////////////////////////////Image Save & Load
	static void saveImage(BufferedImage page, String fileLocation) {
		//unused
		page = null;
		//unused
	}
	//This retrieves images in [images/th2.PNG]
	
	static BufferedImage loadImage(String vampDen) throws IOException {
		
		System.out.println(new File(vampDen).getCanonicalFile());
		BufferedImage buffy = null;
		
		try {
			buffy = ImageIO.read(new File(vampDen));
		}catch(IOException e) {
			e.getMessage();
		}
		if(buffy == null) {
			System.out.println("Nest not cleared");
		}
		
		return buffy;
	}
//////////////////////////////////////////////////////////////////////////Image Conversions
	static void PNGtoPDF() {
		
	}
	
	static void PDFtoPNG(String fileLocation) throws IOException, InterruptedException {
		
	}
	
//////////////////////////////////////////////////////////////////////////Layout Methods
	static void saveLayout() {
				
	}
		
	static void loadLayout() {
				
	}
/////////////////////////////////////////////////////////////////////////////////////////////Open Image
	//Write fileLocation as Desktop/th.pdf
	
	public void openImage(String fileLocation) throws IOException, InterruptedException {
		
		boolean isWindows = System.getProperty("os.name")
	    		  .toLowerCase().startsWith("windows");
		
		String homeDirectory = System.getProperty("user.home");
		//fileLocation = "Desktop/th.PDF";
		
		Process process;
		ProcessBuilder builder = new ProcessBuilder();
		if (isWindows) {
		   builder.command("cmd.exe", "/c", "dir");
		} else {
			builder.directory(new File(System.getProperty("user.home")));
		    builder.command("sh", "-c", "open " + fileLocation);
		}
		builder.directory(new File(System.getProperty("user.home")));
		
		process = builder.start();
		StreamRead streamGobbler = new StreamRead(process.getInputStream(), System.out::println);
		Executors.newSingleThreadExecutor().submit(streamGobbler);
		int exitCode = process.waitFor();
		assert exitCode == 0;
		
		
		
	}
	static class StreamRead implements Runnable {
	    
		private InputStream inputStream;
	    private Consumer<String> consumer;
	 
	    public StreamRead(InputStream inputStream, Consumer<String> consumer) {
	        this.inputStream = inputStream;
	        this.consumer = consumer;
	    }
	
	    @Override
	    public void run() {
	    	//BufferedReader read; read = 
	    	new BufferedReader(new InputStreamReader(inputStream)).lines().forEach(consumer);
	    }
	}
	
	/*
	//Works inside terminal
	//ImageMagik
	//convert -density 300 file:///Users/kd7933mc/Desktop/th.PDF -resize 25% a.png
	//
	//CONVERT file:///Users/kd7933mc/Desktop/th.PDF -quality 100 F:th2.PNG
	 * 
	 * CONVERT file:///Users/kd7933mc/Desktop/th.PDF -quality 100 file:///Users/kd7933mc/Desktop/th2.PNG
	*/
	
}
