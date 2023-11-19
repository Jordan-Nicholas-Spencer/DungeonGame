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
	private Random random;
	private LevelDesign level;
	
	public WorldModel() {
		random = new Random();
		level = new LevelDesign();
		currentRoom = level.LEVELARRAY[0];
		player = new Player ("player", currentRoom.getXStartPos(), currentRoom.getYStartPos());
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
	
	public void movePlayer(int dirX, int dirY) {
		boolean playerMoved = false;
		if (currentRoom.enemyInRoom(getTileInFront(player, dirX, dirY).getPosX(), getTileInFront(player, dirX, dirY).getPosY())) {
			Enemy enemy = currentRoom.getEnemyAt(getTileInFront(player, dirX, dirY).getPosX(), getTileInFront(player, dirX, dirY).getPosY());
			enemy.damage(player.getStrength() - enemy.getDefense());
			System.out.println("fightEnemy");
			System.out.println("Enemy Health: " + enemy.getHealth());
			System.out.println("Player Health: " + player.getHealth());
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
				break;
			case "door":			
				nextLevel();
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
					System.out.println("fightPlayer");
					System.out.println("Player Health: " + player.getHealth());
					break;
				}	
				// if the player is near the enemy, move towards the player
//				else if() {
//					
//				}
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
					System.out.println("fightPlayer");
					System.out.println("Player Health: " + player.getHealth());
					break;
				}	
				// if the player is near the enemy, move towards the player
//				else if() {
//					
//				}
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
					System.out.println("fightPlayer");
					System.out.println("Player Health: " + player.getHealth());
					break;
				}	
				// if the player is near the enemy, move towards the player
//				else if() {
//					
//				}
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
					System.out.println("fightPlayer");
					System.out.println("Player Health: " + player.getHealth());
					break;
				}	
				// if the player is near the enemy, move towards the player
//				else if() {
//					
//				}
				if(name == "floor" || name == "open") {
					enemy.setPosition(enemy.getPosX(), enemy.getPosY() - 1);
					enemy.setFacing("up");
					break;
				}
			}
		}
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
