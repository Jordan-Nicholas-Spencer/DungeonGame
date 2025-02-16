package project.view;

import java.awt.image.BufferedImage;

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
 * Responsibilities of class: Controls data flow between user, model, and view 
 * 
 */

public class SpriteSheetReader {

	private BufferedImage image;
	
	/**
	 * constructor with no image
	 */
	public SpriteSheetReader() {
		this.image = null;
	}
	
	/**
	 * constructor with a PNG
	 * @param image - PNG
	 */
	public SpriteSheetReader(BufferedImage image) {
		this.image = image;
	}
	
	/**
	 * sets the current image to the passed image
	 * @param image - PNG
	 */
	public void setImage(BufferedImage image) {
		this.image = image;
	}
	
	/**
	 * gets the current image
	 * @return current image - PNG
	 */
	public BufferedImage getImage() {
		return this.image;
	}
	
	/**
	 * crops a PNG to the passed dimensions
	 * @param col
	 * @param row
	 * @param width
	 * @param height
	 * @return the cropped image
	 */
	public BufferedImage grabImage(int col, int row, int width, int height) {
		BufferedImage img = image.getSubimage(col * 32, row * 32, width, height);
		return img;
	}
}
