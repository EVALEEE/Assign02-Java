package comp1110.ass2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

@Timeout(value = 3000, unit = TimeUnit.MILLISECONDS)

public class IsGameOverTest {

    private void test(String[] gameState, String action, boolean expected) {
        boolean out = RaceToTheRaft.isGameOver(gameState, action);
        Assertions.assertEquals(expected, out,
                "Expected " + expected + " for gameState " + Arrays.toString(gameState) + ", and action " + action +
                        ", but got " + out);

    }


    @Test
    public void testFullGame() {
        for (int i = 0; i < actions.length - 1; i++) {
            test(fullGame[i], actions[i], false);
        }
        test(fullGame[actions.length - 1], actions[actions.length - 1], true);
    }

    @Test
    public void testFireTilePath() {
        //fire tile makes yellow cat fully enclosed by fire
        test(new String[]{"""
fffgygbyr
fffgygpby
fffrrbrgp
fffbgYpbr
fffpbrpgy
fffyrygyp
fffgbbrpb
ffffggbyg
ffffypgrr
rrbpgyyrb
ygypbbryp
bprbgypgb
rgbyybyrg
pypbgrggy
ygygpgbgp
Bprbgywww
rybpyrbow
bgygppwww
""", "AabcdhijklmnopqrstuvwxyBabcdefghijklopqrstuvwxyCabcdefgiklmopqrstuvwyDabcdeghijklmnopstuvwxy", "ABCxD", "", "abcefghijklmnopqrstuvwxyzABCDE"}, "d0704FN", true);
        //rotated fire tile leaves enough space to place more path cards
        test(new String[]{"""
fffgygbyr
fffgygpby
fffrrbrgp
fffbgRpbr
fffpbrpgy
fffyrygyp
fffgbbrpb
ffffggbyg
ffffypgrr
rrbpgyyrb
ygypbbryp
bprbgypgb
rgbyybyrg
pypbgrggy
ygygpgbgp
Bprbgywww
rybpyrbow
bgygppwww
""", "AabcdhijklmnopqrstuvwxyBabcdefghijklopqrstuvwxyCabcdefgiklmopqrstuvwyDabcdeghijklmnopstuvwxy", "ABCxD", "", "abcefghijklmnopqrstuvwxyzABCDE"}, "d0704FE", false);
        //fire tile leaves a green field for the green cat to cross
        test(new String[]{"""
fffgygbyr
fffgygpby
fffrrbrgp
fffbgGpbr
fffpbrpgg
fffyrygyp
fffgbbrpb
ffffggbyg
ffffypgrr
rrbpgyyrb
ygypbbryp
bprbgypgb
rgbyybyrg
pypbgrggy
ygygpgbgp
Bprbgywww
rybpyrbow
bgygppwww
""", "AabcdhijklmnopqrstuvwxyBabcdefghijklopqrstuvwxyCabcdefgiklmopqrstuvwyDabcdeghijklmnopstuvwxy", "ABCxD", "", "abcefghijklmnopqrstuvwxyzABCDE"}, "d0403TN", false);
        //fire tile leaves only a purple field to cross, which cannot be changed by a card
        test(new String[]{"""
fffgygbyr
fffgygpby
fffrrbrgp
fffbgGpbr
fffpbrpgy
fffyrygyp
fffgbbrpb
ffffggbyg
ffffypgrr
rrbpgyyrb
ygypbbryp
bprbgypgb
rgbyybyrg
pypbgrggy
ygygpgbgp
Bprbgywww
rybpyrbow
bgygppwww
""", "AabcdhijklmnopqrstuvwxyBabcdefghijklopqrstuvwxyCabcdefgiklmopqrstuvwyDabcdeghijklmnopstuvwxy", "ABCxD", "", "abcefghijklmnopqrstuvwxyzABCDE"}, "d0503TN", true);
    }

    @Test
    public void testFireTileSpace() {
        String burningBoard="""
fffbfffff
ffffgffff
fffffffff
ffffffyff
ffffffrrf
fffffffff
fffbfyfff
ffffffgff
fffgpffff
ffffffbfy
ffffyrfff
ffffffyRp
fffgbrwww
fffpgbwow
fffgrpwww
""";
        //fire tile b fits into 3x3 free space
        test(new String[]{burningBoard, "AabcdstuvwxyBabcdefijklotuvwxyCabcdefvwyDabcdeghijkvwxy", "AmBCqDn", "", "acCDE"}, "b1203FN", false);
        //fire tile b fits into 3x3 free space if moved
        test(new String[]{burningBoard, "AabcdstuvwxyBabcdefijklotuvwxyCabcdefvwyDabcdeghijkvwxy", "AmBCqDn", "", "acCDE"}, "b0203FN", false);
        //fire tile t does not fit anywhere
        test(new String[]{burningBoard, "AabcdstuvwxyBabcdefijklotuvwxyCabcdefvwyDabcdeghijkvwxy", "AmBCqDn", "", "abCDE"}, "t1003FN", true);

        String burningBoardFlipVariant = """
                fffbfffff
                ffffgffff
                fffffffff
                ffffffyff
                ffffffrrf
                fffffffff
                fffbfyfff
                ffffffgff
                fffgpffff
                ffffffbfy
                ffffyrfff
                ffffffyRp
                fffgbrwww
                fffffbwow
                ffffrpwww
                """;
        //fire tile b fits if flipped
        test(new String[]{burningBoardFlipVariant, "AabcdstuvwxyBabcdefijklotuvwxyCabcdefvwyDabcdeghijkvwxy", "AmBCqDn", "", "arCDE"}, "b1203TN", false);
        //fire tile b can fit if flipped
        test(new String[]{burningBoardFlipVariant, "AabcdstuvwxyBabcdefijklotuvwxyCabcdefvwyDabcdeghijkvwxy", "AmBCqDn", "", "aCDE"}, "b1203FN", false);

        String burningBoardRotateVariant= """
fffbfffff
ffffgffff
fffffffff
ffffffyff
ffffffrrf
fffffffff
fffbfyfff
ffffffgff
fffgpffff
ffffffbfy
ffffyrfff
ffffffyRp
ffffbrwww
fffffbwow
fffgrpwww
""";

        //fire tile b fits if rotated
        test(new String[]{burningBoardRotateVariant, "AabcdstuvwxyBabcdefijklotuvwxyCabcdefvwyDabcdeghijkvwxy", "AmBCqDn", "", "atCDE"}, "b1203FS", false);
        //fire tile b can fit if rotated
        test(new String[]{burningBoardRotateVariant, "AabcdstuvwxyBabcdefijklotuvwxyCabcdefvwyDabcdeghijkvwxy", "AmBCqDn", "", "ajCDE"}, "b1203FN", false);
    }

    @Test
    public void testPathCard() {
        String board = """
                fffpgyyrbgrgpybygr
                fffpBbrypgyprpgbyb
                fffbgypgbbrbbrbgyr
                fffyybyrggypgpypbg
                fffbgrggypbrrbgpry
                fffgpgbgpygppyRygb
                fffbgybpbbpyrggbpg
                fffpyrgygbrypbyryb
                fffgppbRrgybgybrgy
                fffygybgygyybgpgpb
                fffgyrpbgprbyrgbyr
                fffPbyyrpyprgybpgy
                fffgbrggrggybrrwww
                fffpgbypbbrgpygwow
                fffgrprybbprgpbwww
                """;
        test(new String[]{board, "AabcdstuvwxyBabcdefijklotuvwxyCabcdefvwyDabcdeghijkvwxy", "AmBCqDn", "", "abCDE"}, "Am0506N", false);
        test(new String[]{board, "AabcdstuvwxyBabcdefijklotuvwxyCabcdefvwyDabcdeghijkvwxy", "AmBCqDn", "", ""}, "Am0506N", true);
    }


    @Test
    public void nonstandardObjectivePosn()
    {
        String board = """
                fffpgyyrbgrgpybygr
                fffpbbrypgyprpgbyb
                fffbgypgbbrbbrbgyr
                fffyybyrggypgpypbg
                fffbgrggypbrrbgpry
                fffgpgbgpygppyRygb
                fffbgybpfffffrrbpg
                fffffffffwwwrrrryb
                fffffffffwowgybrgy
                fffygybgywwwbgpgpb
                fffgyrpbgprbyrgbyr
                fffpbyyrpyprgybpgy
                fffgbrggrggybrrbbg
                fffpgbypbbrgpyggyp
                fffgrprybbprgpbbry
                """;
        test(new String[]{board, "AabcdstuvwxyBabcdefijklotuvwxyCabcdefvwyDabcdeghijkvwxy", "AmBCqDn", "", "abCE"}, "D0613FN", true);
        test(new String[]{board, "AabcdstuvwxyBabcdefijklotuvwxyCabcdefvwyDabcdeghijkvwxy", "AmBCqDn", "", "abCE"}, "D0812FN", false);
    }

    @Test
    public void testCatAction() {
        String boardWithGreenLeft= """
fffffffffrrfffffff
fffffffffrrfffffff
fffffffffrrfffffff
fffgffyrgpygyrygbr
fffggfggyygprbprpg
fffgggbgprbpygbpyb
ffffffbpbpGrbrrbgy
ffffffgygybpgygprb
ffffffbrrrybgygybg
ffffffgpbbyrprgbbp
ffffffbyrbpybgpryg
ffffffpgyrggrbgyby
fffffybgbpryybpGYp
ffffYyybpyyyyygRow
fffyyyyyyygbyyywww
""";
        String boardWithoutGreenLeft= """
fffffffffrrfffffff
fffffffffrrfffffff
fffffffffrrfffffff
fffgffyrgpygyrygbr
fffggfggyygprbprpg
fffgggbgprbpygbpyb
ffffffbpbpgrbrrbgy
ffffffgygybpgygprb
ffffffbrrrybgygybg
ffffffgpbbyrprgbbp
ffffffbyrbpybgpryg
ffffffpgyrggrbgyby
fffffybgbpryybpGYp
ffffYyybpyyyyygRow
fffyyyyyyygbyyywww
""";
        test(new String[]{boardWithGreenLeft,"AabcdstuvwxyBabcdefijklotuvwxyCabcdefvwyDabcdeghijkvwxy", "AmBCqDn", "", "abCDE"},"Y13041415Am",false);
        test(new String[]{boardWithoutGreenLeft,"AabcdstuvwxyBabcdefijklotuvwxyCabcdefvwyDabcdeghijkvwxy", "AmBCqDn", "", "abCDE"},"Y13041415Am",true);
        test(new String[]{boardWithoutGreenLeft,"AabcdstuvwxyBabcdefijklotuvwxyCabcdefvwyDabcdeghijkvwxy", "AmBCqDn", "", "abCDE"},"Y13041414Cq",false);
    }


    private final static String[][] fullGame = new String[][]{
            new String[]{
                    """
fffffffffrrfffffff
fffffffffrRfffffff
fffffffffrrfffffff
fffgffpbrrgbyybyrg
fffgGfpgypypbgrggy
fffggggypygygpgbgp
ffffffrpbbprbgybpb
ffffffbygrybpyrgyg
ffffffgrrbgygppbrr
ffffffbbrbyrprgbbp
ffffffrypbpybgpryg
ffffffyggrggrbgyby
fffffyypypryybpgyp
ffffYypbbgbprygrow
fffyyygyrygbygybww
""",
                    "AabcdefghijklmnopqrstuvwxyBbcdeghijkmnopqrsuvwxyCabcdeghijklmnopqrstuwxyDabcdefghijklmnopqrstuvwxy", "ABafltCfvD", "", "abcdefghijklmnopqrstuvwxyzABCDE"},
            new String[]{
                    """
fffffffffrrfffffff
fffffffffrRfffffff
fffffffffrrfffffff
fffgffpbrrgbyybyrg
fffgGfgrrpypbgrggy
fffggggrrygygpgbgp
ffffffgggbprbgybpb
ffffffbygrybpyrgyg
ffffffgrrbgygppbrr
ffffffbbrbyrprgbbp
ffffffrypbpybgpryg
ffffffyggrggrbgyby
fffffyypypryybpgyp
ffffYypbbgbprygrow
fffyyygyrygbygybww
""",
                    "AabcdefghijklmnopqrstuvwxyBbcdeghijkmnopqrsuvwxyCabcdeghijklmnopqrstuwxyDabcdefghijklmnopqrstuvwxy", "ABaflCfvD", "", "abcdefghijklmnopqrstuvwyzABCDE"},
            new String[]{
                    """
fffffffffrrfffffff
fffffffffrRfffffff
fffffffffrrfffffff
fffgffpbrrgbyybyrg
fffgGfgrrpypbgrggy
fffggggrrygygpgbgp
ffffffgggbprbgybpb
ffffffbfgrybpyrgyg
fffffffffbgygppbrr
ffffffbfrbyrprgbbp
ffffffrypbpybgpryg
ffffffyggrggrbgyby
fffffyypypryybpgyp
ffffYypbbgbprygrow
fffyyygyrygbygybww
""",
                    "AabcdefghijklmnopqrstuvwxyBbcdeghijkmnopqrsuvwxyCabcdeghijklmnopqrstuwxyDabcdefghijklmnopqrstuvwxy", "ABaflCfvD", "", "abcdefghijklmnopqrstuvwyzABCDE"},
            new String[]{
                    """
fffffffffrrfffffff
fffffffffrRfffffff
fffffffffrrfffffff
fffgffpbrrgbyybyrg
fffggfgrrpypbgrggy
fffggggrrygygpgbgp
ffffffgggbprbgybpb
ffffffbfGrybpyrgyg
fffffffffbgygppbrr
ffffffbfrbyrprgbbp
ffffffrypbpybgpryg
ffffffyggrggrbgyby
fffffyypypryybpgyp
ffffYypbbgbprygrow
fffyyygyrygbygybww
""",
                    "AabcdefghijklmnopqrstuvwxyBbcdeghijkmnopqrsuvwxyCabcdeghijklmnopqrstuwxyDabcdefghijklmnopqrstuvwxy", "ABalCfvD", "G0708", "abcdefghijklmnopqrstuvwyzABCDE"},
            new String[]{
                    """
fffffffffrrfffffff
fffffffffrRfffffff
fffffffffrrfffffff
fffgffpbrrgbyybyrg
fffggfgrrpypbgrggy
fffggggrrygygpgbgp
ffffffgggbprbgybpb
ffffffbfGrybpyrgyg
fffffffffbgygppbrr
ffffffbfrbyrprgbbp
ffffffrypbpybgpryg
ffffffybbyggrbgyby
fffffyyyyyryybpgyp
ffffYypyggbprygrow
fffyyygyrygbygybww
""",
                    "AabcdefghijklmnopqrstuvwxyBbcdeghijkmnopqrsuvwxyCabcdeghijklmnopqrstuwxyDabcdefghijklmnopqrstuvwxy", "ABalCvD", "G0708", "abcdefghijklmnopqstuvwyzABCDE"},
            new String[]{
                    """
fffffffffrrfffffff
fffffffffrRfffffff
fffffffffrrfffffff
ffffffpbrrgbyybyrg
ffffffgrrpypbgrggy
fffgffgrrygygpgbgp
ffffffgggbprbgybpb
ffffffbfGrybpyrgyg
fffffffffbgygppbrr
ffffffbfrbyrprgbbp
ffffffrypbpybgpryg
ffffffybbyggrbgyby
fffffyyyyyryybpgyp
ffffYypyggbprygrow
fffyyygyrygbygybww
""",
                    "AabcdefghijklmnopqrstuvwxyBbcdeghijkmnopqrsuvwxyCabcdeghijklmnopqrstuwxyDabcdefghijklmnopqrstuvwxy", "ABalCvD", "G0708", "abcdefghijklmnopqstuvwyzABCDE"},
            new String[]{
                    """
fffffffffrrfffffff
fffffffffrRfffffff
fffffffffrrfffffff
ffffffpbrrgbyybyrg
ffffffgrrpypbgrggy
fffgffgrrygygpgbgp
ffffffgggbprbgybpb
ffffffbfGrybpyrgyg
fffffffffbgygppbrr
ffffffbfrbyrprgbbp
ffffffrypbpybgpryg
ffffffybbyggrbgyby
fffffyyyyYryybpgyp
ffffyypyggbprygrow
fffyyygyrygbygybww
""",
                    "AabcdefghijklmnopqrstuvwxyBbcdeghijkmnopqrsuvwxyCabcdeghijklmnopqrstuwxyDabcdefghijklmnopqrstuvwxy", "ABaCvD", "G0708Y1209", "abcdefghijklmnopqstuvwyzABCDE"},
            new String[]{
                    """
fffffffffrrfffffff
fffffffffrrfffffff
fffffffffrrfffffff
ffffffpbrrgbyybyrg
ffffffgrrpypbgrggy
fffgffgrRygygpgbgp
ffffffgggbprbgybpb
ffffffbfGrybpyrgyg
fffffffffbgygppbrr
ffffffbfrbyrprgbbp
ffffffrypbpybgpryg
ffffffybbyggrbgyby
fffffyyyyYryybpgyp
ffffyypyggbprygrow
fffyyygyrygbygybww
""",
                    "AabcdefghijklmnopqrstuvwxyBbcdeghijkmnopqrsuvwxyCabcdeghijklmnopqrstuwxyDabcdefghijklmnopqrstuvwxy", "ABCvD", "G0708R0508Y1209", "abcdefghijklmnopqstuvwyzABCDE"},
            new String[]{
                    """
fffffffffrrfffffff
fffffffffrrfffffff
fffffffffrrfffffff
ffffffpbrrgbyybyrg
ffffffgrrpypbgrggy
fffgffgrRygygpgbgp
ffffffgggbprbgybpb
ffffffbfGgrgpyrgyg
fffffffffgrygppbrr
ffffffbfrgggprgbbp
ffffffrypbpybgpryg
ffffffybbyggrbgyby
fffffyyyyYryybpgyp
ffffyypyggbprygrow
fffyyygyrygbygybww
""",
                    "AabcdefghijklmnopqrstuvwxyBbcdeghijkmnopqrsuvwxyCabcdeghijklmnopqrstuwxyDabcdefghijklmnopqrstuvwxy", "ABCD", "G0708R0508Y1209", "abcdefhijklmnopqstuvwyzABCDE"},
            new String[]{
                    """
fffffffffrrfffffff
fffffffffrrfffffff
fffffffffrrfffffff
ffffffpbrrgbyybyrg
ffffffgrrpypbgrggy
fffgffgrRygygpgbgp
ffffffgggbprbgybpb
ffffffbfGgrgpyrgyg
fffffffffgrygppbrr
ffffffbfrgggprgbbp
ffffffrypbpybgpryg
ffffffybbyggrbgyby
fffffyyyyYryybpgyp
fffffffyggbprygrow
ffffffgyrygbygybww
""",
                    "AabdefghijlnopqrtuvwxyBbcdeghijkmnopqrsuvwxyCadeghijklmnopqrstuwxyDabcdefghijklmnopqrstuvwxy", "AckmsBCbcD", "", "abcdefhijklmnopqstuvwyzABCDE"},
            new String[]{
                    """
fffffffffrrfffffff
fffffffffrrfffffff
fffffffffrrfffffff
ffffffpbrrgbyybyrg
ffffffgrrpypbgrggy
fffgffgrRygygpgbgp
ffffffgggbprbgybpb
ffffffbfGgrgpyrgyg
fffffffffgrygppbrr
ffffffbfrgggprgbbp
ffffffrypbpybgpryg
ffffffybbyggrbgyby
fffffyyyyYyrrbpgyp
fffffffyggyyyygrow
ffffffgyrybbrgybww
""",
                    "AabdefghijlnopqrtuvwxyBbcdeghijkmnopqrsuvwxyCadeghijklmnopqrstuwxyDabcdefghijklmnopqrstuvwxy", "AckmsBCbD", "", "acdefhijklmnopqstuvwyzABCDE"},
            new String[]{
                    """
fffffffffrrfffffff
fffffffffrrfffffff
fffffffffrrfffffff
ffffffpbrrgbyybyrg
ffffffgrrpypbgrggy
fffgffgrRygygpgbgp
ffffffgggbprbgybpb
ffffffbfGgrgpyrgyg
fffffffffgrygppbrr
ffffffbfrgggprgbbp
ffffffrypbpybgpryg
ffffffybbyggrbgyby
ffffffffyYyrrbpgyp
ffffffffggyyyygrow
ffffffffrybbrgybww
""",
                    "AabdefghijlnopqrtuvwxyBbcdeghijkmnopqrsuvwxyCadeghijklmnopqrstuwxyDabcdefghijklmnopqrstuvwxy", "AckmsBCbD", "", "acdefhijklmnopqstuvwyzABCDE"},
            new String[]{
                    """
fffffffffrrfffffff
fffffffffrrfffffff
fffffffffrrfffffff
ffffffpbrrgbyybyrg
ffffffgrrpypbgrggy
fffgffgrRygygpgbgp
ffffffgggbprbgybpb
ffffffbfGgrgpyrgyg
fffffffffgrygppbrr
ffffffbfrggggggbbp
ffffffrypbpyrrrryg
ffffffybbygggggyby
ffffffffyYyrrbpgyp
ffffffffggyyyygrow
ffffffffrybbrgybww
""",
                    "AabdefghijlnopqrtuvwxyBbcdeghijkmnopqrsuvwxyCadeghijklmnopqrstuwxyDabcdefghijklmnopqrstuvwxy", "AckmsBCD", "", "acdefhijklmnopqsuvwyzABCDE"},
            new String[]{
                    """
ffffffffffffffffff
fffffffffrffffffff
fffffffffrffffffff
ffffffpbrrfbyybyrg
ffffffgrrpypbgrggy
fffgffgrRygygpgbgp
ffffffgggbprbgybpb
ffffffbfGgrgpyrgyg
fffffffffgrygppbrr
ffffffbfrggggggbbp
ffffffrypbpyrrrryg
ffffffybbygggggyby
ffffffffyYyrrbpgyp
ffffffffggyyyygrow
ffffffffrybbrgybww
""",
                    "AabdefghijlnopqrtuvwxyBbcdeghijkmnopqrsuvwxyCadeghijklmnopqrstuwxyDabcdefghijklmnopqrstuvwxy", "AckmsBCD", "", "acdefhijklmnopqsuvwyzABCDE"},
            new String[]{
                    """
ffffffffffffffffff
fffffffffrffffffff
fffffffffrffffffff
ffffffpbrrfbyybyrg
ffffffgrrpypbgrggy
fffgffgrRygygpgbgp
ffffffgggbprbgybpb
ffffffbfggrgpyrgyg
fffffffffgrygppbrr
ffffffbfrgggggGbbp
ffffffrypbpyrrrryg
ffffffybbygggggyby
ffffffffyYyrrbpgyp
ffffffffggyyyygrow
ffffffffrybbrgybww
""",
                    "AabdefghijlnopqrtuvwxyBbcdeghijkmnopqrsuvwxyCadeghijklmnopqrstuwxyDabcdefghijklmnopqrstuvwxy", "AcksBCD", "G0914", "acdefhijklmnopqsuvwyzABCDE"},

            new String[]{
                    """
ffffffffffffffffff
fffffffffrffffffff
fffffffffrffffffff
ffffffpbrrfbyybyrg
ffffffgrrgbbbgrggy
fffgffgrRrrbgpgbgp
ffffffgggrrrbgybpb
ffffffbfggrgpyrgyg
fffffffffgrygppbrr
ffffffbfrgggggGbbp
ffffffrypbpyrrrryg
ffffffybbygggggyby
ffffffffyYyrrbpgyp
ffffffffggyyyygrow
ffffffffrybbrgybww
""",
                    "AabdefghijlnopqrtuvwxyBbcdeghijkmnopqrsuvwxyCadeghijklmnopqrstuwxyDabcdefghijklmnopqrstuvwxy", "AckBCD", "G0914", "acdefhjklmnopqsuvwyzABCDE"},
            new String[]{
                    """
ffffffffffffffffff
fffffffffrffffffff
fffffffffrffffffff
ffffffpbrrfbyybyrg
ffffffgrrgbbbgrggy
fffgffgrRrrbgpgbgp
ffffffgggrrrbgybpb
ffffffbfggrgpyrgyg
fffffffffgrygppbrr
ffffffbffgggggGbbp
ffffffffffpyrrrryg
ffffffybfygggggyby
ffffffffyYyrrbpgyp
ffffffffggyyyygrow
ffffffffrybbrgybww
""",
                    "AabdefghijlnopqrtuvwxyBbcdeghijkmnopqrsuvwxyCadeghijklmnopqrstuwxyDabcdefghijklmnopqrstuvwxy", "AckBCD", "G0914", "acdefhjklmnopqsuvwyzABCDE"},
            new String[]{
                    """
ffffffffffffffffff
fffffffffrffffffff
fffffffffrffffffff
ffffffpbrrfbyybyrg
ffffffgrrgbbbgrggy
fffgffgrRrrbgpgbgp
ffffffgggrrrbgybpb
ffffffbfggrgpyrgyg
fffffffffgrygppbrr
ffffffbffgrrrgGbbp
ffffffffffrgrrrryg
ffffffybfyggyggyby
ffffffffyYyrrbpgyp
ffffffffggyyyygrow
ffffffffrybbrgybww
""",
                    "AabdefghijlnopqrtuvwxyBbcdeghijkmnopqrsuvwxyCadeghijklmnopqrstuwxyDabcdefghijklmnopqrstuvwxy",
                    "AkBCD", "G0914", "acdefhklmnopqsuvwyzABCDE"},
            new String[]{
                    """
ffffffffffffffffff
fffffffffrffffffff
ffffffffffffffffff
fffffffffffbyybyrg
ffffffgfrgbbbgrggy
fffgffgrRrrbgpgbgp
ffffffgggrrrbgybpb
ffffffbfggrgpyrgyg
fffffffffgrygppbrr
ffffffbffgrrrgGbbp
ffffffffffrgrrrryg
ffffffybfyggyggyby
ffffffffyYyrrbpgyp
ffffffffggyyyygrow
ffffffffrybbrgybww
""",
                    "AabdefghijlnopqrtuvwxyBbcdeghijkmnopqrsuvwxyCadeghijklmnopqrstuwxyDabcdefghijklmnopqrstuvwxy",
                    "AkBCD", "G0914", "acdefhklmnopqsuvwyzABCDE"},
            new String[]{
                    """
ffffffffffffffffff
fffffffffrffffffff
ffffffffffffffffff
fffffffffffbyybyrg
ffffffgfrgbbbgrggy
fffgffgrrrrbgpgbgp
ffffffgggrrrbgybpb
ffffffbfggrgpyrgyg
fffffffffgrygppbrr
ffffffbffgrrrgGbbp
ffffffffffrgrrRryg
ffffffybfyggyggyby
ffffffffyYyrrbpgyp
ffffffffggyyyygrow
ffffffffrybbrgybww
""",
                    "AadfgijlnopqrtvwxyBbcdeghijkmnopqrsuvwxyCadgijklmnopqrstuwxyDabcdefghijklmnopqrstuvwxy", "AbehuBCehD", "", "acdefhklmnopqsuvwyzABCDE"},
            new String[]{
                    """
ffffffffffffffffff
fffffffffrffffffff
ffffffffffffffffff
fffffffffffbyybyrg
ffffffgfrgbbbgrggy
fffgffgrrrrbgpgbgp
ffffffgggrrrbgybpb
ffffffbfggrgpyrgyg
fffffffffgrygppbrr
ffffffbffgrrrgGbbp
ffffffffffrgrrRryg
ffffffybfygggyryby
ffffffffyYyrgyrgyp
ffffffffggyyggrrow
ffffffffrybbrgybww
""",
                    "AadfgijlnopqrtvwxyBbcdeghijkmnopqrsuvwxyCadgijklmnopqrstuwxyDabcdefghijklmnopqrstuvwxy", "AbehBCehD", "", "acdefhklmnopqsuvwyzACDE"},
            new String[]{
                    """
ffffffffffffffffff
fffffffffrffffffff
ffffffffffffffffff
fffffffffffbyybyrg
ffffffgfrgbbbgrggy
fffgfffrrrrbgpgbgp
fffffffffrrrbgybpb
fffffffffgrgpyrgyg
fffffffffgrygppbrr
ffffffbffgrrrgGbbp
ffffffffffrgrrRryg
ffffffybfygggyryby
ffffffffyYyrgyrgyp
ffffffffggyyggrrow
ffffffffrybbrgybww
""",
                    "AadfgijlnopqrtvwxyBbcdeghijkmnopqrsuvwxyCadgijklmnopqrstuwxyDabcdefghijklmnopqrstuvwxy", "AbehBCehD", "", "acdefhklmnopqsuvwyzACDE"},
            new String[]{
                    """
ffffffffffffffffff
fffffffffrffffffff
ffffffffffffffffff
fffffffffffbyybyrg
ffffffgfrgbbbgrggy
fffgfffrrrrbgpgbgp
fffffffffrrrbgybpb
fffffffffgrgpyrgyg
fffffffffgrygppbrr
ffffffbffgrrrgGbbp
ffffffffffrgrrrryg
ffffffybfygggyryby
ffffffffyYyrgyrgyp
ffffffffggyyggrRow
ffffffffrybbrgybww
""",
                    "AadfgijlnopqrtvwxyBbcdeghijkmnopqrsuvwxyCadgijklmnopqrstuwxyDabcdefghijklmnopqrstuvwxy", "AehBCehD", "R1315", "acdefhklmnopqsuvwyzACDE"},
            new String[]{
                    """
ffffffffffffffffff
fffffffffrffffffff
ffffffffffffffffff
fffffffffffbyybyrg
ffffffgfrgbbbgrggy
fffgfffrrrrbgpgbgp
fffffffffrrrbgybpb
fffffffffgrgpyrgyg
fffffffffgrygppbrr
ffffffbffgrrrgGggg
ffffffffffrgrrrgrr
ffffffybfygggyrgrr
ffffffffyYyrgyrgyp
ffffffffggyyggrRow
ffffffffrybbrgybww
""",
                    "AadfgijlnopqrtvwxyBbcdeghijkmnopqrsuvwxyCadgijklmnopqrstuwxyDabcdefghijklmnopqrstuvwxy", "AehBCeD", "R1315", "acdefhklmnopsuvwyzACDE"},
            new String[]{
                    """
ffffffffffffffffff
fffffffffrffffffff
ffffffffffffffffff
fffffffffffbyybyrg
ffffffgfffbbbgrggy
fffgfffffffbgpgbgp
fffffffffrrrbgybpb
fffffffffgrgpyrgyg
fffffffffgrygppbrr
ffffffbffgrrrgGggg
ffffffffffrgrrrgrr
ffffffybfygggyrgrr
ffffffffyYyrgyrgyp
ffffffffggyyggrRow
ffffffffrybbrgybww
""",
                    "AadfgijlnopqrtvwxyBbcdeghijkmnopqrsuvwxyCadgijklmnopqrstuwxyDabcdefghijklmnopqrstuvwxy", "AehBCeD", "R1315", "acdefhklmnopsuvwyzACDE"},
            new String[]{
                    """
ffffffffffffffffff
fffffffffrffffffff
ffffffffffffffffff
fffffffffffbyybyrg
ffffffgfffbbbgrggy
fffgfffffffbgpgbgp
fffffffffrrrbgybpb
fffffffffgrgpyrgyg
fffffffffgrygppbrr
ffffffbffgrrrggggg
ffffffffffrgrrrgrr
ffffffybfygggyrgrr
ffffffffyYyrgyrGyp
ffffffffggyyggrRow
ffffffffrybbrgybww
""",
                    "AadfgijlnopqrtvwxyBbcdeghijkmnopqrsuvwxyCadgijklmnopqrstuwxyDabcdefghijklmnopqrstuvwxy", "AeBCeD", "G1215R1315", "acdefhklmnopsuvwyzACDE"},
            new String[]{
                    """
ffffffffffffffffff
fffffffffrffffffff
ffffffffffffffffff
fffffffffffbyybyrg
ffffffgfffbbbgrggy
fffgfffffffbgpgbgp
fffffffffrrrbgybpb
fffffffffgrgpyrgyg
fffffffffgrygppbrr
ffffffbffgrrrggggg
ffffffffffppprrgrr
ffffffybfyyyyyrgrr
ffffffffyYbbbyrGyp
ffffffffggyyggrRow
ffffffffrybbrgybww
""",
                    "AadfgijlnopqrtvwxyBbcdeghijkmnopqrsuvwxyCadgijklmnopqrstuwxyDabcdefghijklmnopqrstuvwxy", "AeBCD",
                    "G1215R1315", "acdefhklmopsuvwyzACDE"},
            new String[]{
                    """
ffffffffffffffffff
fffffffffrffffffff
ffffffffffffffffff
fffffffffffbyybfff
ffffffgfffbbbgrfgf
fffgfffffffbgpgbgp
fffffffffrrrbgybpb
fffffffffgrgpyrgyg
fffffffffgrygppbrr
ffffffbffgrrrggggg
ffffffffffppprrgrr
ffffffybfyyyyyrgrr
ffffffffyYbbbyrGyp
ffffffffggyyggrRow
ffffffffrybbrgybww
""",
                    "AadfgijlnopqrtvwxyBbcdeghijkmnopqrsuvwxyCadgijklmnopqrstuwxyDabcdefghijklmnopqrstuvwxy", "AeBCD", "G1215R1315", "acdefhklmopsuvwyzACDE"},
            new String[]{
                    """
ffffffffffffffffff
fffffffffrffffffff
ffffffffffffffffff
fffffffffffbyybfff
ffffffgfffbbbgrfgf
fffgfffffffbgpgbgp
fffffffffrrrbgybpb
fffffffffgrgpyrgyg
fffffffffgrygppbrr
ffffffbffgrrrggggg
ffffffffffppprrgrr
ffffffybfyyYyyrgrr
ffffffffyybbbyrGyp
ffffffffggyyggrRow
ffffffffrybbrgybww
""",
                    "AadfgijlnopqrtvwxyBbcdeghijkmnopqrsuvwxyCadjklmnopstuwxyDabcfghijklmnopqrstuvwxy","ABCgiqrDde","","acdefhklmopsuvwyzACDE"},
            new String[]{
                    """
ffffffffffffffffff
fffffffffrffffffff
ffffffffffffffffff
fffffffffffbyybfff
ffffffgfffbbbgrfgf
fffgfffffffbgpgbgp
fffffffffrrrbgybpb
fffffffffgrgpyrgyg
fffffffffgrygppbrr
ffffffbffgrrrgpppg
ffffffffffppprbpyr
ffffffybfyyYyyyyyr
ffffffffyybbbyrGyp
ffffffffggyyggrRow
ffffffffrybbrgybww
""",
                    "AadfgijlnopqrtvwxyBbcdeghijkmnopqrsuvwxyCadjklmnopstuwxyDabcfghijklmnopqrstuvwxy","ABCgirDde","","acdefhlmopsuvwyzACDE"},
            new String[]{
                    """
ffffffffffffffffff
fffffffffrffffffff
ffffffffffffffffff
ffffffffffffffbfff
ffffffgfffbfbgrfgf
fffgffffffffgpgbgp
fffffffffrrrbgybpb
fffffffffgrgpyrgyg
fffffffffgrygppbrr
ffffffbffgrrrgpppg
ffffffffffppprbpyr
ffffffybfyyYyyyyyr
ffffffffyybbbyrGyp
ffffffffggyyggrRow
ffffffffrybbrgybww
""",
                    "AadfgijlnopqrtvwxyBbcdeghijkmnopqrsuvwxyCadjklmnopstuwxyDabcfghijklmnopqrstuvwxy","ABCgirDde","","acdefhlmopsuvwyzACDE"},


    };


    private final static String[] actions = new String[]{
            "Bt0406N", // card
            "x0706FN", // fire
            "G04040708Af", // cat
            "Cf1107W", // card
            "r0303FS", // fire
            "Y13041209Al", // cat
            "R01100508Ba", // cat
            "Cv0709N", // card
            "g1303TN", // fire
            "Cc1210W", // card
            "b1205TN", // fire
            "Cb0912E", // card
            "t0009FE", // fire
            "G07080914Am", // cat
            "As0409N", // card
            "i0906FS", // fire
            "Ac0910N", // card
            "j0206TN", // fire
            "R05081014Ak", // cat
            "Au1112N", // card
            "B0506TW", // fire
            "R10141315Ab", // cat
            "Ch0915N", // card
            "q0407FS", // fire
            "G09141215Ah", // cat
            "Ce1010E", // card
            "n0315FW", // fire
            "Y12091111Ae", // cat
            "Cq0914W", // card
            "k0311FN", // fire
            "Y11111216De", // cat

    };
}
