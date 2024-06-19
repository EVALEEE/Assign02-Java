package comp1110.ass2.skeleton;

import java.util.Random;

/**
 * @author Yunmeng Zhang
 **/
public class FireTileBag {
    private String fireTilesID;
    private FireTile[] fireTiles;

    public FireTileBag(String fireTilesID, FireTile[] fireTiles) {
        this.fireTilesID = fireTilesID;
        this.fireTiles = fireTiles;
    }

    public FireTileBag(String fireTilesID) {
        this.fireTilesID = fireTilesID;
    }

    //task 5
    public String drawFireTiles() {
        if (fireTilesID.isEmpty()) {         //If there are no tiles remaining, return the empty string.
            return "";
        }
        Random random = new Random();
        int chosen = random.nextInt(fireTilesID.length());    //Draws a random fire tile from those remaining in the bag.
        String fireTileDrawn = String.valueOf(fireTilesID.charAt(chosen));

        return fireTileDrawn;
    }
}
