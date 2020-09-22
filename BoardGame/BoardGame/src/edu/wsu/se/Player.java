package edu.wsu.se;

public class Player {

	int playerNumber;
	int wins = 0;
	double IPaddress;
////////////////////////////////Constructor
	public void Player(int playerNum, int IP)
	{
		playerNumber = playerNum;
		IPaddress = IP;
	}

////////////////////////////////Getters and Setters
	public int getPlayerNumber() {
		return playerNumber;
	}

	public void setPlayerNumber(int playerNumber) {
		this.playerNumber = playerNumber;
	}

	public int getWins() {
		return wins;
	}

	public void setWins(int wins) {
		this.wins = wins;
	}

	public double getIPaddress() {
		return IPaddress;
	}

	public void setIPaddress(double iPaddress) {
		IPaddress = iPaddress;
	}

	
}
