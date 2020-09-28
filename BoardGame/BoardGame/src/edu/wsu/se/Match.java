package edu.wsu.se;

public class Match {

	public static void main(String[] args) {
		System.out.println("Howdy!");

		Match match = new Match();
	}

	public Match() {

		// Display the window
		// maybe insert player#/object in the constructor ex: GUI(player1);
		GUI results = new GUI();
		results.setLocationRelativeTo(null);
		results.setSize(800, 500);
		results.setVisible(true);

		// Real Start
		// Player p1 = new Player(1, "");
		// Player p2 = new Player(2, "");
		// Player p3 = new Player(3, "");
		// Player p4 = new Player(4, "");
		// Dummy Start
		Player p1 = new Player(1, 3, false);
		Player p2 = new Player(2, 4, false);
		Player p3 = new Player(3, 5, false);
		Player p4 = new Player(4, 6, false);

		Game g = new Game(p1.getHand(), p2.getHand(), p3.getHand(), p4.getHand());
		System.out.println("Start");
		System.out.println("Turn Number: " + g.turnNumber);
		System.out.println(p1.displayHand());
		System.out.println(p2.displayHand());
		System.out.println(p3.displayHand());
		System.out.println(p4.displayHand());
		System.out.println(g.displayMatrix());

		// Turn 1
		System.out.println("Turn 1");
		System.out.println("Turn Number: " + g.turnNumber);
		g.newTurn(1, 1, p1.getHand());
		p1.addNumber(1);
		g.newTurn(2, 7, p2.getHand());
		p2.addNumber(1);
		g.newTurn(3, 1, p3.getHand());
		p3.addNumber(1);
		g.newTurn(4, 1, p4.getHand());
		p4.addNumber(1);
		System.out.println(p1.displayHand());
		System.out.println(p2.displayHand());
		System.out.println(p3.displayHand());
		System.out.println(p4.displayHand());
		System.out.println(g.displayMatrix());

		// Turn 2
		System.out.println("Turn 2");
		System.out.println("Turn Number: " + g.turnNumber);
		g.newTurn(1, 1, p1.getHand());
		p1.addNumber(1);
		g.newTurn(2, 3, p2.getHand());
		p2.addNumber(3);
		g.newTurn(3, 1, p3.getHand());
		p3.addNumber(1);
		g.newTurn(4, 1, p4.getHand());
		p4.addNumber(1);
		System.out.println(p1.displayHand());
		System.out.println(p2.displayHand());
		System.out.println(p3.displayHand());
		System.out.println(p4.displayHand());
		System.out.println(g.displayMatrix());

		// Turn 3
		System.out.println("Turn 3");
		System.out.println("Turn Number: " + g.turnNumber);
		g.newTurn(1, 1, p1.getHand());
		p1.addNumber(1);
		g.newTurn(2, 5, p2.getHand());
		p2.addNumber(5);
		g.newTurn(3, 1, p3.getHand());
		p3.addNumber(1);
		g.newTurn(4, 1, p4.getHand());
		p4.addNumber(1);
		System.out.println(p1.displayHand());
		System.out.println(p2.displayHand());
		System.out.println(p3.displayHand());
		System.out.println(p4.displayHand());
		System.out.println(g.displayMatrix());

		// Turn 4
		System.out.println("Turn 4");
		System.out.println("Turn Number: " + g.turnNumber);
		g.newTurn(1, 1, p1.getHand());
		p1.addNumber(1);
		g.newTurn(2, 6, p2.getHand());
		p2.addNumber(6);
		g.newTurn(3, 1, p3.getHand());
		p3.addNumber(1);
		g.newTurn(4, 1, p4.getHand());
		p4.addNumber(1);
		System.out.println(p1.displayHand());
		System.out.println(p2.displayHand());
		System.out.println(p3.displayHand());
		System.out.println(p4.displayHand());
		System.out.println(g.displayMatrix());

	}
}
