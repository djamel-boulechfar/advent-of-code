package advent_of_code.year_2020.day_8;

import advent_of_code.utils.MyFileReader;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws IOException {
        List<String> input = MyFileReader.readFileAndReturnStringList("src/main/java/advent_of_code/year_2020/day_8/input.txt");

        List<Operation> operations = input.stream()
                .map(Operation::new)
                .toList();

        ExecutionResult executionResult = computeOperations(operations);

        System.out.println("Part 1 answer = " + executionResult.accumulator());

        for (int i = 0; i < operations.size(); i++) {
            Operation currentOperation = operations.get(i);

            List<Operation> correctedOperations = new java.util.ArrayList<>(operations.stream()
                    .map(Operation::new)
                    .toList());

            // Try an OperationType correction
            if (OperationType.JMP.equals(currentOperation.getOperationType())) {
                correctedOperations.set(i, new Operation(OperationType.NOP, currentOperation.getValue()));
            } else if (OperationType.NOP.equals(currentOperation.getOperationType())) {
                correctedOperations.set(i, new Operation(OperationType.JMP, currentOperation.getValue()));
            }


            if (!OperationType.ACC.equals(currentOperation.getOperationType())) {
                ExecutionResult currentExecutionResult = computeOperations(correctedOperations);

                if (currentExecutionResult.index() >= correctedOperations.size()) {
                    System.out.println("Part 2 answer = " + currentExecutionResult.accumulator());
                }
            }
        }
    }

    /**
     * Computes given list of information and return accumulator value and last executed instruction index.
     *
     * @param operations Operations to execute.
     * @return Accumulator value and last executed instruction index.
     */
    private static ExecutionResult computeOperations(List<Operation> operations) {
        Set<Integer> executedOperationsIndexes = new HashSet<>();

        int accumulator = 0;
        int index = 0;
        while (executedOperationsIndexes.add(index) && index < operations.size()) {
            Operation currentOperation = operations.get(index);

            switch (currentOperation.getOperationType()) {
                case ACC -> {
                    accumulator += currentOperation.getValue();
                    index++;
                }
                case JMP -> index += currentOperation.getValue();
                default -> index++;
            }
        }

        return new ExecutionResult(accumulator, index);
    }
}
