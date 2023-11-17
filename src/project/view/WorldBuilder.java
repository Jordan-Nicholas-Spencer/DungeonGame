package project.view;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import project.model.Enemy;
import project.model.Player;
import project.model.Room;


public class WorldBuilder {
	
	private static final int MULT = 1;

	public void renderLevel(Room room, Player player, Graphics g) {
		for(int column=0; column < room.getSizeY(); column++) {
			for(int row=0; row < room.getSizeX(); row++) {
				String name = room.getTileAt(row, column).getName();
				BufferedImage sprite = Images.getSprite(name);
				int drawPosX = room.getTileAt(row, column).getPosX() * (sprite.getWidth() * MULT) + ((Window.WIDTH/2) - player.getPosX() * (sprite.getWidth() * MULT) - sprite.getWidth());
				int drawPosY = room.getTileAt(row, column).getPosY() * (sprite.getHeight() * MULT) + ((Window.HEIGHT/2) - player.getPosY() * (sprite.getHeight() * MULT) - sprite.getHeight());
				if (name == "stairs" || name == "chest" || name == "door" || name == "gate" || name == "open") {
					g.drawImage(Images.getSprite("floor"), drawPosX, drawPosY, sprite.getWidth() * MULT, sprite.getHeight() * MULT, null);
				}
				else if (name == "skeleton") {
					g.drawImage(Images.getSprite("wall"), drawPosX, drawPosY, sprite.getWidth() * MULT, sprite.getHeight() * MULT, null);
				}
				g.drawImage(sprite, drawPosX, drawPosY, sprite.getWidth() * MULT, sprite.getHeight() * MULT, null);
			}
		}
	}
	
	public void renderPlayer(Player player, Graphics g) {
		BufferedImage sprite = Images.getSprite("player");
		int drawPosX = (Window.WIDTH / 2) - sprite.getWidth();
		int drawPosY = (Window.HEIGHT / 2) - sprite.getHeight();
		g.drawImage(sprite, drawPosX, drawPosY, sprite.getWidth() * MULT, sprite.getHeight() * MULT, null);
	}
	
	public void renderEnemy(Enemy[] enemies, Player player, Graphics g) {
		int count = 0;
		for (Enemy enemy : enemies) {
			BufferedImage sprite = Images.getSprite(enemy.getName());
			int drawPosX = enemy.getPosX() * (sprite.getWidth() * MULT) + ((Window.WIDTH/2) - player.getPosX() * (sprite.getWidth() * MULT) - sprite.getWidth());
			int drawPosY = enemy.getPosY() * (sprite.getHeight() * MULT) + ((Window.HEIGHT/2) - player.getPosY() * (sprite.getHeight() * MULT) - sprite.getHeight());
			if (count % 2 != 0) {
				sprite = mirrorImage(Images.getSprite(enemy.getName()));	
			}
			g.drawImage(sprite, drawPosX, drawPosY, sprite.getWidth() * MULT, sprite.getHeight() * MULT, null);
			count += 1;
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
