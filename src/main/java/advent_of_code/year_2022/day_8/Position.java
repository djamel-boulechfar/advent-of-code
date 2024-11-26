package advent_of_code.year_2022.day_8;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Position {
    private int x;
    private int y;

    public static Position move(Position position, Direction direction) {
        return switch (direction) {
            case TOP -> new Position(position.getX(), position.getY() - 1);
            case RIGHT -> new Position(position.getX() + 1, position.getY());
            case BOTTOM -> new Position(position.getX(), position.getY() + 1);
            case LEFT -> new Position(position.getX() - 1, position.getY());
        };
    }
}
