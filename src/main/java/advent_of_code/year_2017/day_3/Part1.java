package advent_of_code.year_2017.day_3;

public class Part1 {
    private static final int INPUT = 325489000;
    private static final Direction[] DIRECTIONS = Direction.getOrderedDirections();
    private static final int INITIAL_X_Y = 0;

    public static void main(String[] args) {
        // Position initiale
        int x = INITIAL_X_Y;
        int y = INITIAL_X_Y;

        // Première valeur de la spirale
        int value = 1;
        value++;

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
                        value++;
                    }
                }

                directionIndex++;
            }

            stepsToDo++;
        }

        int distance = Math.abs(x - INITIAL_X_Y) + Math.abs(y - INITIAL_X_Y);

        System.out.println("Distance : " + distance);
    }
}
