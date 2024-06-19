package comp1110.ass2.skeleton;

/**
 * @author Tengyi Zhang
 * **/

public class ExhaustedCat{
    Colour colour;
    Location exCatLocation;

    public ExhaustedCat(String catString) {
        // Parse catString
        parseExCatString(catString);
    }

    public Colour getColour() {
        return colour;
    }

    public void setExCatLocation(Location exCatLocation) {
        this.exCatLocation = exCatLocation;
    }

//    public ExhaustedCat(String catString) {
//        super(catString);
//    }


    public Location getExCatLocation() {
        return exCatLocation;
    }

    /**
     * Parses a string representing an expelled cat and updates the attributes of the Cat object accordingly.
     *
     * @param exCatString The string representing the expelled cat, including its color and coordinates.
     * @author Tengyi Zhang
     */
    public void parseExCatString(String exCatString){
        // Parse the color of the expelled cat from the string.
        char catColour = Character.toLowerCase(exCatString.charAt(0));
        this.colour = Colour.fromChar(catColour);
        // Get the row and column of start and end location of cat.
        int exCatRow = Integer.parseInt(exCatString.substring(1, 3));
        int exCatColumn = Integer.parseInt(exCatString.substring(3, 5));
        // Update the location of the exhausted cat.
        this.exCatLocation = new Location(exCatRow, exCatColumn);
    }
}
