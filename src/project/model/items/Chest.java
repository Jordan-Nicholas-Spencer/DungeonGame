package project.model.items;

public class Chest 
{
	
	private static Item[] weaponArray = {new Weapon("Straight Sword", "slice through even the toughest opponents", 10)};
	
	private static Item[] armorArray = {new Armor("helmet", "blah", 10)};
	
	private static Item[] potionArray = {new Consumable("health", "heal up")};
	
	private Chests chest;
	private int posX;
	private int posY;
	
	public Chest(Chests chest, int posX, int posY)
	{
		this.chest = chest;
		this.posX = posX;
		this.posY = posY;
	}
	
	public int getPosX()
	{
		return posX;
	}
	
	public int getPosY()
	{
		return posY;
	}
	
	public Chests getChest()
	{
		return chest;
	}
	
	public enum Chests
	{
		WEAPONCHEST("Weapon Chest", weaponArray),
		ARMORCHEST("Armor Chest", armorArray),
		POTIONCHEST("Potion Chest", potionArray);
		
		private String chestType;
		private Item[] itemArray;
		
		Chests(String chestType, Item[] itemArray)
		{
			this.chestType = chestType;
			this.itemArray = itemArray;
		}
		
		public String getChestType()
		{
			return chestType;
		}
		
		public Item[] getItemArray()
		{
			return itemArray;
		}
	}
}
