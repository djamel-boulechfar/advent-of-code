package advent_of_code.year_2023.day_21;

import lombok.Data;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
public class StepCounter {
    private static final int MAX_STEPS = 6;

    private Position startPosition;
    private Set<Position> rockPositions;
    private Set<Position> reachablePositions;

    private int xLimit;
    private int yLimit;

    public StepCounter(List<String> input) {
        this.rockPositions = new HashSet<>();
        this.reachablePositions = new HashSet<>();
        this.init(input);
    }

    private void init(List<String> input) {
        for (int i = 0; i < input.size(); i++) {
            String line = input.get(i);

            for (int j = 0; j < line.toCharArray().length; j++) {
                char c = line.charAt(j);

                if (c == '#') {
                    rockPositions.add(new Position(j, i));
                }

                if (c == 'S') {
                    startPosition = new Position(j, i);
                }
            }
        }

        this.yLimit = input.size();
        this.xLimit = input.get(0).length();
    }

    public int computeReachablePositions() {
        this.reachPositions(this.startPosition, 0);
        return this.reachablePositions.size();
    }

    private void reachPositions(Position currentPosition, int currentSteps) {
        if (!this.rockPositions.contains(currentPosition)) {
            if (currentSteps < MAX_STEPS) {
                for (Direction d : Direction.values()) {
                    Position nextPosition = this.getNextDirection(currentPosition, d);
                    if (!nextPosition.equals(currentPosition)) {
                        System.out.println(nextPosition);
                        this.reachPositions(nextPosition, currentSteps + 1);
                    }
                }
            } else {
                this.reachablePositions.add(currentPosition);
            }
        }
    }

    private Position getNextDirection(Position currentPosition, Direction d) {
        switch (d) {
            case NORTH -> {
                if (currentPosition.getY() - 1 >= 0) {
                    return new Position(currentPosition.getX(), currentPosition.getY() - 1);
                }
            }
            case EAST -> {
                if (currentPosition.getX() + 1 < xLimit) {
                    return new Position(currentPosition.getX() + 1, currentPosition.getY());
                }
            }
            case SOUTH -> {
                if (currentPosition.getY() + 1 < yLimit) {
                    return new Position(currentPosition.getX(), currentPosition.getY() + 1);
                }
            }
            case WEST -> {
                if (currentPosition.getX() - 1 >= 0) {
                    return new Position(currentPosition.getX() - 1, currentPosition.getY());
                }
            }
        }
        return currentPosition;
    }
}
