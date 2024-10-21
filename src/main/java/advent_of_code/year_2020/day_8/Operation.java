package advent_of_code.year_2020.day_8;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Data
@AllArgsConstructor
public class Operation {
    private OperationType operationType;
    private int value;

    private static final Pattern OPERATION_PATTERN = Pattern.compile("(?<operationType>(" + OperationType.ACC.getValue() + "|" + OperationType.JMP.getValue() + "|" + OperationType.NOP.getValue() + ")) (?<value>[+-]\\d+)");

    public Operation(String input) {
        Matcher matcher = OPERATION_PATTERN.matcher(input);

        if (!matcher.matches()) {
            throw new IllegalArgumentException("WTF Unable to parse Operation from input : \"" + input + "\"");
        }

        this.operationType = OperationType.fromValue(matcher.group("operationType"));

        this.value = Integer.parseInt(matcher.group("value"));
    }

    public Operation copy() {
        return new Operation(this.getOperationType(), this.getValue());
    }

    /**
     * Computes given list of information and return accumulator value and last executed instruction index.
     *
     * @param operations Operations to execute.
     * @return Accumulator value and last executed instruction index.
     */
    public static ExecutionResult computeOperations(List<Operation> operations) {
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

    public static int correctAndComputeOperations(List<Operation> operations) {
        for (int i = 0; i < operations.size(); i++) {
            Operation currentOperation = operations.get(i);

            if (OperationType.ACC.equals(currentOperation.getOperationType())) {
                continue;
            }

            List<Operation> correctedOperations = new ArrayList<>(operations.stream()
                    .map(Operation::copy)
                    .toList());

            correctedOperations.set(i, new Operation(currentOperation.getOperationType().correct(), currentOperation.getValue()));

            ExecutionResult currentExecutionResult = Operation.computeOperations(correctedOperations);

            if (currentExecutionResult.index() >= correctedOperations.size()) {
                return currentExecutionResult.accumulator();
            }
        }

        throw new IllegalArgumentException("WTF no possible correction.");
    }
}
