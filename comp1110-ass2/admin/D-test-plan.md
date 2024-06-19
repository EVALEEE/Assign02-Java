# TEST PLAN



### Player Class:
1. `removePathwayCardTest(String placementString):`<br><br>
**Test if the cards on hand were got removed after placing all the cards.**<br><br>
**Example:** <br>After player(who has card string “AaBcCaDc” in her hand) placed card ‘a’ from C Deck, after the method proceeded, the string should be updated to “AaBcCDc”.

### FireTileBag Class:
1. `removeFireTileTest (String placementString):`<br><br>
   **Test after player draw a fire title from the fire bag, the state of the bag(in the form of a string) should be updated.**<br><br>
   **Example:** <br>If there is only one fire title in the bag, after the drawing, the bag should return an empty string.

### FireTile Class:
1. `isAdjacentToFireTest ():`<br><br>
   **Test if the fire title that’s going to place on the board is near to at least one old-placed fire tile(which means need to be adjacent to at least one fire title on the board).**<br><br>

2. `isValidTest ():`<br><br>
**Test the validity of a fire title, first, it cannot overlap other title that already on the board, second, 
it should be adjacent to another fire tile that already on the board.**


### Island Class:
1. `updateLayoutByCardsTest (String placementString):`<br><br>
**Test if the layout of the state of the island was updated after the placement of the card.**<br><br>

2. `updateCatLocationTest (Location catNewLocation):`<br><br>
**Test the cat’s location is or is not correct after the movement from the player.**<br><br>

3. `InitialIslandConstructValidTest (String challengeLayoutString):`<br><br>
**Test the initialize of the island from the very beginning is valid or not.**<br><br>
**Example:** <br>Give a certain string of the initial layout, check if the output layout string is the same.


### Cat Class:
1. `parseMovementStringTest(String movementString):`<br><br>
**Parse the cat movement string.**<br><br>
2. `catTest(String challengeCatString):`<br><br>
**Parse the cat string.**<br><br>
3. `reachRaftTest():`<br><br>
**Test if cats reach the raft.**<br><br>
**Example:** <br>To check whether the cats reach the raft, using method getAdjacentLocation() to get the square next to the raft card.


### ExhaustedCat Class:
1. `markExhaustedTest():`<br><br>
**Mark if cat is exhausted after moving.**<br><br>
**Example:** <br>After calling parseMovementString(String movementString) in Cat Class, the cat become exhausted. Parse the exhausted cat string, change the status of cat to "exhausted" and call markExhausted() to mark the status of cat.<br><br>


### Raft Class:
1. `setLocationTest(String challengeRaftString):`<br><br>
**Set the location of raft.**<br><br>
**Example:** <br>Set the location of raft card based on challenge raft string.

### Location Class:
1. `isOnIslandTest(Island island):`<br><br>
**Test if the location on the island.**<br><br>
2. `isOverlapFireTest():`<br><br>
**If the location overlap a fire.**<br><br>
3. `isOverlapCatTest():`<br><br>
**If the location overlap the cat.**<br><br>
4. `isOverlapRaftTest():`<br><br>
**If the location overlap the raft, which cannot be tested in isolation.**<br><br>
**Example:** <br>After calling “getAdjacentLocations(Island island)” the 8 locations of raft are found. And then examine if the location overlap any of the 8 raft locations.<br><br>
5. `getAdjacentLocationsTest(Island island):`<br><br>
**Get the adjacent 8 (or less if the location is on the boundary) locations.**

### PathwayCard Class:
1. `isValidTest():`<br><br>
**If the card placement is valid, which cannot be tested in isolation.**<br><br>
**Example:** <br>After calling “isOnIsland()”, ”isOverlapFire()”, ”isOverlapCat()”, and “isOverlapRaft()”, the validation can be confirmed.

### Board Class:
1. `canFindCatPathTest(Location start, Location end):`<br><br>
**Find the possible path for cat between start location and end location where the color matches. It cannot be tested in isolation.**<br><br>
**Example:** <br> After calling “parseMovementString(String movementString)” in Cat Class, the start location and end location are found. 
And then go through the squares in vertical or horizontal orientation to examine if there exists a path that all the colors of squares are the same as that of the cat.<br><br>
2. `canFireTilePlaceTest(String action):`<br><br>
**If there is a place this fire tile can be placed validly.**




