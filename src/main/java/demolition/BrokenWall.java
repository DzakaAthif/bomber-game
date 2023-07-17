package demolition;

import processing.core.PImage; 

public class BrokenWall extends Wall {
    
    public BrokenWall(int x, int y, PImage sprite) {
        super(x, y, sprite);
    }

    public String toString() {
        return "B";
    }
}
