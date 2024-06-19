package comp1110.ass2.skeleton;

import comp1110.ass2.Utility;

import java.util.ArrayList;
import java.util.List;

import static comp1110.ass2.Utility.FIRE_TILES;

/**
 * @author Yunmeng Zhang, Jialin Li
 **/
public class FireTile {

    private String ID;
    private List<Location> layout;

    private int maxRow;

    public int getMaxRow() {
        return maxRow;
    }

    public int getMaxCol() {
        return maxCol;
    }

    private int maxCol;

    public FireTile() {
    }

    public FireTile(List<Location> layout) {
        this.layout = layout;
    }

    public FireTile(String placementString) {
        this.ID = String.valueOf(placementString.charAt(0));
//        this.row = Integer.parseInt(placementString.substring(2, 4));
//        this.column = Integer.parseInt(placementString.substring(4, 6));
//        this.isFlipped = placementString.charAt(5) == 'T';
//        this.orientation = placementString.substring(5,7);
        for (String f : Utility.FIRE_TILES) {
            if (ID.equals(String.valueOf(f.charAt(0)))) {
                this.layout = parseLayout(f);
                break;
            }
        }
    }

    /**
     * Places fire cards on a given board layout based on a string representation.
     * Each fire card occupies a 3x3 area on the board.
     *
     * @param boardLayout A 2D array representing the board on which fire cards will be placed.
     * @param fireString  A string encoding the positions of fire cards on the board.
     *                    Each fire card's position is encoded in 4 characters:
     *                    - The first two characters represent the row (tens and units),
     *                    - The next two characters represent the column (tens and units).
     * @author Jialin Li
     */
    public void placeFireCards(String[][] boardLayout, String fireString) {
        // Convert the string into a character array for easier processing
        char[] FireStringCharArray = fireString.toCharArray();
        // Check if there is at least one fire card to place
        if (FireStringCharArray.length != 1) {
            int aRow;// Variable to store the row index for the fire card
            int bCol;// Variable to store the column index for the fire card
            // Loop through the encoded string, incrementing by 4 to read each fire card's position
            for (int i = 1; i < FireStringCharArray.length; i += 4) {
                // Calculate the row index for placing the fire card:
                aRow = Integer.parseInt(String.valueOf(FireStringCharArray[i])) * 10
                        + Integer.parseInt(String.valueOf(FireStringCharArray[i + 1]));
                // Calculate the column index for placing the fire card similarly
                bCol = Integer.parseInt(String.valueOf(FireStringCharArray[i + 2])) * 10
                        + Integer.parseInt(String.valueOf(FireStringCharArray[i + 3]));

                // Fill a 3x3 grid with 'f' to represent the fire card on the boardLayout
                for (int row = 0; row < 3; row++) {
                    for (int col = 0; col < 3; col++) {
                        boardLayout[aRow + row][bCol + col] = "f";
                    }
                }
            }
        }
    }


    /**
     * Processes the fire tile placement string to apply rotation based on the orientation specified in the string.
     *
     * @param placementString The placement string that includes ID, position, flip status, and orientation of the fire tile.
     * @return The string representing the rotated fire tile shape, ready to be placed on the game board.
     * @author Jialin Li
     */
    public String proccessFireTileRotate(String placementString, char[] charArrayPlacement) {
        char orientation = placementString.charAt(6);
        char id = placementString.charAt(0);
        this.ID = String.valueOf(id);
        String tileShape = getTileShapeById();
        if (charArrayPlacement[6] != 'N') {
            return rotateFire(tileShape, orientation);
        } else
            return tileShape;
    }


    /**
     * Rotates the given fire tile shape based on the specified orientation.
     *
     * @param tileShape   The string representing the shape of the fire tile in North orientation.
     * @param orientation The character representing the orientation to which the tile should be rotated:
     *                    'N' for North, 'E' for East, 'S' for South, and 'W' for West.
     * @return A string representing the shape of the tile after rotation, maintaining the same format as the input.
     * @author Jialin Li
     */
    public String rotateFire(String tileShape, char orientation) {
        if (tileShape == null || tileShape.isEmpty()) {
            System.out.println("Error: tileShape is empty or null!");
            return tileShape;
        }

        // Extract ID
        char id = tileShape.charAt(0);
        StringBuilder rotatedShape = new StringBuilder();
        rotatedShape.append(id);

        // Determine the dimensions of the tile's bounding box
        int maxRow = 0, maxCol = 0;
        for (int i = 1; i < tileShape.length(); i += 2) {
            int row = tileShape.charAt(i) - '0';
            int col = tileShape.charAt(i + 1) - '0';
            if (row > maxRow) maxRow = row;
            if (col > maxCol) maxCol = col;
        }

        // Rotate each coordinate based on the orientation
        for (int i = 1; i < tileShape.length(); i += 2) {
            int row = tileShape.charAt(i) - '0';
            int col = tileShape.charAt(i + 1) - '0';
            int newRow = 0, newCol = 0;

            switch (orientation) {
                case 'E': // 90 degrees clockwise
                    newRow = col;
                    newCol = maxRow - row;
                    break;
                case 'S': // 180 degrees clockwise
                    newRow = maxRow - row;
                    newCol = maxCol - col;
                    break;
                case 'W': // 270 degrees clockwise
                    newRow = maxCol - col;
                    newCol = row;
                    break;
                default: // No rotation for 'N'
                    newRow = row;
                    newCol = col;
                    break;
            }
            rotatedShape.append((char) ('0' + newRow)).append((char) ('0' + newCol));
        }
        return rotatedShape.toString();
    }


    /**
     * Processes a fire tile placement string to compute the final placement on the board.
     *
     * @param placementString A string representing the placement of the fire tile.
     * @return The shape details of the fire card.
     * @author Jialin Li
     */
    public String processFireTileFlip(String placementString, char[] charArrayPlacement) {
        char id = placementString.charAt(0);
        int row = Integer.parseInt(placementString.substring(1, 3));
        int col = Integer.parseInt(placementString.substring(3, 5));
        this.ID = String.valueOf(id);
        String tileShape = getTileShapeById();

        if (charArrayPlacement[5] == 'T') {
            tileShape = flipFire(tileShape);
        }
        System.out.println(tileShape);
        return tileShape;
    }


    /**
     * Flips the tile horizontally.
     *
     * @param tileShape The string shape of the tile.
     * @return The flipped shape string of the tile.
     * @author Jialin Li
     */
    public String flipFire(String tileShape) {
        StringBuilder flippedShape = new StringBuilder();
        flippedShape.append(tileShape.charAt(0));  // Append the ID

        for (int i = 1; i < tileShape.length(); i += 2) {
            char rowChar = tileShape.charAt(i);
            char colChar = tileShape.charAt(i + 1);
            // Assuming maximum column index is 8 since no tile is bigger than 9x9
            int maxCol = 0;
            int[] cols = new int[6];
            cols[0] = tileShape.charAt(2) - '0';
            cols[1] = tileShape.charAt(4) - '0';
            cols[2] = tileShape.charAt(6) - '0';
            cols[3] = tileShape.charAt(8) - '0';
            cols[4] = tileShape.charAt(10) - '0';
            cols[5] = tileShape.charAt(12) - '0';
            for (int col : cols) {
                maxCol = Math.max(maxCol, col);
            }
            char newColChar = (char) ('0' + (maxCol - (colChar - '0')));
            ;
            flippedShape.append(rowChar).append(newColChar);
        }
        System.out.println(flippedShape.toString());
        return flippedShape.toString();
    }


    /**
     * Retrieves the tile shape string by the tile's ID.
     *
     * @return The shape string of the tile.
     * @author Jialin Li
     */
    public String getTileShapeById() {
        char id = ID.charAt(0);
        if (id >= 'a' && id <= 'z') {
            return FIRE_TILES[id - 'a'];
        } else if (id >= 'A' && id <= 'E') {
            return FIRE_TILES[26 + id - 'A'];
        } else
            return "";
    }


    /**
     * Checks if placing a uniquely shaped fire tile on the board overlaps with fire, cats, or rafts.
     *
     * @param boardCharArray The game board as a 2D character array.
     * @param fireFinal      A string representing the coordinates of the fire tile's shape relative to its top-left corner.
     * @param rowFire        The row index of the top-left corner of the fire tile on the board.
     * @param colFire        The column index of the top-left corner of the fire tile on the board.
     * @return true if there is any overlap with fire, cats, or rafts; false otherwise.
     * @author Jialin Li
     */
    public boolean isFireOverLapping(char[][] boardCharArray, String fireFinal, int rowFire, int colFire) {
        // Validate the starting point to be within the board limits
        if (rowFire < 0 || colFire < 0 || rowFire >= boardCharArray.length || colFire >= boardCharArray[0].length) {
            return true;  // Assuming overlapping if starting point is out of board boundaries
        }

        // Process each coordinate pair from the fireFinal string
        for (int i = 1; i < fireFinal.length(); i += 2) {
            int relRow = Integer.parseInt(fireFinal.substring(i, i + 1));
            int relCol = Integer.parseInt(fireFinal.substring(i + 1, i + 2));
            int absRow = rowFire + relRow;
            int absCol = colFire + relCol;

            // Ensure the absolute coordinates are within board limits
            if (absRow < 0 || absCol < 0 || absRow >= boardCharArray.length || absCol >= boardCharArray[0].length) {
                return true;  // Out of bounds overlaps
            }

            char tile = boardCharArray[absRow][absCol];
            // Check for overlapping with fire ('f') or cats ('c')
            if (tile == 'f' || tile == 'c' || tile == 'G' || tile == 'B' || tile == 'R' || tile == 'Y' || tile == 'P') {
                return true;
            }

            // Check for overlapping with raft ('o') or its adjacent cells
            if (isOverlappingRaft(boardCharArray, absRow, absCol)) {
                return true;
            }
        }
        return false;  // No overlapping found
    }


    /**
     * Checks for the presence of a raft ('o') or its adjacent cells.
     *
     * @param board The game board as a 2D char array.
     * @param row   The row index to check.
     * @param col   The column index to check.
     * @return true if the current or any adjacent cell is a raft ('o'), false otherwise.
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
     * Checks if any square of the uniquely shaped fire tile is orthogonally adjacent to another fire square on the board.
     *
     * @param boardCharArray The game board as a 2D character array.
     * @param fireFinal      A string representing the coordinates of the fire tile's shape relative to its top-left corner.
     * @param rowFire        The row index of the top-left corner of the fire tile on the board.
     * @param colFire        The column index of the top-left corner of the fire tile on the board.
     * @return true if at least one square of the fire tile is next to another fire square, false otherwise.
     * @author Jialin Li
     */
    public boolean isAdjacentToF(char[][] boardCharArray, String fireFinal, int rowFire, int colFire, char[] charArrayPlacement) {
        if (charArrayPlacement[5] != 'T' && charArrayPlacement[5] != 'F') {
            //is a card
            return true;
        }
        // Process each coordinate pair from the fireFinal string
        for (int i = 1; i < fireFinal.length(); i += 2) {
            int relRow = Integer.parseInt(fireFinal.substring(i, i + 1));
            int relCol = Integer.parseInt(fireFinal.substring(i + 1, i + 2));
            int absRow = rowFire + relRow;
            int absCol = colFire + relCol;

            // Check north, south, east, and west for adjacent fire squares
            if (isFireAdjacent(boardCharArray, absRow, absCol)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if there is a fire square ('f') orthogonally adjacent to the given position.
     *
     * @param board The game board as a 2D char array.
     * @param row   The row index to check.
     * @param col   The column index to check.
     * @return true if there is an adjacent fire square, false otherwise.
     * @author Jialin Li
     */
    public boolean isFireAdjacent(char[][] board, int row, int col) {
        int[] dRow = {-1, 1, 0, 0};  // Up, Down
        int[] dCol = {0, 0, -1, 1};  // Left, Right
        for (int i = 0; i < 4; i++) {
            int checkRow = row + dRow[i];
            int checkCol = col + dCol[i];
            if (checkRow >= 0 && checkRow < board.length && checkCol >= 0 && checkCol < board[0].length) {
                if (board[checkRow][checkCol] == 'f') {
                    return true;
                }
            }
        }
        return false;
    }


    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public List<Location> getLayout() {
        return layout;
    }

    public void printLayoutLocations() {
        for (Location loc : layout) {
            System.out.println("(" + loc.row + ", " + loc.column + ")");
        }
    }

    public void setLayout(List<Location> layout) {
        this.layout = layout;
    }


    /**
     * parse the fire tile string (such as a001102123212) to locations
     * @author Yunmeng Zhang
     */
    private List<Location> parseLayout(String layoutString) {
        List<Location> layoutLocations = new ArrayList<>();
        for (int i = 1; i < layoutString.length(); i += 2) {
            int row = Character.getNumericValue(layoutString.charAt(i));
            int col = Character.getNumericValue(layoutString.charAt(i + 1));
            layoutLocations.add(new Location(row, col));
        }
        return layoutLocations;
    }

    /**
     * Checks if none of the locations within a specified layout have adjacent tiles that are on fire.
     * This can be useful for validating moves or setups in gameplay where proximity to fire must be avoided.
     *
     * @param gameBoard The string representation of the game board, where each character's position can be checked for fire.
     * @return true if no location in the layout is adjacent to a fire tile, false otherwise.
     * @author Yunmeng Zhang
     */
    public boolean noneAdjacentFire(String gameBoard) {
        // Iterate through each location defined in the layout.
        for (Location loc : layout) {
            List<Location> adjacents = loc.getAdjacentLocations(gameBoard);
            for (Location adj : adjacents) {
                if (adj.getColourAt(gameBoard).equals(Colour.FIRE)) {
                    return false;
                }
            }
        }
        // If no adjacent tiles are on fire for any of the locations in the layout, return true.
        return true;
    }

    /**
     * return the new layout after rotation
     *
     * @param orientation the pattern of rotation
     * @return list of new locations
     * @author Yunmeng Zhang
     */
    public List<Location> rotateCoordinates(String orientation) {
        int maxRow = 0;
        int maxCol = 0;
        // Find the maximum row and column values in the current layout
        for (Location loc : layout) {
            maxRow = Math.max(maxRow, loc.getRow());
            maxCol = Math.max(maxCol, loc.getColumn());
        }
        List<Location> newLayout = new ArrayList<>();
        // Rotate the coordinates based on the specified orientation
        if (orientation.equals("FN")) {
            // No rotation needed
            newLayout = this.layout;
        } else if (orientation.equals("TN")) {
            // Mirror horizontally (left-right flip)
            for (Location loc : layout) {
                int newRow = loc.getRow();
                int newCol = maxCol - loc.getColumn();
                newLayout.add(new Location(newRow, newCol));
            }
        } else if (orientation.equals("FE")) {
            // Rotate 90 degrees clockwise
            for (Location loc : layout) {
                int newRow = loc.getColumn();
                int newCol = maxRow - loc.getRow();
                newLayout.add(new Location(newRow, newCol));
            }
        } else if (orientation.equals("TE")) {
            // Mirror vertically (top-bottom flip)
            for (Location loc : layout) {
                int newRow = maxCol - loc.getColumn();
                int newCol = maxRow - loc.getRow();
                newLayout.add(new Location(newRow, newCol));
            }
        } else if (orientation.equals("FS")) {
            // Rotate 180 degrees
            for (Location loc : layout) {
                int newRow = maxRow - loc.getRow();
                int newCol = maxCol - loc.getColumn();
                newLayout.add(new Location(newRow, newCol));
            }
        } else if (orientation.equals("TS")) {
            // Mirror horizontally (top-bottom flip)
            for (Location loc : layout) {
                int newRow = maxRow - loc.getRow();
                int newCol = loc.getColumn();
                newLayout.add(new Location(newRow, newCol));
            }
        } else if (orientation.equals("FW")) {
            // Rotate 270 degrees clockwise (or 90 degrees counterclockwise)
            for (Location loc : layout) {
                int newRow = maxCol - loc.getColumn();
                int newCol = loc.getRow();
                newLayout.add(new Location(newRow, newCol));
            }
        } else if (orientation.equals("TW")) {
            // Mirror vertically (left-right flip)
            for (Location loc : layout) {
                int newRow = loc.getColumn();
                int newCol = loc.getRow();
                newLayout.add(new Location(newRow, newCol));
            }
        }
        return newLayout;
    }

}
