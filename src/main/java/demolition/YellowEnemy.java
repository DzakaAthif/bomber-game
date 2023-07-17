package demolition;

import processing.core.PImage; 

import java.util.List;
import java.util.ArrayList;

public class YellowEnemy extends Character{

    public YellowEnemy(int x, int y, List<PImage> sprites, PImage initSprite) {
        super(x, y, sprites, initSprite);
    }

    public void tick() {
        super.tick();
        if (this.counter == 0) {
            this.counter = 60;
            this.move(this.map);
        }
    }

    public void move(Map map) {

        if (map == null)
            return;

        List<List<GameObject>> objMap = map.getObjMap();

        if (objMap.size() == 0)
            return;

        List<Integer> coordinate = new ArrayList<Integer>();
        String possChange = "";

        while (true) {
            if (this.currDir.equals("down")) {
                coordinate = this.possCoor.get(currDir);
                possChange = "left";
            }
            else if (this.currDir.equals("up")) {
                coordinate = this.possCoor.get(currDir);
                possChange = "right";
            }
            else if (this.currDir.equals("left")) {
                coordinate = this.possCoor.get(currDir);
                possChange = "up";
            }
            else if (this.currDir.equals("right")) {
                coordinate = this.possCoor.get(currDir);
                possChange = "down";
            }

            int nextX = coordinate.get(0);
            int nextY = coordinate.get(1);

            int xPos = nextX/32;
            int yPos = (nextY-64)/32;
            
            GameObject obj = objMap.get(yPos).get(xPos);

            if (obj.toString().equals("W") || obj.toString().equals("B")) {
                this.changeDirection(possChange);
                continue;
            }
            else {
                this.changeCoor(nextX, nextY);
                break;
            }
        }
    }
    
    public String toString() {
        return "Y";
    }
}