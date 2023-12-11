package project.view;

import javax.swing.JPanel;

import project.WorldController;
import project.model.WorldModel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

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
 * Responsibilities of class: Defines the JPanel in which the game is displayed
 * 		This is the bulk of the view of our MVC modeled program which
 * 		handles data presentation and passes to window for display
 * 
 */

public class Panel extends JPanel {
	private boolean gameOverScreen = false;
	WorldBuilder worldBuilder = new WorldBuilder();
	
	/**
	 * Purpose: constructor for Panel
	 */
    public Panel() {
    	super();
    	this.setFocusable(true);
    }
    
    /**
     * gets the current WorldBuilder object
     * @return the WorldBuilder object
     */
    public WorldBuilder getWorldBuilder()
    {
    	return worldBuilder;
    }
    
    /**
     * determines if the gameOverScreen is being displayed
     * @return true if gameOverScreen is being displayed, otherwise false
     */
    public boolean isGameOver()
    {
    	return gameOverScreen;
    }
    
    /**
     * sets gameOverScreen to false when a newGame is started
     */
    public void newGame()
    {
    	gameOverScreen = false;
    }
    
    /**
     * renders all of the images displayed to the user
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        // try to render current objects
        try {
        	g.setColor(Color.BLACK);
        	g.fillRect(0, 0, Window.WIDTH, Window.HEIGHT);        	
        	
        	// everything that should always be displayed
        	worldBuilder.renderLevel(WorldModel.getCurrentRoom(), WorldModel.getPlayer(), g);
        	worldBuilder.renderPlayer(WorldModel.getPlayer(), g);
        	worldBuilder.renderEnemy(WorldModel.getCurrentRoom().getEnemies(), WorldModel.getPlayer(), g);
        	worldBuilder.renderHUD(WorldModel.getPlayer(), g);
        	
        	// these are the toggle windows
        	if (WorldController.getIsDialogueActive())
        	{
        		worldBuilder.renderDialogueWindow(WorldModel.getDialogueText(), g);
        	}
        	else if (WorldController.getIsChestWindowActive())
        	{
        		worldBuilder.renderChestWindow(g);
        	}
        	else if (WorldController.getIsInventoryWindowActive())
        	{
        		worldBuilder.renderInventoryWindow(WorldModel.getPlayer(), g);
        	}
        	// when the player dies render the gameOver screen
        	if (WorldModel.getPlayer().getHealth() <= 0)
        	{
        		worldBuilder.renderGameOver(WorldModel.getPlayer(), g);
        		gameOverScreen = true;
        	}
        // catch any exception during render
        } catch (Exception e) {
        	System.out.print("Error rendering ");
        }
        // update rendered objects on screen
        repaint();
    }
}