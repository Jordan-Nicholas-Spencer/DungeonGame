package project;

public class Organism extends Tile{

	protected  int health;
	protected  int maxHP; 
	protected int strength;
	protected int defense;
	
	public Organism(String type, int posX, int posY, int health) {
		super(type, posX, posY);
		this.maxHP = health;
		this.health = health;
	}
	
	public void setPosition(int dirX, int dirY) {
		this.positionX = dirX;
		this.positionY = dirY;
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
