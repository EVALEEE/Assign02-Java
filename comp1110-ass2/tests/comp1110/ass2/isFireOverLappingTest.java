package comp1110.ass2;

import comp1110.ass2.skeleton.Board;
import comp1110.ass2.skeleton.FireTile;
import org.junit.Before;
import org.junit.jupiter.api.Timeout;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author Jialin Li
 */
@Timeout(value = 3000, unit = TimeUnit.MILLISECONDS)
public class isFireOverLappingTest {
    private char[][] boardCharArray;

    @Before
    public void setUp() {
        // Initialize a board with predefined characters
        boardCharArray = new char[][]{
                {'f', 'f', 'f', 'f', 'f', 'f', 'f', 'f', 'f', 'f', 'f', 'f', 'f', 'f', 'f', 'r', 'r', 'f'},
                {'f', 'f', 'f', 'f', 'f', 'f', 'f', 'f', 'f', 'b', 'B', 'b', 'f', 'f', 'f', 'r', 'R', 'f'},
                {'f', 'f', 'f', 'f', 'f', 'f', 'f', 'f', 'f', 'b', 'b', 'b', 'f', 'f', 'f', 'r', 'r', 'f'},
                {'f', 'f', 'f', 'f', 'f', 'f', 'f', 'f', 'f', 'b', 'b', 'b', 'f', 'f', 'f', 'r', 'r', 'f'},
                {'f', 'f', 'f', 'f', 'f', 'f', 'p', 'b', 'g', 'g', 'y', 'b', 'g', 'b', 'r', 'g', 'g', 'y'},
                {'f', 'f', 'f', 'f', 'f', 'f', 'p', 'r', 'y', 'g', 'y', 'g', 'r', 'p', 'y', 'y', 'r', 'b'},
                {'f', 'f', 'f', 'f', 'f', 'f', 'y', 'g', 'b', 'y', 'p', 'r', 'b', 'p', 'b', 'b', 'p', 'y'},
                {'f', 'f', 'f', 'f', 'p', 'p', 'b', 'p', 'g', 'b', 'g', 'y', 'r', 'r', 'g', 'y', 'g', 'r'}
        };
    }

    @Test
    public void testIsFireOverlapping_NoOverlap() {
        String fireFinal = "a000110111221";
        int rowFire = 3;
        int colFire = 4;
        assertTrue(new FireTile().isFireOverLapping(boardCharArray, fireFinal, rowFire, colFire));
    }

    @Test
    public void testIsFireOverlapping_WithFire() {
        String fireFinal = "i011011121321";  // Relative coordinates
        int rowFire = 1;
        int colFire = 1;
        assertTrue(new FireTile().isFireOverLapping(boardCharArray, fireFinal, rowFire, colFire));
    }

    @Test
    public void testIsFireOverlapping_WithCat() {
        String fireFinal = "j001011121322";
        int rowFire = 2;
        int colFire = 1;
        assertTrue(new FireTile().isFireOverLapping(boardCharArray, fireFinal, rowFire, colFire));
    }

    @Test
    public void testIsFireOverlapping_WithColoredTiles() {
        String fireFinal = "d000102121314";
        int rowFire = 4;
        int colFire = 4;
        assertTrue(new FireTile().isFireOverLapping(boardCharArray, fireFinal, rowFire, colFire));
    }
}
