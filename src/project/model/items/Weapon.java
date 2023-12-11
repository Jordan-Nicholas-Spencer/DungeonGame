package project.model.items;

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
 * Responsibilities of class: defines armor for equipping to player
 * 
 */

public class Weapon extends Item {

	private int strength;
	
	// constructor
	public Weapon(String name, String description, int strength) {
		super(name, description);
		this.strength = strength;
	}
	 /**
	  * gets the strength of the weapon
	  * @return the strength value of the weapon
	  */
	public int getStrength() {
		return strength;
	}
	
}