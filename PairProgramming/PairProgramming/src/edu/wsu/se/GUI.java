package edu.wsu.se;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class GUI extends JFrame implements ActionListener{

	JPanel panel1 = new JPanel();
	JPanel panel2 = new JPanel();
	
	// On-screen components for panel1
	JLabel title1 = new JLabel("Input");
	JTextArea displayInput = new JTextArea(10, 15);
	JScrollPane scroll = new JScrollPane(displayInput);
	
	JButton fileButton = new JButton("Choose File");
	JFileChooser fc;
	
	// On-screen text for panel2
	JLabel title2 = new JLabel("Overall Results");
	Font bigFont = new Font("Arial", Font.BOLD, 16);
	
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
		super("Reading Numbers");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		displayInput.setText("");
		
		displayInput.setEditable(false);
		panel1.setLayout((LayoutManager) new BoxLayout(panel1, BoxLayout.Y_AXIS));
		title1.setFont(bigFont);
		
		panel2.setLayout((LayoutManager) new BoxLayout(panel2, BoxLayout.Y_AXIS));
		title2.setFont(bigFont);
		
		// Adds all the components to the first panel
		panel1.add(title1);
		panel1.add(scroll);
		panel1.add(fileButton);
		fileButton.addActionListener(this);
		fc = new JFileChooser();
		
		// Adds all the components to the second panel
		panel2.add(title2);
		panel2.add(totalLines);
		panel2.add(largestValue);
		panel2.add(largeLineOccured);
		panel2.add(largeFrequency);
		panel2.add(smallestValue);
		panel2.add(smallLineOccured);
		panel2.add(smallFrequency);
		panel2.add(totalSum);
		panel2.add(finalStatus);
		
		add(panel1, BorderLayout.WEST);
		add(panel2, BorderLayout.EAST);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// If button clicked... (only one button at the moment)
        if (e.getSource() == fileButton) {
            int returnVal = fc.showOpenDialog(GUI.this);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                // This is where the file is put into displayInput
                displayInput.setText(Main.readInputFile(file.toString()));
                Main.process(file.toString());
                //displayInput.setText(Main.processor.lineDataOutput());
                //displayInput.setText(Main.processor.fileDataOutput());
            } else {
                // User chose cancel
            }
        }
	}
}
