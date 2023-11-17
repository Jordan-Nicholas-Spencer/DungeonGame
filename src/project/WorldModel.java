package project;

//this is the model which handles data logic
public class WorldModel {
	
	private static Player player;
	private static Room currentRoom;
	private LevelDesign level;
	
	public WorldModel() {
		level = new LevelDesign();
		currentRoom = level.LEVEL1;
		player = new Player ("player", 9, 2);
	}
	
	public static void initializeWorld() {
		Images.initializeSprites();
	}
	
	public static Player getPlayer() {
		return player;
	}
	
	public static Room getCurrentRoom() {
		return currentRoom;
	}
	
	public void movePlayer(int dirX, int dirY) {
		player.setPosition(player.getPosX()+dirX, player.getPosY()+dirY);
	}
		
	public LevelDesign getLevel() {
		return level;
	}
	
	public void setLevel(LevelDesign newlevel) {
		level = newlevel;
	}
}
