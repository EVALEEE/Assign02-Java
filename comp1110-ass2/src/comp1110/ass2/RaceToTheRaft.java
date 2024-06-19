package comp1110.ass2;

import comp1110.ass2.skeleton.*;

import java.util.*;


/**
 * This class is for testing purposes only. You should create and use your own objects to solve the tasks below
 * instead of directly using the strings provided. Note that Task 2 is the only task you are expected to use string
 * manipulation to solve.
 */
/**
 * @author Yunmeng Zhang, JiaLin Li, Tengyi Zhang
 */
public class RaceToTheRaft {
    private final String challenge;
    private String boardState;


    public RaceToTheRaft(int difficulty) {
        this.challenge = chooseChallenge(difficulty);
        this.boardState = initialiseChallenge(challenge);
    }

    public String getBoardState() {
        return boardState;
    }

    /**
     * Determine whether a boardState string is well-formed.
     * To be well-formed the string must satisfy all the following conditions:
     * <p>
     * 1. Each line is terminated by a newline character `\n`
     * 2. The number of printable (non-newline) characters in each line is equal AND is either 9 or 18.
     * 3. Each character (except the newline character) is one of the following:
     * - 'b' (blue)
     * - 'B' (blue with blue cat)
     * - 'f' (fire)
     * - 'g' (green)
     * - 'G' (green with green cat)
     * - 'n' (none)
     * - 'o' (objective)
     * - 'p' (purple)
     * - 'P' (purple with purple cat)
     * - 'r' (red)
     * - 'R' (red with red cat)
     * - 'w' (wild)
     * - 'W' (wild with a cat of any colour)
     * - 'y' (yellow)
     * - 'Y' (yellow with yellow cat)
     * 4. The number of lines is either 12, 15 or 18.
     * </p>
     *
     * @param boardString A string representing the boardState
     * @return True if the boardState is well-formed, otherwise false.
     * @author Tengyi Zhang
     */
    public static boolean isBoardStringWellFormed(String boardString) {
        //Create a new object of board
        Board board = new Board(boardString);
        //Check if the string of board is well-formed.
        return board.isWellFormed();
    }
    // FIXME TASK 2

    /**
     * Make Constructors for each of your objects.
     */
    // FIXME TASK 3

    /**
     * Draws a random fire tile from those remaining in the bag.
     *
     * @param gameState the current state of the game, including the fire tile bag
     * @return a random fire tile from those remaining, in string form. If there are no tiles remaining, return the
     * empty string.
     * @author Yunmeng Zhang
     */
    public static String drawFireTile(String[] gameState) {
        FireTileBag fireTileBag = new FireTileBag(gameState[gameState.length - 1]);
        return fireTileBag.drawFireTiles();
    }  // FIXME TASK 5

    /**
     * Chooses a random challenge from those available in the Utility class according
     * to the given difficulty.
     *
     * @param difficulty the desired difficulty of the challenge
     * @return a random challenge of the given difficulty
     * @author Yunmeng Zhang
     */
    public static String chooseChallenge(int difficulty) {
        int start = 0; // start
        int end; // end
        switch (difficulty) {
            case 0 -> {
                end = 3;
            }
            case 1 -> {
                start = 4;
                end = 7;
            }
            case 2 -> {
                start = 8;
                end = 15;
            }
            case 3 -> {
                start = 16;
                end = 23;
            }
            case 4 -> {
                start = 24;
                end = 31;
            }
            case 5 -> {
                start = 32;
                end = 38;
            }
            default -> {
                return null;
            }
        }
        Random random = new Random();
        int randomIndex = random.nextInt(end - start + 1) + start;
        return Challenge.getAChallenge(randomIndex);// FIXME TASK 6
    }

    /**
     * Draw random cards from the specified decks.
     * The decks string denotes what decks to draw from and how many cards to draw from that deck.
     * <p>
     * For example the drawRequest string "A4B1D1" tells us that we should draw 4 cards from deck A, 1 card from deck B
     * and
     * 1 card from deck D.
     * <p>
     * If I draw cards a, b, d, l, from deck A, card a from deck B and card s from deck D, I would return the string:
     * "AabdlBaCDs".
     * Decks should be listed in alphabetical order, with cards drawn from that deck also listed in alphabetical order.
     * </p>
     * Recall the mapping between deck and char:
     * A -> CIRCLE;
     * B -> CROSS;
     * C -> SQUARE;
     * D -> TRIANGLE;
     *
     * @param gameState   the current state of the game, including the current state of the decks
     * @param drawRequest A string representing the decks to draw from and the number of cards to draw from each respective deck.
     * @return The updated gameState array after the cards have been drawn. (Remove all cards drawn from decks and
     * add them to the Hand string). If it is not possible
     * to draw all the specified cards, you should return the original gameState.
     * @author Jialin Li
     */
    public static String[] drawHand(String[] gameState, String drawRequest) {
        //Duplicate a gameState for later using.
        String[] tempgameState = new String[gameState.length];
        for (int i = 0; i < gameState.length; i++) {
            tempgameState[i] = gameState[i];
        }

        //Make String drawRequest into a char[]. Then we can get the details of drawRequest.
        char[] charArray = drawRequest.toCharArray();//A1C2D3
        String decks = gameState[1];

        //Separate different parts of the certain decks from the String.
        char[] charArray_A = Deck.getDeckStringA(decks, 'A', 'B');
        char[] charArray_B = Deck.getDeckStringA(decks, 'B', 'C');
        char[] charArray_C = Deck.getDeckStringA(decks, 'C', 'D');
        char[] charArray_D = Deck.getDeckStringB(decks, 'D');

        //Initialize.
        String stringA = " ";
        String stringB = " ";
        String stringC = " ";
        String stringD = " ";

        label1:
        for (int i = 0; i < charArray.length; i += 2) {
            //Get the number of cards that player wants from drawRequest.
            int num = Integer.parseInt(String.valueOf(charArray[i + 1]));
            // Create a Hashset to store the random number and used to judge if the random number is the same.
            Set<Integer> numbers_set = new HashSet<>();
            //Get the name of the deck from drawRequest (A B C D).
            char deck = charArray[i];

            //Use A B C D as different cases, to solve the certain situation.
            switch (deck) {
                case 'A':
                    if (charArray_A.length != 1 && !(num >= charArray_A.length - 1)) {
                        //1. Generate several number of cards.
                        //2. Judge if there are same numbers.
                        //3. Transform the Hashset into int[].
                        //4. Use a method from Player class.
                        int[] numbers = Player.generateRandomCardnum(numbers_set, num, charArray_A);
                        //The player draw the cards from the decks, and then update the state of cards in player's hand.
                        gameState[2] = Player.updateCardInHnad(numbers, charArray_A, gameState, deck);
                        //Update the deck's state after player taking her cards from decks.
                        stringA = Deck.updateDeck(charArray_A);
                        break;
                    } else
                        break label1;
                case 'B':
                    if (charArray_B.length != 1 && !(num >= charArray_B.length - 1)) {
                        int[] numbers = Player.generateRandomCardnum(numbers_set, num, charArray_B);
                        gameState[2] = Player.updateCardInHnad(numbers, charArray_B, gameState, deck);
                        stringB = Deck.updateDeck(charArray_B);
                        break;
                    } else
                        break label1;
                case 'C':
                    if (charArray_C.length != 1 && !(num >= charArray_C.length - 1)) {
                        int[] numbers = Player.generateRandomCardnum(numbers_set, num, charArray_C);
                        gameState[2] = Player.updateCardInHnad(numbers, charArray_C, gameState, deck);
                        stringC = Deck.updateDeck(charArray_C);
                        break;
                    } else
                        break label1;
                case 'D':
                    if (charArray_D.length != 1 && !(num >= charArray_D.length - 1)) {
                        int[] numbers = Player.generateRandomCardnum(numbers_set, num, charArray_D);
                        gameState[2] = Player.updateCardInHnad(numbers, charArray_D, gameState, deck);
                        stringD = Deck.updateDeck(charArray_D);
                        break;
                    } else
                        break label1;
            }
        }

        //If the deck is empty or don't have enough cards for player. It will pass the process above and still be " ".
        //So we need to deal with it.
        String A = Deck.isDeckValid(stringA, charArray_A);
        String B = Deck.isDeckValid(stringB, charArray_B);
        String C = Deck.isDeckValid(stringC, charArray_C);
        String D = Deck.isDeckValid(stringD, charArray_D);

        //Create a StringBuilder to append all the Strings of A B C D Decks together, become a new gameState[1].
        StringBuilder finalString = new StringBuilder();
        finalString.append(A).append(B).append(C).append(D);

        //Duplicate a new "String[] gameState", so we can let the initial gameState be original.
        String[] newGameState = Arrays.copyOf(gameState, gameState.length);
        newGameState[1] = String.valueOf(finalString);
        gameState = tempgameState;

        return newGameState;
        // FIXME TASK 7
    }

    /**
     * Place the given card or fire tile as described by the placement string and return the updated gameState array.
     * See the README for details on these two strings.
     * You may assume that the placements given are valid.
     * <p>
     * When placing a card, you should update both the Board string and remove the corresponding card from the Hand
     * string in the gameState array.
     *
     * @param gameState       An array representing the game state.
     * @param placementString A string representing a Fire Tile Placement or a Card Placement.
     * @return the updated gameState array after this placement has been made
     * @author Yunmeng Zhang
     */
    public static String[] applyPlacement(String[] gameState, String placementString) {
        //place pathway card
        //if the second letter of placement string is not digit, the placement if about pathway card
        if (!Character.isDigit(placementString.charAt(1))) {
            String deckAndCard = placementString.substring(0, 2);
            String deck = String.valueOf(deckAndCard.charAt(0));
            String cardID = String.valueOf(deckAndCard.charAt(1));
            Player cardsInHand = new Player(gameState[2]);
            if (cardsInHand.cardCanBeDiscarded(deckAndCard)) {
                // Remove the card from the player's hand
                cardsInHand.removeFromHand(placementString);
                // Update gameState[2] with the updated player's hand string
                gameState[2] = cardsInHand.getCardsOnHandString();

                //get the layout of the pathway card by deck and ID
                PathwayCards pathwayCard = new PathwayCards(deck, cardID);

                //rotate the card
                String rotateOrientation = String.valueOf(placementString.charAt(6));
                char[][] cardToPlace = new PathwayCards(pathwayCard.getLayoutOfCard()).rotateCoordinates(rotateOrientation);

                //place the card on board
                String[] gameBoard = gameState[0].split("\n");
                int targetRow = Integer.parseInt(placementString.substring(2, 4));
                int targetCol = Integer.parseInt(placementString.substring(4, 6));
                for (int i = 0; i < cardToPlace.length; i++) {
                    for (int j = 0; j < cardToPlace[i].length; j++) {
                        char[] row = gameBoard[targetRow + i].toCharArray();
                        row[targetCol + j] = cardToPlace[i][j];
                        gameBoard[targetRow + i] = new String(row);
                    }
                }
                gameState[0] = String.join("\n", gameBoard) + '\n';
            }
        }
        //place fire tile
        else {
            //get the layout of the fire tile
            FireTile fireTile = new FireTile(placementString);

            //get the layout of the fire tile after rotation
            String rotateOrientation = placementString.substring(5, 7);
            List<Location> fireTileToPlace = fireTile.rotateCoordinates(rotateOrientation);

            //location to place
            int targetRow = Integer.parseInt(placementString.substring(1, 3));
            int targetCol = Integer.parseInt(placementString.substring(3, 5));

            //get the char[][] gameBoard
            String[] gameRows = gameState[0].split("\n");
            char[][] board = new char[gameRows.length][];
            for (int i = 0; i < gameRows.length; i++) {
                board[i] = gameRows[i].toCharArray();
            }

            //place the fire tile on board
            for (Location loc : fireTileToPlace) {
                int row = loc.getRow() + targetRow;
                int col = loc.getColumn() + targetCol;
                if (row >= 0 && row < board.length && col >= 0 && col < board[row].length) {
                    board[row][col] = 'f';
                }
            }

            //convert the char[][] board to String
            String[] rows = new String[board.length];
            for (int i = 0; i < board.length; i++) {
                rows[i] = new String(board[i]);
            }
            gameState[0] = String.join("\n", rows) + '\n';
        }
        return gameState;
    }
    // FIXME TASK 8


    /**
     * Move the given cat as described by the cat movement string and return the updated gameState array. You may
     * assume that the cat movement is valid.
     * <p>
     * You should both move the cat (updating the Board string) and also add the cat to the Exhausted Cats string, or
     * update that cat's reference in the Exhausted Cats string if it was already exhausted.
     *
     * @param gameState      An array representing the game state.
     * @param movementString A string representing the movement of a cat and the cards discarded to allow this move.
     * @return the updated gameState array after this movement has been made.
     * @author Tengyi Zhang
     */
    public static String[] moveCat(String[] gameState, String movementString) {
        // Create a new Cat object and parse the movement string.
        Cat cat = new Cat();
        cat.parseMovementString(movementString);
//        System.out.println("movement string is: " + movementString);
//        System.out.println("cat colour is: " + cat.getColour());
//        System.out.println("cat start location is: " + cat.getStartLocation());
//        System.out.println("cat end location is: " + cat.getEndLocation());

        // Create a new Player object and remove the cat movement from the player's hand.
        Player player = new Player(gameState[2]);
        player.removeCatMovementFromHand(movementString);

        // If the movement string indicates an exhausted cat, discard it from the player's hand.
        if (movementString.length() == 13) {
            player.discardExhaustedCat(movementString);
        }

        // Create a new Board object and update the cat's position on the board.
        gameState[2] = player.getCardsOnHandString();
        Board board = new Board(gameState[0]);
        board.updateCatOnBoard(cat);
        gameState[0] = board.getBoardString();

        // Update the exhausted cat
        String exhaustedString = gameState[3];
        List<String> exhaustedArray = new ArrayList<>();

        // If there exists an exhausted cat
        if (exhaustedString.length() != 0) {
            // Update the location of the exhausted cat if it matches the moved cat.
            String[] exhaustedCatsList = exhaustedString.split("(?=[A-Z])");
            exhaustedArray = new ArrayList<String>(List.of(exhaustedCatsList));

            for (int i = 0; i < exhaustedArray.size(); i++) {
                // Create a new exhausted cat object
                ExhaustedCat exhaustedCat = new ExhaustedCat(exhaustedArray.get(i));
                Colour exCatColour = exhaustedCat.getColour();
                Location exLocation = exhaustedCat.getExCatLocation();

                // if the exhausted cat is matched to previous cat
                if (cat.getColour().equals(exCatColour) && cat.getStartLocation().equals(exLocation)) {
                    // Set the location of the cat
                    exhaustedCat.setExCatLocation(cat.getEndLocation());
                    exLocation = cat.getEndLocation();

                    // Convert object to String
                    String finalRow = exLocation.getRow() < 10 ? "0" + String.valueOf(exLocation.getRow()) :
                            String.valueOf(exLocation.getRow());
                    String finalColumn = exLocation.getColumn() < 10 ? "0" + String.valueOf(exLocation.getColumn()) :
                            String.valueOf(exLocation.getColumn());
                    exhaustedString =
                            Character.toUpperCase(exCatColour.toChar()) + finalRow + finalColumn;
//                exhaustedCat = new ExhaustedCat(exhaustedString);
                    exhaustedArray.set(i, exhaustedString);
                    break;
                }
            }
        }
        // if there doesn't exist an exhausted cat
        if (movementString.length() == 11) {
            // Add cat to the array directly
            Colour newExCatColour = cat.getColour();
            Location newExCatLocation = cat.getEndLocation();
            String finalRow = newExCatLocation.getRow() < 10 ? "0" + String.valueOf(newExCatLocation.getRow()) :
                    String.valueOf(newExCatLocation.getRow());
            String finalColumn = newExCatLocation.getColumn() < 10 ? "0" + String.valueOf(newExCatLocation.getColumn()) :
                    String.valueOf(newExCatLocation.getColumn());
            exhaustedString =
                    Character.toUpperCase(newExCatColour.toChar()) + finalRow + finalColumn;
            exhaustedArray.add(exhaustedString);
        }

        Collections.sort(exhaustedArray);

        StringBuilder totalExhaustedString = new StringBuilder();
        for (String cats : exhaustedArray) {
            totalExhaustedString.append(cats);
        }
        gameState[3] = String.valueOf(totalExhaustedString);
        return gameState;
        // FIXME TASK 9

    }

    /**
     * Given a challengeString, construct a board string that satisfies the challenge requirements.
     * <p>
     * Each board in the `squareBoard` or `rectangleBoard` arrays may only be used once. For example: if the
     * challenge requires 4 Large (square) boards, you must use all 4 square boards. You may not use the same board
     * twice, even in different orientations.
     * The cat, fire card and raft card placements should all match the challenge string.
     *
     * @param challengeString A string representing the challenge to initialise
     * @return A board string for this challenge.
     * @author Jialin Li
     */
    public static String initialiseChallenge(String challengeString) {
        Cat cat = new Cat();
        FireTile fireTile = new FireTile();
        RaftCard raftCard = new RaftCard();
        Board board = new Board();
        Island island = new Island();
        // Split the input string into parts.
        // Each part starts with F, C, or R indicating FireTiles, CatCards, or RaftCards respectively
        String[] parts = challengeString.split("(?=[FCR])");
        //"LNSNLASA F000300060012001503030903 C112033060340009 R01215"
        // Initialize the board layout based on the island configuration part
        String[][] boardLayout = board.initialiseBoardLayout(parts[0]);

        // Place islands, cat cards, fire cards, and raft cards on the board
        island.placeIsland(boardLayout, parts[0]);
        cat.placeCatCards(boardLayout, parts[2]);
        fireTile.placeFireCards(boardLayout, parts[1]);
        raftCard.placeRaftCard(boardLayout, parts[3]);

        // Convert the 2D board layout into a single string for output
        return board.convertBoardToString(boardLayout);
        // FIXME TASK 10
    }


    /**
     * Given a card placement string or a fire tile placement string, check if that placement is valid.
     * <p>
     * A card placement is valid if all the following conditions are met:
     * <p>
     * 1. No part of the card is off-board
     * 2. No part of the card is overlapping fire.
     * 3. No part of the card is overlapping a cat.
     * 4. No part of the card is overlapping part of a Raft card (any of the 8 squares surrounding the `o`
     * state)
     * </p>
     * A fire tile placement is valid if all the following conditions are met:
     * <p>
     * 1. No part of the fire tile is off-board
     * 2. No part of the fire tile overlaps fire
     * 3. No part of the fire tile overlaps a cat
     * 4. No part of the fire tile overlaps part of a Raft card (any of the 8 squares surrounding the `o` state)
     * 5. The Fire tile is orthogonally adjacent to another fire square.
     * </p>
     *
     * @param gameState       An array representing the gameState
     * @param placementString A string representing a card placement or a fire tile placement
     * @return True if the placement is valid, otherwise false.
     * @author Jialin Li, Yunmeng Zhang
     */
    public static boolean isPlacementValid(String[] gameState, String placementString) {
        //get the char[][] gameBoard
        String[] gameRows = gameState[0].split("\n");
        char[][] board = new char[gameRows.length][];
        for (int i = 0; i < gameRows.length; i++) {
            board[i] = gameRows[i].toCharArray();
        }
        //test fire tile validity
        if (Character.isDigit(placementString.charAt(1))) {
            String orientation = placementString.substring(5, 7);
            FireTile fireTile = new FireTile(placementString);
            List<Location> fireLocations = fireTile.rotateCoordinates(orientation);
            //location to place
            int targetRow = Integer.parseInt(placementString.substring(1, 3));
            int targetCol = Integer.parseInt(placementString.substring(3, 5));
            for (Location loc : fireLocations) {
                int placeRow = loc.getRow() + targetRow;
                int placeCol = loc.getColumn() + targetCol;
                loc.setRow(placeRow);
                loc.setColumn(placeCol);
            }
            for (Location loc : fireLocations) {
                System.out.println(loc.toString());
                //test on board
                if (!loc.isValidLocation(gameState[0])) {
                    return false;
                }
                //test overlap
                if (board[loc.getRow()][loc.getColumn()] == 'f' || Character.isUpperCase(board[loc.getRow()][loc.getColumn()]) || loc.isNearO(board)) {
                    return false;
                }
            }
            //test adjacent to fire
            FireTile finalFire = new FireTile(fireLocations);
            if (finalFire.noneAdjacentFire(gameState[0])) {
                return false;
            }
        }

        //test card validity
        if (!Character.isDigit(placementString.charAt(1))) {
            String deck = String.valueOf(placementString.charAt(0));
            String cardID = String.valueOf(placementString.charAt(1));
            //get the pathway card layout by deck and ID
            PathwayCards pathwayCard = new PathwayCards(deck, cardID);

            int targetRow = Integer.parseInt(placementString.substring(2, 4));
            int targetCol = Integer.parseInt(placementString.substring(4, 6));
            String orientation = String.valueOf(placementString.charAt(6));
            char[][] cardToPlace = pathwayCard.rotateCoordinates(orientation);

            //find the locations to be placed
            List<Location> cardLocations = new ArrayList<>();
            for (int row = 0; row < cardToPlace.length; row++) {
                for (int column = 0; column < cardToPlace[row].length; column++) {
                    cardLocations.add(new Location(row + targetRow, column + targetCol));
                }
            }

            //test on board
            for (Location loc : cardLocations) {
                if (!loc.isValidLocation(gameState[0])) {
                    return false;
                }
                //test overlap
                if (board[loc.getRow()][loc.getColumn()] == 'f' || Character.isUpperCase(board[loc.getRow()][loc.getColumn()]) || loc.isNearO(board)) {
                    return false;
                }
            }
        }
        return true; // FIXME TASK 12
    }


    /**
     * Given a cat movement string, check if the cat movement is valid.
     * <p>
     * A cat movement is valid if:
     * 1. The end location is the same colour as the cat.
     * 2. There is a path from start location to the end location, consisting only of squares the same colour as the
     * cat.
     * 3. The path does not include diagonal movements.
     *
     * @param gameState         An array representing the gameState
     * @param catMovementString A string representing a cat movement.
     * @return True if the cat movement is valid, otherwise false
     * @author Yunmeng Zhang
     */
    public static boolean isCatMovementValid(String[] gameState, String catMovementString) {
        Colour cat = Colour.fromChar(Character.toLowerCase(catMovementString.charAt(0)));
        String boardState = gameState[0];
        Location startLocation = new Location(Integer.parseInt(catMovementString.substring(1, 3)), Integer.parseInt(catMovementString.substring(3, 5)));
        Location endLocation = new Location(Integer.parseInt(catMovementString.substring(5, 7)), Integer.parseInt(catMovementString.substring(7, 9)));
        //is movement in the boundary
        if (!endLocation.isValidLocation(boardState) || !startLocation.isValidLocation(boardState)) {
            return false;
        }
        Colour endLocationColour = endLocation.getColourAt(boardState);
        //return false if color of cat is different from color of end location.
        if (!cat.equals(endLocationColour)) {
            return false;
        }

        //test for the last two numbers considering deck and its card
        //return false if the cat is exhausted and only one card is discarded
        String exhaustedCat = gameState[3];
        if (exhaustedCat.equals(catMovementString.substring(0, 5)) && catMovementString.length() != 13) {
            return false;
        }

        //return false if the card to be discarded is not in hand
        Player cardsInHand = new Player(gameState[2]);
        String deckAndCard = catMovementString.substring(9);
        if (cardsInHand.cardCanBeDiscarded(deckAndCard)) {
            if (findCatPath(startLocation, endLocation, cat, boardState)) {
                return true;
            }
        }
        //   System.out.println("No valid path found.");
        return false; // FIXME TASK 14
    }

    /**
     * Uses a breadth-first search (BFS) algorithm to determine if there is a path of a specific colour
     * (representing a cat) between two locations on the game board.
     *
     * @param startLocation The starting location for the path search.
     * @param endLocation The target location to reach on the board.
     * @param cat The colour of the cat, which determines the allowed path color.
     * @param boardState A string representation of the game board, where each location's state can be queried.
     * @return true if there is a path between the start and end locations following only locations of the cat's colour; false otherwise.
     * @author Yunmeng Zhang
     */
    private static boolean findCatPath(Location startLocation, Location endLocation, Colour cat, String boardState) {
        // BFS to find a valid path
        Queue<Location> queue = new LinkedList<>();
        Set<Location> visited = new HashSet<>();
        queue.add(startLocation);
        visited.add(startLocation);
//        System.out.println("Start"+startLocation.toString());
        while (!queue.isEmpty()) {
            Location current = queue.poll();
//            System.out.println("Visiting: " + current);

            if (current.equals(endLocation)) {
//                System.out.println("Found a valid path to the end location.");
                return true; // Found a valid path
            } else if (current.getColourAt(boardState).equals(cat)) {
                List<Location> adjacent = current.getAdjacentLocations(boardState);
                for (Location location : adjacent) {
                    if (!visited.contains(location) && location.getColourAt(boardState).equals(cat)) {
                        //                       System.out.println("Adding location to queue: " + location);
                        queue.add(location);
                        visited.add(location);
                    }
                }
            }
        }
        return false;
    }


    /**
     * Determine whether the game is over. The game ends if any of the following conditions are met:
     * <p>
     * Fire tile placement:
     * 1. If this placement action is not valid AND there is no other position this tile could be placed validly
     * (the game is lost).
     * 2. If placing this fire tile makes it impossible for any one cat to reach the raft (the game is lost).
     * <p>
     * Cat movement:
     * 1. If after moving this cat, all cats have safely reached the raft (the game is won).
     * <p>
     * Card placement:
     * 1. If after placing this card, there are no more fire tiles left in the bag (the game is lost).
     * </p>
     *
     * @param gameState An array of strings representing the game state
     * @param action    A string representing a fire tile placement, cat movement or card placement action.
     * @return True if the game is over (regardless of whether it is won or lost), otherwise False.
     * @author Yunmeng Zhang
     */
    public static boolean isGameOver(String[] gameState, String action) {
        //test 1, if all cats reached the raft
        if (action.length() == 11 || action.length() == 13) {
            String[] newGameState = moveCat(gameState, action);

            //get the char[][] gameBoard
            String[] gameRows = newGameState[0].split("\n");
            char[][] board = new char[gameRows.length][];
            for (int i = 0; i < gameRows.length; i++) {
                board[i] = gameRows[i].toCharArray();
            }

            int rows = board.length;
            int cols = board[0].length;
            //test if uppercase (cat) is near "o"
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    if (Character.isUpperCase(board[i][j])) {
                        Location cat = new Location(i, j);
                        //if any cat is not near o, return false
                        if (!cat.isNearO(board)) {
                            return false;
                        }
                    }
                }
            }
        }

        //test 2, after pathway card
        if ((action.length() == 7) && !Character.isDigit(action.charAt(1))) {
            String[] newGameState = applyPlacement(gameState, action);
            //if there are still fire tiles left in the bag after card placement, game is not over
            if (!newGameState[4].isEmpty()) {
                return false;
            }
        }

        //game over about fire tile
        if ((action.length() == 7) && Character.isDigit(action.charAt(1))) {
            //test if the fire tile can be placed
            if (isPlacementValid(gameState, action)) {
                //test if placing this fire tile makes it impossible for any cat to reach the raft
                String[] newGameState = applyPlacement(gameState, action);
                //get the char[][] gameBoard
                String[] gameRows = newGameState[0].split("\n");
                char[][] board = new char[gameRows.length][];
                for (int i = 0; i < gameRows.length; i++) {
                    board[i] = gameRows[i].toCharArray();
                }
                int rows = board.length;
                int cols = board[0].length;

                Board gameBoard = new Board(board);

                //all the cats' locations on the board
                List<Location> catLocations = gameBoard.findCatPositions();

                //if any of the cat is enclosed by fire, game is over (return true).
                boolean anyCatEnclosed = false;
                for (Location loc : catLocations) {
                    Colour catColour = loc.getColourAt(newGameState[0]);
                    if (isEnclosed(newGameState[0], loc, rows, cols, board, catColour)){
                        anyCatEnclosed = true;
                        break;
                    }
                }
                return anyCatEnclosed;
            } else if (!isPlacementValid(gameState, action)) {

                StringBuilder sb = new StringBuilder();
                String[] suffixes = {"TN", "FN", "TE", "FE", "TW", "FW", "TS", "FS"};  // 定义所有可能的后缀

                //get the char[][] gameBoard
                String[] gameRows = gameState[0].split("\n");
                char[][] board = new char[gameRows.length][];
                for (int i = 0; i < gameRows.length; i++) {
                    board[i] = gameRows[i].toCharArray();
                }
                int rows = board.length;
                int cols = board[0].length;

                // read through all the col and row
                for (int row = 0; row < rows; row++) {
                    for (int col = 0; col < cols; col++) {
                        for (String suffix : suffixes) {  // test all the orientations for each fire tile

                            sb.setLength(0);
                            sb.append(action.charAt(0));
                            if (row < 10) {
                                sb.append("0");  // if row < 10, add '0' in front of it for format
                            }
                            sb.append(row);
                            if (col < 10) {
                                sb.append("0");  // if col < 10, add '0' in front of it for format
                            }
                            sb.append(col);
                            sb.append(suffix);  // add orientation
                            String newFirePlacement = sb.toString();

                            // test if the new location is valid for placement
                            if (isPlacementValid(gameState, newFirePlacement)) {
                                System.out.println(newFirePlacement);
                                return false;  // return false if there is a valid placement possibility
                            }
                        }
                    }
                }

            }
        }
        return true;     // FIXME TASK 15
    }

    /**
     * Use BFS to find if there exists a path that all the squares of it are
     * neither fire nor the place that the cat is impossible to cross
     * (the narrow space which is not the color of cat and the space has no room for pathway card)
     *
     * @param gameBoard the game board string after placement of new fire tile
     * @param loc       location of the cat
     * @param rows      the total rows of game board
     * @param cols      the total columns of game board
     * @param board     the char[][] game board after placement of new fire tile
     * @param catColour color of the cat
     * @return true if the cat is enclosed
     * @author Yunmeng Zhang
     */
    private static boolean isEnclosed(String gameBoard, Location loc, int rows, int cols, char[][] board, Colour catColour) {
        final int[] dRow = {-1, 1, 0, 0}; // move for row location
        final int[] dCol = {0, 0, -1, 1}; // move for col location
        //BFS test enclosed
        Queue<Location> queue = new LinkedList<>();
        boolean[][] visited = new boolean[rows][cols];
        queue.offer(loc);
        visited[loc.getRow()][loc.getColumn()] = true;

        while (!queue.isEmpty()) {
            Location current = queue.poll();

            for (int i = 0; i < 4; i++) {
                int newRow = current.getRow() + dRow[i];
                int newCol = current.getColumn() + dCol[i];

                // boundary test
                if (newRow < 0 || newRow >= rows || newCol < 0 || newCol >= cols) {
                    continue;
                }

                if (board[newRow][newCol] == 'w') {
                    return false; // if the path includes raft, the cat is not enclosed
                }

                Location potentialPath = new Location(newRow, newCol);

                if (!visited[newRow][newCol]) {
                    //System.out.println("Visiting (" + newRow + ", " + newCol + "), Character: " + board[newRow][newCol]);
                    if (board[newRow][newCol] == 'f') {
                        //    System.out.println("Blocked by fire, not adding to queue.");
                        visited[newRow][newCol] = true;
                    } else if (potentialPath.isFakeFire(gameBoard, catColour)) {
                        //    System.out.println("is fake fire, not adding to queue.");
                        visited[newRow][newCol] = true;
                    } else {
                        queue.add(new Location(newRow, newCol));
                        visited[newRow][newCol] = true;
                    }
                }
            }
        }
        return true;
    }
}
