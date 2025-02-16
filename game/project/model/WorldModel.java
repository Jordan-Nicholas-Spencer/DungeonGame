package project.model;

import java.io.File;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.FileWriter;

import project.WorldController;
import project.model.items.Armor;
import project.model.items.Item;
import project.model.items.Weapon;
import project.view.ImageLoader;

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
 * Responsibilities of class: Handles the data logic of the program
 * 
 */

public class WorldModel {
	
	private static Player player;
	private static Room currentRoom;
	private static Random random;
	private static LevelDesign level;
	private static ArrayList<String> dialogueText;
	
	public WorldModel() {
		initializeGame();
	}
	
	/**
	 * Purpose: initialize the game
	 */
	public void initializeGame() {
		random = new Random();
		level = new LevelDesign();
		currentRoom = level.LEVELARRAY[0];
		player = new Player ("player", currentRoom.getXStartPos(), currentRoom.getYStartPos());
		try {
			read();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Purpose: get the first element of the dialogue array
	 * @return
	 */
	public static String getDialogueText() {
		if (dialogueText.size() > 0)
		{
			return dialogueText.get(0);
		}
		return "";
	}
	
	/**
	 * Purpose: remove the first element of the dialogue array
	 */
	public static void exhaustDialogue()
	{
		if (dialogueText.size() > 0)
		{
			dialogueText.remove(0);
		}
	}
	
	/**
	 * Purpose: read the dialogue file
	 * @throws IOException
	 */
	public void read() throws IOException
	{
		dialogueText = new ArrayList<>();
		
		File file = new File("src/project/view/Dialogue.txt");
		
		Scanner scanner = new Scanner(file);
		
		while(scanner.hasNext())
		{
			String[] tokens = scanner.nextLine().split(";");
			String last = tokens[tokens.length - 1];
			dialogueText.add(last);
		}
	}
	
	/**
	 * Purpose: write the player stats to a file
	 * @param stats
	 */
	public static void writeStats(int stats)
	{
		try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/project/view/stats.txt")))
		{
			writer.write(String.valueOf(stats));
			writer.newLine();
			
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Purpose: read the player stats from the file
	 * @return
	 * @throws IOException
	 */
	public static String readStats() throws IOException
	{
		String stats = "";
		File file = new File("src/project/view/stats.txt");
		Scanner scanner = new Scanner(file);
		while(scanner.hasNext())
		{
			stats = scanner.nextLine();
		}
		
		return stats;
	}
	
	
	/**
	 * Purpose: initialize the images
	 */
	public void initializeImages() {
		ImageLoader.initializeSprites();
	}
	
	/**
	 * Purpose: get the player
	 * @return
	 */
	public static Player getPlayer() {
		return player;
	}
	
	/**
	 * Purpose: get the tile in front of the player
	 * @param organism
	 * @param dirX
	 * @param dirY
	 * @return
	 */
	public static Tile getTileInFront(Organism organism, int dirX, int dirY) {
		return currentRoom.getTileAt(organism.getPosX() + dirX, organism.getPosY() + dirY);
	}
	
	/**
	 * Purpose: get the current room
	 * @return
	 */
	public static Room getCurrentRoom() {
		return currentRoom;
	}
	
	/**
	 * Purpose: move the player in the desired direction
	 * @param dirX
	 * @param dirY
	 */
	public void movePlayer(int dirX, int dirY) {
		boolean playerMoved = false;
		// cant move if the inventory, chest or dialogue window is open
		if (WorldController.getIsInventoryWindowActive()) {
			return;
		}
		if (WorldController.getIsChestWindowActive()) {
			return;
		}
		if (WorldController.getIsDialogueActive()) {
			return;
		}
		// this is how we damage enemies. if they are in front of the player, then we have them fight
		if (currentRoom.enemyInRoom(getTileInFront(player, dirX, dirY).getPosX(), getTileInFront(player, dirX, dirY).getPosY())) {
			Enemy enemy = currentRoom.getEnemyAt(getTileInFront(player, dirX, dirY).getPosX(), getTileInFront(player, dirX, dirY).getPosY());
			enemy.damage(player.getStrength() - enemy.getDefense());
			if (enemy.getHealth() <= 0) {
				currentRoom.killEnemy(enemy.getPosX(), enemy.getPosY());
			}
			moveEnemies();
		}
		else {
			switch(getTileInFront(player, dirX, dirY).getName()) {
			
			case "floor":
				player.setPosition(player.getPosX() + dirX, player.getPosY() + dirY);
				playerMoved = true;
				break;
			case "open":
				player.setPosition(player.getPosX() + dirX, player.getPosY() + dirY);
				playerMoved = true;
				break;
			case "key":
				player.setPosition(player.getPosX() + dirX, player.getPosY() + dirY);
				player.pickUpKey();
				currentRoom.pickUpItem(player.getPosX(), player.getPosY());
				playerMoved = true;
				break;
			case "skeleton":
				break;
			case "gate":
				break;
			case "chest":
				break;
			case "wall":
				break;
			case "stairs":
				// exhaust the dialogue of the npc even if you didnt talk to them
				// assuming they are in the room
				if (WorldController.getDialogueExhausted() == false && currentRoom.getNPCs().length > 0)
				{
					WorldModel.exhaustDialogue();
				}
				else
				{
					WorldController.setDialogueExhausted(false);
				}
				nextLevel();
				break;
			case "door":			
				break;
			default:
				break;
			}
			// move the enemies if the player moved
			if (playerMoved == true) {
				moveEnemies();
			}
		}
	}
	
	/**
	 * Purpose: move the enemies randomly about the room
	 */
	public void moveEnemies() {
		for (Enemy enemy : currentRoom.getEnemies()) {
			String name;
			switch(random.nextInt(4)) {
			case 0:
				name = getTileInFront(enemy, 1, 0).getName();
				
				// if there is an enemy in front, try to move in a different direction
				if(currentRoom.enemyInRoom(enemy.getPosX() + 1, enemy.getPosY())) {
					return; // consider using break in case enemy is surrounded on all sided by enemies
				}
				// if the player is next to the enemy, face player and fight
				else if(playerNextToEnemy(enemy)) {
					player.damage(enemy.getStrength() - player.getDefense());
					if (player.getHealth() <= 0) {
						
					}
					break;
				}	
				else if(name == "floor" || name == "open") {
					enemy.setPosition(enemy.getPosX() + 1, enemy.getPosY());
					enemy.setFacing("right");
					break;
				}
			case 1:
				name = getTileInFront(enemy, -1, 0).getName();
				if(currentRoom.enemyInRoom(enemy.getPosX() - 1, enemy.getPosY())) {
					return;
				}
				else if(playerNextToEnemy(enemy)) {
					player.damage(enemy.getStrength() - player.getDefense());
					break;
				}	
				if(name == "floor" || name == "open") {
					enemy.setPosition(enemy.getPosX() - 1, enemy.getPosY());
					enemy.setFacing("left");
					break;
				}
			case 2:
				name = getTileInFront(enemy, 0, 1).getName();
				if(currentRoom.enemyInRoom(enemy.getPosX(), enemy.getPosY() + 1)) {
					return;
				}
				else if(playerNextToEnemy(enemy)) {
					player.damage(enemy.getStrength() - player.getDefense());
					break;
				}	
				if(name == "floor" || name == "open") {
					enemy.setPosition(enemy.getPosX(), enemy.getPosY() + 1);
					enemy.setFacing("down");
					break;
				}
			case 3:
				name = getTileInFront(enemy, 0, -1).getName();
				if(currentRoom.enemyInRoom(enemy.getPosX(), enemy.getPosY() - 1)) {
					return;
				}
				else if(playerNextToEnemy(enemy)) {
					player.damage(enemy.getStrength() - player.getDefense());
					break;
				}	
				if(name == "floor" || name == "open") {
					enemy.setPosition(enemy.getPosX(), enemy.getPosY() - 1);
					enemy.setFacing("up");
					break;
				}
			}
		}
	}
	
	
	/**
	 * Purpose: unlock a door
	 * @param x
	 * @param y
	 */
	public static void openDoor(int x, int y)
	{
		WorldModel.getCurrentRoom().openDoor(x, y);
		WorldModel.getPlayer().useKey();
	}
	
	/**
	 * Purpose: check if a player is next to an enemy
	 * @param enemy
	 * @return
	 */
	public boolean playerNextToEnemy(Enemy enemy) {
		boolean attackingRange = false;
		// player right
		if (enemy.getPosX() + 1 == player.getPosX() && enemy.getPosY() == player.getPosY()) {
			enemy.setFacing("right");
			attackingRange = true;
			return attackingRange;
		}
		// player left
		else if (enemy.getPosX() - 1 == player.getPosX() && enemy.getPosY() == player.getPosY()) {
			attackingRange = true;
			enemy.setFacing("left");
			return attackingRange;
		}
		// player down
		else if (enemy.getPosX() == player.getPosX() && enemy.getPosY() + 1 == player.getPosY()) {
			enemy.setFacing("down");
			attackingRange = true;
			return attackingRange;
		}
		// player up
		else if (enemy.getPosX() == player.getPosX() && enemy.getPosY() - 1 == player.getPosY()) {
			enemy.setFacing("up");
			attackingRange = true;
			return attackingRange;
		}
		
		// player not next to enemy
		return attackingRange;
	}
	
	/**
	 * Purpose: check if a player is next to a door
	 * @return
	 */
	public static boolean playerNextToDoor()
	{
		boolean isNear = false;
		
		// uses a similar format as the playerNextToEnemy method
		if(WorldModel.getCurrentRoom().getTileAt(WorldModel.getPlayer().getPosX()+1, WorldModel.getPlayer().getPosY()).getName() == "door") 
		{
			isNear = true;
			if (WorldModel.getPlayer().getKeyCount() >= 1)
			{
				WorldModel.getCurrentRoom().openDoor(WorldModel.getPlayer().getPosX()+1, WorldModel.getPlayer().getPosY());
				player.useKey();
			}
			else
			{
				System.out.println("Need key");
			}
		}
		if(WorldModel.getCurrentRoom().getTileAt(WorldModel.getPlayer().getPosX()-1, WorldModel.getPlayer().getPosY()).getName() == "door") 
		{
			isNear = true;
			if (WorldModel.getPlayer().getKeyCount() >= 1)
			{
				WorldModel.getCurrentRoom().openDoor(WorldModel.getPlayer().getPosX()-1, WorldModel.getPlayer().getPosY());
				player.useKey();
			}
			else
			{
				System.out.println("Need key");
			}
		}
		if(WorldModel.getCurrentRoom().getTileAt(WorldModel.getPlayer().getPosX(), WorldModel.getPlayer().getPosY() + 1).getName() == "door") 
		{
			isNear = true;
			if (WorldModel.getPlayer().getKeyCount() >= 1)
			{
				WorldModel.getCurrentRoom().openDoor(WorldModel.getPlayer().getPosX(), WorldModel.getPlayer().getPosY() + 1);
				player.useKey();
			}
			else
			{
				System.out.println("Need key");
			}
		}
		if(WorldModel.getCurrentRoom().getTileAt(WorldModel.getPlayer().getPosX(), WorldModel.getPlayer().getPosY() - 1).getName() == "door") 
		{
			isNear = true;
			if (WorldModel.getPlayer().getKeyCount() >= 1)
			{
				WorldModel.getCurrentRoom().openDoor(WorldModel.getPlayer().getPosX(), WorldModel.getPlayer().getPosY() - 1);
				player.useKey();
			}
			else
			{
				System.out.println("Need key");
			}
		}
		
		return isNear;
	}
	
	/**
	 * Purpose: check if a player is next to a chest
	 * @param itemTaken
	 * @return
	 */
	public static boolean playerNextToChest(boolean itemTaken)
	{
		boolean isNear = false;
		
		// uses a similar format to playerNextToEnemy method
		if (WorldModel.getCurrentRoom().getTileAt(WorldModel.getPlayer().getPosX()+1, WorldModel.getPlayer().getPosY()).getName() == "chest")
		{
			isNear = true;
			// only pick up the item if you pass true as the argument
			if (itemTaken) {
				currentRoom.pickUpItem(WorldModel.getPlayer().getPosX()+1, WorldModel.getPlayer().getPosY());
			}
		}
		else if (WorldModel.getCurrentRoom().getTileAt(WorldModel.getPlayer().getPosX()-1, WorldModel.getPlayer().getPosY()).getName() == "chest") 
		{
			isNear = true;
			// only pick up the item if you pass true as the argument
			if (itemTaken) {
				currentRoom.pickUpItem(WorldModel.getPlayer().getPosX()-1, WorldModel.getPlayer().getPosY());
			}
 
		}
		else if (WorldModel.getCurrentRoom().getTileAt(WorldModel.getPlayer().getPosX(), WorldModel.getPlayer().getPosY() + 1).getName() == "chest") 
		{
			isNear = true;
			// only pick up the item if you pass true as the argument
			if (itemTaken) {
				currentRoom.pickUpItem(WorldModel.getPlayer().getPosX(), WorldModel.getPlayer().getPosY()+1);
			}

		}
		else if (WorldModel.getCurrentRoom().getTileAt(WorldModel.getPlayer().getPosX(), WorldModel.getPlayer().getPosY() - 1).getName() == "chest")
		{
			isNear = true;
			// only pick up the item if you pass true as the argument
			if (itemTaken) {
				currentRoom.pickUpItem(WorldModel.getPlayer().getPosX(), WorldModel.getPlayer().getPosY()-1);
			}

		}
		
		return isNear;		
	}
	
	/**
	 * Purpose: pick up an item
	 * @param player
	 * @param posX
	 * @param posY
	 */
	public static void pickUpItem(Player player, int posX, int posY)
	{
		switch(WorldModel.currentRoom.getTileAt(player.getPosX(), player.getPosY()).getName())
		{
		case "key":
			player.pickUpKey();
			break;
		}
		if (playerNextToChest(false)) {
			Item item = currentRoom.takeItemFromChest();
			if (item instanceof Weapon) {
				player.equipWeapon(item);
				playerNextToChest(true);
			}
			else if (item instanceof Armor) {
				player.equipArmor(item);
				playerNextToChest(true);
			}
			else {
				if (item.getName() == "key") {
					player.pickUpKey();
					playerNextToChest(true);
				}
				else {
					player.addItem(item);
					playerNextToChest(true);
				}
			}
		}
	}
	
	/**
	 * Purpose: check if an npc is near the player
	 * @return boolean
	 */
	public boolean isNPCAtPlayer()
	{
		for (NPC npc : currentRoom.getNPCs())
		{
			if (playerNextToNPC(npc))
			{
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Purpose: get the npc at the player
	 * @return
	 */
	public NPC getNPCAtPlayer()
	{
		for (NPC npc : currentRoom.getNPCs())
		{
			if (playerNextToNPC(npc))
			{
				return npc;
			}
		}
		
		return null;
	}
	
	/**
	 * Purpose: check if a player is near npc
	 * @param npc
	 * @return
	 */
	public boolean playerNextToNPC(NPC npc)
	{
		boolean isNear = false;
		
		if (npc.getPosX() + 1 == player.getPosX() && npc.getPosY() == player.getPosY())
		{
			isNear = true;
			return isNear;
		}
		else if (npc.getPosX() - 1 == player.getPosX() && npc.getPosY() == player.getPosY()) 
		{
			isNear = true;
			return isNear;
		}
		else if (npc.getPosX() == player.getPosX() && npc.getPosY() + 1 == player.getPosY()) 
		{
			isNear = true;
			return isNear;
		}
		else if (npc.getPosX() == player.getPosX() && npc.getPosY() - 1 == player.getPosY())
		{
			isNear = true;
			return isNear;
		}
		return isNear;
	}
	
	/**
	 * Purpose: get the current level
	 * @return
	 */
	public LevelDesign getLevel() {
		return level;
	}
	
	/**
	 * Purpose: move to the next room
	 */
	public void nextLevel() {
		int levelsCompleted = player.getLevelsCompleted();
		currentRoom = level.LEVELARRAY[levelsCompleted + 1];
		player.setPosition(currentRoom.getXStartPos(), currentRoom.getYStartPos());
		player.levelCompleted();
	}
}