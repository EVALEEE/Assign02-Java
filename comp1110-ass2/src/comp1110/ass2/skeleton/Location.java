package comp1110.ass2.skeleton;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Yunmeng Zhang
 * **/
public class Location {
    int row;
    int column;

    /**
     * A default "out-of-bounds" coordinate
     */
    static final int OUT = -1;
    /**
     * Create a Location that is not on the board
     */
    public Location() {
        this.row = OUT;
        this.column = OUT;
    }

    public Location(int row, int column){
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    //test if the location is on the game board
    public boolean isOnIsland(int rowScale, int columnScale){
        return this.row >= 0 && this.row < rowScale && this.column >= 0 && this.column < columnScale;
    }


    /**
     * Retrieves a list of adjacent locations on the game board based on the provided location.
     *
     * @param gameBoard The string representation of the game board.
     * @return A list of adjacent locations.
     * @throws IllegalArgumentException If the game board string is null or empty.
     * @author Yunmeng Zhang
     */
    public List<Location> getAdjacentLocations(String gameBoard){
        // Check if the game board string is null or empty
        if (gameBoard == null || gameBoard.isEmpty()) {
            throw new IllegalArgumentException("Game board cannot be null or empty.");
        }
        // Initialize an empty list to store adjacent locations
        List<Location> adjacent = new ArrayList<>();
        // Calculate the location of the north adjacent square
        Location north = new Location(row-1,column);

        // Split the game board string to extract row and column information
        String[] lines = gameBoard.split("\n");
        int rowScale = lines.length;
        int columnScale = lines[0].length();

        // Check if each adjacent location is on the island and add it to the list if true
        if (north.isOnIsland(rowScale,columnScale)){
            adjacent.add(north);
        }
        Location south = new Location(row+1,column);
        if (south.isOnIsland(rowScale,columnScale)){
            adjacent.add(south);
        }
        Location west = new Location(row, column-1);
        if (west.isOnIsland(rowScale,columnScale)){
            adjacent.add(west);
        }
        Location east = new Location(row, column+1);
        if (east.isOnIsland(rowScale,columnScale)){
            adjacent.add(east);
        }
        return adjacent;
    }

    /**
     * Checks if the current cell is adjacent to a cell containing the character 'o' in the given 2D board.
     *
     * @param board The 2D board represented as a char array.
     * @return True if the current cell is adjacent to a cell containing 'o', false otherwise.
     * @author Yunmeng Zhang
     */
    public boolean isNearO(char[][] board) {
        // Define arrays for the changes in x and y directions for adjacent cells
        int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};

        // Iterate through the changes in x and y directions
        for (int i = 0; i < dx.length; i++) {
            // Calculate the new coordinates of the adjacent cell
            int newX = row + dx[i];
            int newY = column + dy[i];
            // Check if the new coordinates are within the bounds of the board
            if (newX >= 0 && newX < board.length && newY >= 0 && newY < board[0].length) {
                // Check if the adjacent cell contains the character 'o'
                if (board[newX][newY] == 'o') {
                    return true;
                }
            }
        }
        // Return false if none of the adjacent cells contain 'o'
        return false;
    }

    /**
     * Gets the color at the specified location on the game board.
     *
     * @param boardState The current state of the game board represented as a string.
     * @return The color at the specified location.
     * @author Yunmeng Zhang
     */
    public Colour getColourAt(String boardState){
        // Retrieve the character representing the color at the specified location
        char colour =  boardState.split("\n")[row].charAt(column);
        // Convert the character to lowercase and obtain the corresponding color enum
        return Colour.fromChar(Character.toLowerCase(colour));
    }

    /**
     * Checks if the specified location is within the boundaries of the game board.
     *
     * @param boardState The current state of the game board represented as a string.
     * @return True if the location is valid (within the board boundaries), false otherwise.
     * @author Yunmeng Zhang
     */
    public boolean isValidLocation(String boardState){
        // Split the board state into individual lines
        String[] lines = boardState.split("\n");
        // Check if the row and column indices are within the bounds of the board
        if ((row<lines.length)&&(column<lines[0].length())){
            return true;
        }
        return false;
    }


    /**
     * Fake fire means the square which cannot be a potential path of cat
     * @param gameBoard game board after placement of fire tile
     * @param catColour color of cat
     * @return true if the location is fake fire
     * @author Yunmeng Zhang
     */
    public boolean isFakeFire(String gameBoard, Colour catColour){
        String[] lines = gameBoard.split("\n");
        int rowLength = lines.length;
        int colLength = lines[0].length();
        if (this.getColourAt(gameBoard).equals(catColour)){
            return false;
        }
        else {
            //if the square cannot be any part of a pathway card, the square is fake fire (cat cannot pass through)
            if (!atTopLeftCard(gameBoard,rowLength,colLength) && !atTopCard(gameBoard,rowLength,colLength)
                    && !atTopRightCard(gameBoard,rowLength,colLength) && !atLeftCard(gameBoard,rowLength,colLength)
                    && !atCenterCard(gameBoard, rowLength,colLength) && !atRightCard(gameBoard, rowLength,colLength)
                    && !atBottomLeftCard(gameBoard,rowLength,colLength) && !atBottomCard(gameBoard,rowLength,colLength)
                    && !atBottomRightCard(gameBoard,rowLength,colLength)){
                return true;
            }
        }


        return false;
    }

    /**
     * the next 8 methods are to examine if the location can be part of a 3x3 card
     * @param gameBoard game board string
     * @param rowLength height of the board
     * @param colLength width of the board
     * @return if this location can be part of 3x3 pathway card
     * @author Yunmeng Zhang
     */
    //assuming the square is at location 1 of 3x3 card
    private boolean atTopLeftCard(String gameBoard, int rowLength, int colLength){
        List<Location> locations = new ArrayList<>();
        if (column>colLength-3 || row>rowLength-3){
            return false;
        }
        else if (column<=colLength-3 && row<=rowLength-3){
            locations.add(new Location(row, column+1)); //location 2
            locations.add(new Location(row,column+2)); //location 3
            locations.add(new Location(row+1, column)); //location 4
            locations.add(new Location(row+1, column+1)); //location 5
            locations.add(new Location(row+1, column+2)); //location 6
            locations.add(new Location(row+2, column)); //location 7
            locations.add(new Location(row+2, column+1)); //location 8
            locations.add(new Location(row+2, column+2)); //location 9
        }
        for (Location loc: locations){
            if (loc.getColourAt(gameBoard).equals(Colour.FIRE)){
                return false;
            }
        }
        return true;
    }

    //assuming the square is at location 2 of 3x3 card
    /**
     * Determines if a specified card (or tile) and its immediate surrounding area are free of fire.
     * The check is performed within a 3x3 area centered around a given position on the game board.
     *
     * @param gameBoard A string representation of the game board.
     * @param rowLength The total number of rows in the game board.
     * @param colLength The total number of columns in the game board.
     * @return true if there is no fire in the 3x3 area centered on the specified card; false otherwise.
     * @author Yunmeng Zhang
     */
    private boolean atTopCard(String gameBoard, int rowLength, int colLength){
        List<Location> locations = new ArrayList<>();
        if (column<1 || column>colLength-2 || row>rowLength-3){
            return false;
        }
        else if (column>=1 && column<=colLength-2 && row<=rowLength-3){
            locations.add(new Location(row, column-1)); //location 1
            locations.add(new Location(row, column+1)); //location 3
            locations.add(new Location(row+1, column-1)); //location 4
            locations.add(new Location(row+1, column)); //location 5
            locations.add(new Location(row+1, column+1)); //location 6
            locations.add(new Location(row+2, column-1)); //location 7
            locations.add(new Location(row+2, column)); //location 8
            locations.add(new Location(row+2, column+1)); //location 9
        }
        for (Location loc: locations){
            if (loc.getColourAt(gameBoard).equals(Colour.FIRE)){
                return false;
            }
        }
        return true;
    }

    //assuming the square is at location 3 of 3x3 card
    /**
     * Determines if a specified card (or tile) positioned in the top-right region of the board
     * and its immediate surrounding area are free of fire.
     * The check is performed within an area offset to the left of the specified row and column,
     * forming an inverted L-shape to the top-right of the specified location.
     *
     * @param gameBoard A string representation of the game board, used to check tile properties.
     * @param rowLength The total number of rows in the game board.
     * @param colLength The total number of columns in the game board.
     * @return true if there is no fire in the area surrounding the specified position; false otherwise.
     * @author Yunmeng Zhang
     */
    private boolean atTopRightCard(String gameBoard, int rowLength, int colLength){
        List<Location> locations = new ArrayList<>();
        if (column<2 || row>rowLength-3){
            return false;
        }
        else if (column>=2 && row<=rowLength-3){
            locations.add(new Location(row, column-2)); //location 1
            locations.add(new Location(row, column-1)); //location 2
            locations.add(new Location(row+1, column-2)); //location 4
            locations.add(new Location(row+1, column-1)); //location 5
            locations.add(new Location(row+1, column)); //location 6
            locations.add(new Location(row+2, column-2)); //location 7
            locations.add(new Location(row+2, column-1)); //location 8
            locations.add(new Location(row+2, column)); //location 9
        }
        for (Location loc: locations){
            if (loc.getColourAt(gameBoard).equals(Colour.FIRE)){
                return false;
            }
        }
        return true;
    }

    //assuming the square is at location 4 of 3x3 card
    /**
     * Checks if the area to the left of a specified card (or tile) and its immediate right and vertical surroundings
     * are free of fire. This check is important for gameplay elements that need a clean area to the left side
     * for placement or movement.
     *
     * @param gameBoard The string representation of the game board, where fire and other elements are marked.
     * @param rowLength The total number of rows in the game board.
     * @param colLength The total number of columns in the game board.
     * @return true if the left and adjacent right areas of the specified card are free of fire; false otherwise.
     * @author Yunmeng Zhang
     */
    private boolean atLeftCard(String gameBoard, int rowLength, int colLength){
        List<Location> locations = new ArrayList<>();
        if (column>colLength-3 || row>rowLength-2 || row<1){
            return false;
        }
        else if (column<=colLength-3 && row<=rowLength-2 && row>=1){
            locations.add(new Location(row-1, column)); //location 1
            locations.add(new Location(row-1,column+1)); //location 2
            locations.add(new Location(row-1, column+2)); //location 3
            locations.add(new Location(row, column+1)); //location 5
            locations.add(new Location(row, column+2)); //location 6
            locations.add(new Location(row+1, column)); //location 7
            locations.add(new Location(row+1, column+1)); //location 8
            locations.add(new Location(row+1, column+2)); //location 9
        }
        for (Location loc: locations){
            if (loc.getColourAt(gameBoard).equals(Colour.FIRE)){
                return false;
            }
        }
        return true;
    }

    //assuming the square is at location 5 of 3x3 card
    /**
     * Checks if the specified center card position on the game board and its immediate surrounding area
     * are free of fire. The method evaluates a 3x3 grid centered around the given row and column.
     *
     * @param gameBoard The string representation of the game board, used to check tile properties.
     * @param rowLength The total number of rows in the game board.
     * @param colLength The total number of columns in the game board.
     * @return true if the center and its surrounding area are free of fire; false otherwise.
     * @author Yunmeng Zhang
     */
    private boolean atCenterCard(String gameBoard, int rowLength, int colLength){
        List<Location> locations = new ArrayList<>();
        if (column<1 || column>colLength-2 || row>rowLength-3 || row<1){
            return false;
        }
        else if (column>=1 && column<=colLength-2 && row<=rowLength-2 && row>=1){
            locations.add(new Location(row-1, column-1)); //location 1
            locations.add(new Location(row-1, column)); //location 2
            locations.add(new Location(row-1, column+1)); //location 3
            locations.add(new Location(row, column-1)); //location 4
            locations.add(new Location(row, column+1)); //location 6
            locations.add(new Location(row+1, column-1)); //location 7
            locations.add(new Location(row+1, column)); //location 8
            locations.add(new Location(row+1, column+1)); //location 9
        }
        for (Location loc: locations){
            if (loc.getColourAt(gameBoard).equals(Colour.FIRE)){
                return false;
            }
        }
        return true;
    }

    //assuming the square is at location 6 of 3x3 card
    /**
     * Determines if the specified card (or tile) at the right and its surrounding area,
     * shifted left to include one column to the left of the card, are free of fire.
     * The method performs a check within a 3x3 grid leftward from the given row and column,
     * ensuring the safety of card placement or interactions in that area.
     *
     * @param gameBoard The string representation of the game board, used to check tile properties.
     * @param rowLength The total number of rows in the game board.
     * @param colLength The total number of columns in the game board.
     * @return true if the right side and its adjacent left area of the specified card are free of fire; false otherwise.
     * @author Yunmeng Zhang
     */
    private boolean atRightCard(String gameBoard, int rowLength, int colLength){
        List<Location> locations = new ArrayList<>();
        if (column<2 || row>rowLength-2 || row<1){
            return false;
        }
        else if (column>=2 && row<=rowLength-2 && row>=1){
            locations.add(new Location(row-1, column-2)); //location 1
            locations.add(new Location(row-1,column-1)); //location 2
            locations.add(new Location(row-1, column)); //location 3
            locations.add(new Location(row, column-2)); //location 4
            locations.add(new Location(row, column-1)); //location 5
            locations.add(new Location(row+1, column-2)); //location 7
            locations.add(new Location(row+1, column-1)); //location 8
            locations.add(new Location(row+1, column)); //location 9
        }
        for (Location loc: locations){
            if (loc.getColourAt(gameBoard).equals(Colour.FIRE)){
                return false;
            }
        }
        return true;
    }

    //assuming the square is at location 7 of 3x3 card
    /**
     * Determines if the specified card (or tile) positioned towards the bottom-left of a grid
     * and its immediate upper and right surroundings are free of fire.
     * The method evaluates an area directly above and to the right of the given row and column,
     * which is important for gameplay mechanics that require interaction with the bottom-left region.
     *
     * @param gameBoard The string representation of the game board, used to check tile properties.
     * @param rowLength The total number of rows in the game board.
     * @param colLength The total number of columns in the game board.
     * @return true if the bottom-left and its adjacent upper and right areas of the specified card are free of fire; false otherwise.
     * @author Yunmeng Zhang
     */
    private boolean atBottomLeftCard(String gameBoard, int rowLength, int colLength){
        List<Location> locations = new ArrayList<>();
        if (column>colLength-3 || row<2){
            return false;
        }
        else if (column<=colLength-3 && row>=2){
            locations.add(new Location(row-2, column)); //location 1
            locations.add(new Location(row-2,column+1)); //location 2
            locations.add(new Location(row-2, column+2)); //location 3
            locations.add(new Location(row-1, column)); //location 4
            locations.add(new Location(row-1, column+1)); //location 5
            locations.add(new Location(row-1, column+2)); //location 6
            locations.add(new Location(row, column+1)); //location 8
            locations.add(new Location(row, column+2)); //location 9
        }
        for (Location loc: locations){
            if (loc.getColourAt(gameBoard).equals(Colour.FIRE)){
                return false;
            }
        }
        return true;
    }

    //assuming the square is at location 8 of 3x3 card
    private boolean atBottomCard(String gameBoard, int rowLength, int colLength){
        List<Location> locations = new ArrayList<>();
        if (column>colLength-2 || column<1 || row<2){
            return false;
        }
        else if (column<=colLength-2 && column>=1 && row>=2){
            locations.add(new Location(row-2, column-1)); //location 1
            locations.add(new Location(row-2,column)); //location 2
            locations.add(new Location(row-2, column+1)); //location 3
            locations.add(new Location(row-1, column-1)); //location 4
            locations.add(new Location(row-1, column)); //location 5
            locations.add(new Location(row-1, column+1)); //location 6
            locations.add(new Location(row, column-1)); //location 7
            locations.add(new Location(row, column+1)); //location 9
        }
        for (Location loc: locations){
            if (loc.getColourAt(gameBoard).equals(Colour.FIRE)){
                return false;
            }
        }
        return true;
    }

    //assuming the square is at location 9 of 3x3 card
    private boolean atBottomRightCard(String gameBoard, int rowLength, int colLength){
        List<Location> locations = new ArrayList<>();
        if (column<2 || row<2){
            return false;
        }
        else if (column>=2 && row>=2){
            locations.add(new Location(row-2, column-2)); //location 1
            locations.add(new Location(row-2,column-1)); //location 2
            locations.add(new Location(row-2, column)); //location 3
            locations.add(new Location(row-1, column-2)); //location 4
            locations.add(new Location(row-1, column-1)); //location 5
            locations.add(new Location(row-1, column)); //location 6
            locations.add(new Location(row, column-2)); //location 7
            locations.add(new Location(row, column-1)); //location 8
        }
        for (Location loc: locations){
            if (loc.getColourAt(gameBoard).equals(Colour.FIRE)){
                return false;
            }
        }
        return true;
    }

    @Override
    //for testing equal lists of locations
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Location location = (Location) obj;
        return row == location.row && column == location.column;
    }
    @Override
    public int hashCode() {
        return Objects.hash(row, column);
    }
    @Override
    public String toString() {
        return "Location (" + row +", "+column+")";
    }

}
