import java.util.Objects;

public class Card implements Comparable<Card> {
    private Rank rank;
    private Suit suit;

    public Card(Rank rank, Suit suit) {
        if (rank == null || suit == null) throw new NullPointerException();

        this.rank = rank;
        this.suit = suit;
    }

    @Override
    public int compareTo(Card card) {
        int compareVal = suit.compareTo(card.suit);
        if (compareVal == 0) compareVal = rank.compareTo(card.rank);
        return compareVal;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Card)) return false;

        Card card = (Card) obj;

        return (compareTo(card) == 0);
    }

    public Rank getRank() {
        return rank;
    }

    public Suit getSuit() {
        return suit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rank, suit);
    }

    @Override
    public String toString() {
        return rank.toString() + suit.toString();
    }
}
