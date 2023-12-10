package project;

import project.model.WorldModel;
import project.view.Window;
import project.view.WorldBuilder;

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
 * Responsibilities of class: Contains main() for running the program
 * 
 */

public class GameRun {
	
	/* 
	 *  TODO:
	 *  1) Game Logic
	 *  	-Fix chests to use Shop class
	 *  	-Create levels
	 *  	-Create story
	 *  
	 *  2) Display
	 * 		-Inventory Display // fix rendering error
	 *  	-Game win screen //	Game Complete/Credits Display
	 *  
	 *  3) CISC 191
	 *  	- Learning Objectives 
	 *  		- I/O
	 *  		- Abstract class and interface
	 *  
	 *  	// clean up code
	 *  	-Comment all code
	 *  	-Update README documentation
	 *  	-Remove redundant variable
	 *  	-Ensure proper variable names
	 *  	-Ensure proper usage of private,protected,public,static,final
	 *  
	 *  4) Extras
	 *  	-potions/healing
	 *  	-Dialogue Display
	 * 		-Enemy chasing player logic in moveEnemies()
	 * 		-newGame+
	 * 		-Items carry over to newGame+
	 *  	-Movement between tiles to be more fluid (no teleporting)
	 *  	-Start Screen Display
	 *  	-Interaction Display 	//idea - if you are close to the item, render a sprite above the item 
	 *  						    	   the sprite being like a message bubble with a 'E' in it to
	 *  								   inform the player to press E to interact
	 */
	
    public static void main(String[] args) {
    	
       	WorldModel model = new WorldModel();
       	WorldBuilder builder = new WorldBuilder();
    	model.initializeImages();
    	Window view = new Window();
    	WorldController controller = new WorldController(model, view);
    	controller.getModel().initializeGame();
    	view.setVisible(true);    
        
    }
}



