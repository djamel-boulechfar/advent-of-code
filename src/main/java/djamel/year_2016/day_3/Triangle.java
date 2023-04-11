package djamel.year_2016.day_3;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Arrays;

@Data
@AllArgsConstructor
public class Triangle {
    private int side1;
    private int side2;
    private int side3;

    public boolean isPossible() {
        int[] sides = { side1, side2, side3 };
        Arrays.sort(sides);
        return sides[0] + sides[1] > sides[2];
    }

    @Override
    public String toString() {
        return "[" + side1 + ", " + side2 + ", " + side3 + "]";
    }
}
