package advent_of_code.year_2024.day_2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Report {
    private final List<Integer> levels;

    public Report(String line) {
        this.levels = Arrays.stream(line.split(" "))
                .map(Integer::parseInt)
                .toList();
    }

    public boolean isSafe() {
        return this.areLevelsSorted() && this.countLevelsDifferenceErrors() == 0;
    }

    public boolean isSafeWithProblemDampener() {
        if (!this.areLevelsSorted()) {
            return false;
        }

        List<Integer> deepCopyLevels = new ArrayList<>(this.levels);

        int levelsDifferenceErrorsCount = this.countLevelsDifferenceErrors();

        return levelsDifferenceErrorsCount == 0 || levelsDifferenceErrorsCount == 1;
    }

    private boolean areLevelsSorted() {
        List<Integer> levelsSortedIncreasingly = this.levels.stream()
                .sorted((a, b) -> a - b)
                .toList();
        List<Integer> levelsSortedDecreasingly = this.levels.stream()
                .sorted((a, b) -> b - a)
                .toList();

        return this.levels.equals(levelsSortedIncreasingly) || this.levels.equals(levelsSortedDecreasingly);
    }

    private int countLevelsDifferenceErrors() {
        int errorsCount = 0;

        for (int i = 0; i < this.levels.size() - 1; i++) {
            int currentLevel = this.levels.get(i);
            int nextLevel = this.levels.get(i + 1);

            int absoluteDifference = Math.abs(currentLevel - nextLevel);

            if (absoluteDifference == 0 || absoluteDifference > 3) {
                errorsCount++;
            }
        }

        return errorsCount;
    }
}
