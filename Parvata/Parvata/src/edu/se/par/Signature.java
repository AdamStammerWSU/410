package edu.se.par;

import java.awt.image.BufferedImage;

public class Signature {
	
	
	
	public Signature() {
		
	}
	
	
	
	
	
	
	
	class InputPage {
		
		BufferedImage page = null;
		String fileLocation = "";
		boolean loaded = false;
		
		public InputPage(String loc) {
			this.fileLocation = loc;
		}
		
		public void load() {
			page = FileManager.loadImage(fileLocation);
			loaded = true;
		}
		
		public void unload() {
			page = null;
			loaded = false;
		}
		
		public boolean isLoaded() {
			return loaded;
		}
	}
	
	class OutputPage {
		
		InputPage[] inputPages;
		BufferedImage page = null;
		String fileLocation = "";
		
		public OutputPage(String loc, int width, int height, int inputPageCount, int pageNumber) {
			this.fileLocation = loc;
		}
		
		public void addPage(int x, int, y, float rotation) {
			
		}
		
		public void renderPage() {
			FileManager.saveImage(page, fileLocation);
		}
		
		public void unload() {
			page = null;
		}
	}
}
