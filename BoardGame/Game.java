import java.util.Arrays;
import java.util.Random;

public class Game {

	int[] player1 = new int[20];
	int[] player2 = new int[20];
	int[] player3 = new int[20];
	int[] player4 = new int[20];
	
	boolean[][] supersetMatrix = new boolean[4][4];
	
	boolean gameEnd = false;
	int turnNumber = 0;
	int whoseTurn = 0;
	
///////////////////////////////////////////////////Initialize Game
	public void startGame()
	{
		Random rand = new Random();
		for(int i = 0; i < 3; i++)
		{
			//Intitializes 3 random integers
											//Any constraints needed?
			player1[i] = rand.nextInt(20)+1;
			player2[i] = rand.nextInt(20)+1;
			player3[i] = rand.nextInt(20)+1;
			player4[i] = rand.nextInt(20)+1;
		}
	}
///////////////////////////////////////////////////New Turn (player integer , wanted integer)
	public void newTurn(int player, int wanted)
	{
		if(player == 1)
		{
			player1[turnNumber+3] = wanted;
			whoseTurn = 1;
			isSuperset();
		}
		if(player == 2)
		{
			player2[turnNumber+3] = wanted;
			whoseTurn = 2;
			isSuperset();
		}
		if(player == 3)
		{
			player3[turnNumber+3] = wanted;
			whoseTurn = 3;
			isSuperset();
		}
		if(player == 4)
		{
			player4[turnNumber+3] = wanted;
			whoseTurn = 4;
			isSuperset();
			
			//Updates turn number after player 4 receives new integer
			incrementTurn();
			checkgameEnd();
		}
		
	}
///////////////////////////////////////////////////Get Turn	
	public int getTurn()
	{
		return turnNumber;
	}

///////////////////////////////////////////////////Increment Turn	
	public void incrementTurn()
	{
		if(turnNumber == 17)
			gameEnd = true;
		
		turnNumber++;
	}
///////////////////////////////////////////////////Whose Turn		
	public int whoseTurn()
	{
		return whoseTurn;
	}
///////////////////////////////////////////////////isSuperset	
	public void isSuperset()
	{
			if(Arrays.equals(player1, player2))
				supersetMatrix[0][1] = true;
			
			if(Arrays.equals(player1, player3))
				supersetMatrix[0][2] = true;
			
			if(Arrays.equals(player1, player4))
				supersetMatrix[0][3] = true;
			
			if(Arrays.equals(player2, player3))			
				supersetMatrix[1][2] = true;
			
			if(Arrays.equals(player2, player4))
				supersetMatrix[1][3] = true;
			
			if(Arrays.equals(player3, player4))
				supersetMatrix[2][3] = true;
	}
///////////////////////////////////////////////////Check Win
	public void checkWin()
	{
		
	}
///////////////////////////////////////////////////Check Game End	
	public void checkgameEnd()
	{
		if(gameEnd == true)
		{
		
		}
	}
	
	
	
	
	
	
	
	
	
	
	
///////////////////////////////////////////////////Generic Display without GUI
	public String displayHandP1()
	{
		String hand = "[ ";
		
		for(int i = 0; i < turnNumber+4; i++)
		{
			if(player1[i] != 0)
			hand+= player1[i] + " ";	
		}
		hand += " ]";
		return hand;
	}
	public String displayHandP2()
	{
		String hand = "[ ";
		
		for(int i = 0; i < turnNumber+4; i++)
		{
			if(player2[i] != 0)
			hand+= player2[i] + " ";	
		}
		hand += " ]";
		return hand;
	}
	public String displayHandP3()
	{
		String hand = "[ ";
		
		for(int i = 0; i < turnNumber+4; i++)
		{
			if(player3[i] != 0)
			hand+= player3[i] + " ";	
		}
		hand += " ]";
		return hand;
	}
	public String displayHandP4()
	{
		String hand = "[ ";
		
		for(int i = 0; i < turnNumber+4; i++)
		{
			if(player4[i] != 0)
			hand+= player4[i] + " ";	
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
			matrix += "]";
		}
		return matrix;
	}
	
}
