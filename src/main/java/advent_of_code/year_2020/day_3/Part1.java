package advent_of_code.year_2020.day_3;

import advent_of_code.utils.MyFileReader;

import java.io.IOException;
import java.util.List;

public class Part1 {
    public static void main(String[] args) throws IOException {
        List<String> input = MyFileReader.readFileAndReturnStringList("src/main/java/advent_of_code/year_2020/day_3/input.txt");

        Area area = new Area(input);

        Slope slope = new Slope(3, 1);

        int encounteredTree = area.countEncounteredTreesWithGivenSlope(slope);

        System.out.println("Arbres rencontrés : " + encounteredTree);
    }
}
