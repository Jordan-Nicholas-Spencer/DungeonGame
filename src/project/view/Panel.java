package project.view;

import javax.swing.JPanel;

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
 * Version/date: 11/16/2023
 * 
 * Responsibilities of class: Defines the JPanel in which the game is displayed
 * 		This is the bulk of the view of our MVC modeled program which
 * 		handles data presentation and passes to window for display
 * 
 */

public class Panel extends JPanel {
	
	WorldBuilder worldBuilder = new WorldBuilder();
	
	/**
	 * Purpose: constructor for Panel
	 */
    public Panel() {
    	super();
    	this.setFocusable(true);
    }
    
    // invoked by keyListener added to Panel in WorldController
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        // try to render current objects
        try {
        	g.setColor(Color.BLACK);
        	g.fillRect(0, 0, Window.WIDTH, Window.HEIGHT);        	
        	
        	worldBuilder.renderLevel(WorldModel.getCurrentRoom(), WorldModel.getPlayer(), g);
        	worldBuilder.renderPlayer(WorldModel.getPlayer(), g);
        	worldBuilder.renderEnemy(WorldModel.getCurrentRoom().getEnemies(), WorldModel.getPlayer(), g);

        	
        } catch (Exception e) {
        	System.out.print("Error rendering ");
        }

        // update rendered objects on screen
        repaint();
    }
}

    



