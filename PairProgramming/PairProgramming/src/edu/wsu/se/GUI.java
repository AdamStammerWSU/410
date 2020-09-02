package edu.wsu.se;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Arrays;

import javax.swing.BorderFactory;
import javax.swing.Box;
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
	JPanel panel3 = new JPanel();
	
	// On-screen components for panel1
	JLabel title1 = new JLabel("Input");
	JTextArea displayInput = new JTextArea(10, 15);
	JScrollPane scroll1 = new JScrollPane(displayInput);
	
	JButton fileButton = new JButton("Choose File");
	JFileChooser fc;
	
	// On-screen components for panel2
	JLabel title2 = new JLabel("Line by Line");
	JTextArea lineData = new JTextArea(10, 15);
	JScrollPane scroll2 = new JScrollPane(lineData);
	
	
	// On-screen components for panel3
	JLabel title3 = new JLabel("Overall Results");
	Font bigFont = new Font("Arial", Font.BOLD, 16);
	
	JLabel totalLines = new JLabel();
	JLabel largestValue = new JLabel();
	JLabel largeLineOccured = new JLabel();
	JLabel largeFrequency = new JLabel();
	JLabel smallestValue = new JLabel();
	JLabel smallLineOccured = new JLabel();
	JLabel smallFrequency = new JLabel();
	JLabel totalSum = new JLabel();
	JLabel finalStatus = new JLabel(); // LOSS, PROFIT, BREAKEVEN
	
		
	public GUI () {
		super("Reading Numbers");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		displayInput.setEditable(false);
		panel1.setLayout((LayoutManager) new BoxLayout(panel1, BoxLayout.Y_AXIS));
		panel1.setBorder(BorderFactory.createEmptyBorder(2, 10, 2, 5));
		title1.setFont(bigFont);
		
		lineData.setEditable(false);
		panel2.setLayout((LayoutManager) new BoxLayout(panel2, BoxLayout.Y_AXIS));
		panel2.setBorder(BorderFactory.createEmptyBorder(2, 5, 2, 10));
		title2.setFont(bigFont);
		
		panel3.setLayout((LayoutManager) new BoxLayout(panel3, BoxLayout.Y_AXIS));
		panel3.setBorder(BorderFactory.createEmptyBorder(2, 5, 2, 20));
		title3.setFont(bigFont);
		
		// Adds all the components to the first panel
		panel1.add(title1);
		panel1.add(scroll1);
		panel1.add(fileButton);
		fileButton.addActionListener(this);
		fc = new JFileChooser();
		
		// Adds all the components to the second panel
		panel2.add(title2);
		panel2.add(scroll2);
		panel2.add(Box.createRigidArea(new Dimension(0, 29)));	

		// Adds all the components to the third panel
		panel3.add(title3);
		panel3.add(totalLines);
		panel3.add(largestValue);
		panel3.add(largeLineOccured);
		panel3.add(largeFrequency);
		panel3.add(smallestValue);
		panel3.add(smallLineOccured);
		panel3.add(smallFrequency);
		panel3.add(totalSum);
		panel3.add(finalStatus);
		
		add(panel1, BorderLayout.WEST);
		add(panel2, BorderLayout.CENTER);
		add(panel3, BorderLayout.EAST);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// If button clicked...
        if (e.getSource() == fileButton) {
            int returnVal = fc.showOpenDialog(GUI.this);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                // This is where the file is put into displayInput and lineData text areas
                displayInput.setText(Main.readInputFile(file.toString()));
                Main.process(file.toString());
                lineData.setText(Main.processor.lineDataOutput());
                
                // Set text for the third panel
                totalLines.setText("Number of Lines: " + Main.processor.numberOfLines());
                largestValue.setText("Largest Value: " + Main.processor.largestValue());
                largeLineOccured.setText("Largest Value Occured on Line(s): " + Arrays.toString(Main.processor.largestValueLines()));
                largeFrequency.setText("Largest Value Repeated: ");
                smallestValue.setText("Smallest Value: " + Main.processor.smallestValue());
                smallLineOccured.setText("Smallest Value Occured on Line(s): " + Arrays.toString(Main.processor.smallestValueLines()));
                smallFrequency.setText("Smallest Value Repeated: ");
                totalSum.setText("Sum of all values: " + Main.processor.sum());
                finalStatus.setText("Result: " + Main.processor.sumString);
                //displayInput.setText(Main.processor.fileDataOutput());
            } else {
                // User chose cancel
            }
        }
	}
}
