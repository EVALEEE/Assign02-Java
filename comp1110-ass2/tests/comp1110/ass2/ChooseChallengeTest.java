package comp1110.ass2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Timeout(value = 500, unit = TimeUnit.MILLISECONDS)
public class ChooseChallengeTest {

    private final static String[] CHALLENGE_STRINGS = new String[]{
            "LNSNLASAF000300060012001503030903C112033060340009R01215",
            "LNSNLASAF000300090015030309031203C106033000650012R11215",
            "LNSNLASAF000300060012001506030903C000093030341203R11215",
            "LNSNLASAF00030006001203030903C00015100091120350603R21215",
            "SNSNSASAF00030015C00006000094030350012R30909",
            "SASASASAF0009001203000600C10006300154000050900R10915",
            "SNSNSASAF000300060012C00303100094090350015R30915",
            "SNSASASAF000600090012C10015306004000350900R10915",
            "LASALASAF000000030009001503000900C00012312004060050006R20915",
            "LNSNLASAF0003000600120903120312061215C00303100094001551212R10915",
            "LNSALASAF000300090015C00012100061120050900R20915",
            "LNSNLASAF0003000600090303C0060311203303064001250015R11215",
            "LESALESAF1200120912121215C109003060040300R10615",
            "SNSASASAF0003000600090015C109003001240600R20315",
            "LNSNLASAF0003000600090303090309121215C0001211203309153121240015R00603",
            "LASNLASAF00000003000903000909C30006300154001250600R31209",
            "SNSNSASAF0003000903030903C00006100153060340012R30906",
            "SNSNSASAFC1000930003300154000650012R30909",
            "LNSNLASAF000300060009001203030903C00912100151060351203R21215",
            "SNSNSASAF00030009001503030915C10012306034000650903R10615",
            "SNSNSASAF00030006000906030915C00015109033001240303R10912",
            "SNSNSASAF000300150303C0000600009300123090340603R30912",
            "LESNLASAF00090015060009060909C01209103003001251212R20615",
            "LNSNLASAF00030006000903030603C00012200153090351203R11215",
            "LALAF1500C000032000630000R11506",
            "LNSNLASAF000300060012001503030312060306121203C109033120640009R20315",
            "LASAFC100003000640003R20906",
            "LNSNLASSF00030006001203031203C00009106033001540903R11212",
            "LNSNLSSSF000600121212C0120920003303064031251206R00009",
            "LASNLASAF00000003000903000909C30006300154060050012R31209",
            "LASNLESAF0000000603001203C000030031530309309094060950600R31209",
            "LNSNLSSSF0003000609091212C000093091250012R31209",
            "LNSNLNSAFC1090330003300154000660012R31209",
            "SASNSNSAF0000001206090903C2000630300R20015",
            "SNSNSASAF00030009060309030915C10303300124000650015R30912",
            "LESALASAF001200151200120912121215C00600100093030041203R20615",
            "LNSNLASAF000300060012001503030603061212031206C100093031240903R20315",
            "LNSNLASAF0003000900150303030903150603090312091215C100122000661203R01212",
            "LNSNLASAF00030006030306030606060909030906C000123000940015R31203",
    };

    private String getDifficulty(String challenge) {
        if(isCorrectChallenge(challenge, 0,4)) {
            return "difficulty 0";
        }
        if(isCorrectChallenge(challenge, 4,8)) {
            return "difficulty 1";
        }
        if(isCorrectChallenge(challenge, 8,16)) {
            return "difficulty 2";
        }
        if(isCorrectChallenge(challenge, 16,24)) {
            return "difficulty 3";
        }
        if(isCorrectChallenge(challenge, 24,32)) {
            return "difficulty 4";
        }
        if(isCorrectChallenge(challenge, 32,39)) {
            return "difficulty 5";
        }
        return "unknown difficulty";
    }

    private boolean isCorrectChallenge(String challenge, int start, int end) {
        for (int i = start; i < end; i++) {
            if (challenge.equals(CHALLENGE_STRINGS[i])) return true;
        }
        return false;
    }

    private void testChallenges(int difficulty, int start, int end) {
        List<String> uniques=new LinkedList<>();
        for(int i=0;i<16;i++) {
            String response=RaceToTheRaft.chooseChallenge(difficulty);
            Assertions.assertTrue(isCorrectChallenge(response, start, end), "Expected a challenge of difficulty "+difficulty+", but got "+response+", which is of "+getDifficulty(response));
            if(!uniques.contains(response)) {
                uniques.add(response);
            }
        }
        Assertions.assertTrue(uniques.size()>=3, "Expected at least three different challenges, got "+uniques.size());
    }

    @Test
    public void testBeginnerChallenge() {
        testChallenges(0, 0, 4);
        testChallenges(1, 4, 8);
    }

    @Test
    public void testCampaignsOneToThree() {
        testChallenges(2,8,16);
        testChallenges(3,16,24);
        testChallenges(4,24,32);
    }

    @Test
    public void testCampaignFour() {
        testChallenges(5,32,39);
    }
}
