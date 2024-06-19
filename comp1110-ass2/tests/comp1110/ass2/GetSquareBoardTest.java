package comp1110.ass2;

import comp1110.ass2.skeleton.Board;
import comp1110.ass2.skeleton.Colour;
import comp1110.ass2.skeleton.Location;
import comp1110.ass2.skeleton.State;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class GetSquareBoardTest {

        @Test
    public void testGetsquareBoard() {
        Board board = new Board("fffffffffrrfffffff\nfffffffffrRfffffff\nfffffffffrrfffffff" +
                "\nfffgffyrgpygyrygbr" +
                "\nfffgGfggyygprbprpg\nfffgggbgprbpygbpyb\nffffffbpbpgrbrrbgy\nffffffgygybpgygprb\nffffffbrrrybgygybg" +
                "\nffffffgpbbyrprgbbp\nffffffbyrbpybgpryg\nffffffbyrbpybgpryg\nffffffpgyrggrbgyby\nfffffybgbpryybpgyp" +
                "\nffffYyybpgbprygrow\nfffyyyyryygbygybww");

        board.getSquareBoard();

        State[][] squareState = board.getSquareState();
        assertNotNull(squareState);
        assertEquals(16, squareState.length);

        State[] row0 = squareState[0];
        assertEquals(18, row0.length);

        State stateR0C15 = squareState[0][15];
        assertEquals(Colour.FIRE, stateR0C15.getColour());
        assertEquals(0, stateR0C15.getPosition().getRow());
        assertEquals(15, stateR0C15.getPosition().getColumn());

        State stateR4C4 = squareState[4][4];
        assertEquals(Colour.GREEN, stateR4C4.getColour());
        assertEquals(4, stateR4C4.getPosition().getRow());
        assertEquals(4, stateR4C4.getPosition().getColumn());

        State stateR7C6 = squareState[7][6];
        assertEquals(Colour.GREEN, stateR7C6.getColour());
        assertEquals(7, stateR7C6.getPosition().getRow());
        assertEquals(6, stateR7C6.getPosition().getColumn());

        State stateR13C1 = squareState[13][1];
        assertEquals(Colour.FIRE, stateR13C1.getColour());
        assertEquals(13, stateR13C1.getPosition().getRow());
        assertEquals(1, stateR13C1.getPosition().getColumn());

        State stateR14C8 = squareState[14][8];
        assertEquals(Colour.PURPLE, stateR14C8.getColour());
        assertEquals(14, stateR14C8.getPosition().getRow());
        assertEquals(8, stateR14C8.getPosition().getColumn());
    }

    @Test
    public void testGetsquareBoard2() {
        Board board = new Board("fffffffffrrfffffff\nfffffffffrRfffffff\nfffffffffrrfffffff" +
                "\nfffgffyrgpygyrygbr" +
                "\nffffffbyrbpybgpryg\nfffgggbgprbpygbpyb\nffffffbpbpgrbrrbgy\nffffffbrrrybgygybg\nffffffgygybpgygprb" +
                "\nffffffgpbbyrprgbbp\nfffgGfggyygprbprpg\nffffffbyrbpybgpryg\nffffffpgyrggrbgyby" +
                "\nfffyyyyryygbygybww" +
                "\nffffYyybpgbprygrow\nfffffybgbpryybpgyp");

        board.getSquareBoard();

        State[][] squareState = board.getSquareState();
        assertNotNull(squareState);
        assertEquals(16, squareState.length);

        State[] row0 = squareState[0];
        assertEquals(18, row0.length);

        State stateR0C15 = squareState[0][15];
        assertEquals(Colour.FIRE, stateR0C15.getColour());
        assertEquals(0, stateR0C15.getPosition().getRow());
        assertEquals(15, stateR0C15.getPosition().getColumn());

        State stateR5C4 = squareState[5][4];
        assertEquals(Colour.GREEN, stateR5C4.getColour());
        assertEquals(5, stateR5C4.getPosition().getRow());
        assertEquals(4, stateR5C4.getPosition().getColumn());

        State stateR7C6 = squareState[7][6];
        assertEquals(Colour.BLUE, stateR7C6.getColour());
        assertEquals(7, stateR7C6.getPosition().getRow());
        assertEquals(6, stateR7C6.getPosition().getColumn());

        State stateR13C1 = squareState[13][1];
        assertEquals(Colour.FIRE, stateR13C1.getColour());
        assertEquals(13, stateR13C1.getPosition().getRow());
        assertEquals(1, stateR13C1.getPosition().getColumn());

        State stateR14C8 = squareState[14][8];
        assertEquals(Colour.PURPLE, stateR14C8.getColour());
        assertEquals(14, stateR14C8.getPosition().getRow());
        assertEquals(8, stateR14C8.getPosition().getColumn());
    }
}
