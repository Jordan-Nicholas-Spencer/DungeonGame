package project.model;

import java.util.ArrayList;

import project.model.items.Item;

public class Room {

	private Tile[][] room;
	private int xStartPos;
	private int yStartPos;
	private ArrayList<Item> chestItems;
	private ArrayList<Enemy> enemies;
	private ArrayList<NPC> npcs;
	
	/**
	 * 
	 * @param levelDesign - a string array that represents the layout of the room
	 * @param xStartPos	- starting position of the player within the room
	 * @param yStartPos - starting position of the player within the room
	 * @param chestItems - an item array that represents the items available within chests in the room
	 * @param species - an enumeration of the enemies in the current room
	 */
	public Room(String[] levelDesign, int xStartPos, int yStartPos, Item[] chestItems,  Enemy... species) {
		// the double array room is instantiated
		room = new Tile[levelDesign.length][];
		// the ArrayList that will contain the NPCs in the room is instantiated
		this.npcs = new ArrayList<>();
		// here we iterate through String[] levelDesign in order to create a room of correlating tiles with x and y coordinates
		for(int column = 0; column<levelDesign.length; column++) {
			room[column] = new Tile[levelDesign[column].length()];
			
			for(int row = 0; row<levelDesign[column].length(); row++) {
				switch(levelDesign[column].charAt(row)) {
				case '#':
					room[column][row] = new Tile("wall", row, column);
					break;
				case ' ':
					room[column][row] = new Tile("floor", row, column);
					break;
				case 'd':
					room[column][row] = new Tile("door", row, column);
					break;
				case '^':
					room[column][row] = new Tile("stairs", row, column);
					break;
				case 'p':
					room[column][row] = new Tile("player", row, column);
					break;
				case 's':
					room[column][row] = new Tile("skeleton", row, column);
					break;
				case 'c':
					room[column][row] = new Tile("chest", row, column);
					break;
				case 'o':
					room[column][row] = new Tile("open", row, column);
					break;
				case '1':
					room[column][row] = new Tile("npc", row, column);
					NPC npc = new NPC(NPC.Characters.PRISONER, row, column, 1000);
					npcs.add(npc);
					break;
				case 'g':
					room[column][row] = new Tile("gate", row, column);
					break;
				case 'k':
					room[column][row] = new Tile("key", row, column);
					break;
				}
			}
		}
		
		// the chestItems ArrayList of items is filled with all of the items within in the room's LevelDesign
		this.chestItems = new ArrayList<Item>();
		for(Item item : chestItems) {
			this.chestItems.add(item);
		}
		
		this.enemies = new ArrayList<Enemy>();
		for (Enemy enemy : species) {
			this.enemies.add(enemy);
		}
		

		this.xStartPos = xStartPos;
		this.yStartPos = yStartPos;
	}
	
	public Enemy[] getEnemies() {
		Enemy[] roomEnemies = new Enemy[enemies.size()];
		roomEnemies = enemies.toArray(roomEnemies);
		return roomEnemies;
	}
	
	public Item[] getChestItems() {
		Item[] chestItems = new Item[this.chestItems.size()];
		chestItems = this.chestItems.toArray(chestItems);
		return chestItems;
	}
	
	public Item takeItemFromChest() {
		Item item = chestItems.get(0);
		chestItems.remove(0);
		return item;
	}
	
	public void addNPC(NPC npc)
	{
		npcs.add(npc);
	}
	
	public NPC[] getNPCs()
	{
		NPC[] roomNPCs = new NPC[npcs.size()];
		roomNPCs = npcs.toArray(roomNPCs);
		return roomNPCs;
	}
	
	public NPC getNPCAt(int x, int y)
	{
		for (NPC npc : npcs)
		{
			if (npc.getPosX() == x && npc.getPosY() == y)
			{
				return npc;
			}
		}
		return null;
	}
	
	public void openDoor(int x, int y)
	{
		room[y][x] = new Tile("floor", x, y);
	}
	
	public Enemy getEnemyAt(int x, int y)
	{
		for (Enemy enemy: enemies)
		{
			if (enemy.getPosX() == x && enemy.getPosY() == y)
			{
				return enemy;
			}
		}
		return null;
	}
	
	public boolean enemyInRoom(int x, int y) {
		for (int i = 0; i < enemies.size(); i++) {
			if (enemies.get(i).getPosX() == x && enemies.get(i).getPosY() == y) {
				return true;
			}
		}
		return false;
	}
	
	public void killEnemy(int x, int y) {
		for (int i = 0; i < enemies.size(); i++) {
			if(enemies.get(i).getPosX() == x && enemies.get(i).getPosY() == y) {
				enemies.remove(i);
			}
		}
	}
	
	public void pickUpItem(int x, int y) {
		switch(room[y][x].getName()) {
		case "key":
			room[y][x] = new Tile("floor", x, y);
		case "potion":
			room[y][x] = new Tile("floor", x, y);
		case "chest":
			room[y][x] = new Tile("floor", x, y);
		}
	}
	
	public int getXStartPos() {
		return xStartPos;
	}
	
	public int getYStartPos() {
		return yStartPos;
	}
	
	public int getSizeX() {
		return room[0].length;
	}
	
	public int getSizeY() {
		return room.length;
	}
	
	public Tile getTileAt(int x, int y) {
		return room[y][x];
	}
}