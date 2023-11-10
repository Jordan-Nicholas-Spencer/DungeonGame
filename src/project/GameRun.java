package project;

public class GameRun {
    public static void main(String[] args) {
       new Window();
       WorldBuilder world = new WorldBuilder();
       LevelDesign level = new LevelDesign();
       world.renderRoom(level.LEVEL1);
    }
}


