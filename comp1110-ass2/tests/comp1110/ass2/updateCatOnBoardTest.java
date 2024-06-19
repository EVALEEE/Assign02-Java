package comp1110.ass2;

import comp1110.ass2.skeleton.Board;
import comp1110.ass2.skeleton.Cat;
import comp1110.ass2.skeleton.Location;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Timeout;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

/**
 * Test the method updateCatOnBoard created in Board.
 * @author Jialin Li
 */
@Timeout(value = 3000, unit = TimeUnit.MILLISECONDS)
public class updateCatOnBoardTest {
    private Board board;

    @Before
    public void setUp() {
        // Initialize the board with a specific layout where a cat ('C') is present
        String[] lines = {
                "........",
                "....G...",
                "....a...",
                "........"
        };
        board = new Board();
        board.setLines(lines);
    }

    @Test
    public void testUpdateCatOnBoard() {
        // Create a cat with the initial position at (1, 4) and a new position at (2, 4)
        Location start = new Location(1, 4);
        Location end = new Location(2, 4);
        Cat cat = new Cat();
        cat.setStartLocation(start);
        cat.setEndLocation(end);
        // Invoke the method to update the cat's position on the board
        board.updateCatOnBoard(cat);

        // Verify that the start position now contains a 'g'
        assertEquals('g', board.getLines()[start.getRow()].substring(start.getColumn(), start.getColumn() + 1).charAt(0));
        // Verify that the end position now contains a 'A'
        assertEquals('A', board.getLines()[end.getRow()].substring(end.getColumn(), end.getColumn() + 1).charAt(0));
    }
}
