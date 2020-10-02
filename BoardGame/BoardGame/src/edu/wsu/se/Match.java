package edu.wsu.se;

import java.util.ArrayList;
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

		gui = new GUI(this);
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
		gui.TITLE_MESSAGE("Waiting For Others To Connect...");
		netHandler = new NetworkHandler(server, ip, port);

		myPlayerNumber = netHandler.getMyNumber();
		System.out.println("My Number is: " + myPlayerNumber);
		gui.setTitle("Player " + myPlayerNumber);
		gui.thePlayer.setText("Player " + netHandler.getMyNumber());

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

		game = new Game(players, this);

		game.start();

		System.out.println("Start");
		System.out.println("Turn Number: " + game.gameNumber);
		System.out.println(players[0].displayHand());
		System.out.println(players[1].displayHand());
		System.out.println(players[2].displayHand());
		System.out.println(players[3].displayHand());
		System.out.println(game.displayMatrix());
	}

	public ArrayList<Integer> getWinner(boolean gameEnd) {
		ArrayList<Integer> winners = new ArrayList<Integer>();
		int lowestScore = Integer.MAX_VALUE;
		for (int i = 0; i < 4; i++) {
			if(players[i].getScore() < lowestScore) {
				lowestScore = players[i].getScore();
			}
		}
		
		for (int i = 0; i < 4; i++) {
			if(players[i].getScore() == lowestScore) {
				winners.add(i + 1);
			}
		}
		
		if (gameEnd)
			return winners;
		return new ArrayList<Integer>();
	}

	public class Player {
		/*
		 * Contains (A)Player Constructor (B)Dummy Constructor (C)Add to Hand (D)Display
		 * Hand (E)Misc. Setters and Getters
		 */
		Set<Integer> hand = new TreeSet<Integer>();
		int number;
		int wins;
		int score = 0;

		//////////////////////////////////////////////// (A)Constructor
		public Player(int number) {
			// Basic building blocks of player
			this.number = number;
			wins = 0;
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

		public void resetHand() {
			hand = new TreeSet<Integer>();
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
		
		public void addWin() {
			wins++;
		}

		public Set<Integer> getHand() {
			return hand;
		}

		public void setHand(Set<Integer> hand) {
			this.hand = hand;
		}

		public int getScore() {
			return score;
		}

		public void calculateScore(boolean win) {
			if (win) {
				if(hand.size() > 9) {
					score+=9;
				} else {
					score += hand.size();
				}
			} else {
				score += 10;
			}
		}
	}
}
