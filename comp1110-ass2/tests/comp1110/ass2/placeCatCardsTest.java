package comp1110.ass2;

import comp1110.ass2.skeleton.Board;
import comp1110.ass2.skeleton.Cat;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Timeout;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

/**
 * @author Jialin Li
 */
@Timeout(value = 3000, unit = TimeUnit.MILLISECONDS)
public class placeCatCardsTest {
    Cat cat = new Cat();
    String[][] boardLayout = new String[20][20];

    @Test
    public void testPlaceSingleCatCard() {
        //C112033060340009
        String catString = "C112033060340009";
        cat.placeCatCards(boardLayout, catString);

        assertEquals("Y", boardLayout[1][10]);
        assertEquals("G", boardLayout[7][4]);
        assertEquals("B", boardLayout[13][4]);
    }

    @Test
    public void testPlaceMultipleCatCards() {
        String catString = "C00015100091120350603";
        cat.placeCatCards(boardLayout, catString);

        assertEquals("B", boardLayout[1][10]);
        assertEquals("R", boardLayout[1][16]);
        assertEquals("P", boardLayout[7][4]);
        assertEquals("B", boardLayout[13][4]);
    }
}
