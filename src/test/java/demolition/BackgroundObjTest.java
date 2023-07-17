package demolition;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BackgroundObjTest {

    @Test
    public void wallConstructor(){ //check if the solid wall is created
        assertNotNull(new Wall(32, 64, null));
    }

    @Test
    public void wallToString(){ //check if walls's toString is W
        Wall wall = new Wall(32, 64, null);
        assertEquals("W", wall.toString());
    }

    @Test
    public void brokenConstructor(){ //check if the broken wall is created
        assertNotNull(new BrokenWall(32, 64, null));
    }

    @Test
    public void brokenToString(){ //check if broken walls's toString is B
        BrokenWall broken = new BrokenWall(32, 64, null);
        assertEquals("B", broken.toString());
    }

    @Test
    public void emptyConstructor(){ //check if the empty space is created
        assertNotNull(new EmptySpace(32, 64, null));
    }

    @Test
    public void emptyToString(){ //check if empty space's toString is " "
        EmptySpace empty = new EmptySpace(32, 64, null);
        assertEquals(" ", empty.toString());
    }

    @Test
    public void goalConstructor(){ //check if the goal is created
        assertNotNull(new Goal(32, 64, null));
    }

    @Test
    public void goalToString(){ //check if goal's toString is "G"
        Goal goal = new Goal(32, 64, null);
        assertEquals("G", goal.toString());
    }
}
