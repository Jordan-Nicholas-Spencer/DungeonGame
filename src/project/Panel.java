package project;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Panel extends JPanel implements KeyListener, ActionListener 
{
    
    LevelDesign level = new LevelDesign();
    private WorldBuilder world = new WorldBuilder();
	private int playerX = 50; // Initial player X position
    private int playerY = 50; // Initial player Y position    
    
    public Panel() 
    {
    	super();
        setFocusable(true);
        addKeyListener(this);
        setLayout(new GridLayout(level.LEVEL1.getSizeX(), level.LEVEL1.getSizeY()));
        
        for (int row = 0; row < level.LEVEL1.getSizeX(); row++) {
        	for (int column = 0; column < level.LEVEL1.getSizeY(); column ++) {
        		JPanel type = new JPanel();
        		switch (world.renderRoom(level.LEVEL1, null)[row][column]) {
        		case "wall":
        			type.setBackground(Color.WHITE);
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
        			type.setBackground(Color.GRAY);
        			add(type);
        			break;
        		}
        		
        	}
        }
        
        // Start a game timer to handle animation and updates
        javax.swing.Timer timer = new javax.swing.Timer(0, this); // 100ms interval
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) 
    {
        super.paintComponent(g);

        try {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());
       
        // Draw the player
        g.setColor(Color.WHITE);
        g.fillOval(playerX, playerY, 50, 50);
        g.setColor(Color.GREEN);
        g.fillRect(250, 250, 50, 50);
        } catch (Exception e) {
			System.err.println("\n[Logic][GameLoop]: Uncaught exception in render system!\n");
			e.printStackTrace();
			System.exit(-1);
		}
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Implement keyTyped if needed
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        // Implement player movement based on key presses
        if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_S) {
            playerX -= 10;
        } else if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) {
            playerX += 10;
        } else if (key == KeyEvent.VK_UP || key == KeyEvent.VK_W) {
            playerY -= 10;
        } else if (key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) {
            playerY += 10;
        }
    }

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
    @Override
    public void actionPerformed(ActionEvent e) 
    {
        // Implement game logic and updates here
        repaint();
    }
}

    



