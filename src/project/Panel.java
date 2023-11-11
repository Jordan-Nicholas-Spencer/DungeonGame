package project;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Graphics;

public class Panel extends JPanel {
 
	private WorldController controller;
	
    public Panel() {
    	super();
		this.setFocusable(true);
		//this.addKeyListener(this);
    	this.controller = new WorldController();
       
        
    }
    

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        try {
        	g.setColor(Color.BLACK);
        	g.fillRect(0, 0, Window.WIDTH, Window.HEIGHT);
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
        } catch (Exception e) {
        	System.out.print("Error rendering");
        }
        repaint();
    }
}

    



