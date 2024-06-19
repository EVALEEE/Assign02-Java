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
public class flipFireTest {
    @Test
    public void testFlipFire_Simple() {
        String tileShape = "i011011121321";
        FireTile fireTile = new FireTile(); // Assuming the method is in a class called FireTile
        String result = fireTile.flipFire(tileShape);
        assertEquals("i021312111022", result);
    }

    @Test
    public void testFlipFire_MixedPositions() {
        String tileShape = "s000110111222";
        FireTile fireTile = new FireTile();
        String result = fireTile.flipFire(tileShape);
        assertEquals("s020112111020", result);
    }

    @Test
    public void testFlipFire_SameColumn() {
        String tileShape = "d000102121314";
        FireTile fireTile = new FireTile();
        String result = fireTile.flipFire(tileShape);
        assertEquals("d040302121110", result);
    }

    @Test
    public void testFlipFire_EmptyTileShape() {
        String tileShape = "f";
        FireTile fireTile = new FireTile();
        String result = fireTile.flipFire(tileShape);
        assertEquals("f", result);
    }
}
