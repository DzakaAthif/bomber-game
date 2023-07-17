package demolition;

import processing.core.PImage; 
import processing.core.PApplet;

import java.util.List;
import java.util.ArrayList;

public class BombGuy extends Character {
    
    private int lives;
    public List<Bomb> bombs;
    public boolean hasMoved;

    public BombGuy(int x, int y, List<PImage> sprites, PImage initSprite) {
        super(x, y, sprites, initSprite);
        this.bombs = new ArrayList<Bomb>();
        this.hasMoved = false;
    }

    public void tick() {
        super.tick();

        if (this.map != null) {

            List<List<GameObject>> objMap = this.map.getObjMap();

            if (objMap.size() != 0) {
                int xPos = this.realX/32;
                int yPos = (this.realY-64)/32;

                GameObject obj = objMap.get(yPos).get(xPos);
                if (obj.toString().equals("G")) {
                    this.map.changeLevel();
                    if (this.map.getLevel() > this.map.getNumOfLevel()) {
                        this.map.win();
                    }
                    this.map.resetMap();
                }
            }

            List<Character> characters = map.getCharacters();
            for (Character character : characters) {
                if (this.x == character.getX() && this.y == character.getY()) {
                    this.updLives();
                    if (this.lives == 0)
                        this.map.gameOver();
                    map.resetMap();
                }
            }

        }
        
        if (this.bombs.size() > 0) {
            for (Bomb bomb : this.bombs) {
                if(!(bomb.isDead())) {
                    bomb.tick();
                    if (bomb.isExploded()) {
                        bomb.explode(this.map);
                    }
                }
            }
        }
        
    }

    public void draw(PApplet app) {
        super.draw(app);
        for (Bomb bomb : bombs) {
            if(!(bomb.isDead()))
                bomb.draw(app);
        }

    }

    public void move(Map map) {
        if (!this.hasMoved) {
            this.hasMoved = true;
            List<List<GameObject>> objMap = map.getObjMap();
            List<Integer> coordinate = new ArrayList<Integer>();

            if (this.currDir.equals("down")) 
                coordinate = this.possCoor.get(currDir);
            else if (this.currDir.equals("up")) 
                coordinate = this.possCoor.get(currDir);
            else if (this.currDir.equals("left")) 
                coordinate = this.possCoor.get(currDir);
            else if (this.currDir.equals("right"))
                coordinate = this.possCoor.get(currDir);

            int nextX = coordinate.get(0);
            int nextY = coordinate.get(1);

            int xPos = nextX/32;
            int yPos = (nextY-64)/32;
            
            GameObject obj = objMap.get(yPos).get(xPos);

            if (!(obj.toString().equals("W")) && !(obj.toString().equals("B"))) {
                this.changeCoor(nextX, nextY);
            }
        }
    }
    
    public void createBomb(List<PImage> bombImages, List<PImage> expImages, PImage initBombImage) {
        this.bombs.add(new Bomb(this.realX, this.realY, bombImages, expImages, initBombImage));
    }

    public void releaseKey() {
        this.hasMoved = false;
    }

    public int getLives() {
        return this.lives;
    }

    public void updLives() {
        this.lives--;
    }

    public void updLives(int lives) {
        this.lives = lives;
    }

    public String toString() {
        return "P";
    }

}
