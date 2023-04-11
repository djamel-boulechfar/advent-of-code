package djamel.year_2017.day_3;

public enum Direction {
    UP,
    RIGHT,
    DOWN,
    LEFT;

    public static Direction[] getOrderedDirectionsInOrder() {
        return new Direction[] { RIGHT, UP, LEFT, DOWN };
    }
}
