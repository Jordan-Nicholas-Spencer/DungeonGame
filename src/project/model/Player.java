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
	private ArrayList<Consumable> consumables;
	
	private int keyCount = 0;
	private boolean inventoryOpen;
	private Weapon weaponEquipped;
	private Armor armorEquipped;
	private int levelsCompleted;
	
	public Player(String type, int posX, int posY) {
		super(type, posX, posY, STARTINGHP);
		this.consumables = new ArrayList<>(5);
		this.inventoryOpen = false;
		this.weaponEquipped = null;
		this.armorEquipped = null;
		this.strength = STARTINGSTRENGTH;
		this.defense = STARTINGDEFENSE;
		this.levelsCompleted = 0;
	}
	
	/**Adds an items to the first empty slot
	 * @param consumable  The item to add
	 * @return True if the item was added, false if inventory is full
	 */
	public void addItem(Consumable consumable) 
	{
		
	}
	
	public int getKeyCount()
	{
		return keyCount;
	}
	
	public void pickUpKey()
	{
		keyCount++;
	}
	
	public void useKey()
	{
		keyCount--;
	}
	
	public boolean canOpenDoor(Player player)
	{
		if (player.getKeyCount() >= 1)
		{
			return true;
		}
		
		return false;
	}
	
	public Consumable getInventoryItem(int index) 
	{
		return consumables.get(index);
	}
	
	public ArrayList<Consumable> getInventoryConsumables()
	{
		return consumables;
	}
	
	public void setInventoryOpen(boolean inventoryOpen) {
		this.inventoryOpen = inventoryOpen;
	}
	
	public boolean isInventoryOpen() {
		return inventoryOpen;
	}
	
	public void increaseHealth(int amount) {
		this.maxHP += amount;
		this.health += amount;
	}
	
	@Override
	public int getStrength() {
		int str = super.getStrength();
		if(this.weaponEquipped != null) str += this.weaponEquipped.getStrength();
		return str;
	}
	
	@Override
	public int getDefense() {
		int def = super.getDefense();
		if(this.armorEquipped != null) def += this.armorEquipped.getDefense();
		return def;
	}
	
	public void equipWeapon(Item item) {
		if (item.getClass() == Weapon.class)
		{
			this.weaponEquipped = (Weapon) item;
		}
	}
	
	public void equipArmor(Item item) {
		if (item.getClass() == Armor.class)
		{
			this.armorEquipped = (Armor) item;
		}
	}

	public Weapon getWeapon() {
		return this.weaponEquipped;
	}

	public Armor getArmor() {
		return this.armorEquipped;
	}
	
	public int getLevelsCompleted()
	{
		return this.levelsCompleted;
	}
	
	public void levelCompleted()
	{
		this.levelsCompleted += 1;
	}

	@Override
	public void infiniteHealth() {
		this.maxHP = 1000000000;
		this.health = 1000000000;
		
	}

	@Override
	public void onePunchMan() {
		this.strength = 9001;
		
	}

	@Override
	public void normalHealth() {
		this.maxHP = STARTINGHP;
		this.health = STARTINGHP;
		
	}

	@Override
	public void normalDamage() {
		this.strength = STARTINGSTRENGTH;
		
	}
	
	
}