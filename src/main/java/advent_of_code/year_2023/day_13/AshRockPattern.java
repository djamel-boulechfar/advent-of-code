package advent_of_code.year_2023.day_13;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalInt;

@Data
public class AshRockPattern {
    List<List<PatternElement>> elementLines;

    public AshRockPattern(List<String> lines) {
        this.elementLines = new ArrayList<>();
        for (String line: lines) {
            List<PatternElement> lineElements = new ArrayList<>();
            for (char c: line.toCharArray()) {
                lineElements.add(PatternElement.fromValue(c));
            }
            this.elementLines.add(lineElements);
        }
    }

    public OptionalInt linesUntilHorizontalReflectionLine() {
        OptionalInt res = OptionalInt.empty();
        int longestSymmetry = 0;

        for (int i = 0; i < this.elementLines.size() - 1; i++) {
            List<PatternElement> currentLine = this.elementLines.get(i);
            List<PatternElement> nextLine = this.elementLines.get(i + 1);

            if (currentLine.equals(nextLine)) {
                int currentSymmetryLength = 0;
                int backward = i;
                int forward = i + 1;
                boolean isSymmetric = true;

                while (isSymmetric && backward >= 0 && forward < this.elementLines.size()) {
                    List<PatternElement> backwardLine = this.elementLines.get(backward);
                    List<PatternElement> forwardLine = this.elementLines.get(forward);

                    if (backwardLine.equals(forwardLine)) {
                        currentSymmetryLength++;
                    } else {
                        isSymmetric = false;
                    }

                    backward--;
                    forward++;
                }

                if (isSymmetric && currentSymmetryLength > longestSymmetry) {
                    longestSymmetry = currentSymmetryLength;
                    res = OptionalInt.of(i + 1);
                }
            }
        }

        return res;
    }

    public OptionalInt columnsUntilVerticalReflectionLine() {
        OptionalInt res = OptionalInt.empty();
        int longestSymmetry = 0;

        int lineLength = this.elementLines.get(0).size();

        for (int i = 0; i < lineLength - 1; i++) {
            List<PatternElement> currentColumn = this.getColumn(i);
            List<PatternElement> nextColumn = this.getColumn(i + 1);

            if (currentColumn.equals(nextColumn)) {
                int currentSymmetryLength = 0;
                int backward = i;
                int forward = i + 1;
                boolean isSymmetric = true;

                while (isSymmetric && backward >= 0 && forward < lineLength) {
                    List<PatternElement> backwardColumn = this.getColumn(backward);
                    List<PatternElement> forwardColumn = this.getColumn(forward);

                    if (backwardColumn.equals(forwardColumn)) {
                        currentSymmetryLength++;
                    } else {
                        isSymmetric = false;
                    }

                    backward--;
                    forward++;
                }

                if (isSymmetric && currentSymmetryLength > longestSymmetry) {
                    longestSymmetry = currentSymmetryLength;
                    res = OptionalInt.of(i + 1);
                }
            }
        }

        return res;
    }

    public List<PatternElement> getColumn(int index) {
        List<PatternElement> res = new ArrayList<>();
        for (List<PatternElement> line: this.elementLines) {
            res.add(line.get(index));
        }
        return res;
    }
}
