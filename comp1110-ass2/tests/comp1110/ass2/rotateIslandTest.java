package comp1110.ass2;

import comp1110.ass2.skeleton.Island;
import org.junit.Before;
import org.junit.jupiter.api.Timeout;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

/**
 * @author Jialin Li
 */
@Timeout(value = 3000, unit = TimeUnit.MILLISECONDS)
public class rotateIslandTest {
    private Island myObject;  // MyClass should contain the rotateIsland method.

    @Before
    public void setUp() {
        myObject = new Island();

    }

    @Test
    public void testRotateIsland_NoRotation() {
        String originalBoard = "fffgygbyr\nfffgygpby\nfffrrbrgp\nfffbgypbr\nfffpbrpgy\nfffyrygyp\nfffgbbrpb\nfffpggbyg\nfffpypgrr";
        String result = myObject.rotateIsland(originalBoard, 'N');
        assertEquals(originalBoard, result);
    }

    @Test
    public void testRotateIsland_RotateClockwise() {
        String originalBoard = "fffgygbyr\nfffgygpby\nfffrrbrgp\nfffbgypbr\nfffpbrpgy\nfffyrygyp\nfffgbbrpb\nfffpggbyg\nfffpypgrr";
        String result = myObject.rotateIsland(originalBoard, 'E');
        assertEquals("fffffffff\n" +
                "fffffffff\n" +
                "fffffffff\n" +
                "ppgypbrgg\n" +
                "ygbrbgryy\n" +
                "pgbyrybgg\n" +
                "gbrgpprpb\n" +
                "rypygbgby\n" +
                "rgbpyrpyr", result);
    }

    @Test
    public void testRotateIsland_Rotate180Degrees() {
        String originalBoard = "fffgygbyr\nfffgygpby\nfffrrbrgp\nfffbgypbr\nfffpbrpgy\nfffyrygyp\nfffgbbrpb\nfffpggbyg\nfffpypgrr";
        String result = myObject.rotateIsland(originalBoard, 'S');
        assertEquals("rrgpypfff\n" +
                "gybggpfff\n" +
                "bprbbgfff\n" +
                "pygyryfff\n" +
                "ygprbpfff\n" +
                "rbpygbfff\n" +
                "pgrbrrfff\n" +
                "ybpgygfff\n" +
                "rybgygfff", result);
    }

    @Test
    public void testRotateIsland_RotateAntiClockwise() {
        String originalBoard = "fffgygbyr\nfffgygpby\nfffrrbrgp\nfffbgypbr\nfffpbrpgy\nfffyrygyp\nfffgbbrpb\nfffpggbyg\nfffpypgrr";
        String result = myObject.rotateIsland(originalBoard, 'W');
        assertEquals("ryprypbgr\n" +
                "ybgbgypyr\n" +
                "bprppgrbg\n" +
                "ggbyrybgp\n" +
                "yyrgbrbgy\n" +
                "ggrbpygpp\n" +
                "fffffffff\n" +
                "fffffffff\n" +
                "fffffffff", result);
    }
}
