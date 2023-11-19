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

	public final Room[] LEVELARRAY = {new Room(new String[] 
		{
			"##########################",
			"##########################",
			"####ss##   #s#s##s########",
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
		}, 0, 3, new Enemy(Enemy.Species.GARGOYLE, 7 , 6), new Enemy(Enemy.Species.GARGOYLE, 11 , 6)),
			new Room(new String[] {
				"##########################",
				"##########################",
				"####ss######s#s#####s#####",
				"g                      ###",
				"###                    ###",
				"###                    ###",
				"###                      g",
				"##########################"
			}, 1, 3) };
	
	
}
