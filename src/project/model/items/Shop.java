package project.model.items;

public class Shop {

	public static final Weapon SWORD = new Weapon("sword", "A balanced slashing weapon", 5);
	public static final Weapon AXE = new Weapon("axe", "Weapon good for cracking open doors", 6);
	public static final Weapon WHIP= new Weapon("whip", "Attacks faster than the speed of sound", 7);
	
	
	public static final Armor SCRAP = new Armor("scrap", "Any extra padding is helpful, even newspaper", 3);
	public static final Armor LEATHER = new Armor("leather", "The armor of beasts", 5);
	public static final Armor PLATE = new Armor("plate", "A full set of medieval armor", 7);
	public static final Armor BLESSED = new Armor("blessed", "Your armor has been blessed by the gods themselves", 9);

	
	public static final Item HP_POTION = new Item("hp_potion", "Restores 10 HP");
	public static final Item KEY = new Item("small_key", "Can be used once to open a locked door");
}
