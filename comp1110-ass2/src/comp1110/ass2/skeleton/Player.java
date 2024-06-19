package comp1110.ass2.skeleton;


import java.util.Random;
import java.util.Set;

/**
 * @author JiaLin Li, Yunmeng Zhang, Tengyi Zhang
 */
public class Player {

    //    public final String name;// Player's name
    private int cardOnHandAmount;// The number of pathway cards on hand.
    private PathwayCards[] cardsOnHand; // The list of pathway cards on hand.

    private String cardsOnHandString; // The String of pathway cards on hand, eg "AeghrBCjmD".

    /**
     * Constructor
     *
     * @param cardOnHandAmount
     */
    public Player(int cardOnHandAmount) {
        this.cardOnHandAmount = cardOnHandAmount;
    }

    public Player(String cardsOnHandString) {
        this.cardsOnHandString = cardsOnHandString;
    }

    /**
     * @param gameState The state of the current game board.
     * @return Has the player win the game?
     * @author Jialin Li
     */
    public boolean isWin(String[] gameState) {
        return false;
    }

    /**
     * Discard a card form hand.
     *
     * @param cardPlacement the action string of placing a card from hand to board.
     * @author Yunmeng Zhang
     */
    public void discard(String cardPlacement) {
        String deck = String.valueOf(cardPlacement.charAt(0));
        String card = String.valueOf(cardPlacement.charAt(1));
//        int deckStart = cardsOnHandString.indexOf(deck)+1;
//        int deckEnd = cardsOnHandString.
    }

    public String getCardsOnHandString() {
        return this.cardsOnHandString;
    }


    /**
     * @param numbers   The int[] that store the number got randomed before.
     * @param charArray charArray_A/charArray_B/charArray_C/charArray_D
     * @param gameState The original gameState[].
     * @param deck      The name of the deck.
     * @return The updated String of the cards in hand.
     * The player draw the cards from the decks,
     * and then update the state of cards in player's hand.
     * @author Jialin Li
     */
    public static String updateCardInHnad(int[] numbers, char[] charArray, String[] gameState, char deck) {
        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < numbers.length; j++) {
            sb.append(charArray[numbers[j]]);
            charArray[numbers[j]] = ' ';
        }
        String s = sb.toString();
        //"ABC xxxxx D"
        return gameState[2].replaceFirst(deck + "", deck + "" + s);
    }

    /**
     * @param numbersSet A initialized hashset.
     * @param num        The number of cards that player wants from drawRequest.
     * @param charArray  charArray_A/charArray_B/charArray_C/charArray_D
     * @return int[] that stored all the cards that player got.
     * 1. Generate several number of cards.
     * 2. Judge if there are same numbers.
     * 3. Transform the Hashset into int[].
     * 4. Use a method from Player class.
     * @author Jialin Li
     */
    public static int[] generateRandomCardnum(Set<Integer> numbersSet, int num, char[] charArray) {
        Random random = new Random();
        // Keep generating random numbers until we have 'num' distinct numbers
        while (numbersSet.size() < num) {
            int num_of_card = random.nextInt(1, charArray.length - 1);
            //Check if there are same numbers.
            if (!numbersSet.contains(num_of_card)) {
                numbersSet.add(num_of_card);
            }
        }
        // need to convert the Set to an array for further processing
        int[] numbers = numbersSet.stream().mapToInt(Integer::intValue).toArray();
        return numbers;
    }


    /**
     * Checks if a specified card from a specific deck can be discarded based on its presence in the player's hand.
     * The hand is represented as a string with cards grouped by deck and separated by deck identifiers.
     *
     * @param deckAndCard A string where the first character represents the deck and the second character represents the card to be checked.
     * @return true if the card is present in the specified deck section of the hand and can be discarded; false otherwise.
     * @author Yunmeng Zhang
     */
    public boolean cardCanBeDiscarded(String deckAndCard) {
        // Extract deck identifier and card value from the input string.
        String deck = String.valueOf(deckAndCard.charAt(0));
        String card = String.valueOf(deckAndCard.charAt(1));
        String cardsInDeck = ""; // To hold the substring of cards in the specified deck.

        // Find indexes of each deck in the hand string.
        int indexA = cardsOnHandString.indexOf("A");
        int indexB = cardsOnHandString.indexOf("B");
        int indexC = cardsOnHandString.indexOf("C");
        int indexD = cardsOnHandString.indexOf("D");

        // Determine the deck based on the deck identifier and extract the cards belonging to that deck.
        if (deck.equals("A")) {
            cardsInDeck = cardsOnHandString.substring(indexA + 1, indexB);
            // Check if the card is in the extracted substring or if the substring is empty.
            if (!cardsInDeck.contains(card) || cardsInDeck.equals("")) {
                return false;
            }
        } else if (deck.equals("B")) {
            cardsInDeck = cardsOnHandString.substring(indexB + 1, indexC);
            if (!cardsInDeck.contains(card) || cardsInDeck.isEmpty()) {
                return false;
            }
        } else if (deck.equals("C")) {
            cardsInDeck = cardsOnHandString.substring(indexC + 1, indexD);
            if (!cardsInDeck.contains(card)) {
                return false;
            }
        } else if (deck.equals("D")) {
            cardsInDeck = cardsOnHandString.substring(indexD + 1);
            if (!cardsInDeck.contains(card)) {
                return false;
            }
        }
        // If the card is found in the specified deck's section of the hand, return true.
        return true;
    }

    /**
     * Removes a card from the player's hand based on the placement string provided.
     *
     * @param placementString The string representing the placement of the card in the player's hand.
     * @author Tengyi Zhang
     */
    public void removeFromHand(String placementString) {
        // Get the index of the card in the hand and the value of the card.
        char deckIndex = placementString.charAt(0); // Get the card index
        char cardValue = placementString.charAt(1); // Get the card value
        // Locate the position of the card in the hand string.
        int dIndex = this.cardsOnHandString.indexOf(deckIndex);
        int targetIndex = this.cardsOnHandString.indexOf(cardValue, dIndex);

        // If the card is found in the hand, remove it.
        if (targetIndex != -1) {
            // Remove the card from the hand string.
            StringBuilder handBuilder = new StringBuilder(cardsOnHandString);
            handBuilder.deleteCharAt(targetIndex);
            this.cardsOnHandString = handBuilder.toString();
        }
    }

    /**
     * Removes a card representing a cat movement from the player's hand based on the Cat movement string provided.
     *
     * @param movementString The string representing the movement of the cat card in the player's hand.
     * @author Tengyi Zhang
     */
    public void removeCatMovementFromHand(String movementString) {
//        System.out.println("The previous string is: " + this.cardsOnHandString);
        char deckIndex = movementString.charAt(9); //Get the card deck
        char cardValue = movementString.charAt(10); // Get the card's ID
        // Locate the position of the card in the hand string.
        int dIndex = this.cardsOnHandString.indexOf(deckIndex);
        int targetIndex = this.cardsOnHandString.indexOf(cardValue, dIndex);

        // If the card is found in the hand, remove it.
        if (targetIndex != -1) {
            // Remove the card from the hand string.
            StringBuilder handBuilder = new StringBuilder(cardsOnHandString);
            handBuilder.deleteCharAt(targetIndex);
            this.cardsOnHandString = handBuilder.toString();
        }
//        System.out.println("The changed string is: " + this.cardsOnHandString);
    }

    /**
     * Removes a card representing a cat movement from the player's hand based on the Cat movement string provided.
     *
     * @param movementString The string representing the movement of the cat card in the player's hand.
     * @author Tengyi Zhang
     */
    public void discardExhaustedCat(String movementString) {
//        System.out.println("The previous exhausted String is: " + this.cardsOnHandString);
        char deckIndex = movementString.charAt(11); //Get the card deck
        char cardValue = movementString.charAt(12); // Get the card's ID
        // Locate the position of the card in the hand string.
        int dIndex = this.cardsOnHandString.indexOf(deckIndex);
        int targetIndex = this.cardsOnHandString.indexOf(cardValue, dIndex);

        // If the card is found in the hand, remove it.
        if (targetIndex != -1) {
            // Remove the card from the hand string.
            StringBuilder handBuilder = new StringBuilder(cardsOnHandString);
            handBuilder.deleteCharAt(targetIndex);
            this.cardsOnHandString = handBuilder.toString();
        }
    }
}
