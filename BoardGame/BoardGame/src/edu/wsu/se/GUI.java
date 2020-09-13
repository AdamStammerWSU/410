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
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class GUI extends JFrame implements ActionListener{

	// Left, center, and right panels
	JPanel panel1 = new JPanel();
	JPanel panel2 = new JPanel();
	JPanel panel3 = new JPanel();
	
	Font bigFont = new Font("Arial", Font.BOLD, 16);
	
	// On-screen components for panel1
	JLabel gameNumber = new JLabel("Game: #"); // # are currently place holders
	JLabel currentScores = new JLabel("Curent Scores");// getting the data would happen later
	JLabel p1Score = new JLabel("P1: #");
	JLabel p2Score = new JLabel("P2: #");
	JLabel p3Score = new JLabel("P3: #");
	JLabel p4Score = new JLabel("P4: #");
	JLabel howToTitle = new JLabel("How to Play: ");
	
	// On-screen components for panel2
	JLabel playerTurn = new JLabel("Player #'s Turn");
	// matrix with labeled sides
	// and dots
	
	// On-screen components for panel3
	JLabel playerNum = new JLabel("Player #");
	JLabel listOfNum = new JLabel("#, #, #");
	TitledBorder yourNumbers;
	JLabel pickNum = new JLabel("Pick a Number");
	JComboBox dropDown = new JComboBox();
	
	
	// maybe insert player# in the constructor ex: GUI(player1);
	public GUI() {
		super("\"Board\" Game"); // Frame title
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel1.setLayout((LayoutManager) new BoxLayout(panel1, BoxLayout.Y_AXIS));
		panel1.setBorder(BorderFactory.createEmptyBorder(2, 10, 10, 0));
		gameNumber.setFont(bigFont);
		currentScores.setFont(bigFont);
		
		panel2.setLayout((LayoutManager) new BoxLayout(panel2, BoxLayout.Y_AXIS));
		panel2.setBorder(BorderFactory.createEmptyBorder(2, 5, 10, 0));
		playerTurn.setFont(bigFont);
		
		panel3.setLayout((LayoutManager) new BoxLayout(panel3, BoxLayout.Y_AXIS));
		panel3.setBorder(BorderFactory.createEmptyBorder(2, 5, 10, 0));
		playerNum.setFont(bigFont);
		yourNumbers = BorderFactory.createTitledBorder("Your Numbers:");
		
		for (int i = 1; i <= 20; i++) {
			dropDown.addItem(i);
		}
		
		dropDown.addActionListener(this);
		
		// Values for each item should be gotten and set within the constructor
		// ex: append the player's number to playerNum
		
		/* What is all needed:
		 * Current game number
		 * scores for each player
		 * the number of the player whose turn it is (first turn)
		 * the players' starting numbers
		 * the matrix
		 * 
		 * Only needs to be set once: what player the player is (ex: Player 1)
		*/
		
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
		panel2.setPreferredSize(new Dimension(350, 500));
		
		// Adds all the components to the third panel
		panel3.add(playerNum);
		panel3.add(Box.createRigidArea(new Dimension(0,50)));
			// The border should probably go on something that's not a JLabel
		listOfNum.setBorder(yourNumbers); // I'll change it later
		panel3.add(listOfNum);
		panel3.add(Box.createRigidArea(new Dimension(0,50)));
		panel3.add(pickNum);
		panel3.add(dropDown); // I have no clue why this is so far away from the item above it.
		panel3.setPreferredSize(new Dimension(250, 500));
		
		// Panels' position on frame
		add(panel1, BorderLayout.WEST);
		add(panel2, BorderLayout.CENTER);
		add(panel3, BorderLayout.EAST);
		pack();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {		
		// Once a player chooses a number, listOfNum, the matrix, and playerTurn update
		JComboBox cb = (JComboBox)e.getSource();
        int newNum = (int) cb.getSelectedItem();
        // commence update here
		
	}
}