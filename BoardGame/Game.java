import java.awt.List;
import java.util.LinkedList;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

public class Game {

	Set<Integer> player1 = new TreeSet<Integer>();
	Set<Integer> player2 = new TreeSet<Integer>();
	Set<Integer> player3 = new TreeSet<Integer>();
	Set<Integer> player4 = new TreeSet<Integer>();

	boolean[] p1Matrix = new boolean[4];
	boolean[] p2Matrix = new boolean[4];
	boolean[] p3Matrix = new boolean[4];
	boolean[] p4Matrix = new boolean[4];
	
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
			
			p1Matrix[0] = true;
			p2Matrix[1] = true;
			p3Matrix[2] = true;
			p4Matrix[3] = true;
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
			else if(whoseTurn == 2)
			{
				player2.add(wanted);
				updateMatrix();
				checkWin();
				checkgameEnd();
				whoseTurn = 3;
			}
				else if(whoseTurn == 3)
				{
					player3.add(wanted);
					updateMatrix();
					checkWin();
					checkgameEnd();
					whoseTurn = 4;
				}
				else if(whoseTurn == 4)
				{
					player4.add(wanted);
					updateMatrix();
					checkWin();
					checkgameEnd();
					incrementTurn();
					whoseTurn = 1;
				}
		
	}
	public void updateMatrix()
	{
		
		switch (whoseTurn) {
        case 1:  whoseTurn = 1;
	        if(player1.containsAll(player2)){
	    		p1Matrix[1] = true;
	    	}
	        else{
	        	p1Matrix[1] = false;
	        }
	    	if(player1.containsAll(player3)){
	    		p1Matrix[2] = true;	    		
	    	}
	    	else{
	    		p1Matrix[2] = false;	    		
	        }
	    	if(player1.containsAll(player4)){
	    		p1Matrix[3] = true;	    		
	    	}
	    	else{
	    		p1Matrix[3] = false;	
	        }
                 break;
        case 2:  whoseTurn = 2;
	        if(player2.containsAll(player1)){
	    		p2Matrix[0] = true;
	    	}
	        else
	        {
	    		p2Matrix[0] = false;
	        }
	    	if(player2.containsAll(player3)){
	    		p2Matrix[2] = true;
	    	}
	    	else
	        {
	    		p2Matrix[2] = false;
	        }
	    	if(player2.containsAll(player4)){
	    		p2Matrix[3] = true;
	    	}
	    	else
	        {
	    		p2Matrix[3] = false;
	        }
	                 break;
        case 3:  whoseTurn = 3;
	        if(player3.containsAll(player1)){
	    		p3Matrix[0] = true;
	    	}else
	        {
	    		p3Matrix[0] = false;
	        }
	    	if(player3.containsAll(player2)){
	    		p3Matrix[1] = true;
	    	}else
	        {
	    		p3Matrix[1] = false;
	        }
	    	if(player3.containsAll(player4)){
	    		p3Matrix[3] = true;
	    	}else
	        {
	    		p3Matrix[3] = false;
	        }
                 break;
        case 4:  whoseTurn = 4;
	        if(player4.containsAll(player1)){
	    		p4Matrix[0] = true;
	    	}else
	        {
	    		p4Matrix[0] = false;
	        }
	    	if(player4.containsAll(player2)){
	    		p4Matrix[1] = true;
	    	}else
	        {
	    		p4Matrix[1] = false;
	        }
	    	if(player4.containsAll(player3)){
	    		p4Matrix[2] = true;
	    	}else
	        {
	    		p4Matrix[2] = false;
	        }
                 break;
        default: whoseTurn = -1;
                 break;
    }
		
	}
	public void checkWin()
	{
		if(p1Matrix[1] == true & p1Matrix[2] == true & p1Matrix[3] == true)
		{
			whomWon = 1;
			gameEnd = true;
		}
		if(p2Matrix[0] == true & p2Matrix[2] == true & p2Matrix[3] == true)
		{
			whomWon = 2;
			gameEnd = true;
		}
		if(p3Matrix[0] == true & p3Matrix[1] == true & p3Matrix[3] == true)
		{
			whomWon = 3;
			gameEnd = true;
		}
		if(p4Matrix[0] == true & p4Matrix[1] == true & p4Matrix[2] == true)
		{
			whomWon = 4;
			gameEnd = true;
		}
		
	}
	public void checkgameEnd()
	{
		if(gameEnd == true)
		{
				if(whoseTurn != -1)
					System.out.println("Player " + whoseTurn + " has won on turn" +turnNumber);
				if(whoseTurn == -1)
					System.out.println("The game has run out of turns");
		}
	}
	public void incrementTurn()
	{
		turnNumber++;
		if(turnNumber >= 17) {
			whoseTurn = -1;
			gameEnd = true;
		}
		
	}	
///////////////////////////////////////////////////Turn Number and Whose Turn Display
	public int getTurnNum()
	{
		return turnNumber;
	}
	public int whoseTurn()
	{
		return whoseTurn;
	}
///////////////////////////////////////////////////Hand and Matrix Display
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
		String matrix = "P1: [";
		for(int i = 0; i < 4; i++)
		{
			matrix += p1Matrix[i] + " ";	
		}
		matrix += "]\n";
		matrix += "P2: [";
		for(int i = 0; i < 4; i++)
		{
			matrix += p2Matrix[i] + " ";	
		}
		matrix += "]\n";
		matrix += "P3: [";
		for(int i = 0; i < 4; i++)
		{
			matrix += p3Matrix[i] + " ";	
		}
		matrix += "]\n";
		matrix += "P4: [";
		for(int i = 0; i < 4; i++)
		{
			matrix += p4Matrix[i] + " ";	
		}
		matrix += "]\n";
		
		return matrix;
	}

///////////////////////////////////////////////////Generic Game Start for Testing
	public void startGame2()
	{
			p1Matrix[0] = true;
			p2Matrix[1] = true;
			p3Matrix[2] = true;
			p4Matrix[3] = true;
		
			player1.add(1);
			player2.add(1);
			player3.add(1);
			player4.add(1);
		
			player1.add(2);
			player2.add(2);
			player3.add(2);
			player4.add(2);
			
			player1.add(3);
			player2.add(4);
			player3.add(5);
			player4.add(6);
	}


	
}
