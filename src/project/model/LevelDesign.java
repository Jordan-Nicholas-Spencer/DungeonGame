package project.model;

import project.model.items.Item;
import project.model.items.Shop;

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
 * Responsibilities of class: contains the layout of each room. all rooms are in a single array of rooms to be read by the Room class
 * 
 */

public class LevelDesign {
	
	/* character legend:
	*  # = wall
	*  ^ = stairs
	*  o = open gate (some shading) // only use vertically
	*  g = gate
	*  c = chest
	*  d = door
	*    = floor
	*  s = skeleton (decoration)
	*  any number = NPC
	*  k = key
	*/

	/**
	 * creates an array of Rooms
	 */
	public final Room[] LEVELARRAY = {new Room(new String[] 
		{
		  // 0    5    10   15   20   25
		  // |    |    |    |    |    |
			"##########################", // 0
			"##########################",
			"##########################",
			"###g##ss##c c#s#s##s######",
			"##k     1#   #   c  ######",
			"####     #   #     #######", // 5
			"#######d##   #d###########", 
			"###                 ######",
			"###                      ^",
			"###########o##############",
			"###      g   g      ######", // 10
			"###          g      ######", 
			"###      g   g      ######",
			"###k     g   g      ######",
			"##########################",
			"##########################", // 15
			"##########################"  
		}, 4, 4, new Item[] {Shop.SWORD, Shop.SCRAP, Shop.HP_POTION}, 
			new Enemy(Enemy.Species.RAT, 5 , 10), new Enemy(Enemy.Species.GARGOYLE, 15 , 10),
			new Enemy(Enemy.Species.RAT, 6, 11), new Enemy(Enemy.Species.RAT, 5, 12),
			new Enemy(Enemy.Species.SUCCUBUS, 16, 11), new Enemy(Enemy.Species.SUCCUBUS, 15, 13) ),
			
			
		new Room(new String[] {
			"##########################",
			"##########################",
			"####ss######s#s#####s#####",
			"g                      d ^",
			"###          k         ###",
			"#### ##                ###",
			"### c #                ###",
			"#########d################",
			"######### ################",
			"######### ################",
			"#                   ######",
			"g1                  ######",
			"#                   ######",
			"#                   ######",
			"#            ## ##########",
			"#cc          #     k######",
			"##########################",
			"##########################"
		}, 1, 3, new Item[] {Shop.SCRAP, Shop.HP_POTION, Shop.LEATHER}, 
			new Enemy(Enemy.Species.RAT, 15, 10), new Enemy(Enemy.Species.BAT, 17, 9), new Enemy(Enemy.Species.SLIME, 18, 15)),
	
			
		new Room(new String[] {
			"########",
			"##^   ##",
			"##### ##",
			"##g   ##",
			"########"
		}, 3, 3, new Item[] {},
				new Enemy(Enemy.Species.RAT, 5, 1)),
	
			
		new Room(new String[] {
			"########",
			"## ##^##",
			"## ## ##",
			"##    ##",
			"##    ##",
			"##   c##",
			"########"
		}, 2, 1, new Item[] {Shop.HP_POTION} ),
	
			
		new Room(new String[] {
				"##^#####",
				"##d## ##",
				"## ## ##",
				"##    ##",
				"##k   ##",
				"########"
		}, 5, 1, new Item[] {},
				new Enemy(Enemy.Species.BAT, 5, 4)),
			
			
		new Room(new String[] {
			"########################",
			"########################",
			"##c    1#            c##",
			"##c     #            c##",
			"##                    d^",
			"g       #            k##",
			"########################"
		}, 1, 5, new Item[] {Shop.PLATE, Shop.WHIP, Shop.HP_POTION, Shop.HP_POTION},
				new Enemy(Enemy.Species.GARGOYLE, 20, 2)),
		new Room(new String[] {
				"########################",
				"########################",
				"##                   1##",
				"##                    ##",
				"##                    ##",
				"##                     ^",
				"#o                    ##",
				"##                    ##",
				"########################"
			}, 2, 6, new Item[] {},
					new Enemy(Enemy.Species.SLIME, 17, 2), new Enemy(Enemy.Species.SLIME, 14, 2),
		new Enemy(Enemy.Species.SLIME, 17, 2), new Enemy(Enemy.Species.SLIME, 17, 2))};
	
}