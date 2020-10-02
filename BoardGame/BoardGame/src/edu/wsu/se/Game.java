package edu.wsu.se;

import java.util.Random;

import edu.wsu.se.Match.Player;

public class Game {

	boolean gameEnd = false;
	int roundNumber = 1;
	int whoseTurn = 1;
	int whomWon = -1;
	boolean[][] playerMatrix = new boolean[4][4];
	Player[] players = null;
	Match match = null;

/////////////////////////////////////////////////////////////////(A.2)Game Constructor
	public Game(Player[] players, Match m) {
		this.players = players;
		this.match = m;
	}

	public void resetGame() {
		gameEnd = false;
		roundNumber = 1;
		whoseTurn = 1;
		whomWon = -1;
		for (int i = 0; i < 4; i++) {
			players[i].resetHand();
		}
	}

///////////////////////////////////////////////////(B)Matrix Display
	public String displayMatrix() {
		String matrix = "P1: [";
		for (int i = 0; i < 4; i++) {
			matrix += playerMatrix[0][i] + " ";
		}
		matrix += "]\n";
		matrix += "P2: [";
		for (int i = 0; i < 4; i++) {
			matrix += playerMatrix[1][i] + " ";
		}
		matrix += "]\n";
		matrix += "P3: [";
		for (int i = 0; i < 4; i++) {
			matrix += playerMatrix[2][i] + " ";
		}
		matrix += "]\n";
		matrix += "P4: [";
		for (int i = 0; i < 4; i++) {
			matrix += playerMatrix[3][i] + " ";
		}
		matrix += "]\n";
		return matrix;
	}

	public void calculateScores() {
		boolean win = false;
		for (int i = 0; i < 4; i++) {
			if (i + 1 == getWhomWon())
				win = true;
			else
				win = false;
			players[i].calculateScore(win);
		}

	}

	public void start() {

		for (int i = 0; i < 3; i++) {
			resetGame();
			generateHands();
			while (!gameEnd) {
				// do a turn
				if (match.netHandler.isServer()) {
					match.netHandler.broadcast("" + whoseTurn);
				} else {
					// client

					System.out.println("starting-client");
					whoseTurn = Integer.parseInt(match.netHandler.readFromServer());
				}

				match.gui.updateDisplay();

				if (whoseTurn == match.netHandler.getMyNumber()) {
					while (whoseTurn == match.netHandler.getMyNumber()) {
						try {
							Thread.sleep(10);
						} catch (InterruptedException e) {
							match.gui.PROMPT_MESSAGE("Something Went Wrong! Game Closing!");
							System.exit(0);
						}
					}
				} else {
					// someone else's turn
					if (match.netHandler.isServer()) {
						System.out.println("Server Waiting For Client Choice");
						int num = Integer.parseInt(match.netHandler.readFromClient(whoseTurn - 2));
						match.netHandler.broadcastException(num + "", whoseTurn);
						newTurn(whoseTurn, num);
					} else {
						System.out.println("Client Waiting For Server To Forward Other Client Choice");
						int num = Integer.parseInt(match.netHandler.readFromServer());
						newTurn(whoseTurn, num);
					}
				}
			}
			calculateScores();
			match.gui.updateDisplay();
			if (i != 2) {
				match.gui.PROMPT_MESSAGE("Player " + whomWon + " won the game!");
				match.gui.TITLE_MESSAGE("Waiting For Server...");
			}
		}
		match.gui.TITLE_MESSAGE("Game Finished!");
		match.gui.PROMPT_MESSAGE("Player " + match.getWinner(gameEnd) + " won the match! Woo Hoo!");
		System.exit(0);
	}

	public void updateMatrix() {

		for (int i = 0; i < 4; i++) {
			// for each player
			for (int j = 0; j < 4; j++) {
				// loop through each of the players and compare hands
				if (players[i].getHand().containsAll(players[j].getHand())) {
					// player i has everything player j has
					playerMatrix[i][j] = true;
				} else {
					playerMatrix[i][j] = false;
				}
			}
		}
	}

	public void generateHands() {
		// generate player hands
		NetworkHandler handler = match.netHandler;
		if (handler.isServer()) {
			Random rand = new Random();
			for (int x = 0; x < 4; x++) {
				for (int y = 0; y < 3; y++) {
					int i = rand.nextInt(20) + 1;
					//////////////////////////// END GAME TESTING
//					if (x == 1) {
//						i = y + 1;
//					} else {
//						i = y + 2;
//					}
					//////////////////////////// END GAME TESTING
					if (players[x].getHand().contains(i)) {
						y--;
					} else {
						newTurn(x + 1, i);
						handler.broadcast(i + "");
					}
				}
			}
		} else {
			for (int x = 0; x < 4; x++) {
				for (int y = 0; y < 3; y++) {
					int i = Integer.parseInt(handler.readFromServer());
					newTurn(x + 1, i);
				}
			}
		}
		checkWin();
	}

////////////////////////////////////////////////////////////////(D)New Turn
	public void newTurn(int playerNum, int wanted) {

		players[playerNum - 1].addNumber(wanted);
		updateMatrix();
		checkWin();
		whoseTurn++;
		if (whoseTurn > 4) {
			whoseTurn = 1;
		}

	}

///////////////////////////////////////////////////(E)Check If Wins
	public void checkWin() {

		gameEnd = false;
		for (int i = 0; i < 4; i++) {
			int playersBeat = 0;
			for (int j = 0; j < 4; j++) {
				if (playerMatrix[i][j]) {
					playersBeat++;
				}
			}
			if (playersBeat >= 4) {
				// this player won
				whomWon = i + 1;
				gameEnd = true;
			}
		}
	}

/////////////////////////////////////////////////////(H)Getters and Setters
/////////////////////////////////////////////////////Turn Number
	public int getTurnNumber() {
		return roundNumber;
	}

	public void setTurnNumber(int turnNumber) {
		this.roundNumber = turnNumber;
	}

/////////////////////////////////////////////////////Whose Turn
	public int getWhoseTurn() {
		return whoseTurn;
	}

	public void setWhoseTurn(int whoseTurn) {
		this.whoseTurn = whoseTurn;
	}

/////////////////////////////////////////////////////Game End
	public boolean isGameEnd() {
		return gameEnd;
	}

	public void setGameEnd(boolean gameEnd) {
		this.gameEnd = gameEnd;
	}

/////////////////////////////////////////////////////Who Won
	public int getWhomWon() {
		return whomWon;
	}

	public void setWhomWon(int whomWon) {
		this.whomWon = whomWon;
	}

/////////////////////////////////////////////////////Matrix
	public boolean[][] getPlayerMatrix() {
		return playerMatrix;
	}

	public void setPlayerMatrix(boolean[][] playerMatrix) {
		this.playerMatrix = playerMatrix;
	}
}
