package demolition;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import processing.core.PImage;

import java.util.List;
import java.util.ArrayList;

public class MapTest {
    
    @Test
    public void constructor() { //check if map is created

        assertNotNull(new Map("config.json", null, null, null,
        null, null, null, null));
    }

    @Test
    public void getCurrLvlMapTest() {
        //check getCurrLvlMap() method

        Map map = new Map("config.json", null, null, null,
        null, null, null, null);

        assertNotNull(map.getCurrLvlMap());
        
    }

    @Test
    public void getCurrLvlTest() {
        //check getCurrLvl() method

        Map map = new Map("config.json", null, null, null,
        null, null, null, null);

        assertNotNull(map.getCurrLvl());
    }

    @Test
    public void readConfigTest() {
        //check readConfig() method

        assertNotNull(Map.readConfig("config.json"));
    }

    @Test
    public void winTest() {
        //check win() method

        Map map = new Map("config.json", null, null, null,
        null, null, null, null);

        map.win();

        assertEquals(true, map.isWin());
    }

    @Test
    public void isWinTest() {
        //check isWin() method

        Map map = new Map("config.json", null, null, null,
        null, null, null, null);

        assertEquals(false, map.isWin());
    }

    @Test
    public void gameOverTest() {
        //check gameOver() method

        Map map = new Map("config.json", null, null, null,
        null, null, null, null);

        map.gameOver();

        assertEquals(true, map.isGameOver());
    }

    @Test
    public void isGameOverTest() {
        //check isGameOver() method

        Map map = new Map("config.json", null, null, null,
        null, null, null, null);

        assertEquals(false, map.isGameOver());
    }

    @Test
    public void resetMapTest() {
        //check resetMap() method

        Map map = new Map("config.json", null, null, null,
        null, null, null, null);

        map.resetMap();

        assertEquals(true, map.reset);
    }

    @Test
    public void getCurrClockTest() {
        //check getCurrClock() method

        Map map = new Map("config.json", null, null, null,
        null, null, null, null);

        assertNotNull(map.getCurrClock());
    }

    @Test
    public void getLivesTest() {
        //check getLives() method

        Map map = new Map("config.json", null, null, null,
        null, null, null, null);
        
        assertNotNull(map.getLives());
    }

    @Test
    public void getCharactersTest() {
        //check getCharacters() method

        Map map = new Map("config.json", null, null, null,
        null, null, null, null);
        
        assertNotNull(map.getCharacters());
    }

    @Test
    public void getObjMapTest() {
        //check getObjMap() method

        Map map = new Map("config.json", null, null, null,
        null, null, null, null);
        
        assertNotNull(map.getObjMap());
    }

    @Test
    public void getPlayerTest() {
        //check getPlayer() method

        List<PImage> dummyImages = new ArrayList<PImage>();

        dummyImages.add(null);

        Map map = new Map("config.json", null, null, null,
        null, dummyImages, dummyImages, dummyImages);

        map.tick();
        
        assertNotNull(map.getPlayer());
    }
    
    @Test
    public void getNumOfLevelTest() {
        //check getNumOfLevel() method

        Map map = new Map("config.json", null, null, null,
        null, null, null, null);
        
        assertNotNull(map.getNumOfLevel());
    }

    @Test
    public void getLevelTest() {
        //check getLevel() method

        Map map = new Map("config.json", null, null, null,
        null, null, null, null);
        
        assertNotNull(map.getLevel());
    }

    @Test
    public void changeLevelTest() {
        //check changeLevel() method

        Map map = new Map("config.json", null, null, null,
        null, null, null, null);
        
        map.changeLevel();

        assertEquals(2, map.getLevel());
    }

    @Test
    public void isResetTimerTest() {
        //check isResetTimer() method 

        Map map = new Map("config.json", null, null, null,
        null, null, null, null);

        assertEquals(false, map.isResetTimer());
    }

    @Test
    public void doneResetTimerTest() {

        List<PImage> dummyImages = new ArrayList<PImage>();
        dummyImages.add(null);

        //reset timer is false here
        Map map = new Map("config.json", null, null, null,
        null, dummyImages, dummyImages, dummyImages);

        //this change reset attr into true
        map.resetMap(); 

        //this will reset the map as weel change resetTimer into true
        map.tick(); 

        //this will change the resetTimer back to false
        map.doneResetTimer();

        assertEquals(false, map.isResetTimer());
    }
}
