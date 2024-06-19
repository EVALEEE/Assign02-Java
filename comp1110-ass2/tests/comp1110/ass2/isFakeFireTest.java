package comp1110.ass2;

import comp1110.ass2.skeleton.Colour;
import comp1110.ass2.skeleton.Location;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Timeout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static javafx.beans.binding.Bindings.when;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author Jialin Li
 */
@Timeout(value = 3000, unit = TimeUnit.MILLISECONDS)
public class isFakeFireTest {
    String gameBoard =
            "ffffffffffffffffff\n" +
            "ffffffffffffffffff\n" +
            "fffffffffffyffffff\n" +
            "fffffffffgypfffprp\n" +
            "fffffffffyyrfffbow\n" +
            "ffffffffffypfffbyg\n" +
            "fffffffpfbyyfffbpg\n" +
            "ffffffbygbrYfffryb\n" +
            "ffffffgrrgybfffrgy\n" +
            "ffffffbbbyrybpgrpb\n" +
            "fffbbbgggpbygypgrb\n" +
            "fffbbbbbbbgbrrbygg\n" +
            "ffffffgffbbbBygrpy\n" +
            "ffffffggfgggGrybrp\n" +
            "ffffffggggyypgbyyg";

    private Location game;

    @Before
    public void setUp() {
        game = new Location();
    }

    @Test
    public void testIsFakeFire_True_WhenAdjacentFire() {
        game.setColumn(7);
        game.setRow(7);
        assertTrue("Expected fake fire due to adjacent fire", game.isFakeFire(gameBoard, Colour.BLUE));
    }

    @Test
    public void testIsFakeFire_False_WhenNoAdjacentFire() {
        game.setColumn(6);
        game.setRow(10);
        assertFalse("Expected no fake fire due to no adjacent fire", game.isFakeFire(gameBoard, Colour.BLUE));
    }

    @Test
    public void testIsFakeFire_False_WhenSameColourAsCat() {
        game.setColumn(8);
        game.setRow(9);
        assertFalse("Expected no fake fire due to matching cat colour", game.isFakeFire(gameBoard, Colour.RED));
    }

    @Test
    public void testIsFakeFire_True_WhenDifferentColourAndAdjacentFire() {
        game.setColumn(12);
        game.setRow(9);
        assertFalse("Expected fake fire due to adjacent fire", game.isFakeFire(gameBoard, Colour.BLUE));
    }
}
