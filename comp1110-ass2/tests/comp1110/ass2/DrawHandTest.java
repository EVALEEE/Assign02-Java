package comp1110.ass2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

@Timeout(value = 500, unit = TimeUnit.MILLISECONDS)
public class DrawHandTest {

    private void testCardsRemove(String oldDecks, String newDecks, int[] numCardsDrawn) {
        for (char c = 'A'; c < 'D'; c++) {
            String oldDeck = oldDecks.substring(oldDecks.indexOf(c), oldDecks.indexOf(c + 1));
            String newDeck = newDecks.substring(newDecks.indexOf(c), newDecks.indexOf(c + 1));
            Assertions.assertEquals(numCardsDrawn[c - 'A'],oldDeck.length() - newDeck.length(), "Wrong number of cards removed from Deck "+c);
        }
        String oldDeck = oldDecks.substring(oldDecks.indexOf('D'));
        String newDeck = newDecks.substring(newDecks.indexOf('D'));
        Assertions.assertEquals(numCardsDrawn['D' - 'A'], oldDeck.length() - newDeck.length(), "Wrong number of cards removed from Deck D");
    }

    private void testCardsInHand(String[] oldState, String newHand, String drawRequest) {
        String deckString = oldState[1];
        String[] oldDecks = new String[]{
                deckString.substring(0, deckString.indexOf('B')),
                deckString.substring(deckString.indexOf('B'), deckString.indexOf('C')),
                deckString.substring(deckString.indexOf('C'), deckString.indexOf('D')),
                deckString.substring(deckString.indexOf('D'))
        };
        // Hand string is six (one character for each card) plus one for each deck that is present (test allows mentioning a deck without cards, but does not require it)
        Assertions.assertEquals(6+(newHand.contains("A")?1:0)+(newHand.contains("B")?1:0)+(newHand.contains("C")?1:0)+(newHand.contains("D")?1:0), newHand.length(), "Wrong length of hand string");
        newHand=newHand+'E';
        char c='A';
        while(c!='E') {
            if(newHand.contains(((Character)c).toString())) {
                int start = newHand.indexOf(c);
                char nextChar = c;
                nextChar++;
                while(!newHand.contains(((Character)nextChar).toString())) {
                    nextChar++;
                }
                int end = newHand.indexOf(nextChar);
                String hand = newHand.substring(start, end);
                Assertions.assertEquals(c, hand.charAt(0), "Invalid Deck character in hand string");
                for (int i = 1; i < hand.length(); i++) {
                    String card = String.valueOf(hand.charAt(i));
                    Assertions.assertTrue(oldDecks[c - 'A'].contains(card), "New hand contains card that was not in Deck "+c);
                }
                c=nextChar;
            } else {
                c++;
            }
        }
    }
    
    private static String[] callDrawHand(String[] gameState, String drawRequest) {
        String[] gameStateCopy = Arrays.copyOf(gameState, gameState.length);
        return RaceToTheRaft.drawHand(gameStateCopy, drawRequest);
    }

    @Test
    public void testRemovedFromDeck() {
        String[] gameState = new String[]{
                "fffffffffffyffffff\nffffffffffYyffffff\nfffffffffyyyffffff\nffffffyrgggbyrybgp\nffffffggyyyrgbrbgy\nffffffbgpggrbpygpp\nfffbgybpbypbprgyry\nfffpyrgygbrgypbbgb\nfffgppbrrgbybgryyb\nfffgffprgbbggrybbr\nfffgGfybygrpybgryp\nfffgggbrrpgrbpbygg\nffffffgypgpbyprwww\nfffbBbyggbygrygwow\nfffbbbbrygrybpywww\n",
                "AabcdefghijklmnopqrstuvwxyBabcdefghijklmnopqrstuvwxyCabcdefghijklmnopqrstuvwxyDabcdefghijklmnopqrstuvwxy",
                "ABCD",
                "",
                "abcdefghijklmnopqrstuvwxyzABCDE"
        };
        String drawRequest = "A1C2D3";
        String[] nextState = callDrawHand(gameState, drawRequest);
        testCardsRemove(gameState[1], nextState[1], new int[]{1, 0, 2, 3});

        gameState = new String[]{
                "fffffffffffffffrrf\nfffffffffbBbfffrRf\nfffffffffbbbfffrrf\nffffffffrgypgybrbg\nfffffffffpbrrybrry\nfffffffypygppybrgb\nffffpprpbbpyrggbpg\nffffPpbygbrypbyryb\nffffppgrrgybgybrgy\nffffffgpbyypbrgprg\nfffffffyrbpgrbpyby\nfffffffffgbypgybrr\nfffffffffgpppybprp\nfffbbbfffrgggrgbow\nfffbbbffbbbbbBpbyg\n",
                "AabcdefghijklmnopqrstuvwxyBabefghijklmnopqstuvwxyCabcefghijklmnpqstuvwxyDabcdefghijklmnopqrstuvwxy",
                "ABCD",
                "",
                "bcdefghijklmnpqrstuvwxyzACDE"
        };
        drawRequest = "A2B1C1D2";
        nextState = callDrawHand(gameState, drawRequest);
        testCardsRemove(gameState[1], nextState[1], new int[]{2, 1, 1, 2});

        gameState = new String[]{
                "fffffffffffffppfff\nffffffffffffffffff\nffffffffgffffpffff\nffffffffffffgfffff\nfffffffrffffffpbfb\nfffffffgbfffffppfy\nffffffbpbffffffgfr\nfffbbbrybgfgpfygpb\nfffbbbbBggygggygpg\nffffffpggbbgggyypy\nffffffpppgbbggyppg\nffffffbrrgyppggppp\nffffffgypybyppgGyP\nffffffygggyrpgbrow\nffffffbrypbbgrpbww\n",
                "AabcdefghijklmnopqrstuvwxyBabcdfgijklmnopqrsuvwxyCacdefhijklmnopqrstuvwyDbcdefghjklnoprsuwxy",
                "ABCD",
                "",
                "bcdefghjklnopqruvwyzCDE"
        };
        drawRequest = "A2C1D3";
        nextState = callDrawHand(gameState, drawRequest);
        testCardsRemove(gameState[1], nextState[1], new int[]{2, 0, 1, 3});
    }

    @Test
    public void testAddedToHand() {
        String[] gameState = new String[]{
                "ffffffrrfrrffppfff\nffffffrRfrRffPpfff\nffffffrrfrrffppfff\nfffffyypyrrbygpybg\nffffYypbbybypbrgpb\nfffyyygyrgrpgrbpyy\nfffygybgygyybgpgpb\nfffgyrpbgprbyrgbyr\nfffpbyyrpyprgybpgy\nfffgbrggrpbybrrbgb\nfffpgbypbrorpygybp\nfffgrprybgwggpbyry\n",
                "AabcdefghijklmnopqrstuvwxyBabcdefghijklmnopqrstuvwxyCabcdefghijklmnopqrstuvwxyDabcdefghijklmnopqrstuvwxy",
                "ABCD",
                "",
                "abcdefghijklmnopqrstuvwxyzABCDE"
        };
        String drawRequest = "A1B3C1D1";
        String[] nextState = callDrawHand(gameState, drawRequest);
        testCardsInHand(gameState, nextState[2], drawRequest);

         gameState = new String[]{
                 "ffybrgfffffffffgff\nfYyrbpbbbffffffggf\nyyypgybbbffffffggg\nfffbybggbpffyffffg\nfffbrgygbgfffffffg\nfffybpygbyffyfffyg\nfffbpgrpbbbfgfffrg\nfffgypgrBgrpybgggg\nfffrrbyggpgrpggygg\nfppbygrpyrrrppgGyp\nfPpgrybrppppppprow\nfpppgbyygbbbbpybww\n",
                 "AabcfghijklmnopqrstuvwxyBabcdefghijklmnopqrstuvwxyCabcdefhijklmnopqrstuwxyDabcdefghijklmopqrstuvxy",
                 "ABCD",
                 "",
                 "abdefghijlmnopqrstvxyzABCDE"
         };
         drawRequest = "A3B1C1D1";
         nextState = callDrawHand(gameState, drawRequest);
         testCardsInHand(gameState, nextState[2], drawRequest);

         gameState = new String[]{
                 "ffffffffffffffffpp\nfffffffffbBbffffPp\nfffffffffbbbffffpp\nfffrrfyfygrgbfbgyp\nfffrrffffrypbfffgg\nfffrrfrbffpgyfpggp\nfffrrRrrygyybfpppp\nfffyrfffgprbyrgggg\nffffffyrpYprgybpgy\nfffffygppygybrrpby\nffffffyyyyrgpygror\nffffffrrryprgpbgwg\n",
                 "AabcdefghijklnopqrstuvwxyBabcdefghijklmopqrtuvwxyCabcdefghijkmnopqrstvwxyDabcdefghijklmnopqrstuvwx",
                 "ABCD",
                 "",
                 "abefghijkmnopqrstuvwxyzABDE"
         };
         drawRequest = "A1B2C1D2";
         nextState = callDrawHand(gameState, drawRequest);
         testCardsInHand(gameState, nextState[2], drawRequest);
    }

    @Test
    public void testImpossibleDraw() {
        /*
        Test with a possible draw. Otherwise, it'd be possible to pass this
        test by not changing the method at all.

        Note there are no cards in the C deck, but because there is no request
        to draw from C, the draw should still go ahead.
         */
        String[] gameState = new String[]{
                "fffffffffffffffrrf\nfffffffffffffgfrff\nffffffffffffffffff\nfffffffffffpbyrfff\nfffffffffrffffyrgp\nfffffffffbpgfgyRgf\nfffffffgffrpgggfgf\nfffffffffyYggyrfbf\nffffffyyyyggpbyyrf\nffffffggbpppGypfff\nfffbbbbbbByrrowfff\nfffbbbbyypbbbwwfff\n",
                "AabcefghijklmnoqrstuvwyBbcdfghijlnopqrstuvwxyCDabcdghijklnopqrstuvwxy",
                "ABCD",
                "",
                "abcdeghilmprstuvwxyzBCE"
        };
        String drawRequest = "A1B3D2";
        String[] nextState = callDrawHand(gameState, drawRequest);
        testCardsRemove(gameState[1], nextState[1], new int[]{1, 3, 0, 2});
        testCardsInHand(gameState, nextState[2], drawRequest);

        // No cards in a certain deck, draw request asks for one card from that deck
        gameState = new String[]{
                "ffffffffyfffgfffpp\nffffffffffffggffPp\nffffffffyfffgggfpp\nffffffgffyyyybgpgr\nfffbBbygygyrybgppb\nfffbbbbrygybYbgpyb\nffffffgfbbbGgggbbr\nffffffffffrpygpryp\nffffffffypgrygpygg\nffffffffbgpbpbyfff\nfffffffffbygrorfff\nfffffffffffygwgfff\n",
                "ABabcdefghijklmnopqrstuvwxyCacfgkmnoptuvwDabcdefghijklmnopqrstuvwxy",
                "ABCD",
                "",
                "abcdfghjklmnopqrstuvwxyzBCE"
        };
        drawRequest = "A1C2D4";
        nextState = callDrawHand(gameState, drawRequest);
        Assertions.assertArrayEquals(gameState, nextState); // nothing should change because the draw is invalid

        // Three cards from deck A in the draw request, only two cards in the deck
        gameState = new String[]{
                "ffffffffffffffffff\nfffffffffbbbffffff\nfffffffffbbbffffff\nfffffffbrybbyffprp\nfffffffgfbbrrgfbow\nfffffffyffbBrggbyg\nfffffffffrygfffygy\nfffffffffryGfffgbr\nfffffffffgggfffbbg\nfffffffffgygpppbbp\nfffffffffgyggggyYg\nfffffffffffyyyyrby\nffffffffffryybpfyg\nffffffffffbfryffrg\nfffffffffffffffprb\n",
                "AmwBabcdefghiklmnopqrstuvwyCabcdefghijkmnopqrstuvxyDabcdefhjklmnopqruvwxy",
                "ABCD",
                "",
                "adefghiklmnoqrstuwxyABD"
        };
        drawRequest = "A3B1C1D1";
        nextState = callDrawHand(gameState, drawRequest);
        Assertions.assertArrayEquals(gameState, nextState);
    }

    @Test
    public void testContinuousGame() {
        // Turn 1
        String[] gameState = new String[]{
                "ffffffffyfffffffff\nfffffffYyfffbBbfff\nffffffyyyfffbbbfff\nffffffggrgrgbybgyp\nffffffypbrypbrgygg\nffffffrybbpgybpbry\nfffgffgpbrygypbgyp\nfffgGfbyrbbpgyrrow\nfffgggpgyypyrpybww\nffffppbgbggybpbfff\nffffPpybppyrgbyfff\nffffppyryrbbyrgfff\n",
                "AabcdefghijklmnopqrstuvwxyBabcdefghijklmnopqrstuvwxyCabcdefghijklmnopqrstuvwxyDabcdefghijklmnopqrstuvwxy",
                "ABCD",
                "",
                "abcdefghijklmnopqrstuvwxyzABCDE"
        };
        String drawRequest = "A1B2C1D2";
        String[] nextState = callDrawHand(gameState, drawRequest);
        testCardsRemove(gameState[1], nextState[1], new int[]{1, 2, 1, 2});
        testCardsInHand(gameState, nextState[2], drawRequest);
        // Turn 2
        gameState = nextState;
        gameState[2] = "ABCD"; // remove cards from the hand
        drawRequest = "B1C1D4";
        nextState = callDrawHand(gameState, drawRequest);
        testCardsRemove(gameState[1], nextState[1], new int[]{0, 1, 1, 4});
        testCardsInHand(gameState, nextState[2], drawRequest);
        // Turn 3
        gameState = nextState;
        gameState[2] = "ABCD"; // remove cards from the hand
        drawRequest = "A1B2C1D2";
        nextState = callDrawHand(gameState, drawRequest);
        testCardsRemove(gameState[1], nextState[1], new int[]{1, 2, 1, 2});
        testCardsInHand(gameState, nextState[2], drawRequest);
    }

}
