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
 * Version/date: 11/18/2023
 * 
 * Responsibilities of class: Create a "living" tile
 * 
 */

public abstract class Organism extends Tile {

	private String facing;
	protected int health;
	protected int maxHP; 
	protected int strength;
	protected int defense;

	public Organism(String type, int posX, int posY, int health) {
		super(type, posX, posY);
		this.facing = "down";
		this.maxHP = health;
		this.health = health;
	}
	
	public void setPosition(int dirX, int dirY) {
		this.positionX = dirX;
		this.positionY = dirY;
	}
	
	public void setFacing(String direction) {
		this.facing = direction;
	}
	
	public String getFacing() {
		return this.facing;
	}

	public int getHealth() {
		return health;
	}
	
	public int getMaxHP() {
		return maxHP;
	}
	
	public int getStrength() {
		return strength;
	}

	public void setStrength(int strength) {
		this.strength = strength;
	}

	public int getDefense() {
		return defense;
	}

	public void setDefense(int defense) {
		this.defense = defense;
	}
	
	public void heal(int amount) {
		this.health += amount;
		if(health>maxHP)
			this.health = this.maxHP;
	}
	
	public void damage(int amount) {
		if(amount <= 0) amount = 1;
		this.health -= amount;
	}
	
	
}