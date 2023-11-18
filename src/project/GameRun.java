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
	 *  	-Implement battle logic between enemy and player
	 *  
	 *  2) Display
	 *  	-Player HUD Display
	 * 		-Inventory Display
	 *  	-Start Screen Display
	 *  	-Game Over Display
	 *  	-Game Complete/Credits Display
	 *  	-Dialogue Display
	 *  
	 *  3) Level Design
	 *  	-Create levels
	 *  
	 *  4) Inventory System
	 *  	-Design Inventory System
	 *  
	 *  
	 */
	
    public static void main(String[] args) {
    	
    	//loop begin
    	WorldModel model = new WorldModel();
    	WorldModel.initializeWorld();
    	Window view = new Window();
    	WorldController controller = new WorldController(model, view);
    	view.setVisible(true);
        // loop end        
        
    }
}




