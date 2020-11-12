package edu.se.par;

import java.awt.BorderLayout;
import java.awt.Color;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
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
	String defaultLayoutString = "2 2\n5u 12u 4 13\n11u 6u 14 3\n7u 10u 2 15\n9u 8u 16 1";
			//new String[][] { { "5u", "12u", "4", "3" }, { "11u", "6u", "14", "3" },
		//{ "7u", "10u", "2", "15" }, { "9u", "8u", "16", "1" } };
	JTextArea layout = new JTextArea(defaultLayoutString, 10, 15); 
	JScrollPane scrollLayout = new JScrollPane(layout);
	
	// On-screen components for panelRight
	JButton impose = new JButton("Impose");
	JLabel loadingProgress = new JLabel("");
	
	// On-screen components for panelBottom
	JButton openFile = new JButton("Open File");
	JFileChooser fc;
	JFileChooser fc2;
	JTextArea filePath = new JTextArea("File path", 1, 10); // Should the file text areas be editable?
	JScrollPane scrollPath1= new JScrollPane(filePath);
	JButton saveFile = new JButton("Save File to...");
	JTextArea newFilePath = new JTextArea("Imposed File path", 1, 10);
	JScrollPane scrollPath2= new JScrollPane(newFilePath);
	
	
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
		panelCenter.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
		//panelCenter.setBackground(Color.LIGHT_GRAY); // To see what size the panel is ending up at
		enterLayout.setFont(bigFont);
		enterLayout.setAlignmentX(CENTER_ALIGNMENT);
		
		panelRight.setLayout((LayoutManager) new BoxLayout(panelRight, BoxLayout.Y_AXIS));
		//panelRight.setLayout((LayoutManager) new GridLayout(3,1));
		panelRight.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 10));
		//panelRight.setBackground(Color.LIGHT_GRAY); // To see what size the panel is ending up at
		//impose.setPreferredSize(new Dimension(20,20));
		//impose.setMinimumSize(new Dimension(50, 20));
		//impose.setMaximumSize(new Dimension(50, 50));
		loadingProgress.setAlignmentX(CENTER_ALIGNMENT);
		impose.setAlignmentX(CENTER_ALIGNMENT);
		
		panelBottom.setLayout((LayoutManager) new GridLayout(2, 2, 5, 10)); // Might change to a different layout
		panelBottom.setBorder(BorderFactory.createEmptyBorder(6, 6, 6, 6));
		
		
		// Adds components to the top panel
		panelTop.add(title);
		panelTop.setPreferredSize(new Dimension(500, 70));
		
		// Adds components to the center (left) panel
		panelCenter.add(enterLayout);
		panelCenter.add(Box.createRigidArea(new Dimension(0, 5)));
		panelCenter.add(scrollLayout);
		panelCenter.setPreferredSize(new Dimension(325, 300));
		
		// Adds components to the right panel
		panelRight.add(Box.createVerticalGlue());
		panelRight.add(loadingProgress);
		panelRight.add(impose);
		panelRight.add(Box.createVerticalGlue());
		panelRight.setPreferredSize(new Dimension(175, 300));
		
		// Adds components to the bottom panel
		panelBottom.add(openFile);
		panelBottom.add(scrollPath1);
		panelBottom.add(saveFile);
		panelBottom.add(scrollPath2);
		panelBottom.setPreferredSize(new Dimension(500, 92));
		
		impose.addActionListener(this);
		openFile.addActionListener(this);
		fc = new JFileChooser();
		saveFile.addActionListener(this);
		fc2 = new JFileChooser();
		
		// Panels' position on frame
		add(panelTop, BorderLayout.NORTH);
		add(panelCenter, BorderLayout.CENTER);
		add(panelRight, BorderLayout.EAST);
		add(panelBottom, BorderLayout.SOUTH);
		pack();
		
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// When buttons get clicked...
		if (e.getSource() == openFile) {
			int returnVal = fc.showOpenDialog(GUI.this);
			
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				filePath.setText(fc.getSelectedFile().toString());
				System.out.println("You picked a file");
			} else {
				// User chose cancel
			}
			
		} else if (e.getSource() == saveFile){
			int returnVal = fc2.showSaveDialog(GUI.this);
			
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                newFilePath.setText(fc2.getSelectedFile().toString());
                System.out.println("You picked a save path");
            } else {
                // User chose cancel
            }
			
		} else if (e.getSource() == impose) {
			// Do stuff
			System.out.println("You clicked Impose");
			// If everything is entered in properly, example of what could be done:
			setLoadingProgress("Now imposing...");
			try {
				Thread.sleep(50);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			Imposer imposer = new Imposer(this, new Layout(getPageLayout()));
			imposer.impose();
			PROMPT_MESSAGE("Done Imposing!");
		}
	}
	
	/**
	 * Returns the file path that is to be opened, chosen by the user
	 */
	public String getOpenFilePath() {
		return filePath.getText();
	}
	
	/**
	 * Returns the new file path that the output is to be saved to, chosen by the user
	 */
	public String getSaveFilePath() {
		return newFilePath.getText();
	}
	
	/**
	 * Returns the page layout specified by the user
	 */
	public String getPageLayout() {
		return layout.getText();
	}
	
	/**
	 * Sets the loadingProgress JLabel's text to whatever status is given
	 */
	public void setLoadingProgress(String status) {
		loadingProgress.setText(status);
	}
	
	public void PROMPT_ERROR(String s) {
		JOptionPane.showMessageDialog(this, s, "Error", JOptionPane.ERROR_MESSAGE);
	}
	
	
	public void PROMPT_MESSAGE(String s) {
		JOptionPane.showMessageDialog(this, s, "Message", JOptionPane.INFORMATION_MESSAGE);
	}
	
}