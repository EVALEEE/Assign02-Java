package comp1110.ass2.skeleton;

import java.util.List;

import static comp1110.ass2.Utility.CHALLENGES;

/**
 * @author JiaLin Li, Yunmeng Zhang
 */

public class Challenge {
    public int challengeNumber;// The identifying number associated with a challenge.
    public State[] layout;// The initial layout of a board defined by a certain challenge.
    public List<Cat> catLocation;// The initial information of cats.
    public String Fire;
    public String Cat;
    public String Raft;


    /**
     * @param challengeNumber Input a difficulty num, to get a certain level of Challenge.
     */
    public static String getAChallenge(int challengeNumber) {
        return CHALLENGES[challengeNumber];
    }

    /**
     * @param challengeString
     * @return The layout of the board at the beginning.
     */
    public State[] getLayout(String challengeString) {
        return layout;
    }

    /**
     * @param challengeString
     * @return Fire's initial layout Stirng, ex: F098876858658652
     */
    public String getFire(String challengeString) {
        return challengeString.substring('F', 'C');
    }

    /**
     * @param challengeString
     * @return Cat's initial layout Stirng, ex: C098876858658652
     */
    public String getCat(String challengeString) {
        return challengeString.substring('C', 'R');
    }

    /**
     * @param challengeString
     * @return Raft's initial layout Stirng, ex: R098876858658652
     */
    public String getRaft(String challengeString) {
        return challengeString.substring('R');
    }

    /**
     * Constructor
     *
     * @param challengeNumber
     * @param layout
     * @param catLocation
     * @param fire
     * @param cat
     * @param raft
     * @author Jialin Li
     */
    public Challenge(int challengeNumber, State[] layout, List<comp1110.ass2.skeleton.Cat> catLocation, String fire, String cat, String raft) {
        this.challengeNumber = challengeNumber;
        this.layout = layout;
        this.catLocation = catLocation;
        Fire = fire;
        Cat = cat;
        Raft = raft;
    }

    /**
     * Given a difficulty level, choose a random challenge of the given difficulty from the CHALLENGES array above.
     * <p>
     * Difficulty 0: "Starter" Challenge numbers 1-12
     * Difficulty 1: "Junior" Challenge numbers 13-28
     * Difficulty 2: "Expert" Challenge numbers 29-44
     * Difficulty 3: "Master" Challenge numbers 45-60
     * </p>
     *
     * @param difficulty the given difficulty level
     * @return A random challenge of the given difficulty from the CHALLENGES array in this class.
     * @author Yunmeng Zhang
     */
    public static String newChallenge(int difficulty) {
        assert difficulty >= 0 && difficulty <= 5;
        int start = 0;
        int count = 4;

        if (difficulty == 0) {
            start = 0;
            count = 4;
        } else if (difficulty == 1) {
            start = 4;
            count = 4;
        } else if (difficulty == 2) {
            start = 8;
            count = 8; // Difficulty 2 has 8 challenges
        } else if (difficulty == 3) {
            start = 16;
            count = 8;  // Difficulty 3 has 8 challenges
        } else if (difficulty == 4) {
            start = 24;
            count = 8;  // Difficulty 4 has 8 challenges
        } else if (difficulty == 5) {
            start = 32;
            count = 7;  // Difficulty 5 has 7 challenges
        }

        return CHALLENGES[(int) (Math.random() * count + start)];
    }


    public State[] getLayout() {
        return layout;
    }

    public void setLayout(State[] layout) {
        this.layout = layout;
    }

    public String getFire() {
        return Fire;
    }

    public void setFire(String fire) {
        Fire = fire;
    }

    public String getCat() {
        return Cat;
    }

    public void setCat(String cat) {
        Cat = cat;
    }

    public String getRaft() {
        return Raft;
    }

    public void setRaft(String raft) {
        Raft = raft;
    }
}
