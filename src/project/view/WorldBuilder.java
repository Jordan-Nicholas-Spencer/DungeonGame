package project.view;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import project.model.Enemy;
import project.model.Player;
import project.model.Room;


public class WorldBuilder {
	
	private final int MULT = 1;
	private final int SCALE = 50;
	
	SpriteSheetReader ssr = new SpriteSheetReader();

	public void renderLevel(Room room, Player player, Graphics g) {
		for(int column=0; column < room.getSizeY(); column++) {
			for(int row=0; row < room.getSizeX(); row++) {
				String name = room.getTileAt(row, column).getName();
				BufferedImage sprite = ImageLoader.getSprite(name);
				int drawPosX = room.getTileAt(row, column).getPosX() * (sprite.getWidth() * MULT) + ((Window.WIDTH/2) - player.getPosX() * (sprite.getWidth() * MULT) - sprite.getWidth());
				int drawPosY = room.getTileAt(row, column).getPosY() * (sprite.getHeight() * MULT) + ((Window.HEIGHT/2) - player.getPosY() * (sprite.getHeight() * MULT) - sprite.getHeight());
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
		sprite = ssr.grabImage(1, 0, 32, 32);
		int drawPosX = (Window.WIDTH / 2) - SCALE;
		int drawPosY = (Window.HEIGHT / 2) - SCALE;
		g.drawImage(sprite, drawPosX, drawPosY, SCALE * MULT, SCALE * MULT, null);
	}
	
	public void renderEnemy(Enemy[] enemies, Player player, Graphics g) {
		for (Enemy enemy : enemies) {
			BufferedImage sprite = ImageLoader.getSprite(enemy.getName());
			if (sprite.getWidth() > SCALE) {
				ssr.setImage(sprite);
				sprite = ssr.grabImage(1, 1, 32, 32);
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
