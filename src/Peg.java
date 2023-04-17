public enum Peg {
    LEFT,
    MIDDLE,
    RIGHT;

    public static Peg other(Peg p1, Peg p2) {
        if (p1 == null || p2 == null) {
            throw new NullPointerException("Both pegs must be non-null");
        }
        if (p1 == p2) {
            throw new IllegalArgumentException("The two pegs must be different");
        }
        if (p1 != LEFT && p1 != MIDDLE && p1 != RIGHT) {
            throw new IllegalArgumentException("Invalid peg: " + p1);
        }
        if (p2 != LEFT && p2 != MIDDLE && p2 != RIGHT) {
            throw new IllegalArgumentException("Invalid peg: " + p2);
        }
        if ((p1 == LEFT && p2 == RIGHT) || (p1 == RIGHT && p2 == LEFT)) {
            return MIDDLE;
        } else if ((p1 == LEFT && p2 == MIDDLE) || (p1 == MIDDLE && p2 == LEFT)) {
            return RIGHT;
        } else {
            return LEFT;
        }
    }
}
