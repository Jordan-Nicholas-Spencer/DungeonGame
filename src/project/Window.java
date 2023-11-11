package project;

import javax.swing.JFrame;


public class Window extends JFrame {
	public static final int WIDTH = 970;
	public static final int HEIGHT = 800;
	
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
        
//        pack();	// preferably we are able to use pack here
        setBounds(20, 20, WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
	}
}
