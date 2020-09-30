package edu.wsu.se;

import java.util.Random;

import edu.wsu.se.Match.Player;

public class Game {

	boolean gameEnd = false;
	int roundNumber = 1;
	int whoseTurn = 1;
	int whomWon = -1;
	boolean alreadyWon = false;
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
		alreadyWon = false;
		for (int i = 0; i < 4; i++) {
			players[i].hand.clear();
			for (int j = 0; j < 4; j++) {
				if (i == j)
					playerMatrix[i][j] = true;
				else
					playerMatrix[i][j] = false;
			}
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

	public void start() {

		generateHands();
		roundNumber = 1;
		whoseTurn = 1;
		gameEnd = false;

		while (!gameEnd) {
			System.out.println("starting");
			// do a turn
			if (match.netHandler.isServer()) {
				System.out.println("starting-server");
				match.netHandler.broadcast("" + whoseTurn);
			} else {
				// client

				System.out.println("starting-client");
				whoseTurn = Integer.parseInt(match.netHandler.readFromServer());
			}

			match.gui.updateDisplay();

			if (whoseTurn == match.netHandler.getMyNumber()) {
				int z = 0;
				while (whoseTurn == match.netHandler.getMyNumber()) {
					System.out.println(match.netHandler.getMyNumber() + " turn " + z++);
				}
			} else {
				// someone else's turn
				if (match.netHandler.isServer()) {
					int num = Integer.parseInt(match.netHandler.readFromClient(whoseTurn - 2));
					match.netHandler.broadcastException(num + "", whoseTurn);
					newTurn(whoseTurn, num);
				} else {
					int num = Integer.parseInt(match.netHandler.readFromServer());
					newTurn(whoseTurn, num);
				}
			}
			
			//match.gui.updateDisplay();
		}
		System.out.println("Game Over");
		match.gui.updateDisplay();
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
					if (players[x].getHand().contains(i)) {
						y--;
					} else {
						newTurn(x + 1, i);
						// players[x].addNumber(i);
						System.out.println("generating initial hands");
						handler.broadcast(i + "");
					}
				}
			}
		} else {
			for (int x = 0; x < 4; x++) {
				for (int y = 0; y < 3; y++) {
					int i = Integer.parseInt(handler.readFromServer());
					newTurn(x + 1, i);
					// players[x].addNumber(i);
				}
			}
		}
	}

////////////////////////////////////////////////////////////////(D)New Turn
	public void newTurn(int playerNum, int wanted) {

		players[playerNum - 1].addNumber(wanted);
		updateMatrix();
		checkWin();
		checkgameEnd();
		whoseTurn++;
		if (whoseTurn > 4) {
			whoseTurn = 1;
		}
		
//		switch (whoseTurn) {
//		case 1:
//			whoseTurn = 1;
//			updateMatrix();
//			checkWin();
//			checkgameEnd();
//			whoseTurn = 2;
//			break;
//		case 2:
//			whoseTurn = 2;
//			updateMatrix();
//			checkWin();
//			checkgameEnd();
//			whoseTurn = 3;
//			break;
//		case 3:
//			whoseTurn = 3;
//			updateMatrix();
//			checkWin();
//			checkgameEnd();
//			whoseTurn = 4;
//			break;
//		case 4:
//			whoseTurn = 4;
//			updateMatrix();
//			checkWin();
//			checkgameEnd();
//			incrementTurn();
//			whoseTurn = 1;
//			break;
//		}
	}

///////////////////////////////////////////////////(E)Check If Wins
	public void checkWin() {
		if (playerMatrix[0][1] == true && playerMatrix[0][2] == true && playerMatrix[0][3] == true) {
			whomWon = 1;
			gameEnd = true;
		}
		if (playerMatrix[1][0] == true && playerMatrix[1][2] == true && playerMatrix[1][3] == true) {
			whomWon = 2;
			gameEnd = true;
		}
		if (playerMatrix[2][0] == true && playerMatrix[2][1] == true && playerMatrix[2][3] == true) {
			whomWon = 3;
			gameEnd = true;
		}
		if (playerMatrix[3][0] == true && playerMatrix[3][1] == true && playerMatrix[3][2] == true) {
			whomWon = 4;
			gameEnd = true;
		}
	}

///////////////////////////////////////////////////(F)Game End
	public boolean checkgameEnd() {
		if (gameEnd == true) {
			if (whoseTurn != -1 & alreadyWon == false) {
				System.out.println("Player " + whoseTurn + " has won on turn " + roundNumber + "!");
				alreadyWon = true;
				displayMatrix();
			} else if (whoseTurn == -1) {
				System.out.println("The game has run out of turns");
			}
		}
		return gameEnd;
	}

///////////////////////////////////////////////////(G)Increment Turn
	public void incrementTurn() {
		roundNumber++;
		if (roundNumber >= 17) {
			whoseTurn = -1;
			gameEnd = true;
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
