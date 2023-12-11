package project.model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

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
	
	public static String getDialogueText() {
		return dialogueText.get(0);
	}
	
	public static void exhaustDialogue()
	{
		dialogueText.remove(0);
	}
	
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
	
	
	public void initializeImages() {
		ImageLoader.initializeSprites();
	}
	
	public static Player getPlayer() {
		return player;
	}
	
	public static Tile getTileInFront(Organism organism, int dirX, int dirY) {
		return currentRoom.getTileAt(organism.getPosX() + dirX, organism.getPosY() + dirY);
	}
	
	public static Room getCurrentRoom() {
		return currentRoom;
	}
	
	public void movePlayer(int dirX, int dirY) {
		boolean playerMoved = false;
		if (WorldController.getIsInventoryWindowActive()) {
			return;
		}
		if (WorldController.getIsChestWindowActive()) {
			return;
		}
		if (WorldController.getIsDialogueActive()) {
			return;
		}
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
				nextLevel();
				WorldController.setDialogueExhausted(false);
				break;
			case "door":			
				break;
			default:
				break;
			}
			if (playerMoved == true) {
				moveEnemies();
			}
		}
	}
	
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
	
	
	
	public static void openDoor(int x, int y)
	{
		WorldModel.getCurrentRoom().openDoor(x, y);
		WorldModel.getPlayer().useKey();
	}
	
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
	
	public static boolean playerNextToDoor()
	{
		boolean isNear = false;
		
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
	
	public static boolean playerNextToChest(boolean itemTaken)
	{
		boolean isNear = false;
		
		if (WorldModel.getCurrentRoom().getTileAt(WorldModel.getPlayer().getPosX()+1, WorldModel.getPlayer().getPosY()).getName() == "chest")
		{
			isNear = true;
			if (itemTaken) {
				currentRoom.pickUpItem(WorldModel.getPlayer().getPosX()+1, WorldModel.getPlayer().getPosY());
			}
		}
		else if (WorldModel.getCurrentRoom().getTileAt(WorldModel.getPlayer().getPosX()-1, WorldModel.getPlayer().getPosY()).getName() == "chest") 
		{
			isNear = true;
			if (itemTaken) {
				currentRoom.pickUpItem(WorldModel.getPlayer().getPosX()-1, WorldModel.getPlayer().getPosY());
			}
 
		}
		else if (WorldModel.getCurrentRoom().getTileAt(WorldModel.getPlayer().getPosX(), WorldModel.getPlayer().getPosY() + 1).getName() == "chest") 
		{
			isNear = true;
			if (itemTaken) {
				currentRoom.pickUpItem(WorldModel.getPlayer().getPosX(), WorldModel.getPlayer().getPosY()+1);
			}

		}
		else if (WorldModel.getCurrentRoom().getTileAt(WorldModel.getPlayer().getPosX(), WorldModel.getPlayer().getPosY() - 1).getName() == "chest")
		{
			isNear = true;
			if (itemTaken) {
				currentRoom.pickUpItem(WorldModel.getPlayer().getPosX(), WorldModel.getPlayer().getPosY()-1);
			}

		}
		
		return isNear;		
	}
	
	public static void pickUpItem(Player player, int posX, int posY)
	{
		switch(WorldModel.currentRoom.getTileAt(player.getPosX(), player.getPosY()).getName())
		{
		case "key":
			player.pickUpKey();
			break;
		case "potion":
			
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
	
	public void newMethod() {
		return;
	}
	
	public void testMethod() {
		return;
	}
	
	public LevelDesign getLevel() {
		return level;
	}
	
	public void nextLevel() {
		int levelsCompleted = player.getLevelsCompleted();
		currentRoom = level.LEVELARRAY[levelsCompleted + 1];
		player.setPosition(currentRoom.getXStartPos(), currentRoom.getYStartPos());
		player.levelCompleted();
	}
}