package demolition;

import processing.core.PImage;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public abstract class Character extends GameObject {
    
    public String currDir;
    public int imageNum;
    public int counter;
    public int counter1;
    public int realX;
    public int realY;
    public Map map;
    public int heightDiff = 16;

    protected HashMap<String, List<Integer>> possCoor;
    protected final String[] DIRECTIONS = {"down", "up", "left", "right"};
    
    protected List<PImage> down;
    protected List<PImage> up;
    protected List<PImage> left;
    protected List<PImage> right;

    public Character(int x, int y, List<PImage> sprites, PImage initSprite) {
        super(x, y, initSprite);
        this.currDir = "down";
        this.realX = this.x;
        this.realY = this.y + 16;
        this.imageNum = 0;
        this.counter = 60;
        this.counter1 = 0;

        this.possCoor = new HashMap<String, List<Integer>>();

        this.down = new ArrayList<PImage>();
        this.up = new ArrayList<PImage>();
        this.left = new ArrayList<PImage>();
        this.right = new ArrayList<PImage>();

        if(sprites != null) {
            for (int i = 0; i < sprites.size(); i++) {
                if (i >= 0 && i < 4)
                    this.down.add(sprites.get(i));
                else if (i > 3 && i < 8)
                    this.up.add(sprites.get(i));
                else if (i > 7 && i < 12)
                    this.left.add(sprites.get(i));
                else if (i > 11 && i < 16)
                    this.right.add(sprites.get(i));
            }
        }

        this.updPossCoor();
    }

    public abstract void move(Map map);

    public void tick() {
        this.counter--;
        this.counter1++;     
        if (this.counter1 == 12) {
            this.counter1 = 0;
            this.changeImage();
        }
    }

    public void changeImage() {
            
        if (this.imageNum == 3)
            this.imageNum = 0;
        else
            this.imageNum += 1;

        this.setImage(this.currDir);
        
    }

    public void setImage(String direction) {
        if (this.down.size() != 0 && this.up.size() != 0
            && this.left.size() != 0 && this.right.size() != 0) {
            if (direction.equals("down")) 
                this.sprite = this.down.get(this.imageNum);
            else if (direction.equals("up"))
                this.sprite = this.up.get(this.imageNum);
            else if (direction.equals("left"))
                this.sprite = this.left.get(this.imageNum);
            else if (direction.equals("right"))
                this.sprite = this.right.get(this.imageNum);
            }
    }

    public void updPossCoor() {
        Integer[][] nextCoor = {{this.realX, this.realY+32}, 
            {this.realX, this.realY-32}, {this.realX-32, this.realY}, 
            {this.realX+32, this.realY}};

        for (int i = 0; i < nextCoor.length; i++) {
            this.possCoor.put(this.DIRECTIONS[i], Arrays.asList(nextCoor[i]));
        }
    }

    public void changeCoor(int realX, int realY) {
        this.x = realX;
        this.y = realY - this.heightDiff;
        this.realX = this.x;
        this.realY = this.y + this.heightDiff;
        this.updPossCoor();
        this.imageNum = 0;
        this.counter1 = 0;
        this.setImage(this.currDir);
    }

    public void changeDirection(String direction) {
        this.currDir = direction;
    }

    public void updMap(Map map) {
        this.map = map;
    }

    public int getRealX() {
        return this.realX;
    }

    public int getRealY() {
        return this.realY;
    }

}
