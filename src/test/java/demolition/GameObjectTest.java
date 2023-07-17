package demolition;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GameObjectTest {
    
    @Test
    public void getXTest() { //check if the GameObject instance return the correct x
        GameObject obj = new Wall(32, 64, null);
        assertEquals(32, obj.getX());
    }

    @Test
    public void getYTest() { //check if the GameObject instance return the correct y
        GameObject obj = new Wall(32, 64, null);
        assertEquals(64, obj.getY());
    }

}
