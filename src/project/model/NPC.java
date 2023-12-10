package project.model;

public class NPC extends Organism {
    private Characters character;

    public NPC(Characters name, int posX, int posY, int health) {
        super("NPC", posX, posY, health);
        this.character = name;
    }

    public Characters getCharacter() {
        return character;
    }

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