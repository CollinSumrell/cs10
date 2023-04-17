import java.util.*;

public class CribbageHand {
    public static final Map<Rank, Integer> CARD_VALUES;
    static {
        CARD_VALUES = Map.ofEntries(
            Map.entry(Rank.TWO, 2),
            Map.entry(Rank.THREE, 3),
            Map.entry(Rank.FOUR, 4),
            Map.entry(Rank.FIVE, 5),
            Map.entry(Rank.SIX, 6),
            Map.entry(Rank.SEVEN, 7),
            Map.entry(Rank.EIGHT, 8),
            Map.entry(Rank.NINE, 9),
            Map.entry(Rank.TEN, 10),
            Map.entry(Rank.JACK, 10),
            Map.entry(Rank.QUEEN, 10),
            Map.entry(Rank.KING, 10),
            Map.entry(Rank.ACE, 1)
        );
    }

    public final List<Card> cards;
    
    public CribbageHand(Card c1, Card c2, Card c3, Card c4) {
        if (c1 == null || c2 == null || c3 == null || c4 == null) {
            throw new NullPointerException("All cards must be non-null");
        }
        this.cards = Collections.unmodifiableList(List.of(c1, c2, c3, c4));
    }

    public static Set<Set<Card>> powerSet(List<Card> cards) {
        Set<Set<Card>> sets = new HashSet<>();
        if (cards.isEmpty()) {
            sets.add(new HashSet<>());
            return sets;
        }
        List<Card> list = new ArrayList<>(cards);
        Card head = list.get(0);
        List<Card> rest = list.subList(1, list.size());
        for (Set<Card> set : powerSet(rest)) {
            Set<Card> newSet = new HashSet<>();
            newSet.add(head);
            newSet.addAll(set);
            sets.add(newSet);
            sets.add(set);
        }
        return sets;
    }

    public Set<Set<Card>> fifteens(Card starter) {
        Set<Card> allCards = new HashSet<>(cards);
        allCards.add(starter);
        Set<Set<Card>> powerSet = powerSet(new ArrayList<>(allCards));
    
        Set<Set<Card>> fifteenSets = new HashSet<>();
        for (Set<Card> subset : powerSet) {
            int total = subset.stream().mapToInt(card -> CARD_VALUES.get(card.getRank())).sum();
            if (total == 15) {
                fifteenSets.add(subset);
            }
        }
    
        return fifteenSets;
    }
    
    

}
