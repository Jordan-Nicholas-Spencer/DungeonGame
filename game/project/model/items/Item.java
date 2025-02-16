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
 * Responsibilities of class: defines items for the player's inventory
 * 
 */

public class Item {

	private String name;
	private String description;
	
	//constructor
	public Item(String name, String description) {
		this.name = name;
		this.description = description;
	}
	
	/**
	 * gets the name of the item
	 * @return name of the item
	 */
	public String getName() { 
		return name;
	}
	 /**
	  * gets the description of the item
	  * @return the description of the item
	  */
	public String getDescription() {
		return description;
	}
	
}