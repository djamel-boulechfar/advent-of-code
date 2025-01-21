package advent_of_code.year_2024.day_6;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class Guard {
    private Position currentPosition;
    private Direction currentDirection;
    private Set<Position> visitedPositions;

    public Guard(Position startingPosition) {
        this.currentPosition = startingPosition;
        this.currentDirection = Direction.NORTH;
        this.visitedPositions = new HashSet<>();
        this.visitedPositions.add(startingPosition);
    }

    public void move() {
        this.currentPosition = this.currentPosition.getNextPosition(this.currentDirection);
        this.visitedPositions.add(this.currentPosition);
    }

    public void turnNinetyDegree() {
        this.currentDirection = this.currentDirection.turnNinetyDegrees();
    }
}
