## Tasks and Evaluation Criteria

The assignment is broken down into a set of tasks, and a set of deliverables.
Deliverables are listed on [the deliverables page](https://cs.anu.edu.au/courses/comp1110/assessments/deliverables/) of the course web site.
There is not a direct correspondence between tasks and deliverables: some of the tasks are included in more than one deliverable (e.g., tasks 2, 5, 6, and 7 are included in both [D2C](https://cs.anu.edu.au/courses/comp1110/assessments/deliverables/#D2C) and [D2F](https://cs.anu.edu.au/courses/comp1110/assessments/deliverables/#D2F) meaning you can get marks twice for completing these tasks), and some of the deliverables require you to do things not in the list of tasks below (e.g., integration test and code review).

We have added issues for the tasks as listed here, but they will not be added to your existing fork. Instead, you can import the issues from `issues.csv`, the same way you did in the last few labs.

#### Part One

* Task 0: Read the instructions, fork the assignment repo, and complete admin files.
  Detailed instructions for this task are found in [course deliverable D2A](https://cs.anu.edu.au/courses/comp1110/assessments/deliverables/#D2A).
* Task 1: Make an initial *design* for your implementation of the game, including skeleton code.
  Read the description of [deliverable D2B](https://cs.anu.edu.au/courses/comp1110/assessments/deliverables/#D2B) for more information about what your design should include.
* Task 2: Implement the `isBoardStringWellFormed` method in the `RaceToTheRaft` class.
* Task 3: Begin the implementation of your design by storing appropriate information in instances of your classes.
  We will provide you with a string representation of game states (see section [String Representation](#string-representation) below) for testing purposes. Your first implementation step should be to internalise this representation, i.e., to create instances of the classes in your design initialised with the information from the string representation.
* Task 4: Implement the `displayState()` method in the Viewer class, allowing you to visualise a game state String.
* Task 5: Implement the `drawFireTile()` method in the `RaceToTheRaft` class.
* Task 6: Implement the `chooseChallenge()` method in the `RaceToTheRaft` class.
* Task 7: Implement the `drawHand()` method in the `RaceToTheRaft` class.

Part one of the assignment culminates in [deliverable D2C](https://cs.anu.edu.au/courses/comp1110/assessments/deliverables/#D2C).

#### Part Two

In part two, your main objective is to create a working game, using JavaFX to implement a playable graphical version of the game in a 1100x650 window.

Aside from the window size, the details of exactly how the game appears and how players interact with it are **intentionally** left up to you. The images above are for illustration purposes only.
A good game implementation should be faithful to the game rules, as described above, and have an easy-to-use, intuitive user interface.
However, the evaluation (marking) of Tasks 7 and 15 below are not all-or-nothing: we will consider different aspects of the game, and the degree or extent to which each of them has been achieved.

The only **firm** requirements are that:
* you use Java 17 and JavaFX,
* it runs in a 1100x650 window, and
* that it is executable on a standard lab machine from a jar file called `game.jar`.

Your game must successfully run from `game.jar` from within another
user's (i.e.  your tutor's) account on a standard lab machine (in
other words, your game must not depend on features not self-contained
within that jar file and the Java runtime).

* Task 8: Implement the `applyPlacement()` method in the `RaceToTheRaft` class.
* Task 9: Implement the `moveCat()` method in the `RaceToTheRaft` class.
* Task 10: Implement the `initialiseChallenge()` method in the `RaceToTheRaft` class.
* Task 11: Implement a basic working game that allows pieces to be moved, without checking validity of placements or 
  movements (see [more details below](#what-counts-as-a-basic-playable-game)).
* Task 12: Implement the `isPlacementValid()` method in the `RaceToTheRaft` class.
* Task 13: Implement a fully working game. A fully working game extends the basic game by enforcing all game rules,
    ensuring players can in any situation choose all actions that they should be
    allowed to take and none that they shouldn't, ending the game when it is
    over and declaring the game won or lost. You will receive partial marks for the game rules you have enforced 
  even if you have not implemented Task 14 or Task 15. 
* Task 14: Implement the `isCatMovementValid()` method in the `RaceToTheRaft` class.
* Task 15: Implement the `isGameOver()` method in the `RaceToTheRaft` class.

There is no requirement to complete the tasks in the order they are numbered, and you are not required to complete all of them.
Part two of the assignment culminates in [deliverable D2F](https://comp.anu.edu.au/courses/comp1110/assessments/deliverables/#D2F).

## Task Hints
In this section we provide some specific guidance for some of the more open-ended tasks. This
section may be updated throughout the semester in response to questions from other students, so make sure to do an
upstream pull of this assignment repo at least once per week.


### Task 3: Constructors
For the avoidance of doubt, the following lists the minimal information that we expect to find in your object-oriented representation of the game by the time your D2C submission is complete for task 3. Please note that this is the bare minimum to collect the task 3 mark in D2C. Depending on the details of your design, you may need to store additional information in your objects in order to be eligible for the marks associated with good object-oriented design.

- The state of each square in the board
- The location of cats on the board
- The cards in each of the decks
- The cards in the player's hand
- The status of each cat: Exhausted or Not exhausted
- The fire tiles in the bag
- The shape of the fire tiles
- The state of each square on a pathway card
- The state represented by the Challenge string

It is not enough to simply store the strings provided to you in the `Utility` class in your objects. 

Again, this list is not prescriptive, and is not designed to constrain your design; marks will be awarded in the 
sections for good object-oriented design to groups who find clever ways of storing multiple of these pieces of 
information compactly, or in ways that make it easier to program the game logic. The list simply serves to give you 
a reference point for what your tutor will be checking for to confirm you've met the requirements of task 3 of the 
assignment. 

### Task 4: Viewer

#### Hints on object-oriented design

As mentioned above, it is critically important that you follow an object-oriented design in this assignment, and your GUI classes are no exception. In order to receive full marks for good object-oriented design in  the D2C deliverable, you should not be reading GUI information from the String directly, but should instead be reading from your skeleton classes. For example, something like this is good.

```java
// Some code here...
MyObj newObj = new MyObj(inputString);
int myX = newObj.x;
int myY = newObj.y;
// Code continues, using myX and myY to display a piece in the window
```

But something of this pattern would be considered poor use of object orientation, and thus **not be acceptable**.

```java
// Some code here...
int myX = Integer.parseInt(inputString.substring(12,16));
int myY = Integer.parseInt(inputString.substring(16,20));
// Code continues, using myX and myY to display a piece in the window
```

Remember that in task 3 you should already have made constructors that convert from the String representation into 
your own object-oriented representation, so it is as simple as just creating a new object to meet this requirement. 
Doing so will make your code far more understandable, allow you to use the methods you've made in your backend to 
better effect, and ensure that you're demonstrating your understanding of object orientation.

#### Information to be displayed

The information that needs to be displayed by your viewer is similar to the information that needed to be placed 
into a field in task 3. You only need to display information found in the `board` and `hand` strings. Other 
information about the game is not supplied in Task 3 and as such does not need to be displayed.
The things we're looking for in your viewer:
- The colour/state of each square on the board
- The cats on the board (we want to see the cat on the board, not a list of locations)
- The cards in a player's hand

What follows are some steps you may want to follow to help you with implementing the viewer. You do not have to follow this procedure, but it's here to help if you get stuck:
1. Draw the board, with each coloured square in it's correct location
2. Display cats in their correct `row` and `column` coordinates
3. Display the cards in a player's hand in the North orientation

### Task 11 and Task 13: Game

#### What counts as a "basic playable game"?
A basic working game is one where some of the rules may not have been implemented, but the game has some 
functionality that allows it to be visualised. In particular, a basic working game will allow for a full 
visualisation of the current state of the game. It should contain the following things: 

- Cards can be rotated and moved. The rules for valid card placements may not have been fully implemented. 
- Cats can be moved. The rules for valid cat movements may not have been fully implemented.
- Cards can be drawn from the decks. The rules surrounding drawing cards may not have been fully implemented.
- Fire tiles can be drawn from the bag, rotated and moved. The rules around valid fire tile placements may not have 
  been fully implemented.

The game should be relatively intuitive to play, and should not require knowledge of the backend of the game or the String encoding system.


#### What counts as a "fully working game"?
A fully working game is one where all the rules have been implemented. As such, players should be able to play 
through their turns, rotate and place cards or fire, move cats and check whether a game is completed.
The game should be intuitive to play, using a reasonable user interface that any person who has no knowledge of java,
or the assignment, could pick up and play (for instance, one of your friends or family members). You may wish to 
create an instruction/how-to-play screen to teach people how to use the interface. You can assume they already know 
our modified rules for Race to the Raft. ie: You do not need to write down the game rules.

### Detailed task criteria breakdown

In coarse terms, the distribution of assignment marks over different categories is:
* Timely and correct completion of admin tasks and files: 6%
* Healthy use of git and teamwork: 6%
* Viewable, playable, and interesting game: 26%
* Appropriate object-oriented design and implementation, and other aspects of code quality: 25%
* Unit-testable tasks: 24%
* Developing and using tests: 6%
* Code review: 3%
* Presentation: 4%
