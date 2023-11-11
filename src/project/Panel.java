package project;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

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
        
        // render level
        try {
        	g.setColor(Color.BLACK);
        	g.fillRect(0, 0, Window.WIDTH, Window.HEIGHT);
        	
        	for(int column=0; column < controller.getWorld().getCurrentRoom().getSizeY(); column++) {
    			for(int row=0; row < controller.getWorld().getCurrentRoom().getSizeX(); row++) {
    				BufferedImage sprite = Images.getSprite(controller.getWorld().getCurrentRoom().getTileAt(row, column).getName());
    				int drawPosX = controller.getWorld().getCurrentRoom().getTileAt(row, column).getPosX() * sprite.getWidth();
    				int drawPosY = controller.getWorld().getCurrentRoom().getTileAt(row, column).getPosY() * sprite.getWidth();
    				g.drawImage(sprite, drawPosX, drawPosY, sprite.getWidth(), sprite.getHeight(), null);
    			}
    		}
        } catch (Exception e) {
        	System.out.print("Error rendering ");
        }
        
        // render player
//        BufferedImage sprite = Images.getSprite("player");
//        g.drawImage(sprite, 9 * 50, 2 * 50, sprite.getWidth(), sprite.getHeight(), null);
        repaint();
    }
}

    



