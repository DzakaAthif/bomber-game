package demolition;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class IconsTest {

    @Test
    public void constructor() { // check if the instance of Icons is created

        Map map = new Map("config.json", null, null, null,
        null, null, null, null);

        assertNotNull(new Icons(4*32, 8*32, 16, null, 
            null, null, 3, 180, map));
    }

    @Test
    public void tickCounterTest() { 
        //check if tick() method is able to update
        //the counter attribute of icons

        Map map = new Map("config.json", null, null, null,
        null, null, null, null);

        Icons icons = new Icons(4*32, 8*32, 16, null, 
            null, null, 3, 180, map);

        icons.tick();

        assertEquals(59, icons.counter);
    }

    @Test
    public void tickClockTest() { 
        //check if tick() method is able to update
        //the clock attribute of icons

        Map map = new Map("config.json", null, null, null,
        null, null, null, null);

        Icons icons = new Icons(4*32, 8*32, 16, null, 
            null, null, 3, 180, map);

        for (int i = 0; i < 60; i++) {
            icons.tick();
        }

        assertEquals(179, icons.clock);
    }

    @Test
    public void updLivesTest() { 
        //check if updLives() method is able to update lives
        //attribute

        Map map = new Map("config.json", null, null, null,
        null, null, null, null);

        Icons icons = new Icons(4*32, 8*32, 16, null, 
            null, null, 3, 180, map);

        icons.updLives(2);

        assertEquals(2, icons.lives);
    }

    @Test
    public void updClockTest() { 
        //check if updClock() method is able to update
        //the clock attribute of icons
        Map map = new Map("config.json", null, null, null,
        null, null, null, null);
        Icons icons = new Icons(4*32, 8*32, 16, null, 
            null, null, 3, 180, map);

        icons.updClock(100, map);

        assertEquals(100, icons.clock);
    }


}
