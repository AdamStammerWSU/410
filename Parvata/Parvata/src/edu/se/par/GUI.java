package edu.se.par;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
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
	
	// Top, center (left), right, and bottom panels
	JPanel panelTop = new JPanel();
	JPanel panelCenter = new JPanel();
	JPanel panelRight = new JPanel();
	JPanel panelBottom = new JPanel();
	
	Font titleFont = new Font("SansSerif", Font.BOLD, 30);
	Font bigFont = new Font("SansSerif", Font.PLAIN, 20);
	Font mediumFont = new Font("SansSerif", Font.PLAIN, 16); // might use for something later
		
	// On-screen component for panelTop
	JLabel title = new JLabel("Parvata Book Imposer");
	
	// On-screen components for panelCenter
	JLabel enterLayout = new JLabel("Enter Page Layout");
	JTextArea layout = new JTextArea("EXAMPLE LAYOUT HERE", 10, 15);
	
	// On-screen components for panelRight
	JLabel tba = new JLabel("loading progress (so would start blank)");
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
		gui.setLocationRelativeTo(null);
		gui.setVisible(true);
	}
	
	
	public GUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setTitle("Parvata Book Imposer");
		
		panelTop.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
		panelTop.setBackground(Color.LIGHT_GRAY); // To see size of panel
		title.setFont(titleFont);
		
		panelCenter.setLayout((LayoutManager) new BoxLayout(panelCenter, BoxLayout.Y_AXIS));
		panelCenter.setBorder(BorderFactory.createEmptyBorder(0, 10, 5, 10));
		//panelCenter.setBackground(Color.LIGHT_GRAY); // To see what size the panel is ending up at
		enterLayout.setFont(bigFont);
		enterLayout.setAlignmentX(CENTER_ALIGNMENT);
		
		panelRight.setLayout((LayoutManager) new BoxLayout(panelRight, BoxLayout.Y_AXIS));
		panelRight.setBorder(BorderFactory.createEmptyBorder(0, 10, 5, 10));
		//panelRight.setBackground(Color.LIGHT_GRAY); // To see what size the panel is ending up at
		tba.setAlignmentX(CENTER_ALIGNMENT);
		
		panelBottom.setLayout((LayoutManager) new GridLayout(2, 2, 5, 10)); // Might change to a different layout
		panelBottom.setBorder(BorderFactory.createEmptyBorder(6, 6, 6, 6));
		
		
		// Adds components to the top panel
		panelTop.add(title);
		panelTop.setPreferredSize(new Dimension(500, 70));
		
		// Adds components to the center (left) panel
		panelCenter.add(enterLayout);
		//panelLeft.add(Box.createRigidArea(new Dimension(0, 20)));
		panelCenter.add(layout);
		panelCenter.setPreferredSize(new Dimension(300, 300));
		
		// Adds components to the right panel
		//panelRight.add(Box.createRigidArea(new Dimension(0, 150)));
		panelRight.add(tba);
		panelRight.add(impose);
		panelCenter.setPreferredSize(new Dimension(200, 300));
		
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
		add(panelCenter, BorderLayout.CENTER);
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