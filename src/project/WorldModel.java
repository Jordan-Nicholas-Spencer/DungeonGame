package project;

//this is the model which handles data logic
public class WorldModel {
	
	private Player player;
	private LevelDesign level;
	private static Room currentRoom;
	
	public WorldModel() {
		level = new LevelDesign();
		currentRoom = level.LEVEL1;
		player = new Player ("player", 9, 13);
	}
	
	public static void initializeWorld() {
		Images.initializeSprites();
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public void movePlayer(int dirX, int dirY) {
		player.setPosition(player.getPosX() + dirX, player.getPosY() + dirY);
	}
	
	public static Room getCurrentRoom() {
		return currentRoom;
	}
	
	public LevelDesign getLevel() {
		return level;
	}
	
	public void setLevel(LevelDesign newlevel) {
		level = newlevel;
	}
}
