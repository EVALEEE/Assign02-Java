package comp1110.ass2.skeleton;

import comp1110.ass2.skeleton.DeckType;

/**
 * @author Jialin Li, Yunmeng Zhang
 * **/
public class Deck {

    private DeckType type;
    private String[] cardsInDeck;

    public DeckType getType() {
        return type;
    }

    public Deck(DeckType type, String[] cardsInDeck) {
        this.type = type;
        this.cardsInDeck = cardsInDeck;
    }

    public Deck(char ID) {
        this.type = DeckType.fromChar(ID);
    }

    public int cardsCount() {
        return this.cardsInDeck.length;
    }


    /**
     * Retrieves the card content from the deck based on the provided card ID.
     *
     * @param cardId The ID of the card to retrieve.
     * @return The content of the card corresponding to the provided ID.
     *         Returns null if the index is out of bounds.
     * @author Yunmeng Zhang
     */
    public String getCardById(String cardId) {
        int index = cardId.charAt(0) - 'a'; // convert the char into index
        if (index >= 0 && index < cardsInDeck.length) {
            return cardsInDeck[index].substring(1);
        } else {
            return null; // if no such index, return null
        }
    }


    /**
     * @param charArray A certain deck's charArray.
     * @return The string that indicate the state of the Deck after changing.
     * Update the deck's state after player taking her cards from decks.
     * @author Jialin Li
     */
    public static String updateDeck(char[] charArray) {
        int newSize = 0;
        for (char c : charArray) {
            if (c != ' ') {
                newSize++;
            }
        }
        char[] newArray = new char[newSize];
        int index = 0;
        for (char c : charArray) {
            if (c != ' ') {
                newArray[index++] = c;
            }
        }
        return new String(newArray);
    }


    /**
     * @param decks The original Stirng.
     * @param deck The name of the deck.
     * @param nextDeck The next deck's name.
     * @return A char[] of certain deck.
     * Separate different parts of the certain decks.(The end part exclude)
     * And turn into a char[].
     * @author Jialin Li
     */
    public static char[] getDeckStringA(String decks, char deck, char nextDeck) {
        String substring = decks.substring(decks.indexOf(deck), decks.indexOf(nextDeck));
        return substring.toCharArray();
    }

    /**
     * @param decks The original Stirng.
     * @param deck The name of the deck.
     * @return A char[] of certain deck.
     * Same as getDeckStringA(), but use for the end part of the String.
     * @author Jialin Li
     */
    public static char[] getDeckStringB(String decks, char deck) {
        String substring = decks.substring(decks.indexOf(deck));
        return substring.toCharArray();
    }

    /**
     * @param string The String that we need to check.
     * @param charArray The original charArray.
     * @return The updated string.
     * If the deck is empty or don't have enough cards for player.
     * It may not be updated by early process, and will be a special case.
     * So we need to deal with it.
     * @author Jialin Li
     */
    public static String isDeckValid(String string, char[] charArray) {
        if (string.equals(" ")) {
            string = new String(charArray);
        }
        return string;
    }
}
