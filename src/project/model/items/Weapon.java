package project.model.items;

public class Weapon extends Item {

	private int strength;
	
	public Weapon(String name, String description, int strength) {
		super(name, description);
		this.strength = strength;
	}
	
	public int getStrength() {
		return strength;
	}
	
}