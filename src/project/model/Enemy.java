package project.model;

public class Enemy extends Organism {

	private Species species;
	
	public Enemy(Species species, int posX, int posY) {
		super(species.getName(), posX, posY, species.getMaxHp());
		this.strength = species.getStr();
		this.defense = species.getDef();
		this.species = species;
	}
	
	public Species getSpecies() {
		return species;
	}

	public enum Species {
		BAT("bat", 10, 3, 2),
		RAT("rat", 5, 1, 0),		
		GARGOYLE("gargoyle", 15, 5, 5),
		SLIME("slime", 8, 2, 5),
		SUCCUBUS("succubus", 21, 7, 7),
		VAMPIRE("vampire", 28, 9, 9);
		
		private String name;
		private int maxHP;
		private int str;
		private int def;
		
		Species(String name, int maxHP, int str, int def) {
			this.name = name;
			this.maxHP = maxHP;
			this.str = str;
			this.def = def;
		}

		public String getName() {
			return name;
		}

		public int getMaxHp() {
			return maxHP;
		}

		public int getStr() {
			return str;
		}

		public int getDef() {
			return def;
		}
	}

}