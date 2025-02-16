package project.view;

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
 * Version/date: 12/10/2023
 * 
 * Responsibilities of class: Defines the JFrame in which the game is displayed
 * 
 */

public class Window extends JFrame {
	
	public static final int WIDTH = 896;  // 32 x 28
	public static final int HEIGHT = 576; // 32 * 18
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
        setBounds((screenSize.width / 2) - (WIDTH / 2), (screenSize.height / 2) - (HEIGHT / 2), WIDTH, HEIGHT);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
