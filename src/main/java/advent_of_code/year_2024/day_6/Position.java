package advent_of_code.year_2024.day_6;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Position {
    private int x;
    private int y;

    public Position getNextPosition(Direction direction) {
        return switch (direction) {
            case NORTH -> new Position(this.getX(), this.getY() - 1);
            case EAST -> new Position(this.getX() + 1, this.getY());
            case SOUTH -> new Position(this.getX(), this.getY() + 1);
            case WEST -> new Position(this.getX() - 1, this.getY());
        };
    }
}
