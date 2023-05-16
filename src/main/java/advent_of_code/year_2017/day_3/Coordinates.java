package advent_of_code.year_2017.day_3;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

@Data
@AllArgsConstructor
public class Coordinates {
    private int x;
    private int y;

    @Override
    public String toString() {
        return "[" + this.x + ", " + this.y + "]";
    }

    public int getAdjacentSquaresSum(Map<Coordinates, Integer> spiral) {
        int sum = 0;

        int adj1 = spiral.get(new Coordinates(this.x + 1, this.y)) != null ? spiral.get(new Coordinates(this.x + 1, this.y)) : 0;
        int adj2 = spiral.get(new Coordinates(this.x + 1, this.y - 1)) != null ? spiral.get(new Coordinates(this.x + 1, this.y - 1)) : 0;
        int adj3 = spiral.get(new Coordinates(this.x, this.y - 1)) != null ? spiral.get(new Coordinates(this.x, this.y - 1)) : 0;
        int adj4 = spiral.get(new Coordinates(this.x - 1, this.y - 1)) != null ? spiral.get(new Coordinates(this.x - 1, this.y - 1)) : 0;
        int adj5 = spiral.get(new Coordinates(this.x - 1, this.y)) != null ? spiral.get(new Coordinates(this.x - 1, this.y)) : 0;
        int adj6 = spiral.get(new Coordinates(this.x - 1, this.y + 1)) != null ? spiral.get(new Coordinates(this.x - 1, this.y + 1)) : 0;
        int adj7 = spiral.get(new Coordinates(this.x, this.y + 1)) != null ? spiral.get(new Coordinates(this.x, this.y + 1)) : 0;
        int adj8 = spiral.get(new Coordinates(this.x + 1, this.y + 1)) != null ? spiral.get(new Coordinates(this.x + 1, this.y + 1)) : 0;

        int[] adjacents = { adj1, adj2, adj3, adj4, adj5, adj6, adj7, adj8 };
        for (int adjacent: adjacents) sum += adjacent;

        return sum;
    }
}
