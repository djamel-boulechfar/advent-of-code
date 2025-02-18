package advent_of_code.year_2024.day_8;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Position {
    private int x;
    private int y;

    public Position getSymetricalPosition(Position position) {
        int dx = position.x - this.x;
        int dy = position.y - this.y;

        return new Position(this.x + 2 * dx, this.y + 2 * dy);
    }

    public Difference getDifferenceWith(Position position) {
        int dx = position.x - this.x;
        int dy = position.y - this.y;

        return new Difference(dx, dy);
    }
}
