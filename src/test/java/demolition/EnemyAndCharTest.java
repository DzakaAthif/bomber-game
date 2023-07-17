package demolition;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EnemyAndCharTest {
    
    @Test
    public void redConstructor() { //chceck if the RedEnemy is successfully created
        assertNotNull(new RedEnemy(32, 64, null, null));
    }

    @Test
    public void redToString() { //check if the RedEnemy toString() is "R"
        RedEnemy red = new RedEnemy(32, 64, null, null);
        assertEquals("R", red.toString());
    }

    @Test
    public void yellowConstructor() { //chceck if the YellowEnemy is successfully created
        assertNotNull(new YellowEnemy(32, 64, null, null));
    }

    @Test
    public void yellowToString() { //check if the YellowEnemy toString() is "Y"
    YellowEnemy yellow = new YellowEnemy(32, 64, null, null);
        assertEquals("Y", yellow.toString());
    }

    @Test
    public void tickCounter() { 
        //check if tick is able to change the counter
        // and counter1 attributes of Character

        Character character = new RedEnemy(32, 64, null, null);

        character.tick();
        assertEquals(59, character.counter);
        assertEquals(1, character.counter1);
    }

    @Test
    public void tickChangeImage() { 
        //check if tick is able to change the image
        //for cycle animation purpose
        
        Character character = new RedEnemy(32, 64, null, null);

        for (int i = 0; i < 12; i++) 
            character.tick();

        assertEquals(1, character.imageNum);

        for (int i = 0; i < 36; i++) 
            character.tick();

        assertEquals(0, character.imageNum);
    }

    @Test
    public void changeCoorTest() {
        //check if the changeCoor() can change the 
        // x and y of the character

        Character character = new RedEnemy(32, 64, null, null);

        character.changeCoor(64, 112);

        assertEquals(64, character.x);
        assertEquals(96, character.y);
    
    }

    @Test
    public void changeDirectionTest() {
        //check if the changeDirection() can change the 
        //direction of the character

        Character character = new RedEnemy(32, 64, null, null);

        character.changeDirection("left");

        assertEquals("left", character.currDir);
    
    }

    @Test
    public void updMapTest() {
        //check if the updMap() can update the 
        //map attribute of the character

        Character character = new RedEnemy(32, 64, null, null);

        character.updMap(new Map("config.json", null, null, null,
        null, null, null, null));

        assertNotNull(character.map);
    
    }

    @Test
    public void getRealCoordinateTest() {
        //check if the updMap() can update the 
        //map attribute of the character

        Character character = new RedEnemy(32, 64, null, null);

        assertEquals(32, character.getRealX());
        assertEquals(80, character.getRealY());
    
    }

    @Test
    public void tickRed() {
        //check if the tick() method works in RedEnemy

        RedEnemy red = new RedEnemy(32, 64, null, null);

        for (int i = 0; i < 60; i++) {
            red.tick();
        }

        assertEquals(60, red.counter);
    
    }

    @Test
    public void tickRedMove() {
        //check if the tick() method can elicit move()
        //in RedEnemy

        RedEnemy red = new RedEnemy(32, 64, null, null);

        red.updMap(new Map("config.json", null, null, null,
        null, null, null, null));

        for (int i = 0; i < 60; i++) {
            red.tick();
        }

        assertEquals(60, red.counter);
    
    }

    @Test
    public void tickYellow() {
        //check if the tick() method works in YellowEnemy

        YellowEnemy yellow = new YellowEnemy(32, 64, null, null);

        for (int i = 0; i < 60; i++) {
            yellow.tick();
        }

        assertEquals(60, yellow.counter);
    
    }

    @Test
    public void tickYellowMove() {
        //check if the tick() method can elicit move()
        //in RedEnemy

        YellowEnemy yellow = new YellowEnemy(32, 64, null, null);

        yellow.updMap(new Map("config.json", null, null, null,
        null, null, null, null));

        for (int i = 0; i < 60; i++) {
            yellow.tick();
        }

        assertEquals(60, yellow.counter);
    
    }
}
