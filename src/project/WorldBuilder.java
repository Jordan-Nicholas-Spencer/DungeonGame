package project;

import java.awt.Graphics;

public class WorldBuilder {
	
	public void renderRoom(Room currentRoom) {
		// this makes an array to store the classifications of the characters in the room
		String[][] classificationArray = new String[currentRoom.getSizeX()][currentRoom.getSizeY()];
		
		for(int column=0; column<currentRoom.getSizeY(); column++) {
			for(int row=0; row<currentRoom.getSizeX(); row++) {
				classificationArray[row][column] = currentRoom.getTileAt(row, column).getName();
			}
		}
		
		for(int column=0; column<currentRoom.getSizeY(); column++) {
			for(int row=0; row<currentRoom.getSizeX(); row++) {
				System.out.print(classificationArray[row][column]);
			}
			System.out.println();
		}
		
	}
}
