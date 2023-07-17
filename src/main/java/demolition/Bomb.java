package demolition;

import processing.core.PImage; 
import processing.core.PApplet; 

import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Arrays;

public class Bomb extends GameObject{
    
    private List<PImage> bombImages;
    private HashMap<String, PImage> expImages;
    private HashMap<List<Integer>, PImage> possImages;
    private HashMap<List<Integer>, PImage> selImages;
    private HashMap<String, List<Integer>> nextCoor1;
    private HashMap<String, List<Integer>> nextCoor2;
    private boolean exploded;
    private boolean startExplosion;
    private boolean dead;
    public Map map;
    private int explodeCount;
    private int counter;
    private int counter1;
    private int imageNum;

    public Bomb(int x, int y, List<PImage> bombSprites, List<PImage> expSprites, PImage initBombImage) {
        super(x, y, initBombImage);

        this.possImages = new HashMap<List<Integer>, PImage>();
        this.selImages = new HashMap<List<Integer>, PImage>();
        this.nextCoor1 = new HashMap<String, List<Integer>>();
        this.nextCoor2 = new HashMap<String, List<Integer>>();
        this.bombImages = bombSprites;
        this.expImages = new HashMap<String, PImage>();

        if (expSprites != null) {
            String[] pos = {"centre", "horizontal", "vertical", "end_bottom", 
                "end_left", "end_right", "end_top"};

            for (int i = 0; i < pos.length; i++) {
                this.expImages.put(pos[i], expSprites.get(i));
            }
        }

        this.getSelImages();

        this.exploded = false;
        this.startExplosion = false;
        this.dead = false;
        this.counter = 15;
        this.counter1 = 30;
        this.imageNum = 0;
        this.explodeCount = 0;
    }

    public void tick() {
        this.counter--;
        if (this.counter == 0) {
            this.counter = 15;
            if (this.exploded == false)
                this.changeImage();
        }
        if (this.startExplosion) {
            this.counter1--;
            if (this.counter1 == 0) {
                this.dead = true;
            }
        }
    }

    public void draw(PApplet app) {
        if (this.startExplosion) {
            this.drawExplosion(app);
        } else
            super.draw(app);
    }

    public void changeImage() {
        if (this.imageNum == 7)
            this.exploded = true;
        else
            this.imageNum += 1;

        this.sprite = this.bombImages.get(this.imageNum);
    }

    public void explode(Map map) {
        this.startExplosion = true;
        this.explodeCount+=1;
        if (explodeCount == 1) {
            List<List<GameObject>> objMap = map.getObjMap();
            String[] dirs = {"bottom", "top", "left", "right"};

            for (String direction : dirs) {
                int index = 1;
                HashMap<String, List<Integer>> nextCoor = this.nextCoor1;
                while (true) {
                    String key = String.format("%s%d", direction, index);
                    List<Integer> coordinate = nextCoor.get(key);
                    int xPos = coordinate.get(0)/32;
                    int yPos = (coordinate.get(1)-64)/32;

                    GameObject obj = objMap.get(yPos).get(xPos);
                    
                    if (!(obj.toString().equals("W"))) {
                        this.selImages.put(coordinate, this.possImages.get(coordinate));
                        if (index == 1 && !(obj.toString().equals("B"))) {
                            index+=1;
                            nextCoor = this.nextCoor2;
                            continue;
                        }
                    }
                    break;
                }
            }

            List<Character> characters = map.getCharacters();
            BombGuy player = map.getPlayer();

            List<Character> removeChars = new ArrayList<Character>();
            List<List<Integer>> removeObj =  new ArrayList<List<Integer>>();
            PImage emptyImage = map.getEmptyImage();

            for (List<Integer> coordinate : this.selImages.keySet()) {
                    int xPos = coordinate.get(0);
                    int yPos = coordinate.get(1);

                    for (Character character : characters) {
                        int xChar = character.getRealX();
                        int yChar = character.getRealY();
                        if (xChar == xPos && yChar == yPos) {
                            removeChars.add(character);
                        }
                    }

                    if (player.getRealX() == xPos && player.getRealY() == yPos) {
                        player.updLives();
                        if (player.getLives() == 0)
                            map.gameOver();
                        map.resetMap();
                    }

                    int xGrid = xPos/32;
                    int yGrid = (yPos-64)/32;

                    GameObject obj = objMap.get(yGrid).get(xGrid);

                    if(obj.toString().equals("B")) {
                        removeObj.add(coordinate);
                    }
            }

            for (Character character : removeChars) {
                characters.remove(character);
            }
            
            for (List<Integer> coordinate : removeObj) {
                int xPos = coordinate.get(0);
                int yPos = coordinate.get(1);
                int xGrid = xPos/32;
                int yGrid = (yPos-64)/32;
                objMap.get(yGrid).set(xGrid, new EmptySpace(xPos, yPos, emptyImage));
            }
        }
    }

    public void drawExplosion(PApplet app) {
        for (List<Integer> coordinate : this.selImages.keySet()) {
            app.image(this.selImages.get(coordinate), coordinate.get(0), coordinate.get(1));
        }
    }

    public void getSelImages() {

        Integer[][] possCoor1 = {{this.x, this.y+32}, 
            {this.x, this.y-32}, {this.x-32, this.y}, 
            {this.x+32, this.y}};
        Integer[][] possCoor2 = {{this.x, this.y+64}, 
            {this.x, this.y-64}, {this.x-64, this.y}, 
            {this.x+64, this.y}};

        Integer[] centreCoor = {this.x, this.y};
        this.selImages.put(Arrays.asList(centreCoor), 
            this.expImages.get("centre"));

        String[] keys1 = {"vertical", "vertical", "horizontal", "horizontal"};
        String[] keys2 = {"end_bottom", "end_top", "end_left", "end_right"};
        String[] dir1 = {"bottom1", "top1", "left1", "right1"};
        String[] dir2 = {"bottom2", "top2", "left2", "right2"};

        for (int i = 0; i < possCoor1.length; i++) {
            List<Integer> coordinate1 = Arrays.asList(possCoor1[i]);
            List<Integer> coordinate2 = Arrays.asList(possCoor2[i]);

            this.possImages.put(coordinate1, this.expImages.get(keys1[i]));
            this.possImages.put(coordinate2, this.expImages.get(keys2[i]));
            this.nextCoor1.put(dir1[i], coordinate1);
            this.nextCoor2.put(dir2[i], coordinate2);

        }

    }

    public void updMap(Map map) {
        this.map = map;
    } 

    public boolean isExploded() {
        return this.exploded;
    }

    public boolean isDead() {
        return this.dead;
    }
    
}
