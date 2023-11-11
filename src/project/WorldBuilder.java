package project;

import java.awt.Graphics;

public class WorldBuilder {
	
	private static Player player;
	private static LevelDesign level;
	private static Room currentRoom;
	
	public WorldBuilder() {
		level = new LevelDesign();
		currentRoom = level.LEVEL1;
		player = new Player ("player", 9, 13);
	}
	
	public void renderPlayer() {
		
	}
	
	public static Player getPlayer() {
		return player;
	}
	
	public static Room getCurrentRoom() {
		return currentRoom;
	}
	
	public static LevelDesign getLevel() {
		return level;
	}
	
	public static void setLevel(LevelDesign newlevel) {
		level = newlevel;
	}
}
