package comp1110.ass2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.concurrent.TimeUnit;

@Timeout(value = 500, unit = TimeUnit.MILLISECONDS)
public class IsPlacementValidTest {

    private void test(String[] gameState, String placement, boolean expected) {
        boolean out = RaceToTheRaft.isPlacementValid(gameState, placement);
        Assertions.assertEquals(expected, out,
                "Expected: " + expected + " for placement " + placement + ", but got: " + out + " for board \n" + gameState[0]);
    }


    @Test
    public void testValidCard() {
        for (int i = 0; i < validCard.length; i++) {
            test(cardBoard[i], validCard[i], true);
        }
        test(cardBoard[0], "Aa0000N", false);
    }

    @Test
    public void testInvalidCard() {
        for (int i = 0; i < invalidCard.length; i++) {
            test(cardBoard[i], invalidCard[i], false);
        }
        test(cardBoard[0], "Aa1010N", true);

    }

    @Test
    public void testValidFire() {
        for (int i = 0; i < validFire.length; i++) {
            test(fireBoard[i], validFire[i], true);
        }
        test(fireBoard[0], "b1010TE", false);
    }


    @Test
    public void testInvalidFire() {
        for (int i = 0; i < invalidFire.length; i++) {
            test(fireBoard[i], invalidFire[i], false);
        }
        test(fireBoard[0], validFire[0], true);
    }


    private final static String[][] cardBoard = new String[][]{
            new String[]{
                    """
                fffffffffrrfffffff
                fffffffffrRfffffff
                fffffffffrrfffffff
                fffgffpbgypbbpbrpy
                fffgGfprybryyprgyg
                fffgggygbyggrbgbyg
                ffffffbpgprbpgypry
                ffffffrybpyrbrrggb
                ffffffrgyygpybpbyp
                ffffffprgbyrprgbbp
                ffffffybybpybgpryg
                ffffffbrrrggrbgyby
                fffffygyppryybpgyp
                ffffYyygggbprygrow
                fffyyybryygbygybww
                """,
                    "AabcdefghijklmnopqrstuvwxyBabcdefghijklmnopqrstuvwxyCabcdefghijklmnopqrstuvwxyDabcdefghijklmnopqrstuvwxy", "ABCD", "", "abcdefghijklmnopqrstuvwxyzABCDE"},
            new String[]{
                    """
                fffffffffrrfffffff
                fffffffffrRfffffff
                fffffffffrrfffffff
                fffgffffffrbbpbrpy
                fffgGfpfybryyprgyg
                fffgggygybrgrbgbyg
                ffffffbpgprbpgypry
                ffffffrybpyrbrrggb
                ffffffrgyygpybpbyp
                ffffffprgbyrprgbbp
                ffffffybybpybgpryg
                ffffffbrrrggrbgyby
                fffffygyppryybpgyp
                ffffYyygggbprygrow
                fffyyybryygbygybww
                """,
                    "AabcdfghijklmnopqrstuwxyBabdefghijklmnopqrstuvwxyCabcdefghijklmnopqrstuvxyDabcfghijklmnopqrstuvwxy", "AeBcCwDde", "", "abcdefghijklmnopqrstuvwxyzBCDE"},
            new String[]{
                    """
fffffffffffffffrrf
fffffffffbBbfffrRf
fffffffffbbbfffrrf
ffffffpbggybgbrggy
ffffffprygygrpyyrb
ffffffygbyprbpbbpy
ffffppbpgbgyrrgygr
ffffPprybbbgpbybyb
ffffpprgygrybgpgpg
ffffffbbrbyrprgbbp
ffffffrypbpybgpryg
ffffffyggrggrbgyby
ffffffypypryybpprp
fffbBbpbbgbprygbow
fffbbbgyrygbygybyg
""",
                    "AabcdfghijklmnopqrstuvxyBbcdefghijklmnpqrstuvwxyCabcdefghijklmnopqrstuvwxDabcdefghijkmnopqrstuvwxy", "AewBaoCyDl", "", "abcdefghijklmnopqrstuvwxyzABCDE"},
            new String[]{
                    """
fffffffffffffffrrf
fffffffffbBbfffrRf
fffffffffbbbfffrrf
ffffffpbggybgbrggy
ffffffprygygrpyyrb
ffffffygbyprbpbbpy
ffffppbpgbgyrrgygr
ffffPppyybbgpbybyb
ffffppppggrybgpgpg
ffffffgggbyrprgbbp
ffffffrfpbpybgpryg
fffffffffrggrbgyby
ffffffypfpryybpprp
fffbBbpbbgbprygbow
fffbbbgyrygbygybyg
""",
                    "AabcdfghijklmnopqrstuvxyBbcdefghijklmnpqrstuvwxyCabcdefghijklmnopqrstuvwxDabcdefghijkmnopqrstuvwxy", "AewBoCyDl", "", "abcdefghijklmnopqrstuvwxzABCDE"},


    };

    private final static String[] validCard = new String[]{
            "Av0308N",
            "Ae0708E",
            "Ba0706W",
            "Dl0808E",
    };

    private final static String[] invalidCard = new String[]{
            "Av0208N",
            "Ae0304E",
            "Ba0705W",
            "Dl0807E",

    };

    private final static String[][] fireBoard = new String[][]{
            new String[]{
                    """
ffffffffffffffffff
fffffffffbBbffffff
fffffffffbbbffffff
gffprgbrpbprbrgyrp
gGfybprpygbrppbgrb
gggrgybgbpygbyrpby
rrfyppgbygbybygprp
rRfgrbyygpygprgbow
rrfbygrbrgbrybybyg
brrrrybbrbyrprgbbp
grgrbgrypbpybgpryg
pggypbYggrggrbgyby
fffffyypyfffffffff
ffffyypbbfffffffff
fffyyygyrfffffffff
""",
                    "AabdefghiknopqruvwxyBabcdefghijklmnopqrstuvwxyCabcdefghijklmnopqrstuvwxyDabcdefghijklmnopqrstuvwxy","AjlmsBCD","Y1106","abcdefghijlmnopqrstuvwxyzABCDE"},
            new String[]{
                    """
ffffffffffffffffff
fffffffffbBbffffff
fffffffffbbbffffff
gffprgbrpbprbrgyrp
gGfybprpybbbppbgrb
gggrgybgbrrbbyrpby
rrfyppgbyrrbbygprp
rrfgrbyygpygprgbow
rrfbygrbrgbrybybyg
frrrRybbrbyrprgbbp
frgrbgrypbpybgpryg
fffypbYggrggrbgyby
fffffyypyfffffffff
ffffyypbbfffffffff
fffyyygyrfffffffff
""",
                    "AabdefghiknopqruvwxyBabcdefghijklmnopqrstuvwxyCabcdefghijklmnopqrstuvwxyDabcdefghijklmnopqrstuvwxy","AlsBCD","R0904Y1106","abcdefghijlmopqrstuvwxyzABCDE"},
            new String[]{
                    """
ffffffffffffffffff
fffffffffbbbffffff
fffffffffbbbffffff
gffprgbrpbprbrgyrp
ggfybprpybbbppbgrb
ggGrgybgbrrbbyrpby
rrfyppgbyrrbBygprp
rrffffyygpygbbbbow
rrffyfrbrgbrbgbbyg
frrrRybbrbyrygybbp
frgrbgrypbpybgpryg
fffypbYggrggrbgyby
fffffyypyfffffffff
ffffyypbbfffffffff
fffyyygyrfffffffff
""",
                    "AbdeghiknpquvyBabcdefghijklmnopqrstuvwxyCabcdefghijklmnopqrstuvwxyDabcdefghijklmnopqrstuvwxy","AafrwxBCD","","abcdefghijlmpqrstuvwxyzABCDE"},
            new String[]{
                    """
ffffffffffffffffff
fffffffffbbbffffff
fffffffffbbbffffff
gffprgbrpbprbrgyrp
ggfybprpybbbppbgrb
ggGrgybgbrrbbyrpby
rrfyppgbyrrbbygprp
rrffffyygpygbbbBow
rrffyfrbrgbrbgbbyg
frrrRybbbbyrygybfp
frgrbgrbggpybgprff
fffypbYyybggrbgfff
fffffyypyfffffffff
ffffyypbbfffffffff
fffyyygyrfffffffff
""",
                    "AbdeghiknpquvyBabcdefghijklmnopqrstuvwxyCabcdefghijklmnopqrstuvwxyDabcdefghijklmnopqrstuvwxy","ArwxBCD","B0715","abcdefghijlmpqrstuvwxyzABCE"},
            new String[]{
                    """
ffffffffffffffffff
fffffffffbbbffffff
fffffffffbbbffffff
gffprgbrpbprbrffff
ggfybprpybbbppffrb
ggGrgybgbrrbbyrpby
rrfyppgbyrrbbygprp
rrffffyygpygbbbBow
rrffyfrrybbrbgbbyg
frrrRybrybyrygybfp
frgrbgrbybpybgprff
fffypbYyybggrbgfff
fffffyypyfffffffff
ffffyypbbfffffffff
fffyyygyrfffffffff
""",
                    "AbdeghiknpquvyBabcdefghijklmnopqrstuvwxyCabcdefghijklmnopqrstuvwxyDabcdefghijklmnopqrstuvwxy","AwxBCD","B0715","abcdefghijlmpqrstuvwxyzABC"},


    };

    private final static String[] validFire = new String[]{
            "k0900FW",
            "n0703FW",
            "o0915FS",
            "D0314FN",
            "E0109FW",

    };

    private final static String[] invalidFire = new String[]{
            "k1000FW", // overlaps fire
            "n0903FW", // overlaps cat
            "o0916FS", // off-board
            "D0317FE", // overlaps raft
            "E0111FW", // not adjacent to fire
    };
}

