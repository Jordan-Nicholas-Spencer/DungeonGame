package project.model;

import java.util.Random;
import java.util.ArrayList;

import project.model.items.Chest;

public class Room {

	private Tile[][] room;
	private int xStartPos;
	private int yStartPos;
	private ArrayList<Enemy> enemies;
	private ArrayList<NPC> npcs;
	private ArrayList<Chest> chests;
	
	public Room(String[] levelDesign, int xStartPos, int yStartPos,  Enemy... species) {
		room = new Tile[levelDesign.length][];
		this.npcs = new ArrayList<>();
		this.chests = new ArrayList<>();
		Random rand = new Random();
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
					Chest chest;
					switch(rand.nextInt(2) + 1)
					{
					case 1:
						chest = new Chest(Chest.Chests.WEAPONCHEST, row, column);
						chests.add(chest);
						break;
					case 2:
						chest = new Chest(Chest.Chests.ARMORCHEST, row, column);
						chests.add(chest);
						break;
					}
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
	
	public Chest[] getChests()
	{
		Chest[] roomChests = new Chest[chests.size()];
		roomChests = chests.toArray(roomChests);
		return roomChests;
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