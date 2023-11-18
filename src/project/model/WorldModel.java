package project.model;

import java.util.Random;

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
 * Version/date: 11/18/2023
 * 
 * Responsibilities of class: Handles the data logic of the program
 * 
 */

public class WorldModel {
	
	private static Player player;
	private static Room currentRoom;
	private static Enemy[] currentEnemies;
	private Random random;
	private LevelDesign level;
	
	
	public WorldModel() {
		random = new Random();
		level = new LevelDesign();
		currentRoom = LevelDesign.LEVEL1;
		player = new Player ("player", 9, 2);
		currentEnemies = currentRoom.getEnemies();
	}
	
	public static void initializeWorld() {
		ImageLoader.initializeSprites();
	}
	
	public static Player getPlayer() {
		return player;
	}
	
	private static Tile getTileInFront(Organism organism, int dirX, int dirY) {
		return currentRoom.getTileAt(organism.getPosX() + dirX, organism.getPosY() + dirY);
	}
	
	public static Room getCurrentRoom() {
		return currentRoom;
	}
	
	public static Enemy[] getCurrentEnemies() {
		return currentEnemies;
	}
	
	public void movePlayer(int dirX, int dirY) {
		switch(getTileInFront(player, dirX, dirY).getName()) {
		case "floor":
			player.setPosition(player.getPosX() + dirX, player.getPosY() + dirY);
			break;
		case "open":
			player.setPosition(player.getPosX() + dirX, player.getPosY() + dirY);
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
			break;
		case "door":
			break;
		default:
			break;
		}
		moveEnemies();
	}
	
	public void moveEnemies() {
		for (Enemy enemy : currentEnemies) {
			String name;
			switch(random.nextInt(4)) {
			case 0:
				name = getTileInFront(enemy, 1, 0).getName();
				if(currentRoom.enemyInRoom(enemy.getPosX() + 1, enemy.getPosY())) {
					return;
				}
				else if(enemy.getPosX() + 1 == player.getPosX() && enemy.getPosY() == player.getPosY()) {
					// fight player
					break;
				}	
				if(name == "floor" || name == "open") {
					enemy.setPosition(enemy.getPosX() + 1, enemy.getPosY());
					enemy.setFacing("right");
					break;
				}
			case 1:
				name = getTileInFront(enemy, -1, 0).getName();
				if(currentRoom.enemyInRoom(enemy.getPosX() - 1, enemy.getPosY())) {
					return;
				}
				else if(enemy.getPosX() - 1 == player.getPosX() && enemy.getPosY() == player.getPosY()) {
					// fight player
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
				else if(enemy.getPosX() == player.getPosX() && enemy.getPosY() + 1 == player.getPosY()) {
					// fight player
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
				else if(enemy.getPosX() == player.getPosX() && enemy.getPosY() - 1 == player.getPosY()) {
					// fight player
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
		
	public LevelDesign getLevel() {
		return level;
	}
	
	public void setLevel(LevelDesign newlevel) {
		level = newlevel;
	}
}
