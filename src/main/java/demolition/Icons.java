package demolition;

import processing.core.PImage; 
import processing.core.PApplet;
import processing.core.PFont;

public class Icons extends GameObject {

    private PFont font;
    public int lives;
    public int clock;
    public int counter;
    public int xClock;
    public Map map;
    private PImage clockSprite;

    public Icons(int xLive, int xClock, int y, 
        PImage liveSprite, PImage clockSprite, 
        PFont font, int lives, int clock, Map map) {

        super(xLive, y, liveSprite);
        this.xClock = xClock;
        this.clockSprite = clockSprite;
        this.lives = lives;
        this.font = font;
        this.clock = clock;
        this.counter = 60;
        this.map = map;
    }

    public void tick() {
        this.counter--;
        if (this.counter == 0) {
            this.counter = 60;
            this.clock--;
        }
        if (this.clock == 0) {
            this.map.gameOver();
        }
    }

    public void draw(PApplet app) {
        super.draw(app);
        app.image(clockSprite, xClock, this.y);
        app.textFont(this.font,20);
        app.fill(0);
        //for lives
        app.text(String.format("%d", this.lives), (5*32)+10, 43);
        //for clock
        app.text(String.format("%d", this.clock), (9*32)+10, 43);
    }

    public void updLives(int lives) {
        this.lives = lives;
    }

    public void updClock(int timer, Map map) {
        map.doneResetTimer();
        this.clock = timer;
        this.counter = 60;
    }

}
