package demolition;

import processing.core.PImage; 

public class Wall extends GameObject{

    public Wall(int x, int y, PImage sprite) {
        super(x, y, sprite);
    }

    public void tick() {
        
    }

    public String toString() {
        return "W";
    }

}
