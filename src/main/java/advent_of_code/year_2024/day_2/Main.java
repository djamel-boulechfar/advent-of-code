package advent_of_code.year_2024.day_2;

import advent_of_code.utils.MyFileReader;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        List<Report> reports = MyFileReader.readFileAndReturnStringList("src/main/java/advent_of_code/year_2024/day_2/input.txt").stream()
                .map(Report::new)
                .toList();

        long part1Result = reports.stream()
                .filter(Report::isSafe)
                .count();
        System.out.println("Part 1 result = " + part1Result);

        long part2Result = reports.stream()
                .filter(Report::isSafeWithProblemDampener)
                .count();
        System.out.println("Part 2 result = " + part2Result);
    }
}
