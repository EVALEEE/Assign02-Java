package comp1110.ass2.skeleton;

/**
 * @author Tengyi Zhang
 * **/
public enum Colour {
    BLUE, RED, YELLOW, PURPLE, GREEN, RAFT, WILDCARD, FIRE, NONE;
    public static Colour fromChar(char colour) {
        return switch (colour) {
            case 'b' -> BLUE;
            case 'r' -> RED;
            case 'y' -> YELLOW;
            case 'p' -> PURPLE;
            case 'g' -> GREEN;
            case 'o' -> RAFT;
            case 'w' -> WILDCARD;
            case 'f' -> FIRE;
            case 'n' -> NONE;
            default -> throw new IllegalStateException("Unexpected value: " + colour);
        };
    }
    public char toChar() {
        return switch (this) {
            case BLUE -> 'b';
            case RED -> 'r';
            case YELLOW -> 'y';
            case PURPLE -> 'p';
            case GREEN -> 'g';
            case RAFT -> 'o';
            case WILDCARD -> 'w';
            case FIRE -> 'f';
            case NONE -> 'n';
        };
    }

}
