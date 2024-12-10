package advent_of_code.year_2024.day_1;

import advent_of_code.utils.MyFileReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Part1 {
    public static void main(String[] args) throws IOException {
        List<String> input = MyFileReader.readFileAndReturnStringList("src/main/java/advent_of_code/year_2024/day_1/input.txt");

        List<Integer> leftLocationIds = new ArrayList<>();
        List<Integer> rightLocationIds = new ArrayList<>();
        for (String line : input) {
            String[] numbers = line.split("   ");

            if (numbers.length != 2) {
                throw new IllegalArgumentException("Invalid line in input : '" + line + "'");
            }

            leftLocationIds.add(Integer.parseInt(numbers[0]));
            rightLocationIds.add(Integer.parseInt(numbers[1]));
        }

        leftLocationIds.sort(Integer::compare);
        rightLocationIds.sort(Integer::compare);

        assert(leftLocationIds.size() == rightLocationIds.size());

        int distancesSum = 0;
        for (int i = 0; i < leftLocationIds.size(); i++) {
            distancesSum += Math.abs(leftLocationIds.get(i) - rightLocationIds.get(i));
        }

        System.out.println("Part 1 answer = " + distancesSum);

        int res = leftLocationIds.stream()
                .map(leftLocationId ->
                        leftLocationId * (int) rightLocationIds.stream()
                                .filter(leftLocationId::equals)
                                .count()
                )
                .reduce(0, Integer::sum);

        System.out.println("Part 2 answer = " + res);
    }
}
