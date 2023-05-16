package advent_of_code.year_2017.day_3;

public enum Direction {
    UP,
    RIGHT,
    DOWN,
    LEFT;

    public static Direction[] getOrderedDirections() {
        return new Direction[] { RIGHT, UP, LEFT, DOWN };
    }
}
