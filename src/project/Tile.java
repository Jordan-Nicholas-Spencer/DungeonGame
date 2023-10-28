package project;

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
 * Version/date: 10/27/2023
 * 
 * Responsibilities of class: Define what a tile will be in the general case
 * 
 */

public class Tile {
	private String type;
	private int positionX;
	private int positionY;
	private boolean collectible;
	
	/**
	 * Purpose: Creates a Tile
	 * @param name - The registry name of the tile
	 * @param posX - X coordinate in the floor
	 * @param posY - Y coordinate in the floor
	 */
	public Tile(String name, int posX, int posY) {
		this.type = name;
		this.positionX = posX;
		this.positionY = posY;
		if(name == "key" || name == "potion")
			this.collectible = true;
	}
	
	/**
	 * Purpose: return Tile type
	 * @return String type
	 */
	public String getName() {
		return type;
	}
	
	/**
	 * Purpose: return Tile's x-position
	 * @return int positionX
	 */
	public int getPosX() {
		return positionX;
	}
	
	/**
	 * Purpose: return Tile's y-position
	 * @return int positionY
	 */
	public int getPosY() {
		return positionY;
	}
	
	/**
	 * Purpose: determine if Tile is a collectible
	 * @return boolean collectible
	 */
	public boolean isCollectible() {
		return collectible;
	}
}
