package project;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;


public class Panel extends JPanel {

    @Override
    protected void paintComponent(Graphics g) 
    {
        super.paintComponent(g);
        drawSquares(g);
        repaint();
    }
    
    public void drawSquares(Graphics g) {
    	g.setColor(Color.GREEN);
    	g.fillRect(100, 100, 30, 20);
    }
    
	
    public Panel() 
    {
    	super();
    	WorldController controller = new WorldController();
        GridLayout theGrid = new GridLayout(controller.getWorld().getCurrentRoom().getSizeY(), controller.getWorld().getCurrentRoom().getSizeX());
        setLayout(theGrid);
        for(int column=0; column < controller.getWorld().getCurrentRoom().getSizeY(); column++) {
			for(int row=0; row < controller.getWorld().getCurrentRoom().getSizeX(); row++) {
				JPanel type = new JPanel();
        		switch (controller.getWorld().getCurrentRoom().getTileAt(row, column).getName()) {
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
    }
}

    



