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

	public Match() {

		// Real Start
		Player p1 = new Player(1, "");
		Player p2 = new Player(2, "");
		Player p3 = new Player(3, "");
		Player p4 = new Player(4, "");
		// Dummy Start
		//Player p1 = new Player(1, 3, false);
		//Player p2 = new Player(2, 4, false);
		//Player p3 = new Player(3, 5, false);
		//Player p4 = new Player(4, 6, false);

		game = new Game(p1.getHand(), p2.getHand(), p3.getHand(), p4.getHand());
		

		GUI gui = new GUI(this);
		gui.PROMPT_FOR_SERVER();
		// other prompts here
		
		// Other stuff shouldn't happen until prompts have their answer
		gui.setLocationRelativeTo(null);
		gui.setSize(800, 500);
		gui.setVisible(true);
		
		System.out.println("Start");
		System.out.println("Turn Number: " + game.turnNumber);
		System.out.println(p1.displayHand());
		System.out.println(p2.displayHand());
		System.out.println(p3.displayHand());
		System.out.println(p4.displayHand());
		System.out.println(game.displayMatrix());

		// Turn 1
		System.out.println("Turn 1");
		System.out.println("Turn Number: " + game.turnNumber);
		game.newTurn(1, 1, p1.getHand());
		p1.addNumber(1);
		game.newTurn(2, 7, p2.getHand());
		p2.addNumber(1);
		game.newTurn(3, 1, p3.getHand());
		p3.addNumber(1);
		game.newTurn(4, 1, p4.getHand());
		p4.addNumber(1);
		System.out.println(p1.displayHand());
		System.out.println(p2.displayHand());
		System.out.println(p3.displayHand());
		System.out.println(p4.displayHand());
		System.out.println(game.displayMatrix());

		// Turn 2
		System.out.println("Turn 2");
		System.out.println("Turn Number: " + game.turnNumber);
		game.newTurn(1, 1, p1.getHand());
		p1.addNumber(1);
		game.newTurn(2, 3, p2.getHand());
		p2.addNumber(3);
		game.newTurn(3, 1, p3.getHand());
		p3.addNumber(1);
		game.newTurn(4, 1, p4.getHand());
		p4.addNumber(1);
		System.out.println(p1.displayHand());
		System.out.println(p2.displayHand());
		System.out.println(p3.displayHand());
		System.out.println(p4.displayHand());
		System.out.println(game.displayMatrix());

		// Turn 3
		System.out.println("Turn 3");
		System.out.println("Turn Number: " + game.turnNumber);
		game.newTurn(1, 1, p1.getHand());
		p1.addNumber(1);
		game.newTurn(2, 5, p2.getHand());
		p2.addNumber(5);
		game.newTurn(3, 1, p3.getHand());
		p3.addNumber(1);
		game.newTurn(4, 1, p4.getHand());
		p4.addNumber(1);
		System.out.println(p1.displayHand());
		System.out.println(p2.displayHand());
		System.out.println(p3.displayHand());
		System.out.println(p4.displayHand());
		System.out.println(game.displayMatrix());

		// Turn 4
		System.out.println("Turn 4");
		System.out.println("Turn Number: " + game.turnNumber);
		game.newTurn(1, 1, p1.getHand());
		p1.addNumber(1);
		game.newTurn(2, 6, p2.getHand());
		p2.addNumber(6);
		game.newTurn(3, 1, p3.getHand());
		p3.addNumber(1);
		game.newTurn(4, 1, p4.getHand());
		p4.addNumber(1);
		System.out.println(p1.displayHand());
		System.out.println(p2.displayHand());
		System.out.println(p3.displayHand());
		System.out.println(p4.displayHand());
		System.out.println(game.displayMatrix());

	}

	class Player {
		/*
		 * Contains (A)Player Constructor (B)Dummy Constructor (C)Add to Hand (D)Display
		 * Hand (E)Misc. Setters and Getters
		 */
		Set<Integer> hand = new TreeSet<Integer>();
		int number;
		int wins;
		String ip_address;

		//////////////////////////////////////////////// (A)Constructor
		public Player(int number, String ip_address) {
			// Basic building blocks of player
			this.number = number;
			this.ip_address = ip_address;
			wins = 0;
			// Makes sure the new hand has all 3 randomized different numbers
			int counter = 0;
			Random rand = new Random();
			while (counter < 3) {
				Set<Integer> newNumber = new TreeSet<Integer>();
				int newest = rand.nextInt(20) + 1;
				newNumber.add(newest);
				if (hand.containsAll(newNumber) == false) {
					hand.add(newest);
					counter++;
				}
			}
		}

		//////////////////////////////////////////////// (B)Dummy Constructor
		// Player used to test game
		public Player(int number, int dummyNumber, boolean x) {
			this.number = number;
			hand.add(1);
			hand.add(2);
			hand.add(dummyNumber);
		}

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

		public String getIp_address() {
			return ip_address;
		}

		public void setIp_address(String ip_address) {
			this.ip_address = ip_address;
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
