package edu.wsu.se;

public class Main {
	
	static int i = 0;

	public static void main(String[] args) {
		Main main = new Main();
		main.start();
	}
	
	public void start() {
		System.out.println("Howdy!");
		
		// Display the window
		// maybe insert player#/object in the constructor ex: GUI(player1);
		GUI results = new GUI(this);
		i = 10;
		results.setLocationRelativeTo(null);
		results.setSize(800, 500);
		results.setVisible(true);
		
		Game g = new Game();
		g.startGame2();
		System.out.println("Start");
		System.out.println("Turn Number: "+g.turnNumber);
		System.out.println(g.displayHandP1());
		System.out.println(g.displayHandP2());
		System.out.println(g.displayHandP3());
		System.out.println(g.displayHandP4());
		System.out.println(g.displayMatrix());
		
		//Turn 1
		g.newTurn(1, 1);
		g.newTurn(2, 1);
		g.newTurn(3, 1);
		g.newTurn(4, 1);
		System.out.println("Turn 1");
		System.out.println("Turn Number: "+g.turnNumber);
		System.out.println(g.displayHandP1());
		System.out.println(g.displayHandP2());
		System.out.println(g.displayHandP3());
		System.out.println(g.displayHandP4());
		System.out.println(g.displayMatrix());
		
		
		//Turn 2
		g.newTurn(1, 1);
		g.newTurn(2, 3);
		g.newTurn(3, 1);
		g.newTurn(4, 1);
		System.out.println("Turn 2");
		System.out.println("Turn Number: "+g.turnNumber);
		System.out.println(g.displayHandP1());
		System.out.println(g.displayHandP2());
		System.out.println(g.displayHandP3());
		System.out.println(g.displayHandP4());
		System.out.println(g.displayMatrix());
		
		//Turn 3
		g.newTurn(1, 1);
		g.newTurn(2, 5);
		g.newTurn(3, 1);
		g.newTurn(4, 1);
		System.out.println("Turn 3");
		System.out.println("Turn Number: "+g.turnNumber);
		System.out.println(g.displayHandP1());
		System.out.println(g.displayHandP2());
		System.out.println(g.displayHandP3());
		System.out.println(g.displayHandP4());
		System.out.println(g.displayMatrix());
		
		//Turn 4
		g.newTurn(1, 1);
		g.newTurn(2, 6);
		g.newTurn(3, 1);
		g.newTurn(4, 1);
		System.out.println("Turn 4");
		System.out.println(g.displayHandP1());
		System.out.println(g.displayHandP2());
		System.out.println(g.displayHandP3());
		System.out.println(g.displayHandP4());
		System.out.println(g.displayMatrix());
	}

}
