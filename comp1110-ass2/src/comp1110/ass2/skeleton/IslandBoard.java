package comp1110.ass2.skeleton;

import comp1110.ass2.Utility;
import jdk.jshell.execution.Util;

import java.util.Random;

/**
 * @author Yunmeng Zhang, Jialin Li
 **/
public class IslandBoard {
    private String size; // "S" means small board，"L"means large board
    private String rotation; // "N", "E", "S", "W", "A"
    private String[] layout;

    private int id;

    public IslandBoard(String size, String rotation) {
        this.size = size;
        this.rotation = rotation;
    }

    public IslandBoard(String size, String rotation, String[] layout, int id) {
        this.size = size;
        this.rotation = rotation;
        this.layout = layout;
        this.id = id;
    }

    public String[] getLayout() {
        return layout;
    }

    /**
     * Set layout of board
     * @author Yunmeng Zhang
     */
    public void setLayout(String size, int id) {
        if (size == "L") {
            if (id == 1) {
                this.layout = Utility.SQUARE_BOARDS[0];
            }
            if (id == 2) {
                this.layout = Utility.SQUARE_BOARDS[1];
            }
            if (id == 3) {
                this.layout = Utility.SQUARE_BOARDS[2];
            }
            if (id == 4) {
                this.layout = Utility.SQUARE_BOARDS[3];
            }
        } else if (size == "S") {
            if (id == 1) {
                this.layout = Utility.RECTANGLE_BOARDS[0];
            }
            if (id == 2) {
                this.layout = Utility.RECTANGLE_BOARDS[1];
            }
            if (id == 3) {
                this.layout = Utility.RECTANGLE_BOARDS[2];
            }
            if (id == 4) {
                this.layout = Utility.RECTANGLE_BOARDS[3];
            }
        }
    }

    public int getId() {
        return id;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

//    public static String getRotation(String rotation, char orientation) {
//
//        char[][] matrix = new char[3][3];
//        int index = 0;
//        // Fill the matrix from the string
//        for (int i = 0; i < 3; i++) {
//            for (int j = 0; j < 3; j++) {
//                matrix[i][j] = rotation.charAt(index++);
//            }
//        }
//
//        switch (orientation) {
//            case 'A':
//                matrix = randomRotate(matrix);
//                break;
//            case 'W':
//                matrix = rotate180(matrix);
//                break;
//            case 'E':
//                matrix = rotateClockwise(matrix);
//                break;
//            case 'S':
//                // No rotation needed
//                break;
//        }
//
//        // Convert the matrix back to a string
//        StringBuilder sb = new StringBuilder();
//        for (int i = 0; i < 3; i++) {
//            for (int j = 0; j < 3; j++) {
//                sb.append(matrix[i][j]);
//            }
//        }
//
//        return sb.toString();
//    }
//
//    private static char[][] randomRotate(char[][] matrix) {
//        Random random = new Random();
//        int rotationChoice = random.nextInt(4); // 0, 1, 2, or 3
//
//        switch (rotationChoice) {
//            case 0:
//                // No rotation
//                break;
//            case 1:
//                matrix = rotateClockwise(matrix);
//                break;
//            case 2:
//                matrix = rotate180(matrix);
//                break;
//            case 3:
//                matrix = rotateAnticlockwise(matrix);
//                break;
//        }
//        return matrix;
//    }
//
//    private static char[][] rotateClockwise(char[][] matrix) {
//        char[][] result = new char[3][3];
//        for (int i = 0; i < 3; i++) {
//            for (int j = 0; j < 3; j++) {
//                result[j][2 - i] = matrix[i][j];
//            }
//        }
//        return result;
//    }
//
//    private static char[][] rotateAnticlockwise(char[][] matrix) {
//        char[][] result = new char[3][3];
//        for (int i = 0; i < 3; i++) {
//            for (int j = 0; j < 3; j++) {
//                result[2 - j][i] = matrix[i][j];
//            }
//        }
//        return result;
//    }
//
//    private static char[][] rotate180(char[][] matrix) {
//        char[][] result = new char[3][3];
//        for (int i = 0; i < 3; i++) {
//            for (int j = 0; j < 3; j++) {
//                result[2 - i][2 - j] = matrix[i][j];
//            }
//        }
//        return result;
//    }


    public void setRotation(String rotation, char orientation) {
        this.rotation = rotation;
    }


    @Override
    public String toString() {
        return "Island{" +
                "size='" + size + '\'' +
                ", rotation='" + rotation + '\'' +
                '}';
    }
}
