package project.view;

import java.awt.Graphics;

import java.awt.image.BufferedImage;

import project.model.Enemy;
import project.model.Player;
import project.model.Room;

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
 * Version/date: 11/18/2023
 * 
 * Responsibilities of class: Handles logic for drawing images using data passed from Panel
 * 
 */

public class WorldBuilder {
	
	private final int SIZE = 32; // 32 x 32 - this program's standard sprite size
	private final int SCALE = 48; // 32 * 1.5
	private final int STEP = 16; // 48 / 3 - for 3 frames of movement between tiles
	private final int MULT = 1; // optional scalar for zooming in
	
	SpriteSheetReader ssr = new SpriteSheetReader();
	private String name;

	public void renderLevel(Room room, Player player, Graphics g) {
		for(int column=0; column < room.getSizeY(); column++) {
			for(int row=0; row < room.getSizeX(); row++) {
				name = room.getTileAt(row, column).getName();
				BufferedImage sprite = ImageLoader.getSprite(name);
				
				int drawPosX = room.getTileAt(row, column).getPosX() * (SCALE * MULT) + ((Window.WIDTH/2) - player.getPosX() * (SCALE * MULT) - sprite.getWidth());
				int drawPosY = room.getTileAt(row, column).getPosY() * (SCALE * MULT) + ((Window.HEIGHT/2) - player.getPosY() * (SCALE * MULT) - sprite.getHeight());
				
				if (name == "stairs" || name == "chest" || name == "door" || name == "gate" || name == "open") {
					g.drawImage(ImageLoader.getSprite("floor"), drawPosX, drawPosY, sprite.getWidth() * MULT, sprite.getHeight() * MULT, null);
				}
				else if (name == "skeleton") {
					g.drawImage(ImageLoader.getSprite("wall"), drawPosX, drawPosY, sprite.getWidth() * MULT, sprite.getHeight() * MULT, null);
				}
				
				g.drawImage(sprite, drawPosX, drawPosY, sprite.getWidth() * MULT, sprite.getHeight() * MULT, null);
			}
		}
	}
	
	public void renderPlayer(Player player, Graphics g) {
		BufferedImage sprite = ImageLoader.getSprite("player");
		ssr.setImage(sprite);
		
		switch(player.getFacing()) {
		case "down":
			sprite = ssr.grabImage(1, 0, SIZE, SIZE);
			break;
		case "left":
			sprite = ssr.grabImage(1, 1, SIZE, SIZE);
			break;
		case "right":
			sprite = ssr.grabImage(1, 2, SIZE, SIZE);
			break;
		case "up":
			sprite = ssr.grabImage(1, 3, SIZE, SIZE);
			break;
		}
		
		int drawPosX = (Window.WIDTH / 2) - SCALE;
		int drawPosY = (Window.HEIGHT / 2) - SCALE;
		
		g.drawImage(sprite, drawPosX, drawPosY, SCALE * MULT, SCALE * MULT, null);
	}
	
	public void renderEnemy(Enemy[] enemies, Player player, Graphics g) {
		for (Enemy enemy : enemies) {
			BufferedImage sprite = ImageLoader.getSprite(enemy.getName());
			ssr.setImage(sprite);
			
			switch(enemy.getFacing()) {
			case "down":
				sprite = ssr.grabImage(1, 1, SIZE, SIZE);
				break;
			case "left":
				sprite = ssr.grabImage(1, 0, SIZE, SIZE);
				break;
			case "right":
				sprite = ssr.grabImage(1, 2, SIZE, SIZE);
				break;
			case "up":
				sprite = ssr.grabImage(1, 3, SIZE, SIZE);
				break;
			}
			
			int drawPosX = enemy.getPosX() * (SCALE * MULT) + ((Window.WIDTH/2) - player.getPosX() * (SCALE * MULT) - SCALE);
			int drawPosY = enemy.getPosY() * (SCALE * MULT) + ((Window.HEIGHT/2) - player.getPosY() * (SCALE * MULT) - SCALE);
			
			g.drawImage(sprite, drawPosX, drawPosY, SCALE * MULT, SCALE * MULT, null);
		}
	}
	
	/**
	 * Alters the picture so that it displays the a mirror image
	 * source: GeeksForGeeks
	 * @param the image to be altered
	 */
	private BufferedImage mirrorImage(BufferedImage image) {		
		int height = image.getHeight();
		int width = image.getWidth();
		BufferedImage mirrored = new BufferedImage(width, height, image.getType());
		
		for (int y = 0; y < height; y++) {
			for (int lx = 0, rx = width - 1; lx < width; lx++, rx--) {
				 int p = image.getRGB(lx, y); 
				  
	                // set mirror image pixel value 
	                mirrored.setRGB(rx, y, p); 
			}
		}
		return mirrored;
	}
}
