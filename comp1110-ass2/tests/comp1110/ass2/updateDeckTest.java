package comp1110.ass2;

import comp1110.ass2.skeleton.Deck;
import org.junit.Test;
import org.junit.jupiter.api.Timeout;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

/**
 * @author Jialin Li
 */
@Timeout(value = 3000, unit = TimeUnit.MILLISECONDS)
public class updateDeckTest {
    @Test
    public void testUpdateDeck_AllSpaces() {
        char[] charArray = {' ', ' ', ' ', ' '};
        String updatedDeck = Deck.updateDeck(charArray);
        assertEquals("", updatedDeck);
    }

    @Test
    public void testUpdateDeck_NoSpaces() {
        char[] charArray = {'A', 'K', 'Q', 'J'};
        String updatedDeck = Deck.updateDeck(charArray);
        assertEquals("AKQJ", updatedDeck);
    }

    @Test
    public void testUpdateDeck_MixedSpaces() {
        char[] charArray = {'A', ' ', 'Q', ' ', 'J', ' '};
        String updatedDeck = Deck.updateDeck(charArray);
        assertEquals("AQJ", updatedDeck);
    }

    @Test
    public void testUpdateDeck_EmptyArray() {
        char[] charArray = {};
        String updatedDeck = Deck.updateDeck(charArray);
        assertEquals("", updatedDeck);
    }
}
