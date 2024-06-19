package comp1110.ass2.skeleton;

import comp1110.ass2.Utility;

import java.util.Arrays;

/**
 * @author Tengyi Zhang, Jialin Li
 **/

public class Cat {

    public Colour colour;
    //The state of Cat Card
    private char[][] state;
    private int id;
    private Location startLocation;
    private Location endLocation;
    private String movement;
    private boolean exhausted = false;

    public static String getACat(int id) {
        return Utility.CAT_CARDS[id];
    }

    public Cat(Colour colour, char[][] state, int id, Location startLocation, Location endLocation, String movement,
               boolean exhausted) {
        this.colour = colour;
        this.state = state;
        this.id = id;
        this.startLocation = startLocation;
        this.endLocation = endLocation;
        this.movement = movement;
        this.exhausted = exhausted;
    }

    public Cat() {
    }

    public Cat(String catString) {
        // Parse catString
        parseCatString(catString);
    }

    /**
     * Place the cat card
     *
     * @param boardLayout the layout of the board with details
     * @param catString the cat string splided from challenge string
     * @author Jialin Li
     */
    public void placeCatCards(String[][] boardLayout, String catString) {
        int cRow;// Variable to store the row position of the card
        int cCol;// Variable to store the column position of the card

        // Convert the string representing cards into a character array for processing
        char[] CatStringCharArray = catString.toCharArray();

        // Iterate through the characters of the catString, incrementing by 5 to read each card's data block
        for (int i = 1; i < CatStringCharArray.length; i += 5) {
            char[] charArray = Utility.CAT_CARDS[Integer.parseInt(String.valueOf(CatStringCharArray[i]))].toCharArray();
            //3gffgGfggg
            // Calculate the row index for placing the card on the board:
            // Multiply the tens digit by 10 and add the units digit to get the full index
            cRow = Integer.parseInt(String.valueOf(CatStringCharArray[i + 1])) * 10
                    + Integer.parseInt(String.valueOf(CatStringCharArray[i + 2]));
            // Calculate the column index for placing the card similarly
            cCol = Integer.parseInt(String.valueOf(CatStringCharArray[i + 3])) * 10
                    + Integer.parseInt(String.valueOf(CatStringCharArray[i + 4]));

            // Place parts of the card on the boardLayout in a 3x3 grid
            boardLayout[cRow][cCol] = String.valueOf(charArray[1]);
            boardLayout[cRow][cCol + 1] = String.valueOf(charArray[2]);
            boardLayout[cRow][cCol + 2] = String.valueOf(charArray[3]);
            boardLayout[cRow + 1][cCol] = String.valueOf(charArray[4]);
            boardLayout[cRow + 1][cCol + 1] = String.valueOf(charArray[5]);
            boardLayout[cRow + 1][cCol + 2] = String.valueOf(charArray[6]);
            boardLayout[cRow + 2][cCol] = String.valueOf(charArray[7]);
            boardLayout[cRow + 2][cCol + 1] = String.valueOf(charArray[8]);
            boardLayout[cRow + 2][cCol + 2] = String.valueOf(charArray[9]);
        }
    }

    /**
     * Parses a string representing a cat and updates the attributes of the Cat object accordingly.
     *
     * @param catString The string representing the cat, including its ID, color, and state.
     * @author Tengyi Zhang
     */
    public void parseCatString(String catString) {
        // Parse the color of the cat from the string.
        this.colour = Colour.fromChar(catString.charAt(5));//the colour (and location) of cat
        // Parse the ID of the cat from the string.
        this.id = Integer.parseInt(catString.substring(0, 1));
        // Initialize the state array for the cat.
        this.state = new char[3][3];
        int index = 1; // from the second char
        for (int i = 0; i < 3; i++) {//The state of each square in cat card.
            for (int j = 0; j < 3; j++) {
                this.state[i][j] = catString.charAt(index++);
//                System.out.println(i + "," + j + ": " +state[i][j]);
            }
        }
    }

    /**
     * Parses a string representing a movement and updates the attributes of the Movement object accordingly.
     *
     * @param movementString The string representing the movement, including the color and coordinates of the start and end locations.
     * @author Tengyi Zhang
     */
    public void parseMovementString(String movementString) {
        // Parse movement string
        // Parse the color of the cat from the movement string.
        char catColour = Character.toLowerCase(movementString.charAt(0));
        Colour colour = Colour.fromChar(catColour);
        // Get the row and column of start and end location of cat.
        int startRow = Integer.parseInt(movementString.substring(1, 3));
        int startCol = Integer.parseInt(movementString.substring(3, 5));
        int endRow = Integer.parseInt(movementString.substring(5, 7));
        int endCol = Integer.parseInt(movementString.substring(7, 9));

        // Update the colour, start location and end location of the Movement object.
        this.colour = colour;
        this.startLocation = new Location(startRow, startCol);
        this.endLocation = new Location(endRow, endCol);
    }


    public Colour getColour() {
        return colour;
    }

    public void setColour(Colour colour) {
        this.colour = colour;
    }

    public char[][] getState() {
        return state;
    }

    public void setState(char[][] state) {
        this.state = state;
    }

    public int getId() {
        return id;
    }

    public Location getStartLocation() {
        return startLocation;
    }

    public void setStartLocation(Location startLocation) {
        this.startLocation = startLocation;
    }

    public Location getEndLocation() {
        return endLocation;
    }

    public void setEndLocation(Location endLocation) {
        this.endLocation = endLocation;
    }


    @Override
    public String toString() {
        return "Cat{" +
                "colour=" + colour +
                ", state=" + Arrays.toString(state) +
                ", id=" + id +
                ", startLocation=" + startLocation +
                ", endLocation=" + endLocation +
                ", movement='" + movement + '\'' +
                ", exhausted=" + exhausted +
                '}';
    }
}

