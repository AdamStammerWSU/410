package edu.wsu.se;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.*;

public class GUI extends JFrame{

	JPanel panel = new JPanel();
	
	// On-screen text for Results
	JLabel totalLines = new JLabel
			("Number of Lines: ");
	JLabel largestValue = new JLabel
			("Largest Value: ");
	JLabel largeLineOccured = new JLabel
			("Largest Value Occured on Line(s) ");
	JLabel largeFrequency = new JLabel
			("Did the Largest Value occur more than once? "); // yes/no
	JLabel smallestValue = new JLabel
			("Smallest Value: ");
	JLabel smallLineOccured = new JLabel
			("Smallest Value Occured on Line(s) ");
	JLabel smallFrequency = new JLabel
			("Did the Smallest Value occur more than once? "); // yes/no
	JLabel totalSum = new JLabel
			("Sum of all values: ");
	JLabel finalStatus = new JLabel
			("Result: "); // LOSS, PROFIT, BREAKEVEN
		
	public GUI () {
		super("Results");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel.setLayout((LayoutManager) new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		// Adds some nice whitespace
		panel.add(Box.createRigidArea(new Dimension(20, 10)));
		
		// Adds all the labels to the GUI
		panel.add(totalLines);
		panel.add(largestValue);
		panel.add(largeLineOccured);
		panel.add(largeFrequency);
		panel.add(smallestValue);
		panel.add(smallLineOccured);
		panel.add(smallFrequency);
		panel.add(totalSum);
		panel.add(finalStatus);
		
		add(panel);
	}
}
