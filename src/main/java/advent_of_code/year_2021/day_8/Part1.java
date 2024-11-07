package advent_of_code.year_2021.day_8;

import advent_of_code.utils.MyFileReader;

import java.io.IOException;
import java.util.List;

public class Part1 {
    private static final List<Integer> UNIQUE_NUMBER_OF_SEGMENTS = List.of(2, 3, 4, 7);

    public static void main(String[] args) throws IOException {
        List<String> input = MyFileReader.readFileAndReturnStringList("src/main/java/advent_of_code/year_2021/day_8/input.txt");

        int searchedNumberOccurrences = 0;
        for (String line : input) {
            String outputValuesPart = line.split("\\|")[1];
            String[] outputValues = outputValuesPart.split(" ");

            for (String outputValue : outputValues) {
                int outputValueLength = outputValue.length();
                if (UNIQUE_NUMBER_OF_SEGMENTS.contains(outputValueLength)) {
                    searchedNumberOccurrences++;
                }
            }
        }

        System.out.println("Part 1 answer = " + searchedNumberOccurrences);
    }
}
