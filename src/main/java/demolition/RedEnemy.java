package demolition;

import processing.core.PImage;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import java.util.HashMap;
import java.util.Set;

public class RedEnemy extends Character{

    private Random rand;

    public RedEnemy(int x, int y, List<PImage> sprites, PImage initSprite) {
        super(x, y, sprites, initSprite);
        this.rand = new Random();
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

        HashMap<String, List<Integer>> availableCoor = new HashMap<String, List<Integer>>();
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

        if (obj.toString().equals("W") || obj.toString().equals("B")) {
            for (String direction : possCoor.keySet()) {
                coordinate = possCoor.get(direction);

                nextX = coordinate.get(0);
                nextY = coordinate.get(1);

                xPos = nextX/32;
                yPos = (nextY-64)/32;
                
                obj = objMap.get(yPos).get(xPos);
                if (!(obj.toString().equals("W")) && !(obj.toString().equals("B")))
                    availableCoor.put(direction, coordinate);
            }

            int random = this.rand.nextInt(availableCoor.size());

            Set<String> setKey = availableCoor.keySet();
            String[] keys = new String[setKey.size()];
            setKey.toArray(keys);

            String direction = keys[random];
            coordinate = availableCoor.get(direction);
            this.currDir = direction;

            nextX = coordinate.get(0);
            nextY = coordinate.get(1);
        }

        this.changeCoor(nextX, nextY);
        
    }

    public String toString() {
        return "R";
    }
    
}
