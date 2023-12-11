package project.model;

import java.util.ArrayList;

import project.model.items.Armor;
import project.model.items.Consumable;
import project.model.items.Item;
import project.model.items.Weapon;

public class Player extends Organism implements GodMode{

	private static final int STARTINGHP = 20;
	private static final int STARTINGSTRENGTH = 1;
	private static final int STARTINGDEFENSE = 1;
	private static final int MAXCONSUMABLES = 3;
	private ArrayList<Item> consumables;
	
	private int keyCount = 0; 
	private Weapon weaponEquipped;
	private Armor armorEquipped;
	private int levelsCompleted;
	
	public Player(String type, int posX, int posY) {
		super(type, posX, posY, STARTINGHP);
		this.consumables = new ArrayList<>(MAXCONSUMABLES);
		this.weaponEquipped = null;
		this.armorEquipped = null;
		this.strength = STARTINGSTRENGTH;
		this.defense = STARTINGDEFENSE;
		this.levelsCompleted = 0;
	}
	
	/**
	 * Adds an items to the first empty slot
	 * @param consumable  The item to add
	 * @return True if the item was added, false if inventory is full
	 */
	public void addItem(Item consumable) 
	{
		consumables.add(consumable);
	}
	
	/**
	 * gets current number of keys in player's inventory
	 * @return current number of keys in player's inventory
	 */
	public int getKeyCount()
	{
		return keyCount;
	}
	
	/**
	 * adds 1 to keyCount as to add a key to the player's inventory
	 */
	public void pickUpKey()
	{
		keyCount++;
	}
	
	/**
	 * removes 1 from keyCount as to remove a key to the player's inventory
	 */
	public void useKey()
	{
		keyCount--;
	}
	
	/**
	 * determines if player has enough keys to open a door (1 key is enough)
	 * @param the player
	 * @return true if player has enough key to open the door, otherwise false
	 */
	public boolean canOpenDoor(Player player)
	{
		if (player.getKeyCount() >= 1)
		{
			return true;
		}
		
		return false;
	}
	
	/**
	 * selects a consumable item from the player's inventory at a specific index of the consumables array
	 * @param the index of the item in the players consumables inventory
	 * @return a consumable item from the player's inventory
	 */
	public Item getInventoryItem(int index) 
	{
		return consumables.get(index);
	}
	
	/**
	 * get the number of items in the consumables array inventory
	 * @return the number of items in the consumables array inventory
	 */
	public int getInventorySize() {
		return consumables.size();
	}
	
	/**
	 * gets all of the consumable items in the player's inventory
	 * @return an ArrayList<Item> of all of the consumable items in the players inventory
	 */
	public ArrayList<Item> getInventoryConsumables()
	{
		return consumables;
	}
	
	/**
	 * increases maxHP of player and heals for the same amount
	 * @param amount of health to health to increase maxHP
	 */
	public void increaseHealth(int amount) {
		this.maxHP += amount;
		this.health += amount;
	}
	
	/**
	 * player's strength is their base strength plus the strength of the currently equipped weapon
	 */
	@Override
	public int getStrength() {
		int str = super.getStrength();
		if(this.weaponEquipped != null) str += this.weaponEquipped.getStrength();
		return str;
	}
	
	/**
	 * player's defense is their base defense plus the defense of the currently equipped armor
	 */
	@Override
	public int getDefense() {
		int def = super.getDefense();
		if(this.armorEquipped != null) def += this.armorEquipped.getDefense();
		return def;
	}
	
	/**
	 * equips a weapon to the player
	 * @param a weapon to equip (an Item may be passed, but is validated as a weapon before equipping)
	 */
	public void equipWeapon(Item item) {
		if (item.getClass() == Weapon.class)
		{
			this.weaponEquipped = (Weapon) item;
		}
	}
	
	/**
	 * equips armor to the player
	 * @param an armor to equip (an Item may be passed, but is validated as armor before equipping)
	 */
	public void equipArmor(Item item) {
		if (item.getClass() == Armor.class)
		{
			this.armorEquipped = (Armor) item;
		}
	}

	/**
	 * see what weapon the player currently has equipped
	 * @return the equipped weapon
	 */
	public Weapon getWeapon() {
		return this.weaponEquipped;
	}

	/**
	 * see what armor the player currently has equipped
	 * @return the equipped armor
	 */
	public Armor getArmor() {
		return this.armorEquipped;
	}
	
	/**
	 * see the number of rooms cleared by the player
	 * @return the number of rooms cleared by the player
	 */
	public int getLevelsCompleted()
	{
		return this.levelsCompleted;
	}
	
	/**
	 * add 1 to levels completed
	 */
	public void levelCompleted()
	{
		this.levelsCompleted += 1;
	}

	/**
	 * god mode - give the player near infinite health
	 */
	@Override
	public void infiniteHealth() {
		this.maxHP = 1000000000;
		this.health = 1000000000;
		
	}

	/**
	 * god mode - give the player a power level over 9000
	 */
	@Override
	public void onePunchMan() {
		this.strength = 9001;
		
	}

	/**
	 * return to baseline health from god mode
	 */
	@Override
	public void normalHealth() {
		this.maxHP = STARTINGHP;
		this.health = STARTINGHP;
		
	}

	/**
	 * return to baseline strength from god mode
	 */
	@Override
	public void normalDamage() {
		this.strength = STARTINGSTRENGTH;
		
	}

	
	
}