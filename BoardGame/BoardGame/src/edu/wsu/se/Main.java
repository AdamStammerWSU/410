package edu.wsu.se;

public class Main {

	public static void main(String[] args) {
		System.out.println("Howdy!");
		
		// Display the window
		// maybe insert player#/object in the constructor ex: GUI(player1);
		GUI results = new GUI();
		results.setLocationRelativeTo(null);
		results.setSize(800, 500);
		results.setVisible(true);
	}

}
