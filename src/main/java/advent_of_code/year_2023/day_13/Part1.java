package advent_of_code.year_2023.day_13;

import advent_of_code.utils.MyFileReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.OptionalInt;

public class Part1 {
    public static void main(String[] args) throws IOException {
        List<String> input = MyFileReader.readFileAndReturnStringList("src/main/java/advent_of_code/year_2023/day_13/input.txt");

        List<AshRockPattern> patterns = new ArrayList<>();

        List<String> currentPatternLines = new ArrayList<>();
        for (int i = 0; i < input.size(); i++) {
            String line = input.get(i);
            if (line.isBlank()) {
                patterns.add(new AshRockPattern(currentPatternLines));
                currentPatternLines = new ArrayList<>();
            } else {
                currentPatternLines.add(line);
                if (i == input.size() - 1) {
                    patterns.add(new AshRockPattern(currentPatternLines));
                }
            }
        }

        int horizontalResult = patterns.stream()
                .map(pattern -> pattern.linesUntilHorizontalReflectionLine())
                .filter(OptionalInt::isPresent)
                .map(OptionalInt::getAsInt)
                .reduce(0, Integer::sum);

        int verticalResult = patterns.stream()
                .map(pattern -> pattern.columnsUntilVerticalReflectionLine())
                .filter(OptionalInt::isPresent)
                .map(OptionalInt::getAsInt)
                .reduce(0, Integer::sum);

        int result = verticalResult + (100 * horizontalResult);

        System.out.println(result);
    }
}
