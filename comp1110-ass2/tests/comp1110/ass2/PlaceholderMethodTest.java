package comp1110.ass2;

import comp1110.ass2.Placeholder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.concurrent.TimeUnit;

@Timeout(value = 500, unit = TimeUnit.MILLISECONDS)
public class PlaceholderMethodTest {
    @Test
    public void givesCorrectValue() {
        Assertions.assertEquals(Placeholder.placeholderMethod(), 3.9, 0.01);
    }
}
