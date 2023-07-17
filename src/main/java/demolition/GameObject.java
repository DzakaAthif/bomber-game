package demolition;

import processing.core.PImage; 
import processing.core.PApplet; 

public abstract class GameObject {
    protected int x;
    protected int y;

    protected PImage sprite;

    public GameObject(int x, int y, PImage sprite) {
        this.x = x;
        this.y = y;
        this.sprite = sprite;
    }

    public abstract void tick();

    public void draw(PApplet app) {
        app.image(this.sprite, this.x, this.y);
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getWidth() {
        return this.sprite.width;
    }

    public int getHeight() {
        return this.sprite.height;
    }
}
