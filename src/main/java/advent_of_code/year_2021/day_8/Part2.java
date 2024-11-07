package advent_of_code.year_2021.day_8;

import advent_of_code.utils.MyFileReader;

import java.io.IOException;
import java.util.*;

public class Part2 {
    public static void main(String[] args) throws IOException {
        List<String> input = MyFileReader.readFileAndReturnStringList("src/main/java/advent_of_code/year_2021/day_8/input.txt");

        int searchedNumberOccurrences = 0;
        for (String line : input) {
            String[] splitLine = line.split("\\|");
            String[] values = splitLine[0].split(" ");
            String[] outputValues = splitLine[1].split(" ");

            Map<Integer, String> numberPerCode = new HashMap<>();

            String oneCode = Arrays.stream(values)
                    .filter(value -> 2 == value.length())
                    .findFirst()
                    .orElseThrow();
            String fourCode = Arrays.stream(values)
                    .filter(value -> 4 == value.length())
                    .findFirst()
                    .orElseThrow();
            String sevenCode = Arrays.stream(values)
                    .filter(value -> 3 == value.length())
                    .findFirst()
                    .orElseThrow();
            String eightCode = Arrays.stream(values)
                    .filter(value -> 7 == value.length())
                    .findFirst()
                    .orElseThrow();

            numberPerCode.put(1, oneCode);
            numberPerCode.put(4, fourCode);
            numberPerCode.put(7, sevenCode);
            numberPerCode.put(8, eightCode);

            String rightBorder1, rightBorder2;

            Set<Character> oneCodeCharacters = Set.of(oneCode.toCharArray());
        }

        System.out.println("Part 1 answer = " + searchedNumberOccurrences);
    }
}
