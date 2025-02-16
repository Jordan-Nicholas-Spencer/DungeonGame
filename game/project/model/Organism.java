package project.model;

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
 * Responsibilities of class: Create a "living" tile
 * 
 */

// it is not required for an abstract class to have abstract methods
public abstract class Organism extends Tile {

	private String facing;
	protected int health;
	protected int maxHP; 
	protected int strength;
	protected int defense;

	// constructor
	public Organism(String type, int posX, int posY, int health) {
		super(type, posX, posY);
		this.facing = "down";
		this.maxHP = health;
		this.health = health;
	}
	
	/**
	 * sets position of organism
	 */
	public void setPosition(int dirX, int dirY) {
		this.positionX = dirX;
		this.positionY = dirY;
	}
	
	/**
	 * sets the direction that the organism is facing
	 */
	public void setFacing(String direction) {
		this.facing = direction;
	}
	
	/**
	 * gets the direction the the organism is facing
	 * @return the direction the organism is facing
	 */
	public String getFacing() {
		return this.facing;
	}

	/**
	 * gets the current health of the organism
	 * @return current health of the organism
	 */
	public int getHealth() {
		return health;
	}
	
	/**
	 * gets the maximum health allowed for the organism
	 * @return the maximum health allowed for the organism
	 */
	public int getMaxHP() {
		return maxHP;
	}
	
	/**
	 * gets the current strength of the organism
	 * @return the current strength of the organism
	 */
	public int getStrength() {
		return strength;
	}

	/**
	 * sets the strength of the organism
	 * @param a strength value
	 */
	public void setStrength(int strength) {
		this.strength = strength;
	}

	/**
	 * gets the current defense of the organism
	 * @return the current defense of the organism
	 */
	public int getDefense() {
		return defense;
	}

	/**
	 * sets the defense of the organism
	 * @param a defense value
	 */
	public void setDefense(int defense) {
		this.defense = defense;
	}
	
	/**
	 * reduces the health of an organism
	 * @param an amount of health
	 */
	public void damage(int amount) {
		if(amount <= 0) amount = 1;
		this.health -= amount;
	}	
	
	/**
	 * increases the health of an organism
	 * @param an amount of health
	 */
	public void heal(int amount) {
		this.health += amount;
		// health can not be higher than maxHP
		if(health>maxHP)
			this.health = this.maxHP;
	}
	

}