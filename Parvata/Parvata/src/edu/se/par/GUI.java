package edu.se.par;

import java.awt.BorderLayout;
import java.awt.Dimension;
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
	
	public GUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setTitle("Parvata Book Imposer");
		
		title.setFont(titleFont);
		
		panelLeft.setLayout((LayoutManager) new BoxLayout(panelLeft, BoxLayout.Y_AXIS));
		panelRight.setLayout((LayoutManager) new BoxLayout(panelRight, BoxLayout.Y_AXIS));
		
		
		// Adds components to the top panel
		panelTop.add(title);
		panelTop.setPreferredSize(new Dimension(500, 100)); // I don't know why none of these are working or what makes them different from the last two projects
		
		// Adds components to the left panel
		panelLeft.add(enterLayout);
		panelLeft.add(layout);
		panelLeft.setPreferredSize(new Dimension(300, 300));
		
		// Adds components to the right panel
		panelRight.add(tba);
		panelRight.add(impose);
		panelLeft.setPreferredSize(new Dimension(200, 300));
		
		// Adds components to the bottom panel
		panelBottom.add(openFile);
		panelBottom.add(filePath);
		panelBottom.add(saveFile);
		panelBottom.add(newFilePath);
		panelBottom.setPreferredSize(new Dimension(500, 100));
		
		
		//openFile.addActionListener(this);
		//fc = new JFileChooser();
		//saveFile.addActionListener(this);
		
		
		
		// Panels' position on frame
		add(panelTop, BorderLayout.NORTH);
		add(panelLeft, BorderLayout.WEST);
		add(panelRight, BorderLayout.EAST);
		add(panelBottom, BorderLayout.SOUTH);
		pack();
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// Open File is clicked
		if (e.getSource() == openFile) {
			int returnVal = fc.showOpenDialog(GUI.this);

			if (returnVal == JFileChooser.APPROVE_OPTION) {
				// Do stuff
			}
		} else if (e.getSource() == saveFile){
			// Do stuff
		} else if (e.getSource() == impose) {
			// Do stuff
		}
	}
}