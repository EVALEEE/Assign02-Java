package comp1110.ass2;

import comp1110.ass2.skeleton.Board;
import org.junit.Test;
import org.junit.jupiter.api.Timeout;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

/**
 * @author Jialin Li
 */
@Timeout(value = 3000, unit = TimeUnit.MILLISECONDS)
public class convertBoardToStringTest {
    @Test
    public void testConvertEmptyBoard() {
        Board converter = new Board();
        String[][] emptyBoard = new String[0][0];
        String result = converter.convertBoardToString(emptyBoard);
        assertEquals("", result);
    }

    @Test
    public void testConvertCompleteBoard() {
        Board converter = new Board();
        String[][] completeBoard = { { "X", "O", "X" }, { "O", "X", "O" }, { "X", "O", "X" } };
        String result = converter.convertBoardToString(completeBoard);
        String expected = "XOX\nOXO\nXOX";
        assertEquals(expected, result);
    }
}
