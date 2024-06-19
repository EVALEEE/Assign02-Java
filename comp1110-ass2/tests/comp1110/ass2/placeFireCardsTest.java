package comp1110.ass2;

import comp1110.ass2.skeleton.Board;
import comp1110.ass2.skeleton.FireTile;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Timeout;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * @author Jialin Li
 */
@Timeout(value = 3000, unit = TimeUnit.MILLISECONDS)
public class placeFireCardsTest {
    //a000110111221
    private String[][] boardLayout;

    @Before
    public void setUp() {
        // Assume the board is initially empty or null-filled
        boardLayout = new String[25][25];
        for (int i = 0; i < boardLayout.length; i++) {
            for (int j = 0; j < boardLayout[i].length; j++) {
                boardLayout[i][j] = null;
            }
        }
    }

    @Test
    public void testPlaceFireCards_SingleCard() {
        String fireString = "a000110111221";
        FireTile fireTile = new FireTile();
        fireTile.placeFireCards(boardLayout, fireString);

        for (int i = 0; i < boardLayout.length; i++) {
            for (int j = 0; j < boardLayout[i].length; j++) {
                System.out.print(boardLayout[i][j] + " ");
            }
            System.out.println();
        }

        // Check the 3x3 area starting at row 1, column 2
        for (int row = 0; row < 3; row++) {
            for (int col = 1; col < 4; col++) {
                assertEquals("f", boardLayout[row][col]);
            }
        }

        for (int row = 10; row < 13; row++) {
            for (int col = 11; col < 14; col++) {
                assertEquals("f", boardLayout[row][col]);
            }
        }
    }
}
