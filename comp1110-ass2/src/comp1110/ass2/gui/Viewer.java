package comp1110.ass2.gui;

import comp1110.ass2.skeleton.Colour;
import comp1110.ass2.skeleton.Deck;
import comp1110.ass2.skeleton.DeckType;
import comp1110.ass2.Utility;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.util.Map;

/**
 * @author Yunmeng Zhang
 * **/
public class Viewer {

    private final Group root = new Group();
    private static final int VIEWER_WIDTH = 1100;
    private static final int VIEWER_HEIGHT = 650;
    private static final int MARGIN_X = 20;

    private final Group controls = new Group();
    private TextArea handTextField;
    private TextArea boardTextField;

    /**
     * Draw the given board and hand in the window, removing any previously drawn boards/hands.
     *
     * @param boardstate newline separated string representing each row of the board (the board string, see the STRING-REPRESENTATION.md for more details
     * @param hand A string representing the cards in a player's hand (the hand string, see the STRING-REPRESENTATION.md for more details)
     *
     */
        void displayState(String boardstate, String hand) {
            // removing any previously drawn boards/hands.
            root.getChildren().clear();
            makeControls(); // re-create Controls

            // draw board
            drawBoard(boardstate);

            // draw cards in hand
            displayHand(hand);

            // FIXME TASK 4
    }

    private void drawBoard(String boardstate) {
        String[] rows = boardstate.trim().split("\n");
        for (int i = 0; i < rows.length; i++) {
            for (int j = 0; j < rows[i].length(); j++) {
                char cell = rows[i].charAt(j);
                drawSquare(j, i, cell); // draw each square
            }
        }
    }

    private void drawSquare(int col, int row, char state) {
        // set the color
        Color color = switch (Character.toLowerCase(state)) {
            case 'b' -> Color.BLUE;
            case 'r' -> Color.RED;
            case 'y' -> Color.YELLOW;
            case 'p' -> Color.PURPLE;
            case 'g' -> Color.GREEN;
            case 'o', 'w' -> Color.LIGHTGRAY; // raft and wildcard
            default -> Color.GRAY;
        };

        // the size of square is 30*30
        final int squareSize = 30;
        // gap between squares
        final int gap = 2;
        // layout
        double x = MARGIN_X + col * (squareSize + gap)+450;
        double y = 50 + row * (squareSize + gap);

        Rectangle square = new Rectangle(x, y, squareSize, squareSize);
        square.setFill(color);
        root.getChildren().add(square);


        // examine cat with uppercase
        if (Character.isUpperCase(state)) {
            // create cat symbol
            Text catSymbol = new Text(x, y + squareSize / 1.2, "🐱");
            catSymbol.setFont(Font.font("Verdana", squareSize)); // the size of cat symbol
            root.getChildren().add(catSymbol);
        }
        if (state == 'f'){
            //fire symbol
            Text fireSymbol = new Text(x + squareSize / 4, y + squareSize / 1.5, "🔥");
            fireSymbol.setFont(Font.font("Verdana", squareSize/2));
            fireSymbol.setFill(Color.BROWN);
            root.getChildren().add(fireSymbol);
        }
        if (state =='o'||state =='w'){
            //raft symbol
            Text raftSymbol = new Text(x, y+squareSize/1.2,"⛵");
            raftSymbol.setFont(Font.font("Verdana",squareSize));
            root.getChildren().add(raftSymbol);
        }
    }

    private void displayHand(String hand) {
        // re-create the display field
        Group handDisplay = new Group();
        handDisplay.setLayoutX(50);
        handDisplay.setLayoutY(50); // layout

        //construct the decks
        Map<Character, Deck> decks = Map.of(
                'A', new Deck(DeckType.CIRCLE, Utility.DECK_A),
                'B', new Deck(DeckType.CROSS, Utility.DECK_B),
                'C', new Deck(DeckType.SQUARE, Utility.DECK_C),
                'D', new Deck(DeckType.TRIANGLE, Utility.DECK_D)
        );

        Deck currentDeck = null;
        String deckName = null;
        int cardCounter = 0; // cards number count, so that the layout of cards put can be arranged
        for (char ch : hand.toCharArray()) {
            if (Character.isUpperCase(ch)) { // Upper class -> Deck
                currentDeck = decks.get(ch);
                deckName = String.valueOf(ch);
            } else if (currentDeck != null) { // Lower class -> cards
                String cardContent = currentDeck.getCardById(String.valueOf(ch));//get the string content of each card by ID
                if (cardContent != null) {
                    // position of cards
                    double x = 5 + (cardCounter % 3) * (3 * 30 + 30); // 30 gap
                    double y = 5 + (cardCounter / 3) * (3 * 30 + 50); // 50 gap
                    String cardName = "Deck "+deckName +", " + "card "+String.valueOf(ch); // display card name
                    // draw 3x3 cards
                    drawCard(handDisplay, cardContent, x, y, cardName);
                    cardCounter++;
                }
            }
        }

        // add to root
        root.getChildren().add(handDisplay);
    }

    private void drawCard(Group handDisplay, String cardContent, double startX, double startY,String cardName) {
        int rows = 3;
        int cols = 3;
        final int squareSize = 30; // size of each square
        final int gap = 10; // gap between squares
        for (int i = 0; i < cardContent.length(); i++) {
            int col = i % cols;
            int row = i / cols;
            char cell = cardContent.charAt(i);
            drawCardSquare(handDisplay, col, row, cell, startX, startY); // draw each square
        }
        // card name
        Text cardIdText = new Text(startX, startY + rows * squareSize + gap +10, cardName); // under the card
        cardIdText.setFont(Font.font("Arial",14));
        handDisplay.getChildren().add(cardIdText);

    }

    private void drawCardSquare(Group handDisplay, int col, int row, char state, double startX, double startY) {
        // set color
        Color color = switch (Character.toLowerCase(state)) {
            case 'b' -> Color.BLUE;
            case 'r' -> Color.RED;
            case 'y' -> Color.YELLOW;
            case 'p' -> Color.PURPLE;
            case 'g' -> Color.GREEN;
            default -> Color.GRAY;
        };

        // size of each square is 30*30
        final int squareSize = 30;
        // gap
        final int gap = 2;
        // layout
        double x = startX + col * (squareSize + gap);
        double y = startY + row * (squareSize + gap);

        Rectangle square = new Rectangle(x, y, squareSize, squareSize);
        square.setFill(color);
        handDisplay.getChildren().add(square); // add cards to display field
    }





        /**
         * Generate controls for Viewer
         */
    private void makeControls() {
        Label playerLabel = new Label("Cards in hand:");
        handTextField = new TextArea();
        handTextField.setPrefWidth(100);
        Label boardLabel = new Label("Board State:");
        boardTextField = new TextArea();
        boardTextField.setPrefWidth(100);
        Button button = refreshButton();
        button.setLayoutY(VIEWER_HEIGHT - 250);
        button.setLayoutX(MARGIN_X);
        HBox fields = new HBox();
        fields.getChildren().addAll(handTextField, boardTextField);
        fields.setSpacing(20);
        fields.setLayoutX(MARGIN_X);
        fields.setLayoutY(VIEWER_HEIGHT - 200);
        HBox labels = new HBox();
        labels.getChildren().addAll(playerLabel, boardLabel);
        labels.setSpacing(45);
        labels.setLayoutX(MARGIN_X);
        labels.setLayoutY(VIEWER_HEIGHT - 220);
        controls.getChildren().addAll(fields, labels, button);
    }

    /**
     * Create refresh button. Upon pressing, capture the textFields and call displayState
     * @return the created button
     */
    private Button refreshButton() {
        Button button = new Button("Refresh");
        button.setOnAction(e -> {
            String boardText = boardTextField.getText();
            String handCards = handTextField.getText();
            displayState(boardText, handCards);
        });
        return button;
    }

//    @Override
//    public void start(Stage stage) throws Exception {
//        stage.setTitle("Race to the Raft Viewer");
//        Scene scene = new Scene(root, VIEWER_WIDTH, VIEWER_HEIGHT);
//        makeControls();
//        displayState("fffffffffrrfffffff\n" +
//                "fffffffffrRfffffff\n" +
//                "fffffffffrrfffffff\n" +
//                "fffgffyrgpygyrygbr\n" +
//                "fffgGfggyygprbprpg\n" +
//                "fffgggbgprbpygbpyb\n" +
//                "ffffffbpbpgrbrrbgy\n" +
//                "ffffffgygybpgygprb\n" +
//                "ffffffbrrrybgygybg\n" +
//                "ffffffgpbbyrprgbbp\n" +
//                "ffffffbyrbpybgpryg\n" +
//                "ffffffbyrbpybgpryg\n" +
//                "ffffffpgyrggrbgyby\n" +
//                "fffffybgbpryybpgyp\n" +
//                "ffffYyybpgbprygrow\n" +
//                "fffyyyyryygbygybww\n","AfhkDahw");
//        root.getChildren().add(controls);
//        makeControls();
//        stage.setScene(scene);
//        stage.show();
//
//    }
}
