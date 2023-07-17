package demolition;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BombGuyTest {
    
    @Test 
    public void constructor() { //check if BombGuy is created

        assertNotNull(new BombGuy(32, 64, null, null));
    }

    @Test 
    public void createBombTest() { //check if createBomb can create bomb

        BombGuy player = new BombGuy(32, 64, null, null);

        player.createBomb(null, null, null);

        assertEquals(1, player.bombs.size());

    }

    @Test
    public void releaseKeyTest() { 
        //check if releaseKey can update hasMoved
        //attribute

        BombGuy player = new BombGuy(32, 64, null, null);

        player.hasMoved = true;

        player.releaseKey();

        assertEquals(false, player.hasMoved);
    }


    @Test
    public void updAndGetLivesTest() {
        //Check both updLives() method that they can update
        //lives attribute. Check if getLives can return 
        //lives attribute

        BombGuy player = new BombGuy(32, 64, null, null);
        
        player.updLives(3);

        assertEquals(3, player.getLives());

        player.updLives();

        assertEquals(2, player.getLives());
    }

    @Test
    public void toStringTest() { //check if toString() of BombGuy is "P"

        BombGuy player = new BombGuy(32, 64, null, null);
        
        assertEquals("P", player.toString());
    }

    @Test
    public void updMapBombTest() {
        //check updMap() method in Bomb class

        Bomb bomb = new Bomb(32, 64, null, null, null);

        bomb.updMap(new Map("config.json", null, null, null,
        null, null, null, null));

        assertNotNull(bomb.map);
    } 

    @Test
    public void isExplodedTest() {
        //check isExploded() method in Bomb class

        Bomb bomb = new Bomb(32, 64, null, null, null);

        assertEquals(false, bomb.isExploded());
    }

    @Test
    public void isDeadTest() {
        //check isDead() method in Bomb class

        Bomb bomb = new Bomb(32, 64, null, null, null);

        assertEquals(false, bomb.isDead());
    }
}
