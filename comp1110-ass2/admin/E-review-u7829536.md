## Code Review

Reviewed by: Tengyi Zhang, u7829536

Reviewing code written by: Yunmeng Zhang, u7714241

Component: Class: IslandBoard, Island

### Comments 

### 1. Best Features of the Code

- **Well-structured**:The use of the IslandBoard and Island class to encapsulate related data and behavior is a good 
  design choice. 
- **Object-Oriented Design**:
  - 1.The parseLayout() method correctly parses the layout string to create IslandBoard objects.
  - 2. The island class implements a clear logic for parsing the layout string and IslandBoard objects 
       based on the provided input.


### 2. Code Documentation

- **Lack of Class description**: A brief description of what the island and islandboard class represents and its main 
  responsibilities will help.
- **Some of them lack of Method description**: The parseLayout(String layout) is well-documented. However, the 
  method in IslandBoard Class have few method description.

### 3. Program Decomposition

- **Appropriate program decomposition**:The Class Island is responsible for managing the layout and dimensions of an 
  island, which is an appropriate program decomposition. 
- **Clear construtors and methods**: The constructor and methods provide a clear separation of concerns and 
  functionality.

### 4. Java Code Conventions

- **Good code conventions**: The code mostly follows Java code conventions: methods and variables are camelCase, and 
  braces are used consistently.
- **Spaces needed**: Adding spaces between method parameters and after if statements would improve 
  readability.
- **Numbers replaced**: Could think of replacing some numbers (e.g., 18, 15, 12, 9, and 6) with named constants for 
  better maintainability.

### 5. Error Handling

- **Without validating**:The parseLayout(), getHeight() and getWidth() method assumes a specific format or layout for 
  the layout string without validating it. This could lead to errors or unexpected behavior if the input doesn't match the expected format.
 