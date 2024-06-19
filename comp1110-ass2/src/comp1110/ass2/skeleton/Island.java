package comp1110.ass2.skeleton;

import java.util.Random;
import java.util.function.BiFunction;

import static comp1110.ass2.Utility.RECTANGLE_BOARDS;
import static comp1110.ass2.Utility.SQUARE_BOARDS;

/**
 * @author Yunmeng Zhang, Jialin Li
 **/
public class Island {
    private char size;
    private char rotation;
    private char numbersize;
    static String[][] islandArray;
    private IslandBoard[] islandBoards;
    private String layout;
    private static final int ISLAND_SPACING = 9;
    private static final Random random = new Random();

    public Island() {
    }

    public String getLayout() {
        return layout;
    }

    public void setLayout(String layout) {
        this.layout = layout;
    }

    //constructor
    public Island(String layout) {
        this.islandBoards = parseLayout(layout);
    }

    public Island(char size, char rotation) {
        this.size = size;
        this.rotation = rotation;
    }


    /**
     * Places islands on a given board layout based on the island configuration string.
     * Each island's position and orientation is determined by the configuration string.
     *
     * @param boardLayout  The game board to modify.
     * @param islandConfig The configuration string that determines the number and type of islands.
     * @author Jialin Li
     */
    public void placeIsland(String[][] boardLayout, String islandConfig) {
        // Determine the number of islands based on the configuration length
        int numIslands = islandConfig.length() / 2;// Calculate the number of islands
        int rowOffset = 0, columnOffset = 0;// Initialize offsets for placing islands

        for (int i = 0; i < numIslands; i++) {
            char size = islandConfig.charAt(2 * i);// Get the size type of the island (L or smaller)
            char rotation = islandConfig.charAt(2 * i + 1);// Get the rotation type
            String island = selectIsland(size, rotation);// Select the island configuration

            placeSingleIsland(boardLayout, island, rowOffset, columnOffset);// Place the island on the board

            // Update offsets for the next island based on its position in the sequence
            if (i % 2 == 1) {
                rowOffset = 0;// Reset row offset every second island
                columnOffset += ISLAND_SPACING;// Move column offset to the right
            } else
                // Increase row offset based on the island size
                rowOffset += (size == 'L') ? ISLAND_SPACING : ISLAND_SPACING - 3;
        }
    }

    /**
     * Places a single island on the board.
     *
     * @param board    The game board array.
     * @param island   The string representation of the island to place.
     * @param rowStart The starting row on the board for the island.
     * @param colStart The starting column on the board for the island.
     * @author Jialin Li
     */
    private void placeSingleIsland(String[][] board, String island, int rowStart, int colStart) {
        String[] rows = island.split("\n");// Split the island into rows based on new lines
        for (int r = 0; r < rows.length; r++) {
            for (int c = 0; c < rows[r].length(); c++) {
                // Only place the island piece if it's within the board bounds
                if (rowStart + r < board.length && colStart + c < board[0].length) {
                    board[rowStart + r][colStart + c] = String.valueOf(rows[r].charAt(c));
                }
            }
        }
    }

    /**
     * Selects the island configuration based on size and rotation.
     *
     * @param size     The size of the island (L for large).
     * @param rotation The rotation code (A for anticlockwise).
     * @return A string that represents the selected and rotated island.
     * @author Jialin Li
     */
    private String selectIsland(char size, char rotation) {
        // Decide between square or rectangle boards based on size
        String[][] islands = (size == 'L') ? SQUARE_BOARDS : RECTANGLE_BOARDS;
        // Select a random configuration and apply the specified rotation
        String chosenIsland = islands[random.nextInt(islands.length)][(rotation == 'A') ? 1 : 0];
        return rotateIsland(chosenIsland, rotation);
    }

    /**
     * Converts a 2D char array into a single string.
     *
     * @param matrix The 2D char array to convert.
     * @return A string representation of the 2D array.
     * @author Jialin Li
     */
    public String matrixToString(char[][] matrix) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < matrix.length; i++) {
            sb.append(new String(matrix[i]));
            if (i < matrix.length - 1) {
                sb.append("\n");
            }
        }
        // Trim to remove any trailing newlines
        return sb.toString();
    }

    /**
     * Do the Clockwise rotate to the board.
     *
     * @param board
     * @return
     * @author Jialin Li
     */
    public String rotateClockwise(String board) {
        String[] rows = board.trim().split("\n");
        int numRows = rows.length;
        int numCols = rows[0].length();
        char[][] matrix = new char[numRows][numCols];
        char[][] rotatedMatrix = new char[numCols][numRows];

        // Fill the matrix from the string
        for (int i = 0; i < numRows; i++) {
            matrix[i] = rows[i].toCharArray();
        }
        // Rotate the matrix 90 degrees clockwise
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                rotatedMatrix[j][numCols - 1 - i] = matrix[i][j];
            }
        }
        return matrixToString(rotatedMatrix);
    }

    /**
     * Do the 180 rotate to the board.
     *
     * @param islandBoard
     * @return
     * @author Jialin Li
     */
    public String rotate180(String islandBoard) {
        String[] rows = islandBoard.split("\n");
        int numRows = rows.length;
        int numCols = rows[0].length();
        char[][] matrix = new char[numRows][numCols];

        // Fill the matrix from the string
        for (int i = 0; i < numRows; i++) {
            matrix[i] = rows[i].toCharArray();
        }

        char[][] rotatedMatrix = new char[numRows][numCols];

        // Rotate the matrix 180 degrees
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                rotatedMatrix[numRows - 1 - i][numCols - 1 - j] = matrix[i][j];
            }
        }
        return matrixToString(rotatedMatrix);
    }

    /**
     * Rotates a given board representation 90 degrees counterclockwise.
     * The board is represented as a string of newline-separated rows.
     *
     * @param islandBoard The string representation of the board to be rotated.
     * @return A new string representing the board after a 90-degree counterclockwise rotation.
     * @author Jialin Li
     */
    public String rotateAntiClockwise(String islandBoard) {
        String[] rows = islandBoard.trim().split("\n");
        int numRows = rows.length;
        int numCols = rows[0].length();
        char[][] matrix = new char[numRows][numCols];
        char[][] rotatedMatrix = new char[numCols][numRows];
        // Fill the matrix from the string
        for (int i = 0; i < numRows; i++) {
            matrix[i] = rows[i].toCharArray();
        }
        // Rotate the matrix 90 degrees counterclockwise
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                rotatedMatrix[numCols - 1 - j][i] = matrix[i][j];
            }
        }
        return matrixToString(rotatedMatrix);
    }

    /**
     * Do the rotation to the board.
     *
     * @param islandboard
     * @param orientation
     * @return
     * @author Jialin Li
     */
    public String rotateIsland(String islandboard, char orientation) {
        // Determine the rotation function based on the orientation character
        switch (orientation) {
            case 'E':
                return rotateClockwise(islandboard);
            case 'S':
                return rotate180(islandboard);
            case 'W':
                return rotateAntiClockwise(islandboard);
            default:
                return islandboard;
        }
    }

    /**
     * compile layout string
     *
     * @param layout
     * @return IslandBoard[]
     * @author Yunmeng Zhang
     */
    public IslandBoard[] parseLayout(String layout) {
        // the number of board
        islandBoards = new IslandBoard[layout.length() / 2];

        for (int i = 0; i < layout.length(); i += 2) {
            String size = layout.substring(i, i + 1);
            String rotation = layout.substring(i + 1, i + 2);
            // add the board to islandBoards
            islandBoards[i / 2] = new IslandBoard(size, rotation);
        }
        return islandBoards;
    }

    /**
     * Get board height
     *
     * @return int
     * @author Yunmeng Zhang
     */
    public int getHeight() {
        int height = 0;
        if ("L".equals(islandBoards[0].getSize()) && "L".equals(islandBoards[1].getSize())) {
            height = 18;
        } else if (("L".equals(islandBoards[0].getSize()) && "S".equals(islandBoards[1].getSize()))
                || ("S".equals(islandBoards[0].getSize()) && "L".equals(islandBoards[1].getSize()))) {
            height = 15;
        } else if ("S".equals(islandBoards[0].getSize()) && "S".equals(islandBoards[1].getSize())) {
            height = 12;
        }
        return height;
    }

    /**
     * Get board width
     *
     * @return int
     * @author Yunmeng Zhang
     */
    public int getWidth() {
        int width = 0;
        if ("L".equals(islandBoards[0].getSize())) {  //width of board 1
            width += 9; // width of "L"
        } else {
            width += 6; // width of "S"
        }

        if (islandBoards.length > 2) {  //width of board 3
            if ("L".equals(islandBoards[2].getSize())) {
                width += 9;
            } else {
                width += 6;
            }
        }
        return width;
    }


    // Getter
    public IslandBoard[] getIslandBoards() {
        return islandBoards;
    }

    // Print layout String
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("IslandBoard Layout:\n");
        for (IslandBoard islandBoard : islandBoards) {
            sb.append(islandBoard.toString()).append("\n");
        }
        return sb.toString();
    }
}
