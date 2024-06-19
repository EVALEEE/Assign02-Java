package comp1110.ass2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

@Timeout(value = 500, unit = TimeUnit.MILLISECONDS)
public class IsCatMovementValidTest {

    private void test(String[] gameState, String action, boolean expected) {
        boolean out = RaceToTheRaft.isCatMovementValid(gameState, action);
        Assertions.assertEquals(expected, out,
                "Expected " + expected + " for gameState " + Arrays.toString(gameState) + ", and action " + action +
                        ", but got " + out);
    }

    @Test
    public void validMovement() {
        String[] emptyBoard = new String[]{"G04040403Ae", "G04040504Cj", "G04040505Cm"};
        for (String action : emptyBoard) {
            test(gamestates[0], action, true);
        }
        String[] moves = new String[]{"P01130513Bx", "G01070608Cc", "B07040606CcDp" };
        for (String action : moves) {
            test(gamestates[1], action, true);
        }
        test(gamestates[0], "G04049999Ae", false);
    }

    @Test
    public void invalidMovement() {
        String[] emptyBoard = new String[]{"G04040506Ae", "G04040507Cj", "G04040304Cm", "G04040505Ba"};
        for (String action : emptyBoard) {
            test(gamestates[0], action, false);
        }
        String[] moves = new String[]{"P01130713Bx", "G01070611Cc", "G01070411Dp", "B07040908CcDp", "B07040606ByCc", "B07040606Dp"};
        for(String action: moves) {
            test (gamestates[1], action, false);
        }
        test(gamestates[0], "G04040504Ae", true);
    }


    private static final String[][] gamestates = new String[][]{
            new String[]{
                    """
fffffffffrrfffffff
fffffffffrRfffffff
fffffffffrrfffffff
fffgffpbgpgbgpgygy
fffgGfpryyggrgbpyp
fffgggygbgrybyybgr
ffffffbpgbgpygbrpb
ffffffrybpyrbbpygy
ffffffrgybryygpbrr
ffffffbbrgyybgpgpb
ffffffrypprbyrgbyr
ffffffyggyprgybpgy
fffffyypyggybrrgyp
ffffYypbbbrgpygrow
fffyyygyrbprgpbbww
""",
                    "AabcdfijklmnopqstuvwxyBabcdefghijklmnopqrstuvwxyCabcdefghiklnopqrstuvwxyDabcdefghijklmnopqrstuvwxy", "AeghrBCjmD", "", "abcdefghijklmnopqrstuvwxyzABCDE"},
            new String[]{
                    """
ffffffgffffffppfff
ffffffgGfffffPpfff
ffffffgggffffppfff
ffffffpbgggyypyffp
ffffffprygrggprfgy
ffffffygbgrggpyffp
ffffffbpggrgprgyry
fffbBbbyybrgypbbgb
fffbbbffggbybgryyb
fffffffbbyrbpbygpb
fffffffffggygrbpyr
fffffffgypygbybgrg
ffffffffbrrbygpgyp
ffffffffpybypbrrow
fffffffrygrpgrbbww
""",
                    "AabcdefghijklmnopqrstuvwxyBabcdefghijklmnopqstuvwyCadefghijklmnopqrstuvwxyDabcdeghijklmnoqrstuvwxy","ABxCcDp","B0704","acdefghijklmoprstuvwxyzABCDE"},

    };


}
