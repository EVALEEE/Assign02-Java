package comp1110.ass2.skeleton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static comp1110.ass2.skeleton.FireTile.*;
//import static comp1110.ass2.skeleton.PathwayCards.isCardOverLapping;
//import static comp1110.ass2.skeleton.PathwayCards.proccessPWCardRotate;

/**
 * @author Tengyi Zhang, Jialin Li
 **/
public class Board {
    private int height; // Board height
    private int width; // Board width
    private String[] lines; // Represent each lines of gamestate, which related to width.
    private String boardString;
    private State[][] squareState; // Represent state of square
    private char[][] boardArray;
    public static String fireFinal;//reflect fire's shape
    static String cardFinal;//reflecct details of 3*3 card e.g.rrybrybbyb
    static char[][] boardCharArray;
    static int rowFire;
    static int colFire;

    public void setLines(String[] lines) {
        this.lines = lines;
    }

    public String[] getLines() {
        return lines;
    }

    public Board(char[][] boardArray) {
        this.boardArray = boardArray;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public Board() {
    }

    /**
     * Finds all positions on the board where cats are located.
     * Cats are represented by uppercase letters in the board array.
     *
     * @return A list of locations where each location represents the position of a cat on the board.
     * @author Yunmeng Zhang
     */
    public List<Location> findCatPositions() {
        List<Location> catLocations = new ArrayList<>();
        for (int i = 0; i < boardArray.length; i++) {
            for (int j = 0; j < boardArray[i].length; j++) {
                if (Character.isUpperCase(boardArray[i][j])) {
                    catLocations.add(new Location(i, j));
                }
            }
        }
        return catLocations;
    }

    /**
     * Extracts a sub-array from the main board array starting from the specified row.
     *
     * @param startRow The starting row index from which to begin the sub-array.
     * @return A 2D character array representing the subsection of the board.
     * @author Yunmeng Zhang
     */
    public char[][] getSubBoard(int startRow) {
        // Calculate the number of rows in the sub-board, which is the difference
        // between the total number of rows in the original board and the startRow.
        int numRows = boardArray.length - startRow;
        char[][] subBoard = new char[numRows][];

        for (int i = 0; i < numRows; i++) {
            subBoard[i] = new char[boardArray[startRow + i].length];
            System.arraycopy(boardArray[startRow + i], 0, subBoard[i], 0, boardArray[startRow + i].length);
        }

        return subBoard;
    }


    /**
     * Identifies all rows in the game board that do not have enough space for placing a pathway card.
     * A row is considered to have no space if it does not meet the criteria defined in the checkRowForNonF method.
     *
     * @return A list of row indices, using 1-based numbering, that do not have space for a pathway card.
     * @author Yunmeng Zhang
     */
    public List<Integer> rowsWithNoSpaceForPathwayCard() {
        //find the row where there is no place for pathway card
        //if any row of the board has more than three consecutive non-f states, there is a place for pathway card
        // Find all rows that do not have three consecutive non-'f' cells
        List<Integer> rowsWithoutSpace = new ArrayList<>();
        for (int i = 0; i < boardArray.length; i++) {
            if (!checkRowForNonF(boardArray[i])) {
                rowsWithoutSpace.add(i + 1); // Add 1 to index to use 1-based row numbering
            }
        }
        return rowsWithoutSpace; // Return the list of rows that do not have space for a pathway card
    }

    /**
     * Checks if a given row has at least three consecutive non-'f' characters.
     * This is typically used to check if there's enough space to place a pathway card,
     * which requires three consecutive non-'f' cells to fit on the board.
     *
     * @param row A character array representing a single row of the game board.
     * @return true if there are at least three consecutive non-'f' cells, false otherwise.
     * @author Yunmeng Zhang
     */
    private boolean checkRowForNonF(char[] row) {
        int consecutiveCount = 0;
        for (char c : row) {
            if (c != 'f') {
                consecutiveCount++;
                if (consecutiveCount == 3) {
                    return true; // Found three consecutive non-'f' cells, enough space for a pathway card
                }
            } else {
                consecutiveCount = 0; // Reset count if 'f' is found
            }
        }
        return false; // No three consecutive non-'f' cells found in this row
    }


    public Board(String boardString) {
        this.boardString = boardString;
        boardString = boardString.replaceAll("\n", "\n|");
        // Split the boardString into lines using "|" as the delimiter, to avoid accidentally delete '\n'
        this.lines = boardString.split("\\|");
        // The board height is the number of lines
        this.height = lines.length;
        // The board width is the length of the line
        this.width = lines[0].length() - 1;
        // Create a new state.
        squareState = new State[height][width];
    }

    //Get the state of each square in the board
    /**
     * Processes each line of the board representation to create a state matrix that holds
     * the color and location of each square. This state matrix is useful for game logic processing.
     * @author Tengyi Zhang
     */
    public void getSquareBoard() {
        int row = 0;
        for (String line : lines) {
            for (int col = 0; col < line.length(); col++) {
                char c = line.charAt(col);
                if (c == '\n') {
                    break;
                }
                if (c == 'R' || c == 'B' || c == 'Y' || c == 'P' || c == 'G') {
                    c = Character.toLowerCase(c);
                }
                //Get the colour of the square
                Colour colour = Colour.fromChar(c);
                //Get the location of the square
                Location squareLocation = new Location(row, col);
                //Create a new state to parse the colour and location
                squareState[row][col] = new State(colour, squareLocation);
            }
            row++;
        }
    }

    public State[][] getSquareState() {
        return squareState;
    }


     /**
     * Method to check if the board is well-formed task2
     * @return Return boolean to check if board is well formed.
     * @author Tengyi Zhang
     */
    public boolean isWellFormed() {
        if (lines.length != 12 && lines.length != 15 && lines.length != 18) {
            return false;
        }
//        Check if the characters of line is valid.
        for (String line : lines) {
            // Check if the line with \n is valid (length of line is 9 or 18)
            if (line.length() - 1 != 9 && line.length() - 1 != 18) {
                return false;
            }
            for (char c : line.toCharArray()) {
                if (!isValidChar(c)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Method to check if the characters is valid for the board game
     * @return Return boolean to check if char is valid.
     * @author Tengyi Zhang
     */
    public static boolean isValidChar(char c) {
        // Convert the character to lowercase
        c = Character.toLowerCase(c);
        return c == Colour.BLUE.toChar() || c == Colour.FIRE.toChar() || c == Colour.GREEN.toChar
                () || c == Colour.NONE.toChar() || c == Colour.RAFT.toChar() ||
                c == Colour.PURPLE.toChar() || c == Colour.RED.toChar() || c == Colour.WILDCARD.toChar() ||
                c == Colour.YELLOW.toChar() || c == '\n';
    }

    public String getBoardString() {
        return boardString;
    }


    /**
     * Updates the position of a cat on the game board.
     * @param cat The Cat object to update.
     * @author Tengyi Zhang
     */
    public void updateCatOnBoard(Cat cat) {
        Location start = cat.getStartLocation();
        Location end = cat.getEndLocation();

        char startCat = this.lines[start.getRow()].charAt(start.getColumn());
        this.lines[start.getRow()] = this.lines[start.getRow()].substring(0, start.getColumn()) + Character.toLowerCase(startCat) + this.lines[start.getRow()].substring(start.getColumn() + 1);
        char endCat = this.lines[end.getRow()].charAt(end.getColumn());
        this.lines[end.getRow()] =
                this.lines[end.getRow()].substring(0, end.getColumn()) +
                        Character.toUpperCase(endCat) + this.lines[end.getRow()].substring(end.getColumn() + 1);
        StringBuilder newBoard = new StringBuilder();
        for (String line : lines) {
            newBoard.append(line);
        }
        this.boardString = newBoard.toString();
    }

    public char[][] getBoardArray() {
        return boardArray;
    }

    public char getBoardArrayAt(int row, int col) {
        return boardArray[row][col];
    }

    public void setBoardArray(int row, int col, char value) {
        boardArray[row][col] = value;
    }


    /**
     * Initializes the board layout based on the island string provided.
     *
     * @param islandString A string detailing the island placements.
     * @return A 2D array representing the board's layout.
     * @author Jialin Li
     */
    public String[][] initialiseBoardLayout(String islandString) {
        //LNSNLASA
        int row = 0;
        // Determine the number of rows needed for the board based on island sizes
        for (int i = 0; i < islandString.length(); i += 2) {
            row += (islandString.charAt(i) == 'L') ? 9 : 6;
        }
        // Determine the number of columns based on the number of islands
        int column = (islandString.length() / 2 == 2) ? 9 : 18;
        // Create and return the board layout initialized with the computed row and column sizes
        return new String[row][column];
    }


    /**
     * Converts a 2D array representing the game board into a formatted string.
     *
     * @param boardLayout A 2D array of the board's layout.
     * @return A string representation of the board.
     * @author Jialin Li
     */
    public String convertBoardToString(String[][] boardLayout) {
        StringBuilder boardString = new StringBuilder();
        // Iterate over each row of the board layout
        for (String[] row : boardLayout) {
            // Convert each row to a string, replacing nulls with spaces for better readability
            String rowString = Arrays.stream(row)
                    .map(tile -> tile == null ? " " : tile)
                    .collect(Collectors.joining());
            // Only add non-empty rows to the final board string
            if (!rowString.trim().isEmpty()) {
                boardString.append(rowString).append("\n");
            }
        }
        // Return the trimmed string to remove any extra newline characters at the end
        return boardString.toString().trim();
    }


    /**
     * Determines if the specified card or fire tile placement is entirely within the board boundaries.
     *
     * @param charArrayPlacement A character array representing the card or fire tile placement details.
     * @param row The total number of rows in the game board.
     * @param col The total number of columns in the game board.
     * @return true if the entire card or fire tile is on the board, false otherwise.
     * @author Jialin Li
     */
    public boolean isOnTheBoard(char[] charArrayPlacement, int row, int col) {
        FireTile fireTile = new FireTile();
        PathwayCards pathwayCards = new PathwayCards();
        int tempRow = 0;
        int tempCol = 0;

        if (charArrayPlacement[5] != 'T' && charArrayPlacement[5] != 'F') {
            //is a card
            //e.g. "Av0308N"
            //03 08
            int a = Integer.parseInt(String.valueOf(charArrayPlacement[2]));
            int b = Integer.parseInt(String.valueOf(charArrayPlacement[4]));
            int c = Integer.parseInt(String.valueOf(charArrayPlacement[3]));
            int d = Integer.parseInt(String.valueOf(charArrayPlacement[5]));
            tempRow = a * 10 + c;
            tempCol = b * 10 + d;

            if (tempRow + 4 <= row && tempCol + 4 <= col) {//is on the board
                //rotate the card
                String str = new String(charArrayPlacement);
                cardFinal = pathwayCards.proccessPWCardRotate(str);
                return true;
            } else//out from the board
                return false;
        } else {
            //is a fire card.
            //"k 09 00 F W"
            //see if the fire need to be flipped?
            int a = Integer.parseInt(String.valueOf(charArrayPlacement[1]));
            int b = Integer.parseInt(String.valueOf(charArrayPlacement[2]));
            int c = Integer.parseInt(String.valueOf(charArrayPlacement[3]));
            int d = Integer.parseInt(String.valueOf(charArrayPlacement[4]));
            tempRow = a * 10 + b;
            tempCol = c * 10 + d;
            String str;

            str = new String(charArrayPlacement);
            fireFinal = fireTile.processFireTileFlip(str, charArrayPlacement);
            //the shape string of the fire

            //see if the fire need to do the rotation?

            str = new String(charArrayPlacement);
            fireFinal = fireTile.proccessFireTileRotate(str, charArrayPlacement);

            //e.g."g 0 0  0 1  0 2  1 1  1 2  1 3"
            char[] charArray = fireFinal.toCharArray();
            int largestRowNum = 0;
            int largestColNum = 0;
            //find the largest row number
            for (int i = 1; i < charArray.length; i += 2) {
                int e = Integer.parseInt(String.valueOf(charArray[i]));
                if (e > largestRowNum) {
                    largestRowNum = e;
                }
            }

            largestRowNum += tempRow;
            //find the largest col number
            for (int i = 2; i < charArray.length; i += 2) {
                int e = Integer.parseInt(String.valueOf(charArray[i]));
                if (e > largestColNum) {
                    largestColNum = e;
                }
            }

            largestColNum += tempCol;
            if (largestRowNum > row - 1 || largestColNum > col - 1) {
                return false;
            } else
                return true;
        }
    }


    /**
     * Checks if placing a tile (either a card or a fire tile) on the game board overlaps with forbidden elements.
     *
     * @param gameState          Array containing the current state of the game board as strings.
     * @param charArrayPlacement A character array representing the placement properties of the tile.
     * @param row                The number of rows in the game board.
     * @param col                The number of columns in the game board.
     * @return true if there is any overlap; otherwise, false.
     * @author Jialin Li
     */
    public boolean isOverlapping(String[] gameState, char[] charArrayPlacement, int row, int col) {
        FireTile fireTile = new FireTile();
        PathwayCards pathwayCards = new PathwayCards();
        //fire"k 09 00 F W"   card"Av0308N"
        // Initialize the game board as a 2D char array from the first string in gameState
        int temp = 0;
        boardCharArray = new char[row][col];
        char[] board = gameState[0].toCharArray();

        // Fill the 2D board array from the linear game state string
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                boardCharArray[i][j] = board[temp++];
            }
        }

        // Determine if the placement string represents a card or a fire tile
        if (charArrayPlacement[5] != 'T' && charArrayPlacement[5] != 'F') {
            // It's a card, parse its top-left corner coordinates from the placement string
            int a = Integer.parseInt(String.valueOf(charArrayPlacement[2]));
            int b = Integer.parseInt(String.valueOf(charArrayPlacement[3]));
            int c = Integer.parseInt(String.valueOf(charArrayPlacement[4]));
            int d = Integer.parseInt(String.valueOf(charArrayPlacement[5]));
            int rowCard = a * 10 + b;// Combine the two digits to form the full number
            int colCard = c * 10 + d;

            // Check for overlapping issues using the card's top-left coordinates
            if (pathwayCards.isCardOverLapping(boardCharArray, rowCard, colCard)) {
                return true;
            }
        } else {
            // It's a fire tile, parse its top-left corner coordinates from the placement string
            int a = Integer.parseInt(String.valueOf(charArrayPlacement[1]));
            int b = Integer.parseInt(String.valueOf(charArrayPlacement[2]));
            int c = Integer.parseInt(String.valueOf(charArrayPlacement[3]));
            int d = Integer.parseInt(String.valueOf(charArrayPlacement[4]));
            int rowFire = a * 10 + b;
            int colFire = c * 10 + d;
            if (fireTile.isFireOverLapping(boardCharArray, fireFinal, rowFire, colFire)) {
                return true;// Overlapping detected
            }
        }
        return false;
    }

    public String toString() {
        if (boardArray == null) return "NO BOARD";

        StringBuilder s = new StringBuilder();
        for (char[] row : boardArray) {
            s.append(row).append("\n");
        }
        return s.toString();
    }
}
