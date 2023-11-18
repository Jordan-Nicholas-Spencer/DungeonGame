package project.model;

import project.model.items.Armor;
import project.model.items.Consumable;
import project.model.items.Shop;
import project.model.items.Weapon;

public class Player extends Organism{

	private static final int INVENTORY_SIZE = 3;
	private static final int startingHP = 20;
	private Consumable[] inventory;
	private boolean inventoryOpen;
	private Weapon weaponEquipped;
	private Armor armorEquipped;
	
	public Player(String type, int posX, int posY) {
		super(type, posX, posY, startingHP);
		this.inventory = new Consumable[INVENTORY_SIZE];
		this.inventoryOpen = false;
		this.weaponEquipped = Shop.SWORD;
		this.armorEquipped = Shop.SCRAP;
		this.strength = 1;
		this.defense = 0;

	}
	
	/**Adds an items to the first empty slot
	 * @param consumable - The item to add
	 * @return True if the item was added, false if inventory is full
	 */
	public boolean addItem(Consumable item) {
		for(int i=0;i<INVENTORY_SIZE;i++) {
			if(this.inventory[i] == null) {
				this.inventory[i] = item;
				return true;
			}
		}
		return false;
	}
	
	/**Removes an item from the player's inventory
	 * @param index - Inventory slot
	 */
	public void removeItem(int index) {
		try {
			this.inventory[index] = null;
		} catch(ArrayIndexOutOfBoundsException e) {
			return;
		}
	}
	
	public Consumable getInventoryItem(int index) {
		try {
			return inventory[index];
		} catch(ArrayIndexOutOfBoundsException e) {
			return null;
		}
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
	
	public void equipWeapon(Weapon weapon) {
		this.weaponEquipped = new Weapon(weapon.getName(), weapon.getDescription(), weapon.getStrength());
	}
	
	public void equipArmor(Armor armor) {
		this.armorEquipped = new Armor(armor.getName(), armor.getDescription(), armor.getDefense());
	}

	public Weapon getWeapon() {
		return this.weaponEquipped;
	}

	public Armor getArmor() {
		return this.armorEquipped;
	}
	
}
