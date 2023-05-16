package advent_of_code.year_2020.day_3;

import advent_of_code.utils.MyFileReader;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Part2 {
    public static void main(String[] args) throws IOException {
        List<String> input = MyFileReader.readFileAndReturnStringList("src/main/java/djamel/year_2020/day_3/input.txt");

        Area area = new Area(input);

        List<Slope> slopes = new ArrayList<>();
        slopes.add(new Slope(1, 1));
        slopes.add(new Slope(3, 1));
        slopes.add(new Slope(5, 1));
        slopes.add(new Slope(7, 1));
        slopes.add(new Slope(1, 2));

        BigInteger result = BigInteger.valueOf(1);

        for (Slope slope : slopes) {
            int encounteredTrees = area.countEncounteredTreesWithGivenSlope(slope);
            result = result.multiply(BigInteger.valueOf(encounteredTrees));
        }

        System.out.println("Résultat : " + result);
    }
}
