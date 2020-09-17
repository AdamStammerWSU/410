import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

public class Game {

	Set<Integer> player1 = new TreeSet<Integer>();
	Set<Integer> player2 = new TreeSet<Integer>();
	Set<Integer> player3 = new TreeSet<Integer>();
	Set<Integer> player4 = new TreeSet<Integer>();
	
	boolean[][] supersetMatrix = new boolean[4][4];
	
	boolean gameEnd = false;
	int turnNumber = 0;
	int whoseTurn = 1;
	int whomWon = -1;
	
	public void startGame()
	{
		Random rand = new Random();
		for(int i = 0; i < 3; i++)
		{
			//Intitializes 3 random integers
											//Any constraints needed?
			player1.add(rand.nextInt(20)+1);
			player2.add(rand.nextInt(20)+1);
			player3.add(rand.nextInt(20)+1);
			player4.add(rand.nextInt(20)+1);
		}
	}
	public void newTurn(int player, int wanted)
	{
		if(whoseTurn == 1)
		{
			player1.add(wanted);
			updateMatrix();
			checkWin();
			checkgameEnd();
			whoseTurn = 2;
		}
		if(whoseTurn == 2)
		{
			player2.add(wanted);
			updateMatrix();
			checkWin();
			checkgameEnd();
			whoseTurn = 3;
		}
		if(whoseTurn == 3)
		{
			player3.add(wanted);
			updateMatrix();
			checkWin();
			checkgameEnd();
			whoseTurn = 4;
		}
		if(whoseTurn == 4)
		{
			player4.add(wanted);
			updateMatrix();
			checkWin();
			checkgameEnd();
			whoseTurn = 4;
			incrementTurn();
			
		}
		
	}
	public void updateMatrix()
	{
		switch (whoseTurn) {
        case 1:  whoseTurn = 1;
	        if(player1.containsAll(player2)){
	    		supersetMatrix[0][1] = true;
	    		supersetMatrix[1][0] = true;
	    	}
	        else{
	        	supersetMatrix[0][1] = false;
	    		supersetMatrix[1][0] = false;
	        }
	    	if(player1.containsAll(player3)){
	    		supersetMatrix[0][2] = true;
	    		supersetMatrix[2][0] = true;
	    	}
	    	else{
	    		supersetMatrix[0][2] = false;
	    		supersetMatrix[2][0] = false;
	        }
	    	if(player1.containsAll(player4)){
	    		supersetMatrix[0][3] = true;
	    		supersetMatrix[3][0] = true;
	    	}
	    	else{
	    		supersetMatrix[0][3] = false;
	    		supersetMatrix[3][0] = false;
	        }
                 break;
        case 2:  whoseTurn = 2;
	        if(player2.containsAll(player1)){
	    		supersetMatrix[1][0] = true;
	    		supersetMatrix[0][1] = true;
	    	}else
	        {
	    		supersetMatrix[1][0] = false;
	    		supersetMatrix[0][1] = false;
	        }
	    	if(player2.containsAll(player3)){
	    		supersetMatrix[1][2] = true;
	    		supersetMatrix[2][1] = true;
	    	}else
	        {
	    		supersetMatrix[1][2] = true;
	    		supersetMatrix[2][1] = true;
	        }
	    	if(player2.containsAll(player4)){
	    		supersetMatrix[1][3] = true;
	    		supersetMatrix[3][1] = true;
	    	}else
	        {
	    		supersetMatrix[1][3] = true;
	    		supersetMatrix[3][1] = true;
	        }
	                 break;
        case 3:  whoseTurn = 3;
	        if(player3.containsAll(player1)){
	    		supersetMatrix[2][0] = true;
	    		supersetMatrix[0][2] = true;
	    	}else
	        {
	    		supersetMatrix[2][0] = true;
	    		supersetMatrix[0][2] = true;
	        }
	    	if(player3.containsAll(player2)){
	    		supersetMatrix[2][1] = true;
	    		supersetMatrix[1][2] = true;
	    	}else
	        {
	    		supersetMatrix[2][1] = true;
	    		supersetMatrix[1][2] = true;
	        }
	    	if(player3.containsAll(player4)){
	    		supersetMatrix[2][3] = true;
	    		supersetMatrix[3][2] = true;
	    	}else
	        {
	    		supersetMatrix[2][3] = true;
	    		supersetMatrix[3][2] = true;
	        }
                 break;
        case 4:  whoseTurn = 4;
	        if(player4.containsAll(player1)){
	    		supersetMatrix[3][0] = true;
	    		supersetMatrix[0][3] = true;
	    	}else
	        {
	    		supersetMatrix[3][0] = true;
	    		supersetMatrix[0][3] = true;
	        }
	    	if(player4.containsAll(player2)){
	    		supersetMatrix[3][1] = true;
	    		supersetMatrix[1][3] = true;
	    	}else
	        {
	    		supersetMatrix[3][1] = true;
	    		supersetMatrix[1][3] = true;
	        }
	    	if(player4.containsAll(player3)){
	    		supersetMatrix[3][2] = true;
	    		supersetMatrix[2][3] = true;
	    	}else
	        {
	    		supersetMatrix[3][2] = true;
	    		supersetMatrix[2][3] = true;
	        }
                 break;
        default: whoseTurn = 0;
                 break;
    }
		
	}
	public void checkWin()
	{
		if(supersetMatrix[0][1] == true & supersetMatrix[0][2] == true & supersetMatrix[0][3] == true)
		{
			whomWon = 1;
		}
		if(supersetMatrix[1][0] == true & supersetMatrix[1][2] == true & supersetMatrix[1][3] == true)
		{
			whomWon = 2;
		}
		if(supersetMatrix[2][0] == true & supersetMatrix[2][1] == true & supersetMatrix[2][3] == true)
		{
			whomWon = 3;
		}
		if(supersetMatrix[3][0] == true & supersetMatrix[3][1] == true & supersetMatrix[3][2] == true)
		{
			whomWon = 4;
		}
		if(whomWon != -1)
			gameEnd = true;
	}
	public void checkgameEnd()
	{
		if(gameEnd == true)
		{
			if(whoseTurn == -1)
				System.out.println("The game has run out of turns");
			else
				System.out.println("Player "+whoseTurn+" won on turn "+turnNumber);
		}
	}
	public int getTurnNum()
	{
		return turnNumber;
	}
	public void incrementTurn()
	{
		if(turnNumber >= 17) {
			whoseTurn = -1;
			gameEnd = true;
		}
		turnNumber++;
	}	
	public int whoseTurn()
	{
		return whoseTurn;
	}
///////////////////////////////////////////////////Generic Display without GUI
public String displayHandP1()
	{
		String hand = "[ ";
		
		for (int i : player1) 
		{
		    hand += (" "+ i);
		}
		hand += " ]";
		return hand;
	}
public String displayHandP2()
{
	String hand = "[ ";
	
	for (int i : player2) 
	{
	    hand += (" "+ i);
	}
	hand += " ]";
	return hand;
}
public String displayHandP3()
{
	String hand = "[ ";
	
	for (int i : player3) 
	{
	    hand += (" "+ i);
	}
	hand += " ]";
	return hand;
}
public String displayHandP4()
{
	String hand = "[ ";
	
	for (int i : player4) 
	{
	    hand += (" "+ i);
	}
	hand += " ]";
	return hand;
}

	public String displayMatrix()
	{
	String matrix = "";
	for(int i = 0; i < 4; i++)
	{
	matrix += "[ ";
	for(int j = 3; j >= 0; j--)
	{
	matrix += supersetMatrix[i][j] + " ";	
	}
	matrix += "]\n";
	}
	return matrix;
	}

	
	
}
