package advent_of_code.year_2023.day_21;

import advent_of_code.utils.MyFileReader;

import java.io.IOException;
import java.util.List;

public class Part1 {
    public static void main(String[] args) throws IOException {
        List<String> input = MyFileReader.readFileAndReturnStringList("src/main/java/advent_of_code/year_2023/day_21/tinput.txt");

        StepCounter stepCounter = new StepCounter(input);

        System.out.println(stepCounter.computeReachablePositions());
    }
}
