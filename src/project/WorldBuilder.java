package project;

import java.awt.Graphics;

public class WorldBuilder {
	
	private Player player;
	private LevelDesign level;
	private Room currentRoom;
	
	public WorldBuilder() {
		level = new LevelDesign();
		currentRoom = level.LEVEL1;
		player = new Player ("player", 9, 13);
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public void movePlayer(int dirX, int dirY) {
		player.setPosition(dirX, dirY);
	}
	
	public Room getCurrentRoom() {
		return currentRoom;
	}
	
	public LevelDesign getLevel() {
		return level;
	}
	
	public void setLevel(LevelDesign newlevel) {
		level = newlevel;
	}
}
