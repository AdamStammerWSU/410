package edu.wsu.se;

import java.awt.List;
import java.util.LinkedList;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;
public class Game {
/*
 * Contains
 * (A)Game Constructor
 * (B)Matrix Display
 * (C)Update Matrix
 * (D)New Turn
 * (E)Check If Wins
 * (F)Game End
 * (G)Increment Turn
 * (H)Misc. Getters and Setters
 */
	Set<Integer> player1 = new TreeSet<Integer>();
	Set<Integer> player2 = new TreeSet<Integer>();
	Set<Integer> player3 = new TreeSet<Integer>();
	Set<Integer> player4 = new TreeSet<Integer>();
	boolean gameEnd = false;
	int turnNumber = 1;
	int whoseTurn = 1;
	int whomWon = -1;
	boolean alreadyWon = false;
	boolean[][] playerMatrix = new boolean[4][4];
////////////////////////////////////////////////////////////////(A)Game Constructor
	public Game(Set<Integer> hand, Set<Integer> hand2, Set<Integer> hand3, Set<Integer> hand4) {
		player1 = hand;
		player2 = hand2;
		player3 = hand3;
		player4 = hand4;
		playerMatrix[0][0] = true;
		playerMatrix[1][1] = true;
		playerMatrix[2][2] = true;
		playerMatrix[3][3] = true;
	}
///////////////////////////////////////////////////(B)Matrix Display
	public String displayMatrix(){
		String matrix = "P1: [";
		for(int i = 0; i < 4; i++){
			matrix += playerMatrix[0][i] + " ";	
		}
		matrix += "]\n";
		matrix += "P2: [";
		for(int i = 0; i < 4; i++){
			matrix += playerMatrix[1][i] + " ";	
		}
		matrix += "]\n";
		matrix += "P3: [";
		for(int i = 0; i < 4; i++){
			matrix += playerMatrix[2][i] + " ";	
		}
		matrix += "]\n";
		matrix += "P4: [";
		for(int i = 0; i < 4; i++){
			matrix += playerMatrix[3][i] + " ";	
		}
		matrix += "]\n";
		return matrix;
	}
///////////////////////////////////////////////////(C)Update Matrix
	public void updateMatrix(Set<Integer> p){
		switch (whoseTurn) {
        case 1:  whoseTurn = 1;
	        if(p.containsAll(player2)){
	    		playerMatrix[0][1] = true;
	    	}else{
	        	playerMatrix[0][1] = false;
	        }
	    	if(p.containsAll(player3)){	
	    		playerMatrix[0][2] = true;
	    	}else{
	    		playerMatrix[0][2] = false;
	        }
	    	if(p.containsAll(player4)){
	    		playerMatrix[0][3] = true;
	    	}else{
	    		playerMatrix[0][3] = false;
	        }
                 break;
        case 2:  whoseTurn = 2;
	        if(p.containsAll(player1)){	
	    		playerMatrix[1][0] = true;
	    	}else{
	    		playerMatrix[1][0] = false;
	        }
	    	if(p.containsAll(player3)){
	    		playerMatrix[1][2] = true;
	    	}else{	
	    		playerMatrix[1][2] = false;
	        }
	    	if(p.containsAll(player4)){
	    		playerMatrix[1][3] = true;
	    	}else{
	    		playerMatrix[1][3] = false;
	        }
	                 break;
        case 3:  whoseTurn = 3;
	        if(p.containsAll(player1)){
	    		playerMatrix[2][0] = true;
	    	}else{
	    		playerMatrix[2][0] = false;
	        }
	    	if(p.containsAll(player2)){
	    		playerMatrix[2][1] = true;
	    	}else{	
	    		playerMatrix[2][1] = false;
	        }
	    	if(p.containsAll(player4)){
	    		playerMatrix[2][3] = true;
	    	}else{	
	    		playerMatrix[2][3] = false;
	        }
                 break;
        case 4:  whoseTurn = 4;
	        if(p.containsAll(player1)){
	    		playerMatrix[3][0] = true;
	    	}else{
	    		playerMatrix[3][0] = false;
	        }
	    	if(p.containsAll(player2)){
	    		playerMatrix[3][1] = true;
	    	}else{
	    		playerMatrix[3][1] = false;
	        }
	    	if(p.containsAll(player3)){
	    		playerMatrix[3][2] = true;
	    	}else{
	    		playerMatrix[3][2] = false;
	        }
                 break;
        default: whoseTurn = -1;
                 break;
		}	
	}
////////////////////////////////////////////////////////////////(D)New Turn
public void newTurn(int player, int wanted,Set<Integer> p){
	switch (whoseTurn) {
		case 1:  whoseTurn = 1;
			player1.add(wanted);
			updateMatrix(p);
			checkWin();
			checkgameEnd();
			whoseTurn = 2;
			break;
		case 2:  whoseTurn = 2;	
			player2.add(wanted);
			updateMatrix(p);
			checkWin();
			checkgameEnd();
			whoseTurn = 3;
			break;
		case 3:  whoseTurn = 3;	
			player3.add(wanted);
			updateMatrix(p);
			checkWin();
			checkgameEnd();
			whoseTurn = 4;
			break;
		case 4:  whoseTurn = 4;	
			player4.add(wanted);
			updateMatrix(p);
			checkWin();
			checkgameEnd();
			incrementTurn();
			whoseTurn = 1;
			break;
		}
	}
///////////////////////////////////////////////////(E)Check If Wins
	public void checkWin(){
		if(playerMatrix[0][1] == true & playerMatrix[0][2] == true & playerMatrix[0][3] == true){
			whomWon = 1;
			gameEnd = true;
		}
		if(playerMatrix[1][0] == true & playerMatrix[1][2] == true & playerMatrix[1][3] == true){
			whomWon = 2;
			gameEnd = true;
		}
		if(playerMatrix[2][0] == true & playerMatrix[2][1] == true & playerMatrix[2][3] == true){
			whomWon = 3;
			gameEnd = true;
		}
		if(playerMatrix[3][0] == true & playerMatrix[3][1] == true & playerMatrix[3][2] == true){
			whomWon = 4;
			gameEnd = true;
		}
	}
///////////////////////////////////////////////////(F)Game End
	public boolean checkgameEnd()
	{
		if(gameEnd == true){
			if(whoseTurn != -1 & alreadyWon == false) {
				System.out.println("Player " + whoseTurn + " has won on turn " +turnNumber+"!");
				alreadyWon = true;
				displayMatrix();
			}
			else if(whoseTurn == -1) {
				System.out.println("The game has run out of turns");
			}
		}
		return gameEnd;
	}
///////////////////////////////////////////////////(G)Increment Turn
	public void incrementTurn()
	{
		turnNumber++;
		if(turnNumber >= 17) {
			whoseTurn = -1;
			gameEnd = true;
		}
	}
/////////////////////////////////////////////////////(H)Getters and Setters
/////////////////////////////////////////////////////Turn Number
	public int getTurnNumber(){
		return turnNumber;
	}
	public void setTurnNumber(int turnNumber) {
		this.turnNumber = turnNumber;
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
