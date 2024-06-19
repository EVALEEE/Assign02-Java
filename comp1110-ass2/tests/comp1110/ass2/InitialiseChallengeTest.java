package comp1110.ass2;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
public class InitialiseChallengeTest {

    char[][] boardToChars(String board)
    {
        String[] lines=board.split("\n");
        char[][] ret=new char[lines.length][lines[0].length()];
        for(int r=0;r<lines.length;r++)
        {
            ret[r]=lines[r].toCharArray();
        }
        return ret;
    }
    void testBoardSatisfiesFire(String challenge, String board) {
        String firepart=challenge.substring(challenge.indexOf('F')+1, challenge.indexOf('C'));
        char[][] charboard = boardToChars(board);
        while(!firepart.isEmpty())
        {
            int row=Integer.parseInt(firepart.substring(0,2));
            int column=Integer.parseInt(firepart.substring(2,4));
            firepart=firepart.substring(4);
            for(int r=row;r<row+3;r++)
            {
                for(int c=column;c<column+3;c++)
                {
                    assertEquals('f', charboard[r][c], "Expected fire field at row "+r+" column "+c+", but found "+charboard[r][c]);
                }
            }
        }
        String islandPart=challenge.substring(0, challenge.indexOf('F'));
        int islandTileCount=islandPart.length()/2;
        int rowOffset=0;
        int columnOffset=0;
        for(int i=0;i<islandTileCount;i++)
        {
            char islandType=islandPart.charAt(i*2);
            char orChar=islandPart.charAt(i*2+1);
            int fireRowsStart=-1;
            int fireRowsEnd=-1;
            int fireColumnsStart=-1;
            int fireColumnsEnd=-1;
            switch(orChar) {
                case 'A':
                    break;
                case 'N':
                    fireRowsStart = rowOffset;
                    fireRowsEnd = rowOffset + islandType == 'L' ? 9 : 6;
                    fireColumnsStart = columnOffset;
                    fireColumnsEnd = columnOffset + 3;
                    break;
                case 'E':
                    fireRowsStart = rowOffset;
                    fireRowsEnd = rowOffset + 3;
                    fireColumnsStart = columnOffset;
                    fireColumnsEnd= columnOffset + 9;
                    break;
                case 'S':
                    fireRowsStart = rowOffset;
                    fireRowsEnd = rowOffset + islandType == 'L' ? 9 : 6;
                    fireColumnsStart = columnOffset + 6;
                    fireColumnsEnd = columnOffset + 9;
                    break;
                case 'W':
                    fireRowsStart = rowOffset + islandType == 'L' ? 3 : 6;
                    fireRowsEnd = rowOffset + islandType == 'L' ? 9 : 6;
                    fireColumnsStart = columnOffset;
                    fireColumnsEnd = columnOffset + 9;
                    break;
            }
            for(int r=fireRowsStart;r<fireRowsEnd;r++)
            {
                for(int c=fireColumnsStart;c<fireColumnsEnd;c++)
                {
                    assertEquals('f', charboard[r][c], "Expected fire field based on island board at row "+r+" column "+c+", but found "+charboard[r][c]);
                }
            }
            if(i%2==0)
            {
                rowOffset=islandType=='L'?9:6;
            }
            else
            {
                rowOffset=0;
                columnOffset=9;
            }
        }
    }
    void testBoardSatisfiesCats(String challenge, String board) {
        String catsPart=challenge.substring(challenge.indexOf("C")+1, challenge.indexOf("R"));
        char[][] charboard = boardToChars(board);
        while(!catsPart.isEmpty())
        {
            int catIndex=Integer.parseInt(catsPart.substring(0,1));
            int catRow=Integer.parseInt(catsPart.substring(1,3));
            int catCol=Integer.parseInt(catsPart.substring(3,5));
            String catCardString=Utility.CAT_CARDS[catIndex];
            for(int r=0;r<3;r++)
            {
                for(int c=0;c<3;c++)
                {
                    char expected=catCardString.charAt(1+r*3+c);
                    char actual=charboard[r+catRow][c+catCol];
                    assertEquals(expected, actual, "Expected field '"+expected+"' at row "+(r+catRow)+" column "+(c+catCol)+", but found '"+actual+"'");
                }
            }
            catsPart=catsPart.substring(5);
        }
    }
    void testBoardSatisfiesRaft(String challenge, String board) {
        String raftPart=challenge.substring(challenge.indexOf("R")+1);
        char[][] charboard = boardToChars(board);
        int raftIndex=Integer.parseInt(raftPart.substring(0,1));
        int raftRow=Integer.parseInt(raftPart.substring(1,3));
        int raftCol=Integer.parseInt(raftPart.substring(3,5));
        String raftCardString=Utility.RAFT_CARDS[raftIndex];
        for(int r=0;r<3;r++)
        {
            for(int c=0;c<3;c++)
            {
                char expected=raftCardString.charAt(1+r*3+c);
                char actual=charboard[r+raftRow][c+raftCol];
                assertEquals(expected, actual, "Expected field '"+expected+"' at row "+(r+raftRow)+" column "+(c+raftCol)+", but found '"+actual+"'");
            }
        }
    }
    void testBoardSatisfiesSize(String challenge, String board) {
        char[][] charboard = boardToChars(board);
        String islandPart=challenge.substring(0, challenge.indexOf('F'));
        int islandTileCount=islandPart.length()/2;
        int rows=0;
        int columns=9;
        if(islandTileCount>2)
        {
            columns+=9;
        }
        if(islandPart.charAt(0)=='L') {
            rows += 9;
        } else {
            rows+=6;
        }
        if(islandPart.charAt(2)=='L') {
            rows += 9;
        } else {
            rows+=6;
        }
        assertEquals(rows, charboard.length, "Expected board of height "+rows+", but got "+charboard.length+" rows");
        assertEquals(columns, charboard[0].length, "Expected board of width "+columns+", but got "+charboard[0].length+" columns");
    }


    final static String[] challenges=new String[]{
            "LNSNLASAF000300060012001506030903C000093030341203R11215",
            "LASAFC100003000640003R20906",
            "LNSNLASAF0003000900150303030903150603090312091215C100122000661203R01212",
            "LALAF1500C000032000630000R11506",
            "SASASASAF0009001203000600C10006300154000050900R10915",
            "LESALASAF001200151200120912121215C00600100093030041203R20615"
    };

    static Stream<String> getChallenges()
    {
        return Arrays.stream(challenges);
    }

    @ParameterizedTest
    @MethodSource("getChallenges")
    public void testChallenge(String challenge)
    {
        String board=RaceToTheRaft.initialiseChallenge(challenge);
        testBoardSatisfiesSize(challenge, board);
        testBoardSatisfiesFire(challenge, board);
        testBoardSatisfiesCats(challenge, board);
        testBoardSatisfiesRaft(challenge, board);
    }

    @Test
    public void testBoardSizes() {
        for(String challenge : challenges) {
            String board=RaceToTheRaft.initialiseChallenge(challenge);
            testBoardSatisfiesSize(challenge, board);
        }
    }
    @Test
    public void testBoardFire() {
        for(String challenge : challenges) {
            String board=RaceToTheRaft.initialiseChallenge(challenge);
            testBoardSatisfiesFire(challenge, board);
        }
    }
    @Test
    public void testBoardCats() {
        for(String challenge : challenges) {
            String board=RaceToTheRaft.initialiseChallenge(challenge);
            testBoardSatisfiesCats(challenge, board);
        }
    }
    @Test
    public void testBoardRaft() {
        for(String challenge : challenges) {
            String board=RaceToTheRaft.initialiseChallenge(challenge);
            testBoardSatisfiesRaft(challenge, board);
        }
    }
}
