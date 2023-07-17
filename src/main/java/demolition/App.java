package demolition;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PFont;
import processing.event.KeyEvent;

import java.util.ArrayList;
import java.util.List;

public class App extends PApplet {

    public static final int WIDTH = 480;
    public static final int HEIGHT = 480;

    public static final int FPS = 60;

    private final String CONFIGFILENAME = "config.json";
    private Map map;
    private int lives;
    private int timer;

    private BombGuy player;
    private Icons icons;
    private List<Character> characters;

    private PFont font;

    public PImage solidImage;
    public PImage brokenImage;
    public PImage emptyImage;
    public PImage goalImage;
    public PImage liveImage;
    public PImage clockImage;
    private List<PImage> bombImages;
    private List<PImage> expImages;
    private List<PImage> bGImages;
    private List<PImage> rEImages;
    private List<PImage> yEImages;

    public App() {
        this.characters =  new ArrayList<Character>();

        this.bombImages = new ArrayList<PImage>();
        this.expImages = new ArrayList<PImage>();
        this.bGImages = new ArrayList<PImage>();
        this.rEImages = new ArrayList<PImage>();
        this.yEImages = new ArrayList<PImage>();
    }

    public void settings() {
        size(WIDTH, HEIGHT);
    }

    public void setup() {
        frameRate(FPS);

        this.font = createFont("src/main/resources/PressStart2P-Regular.ttf", 16, true);

        // Load images during setup
        this.solidImage = this.loadImage("src/main/resources/wall/solid.png");
        this.brokenImage = this.loadImage("src/main/resources/broken/broken.png");
        this.emptyImage = this.loadImage("src/main/resources/empty/empty.png");
        this.goalImage = this.loadImage("src/main/resources/goal/goal.png");
        this.liveImage = this.loadImage("src/main/resources/icons/player.png");
        this.clockImage = this.loadImage("src/main/resources/icons/clock.png");

        String[] pos = {"centre", "horizontal", "vertical", "end_bottom", 
            "end_left", "end_right", "end_top"};

        for (int i = 0; i < 8; i++) {
            int index = i + 1;
            String bombPath = String.format("src/main/resources/bomb/bomb%d.png", index);
            PImage bombSprite = this.loadImage(bombPath);
            this.bombImages.add(bombSprite);

            if (i < 7) {
                String expPath = String.format("src/main/resources/explosion/%s.png", pos[i]);
                PImage expSprite = this.loadImage(expPath);
                this.expImages.add(expSprite);
            }
            
        }

        String[] directions = {"_down", "_up", "_left", "_right"};

        for (String direction : directions) {
            for (int i = 0; i < 4; i++) {
                int index = i + 1;
                String bGPath = String.format("src/main/resources/player/player%s%d.png", direction, index);
                PImage bGSprite = this.loadImage(bGPath);

                String rEPath = String.format("src/main/resources/red_enemy/red%s%d.png", direction, index);
                PImage rESprite = this.loadImage(rEPath);
                
                String yEPath = String.format("src/main/resources/yellow_enemy/yellow%s%d.png", direction, index);
                PImage yESprite = this.loadImage(yEPath);

                this.bGImages.add(bGSprite);
                this.rEImages.add(rESprite);
                this.yEImages.add(yESprite);
            }
        }

        this.map = new Map(CONFIGFILENAME, this.solidImage, 
            this.brokenImage, this.emptyImage, 
            this.goalImage, this.bGImages, 
            this.rEImages, this.yEImages);

        this.lives = this.map.getLives();
        this.timer = this.map.getCurrClock();
        this.icons = new Icons(4*32, 8*32, 16, this.liveImage, 
            this.clockImage, this.font, this.lives, 
            this.timer, this.map);
        
    }

    public void draw() {

        background(239, 129, 0);
        textFont(this.font, 20);
        fill(0);

        if (this.map.isWin()) {
            text("YOU WIN", 160, 220);

        } else if (this.map.isGameOver()) {
            text("GAME OVER", 145, 220);

        } else {
            this.map.tick();
            this.characters = this.map.getCharacters();
            this.player = this.map.getPlayer();

            this.player.updLives(this.lives);        
            this.player.updMap(this.map);
            this.player.tick();

            this.lives = this.player.getLives();
            this.icons.updLives(this.lives);
            if (this.map.isResetTimer())
                this.icons.updClock(this.map.getCurrClock(), this.map);
            this.icons.tick();

            for (Character character : this.characters) {
                character.updMap(this.map);
                character.tick();
            }

            //######## for drawing #########

            this.map.draw(this);

            this.icons.draw(this);

            for (Character character : this.characters) {
                character.draw(this);
            }

            this.player.draw(this);
        }

    }

    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if (keyCode == 32 ) {
            this.player.createBomb(this.bombImages, this.expImages, 
                this.bombImages.get(0));
            return;
        }

        if (keyCode == 39 ) {
            this.player.changeDirection("right");
        } else if (keyCode == 37 ) {
            this.player.changeDirection("left");
        } else if (keyCode == 38 ) {
            this.player.changeDirection("up");
        } else if (keyCode == 40 ) {
            this.player.changeDirection("down");
        }
        this.player.move(this.map);
        
    }

    public void keyReleased() {
        this.player.releaseKey();
    }

    public static void main(String[] args) {
        PApplet.main("demolition.App");
    }
}
