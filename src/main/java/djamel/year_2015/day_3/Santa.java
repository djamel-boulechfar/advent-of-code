package djamel.year_2015.day_3;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Santa {
    private static final String NORTH = "^";
    private static final String EAST = ">";
    private static final String SOUTH = "v";
    private static final String WEST = "<";

    private int[][] housesGrid;
    private int x;
    private int y;

    public Santa(int[][] housesGrid) {
        this.housesGrid = housesGrid;
        this.x = housesGrid.length / 2;
        this.y = housesGrid[0].length / 2;
    }

    public void move(String direction) {
        switch (direction) {
            case NORTH:
                this.y--;
                break;
            case EAST:
                this.x++;
                break;
            case SOUTH:
                this.y++;
                break;
            case WEST:
                this.x--;
                break;
            default:
                break;
        }
    }

    public void deliverPresent() {
        this.housesGrid[this.x][this.y]++;
    }
}
