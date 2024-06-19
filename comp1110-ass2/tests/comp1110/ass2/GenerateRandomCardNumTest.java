package comp1110.ass2;

import comp1110.ass2.skeleton.Player;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Timeout(value = 500, unit = TimeUnit.MILLISECONDS)
public class GenerateRandomCardNumTest {
    @Test
    public void testGenerateRandomCardNum5() {
        // Set up
        Set<Integer> numbersSet = new HashSet<>();
        char[] charArray = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k'};
        int num = 5; // number of distinct numbers to generate
        // Execute
        int[] result = Player.generateRandomCardnum(numbersSet, num, charArray);

        // Verify that the result is an integer array
        Assertions.assertTrue(result instanceof int[], "The result should be an instance of int[]");

        // Verify the length
        Assertions.assertEquals(num, result.length, "The length of the result array should be equal to 'num'");
        Assertions.assertEquals(num, numbersSet.size(), "The size of the numbers set should be equal to 'num'");

        // Check for uniqueness in the result
        Set<Integer> resultCheckSet = new HashSet<>();
        for (int number : result) {
            Assertions.assertTrue(resultCheckSet.add(number) && number >= 0 && number < charArray.length,
                    "Each number in the array should be unique and valid"
            );
        }
    }

    @Test
    public void testGenerateRandomCardNum1() {
        // Set up
        Set<Integer> numbersSet = new HashSet<>();
        char[] charArray ={'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k'};
        int num = 1; // number of distinct numbers to generate
        // Execute
        int[] result = Player.generateRandomCardnum(numbersSet, num, charArray);

        // Verify that the result is an integer array
        Assertions.assertTrue(result instanceof int[], "The result should be an instance of int[]");

        // Verify the length
        Assertions.assertEquals(num, result.length, "The length of the result array should be equal to 'num'");
        Assertions.assertEquals(num, numbersSet.size(), "The size of the numbers set should be equal to 'num'");

        // Check for uniqueness in the result
        Set<Integer> resultCheckSet = new HashSet<>();
        for (int number : result) {
            Assertions.assertTrue(resultCheckSet.add(number) && number >= 0 && number < charArray.length,
                    "Each number in the array should be unique and valid"
            );
        }
    }

    @Test
    public void testGenerateRandomCardNum3() {
        // Set up
        Set<Integer> numbersSet = new HashSet<>();
        char[] charArray = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k'};
        int num = 3; // number of distinct numbers to generate
        // Execute
        int[] result = Player.generateRandomCardnum(numbersSet, num, charArray);

        // Verify that the result is an integer array
        Assertions.assertTrue(result instanceof int[], "The result should be an instance of int[]");

        // Verify the length
        Assertions.assertEquals(num, result.length, "The length of the result array should be equal to 'num'");
        Assertions.assertEquals(num, numbersSet.size(), "The size of the numbers set should be equal to 'num'");

        // Check for uniqueness in the result
        Set<Integer> resultCheckSet = new HashSet<>();
        for (int number : result) {
            Assertions.assertTrue(resultCheckSet.add(number) && number >= 0 && number < charArray.length,
                    "Each number in the array should be unique and valid"
            );
        }
    }
}
