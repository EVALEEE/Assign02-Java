package comp1110.ass2;

import comp1110.ass2.skeleton.Board;
import org.junit.Test;
import org.junit.jupiter.api.Timeout;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * @author Jialin Li
 */
@Timeout(value = 3000, unit = TimeUnit.MILLISECONDS)
public class isOnTheBoardTest {
    @Test
    public void testCardFullyOnBoard() {
        Board board = new Board();
        char[] cardPlacement = new char[] {'A', 'v', '0', '3', '0', '8', 'N'};
        assertTrue(board.isOnTheBoard(cardPlacement, 100, 100));
    }

    @Test
    public void testCardPartiallyOffBoard() {
        Board board = new Board();
        char[] cardPlacement = new char[] {'A', 'v', '9', '9', '9', '9', 'N'};
        assertFalse(board.isOnTheBoard(cardPlacement, 100, 100), "Card should be off the board when exceeding bounds");
    }

    @Test
    public void testFireFullyOnBoard() {
        Board board = new Board();
        char[] firePlacement = new char[] {'a', '0', '9', '0', '0', 'F', 'W'};
        assertTrue(board.isOnTheBoard(firePlacement, 100, 100));
    }
}
