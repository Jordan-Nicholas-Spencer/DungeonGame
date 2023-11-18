package project;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

import javax.swing.Timer;

import project.model.WorldModel;
import project.view.Panel;
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
	
		public WorldController(WorldModel model, Window view) {
			this.model = model;
			this.view = view;
			this.panel = new Panel();
			this.panel.addKeyListener(new KeyboardListener());
			this.view.add(panel);
			
			// Start a game timer to handle animation and updates
		    Timer timer = new Timer(0, this); // 100ms interval
		    timer.start();
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
		        	model.movePlayer(-1, 0);
		        	WorldModel.getPlayer().setFacing("left");
		        } 
		        else if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) {
		        	model.movePlayer(1, 0);
		        	WorldModel.getPlayer().setFacing("right");
		        } 
		        else if (key == KeyEvent.VK_UP || key == KeyEvent.VK_W) {
		        	model.movePlayer(0, -1);
		        	WorldModel.getPlayer().setFacing("up");
		        } 
		        else if (key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) {
		        	model.movePlayer(0, 1);
		        	WorldModel.getPlayer().setFacing("down");
		        }
		    }
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// can we use this to slow down movement?
		}
}
