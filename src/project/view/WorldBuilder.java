package project.view;


import java.awt.Color;


import java.awt.Font;
import java.awt.Graphics;

import java.awt.image.BufferedImage;

import javax.swing.JPanel;
import javax.swing.JTextArea;

import project.model.Enemy;
import project.model.Player;
import project.model.Room;
import project.model.WorldModel;
import project.model.items.Item;

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
 * Responsibilities of class: Handles logic for drawing images using data passed from Panel
 * 
 */

public class WorldBuilder extends JPanel {
	
	private final int SIZE = 32; // 32 x 32 - this program's standard sprite size
	private final int SCALE = 48; // 32 * 1.5
	private final int STEP = 16; // 48 / 3 - for 3 frames of movement between tiles
	private final int MULT = 1; // optional scalar for zooming in
	
	private SpriteSheetReader ssr = new SpriteSheetReader();
	private BufferedImage sprite;
	private BufferedImage floor;
	private BufferedImage wall;

	/**
	 * renders the room one sprite image at a time, but refrains from rendering the player or enemies
	 * @param room
	 * @param player
	 * @param g - graphics
	 */
	public void renderLevel(Room room, Player player, Graphics g) {
		// this sets the base image for floor and wall to be used throughout the room
		// floor tiles 512 x 384 / 32 = 16 x 12
		floor = ImageLoader.getSprite("floor");
		ssr.setImage(floor);
		floor = ssr.grabImage(0, 6, SIZE, SIZE);
		// wall tiles 512 x 480 / 32 = 16 x 15
		wall = ImageLoader.getSprite("wall");
		ssr.setImage(wall);
		wall = ssr.grabImage(8, 3, SIZE, SIZE);

		
		String name;
		for(int column=0; column < room.getSizeY(); column++) {
			for(int row=0; row < room.getSizeX(); row++) {
				name = room.getTileAt(row, column).getName();
				sprite = ImageLoader.getSprite(name);
				
				int drawPosX = room.getTileAt(row, column).getPosX() * (SCALE * MULT) + ((Window.WIDTH/2) - player.getPosX() * (SCALE * MULT) - SCALE);
				int drawPosY = room.getTileAt(row, column).getPosY() * (SCALE * MULT) + ((Window.HEIGHT/2) - player.getPosY() * (SCALE * MULT) - SCALE);
				
				if (name == "stairs" || name == "chest" || name == "door" || name == "gate" || name == "open") {
					g.drawImage(floor, drawPosX, drawPosY, SCALE * MULT, SCALE * MULT, null);
				}
				else if (name == "skeleton") {
					g.drawImage(wall, drawPosX, drawPosY, SCALE * MULT, SCALE * MULT, null);
				}
				
				if (name == "floor") {
					g.drawImage(floor, drawPosX, drawPosY, SCALE * MULT, SCALE * MULT, null);
				}
				else if (name == "wall") {
					g.drawImage(wall, drawPosX, drawPosY, SCALE * MULT, SCALE * MULT, null);
				}
				else {
					g.drawImage(sprite, drawPosX, drawPosY, SCALE * MULT, SCALE * MULT, null);
				}
				
			}
		}
	}
	
	 /**
	  * renders the player based on the direction they are facing
	  * @param player
	  * @param g - graphics
	  */
	public void renderPlayer(Player player, Graphics g) {
		int c, r;
		
		sprite = ImageLoader.getSprite("player");
		ssr.setImage(sprite);
		
		switch(player.getFacing()) {
		case "down":
			c = 1; r = 0;
			sprite = ssr.grabImage(c, r, SIZE, SIZE);
			break;
		case "left":
			c = 1; r = 1;
			sprite = ssr.grabImage(c, r, SIZE, SIZE);
			break;
		case "right":
			c = 1; r = 2;
			sprite = ssr.grabImage(c, r, SIZE, SIZE);
			break;
		case "up":
			c = 1; r = 3;
			sprite = ssr.grabImage(c, r, SIZE, SIZE);
			break;
		}
		
		int drawPosX = (Window.WIDTH / 2) - SCALE;
		int drawPosY = (Window.HEIGHT / 2) - SCALE;
		
		g.drawImage(sprite, drawPosX, drawPosY, SCALE * MULT, SCALE * MULT, null);
	}
	
	/**
	 * renders the enemy based on the direction they are facing
	 * @param enemies
	 * @param player
	 * @param g - graphics
	 */
	public void renderEnemy(Enemy[] enemies, Player player, Graphics g) {
		int c, r;
		
		for (Enemy enemy : enemies) {
			sprite = ImageLoader.getSprite(enemy.getName());
			ssr.setImage(sprite);
			
			switch(enemy.getFacing()) {
			case "down":
				c = 1; r = 0;
				sprite = ssr.grabImage(c, r, SIZE, SIZE);
				break;
			case "left":
				c = 1; r = 1;
				sprite = ssr.grabImage(c, r, SIZE, SIZE);
				break;
			case "right":
				c = 1; r = 2;
				sprite = ssr.grabImage(c, r, SIZE, SIZE);
				break;
			case "up":
				c = 1; r = 3;
				sprite = ssr.grabImage(c, r, SIZE, SIZE);
				break;
			}
			
			int drawPosX = enemy.getPosX() * (SCALE * MULT) + ((Window.WIDTH/2) - player.getPosX() * (SCALE * MULT) - SCALE);
			int drawPosY = enemy.getPosY() * (SCALE * MULT) + ((Window.HEIGHT/2) - player.getPosY() * (SCALE * MULT) - SCALE);
			
			g.drawImage(sprite, drawPosX, drawPosY, SCALE * MULT, SCALE * MULT, null);
		}
	}
	 /**
	  * renders the gameOverScreen
	  * @param player
	  * @param g - graphics
	  */
	public void renderGameOver(Player player, Graphics g) {
		
		if (player.getHealth() <= 0) {
			int x = (Window.WIDTH / 2) - SCALE * 5;
			int y = (Window.HEIGHT/ 2) - SCALE * 4;
			int width = SCALE * 10;
			int height = SCALE * 5;
			
			g.setColor(Color.BLACK);
			g.fillRoundRect(x, y, width, height, SCALE, SCALE);
			g.setColor(Color.WHITE);
			g.drawRoundRect(x, y, width, height, SCALE, SCALE);
			
			g.setFont(new Font("Dialog", Font.PLAIN, 20));
			g.drawString("Floors cleared: " + player.getLevelsCompleted(), x + SCALE / 2, y + SCALE * 2);
			g.drawString("Press any key to restart the game.", x + SCALE / 2, y + SCALE * 2 + SCALE / 2);
			
			g.setColor(Color.RED);
			g.setFont(new Font("Dialog", Font.PLAIN, 40));
			g.drawString("GAME OVER", x + SCALE * 2 + SCALE / 2, y + SCALE);
		}

	}
	
	/**
	 * renders the HUD with basic player stats
	 * @param player
	 * @param g - graphics
	 */
	public void renderHUD(Player player, Graphics g) {
		int x = SCALE / 2;
		int y = SCALE / 2;
		int width = SCALE * 2;
		int height = SCALE * 2;
		
		Color c = new Color(0, 0, 0, 150);	// 150 setsOpacity
		g.setColor(c);
		g.fillRoundRect(x, y, width, height, SCALE / 2, SCALE / 2);
		g.setColor(Color.WHITE);
		g.drawRoundRect(x, y, width, height, SCALE / 2, SCALE / 2);
		
		g.setFont(new Font("Dialog", Font.PLAIN, 20));
		g.drawString("- HUD -", x + 14, y + 20);
		g.setFont(new Font("Dialog", Font.PLAIN, 12));
		g.drawString("HP: " + player.getHealth() + "/" + player.getMaxHP() , x + 5, y + 40);
	}
	
	/**
	 * renders the dialogue window when talking to an NPC
	 * @param g - graphics
	 */
	public void renderDialogueWindow(Graphics g) {
		int x = (Window.WIDTH / 2) - SCALE - STEP;
		int y = (Window.HEIGHT / 2) + SCALE;
		int width = SCALE * 10;
		int height = SCALE * 4;
		String str = WorldModel.dialogueText.get(0);
		
		drawWrappedText(str, x, y, width, height, g);
	}
	
	/**
	 * used with renderDialogueWindow in order to wrap long winded NPCs
	 * @param text
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 * @param g
	 */
	public void drawWrappedText(String text, int x, int y, int w, int h, Graphics g) {
        JTextArea ta = new JTextArea(text);
        ta.setLineWrap(true);
        ta.setWrapStyleWord(true);
        ta.setBounds(0, 0, w, h);
        ta.setBackground(new Color(0,0,0));
        ta.setForeground(g.getColor());
        ta.setFont(g.getFont());
        Graphics g2 = g.create(x, y, w, h); // Use new graphics to leave original graphics state unchanged
        ta.paint(g2);
    }
	
	/**
	 * renders the chestWindow which shows what is inside a chest
	 * @param b - boolean
	 * @param g
	 */
	public void renderChestWindow(Graphics g)
	{
		int x = (Window.WIDTH / 2) - SCALE - STEP;
		int y = (Window.HEIGHT / 2) + SCALE;
		int width = SCALE * 6;
		int height = SCALE * 2;
		
		String content = "";
		Item[] itemArray = WorldModel.getCurrentRoom().getChestItems();
		content = itemArray[0].getName() + ": " + itemArray[0].getDescription();
		Color c = new Color(0, 0, 0);
		g.setColor(c);
		g.fillRoundRect(x, y, width, height, SCALE, SCALE);
		g.setColor(Color.WHITE);
		g.drawRoundRect(x, y, width, height, SCALE / 2, SCALE / 2);
		g.drawString(content, x + 30, y + 50);
	}
	
	/**
	 * renders the inventoryWindow
	 * @param player
	 * @param g - graphics
	 */
	public void renderInventoryWindow(Player player, Graphics g)
	{
		int x = 10;
	    int y = 10;
	    int width = Window.WIDTH - 200;
	    int height = Window.HEIGHT - 100;
	    String str = "Inventory";

	    Color c = new Color(0, 0, 0, 225);
	    g.setColor(c);
	    g.fillRoundRect(x, y, width, height, SCALE, SCALE);
	    g.setColor(Color.WHITE);
	    g.drawRoundRect(x, y, width, height, SCALE / 2, SCALE / 2);

	    int cellWidth = width / 2;
	    int cellHeight = 100;
	    int tableX = x + 10;
	    int tableY = y + 60;

	    g.drawLine(tableX, tableY, tableX + width - 20, tableY);

	    // draw horizontal lines for each row on the right half
	    for (int i = 0; i < 4; i++) {
	        int rowY = tableY + i * cellHeight;
	        g.drawLine(tableX + cellWidth, rowY, tableX + width - 20, rowY);
	    }

	    // draw vertical line separating the two halves
	    int columnX = tableX + cellWidth;
	    g.drawLine(columnX, tableY, columnX, tableY + height - 60);

	    //display stats on the left half
	    String weaponName;
	    String armorName;
	    
	    if (player.getWeapon() != null)
	    {
	    	weaponName = player.getWeapon().getName();
	    } else
	    {
	    	weaponName = "None equipped.";
	    }
	    
	    if (player.getArmor() != null)
	    {
	    	armorName = player.getArmor().getName();
	    } else
	    {
	    	armorName = "None equipped.";
	    }
	    
	    g.drawString("Player Stats", tableX + 10, tableY + 20);
	    g.drawString("Weapon: " + weaponName, tableX + 10, tableY + 40);
	    g.drawString("Armor: " + armorName, tableX + 10, tableY + 60);
	    g.drawString("Defense: " + player.getDefense(), tableX + 10, tableY + 80);
	    g.drawString("Strength: " + player.getStrength(), tableX + 10, tableY + 100);
	    g.drawString("Keys: " + player.getKeyCount(), tableX + 10, tableY + 120);

	    // display items on the right half
	    g.drawString("Item Inventory", tableX + cellWidth + 10, tableY + 20);

	    for (int i = 0; i < player.getInventorySize(); i++) {
	        String itemName = player.getInventoryItem(i).getName();
	        g.drawString(itemName, tableX + cellWidth + 10, tableY + (i + 1) * cellHeight + 20);
	    }
	}
}