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
		
		//Start
		Player p1 = new Player();
		p1.Player(1, 1.0);
		Player p2 = new Player();
		p2.Player(2, 2.0);
		Player p3 = new Player();
		p2.Player(3, 3.0);
		Player p4 = new Player();
		p2.Player(4, 4.0);
	
		Game g = new Game(p1.getHand(),p2.getHand(),p3.getHand(),p4.getHand());
		
		g.startGame2();
		System.out.println("Start");
		System.out.println("Turn Number: "+g.turnNumber);
		System.out.println(p1.displayHand());
		System.out.println(p2.displayHand());
		System.out.println(p3.displayHand());
		System.out.println(p4.displayHand());
		System.out.println(g.displayMatrix());
		
		//Turn 1
		g.newTurn(1, 1,p1.getHand(),p2.getHand(),p3.getHand(),p4.getHand());
		p1.addNumber(1);
		g.newTurn(2, 1,p1.getHand(),p2.getHand(),p3.getHand(),p4.getHand());
		p2.addNumber(1);
		g.newTurn(3, 1,p1.getHand(),p2.getHand(),p3.getHand(),p4.getHand());
		p3.addNumber(1);
		g.newTurn(4, 1,p1.getHand(),p2.getHand(),p3.getHand(),p4.getHand());
		p4.addNumber(1);
		System.out.println("Turn 1");
		System.out.println("Turn Number: "+g.turnNumber);
		System.out.println(p1.displayHand());
		System.out.println(p2.displayHand());
		System.out.println(p3.displayHand());
		System.out.println(p4.displayHand());
		System.out.println(g.displayMatrix());
		
		
		//Turn 2
		g.newTurn(1, 1,p1.getHand(),p2.getHand(),p3.getHand(),p4.getHand());
		p1.addNumber(1);
		g.newTurn(2, 3,p1.getHand(),p2.getHand(),p3.getHand(),p4.getHand());
		p2.addNumber(3);
		g.newTurn(3, 1,p1.getHand(),p2.getHand(),p3.getHand(),p4.getHand());
		p3.addNumber(1);
		g.newTurn(4, 1,p1.getHand(),p2.getHand(),p3.getHand(),p4.getHand());
		p4.addNumber(1);
		System.out.println("Turn 2");
		System.out.println("Turn Number: "+g.turnNumber);
		System.out.println(p1.displayHand());
		System.out.println(p2.displayHand());
		System.out.println(p3.displayHand());
		System.out.println(p4.displayHand());
		System.out.println(g.displayMatrix());
		
		//Turn 3
		g.newTurn(1, 1,p1.getHand(),p2.getHand(),p3.getHand(),p4.getHand());
		p1.addNumber(1);
		g.newTurn(2, 5,p1.getHand(),p2.getHand(),p3.getHand(),p4.getHand());
		p2.addNumber(5);
		g.newTurn(3, 1,p1.getHand(),p2.getHand(),p3.getHand(),p4.getHand());
		p3.addNumber(1);
		g.newTurn(4, 1,p1.getHand(),p2.getHand(),p3.getHand(),p4.getHand());
		p4.addNumber(1);
		System.out.println("Turn 3");
		System.out.println("Turn Number: "+g.turnNumber);
		System.out.println(p1.displayHand());
		System.out.println(p2.displayHand());
		System.out.println(p3.displayHand());
		System.out.println(p4.displayHand());
		System.out.println(g.displayMatrix());
		
		//Turn 4
		g.newTurn(1, 1,p1.getHand(),p2.getHand(),p3.getHand(),p4.getHand());
		p1.addNumber(1);
		g.newTurn(2, 6,p1.getHand(),p2.getHand(),p3.getHand(),p4.getHand());
		p2.addNumber(6);
		g.newTurn(3, 1,p1.getHand(),p2.getHand(),p3.getHand(),p4.getHand());
		p3.addNumber(1);
		g.newTurn(4, 1,p1.getHand(),p2.getHand(),p3.getHand(),p4.getHand());
		p4.addNumber(1);
		System.out.println("Turn 4");
		System.out.println(p1.displayHand());
		System.out.println(p2.displayHand());
		System.out.println(p3.displayHand());
		System.out.println(p4.displayHand());
		System.out.println(g.displayMatrix());
	}

}
