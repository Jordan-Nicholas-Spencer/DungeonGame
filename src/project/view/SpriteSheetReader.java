package project.view;

import java.awt.image.BufferedImage;

public class SpriteSheetReader {

	private BufferedImage image;
	
	public SpriteSheetReader() {
		this.image = null;
	}
	
	public SpriteSheetReader(BufferedImage image) {
		this.image = image;
	}
	
	public void setImage(BufferedImage image) {
		this.image = image;
	}
	
	public BufferedImage getImage() {
		return this.image;
	}
	
	public BufferedImage grabImage(int col, int row, int width, int height) {
		BufferedImage img = image.getSubimage(col * 32, row * 32, width, height);
		return img;
	}
}
