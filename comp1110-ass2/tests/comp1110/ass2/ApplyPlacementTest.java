package comp1110.ass2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.concurrent.TimeUnit;

@Timeout(value = 500, unit = TimeUnit.MILLISECONDS)
public class ApplyPlacementTest {

    private void testBoard(String[] gameState, String placement, String[] expected) {
        String[] out = RaceToTheRaft.applyPlacement(gameState, placement);
        Assertions.assertEquals(expected[0], out[0]);
    }

    private void testHand(String[] gameState, String placement, String[] expected) {
        String[] out = RaceToTheRaft.applyPlacement(gameState, placement);
        Assertions.assertEquals(expected[2], out[2]);
    }



    @Test
    public void testPlacePathwayCard() {
        for (int i = 0; i < gameStates.length; i++) {
            testBoard(gameStates[i], placements[i], expected[i]);
        }
    }

    @Test
    public void testCardRemovedFromHand() {
        for (int i = 0; i < gameStates.length; i++) {
            testHand(gameStates[i], placements[i], expected[i]);
        }
    }

    @Test
    public void testPlaceFireTile() {
        for (int i = 0; i < fireGameStates.length; i++) {
            testBoard(fireGameStates[i], firePlacements[i], fireExpected[i]);
        }
    }

    private final String[][] gameStates = new String[][]{
            new String[]{
                    """
                ffffffffffffffffpf
                fffffffffbBbffffff
                fffffffffbbbffffpf
                fffrrfbgbgrgbyffff
                fffrRfybprypfffgfp
                fffrrfyrybggpffPpp
                fffygybgybpppffpyr
                fffgyrpbgggggfgpyr
                fffpbyyrppgrbpbpyr
                fffffyggrgpbyprpby
                ffffYyypbbygrygror
                fffyyyrybgrybpygwg
                """,
                    "AabcefghijklmnopqrstuvxyBbcdfghikmopqrstuwyCabcdefghijklmnopqrstuvwxyDbcdefghijklmnpqrstuvwx",
                    "AwBaejlCDo", "", "acdefghiklmnopqrstuvwxyzBCDE"
            },
            new String[]{
                    """
                ffffffffffffgfffff
                ffffffffffffgGffff
                ffffffffffffgggfff
                fffbybgypybygbrprp
                fffbrgygggrrggbbow
                fffybpbryprbbrpbyg
                ffygrybbrgybbgpgpb
                fYyybgrypbbByrgbyr
                yyybpfffpbbrgybpgy
                ffffffgpgbbybrrbgb
                bfbffffbbbrgpygybp
                ffffffbyrbprgpbyry
                """,
                    "AabcdefghijklnopqrstuvxyBabdeghiklmnoqrstuvwxyCabcdfghijklmnopqrstuvwxyDabcdefghilnopqtuvwxy",
                    "ABcfjCDjks", "", "abcefghijklmnoprstuvwxyzBCDE"

            },
            new String[]{
                    """
                ffffffffffffffffff
                ffffffffffffffffff
                fffffffffffyffffff
                fffffffffgypfffprp
                fffffffffyyrfffbow
                ffffffffffypfffbyg
                fffffffpfbyyfffbpg
                ffffffbygbrYfffryb
                ffffffgrrgybfffrgy
                ffffffbbbyrybpgrpb
                fffbbbgggpbygypgrb
                fffbbbbbbbgbrrbygg
                ffffffgffbbbBygrpy
                ffffffggfgggGrybrp
                ffffffggggyypgbyyg
                """,
                    "AabcfghijlmnopqrstuvwxBabcdefghjklmnopqstuvwxyCacdefghijklmnopqrstuvwxDabcdfghjklmnpqrstuwxy",
                    "ABirCbyDio", "", "abcdefghjklmnopqstuvwxyzABCE"
            },
            new String[]{
                    """
                fffffffffffyffffff
                ffffffffffyyffffff
                ffffffffffyyffffff
                ffffffffffgyryrfff
                ffffffffffpyryyfbf
                fffffffffpryryprpg
                fffgffffbgpyggpyyb
                fffgffffpgyygbgpry
                ffffffffggGYybybbg
                ffffffggggbggrybbr
                ffffffbyrgrpybgryp
                ffffffpggggrbpbygg
                ffffffbrgggbyprwww
                fffbbbbrbbBgrygwow
                fffbbbbbbgrybpywww
                """,
                    "AabcdfghikmnopqrstuwxyBabcdefgijklmnopqrstuvwxyCabcdefhikmnopqrstuvwyDacdefghilmnopqrstuvwxy",
                    "AeBCjlDj", "Y0811", "abcdefgijklmopqrstuwxyzCDE"},
    };

    private final String[][] expected = new String[][]{
            new String[]{
                    """
                ffffffffffffffffpf
                fffffffffbBbffffff
                fffffffffbbbffffpf
                fffrrfbgbbbybyffff
                fffrRfybpbrrfffgfp
                fffrrfyrybbrpffPpp
                fffygybgybpppffpyr
                fffgyrpbgggggfgpyr
                fffpbyyrppgrbpbpyr
                fffffyggrgpbyprpby
                ffffYyypbbygrygror
                fffyyyrybgrybpygwg
                """,
                    "AabcefghijklmnopqrstuvxyBbcdfghikmopqrstuwyCabcdefghijklmnopqrstuvwxyDbcdefghijklmnpqrstuvwx",
                    "AwBaelCDo", "", "acdefghiklmnopqrstuwxyzBCDE"
            },
            new String[]{
                    """
                ffffffffffffgfffff
                ffffffffffffgGffff
                ffffffffffffgggfff
                fffbybgypybygbrprp
                fffbrgygggrrbbbbow
                fffybpbryprbbrbbyg
                ffygrybbrgybrrygpb
                fYyybgrypbbByrgbyr
                yyybpfffpbbrgybpgy
                ffffffgpgbbybrrbgb
                bfbffffbbbrgpygybp
                ffffffbyrbprgpbyry
                """,
                    "AabcdefghijklnopqrstuvxyBabdeghiklmnoqrstuvwxyCabcdfghijklmnopqrstuvwxyDabcdefghilnopqtuvwxy",
                    "ABcfCDjks", "", "abcefghijklmnoprstuvwxyBCDE"
            },
            new String[]{
                    """
                ffffffffffffffffff
                ffffffffffffffffff
                fffffffffffyffffff
                fffffffffgypfffprp
                fffffffffyyrfffbow
                ffffffffffypfffbyg
                fffffffpfbyyfffbpg
                ffffffbygbrYfffryb
                ffffffgrrgybfffrgy
                ffffffbbbyrybpgrpb
                fffbbbgggpbygypgrb
                fffbbbbbbbgbrrbygg
                ffffffgffbbbBbbgpy
                ffffffggfgggGgggrp
                ffffffggggyypyyyyg
                """,
                    "AabcfghijlmnopqrstuvwxBabcdefghjklmnopqstuvwxyCacdefghijklmnopqrstuvwxDabcdfghjklmnpqrstuwxy",
                    "ABirCbyDo", "", "abcdefghjklmnpqstuvwxyzABCE"
            },
            new String[]{
                    """
                fffffffffffyffffff
                ffffffffffyyffffff
                ffffffffffyyffffff
                ffffffffffgyryrfff
                ffffffffffpyryyfbf
                fffffffffpryryprpg
                fffgffffbgpyggpyyb
                fffgffffpgyygbgpry
                ffffffffggGYybybbg
                ffffffgggpgygrybbr
                ffffffbyrpgyybgryp
                ffffffpggpgybpbygg
                ffffffbrgggbyprwww
                fffbbbbrbbBgrygwow
                fffbbbbbbgrybpywww
                """,
                    "AabcdfghikmnopqrstuwxyBabcdefgijklmnopqrstuvwxyCabcdefhikmnopqrstuvwyDacdefghilmnopqrstuvwxy",
                    "AeBCjlD", "Y0811", "abcdefgijklmopqrstwxyzCDE"},

    };

    private final String[] placements = new String[]{
            "Bj0309W",
            "Bj0412N",
            "Di1213E",
            "Dj0909S",
    };

    private final String[][] fireGameStates = new String[][]{
            new String[]{
                    """
gffrrffff
gGfrRfbBb
gggrrfbbY
ppgyrybgg
ygbrrybyy
pgbyrybgg
gbrgpprpb
rypygbgby
rgbpyrpyr
grgpybygr
gyprpgbyb
brbbrbgyr
gypgpypbg
pbrrbgpry
ygppyrygb
fffrgggyp
fffpbyrow
fffgybbww
""",
                    "AabcdefhijklmnopqrstuvwxyBabcdefghijklmnopqrstuvwxyCabcdefghijklmnopqrstuvwxyDabcdefghijklmopqtwxy","AgBCDnruv","","bcdefghijklmnopqrstuvwxyzABCDE"},
            new String[]{
                    """
gffrrffff
gGfrrfbBb
gggrrfbbY
ppgyrybgg
ygbrrybyy
pgbyRybgg
gbrgrrbpb
rypyrbbby
rgbpbbbyr
grgpybygr
gyprpgbyb
brbbrbgyr
gfpgpypbg
fffrbgpry
ffppyrygb
fffrgggyp
fffpbyrow
fffgybbww
""",
                    "AabcdefhijklmnopqrstuvwxyBabcdefghijklmnopqrstuvwxyCabcdefghijklmnopqrstuvwxyDabcdefghijklmopqtwxy","ABCDnrv","R0504","bcdefgijklmnopqrstuvwxyzABCDE"},
            new String[]{
                    """
fffgffffyffffppgff
fffgGffYyffffPpgGf
fffgggyyyffffpRggg
fffbggygrfffgpypbg
fffpbgygyfffrbgpry
fffyrgggpfffpyrygb
fffgbbrpbfffrggbpg
fffpggbygfffpbyryb
fffpypgrrfffgybrgy
ffffffbgyyrbpbygpb
fffbBbpbgggygrbpyr
fffbbbyrppygbybgrg
fffgbrggrpbyygpybg
fffpgbypbrorpbrgpb
fffgrprybgwggrbpyy
""",
                    "AabcdefghijklmnopqrstuvwxyBabdefhijklmopqrstuvwxyCabcdefghijklmnopqrstuvxyDacdefghijklmnoprstuvwxy","ABcgnCDbq","","abcdefghijkmnopqrstuvwxyzABCDE"},
            new String[]{
                    """
fffgffffyffffppgff
fffggffyyffffPpgGf
fffgggyyyffffpRggg
fffbggygrfffgpppgg
fffpbgYgyfffrbgpgy
fffyrGggpfffpygpgb
fffgbbrpbfffrggbpg
fffpggbygfffpbyryb
fffpypgrrfffgybrgy
ffffffbgyyrbpbygpb
fffbBbpbgggygrbpyr
ffffbbyrppygbybgrg
fffffrggrpbyygpybg
fffpffypbrorpbrgpb
fffgfprybgwggrbpyy
""",
                    "AabcdefghijklmnopqrstuvwxyBabdefhijklmopqrstuvwxyCabcdefghijklmnopqrstuvxyDacdefghijklmnoprstuvwxy","ABgCDq","G0505Y0406","abcdefghijkmnopqrstuvxyzABCDE"},


    };

    private final String[] firePlacements = new String[]{
            "a1200FW",
            "h1000FW",
            "l1103TW",
            "w0103FS",

    };

    private final String[][] fireExpected = new String[][]{
            new String[]{
                    """
gffrrffff
gGfrRfbBb
gggrrfbbY
ppgyrybgg
ygbrrybyy
pgbyrybgg
gbrgpprpb
rypygbgby
rgbpyrpyr
grgpybygr
gyprpgbyb
brbbrbgyr
gfpgpypbg
fffrbgpry
ffppyrygb
fffrgggyp
fffpbyrow
fffgybbww
""",
                    "AabcdefhijklmnopqrstuvwxyBabcdefghijklmnopqrstuvwxyCabcdefghijklmnopqrstuvwxyDabcdefghijklmopqtwxy","AgBCDnruv","","bcdefghijklmnopqrstuvwxyzABCDE"},
            new String[]{
                    """
gffrrffff
gGfrrfbBb
gggrrfbbY
ppgyrybgg
ygbrrybyy
pgbyRybgg
gbrgrrbpb
rypyrbbby
rgbpbbbyr
grgpybygr
fyprpgbyb
fffbrbgyr
ffpgpypbg
fffrbgpry
ffppyrygb
fffrgggyp
fffpbyrow
fffgybbww
""",
                    "AabcdefhijklmnopqrstuvwxyBabcdefghijklmnopqrstuvwxyCabcdefghijklmnopqrstuvwxyDabcdefghijklmopqtwxy","ABCDnrv","R0504","bcdefgijklmnopqrstuvwxyzABCDE"},
            new String[]{
                    """
fffgffffyffffppgff
fffgGffYyffffPpgGf
fffgggyyyffffpRggg
fffbggygrfffgpypbg
fffpbgygyfffrbgpry
fffyrgggpfffpyrygb
fffgbbrpbfffrggbpg
fffpggbygfffpbyryb
fffpypgrrfffgybrgy
ffffffbgyyrbpbygpb
fffbBbpbgggygrbpyr
ffffbbyrppygbybgrg
fffffrggrpbyygpybg
fffpffypbrorpbrgpb
fffgfprybgwggrbpyy
""",
                    "AabcdefghijklmnopqrstuvwxyBabdefhijklmopqrstuvwxyCabcdefghijklmnopqrstuvxyDacdefghijklmnoprstuvwxy","ABcgnCDbq","","abcdefghijkmnopqrstuvwxyzABCDE"},
            new String[]{
                    """
fffgffffyffffppgff
ffffgffyyffffPpgGf
ffffffyyyffffpRggg
fffbgfygrfffgpppgg
fffpbgYgyfffrbgpgy
fffyrGggpfffpygpgb
fffgbbrpbfffrggbpg
fffpggbygfffpbyryb
fffpypgrrfffgybrgy
ffffffbgyyrbpbygpb
fffbBbpbgggygrbpyr
ffffbbyrppygbybgrg
fffffrggrpbyygpybg
fffpffypbrorpbrgpb
fffgfprybgwggrbpyy
""",
                    "AabcdefghijklmnopqrstuvwxyBabdefhijklmopqrstuvwxyCabcdefghijklmnopqrstuvxyDacdefghijklmnoprstuvwxy","ABgCDq","G0505Y0406","abcdefghijkmnopqrstuvxyzABCDE"},

    };

}
