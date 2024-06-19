package comp1110.ass2;

import comp1110.ass2.skeleton.Location;
import org.junit.Test;
import org.junit.jupiter.api.Timeout;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author Jialin Li
 */
@Timeout(value = 3000, unit = TimeUnit.MILLISECONDS)
public class getAdjacentLocationsTest {

    String gameBoard = "ffffffffffffffffff\n" +
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

    @Test
    public void testGetAdjacentLocations_Center() {
        Location myClass = new Location();
        myClass.setColumn(4);
        myClass.setColumn(5);
        List<Location> result = myClass.getAdjacentLocations(gameBoard);
        assertTrue(result.size() == 1);
        assertFalse(result.contains(new Location(0, 1)));
        assertFalse(result.contains(new Location(2, 1)));
    }

    @Test
    public void testGetAdjacentLocations_Edge() {
        Location myClass = new Location();
        List<Location> result = myClass.getAdjacentLocations(gameBoard);
        assertTrue(result.size() == 0);
        assertFalse(result.contains(new Location(1, 1)));
        assertFalse(result.contains(new Location(0, 0)));
        assertFalse(result.contains(new Location(0, 2)));
    }
}
