package project;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

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

public class WorldController
{
	
		private WorldModel model;
		private Window view;
		private Panel panel;
		private static boolean isDialogueActive = false;
		private static boolean isTalking = false;
		private static boolean isChestWindowActive = false;
		private static boolean inMenu = false;
		private static boolean isInventoryWindowActive = false;
		private static boolean inInventory = false;
	
		public WorldController(WorldModel model, Window view) {
			this.model = model;
			this.view = view;
			this.panel = new Panel();
			this.panel.addKeyListener(new KeyboardListener());
			this.view.add(panel);
		}
		
		public static void setDialogueActive(boolean active)
		{
			isDialogueActive = active;
		}
		
		public static boolean getIsDialogueActive()
		{
			return isDialogueActive;
		}
		
		public static void setChestWindowActive(boolean active)
		{
			isChestWindowActive = active;
		}
		
		public static boolean getIsChestWindowActive()
		{
			return isChestWindowActive;
		}
		
		public static boolean getIsInventoryWindowActive()
		{
			return isInventoryWindowActive;
		}
		
		public static void setInventoryWindowActive(boolean active)
		{
			isInventoryWindowActive = active;
		}
		
		public WorldModel getWorld() {
			return model;			
		}		
		
		class KeyboardListener implements KeyListener{
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
		        	if (panel.isGameOver())
		        	{
		        		model.initializeGame();
		        		panel.newGame();
		        	}
		        }
		        else if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) {
		        	WorldModel.getPlayer().setFacing("right");
		        	model.movePlayer(1, 0);
		        	if (panel.isGameOver())
		        	{
		        		model.initializeGame();
		        		panel.newGame();
		        	}
		        } 
		        else if (key == KeyEvent.VK_UP || key == KeyEvent.VK_W) {
		        	WorldModel.getPlayer().setFacing("up");
		        	model.movePlayer(0, -1);
		        	if (panel.isGameOver())
		        	{
		        		model.initializeGame();
		        		panel.newGame();
		        	}
		        } 
		        else if (key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) {
		        	WorldModel.getPlayer().setFacing("down");
		        	model.movePlayer(0, 1);
		        	if (panel.isGameOver())
		        	{
		        		model.initializeGame();
		        		panel.newGame();
		        	}
		        }
		        
		        // player interaction
		        if (key == KeyEvent.VK_E && model.isNPCAtPlayer() && WorldController.isTalking == false)
		        {
		        	WorldController.isTalking = true;
		        	WorldController.setDialogueActive(true);
		        }
		        else if (key == KeyEvent.VK_E && WorldController.getIsDialogueActive() && WorldController.isTalking)
		        {
		        	WorldController.setDialogueActive(false);
		        	WorldController.isTalking = false;
		        }
		        else if (key == KeyEvent.VK_E && WorldModel.playerNextToChest(false) && WorldController.inMenu == false)
		        {
		        	WorldController.inMenu = true;
		        	WorldController.setChestWindowActive(true);
		        }
		        else if ((key == KeyEvent.VK_E && WorldController.getIsChestWindowActive() && WorldController.inMenu))
		        {
		        	WorldController.inMenu = false;
		        	WorldController.setChestWindowActive(false);
		        }
		        else if ((key == KeyEvent.VK_E && WorldModel.playerNextToDoor()))
		        {
		        	// message
		        }
		        
		        if (key == KeyEvent.VK_1 && WorldController.getIsChestWindowActive())
		        {
		        	WorldModel.pickUpItem(WorldModel.getPlayer(), WorldModel.getPlayer().getPosX(), WorldModel.getPlayer().getPosY());
		        	WorldController.inMenu = false;
		        	WorldController.setChestWindowActive(false);
		        }
		        
		        
		        // inventory
		        if (key == KeyEvent.VK_I && WorldController.inInventory == false)
		        {
		        	WorldController.inInventory = true;
		        	WorldController.setInventoryWindowActive(true);
		        }
		        else if (key == KeyEvent.VK_I && WorldController.getIsInventoryWindowActive() && WorldController.inInventory)
		        {
		        	WorldController.inInventory = false;
		        	WorldController.setInventoryWindowActive(false);
		        }

		        
		        // god mode
		        if (key == KeyEvent.VK_G)
		        {
		        	if (WorldModel.getPlayer().getHealth() < 1000)
		        	{
		        		WorldModel.getPlayer().infiniteHealth();
		        		WorldModel.getPlayer().onePunchMan();
		        	}
		        	else
		        	{
		        		WorldModel.getPlayer().normalHealth();
		        		WorldModel.getPlayer().normalDamage();
		        	}
		        }
		    }
		}
		
		public WorldModel getModel()
		{
			return model;
		}
}