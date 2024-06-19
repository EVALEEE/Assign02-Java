package comp1110.ass2.skeleton;

import static comp1110.ass2.Utility.*;

import comp1110.ass2.Utility;

/**
 * @author JiaLin Li, Yunmeng Zhang, Tengyi Zhang
 */
public class PathwayCards {
    private Location location;//Current location of the card(If it is on the board...)
    private Deck deck;//Which deck the card belongs to?
    private char[][] layoutOfCard; //Store the states of each square of the 3*3 squares' card.
    private char id;

    public PathwayCards() {
    }

    /**
     * Constructs a PathwayCards object representing a pathway card from a specific deck and with a given card ID.
     *
     * @param deck   The deck from which the card belongs (A, B, C, or D).
     * @param cardID The ID of the pathway card within the specified deck.
     * @author Yunmeng Zhang
     */
    public PathwayCards(String deck, String cardID) {
        Deck deckA = new Deck(DeckType.CIRCLE, Utility.DECK_A);
        Deck deckB = new Deck(DeckType.CROSS, Utility.DECK_B);
        Deck deckC = new Deck(DeckType.SQUARE, Utility.DECK_C);
        Deck deckD = new Deck(DeckType.TRIANGLE, Utility.DECK_D);
        //get the card contents
        char[][] cardLayout = new char[3][3];
        if (deck.equals("A")) {
            String cardContent = deckA.getCardById(cardID);
            cardLayout = new PathwayCards(cardContent).getLayoutOfCard();
        }
        if (deck.equals("B")) {
            String cardContent = deckB.getCardById(cardID);
            cardLayout = new PathwayCards(cardContent).getLayoutOfCard();
        }
        if (deck.equals("C")) {
            String cardContent = deckC.getCardById(cardID);
            cardLayout = new PathwayCards(cardContent).getLayoutOfCard();
        }
        if (deck.equals("D")) {
            String cardContent = deckD.getCardById(cardID);
            cardLayout = new PathwayCards(cardContent).getLayoutOfCard();
        }
        this.layoutOfCard = cardLayout;
    }

    /**
     * Constructor
     *
     * @param cardContent contents of a pathway card, such as "ppbgpbgpp".
     * @author Yunmeng Zhang
     */
    public PathwayCards(String cardContent) {
        char[][] card = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                card[i][j] = cardContent.charAt(i * 3 + j);
            }
        }
        this.layoutOfCard = card;
    }

    public PathwayCards(char[][] layoutOfCard) {
        this.layoutOfCard = layoutOfCard;
    }

    /**
     * Constructor
     *
     * @param location
     * @param deck
     * @param layoutOfCard
     * @author Jialin Li
     */
    public PathwayCards(Location location, Deck deck, char[][] layoutOfCard) {
        this.location = location;
        this.deck = deck;
        this.layoutOfCard = layoutOfCard;
    }

    /**
     * Processes the pathway card placement string to rotate the card based on its specified orientation.
     * The placement string defines which deck the card comes from, its ID, and how it should be oriented on the board.
     *
     * @param placementString The complete placement string that specifies deck, card ID, and orientation, e.g., "Ab1208S".
     * @return The card details string with updated orientation reflecting the rotated layout.
     * @author Jialin Li
     */
    public String proccessPWCardRotate(String placementString) {
        //"A b 12 08 S
        char orientation = placementString.charAt(6);
        char deck = placementString.charAt(0);
        char id = placementString.charAt(1);

        String cardDetails = getPWCardStringById(deck, id);
        return rotatePWCard(cardDetails, orientation);
    }


    /**
     * Rotates the provided card details based on the given orientation.
     * This method assumes the card details include a unique ID followed by a 3x3 grid representation.
     *
     * @param cardDetails The card details including the ID and its 3x3 grid layout.
     * @param orientation The desired orientation to rotate the card ('N', 'E', 'S', 'W').
     * @return A string representing the rotated card including its ID.
     * @author Jialin Li
     */
    public String rotatePWCard(String cardDetails, char orientation) {
        char[] grid = cardDetails.toCharArray(); // Exclude the ID for rotation
        char[] rotatedGrid = new char[grid.length];

        switch (orientation) {
            case 'N': // No rotation needed
                return cardDetails;
            case 'E': // Rotate 90 degrees clockwise
                rotatedGrid[0] = grid[6];
                rotatedGrid[1] = grid[3];
                rotatedGrid[2] = grid[0];
                rotatedGrid[3] = grid[7];
                rotatedGrid[4] = grid[4];
                rotatedGrid[5] = grid[1];
                rotatedGrid[6] = grid[8];
                rotatedGrid[7] = grid[5];
                rotatedGrid[8] = grid[2];
                break;
            case 'S': // Rotate 180 degrees
                for (int i = 0; i < grid.length; i++) {
                    rotatedGrid[i] = grid[grid.length - 1 - i];
                }
                break;
            case 'W': // Rotate 270 degrees clockwise
                rotatedGrid[0] = grid[2];
                rotatedGrid[1] = grid[5];
                rotatedGrid[2] = grid[8];
                rotatedGrid[3] = grid[1];
                rotatedGrid[4] = grid[4];
                rotatedGrid[5] = grid[7];
                rotatedGrid[6] = grid[0];
                rotatedGrid[7] = grid[3];
                rotatedGrid[8] = grid[6];
                break;
        }
        return new String(rotatedGrid); // Include the ID
    }

    /**
     * Retrieves the card details string by its ID from the specified deck.
     * This method maps each deck identifier to its corresponding array of card details.
     *
     * @param deck The deck identifier (A, B, C, or D).
     * @param id   The ID of the card (from 'a' to 'y').
     * @return The string containing the card's unique ID and its 3x3 grid layout.
     * @throws IllegalArgumentException if the deck identifier is not recognized.
     * @author Jialin Li
     */
    public String getPWCardStringById(char deck, char id) {
        // Map the deck character to the corresponding deck array
        String[] deckArray;
        switch (deck) {
            case 'A':
                deckArray = DECK_A;
                break;
            case 'B':
                deckArray = DECK_B;
                break;
            case 'C':
                deckArray = DECK_C;
                break;
            case 'D':
                deckArray = DECK_D;
                break;
            default:
                throw new IllegalArgumentException("Invalid deck identifier: " + deck);
        }
        // IDs in 'a' to 'y' correspond to indices 0 to 24
        return deckArray[id - 'a'];
    }


    /**
     * Checks if placing a 3x3 card on the given board at the specified location will overlap
     * with forbidden characters representing fire, cats, or rafts.
     *
     * @param boardCharArray The game board represented as a 2D character array.
     * @param rowCard        The row coordinate of the top-left corner where the card will be placed.
     * @param colCard        The column coordinate of the top-left corner where the card will be placed.
     * @return true if there is an overlap with either fire, cat, or raft; false otherwise.
     * @author Jialin Li
     */
    public boolean isCardOverLapping(char[][] boardCharArray, int rowCard, int colCard) {
        // No part of the card is overlapping fire or cat or raft.
        // Check boundaries first to ensure the 3x3 grid can be placed without index out of bounds error
        if (rowCard < 0 || colCard < 0 || rowCard + 2 >= boardCharArray.length || colCard + 2 >= boardCharArray[0].length) {
            return true; // Out of bounds, assume overlapping to prevent erroneous placement
        }

        // Check for overlapping fire, cats, and raft
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int currentRow = rowCard + i;
                int currentCol = colCard + j;
                char currentChar = boardCharArray[currentRow][currentCol];
                if (currentChar == 'f' || currentChar == 'c' || isOverlappingRaft(boardCharArray, currentRow, currentCol)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Checks if the specified position on the board is part of a raft, including checking the 8 surrounding squares.
     *
     * @param board The game board as a 2D char array.
     * @param row   The row index to check.
     * @param col   The column index to check.
     * @return true if the position or any of its neighbors is marked as part of a raft ('o'), false otherwise.
     * @author Jialin Li
     */
    public boolean isOverlappingRaft(char[][] board, int row, int col) {
        for (int i = Math.max(0, row - 1); i <= Math.min(row + 1, board.length - 1); i++) {
            for (int j = Math.max(0, col - 1); j <= Math.min(col + 1, board[0].length - 1); j++) {
                if (board[i][j] == 'o') {
                    return true;
                }
            }
        }
        return false;
    }


    /**
     * Parse Pathway card
     *
     * @return Pathway card string
     * @author Tengyi Zhang
     */
    public String parsePathwayCard() {
        String[] cards = null;
        switch (deck.getType()) {
            case CIRCLE:
                cards = Utility.DECK_A;
                break;
            case CROSS:
                cards = Utility.DECK_B;
                break;
            case SQUARE:
                cards = Utility.DECK_C;
                break;
            case TRIANGLE:
                cards = Utility.DECK_D;
                break;
            case NONE:
                break;
        }

        StringBuilder pathwayCard = new StringBuilder();
        if (cards != null) {
            for (String card : cards) {
                if (card.startsWith(String.valueOf(this.id))) {
                    pathwayCard.append(card);
                }
            }
        }
        return pathwayCard.toString();
    }


    /**
     * rotate the locations of each squares of card
     *
     * @param orientation target orientation
     * @return new layout of card
     * @author Tengyi Zhang
     */
    public char[][] rotateCoordinates(String orientation) {
        char[][] newLayout = new char[3][3];
        if (orientation.equals("N")) {
            newLayout = layoutOfCard;
        } else if (orientation.equals("E")) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    newLayout[j][2 - i] = layoutOfCard[i][j];
                }
            }
        } else if (orientation.equals("S")) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    newLayout[2 - i][2 - j] = layoutOfCard[i][j];
                }
            }
        } else if (orientation.equals("W")) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    newLayout[2 - j][i] = layoutOfCard[i][j];
                }
            }
        }

        return newLayout;
    }


    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Deck getDeck() {
        return deck;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    public char[][] getLayoutOfCard() {
        return layoutOfCard;
    }

    //the state of each square on a pathway card
    public static State[] stateOfSquare() {
        return stateOfSquare();
    }
}