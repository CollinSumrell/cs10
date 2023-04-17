import java.util.*;

public class TowerOfHanoi {
    private Map<Peg, Deque<Integer>> diskStacks;
    
    public TowerOfHanoi(int numDisks, Peg start) {
        if (numDisks <= 0) {
            throw new IllegalArgumentException("Number of disks must be positive");
        }
        if (start == null) {
            throw new NullPointerException("Starting peg cannot be null");
        }
        diskStacks = new HashMap<>();
        for (Peg peg : Peg.values()) {
            diskStacks.put(peg, new ArrayDeque<>());
        }
        for (int i = numDisks; i > 0; i--) {
            diskStacks.get(start).push(i);
        }
    }

    public Deque<Integer> getDiskStack(Peg peg) {
        if (peg == null) {
            throw new NullPointerException("Peg cannot be null.");
        }
        return new ArrayDeque<>(diskStacks.get(peg));
    }

    public void moveDisk(Move move) {
        if (move == null) {
            throw new NullPointerException("Move cannot be null");
        }
        Deque<Integer> fromStack = diskStacks.get(move.from);
        Deque<Integer> toStack = diskStacks.get(move.to);
    
        if (fromStack.isEmpty()) {
            throw new IllegalArgumentException("There are no disks on the first peg.");
        }
    
        Integer diskToMove = fromStack.peek();
        if (!toStack.isEmpty() && toStack.peek() < diskToMove) {
            throw new IllegalArgumentException("Disk radius is larger than the top disk on the second peg.");
        }
    
        toStack.push(fromStack.pop());
    }    

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("  LEFT: ");
        sb.append(dequeToStringReverse(diskStacks.get(Peg.LEFT)));
        sb.append(System.lineSeparator());
        sb.append("MIDDLE: ");
        sb.append(dequeToStringReverse(diskStacks.get(Peg.MIDDLE)));
        sb.append(System.lineSeparator());
        sb.append(" RIGHT: ");
        sb.append(dequeToStringReverse(diskStacks.get(Peg.RIGHT)));
        return sb.toString();
    }

    private static String dequeToStringReverse(Deque<Integer> deque) {
        if (deque.isEmpty()) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Iterator<Integer> iterator = deque.descendingIterator();
        while (iterator.hasNext()) {
            sb.append(iterator.next());
            if (iterator.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public static List<Move> solve(int numDisks, Peg start, Peg end) {
        if (start == null || end == null) {
            throw new NullPointerException("Pegs cannot be null.");
        }
        if (numDisks < 0) {
            throw new IllegalArgumentException();
        }
        if (numDisks == 0 || start == end) {
            return new ArrayList<Move>();
        }
        Peg aux = Peg.other(start, end);
        List<Move> result = new ArrayList<Move>();
        if (numDisks == 1) {
            result.add(Move.move(start, end));
        } else {
            result.addAll(solve(numDisks - 1, start, aux));
            result.add(Move.move(start, end));
            result.addAll(solve(numDisks - 1, aux, end));
        }
        return result;
    }
    
    
}
