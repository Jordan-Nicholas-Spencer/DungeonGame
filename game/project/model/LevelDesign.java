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
		}, 1, 3, new Item[] {Shop.SWORD, Shop.SCRAP, Shop.HP_POTION}, 
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
//				 0    5    10   15   20 23
		//		 |    |    |    |    |  |
				"########################", // 0
				"########################",
				"##                   1##",
				"##                    ##",
				"##                    ##", 
				"##                     ^",	// 5
				"#o                    ##",
				"##                  cc##",
				"########################" // 8
			}, 2, 6, new Item[] {Shop.HP_POTION, Shop.HP_POTION},
					new Enemy(Enemy.Species.SLIME, 3, 2), new Enemy(Enemy.Species.SLIME, 6, 2),
					new Enemy(Enemy.Species.SLIME, 9, 2), new Enemy(Enemy.Species.SLIME, 12, 2),
					new Enemy(Enemy.Species.SLIME, 15, 2), new Enemy(Enemy.Species.SLIME, 18, 2),
					new Enemy(Enemy.Species.SLIME, 3, 4), new Enemy(Enemy.Species.SLIME, 6, 4),
					new Enemy(Enemy.Species.SLIME, 9, 4), new Enemy(Enemy.Species.SLIME, 12, 4),
					new Enemy(Enemy.Species.SLIME, 15, 4), new Enemy(Enemy.Species.SLIME, 18, 4),
					new Enemy(Enemy.Species.SLIME, 3, 7), new Enemy(Enemy.Species.SLIME, 6, 7),
					new Enemy(Enemy.Species.SLIME, 9, 7), new Enemy(Enemy.Species.SLIME, 12, 7),
					new Enemy(Enemy.Species.SLIME, 15, 7), new Enemy(Enemy.Species.SLIME, 18, 7)),
		
		new Room(new String[] {
//				 0    5    10   15   20   25   30   30   35   40
//				 |    |    |    |    |    |    |    |    |    |  
				"#################################################", // 0
				"#########################o#######s###############",
				"##      #                               #     c##",
				"#s                                             s#",
				"##      #                               #      ##",
				"##c     #                               #      ##", // 5
				"#########                               ####s####",
				"##c     #                               #      ##",
				"##      #                               #      ##",
				"#s                                             ##",
				"##      #                               #      ##", // 10
				"##      #                               #     c##",
				"#########                               #########",
				"##c     #                               #     c##",
				"##      #                               #      s#",
				"#s                                             ##", // 15
				"##      #                 1             #      ##",
				"##c     #               #d#             #     c##",
				"######s##               # #             #########",
				"##                    ##  #                    ##",
				"##cc    #             #c  #             #    cc##", // 20
				"#########################^#######################"
			}, 25, 1, new Item[] {Shop.HP_POTION, Shop.HP_POTION, Shop.HP_POTION,Shop.HP_POTION, Shop.HP_POTION, Shop.HP_POTION,
					Shop.HP_POTION, Shop.HP_POTION, Shop.HP_POTION, Shop.HP_POTION,Shop.HP_POTION, Shop.KEY, Shop.BLESSED},
				new Enemy(Enemy.Species.GARGOYLE, 5, 3), new Enemy(Enemy.Species.GARGOYLE, 6, 3), new Enemy(Enemy.Species.GARGOYLE, 37, 3),
				new Enemy(Enemy.Species.GARGOYLE, 39, 3), new Enemy(Enemy.Species.GARGOYLE, 22, 5), new Enemy(Enemy.Species.GARGOYLE, 28, 5),
				new Enemy(Enemy.Species.GARGOYLE, 22, 10), new Enemy(Enemy.Species.GARGOYLE, 28, 10), new Enemy(Enemy.Species.GARGOYLE, 22, 15),
				new Enemy(Enemy.Species.GARGOYLE, 27, 15), new Enemy(Enemy.Species.GARGOYLE, 22, 20), new Enemy(Enemy.Species.GARGOYLE, 27, 20),
				new Enemy(Enemy.Species.GARGOYLE, 5, 8), new Enemy(Enemy.Species.GARGOYLE, 6, 8), new Enemy(Enemy.Species.GARGOYLE, 37, 8),
				 new Enemy(Enemy.Species.GARGOYLE, 39, 8), new Enemy(Enemy.Species.GARGOYLE, 5, 13), new Enemy(Enemy.Species.GARGOYLE, 6, 13),
				 new Enemy(Enemy.Species.GARGOYLE, 37, 13), new Enemy(Enemy.Species.GARGOYLE, 39, 13), new Enemy(Enemy.Species.GARGOYLE, 5, 18),
				 new Enemy(Enemy.Species.GARGOYLE, 6, 18), new Enemy(Enemy.Species.GARGOYLE, 37, 18), new Enemy(Enemy.Species.GARGOYLE, 39, 18)
					),
		new Room(new String[] {
				"#######g#######",
				"#            1#",
				"#             #",
				"#             #",
				"#c            #",
				"#######^#######"
			}, 7, 1, new Item[] {Shop.STAKE}, new Enemy(Enemy.Species.VAMPIRE, 7, 3)),
		new Room(new String[] {
				"####g####",
				"#       #",
				"#       #",
				"#   1   #",
				"#       #",
				"#########"
			}, 4, 1, new Item[] {})
		};
	
	
}