package project.model;

public class LevelDesign {

	public static final Room LEVEL1 = new Room(new String[] {
			"###################",
			"###################",
			"####ss## ^ #s#s##s#",
			"#      #   #      #",
			"##     #   #     ##",
			"#####o##   #g######",
			"#                 #",
			"|                 |",
			"#                 #",
			"#########g#########",
			"####ccccc ccccc####",
			"####c         c####",
			"####cccc   cccc####",
			"#######ccccc#######",
			"###################"
		}, 9, 2, new Enemy(Enemy.Species.GARGOYLE, 7 , 6), new Enemy(Enemy.Species.GARGOYLE, 11 , 6));
	
	
}
