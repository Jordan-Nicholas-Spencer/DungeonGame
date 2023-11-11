package project;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class WorldController implements KeyListener, ActionListener{
		private WorldBuilder world = new WorldBuilder();
	
		public void WorldController() {

			// Start a game timer to handle animation and updates
		    javax.swing.Timer timer = new javax.swing.Timer(0, this); // 100ms interval
		    timer.start();
		}

		public WorldBuilder getWorld() {
			return world;
		}
		
	 	@Override
	    public void keyTyped(KeyEvent e) {
	        // Implement keyTyped if needed
	    }

	    @Override
	    public void keyPressed(KeyEvent e) {
	        int key = e.getKeyCode();
	        // Implement player movement based on key presses
	        if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) {
	        	world.movePlayer(-1, 0);
	        } else if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) {
	        	world.movePlayer(1, 0);
	        } else if (key == KeyEvent.VK_UP || key == KeyEvent.VK_W) {
	        	world.movePlayer(0, 1);
	        } else if (key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) {
	        	world.movePlayer(0, -1);
	        }
	    }

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	    @Override
	    public void actionPerformed(ActionEvent e) 
	    {
	        // Implement game logic and updates here
	        // repaint();
	    }
}
