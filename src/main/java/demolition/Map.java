package demolition;

import processing.core.PImage; 
import processing.core.PApplet;
import processing.data.JSONObject;
import processing.data.JSONArray;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Map {
    
    private JSONObject config;

    private int numOfLevel;
    private int currLevel;
    private int heigtDiff;
    public boolean reset;
    private boolean resetTimer;
    private boolean win;
    private boolean gameOver;

    private List<List<GameObject>> backObjects;
    private List<Character> characters;
    private BombGuy player;

    private PImage solidImage;
    private PImage brokenImage;
    private PImage emptyImage;
    private PImage goalImage;
    private List<PImage> bGImages;
    private List<PImage> rEImages;
    private List<PImage> yEImages;

    public Map(String configFile, PImage solidImage, 
        PImage brokenImage, PImage emptyImage, 
        PImage goalImage, List<PImage> bGImages,
        List<PImage> rEImages, List<PImage> yEImages) {

        String configContent = readConfig(configFile);
        this.config = JSONObject.parse(configContent);
        this.numOfLevel = this.config.getJSONArray("levels").size();

        this.currLevel = 1;
        this.heigtDiff = 16;
        this.reset = false;
        this.resetTimer = false;
        this.win = false;
        this.gameOver = false;

        this.backObjects = new ArrayList<List<GameObject>>();
        this.characters = new ArrayList<Character>();
    
        this.solidImage = solidImage;
        this.brokenImage = brokenImage;
        this.emptyImage = emptyImage;
        this.goalImage = goalImage;
        this.bGImages = bGImages;
        this.rEImages = rEImages;
        this.yEImages = yEImages;
    }

    public void tick() {

        if (this.backObjects.size() == 0 || this.reset) {

            if (this.reset) {
                this.reset = false;
                this.resetTimer = true;
                this.backObjects.clear();
                this.characters.clear();
                this.player = null;
            }

            List<String[]> map = this.getCurrLvlMap();
            
            for (int i = 0; i < map.size(); i++) {

                int y = (i * 32) + 64;
                int yChar = y - this.heigtDiff;
                String[] line = map.get(i);
                List<GameObject> aLineOfObj = new ArrayList<GameObject>();
                
                for (int j = 0; j < line.length; j++) {
                    int x = j * 32;
                    String obj = line[j];

                    if (obj.equals("W"))
                        aLineOfObj.add(new Wall(x, y, this.solidImage));
                    else if (obj.equals("B"))
                        aLineOfObj.add(new BrokenWall(x, y, this.brokenImage));
                    else if (obj.equals("G"))
                        aLineOfObj.add(new Goal(x, y, this.goalImage));
                    else
                        aLineOfObj.add(new EmptySpace(x, y, this.emptyImage));
                        if (obj.equals("P")) {
                            this.player = new BombGuy(x, yChar, this.bGImages, 
                                this.bGImages.get(0));
                        }          
                        else if (obj.equals("R")) {
                            this.characters.add(new RedEnemy(x, yChar, this.rEImages, 
                                this.rEImages.get(0)));
                        }
                        else if (obj.equals("Y")) {
                            this.characters.add(new YellowEnemy(x, yChar, this.yEImages, 
                                this.yEImages.get(0)));
                        }
                }
                
                this.backObjects.add(aLineOfObj);
            }
        }
    }

    public void draw(PApplet app) {

        List<List<GameObject>> map = this.backObjects;

        for (int i = 0; i < map.size(); i++) {
            List<GameObject> line = map.get(i);

            for (int j = 0; j < line.size(); j++) {
                GameObject obj = line.get(j);
                obj.draw(app);
            }

        }
        
    }

    public List<String[]> getCurrLvlMap() {

        JSONObject currLvl = getCurrLvl();
        
        if (currLvl == null)
            return null;

        String fileName = currLvl.getString("path");
        List<String> configContent = new ArrayList<String>();

        try {

            File f = new File(fileName);
            Scanner scan = new Scanner(f);
            while (scan.hasNextLine())
                configContent.add(scan.nextLine());
            
            scan.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        List<String[]> map = new ArrayList<String[]>();

        for (String line : configContent) {
            String[] arrOfLine = line.split("");
            map.add(arrOfLine);
        }

        return map;

    }

    public JSONObject getCurrLvl() {

        String fileName = String.format("level%s.txt", this.currLevel);
        JSONArray levels = this.config.getJSONArray("levels");
        JSONObject foundLevel = null;

        for (int i = 0; i < levels.size(); i++) {
            JSONObject level = levels.getJSONObject(i);
            if (level.getString("path").equals(fileName)) {
                foundLevel = level;
                break;
            }
        }

        return foundLevel;
    }

    public static String readConfig(String configFile) {

        String configContent = "";

        try {
            File f = new File(configFile);
            Scanner scan = new Scanner(f);

            while (scan.hasNextLine())
                configContent += scan.nextLine();
            
            scan.close();
            return configContent;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void win() {
        this.win = true;
    }

    public boolean isWin() {
        return this.win;
    }

    public void gameOver() {
        this.gameOver = true;
    }

    public boolean isGameOver() {
        return this.gameOver;
    }

    public void resetMap() {
        this.reset = true;
    }

    public int getCurrClock() {
        JSONObject currLvl = getCurrLvl();
        return currLvl.getInt("time");
    }

    public int getLives() {
        return this.config.getInt("lives");
    }

    public List<Character> getCharacters() {
        return this.characters;
    }

    public List<List<GameObject>> getObjMap() {
        return this.backObjects;
    }

    public BombGuy getPlayer() {
        return this.player;
    }
    
    public int getNumOfLevel() {
        return this.numOfLevel;
    }

    public int getLevel() {
        return this.currLevel;
    }

    public void changeLevel() {
        this.currLevel += 1;
    }

    public PImage getEmptyImage() {
        return this.emptyImage;
    }

    public boolean isResetTimer() {
        return this.resetTimer;
    }

    public void doneResetTimer() {
        this.resetTimer = false;
    }

}
