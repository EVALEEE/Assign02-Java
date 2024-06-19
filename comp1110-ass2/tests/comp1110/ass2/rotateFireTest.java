package comp1110.ass2;

import comp1110.ass2.skeleton.FireTile;
import org.junit.jupiter.api.Timeout;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

/**
 * @author Jialin Li
 */
@Timeout(value = 3000, unit = TimeUnit.MILLISECONDS)
public class rotateFireTest {
    @Test
    public void testRotateFire_North() {
        String tileShape = "a000110111221";
        char orientation = 'N';
        FireTile fireTile = new FireTile(); // Assuming the method is in a class called FireTile
        String result = fireTile.rotateFire(tileShape, orientation);
        assertEquals("a000110111221", result);
    }

    @Test
    public void testRotateFire_East() {
        String tileShape = "b000102102021";
        char orientation = 'E';
        FireTile fireTile = new FireTile();
        String result = fireTile.rotateFire(tileShape, orientation);
        assertEquals("b021222010010", result);
    }

    @Test
    public void testRotateFire_South() {
        String tileShape = "c000102031121";
        char orientation = 'S';
        FireTile fireTile = new FireTile();
        String result = fireTile.rotateFire(tileShape, orientation);
        assertEquals("c232221201202", result);
    }

    @Test
    public void testRotateFire_West() {
        String tileShape = "h0001021121";
        char orientation = 'W';
        FireTile fireTile = new FireTile();
        String result = fireTile.rotateFire(tileShape, orientation);
        assertEquals("h2010001112", result);
    }

    @Test
    public void testRotateFire_EmptyTileShape() {
        String tileShape = "";
        char orientation = 'E';
        FireTile fireTile = new FireTile();
        String result = fireTile.rotateFire(tileShape, orientation);
        assertEquals("", result);
    }
}
