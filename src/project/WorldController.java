package project;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

import javax.swing.Timer;

import project.model.WorldModel;
import project.view.Panel;
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
 * Version/date: 11/18/2023
 * 
 * Responsibilities of class: Controls data flow between user, model, and view
 * 		controller never handles data logic (reserved for model - WorldBuilder)
 *		controller never handles data presentation (reserved for view - Window/Panel)
 * 
 */

public class WorldController implements ActionListener{
	
		private WorldModel model;
		private Window view;
		private Panel panel;
		private WorldBuilder builder;
		private static boolean gameOver = false;
		private static boolean isDialogueActive = false;
		private static boolean isTalking = false;
	
		public WorldController(WorldModel model, Window view) {
			this.model = model;
			this.view = view;
			this.panel = new Panel();
			this.panel.addKeyListener(new KeyboardListener());
			this.view.add(panel);
			this.builder = panel.getWorldBuilder();
			
			// Start a game timer to handle animation and updates
		    Timer timer = new Timer(0, this); // 100ms interval
		    timer.start();
		}
		
		public static void setDialogueActive(boolean active)
		{
			isDialogueActive = active;
		}
		
		public static boolean getIsDialogueActive()
		{
			return isDialogueActive;
		}
		
		public WorldModel getWorld() {
			return model;			
		}		
		
		class KeyboardListener implements KeyListener {
			@Override
		    public void keyTyped(KeyEvent e) {
		        // Implement keyTyped if needed
		    }
			
			@Override
			public void keyReleased(KeyEvent e) {				
				// Implement keyReleased if needed
			}

		    @Override
		    public void keyPressed(KeyEvent e) {
		        int key = e.getKeyCode();
		        // Implement player movement based on key presses
		        if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) {
		        	WorldModel.getPlayer().setFacing("left");
		        	model.movePlayer(-1, 0);
		        }
		        else if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) {
		        	WorldModel.getPlayer().setFacing("right");
		        	model.movePlayer(1, 0);
		        } 
		        else if (key == KeyEvent.VK_UP || key == KeyEvent.VK_W) {
		        	WorldModel.getPlayer().setFacing("up");
		        	model.movePlayer(0, -1);
		        } 
		        else if (key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) {
		        	WorldModel.getPlayer().setFacing("down");
		        	model.movePlayer(0, 1);
		        }
		        else if (key == KeyEvent.VK_E && model.isNPCAtPlayer() && WorldController.isTalking == false)
		        {
		        	WorldController.isTalking = true;
		        	String dialogue = "yo";
		        	builder.renderDialogueWindow(panel.getGraphics());
		        	WorldController.setDialogueActive(true);
		        }
		        else if (key == KeyEvent.VK_E && WorldController.getIsDialogueActive() && WorldController.isTalking)
		        {
		        	WorldController.setDialogueActive(false);
		        	WorldController.isTalking = false;
		        }
		    }
		}
		
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			
		}
}
