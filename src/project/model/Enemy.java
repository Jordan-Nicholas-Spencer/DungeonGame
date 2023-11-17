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
		BAT("bat", 11, 3, 2),
		RAT("rat", 7, 1, 0),
		GARGOYLE("gargoyle", 13, 3, 5),
		VAMPIRE("vampire", 26, 6, 10);
		
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
