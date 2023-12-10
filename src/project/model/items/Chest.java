package project.model.items;

public class Chest 
{
	static Shop shop = new Shop();
	
	private static Item[] weaponArray = {shop.getRandWeapon()};
	
	private static Item[] armorArray = {shop.getRandArmor()};
	
	private static Item[] potionArray = {shop.getPotion()};
	
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
