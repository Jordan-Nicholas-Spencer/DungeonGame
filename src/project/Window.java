package project;

import javax.swing.JFrame;


public class Window extends JFrame {

	/**
	 * Purpose: Constructor for GameWindow
	 */
	public Window() {
		initComponents();	
	}
	
	/**
	 * Purpose: initializes components of GameWindow
	 * @param none
	 * @return none
	 */
	private void initComponents() {
		setTitle("HUNTER");
		
		Panel mainPanel = new Panel();
        add(mainPanel);
        
        // pack();	// preferably we are able to use pack here and gridLayout in GamePanel
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
	}
}
