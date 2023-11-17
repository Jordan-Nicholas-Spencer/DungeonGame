package project;

import java.awt.Graphics;
import java.awt.image.BufferedImage;


public class WorldBuilder {
	

	public void renderLevel(Room room, Player player, Graphics g) {
		for(int column=0; column < room.getSizeY(); column++) {
			for(int row=0; row < room.getSizeX(); row++) {
				BufferedImage sprite = Images.getSprite(room.getTileAt(row, column).getName());
				int drawPosX = room.getTileAt(row, column).getPosX() * (sprite.getWidth() * 2) + ((Window.WIDTH/2) - player.getPosX() * (sprite.getWidth() * 2) - sprite.getWidth());
				int drawPosY = room.getTileAt(row, column).getPosY() * (sprite.getHeight() * 2) + ((Window.HEIGHT/2) - player.getPosY() * (sprite.getHeight() * 2) - sprite.getHeight());
				g.drawImage(sprite, drawPosX, drawPosY, sprite.getWidth() * 2, sprite.getHeight() * 2, null);
			}
		}
	}
	
	public void renderPlayer(Player player, Graphics g) {
		BufferedImage sprite = Images.getSprite("player");
		int drawPosX = (Window.WIDTH / 2) - sprite.getWidth();
		int drawPosY = (Window.WIDTH / 2) - sprite.getHeight();
		g.drawImage(sprite, drawPosX, drawPosY, sprite.getWidth() * 2, sprite.getHeight() * 2, null);
	}
	
	
}
