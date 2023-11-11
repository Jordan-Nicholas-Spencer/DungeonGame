package project;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class Images {
	private static HashMap<String, BufferedImage> sprites;
	
	public static void initializeSprites() {
		sprites = new HashMap<String, BufferedImage>();
		
		File folder = new File("src/imagelibrary");
		
		for (File file : folder.listFiles()) {
			try {
				sprites.put(file.getName().replaceAll(".png", ""), ImageIO.read(file));
			} catch (IOException e) {
				System.out.print("Error reading file" + file.getName());
			}
		}
	}
	
	public static BufferedImage getSprite(String name) {
		BufferedImage sprite = sprites.get(name);
		if (sprite != null) return sprite;
		else return sprites.get("error");
	}
	
	
}
