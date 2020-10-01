package edu.wsu.se;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class GUI extends JFrame implements ActionListener{

	Match match;
	// Left, center, and right panels
	JPanel panel1 = new JPanel();
	JPanel panel2 = new JPanel();
	JPanel panel3 = new JPanel();
	
	Font bigFont = new Font("Arial", Font.BOLD, 16);
	
	boolean isYourTurn = false; // combo box shouldn't be selectable when it's not your turn
	
	// On-screen components for panel1
	JLabel gameNumber = new JLabel("Game: ");
	JLabel currentScores = new JLabel("Curent Scores");// getting the data would happen later
	JLabel p1Score = new JLabel("P1: ");
	JLabel p2Score = new JLabel("P2: ");
	JLabel p3Score = new JLabel("P3: ");
	JLabel p4Score = new JLabel("P4: ");
	JLabel howToTitle = new JLabel("How to Play: ");
	
	// On-screen components for panel2
	JLabel playerTurn = new JLabel("Player #'s Turn");
	// matrix with labeled sides
	// and dots
	JTextArea matrix = new JTextArea(5, 5); // temp until I figure out canvas for matrix and dots
	
	// On-screen components for panel3
	JLabel thePlayer = new JLabel("Player #");
	JTextArea listOfNum = new JTextArea("#, #, #");
	//TitledBorder border;
	JLabel pickNum = new JLabel("Pick a Number");
	JComboBox dropDown = new JComboBox();
	
	
	// maybe insert player# in the constructor ex: GUI(player1);
	public GUI(Match m) {
		super("\"Board\" Game"); // Frame title
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		match = m;
		
		panel1.setLayout((LayoutManager) new BoxLayout(panel1, BoxLayout.Y_AXIS));
		panel1.setBorder(BorderFactory.createEmptyBorder(2, 10, 10, 0));
		gameNumber.setFont(bigFont);
		currentScores.setFont(bigFont);
		
		panel2.setLayout((LayoutManager) new BoxLayout(panel2, BoxLayout.Y_AXIS));
		panel2.setBorder(BorderFactory.createEmptyBorder(2, 5, 100, 0));
		playerTurn.setFont(bigFont);
		matrix.setEditable(false);
		
		panel3.setLayout((LayoutManager) new BoxLayout(panel3, BoxLayout.Y_AXIS));
		panel3.setBorder(BorderFactory.createEmptyBorder(2, 5, 10, 0));
		thePlayer.setFont(bigFont);
		//border = BorderFactory.createTitledBorder("Your Numbers:");
		listOfNum.setBackground(getBackground());
		
		for (int i = 1; i <= 20; i++) {
			dropDown.addItem(i);
		}
		
		dropDown.addActionListener(this);
		
		// Values for each item should be gotten and set within the constructor
		// ex: append the player's number to thePlayer
		
		/* What is all needed:
		 * Current game number
		 * scores for each player (0 for everyone to start)
		 * the number of the player whose turn it is (first turn)
		 * the players' starting numbers
		 * the matrix
		 * 
		 * Only needs to be set once: what player the player is (ex: Player 1)
		 * Again, this should mostly be done by getters and setters
		*/
		
		String tempNumber = "#";
		// only needs to be set once
		thePlayer.setText("Player: " + tempNumber);
		
		// set at the beginning of every game
		gameNumber.setText("Game: " + tempNumber);
		p1Score.setText("P1: " + tempNumber);
		p2Score.setText("P2: " + tempNumber);
		p3Score.setText("P3: " + tempNumber);
		p4Score.setText("P4: " + tempNumber);
		
		// set after every turn 
		//this could be initial setup with the listener then doing change every turn
		//playerTurn.setText("Player "+ match.game.getWhoseTurn() +"'s Turn");

		//matrix.setText(match.game.displayMatrix());
		
		// initially
		listOfNum.setText(tempNumber + ", " + tempNumber + ", " + tempNumber);
		
		
		//set isYourTurn either false or true
		
		// Adds all the components to the first panel
		panel1.add(gameNumber);
		panel1.add(Box.createRigidArea(new Dimension(0,50)));
		panel1.add(currentScores);
		panel1.add(p1Score);
		panel1.add(p2Score);
		panel1.add(p3Score);
		panel1.add(p4Score);
		panel1.add(Box.createVerticalGlue());
		panel1.add(howToTitle);
		panel1.setPreferredSize(new Dimension(200, 500));
		
		// Adds all the components to the second panel
		panel2.add(playerTurn);
		panel2.add(Box.createRigidArea(new Dimension(0,50)));
		panel2.add(matrix);
		panel2.setPreferredSize(new Dimension(350, 500));
		
		// Adds all the components to the third panel
		panel3.add(thePlayer);
		panel3.add(Box.createRigidArea(new Dimension(0,50)));
			// The border should probably go on something that's not a JLabel
		//listOfNum.setBorder(border); // I'll change it later
		panel3.add(listOfNum);
		panel3.add(Box.createRigidArea(new Dimension(0,100)));
		panel3.add(pickNum);
		panel3.add(dropDown); // I have no clue why this is so far away from the item above it.
		panel3.setPreferredSize(new Dimension(250, 500));
		
		// Panels' position on frame
		add(panel1, BorderLayout.WEST);
		add(panel2, BorderLayout.CENTER);
		add(panel3, BorderLayout.EAST);
		pack();
	}
	
	public void updateDisplay() {
		System.out.println("updating gui 1");
		
		playerTurn.setText("Player "+ match.game.getWhoseTurn() +"'s Turn");
		System.out.println("updating gui 2");
		matrix.setText(match.game.displayMatrix());
		System.out.println("updating gui 3a");
		int index = match.netHandler.getMyNumber()-1;
		System.out.println("updating gui 3b: " + index);
		String s = match.players[index].displayHand();
		System.out.println("updating gui 3c: " + s);
		try {
			listOfNum.setText(s);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//matrix.setText(s);
		
		System.out.println("updating gui 4");
		
		if(match.game.getWhoseTurn() != match.netHandler.getMyNumber()) {
			dropDown.setEnabled(false);
			System.out.println("updating gui 5");

		} else {
			dropDown.setEnabled(true);
			System.out.println("updating gui 6");
		}
		System.out.println("gui updated");
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {	
		System.out.println("action happened");
		if (e.getSource() == dropDown) {
			// Once a player chooses a number, listOfNum, the matrix, and playerTurn update
			int newNum = (int) dropDown.getSelectedItem();
			
			// popup if that number is already in their list and handling here
			
	        // commence update here
			System.out.println("You choose " + newNum);
			System.out.println("Time to update!");
			
			// set after thePlayer takes a turn
			//match.game.incrementTurn(); 
			// SOMETHING THAT CHANGES THE TURN HERE 
			playerTurn.setText("Player "+ match.game.getWhoseTurn() +"'s Turn");
			// TAKE THE ADDRESS OF THE PLAYER SOMEHOW AND UPDATE PLAYER'S HAND
			// RECALCULATE THE MATRIX
			matrix.setText(match.game.displayMatrix());
			
			//listOfNum.setText(listOfNum.getText() + ", " + newNum);
			// change turn and refresh matrix and player #'s turn
			//isYourTurn = false;
			
			if(match.netHandler.isServer()) {
				match.netHandler.broadcast("" + newNum);
			} else {
				match.netHandler.sendToServer("" + newNum);
			}
			match.game.newTurn(match.netHandler.getMyNumber(), newNum);
			//updateDisplay();
		}
		System.out.println("action processed");
		
		// other buttons/user input stuff goes here
		// an ok clicked on a popup after someone wins could refresh the gui and go to the next
		// game or say that all 10 games have been completed and the overall winner
		// etc.
	
	}

	public void PROMPT_MESSAGE(String s) {
		JOptionPane.showMessageDialog(this, s, "Message",
				JOptionPane.ERROR_MESSAGE);
	}

	public boolean PROMPT_FOR_SERVER() {
		int r = JOptionPane.showConfirmDialog(this, "Are you hosting the game (SERVER)?");
		if (r == JOptionPane.YES_OPTION)
			return true;
		return false;
	}
	
	public String PROMPT_FOR_IP() {
		String s = JOptionPane.showInputDialog(this, "IP to connect to:");
		if (s == null) {
			return "localhost";
		}
		return s;
	}
	
	public int PROMPT_FOR_PORT() {
		String s = JOptionPane.showInputDialog(this, "Port to use:");
		if(s == null) {
			return 25565;
		}
		return Integer.parseInt(s);
	}
}