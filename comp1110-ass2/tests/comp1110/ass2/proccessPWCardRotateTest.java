package comp1110.ass2;

import comp1110.ass2.skeleton.PathwayCards;
import org.junit.Before;
import org.junit.jupiter.api.Timeout;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

/**
 * @author Jialin Li
 */
@Timeout(value = 3000, unit = TimeUnit.MILLISECONDS)
public class proccessPWCardRotateTest {
    private PathwayCards myClass;

    @Test
    public void testProcessPWCardRotate1() {
        myClass = new PathwayCards();
        String placementString = "Ab1208S";
        String result = myClass.proccessPWCardRotate(placementString);
        // Check the final result
        assertEquals("brrbrrbbbg", result);
    }

    @Test
    public void testProcessPWCardRotate2() {
        myClass = new PathwayCards();
        String placementString = "Ba1008E";
        String result = myClass.proccessPWCardRotate(placementString);
        // Check the final result
        assertEquals("aggggppyyp", result);
    }

    @Test
    public void testProcessPWCardRotate3() {
        myClass = new PathwayCards();
        String placementString = "Cc1106S";
        String result = myClass.proccessPWCardRotate(placementString);
        // Check the final result
        assertEquals("cryrrybyyb", result);
    }
}
