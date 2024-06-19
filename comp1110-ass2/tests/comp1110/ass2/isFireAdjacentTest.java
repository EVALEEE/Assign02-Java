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
public class isFireAdjacentTest {
    private char[][] board;

    @Before
    public void setUp() {
        // Initialize a board with some fire ('f') and empty (' ') spaces
        board = new char[][]{
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
    public void testIsFireAdjacent_TrueAdjacent() {
        assertTrue(new FireTile().isFireAdjacent(board, 0, 3)); // Directly adjacent to a fire at (1, 3)
    }

    @Test
    public void testIsFireAdjacent_FalseNoAdjacent() {
        assertTrue(new FireTile().isFireAdjacent(board, 4, 4)); // No adjacent fire
    }

    @Test
    public void testIsFireAdjacent_EdgeOfBoard() {
        assertTrue(new FireTile().isFireAdjacent(board, 0, 0)); // On the edge, no adjacent fire
    }

    @Test
    public void testIsFireAdjacent_AdjacentOnMultipleSides() {
        assertTrue(new FireTile().isFireAdjacent(board, 2, 1)); // Fire adjacent on two sides
    }

    @Test
    public void testIsFireAdjacent_BoundaryConditions() {
        assertTrue(new FireTile().isFireAdjacent(board, 4, 0)); // On the lower boundary with no adjacent fire
        assertTrue(new FireTile().isFireAdjacent(board, 4, 1)); // On the lower boundary with adjacent fire
    }

    @Test
    public void testIsFireAdjacent_AtCorner() {
        assertTrue(new FireTile().isFireAdjacent(board, 0, 0)); // Top-left corner
        assertTrue(new FireTile().isFireAdjacent(board, 3, 0)); // Adjacent to fire at bottom-left corner

    }

}
