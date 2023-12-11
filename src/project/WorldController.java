package project;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

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
 * Version/date: 12/10/2023
 * 
 * Responsibilities of class: Controls data flow between user, model, and view 
 * 
 */

public class WorldController
{
		
		private WorldModel model;
		private Window view;
		private Panel panel;
		private static boolean dialogueExhausted = false;
		private static boolean isDialogueActive = false;
		private static boolean isChestWindowActive = false;
		private static boolean isInventoryWindowActive = false;
	
		// constructor
		// adds keyListener to panel and adds panel to view
		public WorldController(WorldModel model, Window view) {
			this.model = model;
			this.view = view;
			this.panel = new Panel();
			this.panel.addKeyListener(new KeyboardListener());
			this.view.add(panel);
		}
		
		/**
		 * sets isDialogueActive to active status parameter
		 * @param a boolean active status
		 */
		public static void setIsDialogueActive(boolean active)
		{
			isDialogueActive = active;
		}
		
		/**
		 * gets isDialogueActive current status
		 * @return isDialogueActive status
		 */
		public static boolean getIsDialogueActive()
		{
			return isDialogueActive;
		}
		
		/**
		 * sets isChestWindowActive to active status parameter
		 * @param a boolean active status
		 */
		public static void setIsChestWindowActive(boolean active)
		{
			isChestWindowActive = active;
		}
		
		/**
		 * gets isChestWindowActive current status
		 * @return isChestWindowActive status
		 */
		public static boolean getIsChestWindowActive()
		{
			return isChestWindowActive;
		}
		
		/**
		 * sets isInventoryWindowActive to active status parameter
		 * @param a boolean active status
		 */
		public static void setInventoryWindowActive(boolean active)
		{
			isInventoryWindowActive = active;
		}
		
		/**
		 * Purpose: set the value of dialogueExhausted
		 * @param a boolean status
		 */
		public static void setDialogueExhausted(boolean active)
		{
			dialogueExhausted = active;
		}
		
		/**
		 * Purpose: set the value of dialogueExhausted
		 * @param a boolean status
		 */
		public static boolean getDialogueExhausted()
		{
			return dialogueExhausted;
		}
		/**
		 * gets isInventoryWindowActive current status
		 * @return isInventoryWindowActive status
		 */
		public static boolean getIsInventoryWindowActive()
		{
			return isInventoryWindowActive;
		}
		
		/**
		 * gets current WorldModel
		 * @return current WorldModel
		 */
		public WorldModel getModel() {
			return model;			
		}		
		
		/**
		 * Responsibilities of class: executes simple operations based on keyboard input
		 */
		class KeyboardListener implements KeyListener{
			@Override
		    public void keyTyped(KeyEvent e) {
		        // Implement keyTyped if needed
		    }
			
			@Override
			public void keyReleased(KeyEvent e) {				
				// Implement keyReleased if needed
			}

			/**
			 * executes simple operations based on keyboard input
			 * @param any KeyEvent
			 * @return current WorldModel
			 */
		    @Override
		    public void keyPressed(KeyEvent e) {
		        int key = e.getKeyCode();
		        
		        // player movement left
		        if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) {
		        	WorldModel.getPlayer().setFacing("left");
		        	model.movePlayer(-1, 0);
		        	if (panel.isGameOver())
		        	{
		        		model.initializeGame();
		        		panel.newGame();
		        	}
		        }
		        // player movement right
		        else if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) {
		        	WorldModel.getPlayer().setFacing("right");
		        	model.movePlayer(1, 0);
		        	if (panel.isGameOver())
		        	{
		        		model.initializeGame();
		        		panel.newGame();
		        	}
		        } 
		        // player movement up
		        else if (key == KeyEvent.VK_UP || key == KeyEvent.VK_W) {
		        	WorldModel.getPlayer().setFacing("up");
		        	model.movePlayer(0, -1);
		        	if (panel.isGameOver())
		        	{
		        		model.initializeGame();
		        		panel.newGame();
		        	}
		        } 
		        // player movement down
		        else if (key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) {
		        	WorldModel.getPlayer().setFacing("down");
		        	model.movePlayer(0, 1);
		        	if (panel.isGameOver())
		        	{
		        		model.initializeGame();
		        		panel.newGame();
		        	}
		        }		
		        
		        // toggle god mode
		        else if (key == KeyEvent.VK_G)
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
		        // toggle dialogue window
		        else if (key == KeyEvent.VK_E && model.isNPCAtPlayer() && WorldController.dialogueExhausted == false)
		        {
		        	if (getIsDialogueActive()) {
		        		WorldController.setIsDialogueActive(false);
		        		WorldModel.exhaustDialogue();
		        		WorldController.setDialogueExhausted(true);
		        	}
		        	else {
		        		WorldController.setIsDialogueActive(true);
		        	}
		        	
		        }
		        // toggle chest window
		        else if (key == KeyEvent.VK_E && WorldModel.playerNextToChest(false))
		        {
		        	if (getIsChestWindowActive()) {
		        		WorldController.setIsChestWindowActive(false);
		        	}
		        	else {
		        		WorldController.setIsChestWindowActive(true);
		        	}
		        }
		        
		        
		        // toggle inventory
		        else if (key == KeyEvent.VK_I)
		        {
		        	if (!WorldController.getIsInventoryWindowActive()) {
		        		WorldController.setInventoryWindowActive(true);
		        	}
		        	else {
		        		WorldController.setInventoryWindowActive(false);
		        	}
		        }		    
		        
		        // take item from chest
		        else if (key == KeyEvent.VK_1 && WorldController.getIsChestWindowActive())
		        {
		        	WorldModel.pickUpItem(WorldModel.getPlayer(), WorldModel.getPlayer().getPosX(), WorldModel.getPlayer().getPosY());
		        	WorldController.setIsChestWindowActive(false);
		        }
		        
		        // heal the player
		        else if (key == KeyEvent.VK_1 && WorldController.getIsInventoryWindowActive())
		        {
		        	// check to see if the inventory size is greater than zero
		        	// if it is, then check to see which consumable is being used
		        	if (WorldModel.getPlayer().getInventorySize() > 0)
		        	{
		        		if (WorldModel.getPlayer().getInventoryItem(0).getName() == "Health Potion")
			        	{
			        		WorldModel.getPlayer().heal(10);
			        		WorldModel.getPlayer().getInventoryConsumables().remove(0);
			        	}
		        	}
		        }
		        
		        else if (key == KeyEvent.VK_2 && WorldController.getIsInventoryWindowActive())
		        {
		        	// check to see if the inventory size is greater than one
		        	// if it is, then check to see which consumable is being used
		        	if (WorldModel.getPlayer().getInventorySize() > 1)
		        	{
		        		if (WorldModel.getPlayer().getInventoryItem(1).getName() == "Health Potion")
			        	{
			        		WorldModel.getPlayer().heal(10);
			        		WorldModel.getPlayer().getInventoryConsumables().remove(1);
			        	}
		        	}
		        }
		        
		        else if (key == KeyEvent.VK_3 && WorldController.getIsInventoryWindowActive())
		        {
		        	// check to see if the inventory size is greater than two
		        	// if it is, then check to see which consumable is being used
		        	if (WorldModel.getPlayer().getInventorySize() > 2)
		        	{
		        		if (WorldModel.getPlayer().getInventoryItem(2).getName() == "Health Potion")
			        	{
			        		WorldModel.getPlayer().heal(10);
			        		WorldModel.getPlayer().getInventoryConsumables().remove(2);
			        	}
		        	}
		        }
		        
		        else if (key == KeyEvent.VK_4 && WorldController.getIsInventoryWindowActive())
		        {
		        	// check to see if the inventory size is greater than three
		        	// if it is, then check to see which consumable is being used
		        	if (WorldModel.getPlayer().getInventorySize() > 3)
		        	{
		        		if (WorldModel.getPlayer().getInventoryItem(3).getName() == "Health Potion")
			        	{
			        		WorldModel.getPlayer().heal(10);
			        		WorldModel.getPlayer().getInventoryConsumables().remove(3);
			        	}
		        	}
		        }
		        // open door
		        else if ((key == KeyEvent.VK_E && WorldModel.playerNextToDoor()))
		        {
		        	// message
		        }  
		    }
		}
}