package project.model;

public class LevelDesign {
	
	/* character legend:
	*  # = wall
	*  ^ = stairs
	*  o = open (gate)
	*  g = gate
	*  c = chest
	*  d = door
	*    = floor
	*  s = skeleton (decoration)
	*/

	public static final Room LEVEL1 = new Room(new String[] {
			"##########################",
			"##########################",
			"####ss## ^ #s#s##s########",
			"#      #   #      ########",
			"##     #   #     #########",
			"#####o##   #g#############",
			"#                 ########",
			"#                        d",
			"#                 ########",
			"#########o################",
			"####ccccc ccccc###########",
			"####c         c###########",
			"####cccc   cccc###########",
			"#######ccccc##############",
			"##########################"
		}, 9, 2, new Enemy(Enemy.Species.GARGOYLE, 7 , 6), new Enemy(Enemy.Species.GARGOYLE, 11 , 6));
	
	
}
