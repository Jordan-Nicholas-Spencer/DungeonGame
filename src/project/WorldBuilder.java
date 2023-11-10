package project;

import java.awt.Graphics;

public class WorldBuilder {
	
	public void renderRoom(Room currentRoom, Graphics graphics) {
		for(int row=0; row<currentRoom.getSizeX(); row++) {
			for(int column=0; column<currentRoom.getSizeY(); column++) {
				currentRoom.getTileAt(row, column).getName();
			}
		}
	}
}
