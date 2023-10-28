package project;

import javax.swing.JFrame;


public class GameRun {
    public static void main(String[] args) {
        JFrame frame = new JFrame("ZOMBIE HELL!");
        GamePanel gamePanel = new GamePanel();
        frame.add(gamePanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600); // Set the size as per your requirements
        frame.setVisible(true);
    }
}


