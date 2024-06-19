package comp1110.ass2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.concurrent.TimeUnit;

@Timeout(value = 500, unit = TimeUnit.MILLISECONDS)
public class IsBoardStringWellFormedTest {

    @Test
    public void testNewLines() {
        // Well-formed game state
        String wellFormed =
                """
                fffffffffffffffrrf
                fffffffffbbbfffrRf
                fffffffffbbbfffrrf
                ffffffyfffypfffpbr
                ffffffffffgffbrpgy
                ffffffbfffffyrygyp
                ffffppgyybbbbybrpb
                ffffppppyyggbygbyg
                ffffppppPbbgbbpgrr
                ffffffffgbyyybgbbp
                fffffffbybpybbpryg
                fffffffrfrgyybgyby
                fffffffyppryyBpprp
                fffbBbygggbprygbow
                fffbbbbryygbygybyg
                """;
        Assertions.assertTrue(RaceToTheRaft.isBoardStringWellFormed(wellFormed));
        String tabInsteadOfNewline =
                """
                fffffffffffyffffff
                ffffffffffyyffffff\tfffffffffyyyffffff
                fffffffffybprfffff
                ffffffffyyrybfprff\tfffffffypyrygfbfff
                fffgbbrpbyrybfryff
                fffpggbygygryypgff
                fffpypgrryypggybbg\tfffgffbgyygpypbyrg
                fffgGfpbgygpgyrgyb
                fffgggyrpYpyrpybpg
                ffffffrrbbbbbpbwww
                fffbbbbyyggggbywow
                fffbbbbbbbbByrgwww
                """;
        Assertions.assertFalse(RaceToTheRaft.isBoardStringWellFormed(tabInsteadOfNewline));
        String noNewLineAtEnd =
                """
                fffffffffrrfffffff
                fffffffffrrfffffff
                fffffffffrrfffffff
                fffgffpbgprbyfffff
                fffgGfpryyrbybfrfg
                fffgggygbrrryffpfb
                ffffffbfgpgrbrrbfy
                ffffffrfbgrrgygprb
                ffffffffybrRgygybg
                ffffffffrfbgbrgprg
                ffffffffffpgrbpyby
                ffffffybfpbypgybrr
                fffffyyyybbbbybgyp
                ffffyypyyyyYbrgrow
                fffyyygyrrrbybpbww""";
        Assertions.assertFalse(RaceToTheRaft.isBoardStringWellFormed(noNewLineAtEnd));
    }

    @Test
    public void testLineLength() {
        String wellFormed =
                """
                fffrrfffffffffffff
                fffrrfffffffffffff
                ffffrfffffffffffff
                fffffffrpgffffyrrf
                fffffffpygGfpfyrRf
                ffffffggbggggffrrf
                fppyyrRbyffybgypry
                fppgrbyygfYyggrgyr
                fppppprbryyypygbpb
                fffggPbgygffbgpgpb
                fffgbbpbgggfyrgbyr
                fffpbyyrpggggybpgy
                ffffffggrpbygyrbgb
                ffffffypbrorgyrybp
                ffffffrybgwGggryry
                """;
        Assertions.assertTrue(RaceToTheRaft.isBoardStringWellFormed(wellFormed));
        String variableNineWidth =
                """
                fffffyfff
                bBbffffff
                bbbyfyggf
                gbbYppGff
                rbbrgpffy
                rrbygpgfg
                grbyppffff
                rrbygbgfy
                rgbpyrpfr
                yrbpbyprp
                ggygrbbow
                pygbybbyg
                rrbygpybg
                ybypbrgpb
                grpgrbpyy
                """;
        Assertions.assertFalse(RaceToTheRaft.isBoardStringWellFormed(variableNineWidth));
        String variableEighteenWidth =
                """
                ffffffffffffffffff
                ffffffffffyfffffff
                ffffffffffffffffff
                fffffffrgfbffffprp
                fffffffgfffyfffbow
                ffffffffpypyfffbyg
                ffffffbffygyfffygy
                fffffffgygYgrfffgbr
                ffffffbbyyyyfffbbg
                ffffffgggbgyypbyrg
                fffbBbbbbbgygyrgyb
                fffbbbpgyypyrpybpg
                ffffffgffggybpbrgp
                ffffffgGfpyrgbyprg
                ffffffgggrbbyrggbb
                """;
        Assertions.assertFalse(RaceToTheRaft.isBoardStringWellFormed(variableEighteenWidth));
        String wrongWidth =
                """
                ffffffffffff
                ffffffffffff
                ffffffffffff
                ffffffffffff
                ffffffffffff
                ffffffffffff
                ffffffffffff
                ffffffffffff
                ffffffffffff
                ffffffffffff
                ffffffffffff
                ffffffffffff
                """;
        Assertions.assertFalse(RaceToTheRaft.isBoardStringWellFormed(wrongWidth));
    }

    @Test
    public void testCorrectCharacters() {
        String wellFormed =
                """
                ffffffffffffffffff
                fffffffffbbbffffff
                fffffffffbbbfffffp
                fffrrffgbrrbyffpfp
                fffrRffffrpbffrpyb
                fffrrffryrpbfrbPyb
                fffygyfgyrpBypbyrg
                fffgyrpbgbbpgyrgyb
                fffpbyyrrypyrpybpg
                fffffygryygybpbpby
                ffffyyyyyyYrgbyror
                fffyyyrybrbbyrggwg
                """;
        Assertions.assertTrue(RaceToTheRaft.isBoardStringWellFormed(wellFormed));
        String incorrectAlphabetical =
                // Row 8, column 14 has an "x"
                """
                fffffyffffffffffff
                fffffffffffffffbBb
                fffyyffffffffffbbb
                fffffffypggybpbrgp
                fffbybffgpyrgbyprg
                fffyypbffrbbyrggbb
                gffbyyYpbbrpygybgy
                ggfyrgbyrgrbgxrpbg
                ggggggPgygyppbyyrp
                fppbfgrrbybygbrgyp
                fppffggGpgyrpgbrow
                ffffpgyyypbbgrpbww
                """;
        Assertions.assertFalse(RaceToTheRaft.isBoardStringWellFormed(incorrectAlphabetical));
        String incorrectNumberAndSymbol =
                // Row 8, column 10 contains a "$"
                // Row 11, column 17 contains a "0"
                """
                fffffyffffffffffff
                ffffYyfffffffffbbf
                fffyyyfffffffffbff
                fffgbrggrgfffyfffb
                fffpgbypbfffffgygb
                fffgrprybbpfffgppb
                gffypbyrggyybgbbbb
                gGfgyrgyb$rbyrbbbr
                ggggggbpgyprgyBpgy
                fppppprgpggybrrgyp
                fPppggprgbrgpygr0w
                fppyrggbbbprgpbbww
                """;
        Assertions.assertFalse(RaceToTheRaft.isBoardStringWellFormed(incorrectNumberAndSymbol));
    }

    @Test
    public void testNumberOfLines() {
        String wellFormed =
                """
                ffffffffffffffffff
                ffffffffffffffffff
                ffffffffffffffffff
                fffffffffypbbpbrpy
                fyyybfffybryyprgyg
                yyyyyyfgbbygrbgbyg
                gffbbbgbyyybpgygyp
                gGfgggyYyggrbrrrow
                ggggyyrbrygpybpbww
                fffbByrpbbyrprgbbp
                bbbbggffbbpybgpryg
                bbbbbyfggrggrbgyby
                fffbyfffyfffffffff
                fffgrfffpfffffffff
                fffpgbffffffffffff
                """;
        Assertions.assertTrue(RaceToTheRaft.isBoardStringWellFormed(wellFormed));
        String seventeenLines =
                """
                fffffffppfffrrffff
                fffffffppfffrrffff
                fffffffppfffrrffff
                fffffffypbgbbrbgyb
                ffffffbypyprbrbgby
                fffbfffypPrbbrbrpg
                ffyrrgygrgpbpRpyyb
                fYypbybybryrybgpry
                fffgrybbrbyrprgprp
                fffybgrypbpybgpbow
                fffbpbyggrggrbgbyg
                gffgyGypypryybppyg
                fffrrgpbbgbprygbrg
                gfffgggyrygbygyprb
                """;
        Assertions.assertFalse(RaceToTheRaft.isBoardStringWellFormed(seventeenLines));
        String fourteenLines =
                """
                ffffffffffffffyfpp
                ffffffffffffffyfPp
                ffffffgfffffyffyrp
                ffffffggfgfgypbgrb
                fffrrffffgppbYgbrp
                fffrrffygggGprgryg
                fffrrffrRgbrybyppy
                fffrbrrbybpybgpryg
                fffpbbgrrrggrbgyby
                ffffffgyppryybpgyp
                fffbbbbryygbygybww
                """;
        Assertions.assertFalse(RaceToTheRaft.isBoardStringWellFormed(fourteenLines));
    }

}
