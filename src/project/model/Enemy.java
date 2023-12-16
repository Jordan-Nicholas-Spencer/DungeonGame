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
 * Responsibilities of class: Defines enemy objects and defines all enemies that shall be used in the game through enumeration
 * 
 */

public class Enemy extends Organism {

	private Species species;
	
	// constructor
	public Enemy(Species species, int posX, int posY) {
		super(species.getName(), posX, posY, species.getMaxHp());
		this.strength = species.getStr();
		this.defense = species.getDef();
		this.species = species;
	}
	
	/**
	 * gets an enemy from the Species enumeration
	 * @return a Species
	 */
	public Species getSpecies() {
		return species;
	}

	/**
	 * Enumeration that contains all of the enemies to be used in the game
	 */
	public enum Species {
		BAT("bat", 5, 3, 2),
		RAT("rat", 5, 1, 0),		
		GARGOYLE("gargoyle", 10, 5, 5),
		SLIME("slime", 8, 2, 5),
		SUCCUBUS("succubus", 15, 7, 7),
		VAMPIRE("vampire", 50, 50, 0);
		
		private String name;
		private int maxHP;
		private int str;
		private int def;
		
		Species(String name, int maxHP, int str, int def) {
			this.name = name;
			this.maxHP = maxHP;
			this.str = str;
			this.def = def;
		}

		public String getName() {
			return name;
		}

		public int getMaxHp() {
			return maxHP;
		}

		public int getStr() {
			return str;
		}

		public int getDef() {
			return def;
		}
	}

}