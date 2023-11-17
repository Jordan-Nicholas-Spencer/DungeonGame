package project;

import java.awt.Graphics;
import java.awt.image.BufferedImage;


public class WorldBuilder {

	public void renderLevel(Room room, Graphics g) {
		for(int column=0; column < room.getSizeY(); column++) {
			for(int row=0; row < room.getSizeX(); row++) {
				BufferedImage sprite = Images.getSprite(room.getTileAt(row, column).getName());
				int drawPosX = room.getTileAt(row, column).getPosX() * sprite.getWidth();
				int drawPosY = room.getTileAt(row, column).getPosY() * sprite.getWidth();
				g.drawImage(sprite, drawPosX, drawPosY, sprite.getWidth(), sprite.getHeight(), null);
			}
		}
	}
	
	public void renderPlayer(Graphics g) {
		
	}
	
}
