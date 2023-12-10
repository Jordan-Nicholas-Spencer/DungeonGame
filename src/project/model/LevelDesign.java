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
		  // 0    5    10   15   20   25
		  // |    |    |    |    |    |
			"##########################", // 0
			"##########################",
			"##########################",
			"######ss##ccc#s#s##s######",
			"###     1#   #      ######",
			"####     #   #     #######", // 5
			"#######o##   #g###########", 
			"###                 ######",
			"###                      d",
			"###########o##############",
			"###      g   g      ######", // 10
			"###          g      ######", 
			"###      g   g      ######",
			"###      g   g      ######",
			"##########################",
			"##########################", // 15
			"##########################"  
		}, 4, 4, new Enemy(Enemy.Species.BAT, 5 , 10), new Enemy(Enemy.Species.GARGOYLE, 15 , 10),
			new Enemy(Enemy.Species.RAT, 6, 11), new Enemy(Enemy.Species.SLIME, 5, 12),
			new Enemy(Enemy.Species.SUCCUBUS, 16, 11), new Enemy(Enemy.Species.VAMPIRE, 15, 13) ),
			new Chest(),
			
			new Room(new String[] {
				"##########################",
				"##########################",
				"####ss######s#s#####s#####",
				"g                        d",
				"###                    ###",
				"###                    ###",
				"###                    ###",
				"##########################"
			}, 1, 3 ),
	
			new Room(new String[] {
					"########",
					"##^   ##",
					"##### ##",
					"##g   ##",
					"########"
				}, 3, 3 ),
	
			new Room(new String[] {
					"########",
					"## ##^##",
					"## ## ##",
					"##    ##",
					"########"
				}, 2, 1 ),
	
			new Room(new String[] {
					"#########",
					"##     ##",
					"##     ##",
					"##     ##",
					"#########"
				}, 6, 1) };
	
}