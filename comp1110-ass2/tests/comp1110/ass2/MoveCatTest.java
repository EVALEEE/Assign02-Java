package comp1110.ass2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.concurrent.TimeUnit;

@Timeout(value = 500, unit = TimeUnit.MILLISECONDS)
public class MoveCatTest {

    private void testBoard(String[] gameState, String placement, String[] expected) {
        String[] out = RaceToTheRaft.moveCat(gameState, placement);
        Assertions.assertEquals(expected[0], out[0]);
    }

    private void testExhausted(String[] gameState, String placement, String[] expected) {
        String[] out = RaceToTheRaft.moveCat(gameState, placement);
        Assertions.assertEquals(expected[3], out[3]);
    }

    @Test
    public void testMoveCat() {
        for (int i = 0; i < gameStates.length; i++) {
            testBoard(gameStates[i], placement[i], expected[i]);
        }
    }

    @Test
    public void testCatExhausted() {
        for (int i = 0; i < gameStates.length; i++) {
            testExhausted(gameStates[i], placement[i], expected[i]);
        }
    }

    private final String[][] gameStates = new String[][]{
            new String[]{
                    """
                fffffffffffyffffff
                ffffffffffYyffffff
                fffffffffyyyffffff
                fffffffrggypgpypbg
                fffffffffpyrybgpry
                ffffffbfpyyryyrygb
                fffgffbpbbyryggbpg
                fffgGfgygbrypbyryb
                fffgggbrrgybgybrgy
                ffffffprgbrpygybgy
                ffffffybygrbgyrpbg
                ffffffbrrgyppbyyrp
                ffffffgypybygbrwww
                fffbBbygggyrpgbwow
                fffbbbbrypbbgrpwww
                """,
                    "AabcdefghijklmnopqrstuvwxyBabcdefghijklmnopqrstuvwxyCabcdefhklmnpqrstuwyDabcdefghijklmnopqrstuvwxy", "ABCgijovD", "", "abcdefghijklmnopqrstuvwxzABCDE"},
            new String[]{
                    """
                fffffffffffffffrrf
                fffffffffbBbfffrRf
                fffffffffbbbfffrrf
                ffffffffppbgbpbrgg
                fffffffffybbbbgryy
                ffffffpfypbyyrybgg
                ffffffffpgbrgpprpb
                ffffPffyppbpygbgby
                ffffpppppyybpyrpyr
                ffffffbgryybpbygpb
                ffffffpbgggygrbpyr
                ffffffyrppygbybgrg
                ffffffggrrrbygpprp
                fffbBbypbybypbrbow
                fffbbbrybgrpgrbbyg
                """,
                    "AabcdefgijklmnoprstuvwxyBabdefghijklnopqrstuvwxyCabcdefghiklmnopqrstuvwxyDabcefghijklmnopqrstuvwxy", "AhBcmCjD", "", "bcdefghijklmnoprstuvwxyzABCDE"},
            new String[]{
                    """
                fffffffffffffffrrf
                fffffffffbBbfffrRf
                fffffffffbbbfffrrf
                ffffffffppbgbpbrgg
                fffffffffybbbbgryy
                ffffffpfypbyyrybgg
                ffffffffpgbrgpprpb
                ffffpffypPbpygbgby
                ffffpppppyybpyrpyr
                ffffffbgryybpbygpb
                ffffffpbgggygrbpyr
                ffffffyrppygbybgrg
                ffffffggrrrbygpprp
                fffbBbypbybypbrbow
                fffbbbrybgrpgrbbyg
                """,
                    "AabcdefgijklmnoprstuvwxyBabdefghijklnopqrstuvwxyCabcdefghiklmnopqrstuvwxyDabcefghijklmnopqrstuvwxy", "AhBcmCD", "P0709", "bcdefghijklmnoprstuvwxyzABCDE"},
            new String[]{
                    """
                fffffffffffffffrrf
                fffffffffbbbfffrRf
                fffffffffbbbfffrrf
                ffffffffppbgbpbrgg
                fffffffffybbbbgryy
                ffffffpfypbyyrybgg
                ffffffffpgbrgpprpb
                ffffpffypPBpygbgby
                ffffpppppyybpyrpyr
                ffffffbgryybpbygpb
                ffffffpbgggygrbpyr
                ffffffyrppygbybgrg
                ffffffggrrrbygpprp
                fffbBbypbybypbrbow
                fffbbbrybgrpgrbbyg
                """,
                    "AabcdefgijklmnoprstuvwxyBabdefghijklnopqrstuvwxyCabcdefghiklmnopqrstuvwxyDabcefghijklmnopqrstuvwxy", "AhBcCD", "B0710P0709", "bcdefghijklmnoprstuvwxyzABCDE"},
            new String[]{
                    """
                fffffffffffffffrrf
                fffffffffffbfffrRf
                fffffffffffbfffrrf
                ffffffffffbgbpbrgg
                fffffffffybbbBgryy
                ffffffpfypbyyrbrbg
                ffffffffpgbrgpbrbb
                fffffffypPppbgbrby
                ffffffffpyypbyrpyr
                fffffffgryybbbygpb
                ffffffpbgggygrbpyr
                ffffffyrppygbybgrg
                ffffffggrrrbygpprp
                fffbBbypbybypbrbow
                fffbbbrybgrpgrbbyg
                """,
                    "AabcdefgijklmnoprstuvwxyBabdeghijklnoqrstvwxCabdefghiklmnopqrstvwxyDabcefghijklmnopqrstuvwxy", "ABpyCcuD", "", "bcdefghiklmnoprtuvwxyzABCDE"},
            new String[]{
                    """
                fffffffffffffffrrf
                fffffffffffbfffrRf
                fffffffffffbfffrrf
                ffffffffffbgbpbrgg
                fffffffffybbbBgryy
                ffffffpfypbyyrbrbg
                ffffffffpgbrgpbrbb
                fffffffyppppbgbrby
                ffffffffpyyPbyrpyr
                fffffffgryybbbygpb
                ffffffpbgggygrbpyr
                ffffffyrppygbybgrg
                ffffffggrrrbygpprp
                fffbBbypbybypbrbow
                fffbbbrybgrpgrbbyg
                """,
                    "AabcdefgijklmnoprstuvwxyBabdeghijklnoqrstvwxCabdefghiklmnopqrstvwxyDabcefghijklmnopqrstuvwxy", "AByCcuD", "P0811", "bcdefghiklmnoprtuvwxyzABCDE"},


    };

    private final String[] placement = new String[]{
            "Y01100610Cv",
            "P07040709Cj",
            "B01100710Bm",
            "B07100413AhBc",
            "P07090811Bp",
            "R01160715By",
    };

    private final String[][] expected = new String[][]{
            new String[]{
                    """
                fffffffffffyffffff
                ffffffffffyyffffff
                fffffffffyyyffffff
                fffffffrggypgpypbg
                fffffffffpyrybgpry
                ffffffbfpyyryyrygb
                fffgffbpbbYryggbpg
                fffgGfgygbrypbyryb
                fffgggbrrgybgybrgy
                ffffffprgbrpygybgy
                ffffffybygrbgyrpbg
                ffffffbrrgyppbyyrp
                ffffffgypybygbrwww
                fffbBbygggyrpgbwow
                fffbbbbrypbbgrpwww
                """,
                    "AabcdefghijklmnopqrstuvwxyBabcdefghijklmnopqrstuvwxyCabcdefhklmnpqrstuwyDabcdefghijklmnopqrstuvwxy", "ABCgijoD", "Y0610", "abcdefghijklmnopqrstuvwxzABCDE"},
            new String[]{
                    """
                fffffffffffffffrrf
                fffffffffbBbfffrRf
                fffffffffbbbfffrrf
                ffffffffppbgbpbrgg
                fffffffffybbbbgryy
                ffffffpfypbyyrybgg
                ffffffffpgbrgpprpb
                ffffpffypPbpygbgby
                ffffpppppyybpyrpyr
                ffffffbgryybpbygpb
                ffffffpbgggygrbpyr
                ffffffyrppygbybgrg
                ffffffggrrrbygpprp
                fffbBbypbybypbrbow
                fffbbbrybgrpgrbbyg
                """,
                    "AabcdefgijklmnoprstuvwxyBabdefghijklnopqrstuvwxyCabcdefghiklmnopqrstuvwxyDabcefghijklmnopqrstuvwxy", "AhBcmCD", "P0709", "bcdefghijklmnoprstuvwxyzABCDE"},
            new String[]{
                    """
                fffffffffffffffrrf
                fffffffffbbbfffrRf
                fffffffffbbbfffrrf
                ffffffffppbgbpbrgg
                fffffffffybbbbgryy
                ffffffpfypbyyrybgg
                ffffffffpgbrgpprpb
                ffffpffypPBpygbgby
                ffffpppppyybpyrpyr
                ffffffbgryybpbygpb
                ffffffpbgggygrbpyr
                ffffffyrppygbybgrg
                ffffffggrrrbygpprp
                fffbBbypbybypbrbow
                fffbbbrybgrpgrbbyg
                """,
                    "AabcdefgijklmnoprstuvwxyBabdefghijklnopqrstuvwxyCabcdefghiklmnopqrstuvwxyDabcefghijklmnopqrstuvwxy", "AhBcCD", "B0710P0709", "bcdefghijklmnoprstuvwxyzABCDE"},
            new String[]{
                    """
                fffffffffffffffrrf
                fffffffffbbbfffrRf
                fffffffffbbbfffrrf
                ffffffffppbgbpbrgg
                fffffffffybbbBgryy
                ffffffpfypbyyrybgg
                ffffffffpgbrgpprpb
                ffffpffypPbpygbgby
                ffffpppppyybpyrpyr
                ffffffbgryybpbygpb
                ffffffpbgggygrbpyr
                ffffffyrppygbybgrg
                ffffffggrrrbygpprp
                fffbBbypbybypbrbow
                fffbbbrybgrpgrbbyg
                """,
                    "AabcdefgijklmnoprstuvwxyBabdefghijklnopqrstuvwxyCabcdefghiklmnopqrstuvwxyDabcefghijklmnopqrstuvwxy", "ABCD", "B0413P0709", "bcdefghijklmnoprstuvwxyzABCDE"},
            new String[]{
                    """
                fffffffffffffffrrf
                fffffffffffbfffrRf
                fffffffffffbfffrrf
                ffffffffffbgbpbrgg
                fffffffffybbbBgryy
                ffffffpfypbyyrbrbg
                ffffffffpgbrgpbrbb
                fffffffyppppbgbrby
                ffffffffpyyPbyrpyr
                fffffffgryybbbygpb
                ffffffpbgggygrbpyr
                ffffffyrppygbybgrg
                ffffffggrrrbygpprp
                fffbBbypbybypbrbow
                fffbbbrybgrpgrbbyg
                """,
                    "AabcdefgijklmnoprstuvwxyBabdeghijklnoqrstvwxCabdefghiklmnopqrstvwxyDabcefghijklmnopqrstuvwxy", "ABpCcuD", "P0811", "bcdefghiklmnoprtuvwxyzABCDE"},
            new String[]{
                    """
                fffffffffffffffrrf
                fffffffffffbfffrrf
                fffffffffffbfffrrf
                ffffffffffbgbpbrgg
                fffffffffybbbBgryy
                ffffffpfypbyyrbrbg
                ffffffffpgbrgpbrbb
                fffffffyppppbgbRby
                ffffffffpyyPbyrpyr
                fffffffgryybbbygpb
                ffffffpbgggygrbpyr
                ffffffyrppygbybgrg
                ffffffggrrrbygpprp
                fffbBbypbybypbrbow
                fffbbbrybgrpgrbbyg
                """,
                    "AabcdefgijklmnoprstuvwxyBabdeghijklnoqrstvwxCabdefghiklmnopqrstvwxyDabcefghijklmnopqrstuvwxy","ABCcuD","P0811R0715","bcdefghiklmnoprtuvwxyzABCDE"},
    };
}

