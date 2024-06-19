## Code Review

Reviewed by: JiaLin Li, u7829183

Reviewing code written by: TengYi Zhang, u7829536

Component: class: Board, method: isValidChar(char c)

### Comments


### 1. Best Features of the Code

- **Simplicity**: The functions are straightforward and serve clear purposes, such as checking the validity of characters and manipulating a board array.
- **Reusability**: `isValidChar()` can be reused in various parts of a program to ensure that only valid characters (defined by the `Colour` enum) are processed.
- **Encapsulation**: The methods for manipulating the board (`getBoardArray`, `getBoardArrayAt`, and `setBoardArray`) encapsulate the access to the board array, which helps maintain control over how the array is accessed and modified.

### 2. Documentation

- **Inline Comments**: The `isValidChar` method includes a comment explaining the conversion of characters to lowercase, which is a helpful clarification.

### 3. Program Decomposition

- **Method Organization**: The methods are organized to handle specific aspects of board management (retrieval and setting of board elements) and character validation, which is good for maintaining separation of concerns.

### 4. Java Coding Conventions

- **Method Naming**: The method names (`getBoardArray`, `getBoardArrayAt`, `setBoardArray`) are clear and follow Java naming conventions, indicating actions performed on the board array.
- **Variable Naming**: Variables are named appropriately。
- **Consistent Style**: The style appears consistent within the code, using clear method calls and control structures.

### 5. Potential Errors

- **Array Index Out of Bounds**: The `getBoardArrayAt` and `setBoardArray` methods access the array `boardArray` directly using indices, which can lead to `ArrayIndexOutOfBoundsException` if the `row` or `col` parameters are not validated before use.
- **Case Sensitivity**: The method `isValidChar` converts the input character to lowercase and compares it against presumably constant character values. If these constants (`Colour.BLUE.toChar()` and others) are not strictly lowercase, the comparison might fail. This situation, however, seems to be handled correctly by converting the input to lowercase, as long as the constants are also defined in lowercase.

### Conclusion

The code snippet generally follows Java conventions and is simple and structured. But, care should be taken to handle potential exceptions related to array access, which is a common source of runtime errors in array-manipulating code.


