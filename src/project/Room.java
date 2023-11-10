package project;

public class Room {

	private Tile[][] room;
	private int xStartPos;
	private int yStartPos;
	
	public Room(String[] levelDesign, int xStartPos, int yStartPos) {
		room = new Tile[levelDesign.length][];
		for(int row=0; row<levelDesign.length; row++) {
			room[row] = new Tile[levelDesign[row].length()];
			
			for(int column=0; column<levelDesign[row].length(); column++) {
				switch(levelDesign[row].charAt(column)) {
				case '#':
					room[row][column] = new Tile("wall", row, column);
					break;
				case ' ':
					room[row][column] = new Tile("floor", row, column);
					break;
				case '|':
					room[row][column] = new Tile("door", row, column);
					break;
				case '^':
					room[row][column] = new Tile("stairs", row, column);
					break;
				}
			}
		}
		
		this.xStartPos = xStartPos;
		this.yStartPos = yStartPos;
	}
	
	public int getxStartPos() {
		return xStartPos;
	}
	
	public int getyStartPos() {
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
