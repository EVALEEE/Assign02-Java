## Code Review

Reviewed by: Yunmeng Zhang, u7714241

Reviewing code written by: Jialin Li, u7829183

Component: Class: Player

### Comments 

### 1. Best Features of the Code

- **Simplicity:** The class methods are straightforward and named in a way that indicates their functionality, which is a good practice.
- **High Cohesion:** The functions in this module are independent of other classes.
- **Low Coupling:** Methods are independent of each other.

### 2. Code Documentation

- **Sufficient Comments:** Methods `updateCardInHand()` and `generateRandomCardnum()` are well-documented, explaining parameters and the method's purpose.

### 3. Program Decomposition

- **Appropriate Decomposition:** The class seems well-decomposed into methods handling specific functionalities related to Player.
- **Inappropriate Encapsulation:** Fields such as `cardOnHandAmount` and `cardsOnHand` are public, which breaks encapsulation. Other class can modify these fields directly, which can lead to problems.

### 4. Java Code Conventions

- **Naming Convention Issues:** The method `updateCardInHnad` has a typo and should be `updateCardInHand`. And, Java naming conventions are not followed consistently; e.g., `num_of_card` should be `numOfCard`.

### 5. Error Handling

- **Error Handling:** Method `generateRandomCardnum()` do not handle cases where the game state might not allow for the expected operations, such as drawing cards from an empty deck. But there is no error for functions required.


