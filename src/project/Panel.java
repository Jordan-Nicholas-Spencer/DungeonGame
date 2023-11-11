package project;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;


public class Panel extends JPanel {
    
	private int playerX = 50; // Initial player X position
    private int playerY = 50; // Initial player Y position    
    
    public Panel() 
    {
    	super();
    	
        
        setLayout(new GridLayout(world.getCurrentRoom().getSizeY(), world.getCurrentRoom().getSizeX()));
        for(int column=0; column < world.getCurrentRoom().getSizeY(); column++) {
			for(int row=0; row < world.getCurrentRoom().getSizeX(); row++) {
				JPanel type = new JPanel();
        		switch (world.getCurrentRoom().getTileAt(row, column).getName()) {
        		case "wall":
        			type.setBackground(Color.GRAY);
        			add(type);
        			break;
        		case "floor":
        			type.setBackground(new Color(123,63,0));
        			add(type);
        			break;
        		case "door":
        			type.setBackground(Color.BLACK);
        			add(type);
        			break;
        		case "stairs":
        			type.setBackground(Color.RED);
        			add(type);
        			break;
        		}
			}
		}
        JPanel player = new JPanel();
        player.setBackground(Color.GREEN);
        world.getPlayer();
      
    }

    @Override
    protected void paintComponent(Graphics g) 
    {
        super.paintComponent(g);
    }

   
}

    



