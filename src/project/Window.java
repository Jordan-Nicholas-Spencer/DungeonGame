package project;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

/**
 * Lead Author(s):
 * @author Jordan Spencer
 * @author Nicholas Moffat
 * 
 * References:
 * Morelli, R., & Walde, R. (2016). Java, Java, Java: Object-Oriented Problem Solving.
 * Retrieved from https://open.umn.edu/opentextbooks/textbooks/java-java-java-object-oriented-problem-solving
 * 
 *  
 * Version/date: 11/16/2023
 * 
 * Responsibilities of class: Defines the JFrame in which the game is displayed
 * 
 */

public class Window extends JFrame {
	
	// constants for window size and placement on screen
	public static final int WIDTH = 970;
	public static final int HEIGHT = 800;
	private static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	
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
		
//		Panel display = new Panel();
//        add(display);
        
//      pack();	// preferably we are able to use pack here
        setBounds((screenSize.width / 2) - (WIDTH / 2), (screenSize.height / 2) - (HEIGHT / 2), WIDTH, HEIGHT);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
