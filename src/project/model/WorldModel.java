package project.model;

import java.util.Random;

import project.view.Images;

//this is the model which handles data logic
public class WorldModel {
	
	private static Player player;
	private static Room currentRoom;
	private static Enemy[] currentEnemies;
	private Random random;
	private LevelDesign level;
	
	
	public WorldModel() {
		random = new Random();
		level = new LevelDesign();
		currentRoom = level.LEVEL1;
		player = new Player ("player", 9, 2);
		currentEnemies = currentRoom.getEnemies();
	}
	
	public static void initializeWorld() {
		Images.initializeSprites();
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
			switch(random.nextInt(4)) {
			case 0:
				if(currentRoom.enemyInRoom(enemy.getPosX() + 1, enemy.getPosY())) {
					return;
				}
				else if(enemy.getPosX() + 1 == player.getPosX() && enemy.getPosY() == player.getPosY()) {
					// fight player
					break;
				}	
				if(getTileInFront(enemy, 1, 0).getName() == "floor") {
					enemy.setPosition(enemy.getPosX() + 1, enemy.getPosY());
					break;
				}
			case 1:
				if(currentRoom.enemyInRoom(enemy.getPosX() - 1, enemy.getPosY())) {
					return;
				}
				else if(enemy.getPosX() - 1 == player.getPosX() && enemy.getPosY() == player.getPosY()) {
					// fight player
					break;
				}	
				if(getTileInFront(enemy, -1, 0).getName() == "floor") {
					enemy.setPosition(enemy.getPosX() - 1, enemy.getPosY());
					break;
				}
			case 2:
				if(currentRoom.enemyInRoom(enemy.getPosX(), enemy.getPosY() + 1)) {
					return;
				}
				else if(enemy.getPosX() == player.getPosX() && enemy.getPosY() + 1 == player.getPosY()) {
					// fight player
					break;
				}	
				if(getTileInFront(enemy, 0, 1).getName() == "floor") {
					enemy.setPosition(enemy.getPosX(), enemy.getPosY() + 1);
					break;
				}
			case 3:
				if(currentRoom.enemyInRoom(enemy.getPosX(), enemy.getPosY() - 1)) {
					return;
				}
				else if(enemy.getPosX() == player.getPosX() && enemy.getPosY() - 1 == player.getPosY()) {
					// fight player
					break;
				}	
				if(getTileInFront(enemy, 0, -1).getName() == "floor") {
					enemy.setPosition(enemy.getPosX(), enemy.getPosY() - 1);
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
