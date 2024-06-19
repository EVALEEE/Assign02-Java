package comp1110.ass2.skeleton;

/**
 * @author Tengyi Zhang
 * **/
public class Card {
    private Deck deck;
    private char id;

    // Initialize cards
    public Card(Deck deck, char id) {
        this.deck = deck;
        this.id = id;
    }

    public Deck getDeck() {
        return deck;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    public char getId() {
        return id;
    }

    public void setId(char id) {
        this.id = id;
    }
}

