package edu.se.par;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class GUI extends JFrame implements ActionListener {
	
	// Top, left, right, and bottom panels
	JPanel panelTop = new JPanel();
	JPanel panelLeft = new JPanel();
	JPanel panelRight = new JPanel();
	JPanel panelBottom = new JPanel();
	
	Font titleFont = new Font("SansSerif", Font.BOLD, 30);
		
	// On-screen component for panelTop
	JLabel title = new JLabel("Parvata Book Imposer");
	
	// On-screen components for panelLeft
	JLabel enterLayout = new JLabel("Enter Page Layout");
	JTextArea layout = new JTextArea("EXAMPLE LAYOUT HERE", 20, 15);
	
	// On-screen components for panelRight
	JLabel tba = new JLabel("panel TBA");
	JButton impose = new JButton("Impose");
	
	// On-screen components for panelBottom
	JButton openFile = new JButton("Open File");
	JFileChooser fc;
	JTextArea filePath = new JTextArea("File path", 1, 10);
	JButton saveFile = new JButton("Save File to...");
	JTextArea newFilePath = new JTextArea("Imposed File path", 1, 10);
	
	public static void main(String[] args) {
		System.out.println("Howdy");
		GUI gui = new GUI();
	}
	
	
	public GUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500, 500);
		setTitle("Parvata Book Imposer");
		
		title.setFont(titleFont);
		
		panelLeft.setLayout((LayoutManager) new BoxLayout(panelLeft, BoxLayout.Y_AXIS));
		panelRight.setLayout((LayoutManager) new BoxLayout(panelRight, BoxLayout.Y_AXIS));
		
		
		// Adds components to the top panel
		panelTop.add(title);
		
		// Adds components to the left panel
		panelLeft.add(enterLayout);
		panelLeft.add(layout);
		
		// Adds components to the right panel
		panelRight.add(tba);
		panelRight.add(impose);
		
		// Adds components to the bottom panel
		panelBottom.add(openFile);
		panelBottom.add(filePath);
		panelBottom.add(saveFile);
		panelBottom.add(newFilePath);
		
		
		//openFile.addActionListener(this);
		//fc = new JFileChooser();
		//saveFile.addActionListener(this);
		
		
		
		
		
		// Panels' position on frame
		add(panelTop, BorderLayout.NORTH);
		add(panelLeft, BorderLayout.WEST);
		add(panelRight, BorderLayout.EAST);
		add(panelBottom, BorderLayout.SOUTH);
		
		//pack();
		setVisible(true);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// Open File is clicked
		if (e.getSource() == openFile) {
			int returnVal = fc.showOpenDialog(GUI.this);

			if (returnVal == JFileChooser.APPROVE_OPTION) {
				// Do stuff
			}
		}
		
		if (e.getSource() == saveFile) {
			// Do stuff
		}
	}
}