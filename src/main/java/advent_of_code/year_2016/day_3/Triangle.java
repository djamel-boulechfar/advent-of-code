package advent_of_code.year_2016.day_3;

import lombok.Data;

import java.util.Arrays;

@Data
public class Triangle {
    private int[] sides;

    public Triangle(int value1, int value2, int value3) {
        this.sides = new int[] { value1, value2, value3 };
        Arrays.sort(this.sides);
    }

    public boolean isPossible() {
        return this.sides[0] + this.sides[1] > this.sides[2];
    }

    @Override
    public String toString() {
        return "[" + this.sides[0] + ", " + this.sides[1] + ", " + this.sides[2] + "]";
    }
}
