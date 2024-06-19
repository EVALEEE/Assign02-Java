package comp1110.ass2;

import comp1110.ass2.skeleton.FireTile;
import comp1110.ass2.skeleton.Location;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Timeout;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author Jialin Li
 */
@Timeout(value = 3000, unit = TimeUnit.MILLISECONDS)
public class rotateCoordinatesTest {
    private FireTile myObject;  // Assuming MyClass is the class containing the rotateCoordinates method

    @Before
    public void setUp() {
        myObject = new FireTile();
        myObject.setLayout(Arrays.asList(new Location(0, 0), new Location(0, 1), new Location(1, 0), new Location(1, 1)));
    }

    @Test
    public void testRotateCoordinates_NoRotation() {
        List<Location> expected = Arrays.asList(new Location(0, 0), new Location(0, 1), new Location(1, 0), new Location(1, 1));
        assertEquals(expected, myObject.rotateCoordinates("FN"));
    }

    @Test
    public void testRotateCoordinates_HorizontalFlip() {
        List<Location> expected = Arrays.asList(new Location(0, 1), new Location(0, 0), new Location(1, 1), new Location(1, 0));
        assertEquals(expected, myObject.rotateCoordinates("TN"));
    }

    @Test
    public void testRotateCoordinates_Clockwise90() {
        List<Location> expected = Arrays.asList(new Location(0, 1), new Location(1, 1), new Location(0, 0), new Location(1, 0));
        assertEquals(expected, myObject.rotateCoordinates("FE"));
    }


    @Test
    public void testRotateCoordinates_180Degrees() {
        List<Location> expected = Arrays.asList(new Location(1, 1), new Location(1, 0), new Location(0, 1), new Location(0, 0));
        assertEquals(expected, myObject.rotateCoordinates("FS"));
    }

    @Test
    public void testRotateCoordinates_LeftRightFlip() {
        List<Location> expected = Arrays.asList(new Location(1, 0), new Location(1, 1), new Location(0, 0), new Location(0, 1));
        assertEquals(expected, myObject.rotateCoordinates("TS"));
    }

    @Test
    public void testRotateCoordinates_CounterClockwise90() {
        List<Location> expected = Arrays.asList(new Location(1, 0), new Location(0, 0), new Location(1, 1), new Location(0, 1));
        assertEquals(expected, myObject.rotateCoordinates("FW"));
    }
}
