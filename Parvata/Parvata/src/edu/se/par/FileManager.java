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
	//Just use image name for fileLocation right now for example NEWFILE.png
	//(IMAGE IS IN FOLDER ON DESKTOP CALLED BOOK)
	
	static void saveImage(BufferedImage buffy, String fileLocation) {
		
		boolean isWindows = System.getProperty("os.name")
	    		  .toLowerCase().startsWith("windows");
		
		String homeDirectory = System.getProperty("user.home");
		Process process;
		ProcessBuilder builder = new ProcessBuilder();
		
		try {
			BufferedImage slayer = buffy;
			File outputFile = new File(homeDirectory+"/Desktop/BOOK/"+fileLocation);//homeDirectory+"/Desktop/TESTER/"
			
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
				System.out.println("Saved " + outputFile + " file successfully.");
			else {
				System.out.println("Did NOT save " + outputFile + " file successfully.");
			}
			
		} catch (IOException e) {
			e.getMessage();
		}
	}

//////////////////////////////////////////////////////////////////////////Image Load
		//Input as fileName.PNG 
		//(IMAGE MUST BE IN IMAGES FOLDER ON DESKTOP CALLED BOOK)
		
		static BufferedImage loadImage(String fileLocation) throws Exception{
			
			//System.out.println("Canonical: "+new File(fileLocation).getCanonicalFile());
			boolean isWindows = System.getProperty("os.name")
		    		  .toLowerCase().startsWith("windows");
			
			String homeDirectory = System.getProperty("user.home");
			Process process;
			ProcessBuilder builder = new ProcessBuilder();
			
			BufferedImage originalImage = null;
			try {
			
			originalImage = ImageIO.read(new File(homeDirectory+"/Desktop/BOOK/"+fileLocation));
			}catch(IOException e) {
				e.getMessage();
			}
			if(originalImage == null) {
				System.out.println("Did NOT load "+ fileLocation+ " file successfully.");
			}else {
				System.out.println("Loaded "+ fileLocation+ " file successfully.");
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
		
		builder.directory(new File(System.getProperty("user.home")+"/Desktop/BOOK/"));
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
