package project.model;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import project.view.ImageLoader;

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
    
    public void interact(Graphics g) {
        BufferedImage sprite = ImageLoader.getSprite("npc");

        g.setColor(Color.BLACK);
        g.fillRoundRect(100, 100, 100, 100, 48, 48);
        g.setColor(Color.WHITE);
        g.drawRoundRect(100, 100, 100, 100, 48, 48);

        g.setFont(new Font("Dialog", Font.PLAIN, 20));
        g.drawString("yo whats up", 100, 100);
    }
}