package demolition;

import processing.core.PImage; 

public class EmptySpace extends GameObject{

    public EmptySpace(int x, int y, PImage sprite) {
        super(x, y, sprite);
    }

    public void tick() {
        
    }

    public String toString() {
        return " ";
    }
}
