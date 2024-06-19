package comp1110.ass2;

import comp1110.ass2.skeleton.Island;
import org.junit.Before;
import org.junit.jupiter.api.Timeout;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author Jialin Li
 */
@Timeout(value = 3000, unit = TimeUnit.MILLISECONDS)
public class placeIslandTest {
    private static final int ISLAND_SPACING = 9;
    private String[][] boardLayout;
    private final static int BOARD_ROWS = 20; // Assumed board size
    private final static int BOARD_COLS = 20;
    private Island boardModifier;

    @Before
    public void setUp() {
        boardLayout = new String[BOARD_ROWS][BOARD_COLS]; // Initialize an empty board
        for (int i = 0; i < BOARD_ROWS; i++) {
            Arrays.fill(boardLayout[i], ".");
        }
        boardModifier = new Island();
    }

    @Test
    public void testPlaceIsland_SingleLargeIsland() {
        String islandConfig = "LN";
        boardModifier.placeIsland(boardLayout, islandConfig);
        assertEquals( "f", boardLayout[0][0]);
    }

    @Test
    public void testPlaceIsland_MultipleIslands() {
        String islandConfig = "LNSM";
        boardModifier.placeIsland(boardLayout, islandConfig);
        assertEquals("f", boardLayout[0][0]);
    }

    @Test
    public void testPlaceIsland_SequencePlacement() {
        String islandConfig = "LNLS";
        boardModifier.placeIsland(boardLayout, islandConfig);
        assertEquals( "y", boardLayout[ISLAND_SPACING][0]);
    }
}
