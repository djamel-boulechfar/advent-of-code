package advent_of_code.year_2020.day_8;

import advent_of_code.utils.MyFileReader;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Part {
    public static void main(String[] args) throws IOException {
        List<String> input = MyFileReader.readFileAndReturnStringList("src/main/java/advent_of_code/year_2020/day_8/input.txt");

        List<Operation> operations = input.stream()
                .map(Operation::new)
                .toList();

        Set<Integer> executedOperationsIndexes = new HashSet<>();

        int accumulator = 0;
        int index = 0;
        Operation currentOperation = operations.get(index);
        while (!executedOperationsIndexes.contains(index)) {
            executedOperationsIndexes.add(index);

            switch (currentOperation.getOperationType()) {
                case ACC -> {
                    accumulator += currentOperation.getValue();
                    index++;
                }
                case JMP -> index += currentOperation.getValue();
                default -> index++;
            }

            currentOperation = operations.get(index);
        }

        System.out.println("Part 1 answer = " + accumulator);
    }
}
