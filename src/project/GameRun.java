package project;

import project.model.WorldModel;
import project.view.Window;

/**
 * Lead Author(s):
 * @author Jordan Spencer
 * @author Nicholas Moffat
 * 
 * References:
 * Morelli, R., & Walde, R. (2016). Java, Java, Java: Object-Oriented Problem Solving.
 * Retrieved from https://open.umn.edu/opentextbooks/textbooks/java-java-java-object-oriented-problem-solving
 * 
 * Version/date: 12/10/2023
 * 
 * Responsibilities of class: Contains main() for running the program
 * 
 */

public class GameRun {
	
	/* 
	 *  TODO:
	 *  1) Game Logic
	 *  	-Create levels
	 *  	-Create story
	 *  
	 *  2) Display
	 *  	-Game Complete/Credits Display
	 *  	-Start Screen Display
	 *  
	 *  3) CISC 191
	 *  	-Comment WorldBuilder
	 *  	-Update README documentation
	 */
	
    public static void main(String[] args) {
    	
       	WorldModel model = new WorldModel();
    	model.initializeImages();
    	Window view = new Window();
    	WorldController controller = new WorldController(model, view);
    	controller.getModel().initializeGame();
    	view.setVisible(true);    
        
    }
}



