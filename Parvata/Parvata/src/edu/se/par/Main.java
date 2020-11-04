package edu.se.par;

public class Main {

	public static void main(String[] args) {
		System.out.println("Howdy");
		GUI gui = new GUI();
		gui.setSize(500, 500);
		gui.setLocationRelativeTo(null); // this isn't working either, for some reason...
		gui.setVisible(true);

	}

}
