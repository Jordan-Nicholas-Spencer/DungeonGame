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
 * Responsibilities of class: Defines NPC objects and defines all NPCs that shall be used in the game through enumeration
 * 
 */

public class NPC extends Organism {
    private Characters character;

    // constructor
    public NPC(Characters name, int posX, int posY, int health) {
        super("NPC", posX, posY, health);
        this.character = name;
    }

    /**
	 * gets a NPC from the Character enumeration
	 * @return a character (NPC)
	 */
    public Characters getCharacter() {
        return character;
    }    

    /**
	 * allows an NPC to be wailed on for target practice
	 * @param a value for the organisms maxHP
	 */
    
    /**
	 * Enumeration that contains all of the NPCs to be used in the game
	 */
    public enum Characters {
        PRISONER("Mysterious Prisoner", "Yo what's up loser");
        // Add more characters as needed

        private String name;
        private String dialogue;

        Characters(String name, String dialogue) {
            this.name = name;
            this.dialogue = dialogue;
        }

        public String getName() {
            return name;
        }
        
        public String getDialogue()
        {
        	return dialogue;
        }
    }
}