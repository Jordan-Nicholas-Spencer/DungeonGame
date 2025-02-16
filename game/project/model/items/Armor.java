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

public class Armor extends Item {

	private int defense;
	
	// constructor
	public Armor(String name, String description, int defense) {
		super(name, description);
		this.defense = defense;
	}

	/**
	 * gets the defense value of the armor
	 * @return defense value
	 */
	public int getDefense() {
		return defense;
	}
	
}