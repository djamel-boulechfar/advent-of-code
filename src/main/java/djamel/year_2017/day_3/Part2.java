package djamel.year_2017.day_3;

import java.util.HashMap;
import java.util.Map;

public class Part2 {
    private static final int INPUT = 325489;
    private static final Direction[] DIRECTIONS = Direction.getOrderedDirections();
    private static final int INITIAL_X_Y = 0;

    public static void main(String[] args) {
        Map<Coordinates, Integer> spiral = new HashMap<>();

        // Position initiale
        int x = INITIAL_X_Y;
        int y = INITIAL_X_Y;

        // Première valeur de la spirale
        int value = 1;
        Coordinates firstCoordinates = new Coordinates(x, y);
        spiral.put(firstCoordinates, value);

        int directionIndex = 0;
        int stepsToDo = 1;

        while (value <= INPUT) {

            for (int i = 0; i < 2; i++) {
                Direction direction = DIRECTIONS[directionIndex % 4];

                for (int j = 0; j < stepsToDo; j++) {
                    if (value <= INPUT) {
                        switch (direction) {
                            case UP -> y--;
                            case RIGHT -> x++;
                            case DOWN -> y++;
                            case LEFT -> x--;
                            default -> throw new IllegalArgumentException("Direction invalide : " + direction);
                        }
                        Coordinates coordinates = new Coordinates(x, y);
                        value = coordinates.getAdjacentSquaresSum(spiral);
                        spiral.put(coordinates, value);
                    }
                }

                directionIndex++;
            }

            stepsToDo++;
        }

        System.out.println("Valeur : " + value);
    }
}

