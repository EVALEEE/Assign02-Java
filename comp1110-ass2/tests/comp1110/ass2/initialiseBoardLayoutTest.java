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
public class initialiseBoardLayoutTest {
    //LNSNLASA
    //LALA
    @Test
    public void testInitialiseBoardLayoutWithTwoIslands() {
        Board board = new Board();
        String[][] result = board.initialiseBoardLayout("LALA");
        assertEquals(18, result.length);
        assertEquals(9, result[0].length);
    }

    @Test
    public void testInitialiseBoardLayoutWithMultipleIslands() {
        Board board = new Board();
        String[][] result = board.initialiseBoardLayout("LNSNLASA");
        assertEquals(30, result.length);
        assertEquals(18, result[0].length);
    }
}
