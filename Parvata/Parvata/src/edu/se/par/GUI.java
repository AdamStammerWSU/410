package edu.se.par;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
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
	JTextArea layout = new JTextArea("EXAMPLE LAYOUT HERE", 10, 15);
	
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
		gui.setSize(500, 500);
		gui.setLocationRelativeTo(null); // this isn't working either, for some reason...
		gui.setVisible(true);
	}
	
	
	public GUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setTitle("Parvata Book Imposer");
		
		title.setFont(titleFont);
		
		panelTop.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
		
		panelLeft.setLayout((LayoutManager) new FlowLayout());
		panelLeft.setBorder(BorderFactory.createEmptyBorder(0, 10, 5, 0));
		
		panelRight.setLayout((LayoutManager) new BoxLayout(panelRight, BoxLayout.Y_AXIS));
		
		
		// Adds components to the top panel
		panelTop.add(title);
		panelTop.setPreferredSize(new Dimension(500, 80)); // I don't know why none of these are working or what makes them different from the last two projects
		
		// Adds components to the left panel
		panelLeft.add(enterLayout);
		panelLeft.add(Box.createRigidArea(new Dimension(0, 20)));
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
		
		impose.addActionListener(this);
		openFile.addActionListener(this);
		fc = new JFileChooser();
		saveFile.addActionListener(this);
		
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
				System.out.println("You picked a file");
			} else {
				// User choose cancel
			}
		} else if (e.getSource() == saveFile){
			// Do stuff
			System.out.println("You clicked Save File");
			
		} else if (e.getSource() == impose) {
			// Do stuff
			System.out.println("You clicked Impose");
		}
	}
}