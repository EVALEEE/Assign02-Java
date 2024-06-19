package comp1110.ass2.skeleton;

import comp1110.ass2.Utility;

/**
 * @author JiaLin Li
 */
public class RaftCard {
    public Location location;//The Raft card's location.
    public int ID;//The ID of the raft card.

    public RaftCard() {
    }

    /**
     * Constructor
     *
     * @param location
     * @param ID
     */
    public RaftCard(Location location, int ID) {
        this.location = location;
        this.ID = ID;
    }

    /**
     * Places a raft card on the game board according to the specified positions and card details
     * encoded in the raftString. The raft card covers a 3x3 area on the board.
     *
     * @param boardLayout The 2D array representing the game board layout where the raft card will be placed.
     * @param raftString  A string encoding the raft card's ID and its position on the board.
     *                    The format includes the card ID and row and column indices, e.g., "R010203".
     * @author JiaLin Li
     */
    public void placeRaftCard(String[][] boardLayout, String raftString) {
        char[] RaftStringCharArray = raftString.toCharArray();
        int rRow;
        int rCol;
        char[] charArray = Utility.RAFT_CARDS[Integer.parseInt(String.valueOf(RaftStringCharArray[1]))].toCharArray();
        // Compute the row index from the third and fourth characters of the raftString.
        rRow = Integer.parseInt(String.valueOf(RaftStringCharArray[2])) * 10
                + Integer.parseInt(String.valueOf(RaftStringCharArray[3]));
        // Compute the column index from the fifth and sixth characters of the raftString.
        rCol = Integer.parseInt(String.valueOf(RaftStringCharArray[4])) * 10
                + Integer.parseInt(String.valueOf(RaftStringCharArray[5]));

        boardLayout[rRow][rCol] = String.valueOf(charArray[1]);
        boardLayout[rRow][rCol + 1] = String.valueOf(charArray[2]);
        boardLayout[rRow][rCol + 2] = String.valueOf(charArray[3]);
        boardLayout[rRow + 1][rCol] = String.valueOf(charArray[4]);
        boardLayout[rRow + 1][rCol + 1] = String.valueOf(charArray[5]);
        boardLayout[rRow + 1][rCol + 2] = String.valueOf(charArray[6]);
        boardLayout[rRow + 2][rCol] = String.valueOf(charArray[7]);
        boardLayout[rRow + 2][rCol + 1] = String.valueOf(charArray[8]);
        boardLayout[rRow + 2][rCol + 2] = String.valueOf(charArray[9]);
    }

    /**
     * Constructor
     *
     * @return
     */
    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
}
