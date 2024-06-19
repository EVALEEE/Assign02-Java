package comp1110.ass2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

@Timeout(value = 500, unit = TimeUnit.MILLISECONDS)
public class DrawFireTileTest {

    private boolean isLegitID(char id) {
        return (id >= 'a' && id <= 'z') || (id >= 'A' && id <= 'E');
    }


    private boolean comesFromBag(char id, String bag) {
        return bag.contains(String.valueOf(id));
    }

    private void testIsFireTile(String fireTile, String bag) {
        char id = fireTile.charAt(0);
        Assertions.assertTrue(isLegitID(id));
        Assertions.assertTrue(comesFromBag(id, bag));
    }

    private static String callDrawFireTile(String[] gameState) {
        String[] gameStateCopy = Arrays.copyOf(gameState, gameState.length);
        return RaceToTheRaft.drawFireTile(gameStateCopy);
    }
    @Test
    public void testFullBag() {
        final String bag = "abcdefghijklmnopqrstuvwxyzABCDE";

        String[] gameState = new String[]{
                "fffffffffffffffrrf\nfffffffffbBbfffrRf\nfffffffffbbbfffrrf\nffffffpbgggbyrybgp\nffffffpryyyrgbrbgy\nffffffygbggrbpygpp\nffffppbpgypbprgyry\nffffPprybbrgypbbgb\nffffpprgygbybgryyb\nffffffgpbbrpygybgy\nffffffbyrgrbgyrpbg\nffffffpgygyppbyyrp\nffffffbgbybygbrprp\nfffbBbybpgyrpgbbow\nfffbbbyrypbbgrpbyg\n",
                "AabcdefghijklmnopqrstuvwxyBabcdefghijklmnopqrstuvwxyCabcdefghijklmnopqrstuvwxyDabcdefghijklmnopqrstuvwxy",
                "ABCD",
                "",
                "abcdefghijklmnopqrstuvwxyzABCDE"
        };
        String fireTile = callDrawFireTile(gameState);
        testIsFireTile(fireTile, bag);

        gameState = new String[]{
                "ffffffrrfrrffppfff\nffffffrRfrRffPpfff\nffffffrrfrrffppfff\nfffffyggrrrbygpybg\nffffYyypbybypbrgpb\nfffyyyrybgrpgrbpyy\nfffgrybbryrybpgrpb\nfffybgryppbygypgrb\nfffbpbyggbgbrrbygg\nfffyprypypbybygrpy\nfffrygpbbrorgrybrp\nfffbpygyrgwgpgbyyg\n",
                "AabcdefghijklmnopqrstuvwxyBabcdefghijklmnopqrstuvwxyCabcdefghijklmnopqrstuvwxyDabcdefghijklmnopqrstuvwxy",
                "ABCD",
                "",
                "abcdefghijklmnopqrstuvwxyzABCDE"
        };
        fireTile = callDrawFireTile(gameState);
        testIsFireTile(fireTile, bag);

        gameState = new String[]{
                "fffffffffffffffffy\nfffffffffbBbffffYy\nfffffffffbbbfffyyy\nfffrrfpbrbgbygrgyb\nfffrRfpgyyprpbygby\nfffrrfgypprbgrprpg\nfffgbbrpbgpbprpyyb\nfffpggbygryrybgpry\nfffpypgrrggbgpybbg\nffffffprgbbggrygyp\nffffffybygrpybgrow\nffffffbrrpgrbpbbww\nfffffffffgpbfppfff\nfffffffffbygfPpfff\nfffffffffgryfppfff\n",
                "AabcdefghijklmnopqrstuvwxyBabcdefghijklmnopqrstuvwxyCabcdefghijklmnopqrstuvwxyDabcdefghijklmnopqrstuvwxy",
                "ABCD",
                "",
                "abcdefghijklmnopqrstuvwxyzABCDE"
        };
        fireTile = callDrawFireTile(gameState);
        testIsFireTile(fireTile, bag);
    }

    @Test
    public void testNotFullBag() {
        String[] gameState = new String[]{
                "ffffffffffffffffff\nfffffffffffffffbBb\nfffffffffffffffbbb\nfffffffbggybgbrggy\nfffffffffgygrpyyrb\nfffffffgbyprbpbbpy\nffffffygybgyrrgggr\nfffbbbbgbbBgprrgyb\nfffbbbbbbgrybrrRpg\nffffffbbryyprrfprg\nffffffrfpbpgrrfyby\nffffffffggbyrrfbrr\nfffffffpggrgbybprp\nffffffpppgypbrgbow\nffffffffppPgybpbyg\n",
                "AabcdefghijklmnpqstuvxyBabcdefghijklmnopqrstuvwxyCabcdefghijklmopqrstuvwxyDabcdefghjklmopqrstuvwxy",
                "ABCD",
                "",
                "abcdefgijklmnopqrstuvwxyzABC"
        };
        String fireTile = callDrawFireTile(gameState);
        testIsFireTile(fireTile, gameState[4]);

        gameState = new String[]{
                "fffffffff\nfffffffff\nffffffgfg\nfffffffff\nffbfffbGy\nfgbfbfggb\nfffByyggb\nfrgypyggb\nfbybgygyb\ngyyyyyprp\nprbygybow\nyprygybYg\nggybryyyb\nbrgpybyrp\nbprgpbyry\n",
                "AbcdefghijkmnoqrstuvwxyBabcdefgijkmnopqrstuvwxyCbcdefgijlmnoptuvwxDabcdefghijklmnopqrstuvwxy",
                "ABCD",
                "",
                "bdefgiklmoqrsuvwxyzABCDE"
        };
        fireTile = callDrawFireTile(gameState);
        testIsFireTile(fireTile, gameState[4]);

        // Empty fire tile bag
        gameState = new String[]{
                "ffffffffffffffffff\nffffffffffffffffff\nffffffffffffffffff\nfffffffrpffffffprp\nfffffffffffffffbow\nfffffffbfffffggbyG\nfffffffrfffgfffggg\nfffffffffffyfffgrr\nffffffppfffffffgrr\nfffffypyggyfffggyr\nffffYyyyyyyfgfpgfr\nfffyyybrrypggbbBfy\nfffffffffffyfrffff\nffffffffffrffffybf\nfffffffffffffffyrf\n",
                "AabcdefghijlmnpqrsvwxyBbcdefghjkmnopqrstuvxyCacdeglnopqrsuvwxyDabcdeghijklmnopqrtuvwxy",
                "ABCD",
                "",
                ""
        };
        fireTile = callDrawFireTile(gameState);
        Assertions.assertEquals(fireTile, "");
    }

}
