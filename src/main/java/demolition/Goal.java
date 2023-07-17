package demolition;

import processing.core.PImage; 

public class Goal extends EmptySpace{
    public Goal(int x, int y, PImage sprite) {
        super(x, y, sprite);
    }

    public String toString() {
        return "G";
    }
}
