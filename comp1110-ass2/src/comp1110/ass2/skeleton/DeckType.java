package comp1110.ass2.skeleton;

/**
 * @author Yunmeng Zhang
 * **/
public enum DeckType {
    CIRCLE,CROSS,SQUARE,TRIANGLE,NONE;

    public static DeckType fromChar(char type){
        return switch (type){
            case 'A' -> CIRCLE;
            case 'B' -> CROSS;
            case 'C' -> SQUARE;
            case 'D' -> TRIANGLE;
            default -> NONE;
        };
    }

    public char toChar(){
        return switch (this){

            case CIRCLE -> 'A';
            case CROSS -> 'B';
            case SQUARE -> 'C';
            case TRIANGLE -> 'D';
            case NONE -> 'N';
        };
    }
}


