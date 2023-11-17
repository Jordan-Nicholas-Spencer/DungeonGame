package project;

import project.Enemy.Species;

public class LevelDesign {

	public static final Room LEVEL1 = new Room(new String[] {
			"###################",
			"###################",
			"######## ^ ########",
			"####   #   #   ####",
			"####           ####",
			"####           ####",
			"#                 #",
			"|                 |",
			"#                 #",
			"####           ####",
			"####           ####",
			"####           ####",
			"#######     #######",
			"#######     #######",
			"#########|#########"
		}, 9, 2, new Enemy(Enemy.Species.GARGOYLE, 7 , 4), new Enemy(Enemy.Species.GARGOYLE, 11 , 4));
	
	
}
