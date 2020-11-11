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
	
//////////////////////////////////////////////////////////////////////////Image Save
	//Just use image name for fileLocation right now for example newFile.png
	
		static void saveImage(BufferedImage buffy, String fileLocation) {
			
			System.out.println("LEAP");
			
			try {
				BufferedImage slayer = buffy;
				File outputFile = new File(fileLocation);
				
				boolean didCreate = false;
				int n = fileLocation.length();
				char last = fileLocation.charAt(n - 1);
				char secondLast = fileLocation.charAt(n - 2);
				
				if(Character.compare(last,'f') == 0 || Character.compare(last,'F') == 0 ) {
					didCreate = ImageIO.write(buffy, "pdf", outputFile);
				}
				else {
					if(Character.compare(secondLast,'n') == 0|| Character.compare(secondLast,'N') == 0)
						didCreate = ImageIO.write(buffy, "png", outputFile);
					}
				
				if(didCreate == true)
					System.out.println("Saved " + outputFile + " file.");
				
			} catch (IOException e) {
				e.getMessage();
			}
			
		}
//////////////////////////////////////////////////////////////////////////Image Load
		//Input as images/fileName.PNG
		
		static BufferedImage loadImage(String fileLocation) throws Exception{
			
			//Get location
			System.out.println(new File(fileLocation).getCanonicalFile());
			BufferedImage originalImage = null;
			
			//Loag Image
			try {
			originalImage = ImageIO.read(new File(fileLocation));
			}catch(IOException e) {
				e.getMessage();
			}
			
			//Output in terminal
			if(originalImage == null) {
				System.out.println("Image "+fileLocation+" has NOT been loaded.");
			}else {
				System.out.println("Image "+fileLocation+" has been loaded.");
			}
			
			return originalImage;
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
	    	new BufferedReader(new InputStreamReader(inputStream)).lines().forEach(consumer);
	    }
	}
}
