package comp1110.ass2.gui;

import comp1110.ass2.RaceToTheRaft;
import comp1110.ass2.Utility;
import comp1110.ass2.skeleton.*;
import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;

import javafx.geometry.Point2D;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

public class Game extends Application {

    private final Group root = new Group();
    private static final int WINDOW_WIDTH = 1100;
    private static final int WINDOW_HEIGHT = 650;
    private static final int MARGIN_X = 470;
    private static final int MARGIN_Y = 50;
    // size of each square is 30*30
    final int squareSize = 30;
    // gap
    final int gap = 2;

    private final Group controls = new Group();

    private TextField difficultyInput;
    private Button startGameButton;
    private Button drawFireButton; // Declare the button
    private Button drawCardButton;
    private Button rulesButton;
    private Button rotateFireButton;
    private Button rotateCardButton;
    private Button discardButton;

    private TextField inputA, inputB, inputC, inputD;
    private TextField discardRequest;
    private String fireTileDrawn = "";
    private int clickCount = 0;  // click counter for rotating and flipping fire tile
    private int clickCountCard = 0;

    RaceToTheRaft game;

    private final String allPathwayCards = "AabcdefghijklmnopqrstuvwxyBabefghijklmnopqstuvwxyCabcefghijklmnpqstuvwxyDabcdefghijklmnopqrstuvwxy";
    private final String allFireTiles = "abcdefghijklmnopqrstuvwxyzABCDE";
    private String[] gameState = new String[5];
    private String newFireTile = "";
    String fireTileOrientation = "FN";
    String cardOrientation = "N";
    private String discardCard = "";


    // FIXME TASK 11 Basic game
    // FIXME TASK 13 Fully working game

    /**
     * Stage
     * @author Yunmeng Zhang
     */
    public void start(Stage stage) {
        Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);


        stage.setScene(scene);
        stage.setTitle("Race to the Raft Game");

        makeControls();
        rotateFireButton.setVisible(false);
        drawFireButton.setVisible(false);
        drawCardButton.setVisible(false);
        rotateCardButton.setVisible(false);
        inputA.setVisible(false);
        inputB.setVisible(false);
        inputC.setVisible(false);
        inputD.setVisible(false);
        discardButton.setVisible(false);
        discardRequest.setVisible(false);
        stage.show();
    }

    /**
     * Start game
     * @author Yunmeng Zhang
     */
    private void startGame() {
        try {
            int difficulty = Integer.parseInt(difficultyInput.getText());
            if (difficulty < 0 || difficulty > 5) {
                throw new IllegalArgumentException("Difficulty must be between 0 and 5.");
            }
            game = new RaceToTheRaft(difficulty);
            gameState[0] = game.getBoardState();
            gameState[1] = allPathwayCards;
            gameState[2] = "ABCD"; // Cards on hand
            gameState[3] = ""; // Exhausted cat
            gameState[4] = allFireTiles;
            displayState(gameState[0], gameState[2], fireTileDrawn, fireTileOrientation, cardOrientation); // Update display based on new game state

            rotateFireButton.setVisible(false);
            discardRequest.setVisible(false);
            discardButton.setVisible(false);
            rotateCardButton.setVisible(false);
            drawFireButton.setVisible(true);
            drawCardButton.setVisible(true);
            inputA.setVisible(true);
            inputB.setVisible(true);
            inputC.setVisible(true);
            inputD.setVisible(true);

        } catch (IllegalArgumentException e) {
            System.err.println("Invalid difficulty: " + e.getMessage());
        }

    }

    /**
     * Draw the given board and hand in the window, removing any previously drawn boards/hands.
     *
     * @param boardState newline separated string representing each row of the board (the board string, see the STRING-REPRESENTATION.md for more details
     * @param hand A string representing the cards in a player's hand (the hand string, see the STRING-REPRESENTATION.md for more details)
     * @author Yunmeng Zhang
     */
    void displayState(String boardState, String hand, String fireTileDrawn, String fireTileOrientation, String cardOrientation) {
        // Clear all game elements but keep the control panel and input components
        root.getChildren().removeIf(child -> !(child instanceof HBox)); // Keep only HBox (containers containing controls)


        // draw board
        drawBoard(boardState);

        // draw cards in hand
        displayHand(hand,cardOrientation);

        //draw fire tiles in bag
        displayFireTile(fireTileDrawn, fireTileOrientation);
    }

    /**
     * set the color of a square
     * @param col column
     * @param row row
     * @param state the character of that square
     * @return a square with color
     * @author Yunmeng Zhang
     */
    private Rectangle setSquareColor(int col, int row, char state, double x, double y) {
        Color color = switch (state) {
            case 'b' -> Color.BLUE;
            case 'r' -> Color.RED;
            case 'y' -> Color.YELLOW;
            case 'p' -> Color.PURPLE;
            case 'g' -> Color.GREEN;
            case 'o', 'w' -> Color.LIGHTGRAY; // raft and wildcard
            case 'Y', 'B', 'G', 'R','P' -> Color.WHITE;
            default -> Color.GRAY;
        };

        final int squareSize = 30; // Size of each square
        Rectangle square = new Rectangle(x, y, squareSize, squareSize);
        square.setFill(color);
        return square;
    }

    /**
     * add symbol to a square
     * @param root the root of game
     * @param x col of square
     * @param y row of square
     * @param state state character of square
     * @param squareSize square size
     * @author Yunmeng Zhang
     */
    private void addSymbols(Group root, double x, double y, char state, int squareSize) {
        // Examine state to determine if a symbol needs to be added
        if (Character.isUpperCase(state)) {
            // Add a cat symbol
            Text catSymbol = new Text(x, y + squareSize / 1.2, "🐱");
            catSymbol.setFont(Font.font("Verdana", squareSize)); // Size of the cat symbol
            if (state == 'Y'){
                catSymbol.setFill(Color.YELLOW);
            }
            if (state =='R'){
                catSymbol.setFill(Color.RED);
            }
            if (state =='G'){
                catSymbol.setFill(Color.GREEN);
            }
            if (state =='B'){
                catSymbol.setFill(Color.BLUE);
            }
            if (state == 'P'){
                catSymbol.setFill(Color.PURPLE);
            }
            root.getChildren().add(catSymbol);
            int originalCol = (int) ((x - MARGIN_X) / (squareSize + gap));
            int originalRow = (int) ((y - MARGIN_Y) / (squareSize + gap));
            makeCatsDraggable(catSymbol, state,originalRow,originalCol); // Make this cat draggable
        }
        if (state == 'f') {
            // Add a fire symbol
            Text fireSymbol = new Text(x + squareSize / 4, y + squareSize / 1.5, "🔥");
            fireSymbol.setFont(Font.font("Verdana", squareSize / 2));
            fireSymbol.setFill(Color.BROWN);
            root.getChildren().add(fireSymbol);
        }
        if (state == 'o' || state == 'w') {
            // Add a raft symbol
            Text raftSymbol = new Text(x, y + squareSize / 1.2, "⛵");
            raftSymbol.setFont(Font.font("Verdana", squareSize));
            root.getChildren().add(raftSymbol);

        }
    }

    /**
     * Makes the cat symbol draggable.
     *
     * @param catSymbol    The text representing the cat symbol.
     * @param catType      The type of the cat.
     * @param originalRow  The original row position of the cat.
     * @param originalCol  The original column position of the cat.
     * @author Yunmeng Zhang
     */
    private void makeCatsDraggable(Text catSymbol, char catType, int originalRow, int originalCol) {
        catSymbol.setOnMousePressed(event -> {
            // Displaying the current gameState for debugging purposes
            System.out.println(gameState[1]);
            System.out.println(gameState[2]);
            System.out.println(gameState[3]);
        });

        catSymbol.setOnMouseDragged(event -> {
            // Set the new position of catSymbol directly using the mouse position
            catSymbol.setLayoutX(event.getSceneX());
            catSymbol.setLayoutY(event.getSceneY());
        });

        catSymbol.setOnMouseReleased(event -> {
            // Since dragging might result in catSymbol being misaligned with the grid, we need to adjust it to the nearest grid
            double centerX = catSymbol.getLayoutX() + catSymbol.getBoundsInLocal().getWidth() / 2;
            double centerY = catSymbol.getLayoutY() + catSymbol.getBoundsInLocal().getHeight() / 2;

            int closestRow = (int) ((centerY - MARGIN_Y) / (squareSize + gap));
            int closestCol = (int) ((centerX - MARGIN_X) / (squareSize + gap));

            // Discard a card from the player's hand
            String newHand = discard();

            // Update the game state
            displayState(gameState[0], gameState[2], fireTileDrawn, fireTileOrientation,cardOrientation);

            // Build the movement string
            String movementString = String.format("%c%02d%02d%02d%02d", catType, originalRow, originalCol, closestRow, closestCol) + discardCard;
            System.out.println(movementString);
            System.out.println(gameState[2]);

            // Check if the cat movement is valid
            if (RaceToTheRaft.isCatMovementValid(gameState, movementString)){
                //if all cats reach the raft
                if (RaceToTheRaft.isGameOver(gameState,movementString)){
                    showGameOverDialog();
                }
                // Call moveCat method to update the game state
                gameState = RaceToTheRaft.moveCat(gameState, movementString);
                System.out.println(gameState[1]);
                System.out.println(gameState[2]);
                System.out.println(gameState[3]);
                // Update the player's hand with the new hand
                gameState[2] = newHand;
                System.out.println(gameState[2]);
                // Redraw the game state with updated information
                displayState(gameState[0], gameState[2], fireTileDrawn, fireTileOrientation,cardOrientation);
                // Make the draw fire button visible
                drawFireButton.setVisible(true);
            }
            else {
                showInvalidMoveDialog();
                // Redraw the game state with the previous information
                displayState(gameState[0], gameState[2], fireTileDrawn, fireTileOrientation,cardOrientation);
            }

        });
    }

    /**
     * Draws the game board based on the provided board state.
     *
     * @param boardstate The string representation of the board state.
     * @author Yunmeng Zhang
     */
    private void drawBoard(String boardstate) {
        String[] rows = boardstate.trim().split("\n");
        for (int i = 0; i < rows.length; i++) {
            for (int j = 0; j < rows[i].length(); j++) {
                char cell = rows[i].charAt(j);
                drawBoardSquare(j, i, cell); // draw each square
            }
        }
    }

    /**
     * Draws a single square on the game board with the specified color and symbols.
     *
     * @param col    The column index of the square.
     * @param row    The row index of the square.
     * @param state  The state of the square ('b' for blue, 'r' for red, 'y' for yellow, 'p' for purple,
     *               'g' for green, 'o' or 'w' for raft or wildcard, 'Y', 'B', 'G', 'R', 'P' for a cat of corresponding color).
     * @author Yunmeng Zhang
     */
    private void drawBoardSquare(int col, int row, char state) {
        double x = MARGIN_X + col * (squareSize + gap);
        double y = MARGIN_Y + row * (squareSize + gap);

        // Get the square with the specified color
        Rectangle square = setSquareColor(col, row, state,x,y);
        // Add the square to the interface
        root.getChildren().add(square);
        // Add symbols (cat, fire, or raft) to the square
        addSymbols(root, square.getX(), square.getY(), state, (int) square.getWidth());
    }

    /**
     * Displays the player's hand on the screen.
     *
     * @param hand            The string representation of the player's hand.
     * @param cardOrientation The orientation of the cards ('N' for north, 'E' for east, 'S' for south, 'W' for west).
     * @author Yunmeng Zhang
     */
    private void displayHand(String hand, String cardOrientation) {
        drawCardButton.setVisible(false);
        // re-create the display field
        Group handDisplay = new Group();
        handDisplay.setLayoutX(50);
        // Set layout
        handDisplay.setLayoutY(5);

        //construct the decks
        Map<Character, Deck> decks = Map.of(
                'A', new Deck(DeckType.CIRCLE, Utility.DECK_A),
                'B', new Deck(DeckType.CROSS, Utility.DECK_B),
                'C', new Deck(DeckType.SQUARE, Utility.DECK_C),
                'D', new Deck(DeckType.TRIANGLE, Utility.DECK_D)
        );

        Deck currentDeck = null;
        String deckName = null;
        // cards number count, so that the layout of cards put can be arranged
        int cardCounter = 0;
        for (char ch : hand.toCharArray()) {
            // Upper class -> Deck
            if (Character.isUpperCase(ch)) {
                currentDeck = decks.get(ch);
                deckName = String.valueOf(ch);
                // Lower class -> cards
            } else if (currentDeck != null) {
                //get the string content of each card by ID
                String initCardContent = currentDeck.getCardById(String.valueOf(ch));
                PathwayCards pathwayCard = new PathwayCards(initCardContent);
                String cardContent = pathwayCard.rotatePWCard(initCardContent,cardOrientation.charAt(0));
                System.out.println(cardContent);
                if (cardContent != null) {
                    // position of cards
                    double x = 5 + (cardCounter % 3) * (3 * 30 + 30); // 30 gap
                    double y = 5 + (cardCounter / 3) * (3 * 30 + 50); // 50 gap
                    String cardName = "Deck "+deckName +", " + "card "+String.valueOf(ch); // display card name
                    // draw 3x3 cards
                    String deckAndCard = deckName+ch;  //eg: Ao
                    drawCard(deckAndCard, handDisplay, cardContent, x, y, cardName);
                    cardCounter++;
                }
            }
        }

        // Add to root
        root.getChildren().add(handDisplay);
    }

    /**
     * Draws a card with its squares and adds it to the hand display.
     *
     * @param deckAndCard  The identifier of the deck and card.
     * @param handDisplay  The group representing the hand display.
     * @param cardContent  The string content of the card.
     * @param startX       The x-coordinate of the starting position of the card.
     * @param startY       The y-coordinate of the starting position of the card.
     * @param cardName     The name of the card.
     * @author Yunmeng Zhang
     */
    private void drawCard(String deckAndCard, Group handDisplay, String cardContent, double startX, double startY,String cardName) {
        int rows = 3;
        int cols = 3;

        // A group to hold all parts of the card
        Group cardGroup = new Group();

        for (int i = 0; i < cardContent.length(); i++) {
            int col = i % cols;
            int row = i / cols;
            char state = cardContent.charAt(i);
            // drawCardSquare(handDisplay, col, row, cell, startX, startY);
            // draw each square
            Rectangle square = setSquareColor(col, row, state, startX + col * (squareSize + gap), startY + row * (squareSize + gap));
            cardGroup.getChildren().add(square);
            //handDisplay.getChildren().add(square); // add cards to display field
        }

        // Set the position of the group
        cardGroup.setLayoutX(0);
        cardGroup.setLayoutY(0);
        // Card name
        Text cardIdText = new Text(startX, startY + rows * squareSize + gap +15, cardName); // under the card
        cardIdText.setFont(Font.font("Arial",14));
        handDisplay.getChildren().add(cardIdText);

        handDisplay.getChildren().add(cardGroup);
        addTileDragHandlers(deckAndCard, cardGroup);

    }


    /**
     * Displays the drawn fire tile on the screen.
     *
     * @param fireTileDrawn The string representation of the drawn fire tile.
     * @param orientation   The orientation of the fire tile ('N' for north, 'E' for east, 'S' for south, 'W' for west).
     * @author Yunmeng Zhang
     */
    private void displayFireTile(String fireTileDrawn, String orientation){
        drawFireButton.setVisible(false);
        // Check if the fireTileDrawn is empty
        if(fireTileDrawn == null || fireTileDrawn.isEmpty()) {
            System.out.println("No fire tile to draw.");
            return; // Exit the method if there is no data to process
        }

        // Re-create the display field
        Group tileDisplay = new Group();
        // Set layout
        tileDisplay.setLayoutX(50);
        tileDisplay.setLayoutY(270);

        FireTile fireTile = new FireTile(fireTileDrawn);
        //String tileLayout = fireTile.getTileShapeById().substring(1);//get the string content of each card by ID
        List<Location> layout = fireTile.rotateCoordinates(orientation);
        String tileLayout = convertLocationsToString(layout);

        System.out.println("tile layout: " + tileLayout);
        if (tileLayout != null) {
            // Position of cards
            double x = 0; // 30 gap
            double y = 0; // 50 gap
            String cardName =  "fire tile "+fireTileDrawn; // display card name
            System.out.println("card name is:" + cardName);
            // Draw fire tile
            drawFireTile(fireTileDrawn,tileDisplay, tileLayout, cardName);
        }
        root.getChildren().add(tileDisplay);
    }


    /**
     * Draws the layout of a fire tile.
     *
     * @param fireTileDrawn The string representation of the drawn fire tile.
     * @param tileDisplay   The group representing the display area for the fire tile.
     * @param cardContent   The string content representing the layout of the fire tile.
     * @param cardName      The name of the fire tile.
     * @author Yunmeng Zhang
     */
    private void drawFireTile(String fireTileDrawn, Group tileDisplay, String cardContent, String cardName) {
        // Create a new group to act as the container for the entire tile
        Group tileGroup = new Group();
        // Parse the card content string, where every two characters represent the row and column coordinates of a square
        for (int i = 0; i < cardContent.length(); i += 2) {
            int row = Character.getNumericValue(cardContent.charAt(i));
            int col = Character.getNumericValue(cardContent.charAt(i + 1));

            double x = col * (squareSize + gap);
            double y = row * (squareSize + gap);

            Rectangle square = new Rectangle(x, y, squareSize, squareSize);
            // Set color to gray
            square.setFill(Color.GRAY);
            // Add each square to the group
            tileGroup.getChildren().add(square);
        }
        // Set the position of the group
        tileGroup.setLayoutX(0);
        tileGroup.setLayoutY(0);
        // Add drag handlers to the entire group
        addTileDragHandlers(fireTileDrawn, tileGroup);

        // Add the entire group to the display area
        tileDisplay.getChildren().add(tileGroup);

        // Display the card name
        // Display the name below the tiles
        Text cardIdText = new Text(0,  105, cardName);
        cardIdText.setFont(Font.font("Arial", FontPosture.REGULAR, 14));
        tileDisplay.getChildren().add(cardIdText);
    }

    /**
     * Adds drag and drop handlers for a tile group.
     *
     * @param toBePlaced The identifier of the tile to be placed.
     * @param tileGroup  The group representing the tile.
     * @author Yunmeng Zhang
     */
    private void addTileDragHandlers(String toBePlaced, Group tileGroup) {
        final Point2D[] dragDelta = new Point2D[1];
        AtomicReference<String> placementString = new AtomicReference<>("");
        tileGroup.setOnMousePressed(event -> {
            clickCount = 0;
            clickCountCard = 0;
            // Record the offset when pressed
            dragDelta[0] = new Point2D(event.getSceneX() - tileGroup.getLayoutX(), event.getSceneY() - tileGroup.getLayoutY());

        });

        tileGroup.setOnMouseDragged(event -> {
            // Update position
            tileGroup.setLayoutX(event.getSceneX() - dragDelta[0].getX());
            tileGroup.setLayoutY(event.getSceneY() - dragDelta[0].getY());
        });

        tileGroup.setOnMouseReleased(event -> {
            if (toBePlaced.length() == 1){
                // Calculate the grid indices based on the mouse release position
                int closestCol = (int) ((tileGroup.getLayoutX() - MARGIN_X + squareSize / 2+50) / (squareSize + gap));
                int closestRow = (int) ((tileGroup.getLayoutY() + squareSize / 2+220) / (squareSize + gap));


                // Recalculate new position, including gaps
                double newX = MARGIN_X + closestCol * (squareSize + gap);
                double newY = MARGIN_Y + closestRow * (squareSize + gap);

                tileGroup.setLayoutX(newX);
                tileGroup.setLayoutY(newY);

                //placement of fire tile

                // e.g. "a1200FN"
                placementString.set(toBePlaced.charAt(0) + String.format("%02d%02d", closestRow, closestCol) + fireTileOrientation);
                System.out.println(placementString.get());
                clickCount = 0;
                rotateFireButton.setVisible(false);

                // Call the applyPlacement method to update the game state
                if (RaceToTheRaft.isPlacementValid(gameState,placementString.get())){
                    if (RaceToTheRaft.isGameOver(gameState, placementString.get())){
                        System.out.println("game over");
                        showGameOverDialog();
                    }
                    gameState = RaceToTheRaft.applyPlacement(gameState, placementString.get());
                    // Refresh the game board display
                    displayState(gameState[0], gameState[2], "", "FN",cardOrientation);
                    // Re-show or activate the 'Draw Fire' button
                    drawFireButton.setVisible(true);
                    drawCardButton.setVisible(true);
                    inputA.setVisible(true);
                    inputB.setVisible(true);
                    inputC.setVisible(true);
                    inputD.setVisible(true);

                }
                else {
                    fireTileDrawn = toBePlaced;
                    showInvalidPlaceDialog();
                    // Re-display the game board before the move
                    displayState(gameState[0], gameState[2], fireTileDrawn,"FN",cardOrientation);
                }

            }
            //placement of pathway card
            if (toBePlaced.length() == 2){
                // Calculate the grid indices based on the mouse release position
                int closestCol = (int) Math.round((tileGroup.getLayoutX() - MARGIN_X+50) / (squareSize + gap));
                int closestRow = (int) Math.round((tileGroup.getLayoutY() - MARGIN_Y) / (squareSize + gap));

                // Recalculate new position, including gaps
                double newX = MARGIN_X + closestCol * (squareSize + gap);
                double newY = MARGIN_Y + closestRow * (squareSize + gap);

                // Update the position of tileGroup to the nearest grid point
                tileGroup.setLayoutX(newX);
                tileGroup.setLayoutY(newY);

                placementString.set(toBePlaced.substring(0, 2) + String.format("%02d%02d", closestRow, closestCol) + cardOrientation);
                System.out.println(placementString.get());

                // Call the applyPlacement method to update the game state
                if (RaceToTheRaft.isPlacementValid(gameState,placementString.get())){
                    gameState = RaceToTheRaft.applyPlacement(gameState, placementString.get());
                    // Refresh the game board display
                    displayState(gameState[0], gameState[2], "", "FN",cardOrientation);
                    clickCountCard = 0;

                    // Re-show or activate the 'Draw Fire' button
                    drawFireButton.setVisible(true);
                    drawCardButton.setVisible(true);
                    inputA.setVisible(true);
                    inputB.setVisible(true);
                    inputC.setVisible(true);
                    inputD.setVisible(true);
                }
                else {
                    showInvalidPlaceDialog();
                    cardOrientation = "N";
                    fireTileDrawn = "FN";
                    // Re-display the game board before the move
                    displayState(gameState[0], gameState[2], fireTileDrawn,"FN",cardOrientation);
                }
            }
        });
    }


    /**
     * Generate controls for Viewer
     * @author Yunmeng Zhang
     */
    private void makeControls() {
        // Difficulty input field
        difficultyInput = new TextField();
        difficultyInput.setPromptText("Enter difficulty (0-5)");
        // Start game button
        startGameButton = new Button("Start Game");
        startGameButton.setOnAction(e -> startGame());
        // Difficulty label
        Label difficultyLabel = new Label("Difficulty:");
        HBox inputBox = new HBox(10, difficultyLabel, difficultyInput, startGameButton);
        inputBox.setLayoutX(20);
        inputBox.setLayoutY(500);
        root.getChildren().add(inputBox);

        // Game rules button
        rulesButton = new Button("Game Rules");
        rulesButton.setPrefWidth(200);
        rulesButton.setOnAction((ActionEvent) -> {
            showGameRulesAlert();
        });
        root.getChildren().add(rulesButton);

        // Draw fire button
        drawFireButton = new Button("Draw Fire");
        drawFireButton.setOnAction(e -> drawFire());
        HBox controlPanel = new HBox(10);
        controlPanel.getChildren().add(drawFireButton);
        controlPanel.setLayoutX(20);
        controlPanel.setLayoutY(550);
        root.getChildren().add(controlPanel);

        // Card input text fields
        inputA = new TextField();
        inputA.setPromptText("A");
        inputA.setPrefWidth(40);  // Set width
        inputB = new TextField();
        inputB.setPromptText("B");
        inputB.setPrefWidth(40);
        inputC = new TextField();
        inputC.setPromptText("C");
        inputC.setPrefWidth(40);
        inputD = new TextField();
        inputD.setPromptText("D");
        inputD.setPrefWidth(40);

        HBox cardInputs = new HBox(3, inputA, inputB, inputC, inputD);
        cardInputs.setLayoutX(170);
        cardInputs.setLayoutY(550);  // Adjust according to your layout needs

        root.getChildren().addAll(cardInputs);

        // Draw pathway card button
        drawCardButton = new Button("Draw Pathway Card");
        drawCardButton.setOnAction(e -> drawPathwayCard());
        HBox controlPanel2 = new HBox(10);
        controlPanel2.getChildren().add(drawCardButton);
        controlPanel2.setLayoutX(350);
        controlPanel2.setLayoutY(550);
        root.getChildren().add(controlPanel2);

        // Rotate and flip fire button
        rotateFireButton = new Button("Rotate and Flip");
        rotateFireButton.setOnAction(e -> rotateFlipFire());
        HBox fireRotate = new HBox(10);
        fireRotate.getChildren().add(rotateFireButton);
        fireRotate.setLayoutX(200);
        fireRotate.setLayoutY(350);
        root.getChildren().add(fireRotate);

        // Discard request input field and button
        discardRequest = new TextField();
        discardRequest.setPrefWidth(40);
        HBox discardInput = new HBox(10);
        discardInput.getChildren().add(discardRequest);
        discardInput.setLayoutX(400);
        discardInput.setLayoutY(30);
        root.getChildren().add(discardInput);

        discardButton = new Button("discard");
        discardButton.setOnAction(e -> discard());
        HBox discardPanel = new HBox(10);
        discardPanel.getChildren().add(discardButton);
        discardPanel.setLayoutX(400);
        discardPanel.setLayoutY(50);
        root.getChildren().add(discardPanel);

        // Rotate card button
        rotateCardButton = new Button("rotate card");
        rotateCardButton.setOnAction(e -> rotateCard());
        HBox rotateCardPanel = new HBox(10);
        rotateCardPanel.getChildren().add(rotateCardButton);
        rotateCardPanel.setLayoutX(400);
        rotateCardPanel.setLayoutY(80);
        root.getChildren().add(rotateCardPanel);

        // Add to the root if not already added
        root.getChildren().add(controls);
    }

    /**
     * Rotate card
     * @author Yunmeng Zhang
     */

    private void rotateCard(){
        clickCountCard++;
        if (clickCountCard % 4 == 1){
            cardOrientation = "E";
        }
        else if (clickCountCard % 4 == 2){
            cardOrientation = "S";
        }
        else if (clickCountCard % 4 == 3){
            cardOrientation = "W";
        }
        else if (clickCountCard % 4 == 0){
            cardOrientation = "N";
        }
        newFireTile = "";
        displayState(gameState[0], gameState[2], newFireTile, fireTileOrientation,cardOrientation);
    }

    /**
     * Rotates and flips the fire tile.
     * @author Yunmeng Zhang
     */
    private void rotateFlipFire(){
        clickCount++; // Increment the counter with each click
        // Determine the orientation based on the number of clicks
        if (clickCount % 8 == 1) {
            fireTileOrientation = "TN";
        }
        else if (clickCount%8==2){
            fireTileOrientation = "FE";
        }
        else if (clickCount%8==3){
            fireTileOrientation = "TE";
        }
        else if (clickCount%8==4){
            fireTileOrientation = "FS";
        }
        else if (clickCount%8==5){
            fireTileOrientation = "TS";
        }
        else if (clickCount%8==6){
            fireTileOrientation = "FW";
        }
        else if (clickCount%8==2){
            fireTileOrientation = "TW";
        }
        else if (clickCount%8==0){
            fireTileOrientation = "FN";
        }
        // Update the game state to display the rotated and flipped fire tile
        displayState(gameState[0], gameState[2], newFireTile, fireTileOrientation,cardOrientation);
    }


    /**
     * Draws a fire tile and updates the game state.
     * @author Yunmeng Zhang
     */
    private void drawFire() {
        // Call the drawFireTile method and update the fireTileDrawn
        newFireTile = RaceToTheRaft.drawFireTile(gameState);
        gameState[4] = gameState[4].replace(newFireTile,""); // Update the game state with the new fire tile, if needed

        displayState(gameState[0], gameState[2], newFireTile, "FN",cardOrientation);
        // Hide unnecessary controls after drawing fire tile
        inputA.setVisible(false);
        inputB.setVisible(false);
        inputC.setVisible(false);
        inputD.setVisible(false);
        discardButton.setVisible(false);
        discardRequest.setVisible(false);

        // Show the rotate fire button
        rotateFireButton.setVisible(true);
        // Set fire tile orientation to default
        fireTileOrientation = "FN";
    }


    /**
     * Discards a card from the player's hand.
     *
     * @return String representing the updated hand after discarding the card
     * @author Yunmeng Zhang
     */
    private String discard(){
        discardCard = discardRequest.getText();
        if (discardCard.length()==2){
            String deck = String.valueOf(discardCard.charAt(0));
            String cardID = String.valueOf(discardCard.charAt(1));
            StringBuilder newState = new StringBuilder(gameState[2]);
            int positionOfDeck = newState.indexOf(deck);
            String beforeDeck = gameState[2].substring(0,positionOfDeck+1);
            String inDeck = "";
            int positionOfNextDeck = 0;
            String afterDeck = "";
            if (deck.equals("A")){
                positionOfNextDeck = newState.indexOf("B");
                inDeck = gameState[2].substring(positionOfDeck+1, positionOfNextDeck);
                afterDeck = gameState[2].substring(positionOfNextDeck);
            }
            else if (deck.equals("B")){
                positionOfNextDeck = newState.indexOf("C");
                inDeck = gameState[2].substring(positionOfDeck+1, positionOfNextDeck);
                afterDeck = gameState[2].substring(positionOfNextDeck);
            }
            else if (deck.equals("C")){
                positionOfNextDeck = newState.indexOf("D");
                inDeck = gameState[2].substring(positionOfDeck+1, positionOfNextDeck);
                afterDeck = gameState[2].substring(positionOfNextDeck);
            }
            else if (deck.equals("D")){
                inDeck = gameState[2].substring(positionOfDeck+1);
            }
            return beforeDeck+inDeck.replace(cardID,"")+afterDeck;
        }
        else if (discardCard.length()==4){
            String deck1 = String.valueOf(discardCard.charAt(0));
            String cardID1 = String.valueOf(discardCard.charAt(1));
            StringBuilder newState = new StringBuilder(gameState[2]);
            int positionOfDeck = newState.indexOf(deck1);
            String beforeDeck = gameState[2].substring(0,positionOfDeck+1);
            String inDeck = "";
            int positionOfNextDeck = 0;
            String afterDeck = "";
            if (deck1.equals("A")){
                positionOfNextDeck = newState.indexOf("B");
                inDeck = gameState[2].substring(positionOfDeck+1, positionOfNextDeck);
                afterDeck = gameState[2].substring(positionOfNextDeck);
            }
            else if (deck1.equals("B")){
                positionOfNextDeck = newState.indexOf("C");
                inDeck = gameState[2].substring(positionOfDeck+1, positionOfNextDeck);
                afterDeck = gameState[2].substring(positionOfNextDeck);
            }
            else if (deck1.equals("C")){
                positionOfNextDeck = newState.indexOf("D");
                inDeck = gameState[2].substring(positionOfDeck+1, positionOfNextDeck);
                afterDeck = gameState[2].substring(positionOfNextDeck);
            }
            else if (deck1.equals("D")){
                inDeck = gameState[2].substring(positionOfDeck+1);
            }
            String temp =  beforeDeck+inDeck.replace(cardID1,"")+afterDeck;

            String deck2 = String.valueOf(discardCard.charAt(2));
            String cardID2 = String.valueOf(discardCard.charAt(3));
            StringBuilder newState2 = new StringBuilder(temp);
            int positionOfDeck2 = newState.indexOf(deck2);
            String beforeDeck2 = temp.substring(0,positionOfDeck+1);
            String inDeck2 = "";
            int positionOfNextDeck2 = 0;
            String afterDeck2 = "";
            if (deck2.equals("A")){
                positionOfNextDeck2 = newState.indexOf("B");
                inDeck2 = temp.substring(positionOfDeck2+1, positionOfNextDeck2);
                afterDeck2 = temp.substring(positionOfNextDeck2);
            }
            else if (deck2.equals("B")){
                positionOfNextDeck2 = newState.indexOf("C");
                inDeck2 = temp.substring(positionOfDeck2+1, positionOfNextDeck2);
                afterDeck2 = temp.substring(positionOfNextDeck2);
            }
            else if (deck2.equals("C")){
                positionOfNextDeck2 = newState.indexOf("D");
                inDeck2 = temp.substring(positionOfDeck2+1, positionOfNextDeck2);
                afterDeck2 = temp.substring(positionOfNextDeck2);
            }
            else if (deck2.equals("D")){
                inDeck2 = temp.substring(positionOfDeck2+1);
            }
            return beforeDeck2+inDeck2.replace(cardID2,"")+afterDeck2;
        }
        return null;
    }

    /**
     * Draws and updates pathway cards in hand.
     * @author Yunmeng Zhang
     */
    private void drawPathwayCard() {
        // Read inputs and construct the draw request
        String request = "";
        request += constructRequestPart('A', inputA.getText());
        request += constructRequestPart('B', inputB.getText());
        request += constructRequestPart('C', inputC.getText());
        request += constructRequestPart('D', inputD.getText());
        gameState = RaceToTheRaft.drawHand(gameState, request);

        // Display the updated game state
        displayState(gameState[0], gameState[2],fireTileDrawn, "",cardOrientation);

        // Hide input fields and buttons after drawing
        inputA.setVisible(false);
        inputB.setVisible(false);
        inputC.setVisible(false);
        inputD.setVisible(false);
        rotateCardButton.setVisible(true);
        discardRequest.setVisible(true);
        discardButton.setVisible(true);

        // Clear the exhausted cat and reset click count and card orientation
        gameState[3] = ""; //clear the exhausted cat
        clickCountCard = 0;
        cardOrientation = "N";
    }

    /**
     * Generates a draw card request string from 4 inputs.
     *
     * @param deckType The type of deck (A, B, C, or D).
     * @param input The input value representing the number of cards to draw from the deck.
     * @return The constructed draw card request string for the specified deck type and input value.
     * @author Yunmeng Zhang
     */
    private String constructRequestPart(char deckType, String input) {
        try {
            int num = Integer.parseInt(input);
            if (num > 0) {
                return deckType + input;
            }
        } catch (NumberFormatException e) {
            // Handle invalid input or empty field
        }
        return "";  // Return empty string if input is zero or invalid
    }


    /**
     * Converts a list of locations to a string representation.
     *
     * @param locations The list of locations to be converted.
     * @return The string representation of the locations, where each location is represented by its row and column.
     * @author Yunmeng Zhang
     */
    public static String convertLocationsToString(List<Location> locations) {
        StringBuilder builder = new StringBuilder();
        for (Location location : locations) {
            builder.append(location.getRow());
            builder.append(location.getColumn());
        }
        return builder.toString();
    }


    /**
     * Displays a pop-up window indicating that the game is over.
     * @author Yunmeng Zhang
     */
    public void showGameOverDialog() {
        // Create an alert pop-up window
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Game Over");// Set the title of the alert
        alert.setHeaderText(null); // Set the header text to null
        alert.setContentText("Game Over!");
        alert.showAndWait();// Display the alert and wait for user interaction
    }



    /**
     * Displays a pop-up window indicating that the movement of the cat is invalid.
     * @author Yunmeng Zhang
     */
    public void showInvalidMoveDialog() {
        // Create an alert pop-up window
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Invalid Movement");
        alert.setHeaderText(null);
        alert.setContentText("cat cannot move without enough discarding or there isn't a path with the same color");
        alert.showAndWait();
    }

    /**
     * Displays a pop-up window indicating that the placement of a card or fire tile is invalid.
     * @author Yunmeng Zhang
     */
    public void showInvalidPlaceDialog() {
        // Display the alert pop-up window on the JavaFX thread
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Invalid Placement");
        alert.setHeaderText(null);
        // Set the content text to indicate the reason for the invalid placement
        alert.setContentText("card or fire tile cannot be placed here!");
        alert.showAndWait();
    }


    /**
     * Displays an alert dialog box with the game rules when triggered.
     * @author Tengyi Zhang
     */
    private void showGameRulesAlert() {
        // Create a new information alert
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Game Rules");
        alert.setHeaderText(null);

        // Create labels for different sections of the game rules
        Label headingLabel = new Label("Game Rules");
        headingLabel.setStyle("-fx-font-weight: bold;");

        Label rule1Label = new Label("There are three steps to each round in Race to the Raft:");
        Label rule2Label = new Label("1. Draw pathway cards");
        Label rule21Label = new Label("\tThe player draws six pathway cards from among the four decks.\n " +
                "\tThe player can draw these cards in any combination, from any decks they wish.\n" +
                "\tHowever, you are not allowed to look at the cards you have drawn until you have drawn all six " +
                "cards.\n" +
                "\tDecks are not replaced in this game. If a deck has no cards, you can no longer draw from that deck" +
                ".\n");
        Label rule3Label = new Label("2. Play or discard pathway cards to make pathways or move cats");
        Label rule31Label = new Label("\tThe player plays cards to move cats, or create paths to the island. All " +
                "cards " +
                "in their hand must be played before " +
                "the next step.");
        Label rule32Label = new Label("\tAction: Add Pathway\n" +
                "\t\tA player can place one pathway card from their hand onto the island. This placement must be " +
                "consistent with the placement rules.\n" +
                "\t\tAfterwards, the player places a random fire tile on the island\n" +
                "\tPlacing Cards and Fire Tiles\n" +
                "\t\tPathway cards and fire tiles cannot have any of their squares hanging off the edge of the island" +
                ".\n" +
                "\t\tThe squares of pathway cards and fire tiles must line up with the squares of the island.\n" +
                "\t\tPathway cards and fire tiles can be rotated in any orientation. Fire tiles can also be flipped, " +
                "both horizontally and vertically.\n" +
                "\t\tPathway cards and fire tiles can fully or partially overlap other pathway cards.\n" +
                "\t\tPathway cards and fire tiles cannot overlap any squares with fire or belonging to a " +
                "raft card or with cats on them, also cannot be placed under other pathway cards or fire tiles.\n" +
                "\t\tFire tiles must be placed next to existing fire.\n" +
                "\t\tPathway cards do not have any adjacency requirements like fire tiles.\n" +
                "\tAction: Move a Cat\n" +
                "\t\tDiscard one card from your hand, or two cards if the cat is exhausted.\n" +
                "\t\tMove the cat along any number of orthogonally adjacent* squares of the same colour as the cat.\n" +
                "\t\tWhen you have finished moving the cat, the cat becomes exhausted for the rest of this round.\n" +
                "\t\tCats can move through squares occupied by other cats, but no two cats can occupy the same square" +
                ".\n");
        Label rule4Label = new Label("3. Rest.");
        Label rule41Label = new Label("\tAll cards must be played from the player's hand. Once all cards have been " +
                "played, any exhausted cats are no longer exhausted and the player starts a new round, returning to " +
                "Step 1.");

        Label rule5Label = new Label("Winning\n" +
                "\tYou win if all the cats are on the raft card.\n" +
                "\n" +
                "Losing\n" +
                "\tThere are several ways that the game can be lost:\n" +
                "\t\tIf a player cannot place a fire tile on the island in a valid way according to the game rules;\n" +
                "\t\tIf there is no way for a cat to reach the raft;\n" +
                "\t\tIf a there are no more fire tiles remaining AND the player is required to draw one; or\n" +
                "\t\tIf there are no more pathway cards in any of the decks AND the player is required to draw a card" +
                ".\n" +
                "\n" +
                "\n");


        VBox content = new VBox();
        content.getChildren().addAll(headingLabel, rule1Label, rule2Label, rule21Label, rule3Label, rule31Label,
                rule32Label,
                rule4Label, rule41Label,
                rule5Label);
        // Set the content of the alert dialog to the VBox
        alert.getDialogPane().setContent(content);

        // Display the alert dialog and wait for user interaction
        alert.showAndWait();
    }
}
