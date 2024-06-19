package comp1110.ass2;

import comp1110.ass2.skeleton.Island;
import comp1110.ass2.skeleton.Location;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

@Timeout(value = 500, unit = TimeUnit.MILLISECONDS)
public class LocationTest {
    @Test
    public void testIsOnIsland() {
        //return true if the location is on island
        Location loc1 = new Location(5, 5);
        Island island = new Island("LNSNLASA");  //15*18 Island
        assertTrue(loc1.isOnIsland(island.getHeight(),island.getWidth()), "Location should be on island");

        //return false with negative location
        Location loc2 = new Location(-1, 5);
        assertFalse(loc2.isOnIsland(island.getHeight(),island.getWidth()), "Location should not be on island because row is negative");

        //return false with the location beyond the boundary
        Location loc3 = new Location(20,20);
        assertFalse(loc3.isOnIsland(island.getHeight(),island.getWidth()), "Location should not be on island because the location is beyond boundary");
    }

    @Test
    public void testIsOnIslandBoundary() {
        //return true if the location is on boundary
        Location loc1 = new Location(15, 10);
        Island island = new Island("LNSNLASA");  //size of 15*18
        assertTrue(loc1.isOnIsland(island.getHeight(),island.getWidth()), "Location on boundary should be considered on island");

        //return false if the location is just beyond the boundary
        Location loc2 = new Location(16,10);
        assertFalse(loc2.isOnIsland(island.getHeight(),island.getWidth()), "Location is beyond boundary");
    }

    @Test
    public void testGetAdjacentLocations() {
        //test when 4 adjacent locations are on island
        Location loc = new Location(5, 5);
        List<Location> expected= List.of(
                new Location(4, 5),  // North
                new Location(6, 5),  // South
                new Location(5, 4),  // West
                new Location(5, 6)   // East
        );

        List<Location> actual = loc.getAdjacentLocations("""
fffffffffrrfffffff
fffffffffrRfffffff
fffffffffrrfffffff
fffgffpbgpgbgpgygy
fffgGfpryyggrgbpyp
fffgggygbgrybyybgr
ffffffbpgbgpygbrpb
ffffffrybpyrbbpygy
ffffffrgybryygpbrr
ffffffbbrgyybgpgpb
ffffffrypprbyrgbyr
ffffffyggyprgybpgy
fffffyypyggybrrgyp
ffffYypbbbrgpygrow
fffyyygyrbprgpbbww
""");
        assertEquals(expected, actual, "Arrays should be the same");

    }

    @Test
    public void testOutOfRangeAdjacent(){
        //test when not all adjacent locations are on island
        Location loc  = new Location(15,18);
        List<Location> expected = List.of(
                new Location(14,18),  //North
                new Location(15,17)  //West

        );
        List<Location> actual = loc.getAdjacentLocations("""
fffffffffrrfffffff
fffffffffrRfffffff
fffffffffrrfffffff
fffgffpbgpgbgpgygy
fffgGfpryyggrgbpyp
fffgggygbgrybyybgr
ffffffbpgbgpygbrpb
ffffffrybpyrbbpygy
ffffffrgybryygpbrr
ffffffbbrgyybgpgpb
ffffffrypprbyrgbyr
ffffffyggyprgybpgy
fffffyypyggybrrgyp
ffffYypbbbrgpygrow
fffyyygyrbprgpbbww
""");
        assertEquals(expected, actual, "Arrays should have the length of 4");


    }
}
