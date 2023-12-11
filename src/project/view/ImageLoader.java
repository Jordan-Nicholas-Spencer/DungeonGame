package project.view;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

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
 * Responsibilities of class: parses all of the images in the project and adds them to a HashMap for rendering
 * 
 */

public class ImageLoader {
	private static HashMap<String, BufferedImage> sprites;
	
	/**
	 * creates a HashMap of strings and PNGs
	 */
	public static void initializeSprites() {
		sprites = new HashMap<String, BufferedImage>();
		
		File folder = new File("src/imagelibrary");
		
		// removes ".png" from the PNG file names
		for (File file : folder.listFiles()) {
			try {
				sprites.put(file.getName().replaceAll(".png", ""), ImageIO.read(file));
			} catch (IOException e) {
				System.out.print("Error reading file" + file.getName());
			}
		}
	}
	
	/**
	 * gets the PNG of the file name from the HashMap sprites
	 * @param name - the file name of the PNG
	 * @return the PNG of the requested file
	 */
	public static BufferedImage getSprite(String name) {
		BufferedImage sprite = sprites.get(name);
		if (sprite != null) {
			return sprite;
		}
		else {
			return sprites.get("error");
		}
	}
	
	
}
