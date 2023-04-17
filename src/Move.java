public enum Move {
    LEFT_TO_MIDDLE(Peg.LEFT, Peg.MIDDLE),
    LEFT_TO_RIGHT(Peg.LEFT, Peg.RIGHT),
    MIDDLE_TO_LEFT(Peg.MIDDLE, Peg.LEFT),
    MIDDLE_TO_RIGHT(Peg.MIDDLE, Peg.RIGHT),
    RIGHT_TO_LEFT(Peg.RIGHT, Peg.LEFT),
    RIGHT_TO_MIDDLE(Peg.RIGHT, Peg.MIDDLE);

    public final Peg from;
    public final Peg to;

    private Move(Peg from, Peg to) {
        this.from = from;
        this.to = to;
    }

    public static Move move(Peg from, Peg to) {
        if (from == null || to == null) {
            throw new NullPointerException("Pegs cannot be null");
        }
        if (from == to) {
            throw new IllegalArgumentException("Pegs must be different");
        }
        for (Move move : Move.values()) {
            if (move.from == from && move.to == to) {
                return move;
            }
        }
        throw new IllegalStateException("No move found from " + from + " to " + to);
    }
    
}
