package advent_of_code.year_2024.day_6;

public enum Direction {
    NORTH,
    EAST,
    SOUTH,
    WEST;

    public Direction turnNinetyDegrees() {
        return switch (this) {
            case NORTH -> EAST;
            case EAST -> SOUTH;
            case SOUTH -> WEST;
            case WEST -> NORTH;
        };
    }
}
