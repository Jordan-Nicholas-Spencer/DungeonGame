package project;

public class Room {

	private Tile[][] room;
	private int xStartPos;
	private int yStartPos;
	
	public Room(String[] levelDesign, int xStartPos, int yStartPos) {
		room = new Tile[levelDesign.length][];
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
				case '|':
					room[column][row] = new Tile("door", row, column);
					break;
				case '^':
					room[column][row] = new Tile("stairs", row, column);
					break;
				case 'p':
					room[column][row] = new Tile("player", row, column);
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
