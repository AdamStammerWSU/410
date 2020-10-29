package edu.se.par;

import javax.swing.JFrame;

public class GUI {
	
	public static void main(String[] args) {
		System.out.println("Howdy");
		GUI gui = new GUI();
	}
	
	
	
	JFrame frame;
	
	
	public GUI() {
		frame = new JFrame();
		frame.setSize(500, 500);
		frame.setTitle("This is a test");
		frame.setVisible(true);
	}

}
