package comp1110.ass2.skeleton;

/**
 * @author Tengyi Zhang
 * **/

public class State {
    private final Colour colour;
    private Location position;

    State(Colour colour, Location position) {
        this.colour = colour;
        this.position = position;
    }

    public Colour getColour() {
        return colour;
    }

    public Location getPosition() {
        return position;
    }

    public void setPosition(Location position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "State{" +
                "colour=" + colour +
                ", position=" + position +
                '}';
    }
}
