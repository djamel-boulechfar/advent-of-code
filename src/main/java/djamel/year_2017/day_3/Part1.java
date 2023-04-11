package djamel.year_2017.day_3;

public class Part1 {
    private static final int INPUT = 325489;
    // private static final Direction[] DIRECTIONS = Direction.getOrderedDirectionsInOrder();
    private static final String[] DIRECTIONS = new String[] { "RIGHT", "UP", "LEFT", "DOWN" };

    public static void main(String[] args) {
        int[][] grid = new int[1000][1000];
        int x = 500;
        int y = 500;

        int value = 1;
        grid[x][y] = value;
        value++;

        int d = 0;
        int stepsToDo = 1;

        while (value <= INPUT) {

            for (int i = 0; i < 2; i++) {
                String direction = DIRECTIONS[d % 4];

                for (int j = 0; j < stepsToDo; j++) {
                    if (value <= INPUT) {
                        switch (direction) {
                            case "UP" -> y--;
                            case "RIGHT" -> x++;
                            case "DOWN" -> y++;
                            case "LEFT" -> x--;
                            default -> throw new IllegalArgumentException("Direction invalide : " + direction);
                        }
                        grid[x][y] = value;
                        value++;
                    }
                }

                d++;
            }

            stepsToDo++;
        }

        int distance = Math.abs(x - 500) + Math.abs(y - 500);

        System.out.println(distance);

//        System.out.println(grid[500][500]);
//        System.out.println(grid[501][500]);
//        System.out.println(grid[501][499]);
//        System.out.println(grid[500][499]);
//        System.out.println(grid[499][499]);
//        System.out.println(grid[499][500]);
//        System.out.println(grid[499][501]);
//        System.out.println(grid[500][501]);
//        System.out.println(grid[501][501]);
//        System.out.println(grid[502][501]);
//        System.out.println(grid[502][500]);
//        System.out.println(grid[502][499]);
//        System.out.println(grid[502][498]);
//        System.out.println(grid[501][498]);
//        System.out.println(grid[500][498]);
    }
}
