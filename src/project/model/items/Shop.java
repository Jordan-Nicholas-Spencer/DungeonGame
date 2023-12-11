package project.model.items;
import java.util.Random;

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
	
	public Weapon getRandWeapon()
	{
		Random rand = new Random();
		Weapon weapon;
		switch (rand.nextInt(3) + 1)
		{
		case 1:
			weapon = SWORD;
			return weapon;
		case 2:
			weapon = AXE;
			return weapon;
		case 3:
			weapon = WHIP;
			return weapon;
		}
		
		return null;
	}
	
	public Armor getRandArmor()
	{
		Random rand = new Random();
		Armor armor;
		switch (rand.nextInt(4) + 1)
		{
		case 1:
			armor = SCRAP;
			return armor;
		case 2:
			armor = LEATHER;
			return armor;
		case 3:
			armor = PLATE;
			return armor;
		case 4:
			armor = BLESSED;
			return armor;
		}
		
		return null;
	}
	
	public Item getKey()
	{
		return KEY;
	}
	
	public Item getPotion()
	{
		return HP_POTION;
	}
}
