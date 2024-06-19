package comp1110.ass2;

import comp1110.ass2.skeleton.FireTile;
import comp1110.ass2.skeleton.PathwayCards;
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
public class isCardOverLappingTest {
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
    public void testIsFCatOverlapping_isOverlap1() {
        int rowCard = 3;
        int colCard = 4;
        assertTrue(new PathwayCards().isCardOverLapping(boardCharArray, rowCard, colCard));
    }

    @Test
    public void testIsFCatOverlapping_noOverlap() {
        int rowCard = 5;
        int colCard = 14;
        assertFalse(new PathwayCards().isCardOverLapping(boardCharArray, rowCard, colCard));
    }

    @Test
    public void testIsFCatOverlapping_isOverlap2() {
        int rowCard = 6;
        int colCard = 3;
        assertTrue(new PathwayCards().isCardOverLapping(boardCharArray, rowCard, colCard));
    }
}
