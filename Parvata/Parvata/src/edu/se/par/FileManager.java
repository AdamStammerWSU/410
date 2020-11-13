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
	static void saveImage(BufferedImage buffy, String fileLocation) {

		boolean isWindows = isWindows();
		
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
				System.out.println("Saved " + outputFile + " file successfully.");
			else {
				System.out.println("Did NOT save " + outputFile + " file successfully.");
			}
			
		} catch (IOException e) {
			e.getMessage();
		}
	}
//////////////////////////////////////////////////////////////////////////Image Load
		static BufferedImage loadImage(String fileLocation) throws Exception{
			
			BufferedImage originalImage = null;
			try {
			
			originalImage = ImageIO.read(new File(fileLocation));
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
//////////////////////////////////////////////////////////////////////////Image PNG to PDF
	static void PNGtoPDF(String prefix, String fileLocation) {
		
		boolean isWindows = isWindows();
		
		Process p = null;
		try {
			if(isWindows == true)
				p = Runtime.getRuntime().exec("cmd /c convert " + prefix + "* " + fileLocation);
			else
				p = Runtime.getRuntime().exec("sh -c convert " + prefix + "* " + fileLocation);
			
		} catch (IOException e) {
			System.out.println("Failed to compile pdf");
		}
		
		while(p.isAlive()) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				System.out.println("Failed to compile pdf");
			}
		}
	}
//////////////////////////////////////////////////////////////////////////Image PDF to PNG
	static void PDFtoPNG(String fileLocation) throws IOException, InterruptedException {
		
		boolean isWindows = isWindows();
		
		Process p = null;
		try {
			if(isWindows == true)
				p = Runtime.getRuntime().exec("cmd /c convert -density 300 " + fileLocation + " input-%04d.png");
			else
				p = Runtime.getRuntime().exec("sh -c convert -density 300 " + fileLocation + " input-%04d.png");
			
		} catch (IOException e) {
			System.out.println("Failed to decompile pdf");
			e.printStackTrace();
		}
		
		while(p.isAlive()) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				System.out.println("Failed to decompile pdf");
			}
		}
	}
	
//////////////////////////////////////////////////////////////////////////Layout Methods
	static void saveLayout() {
				
	}
		
	static void loadLayout() {
				
	}
////////////////////////////////////////////////////////////////////////// Helper Method(s)
	static boolean isWindows()
	{
		boolean isWindows = System.getProperty("os.name")
	    		  .toLowerCase().startsWith("windows");
		
		if(isWindows == true)
			return true;
		else
			return false;
	}
/////////////////////////////////////////////////////////////////////////////////////////////Open Image
	//Write fileLocation as Desktop/th.pdf
	/*public void openImage(String fileLocation) throws IOException, InterruptedException {
		
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
		
		builder.directory(new File(System.getProperty("user.home")+"\\));
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
	}*/
}