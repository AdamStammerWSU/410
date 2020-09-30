package edu.wsu.se;

import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

public class Match {

	Game game;
	
	public static void main(String[] args) {
		System.out.println("Howdy!");

		Match match = new Match();
	}

	GUI gui = null;
	NetworkHandler netHandler = null;

	Player[] players = null;
	int myPlayerNumber = -1;
	boolean server = false;

	public Match() {


		// Display the window
		// maybe insert player#/object in the constructor ex: GUI(player1);
		// GUI results = new GUI();
		gui = new GUI();
		gui.setLocationRelativeTo(null);
		gui.setSize(800, 500);
		gui.setVisible(true);

		players = new Player[4];

		server = gui.PROMPT_FOR_SERVER();
		String ip = "localhost";
		if (!server)
			ip = gui.PROMPT_FOR_IP();

		int port = gui.PROMPT_FOR_PORT();

		if (server)
			System.out.println("Starting as server");
		else
			System.out.println("Starting as client");

		netHandler = new NetworkHandler(server, ip, port);

		myPlayerNumber = netHandler.getMyNumber();
		System.out.println("My Number is: " + myPlayerNumber);
		gui.setTitle("Player " + myPlayerNumber);

		for (int i = 0; i < 4; i++) {
			players[i] = new Player(i + 1);
		}

		if (server) {
			// send out message that all players are connected
			netHandler.broadcast("All clear");
		} else {
			// clients need to wait to receive the message that everyone is connected
			System.out.println(netHandler.readFromServer());
		}

		// A:
		Game g = new Game(players);

		// generate player hands
		if (server) {
			Random rand = new Random();
			for (int x = 0; x < 4; x++) {
				for (int y = 0; y < 3; y++) {
					int i = rand.nextInt(20) + 1;
					g.newTurn(x, i, players[x].getHand());
					players[x].addNumber(i);
					System.out.println("generating initial hands");
					netHandler.broadcast(i+"");
				}
			}
		} else {
			for (int x = 0; x < 4; x++) {
				for (int y = 0; y < 3; y++) {
					int i = Integer.parseInt(netHandler.readFromServer());
					g.newTurn(x, i, players[x].getHand());
					players[x].addNumber(i);
				}
			}
		}

		// Real Start
		// Player players[0] = new Player(1);
		// Player players[1] = new Player(2);
		// Player players[2] = new Player(3);
		// Player players[3] = new Player(4);
		// Dummy Start
		// Player players[0] = new Player(1, 3, false);
		// Player players[1] = new Player(2, 4, false);
		// Player players[2] = new Player(3, 5, false);
		// Player players[3] = new Player(4, 6, false);

//		Game g = new Game(players[0].getHand(), players[1].getHand(), players[2].getHand(), players[3].getHand());
		System.out.println("Start");
		System.out.println("Turn Number: " + g.turnNumber);
		System.out.println(players[0].displayHand());
		System.out.println(players[1].displayHand());
		System.out.println(players[2].displayHand());
		System.out.println(players[3].displayHand());
		System.out.println(g.displayMatrix());
//
//		// Turn 1
//		System.out.println("Turn 1");
//		System.out.println("Turn Number: " + g.turnNumber);
//		g.newTurn(1, 1, players[0].getHand());
//		players[0].addNumber(1);
//		g.newTurn(2, 7, players[1].getHand());
//		players[1].addNumber(1);
//		g.newTurn(3, 1, players[2].getHand());
//		players[2].addNumber(1);
//		g.newTurn(4, 1, players[3].getHand());
//		players[3].addNumber(1);
//		System.out.println(players[0].displayHand());
//		System.out.println(players[1].displayHand());
//		System.out.println(players[2].displayHand());
//		System.out.println(players[3].displayHand());
//		System.out.println(g.displayMatrix());
//
//		// Turn 2
//		System.out.println("Turn 2");
//		System.out.println("Turn Number: " + g.turnNumber);
//		g.newTurn(1, 1, players[0].getHand());
//		players[0].addNumber(1);
//		g.newTurn(2, 3, players[1].getHand());
//		players[1].addNumber(3);
//		g.newTurn(3, 1, players[2].getHand());
//		players[2].addNumber(1);
//		g.newTurn(4, 1, players[3].getHand());
//		players[3].addNumber(1);
//		System.out.println(players[0].displayHand());
//		System.out.println(players[1].displayHand());
//		System.out.println(players[2].displayHand());
//		System.out.println(players[3].displayHand());
//		System.out.println(g.displayMatrix());
//
//		// Turn 3
//		System.out.println("Turn 3");
//		System.out.println("Turn Number: " + g.turnNumber);
//		g.newTurn(1, 1, players[0].getHand());
//		players[0].addNumber(1);
//		g.newTurn(2, 5, players[1].getHand());
//		players[1].addNumber(5);
//		g.newTurn(3, 1, players[2].getHand());
//		players[2].addNumber(1);
//		g.newTurn(4, 1, players[3].getHand());
//		players[3].addNumber(1);
//		System.out.println(players[0].displayHand());
//		System.out.println(players[1].displayHand());
//		System.out.println(players[2].displayHand());
//		System.out.println(players[3].displayHand());
//		System.out.println(g.displayMatrix());
//
//		// Turn 4
//		System.out.println("Turn 4");
//		System.out.println("Turn Number: " + g.turnNumber);
//		g.newTurn(1, 1, players[0].getHand());
//		players[0].addNumber(1);
//		g.newTurn(2, 6, players[1].getHand());
//		players[1].addNumber(6);
//		g.newTurn(3, 1, players[2].getHand());
//		players[2].addNumber(1);
//		g.newTurn(4, 1, players[3].getHand());
//		players[3].addNumber(1);
//		System.out.println(players[0].displayHand());
//		System.out.println(players[1].displayHand());
//		System.out.println(players[2].displayHand());
//		System.out.println(players[3].displayHand());
//		System.out.println(g.displayMatrix());
		//

	}

	public class Player {
		/*
		 * Contains (A)Player Constructor (B)Dummy Constructor (C)Add to Hand (D)Display
		 * Hand (E)Misc. Setters and Getters
		 */
		Set<Integer> hand = new TreeSet<Integer>();
		int number;
		int wins;

		//////////////////////////////////////////////// (A)Constructor
		public Player(int number) {
			// Basic building blocks of player
			this.number = number;
			wins = 0;
			// Makes sure the new hand has all 3 randomized different numbers
//			int counter = 0;
//			Random rand = new Random();
//			// while (counter < 3) {
//			for (int i = 0; i < 3; i++) {
////				Set<Integer> newNumber = new TreeSet<Integer>();
////				int newest = rand.nextInt(20) + 1;
////				newNumber.add(newest);
//				int random = rand.nextInt(20) + 1;
//				if (!hand.contains(random)) {
//					hand.add(random);
//					// counter++;
//				}
//			}
		}

		//////////////////////////////////////////////// (B)Dummy Constructor
		// Player used to test game
//		public Player(int number, int dummyNumber, boolean x) {
//			this.number = number;
//			hand.add(1);
//			hand.add(2);
//			hand.add(dummyNumber);
//		}

		//////////////////////////////////////////////// (C)Add to Hand
		// Add to players hand
		public void addNumber(int number) {
			hand.add(number);
		}

		//////////////////////////////////////////////// (D)Display Hand
		// Display hand
		public String displayHand() {
			String value = "[ ";
			for (int i : hand) {
				value += (" " + i);
			}
			value += " ]";
			return value;
		}

		/////////////////////////////////////////////// (E)Setters and Getters
		public int getNumber() {
			return number;
		}

		public void setNumber(int number) {
			this.number = number;
		}

		public int getWins() {
			return wins;
		}

		public void setWins(int wins) {
			this.wins = wins;
		}

		public Set<Integer> getHand() {
			return hand;
		}

		public void setHand(Set<Integer> hand) {
			this.hand = hand;
		}
	}
}
