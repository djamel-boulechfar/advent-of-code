package advent_of_code.year_2017.day_8;

import advent_of_code.utils.MyFileReader;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("java:S106")
public class Part1 {

    public static void main(String[] args) throws IOException {
        // Input reading
        List<String> input = MyFileReader.readFileAndReturnStringList("src/main/java/advent_of_code/year_2017/day_8/input.txt");

        // Input parsing
        List<Instruction> instructions = input.stream()
                .map(Instruction::new)
                .toList();

        // Map containing value of each register
        Map<String, Integer> registerValues = new HashMap<>();

        // Part 2 answer : max register value held throughout instructions executions
        int highestHeldRegisterValue = Integer.MIN_VALUE;

        // Execute each parsed instruction
        for (Instruction instruction : instructions) {
            // Compute operation if condition is met
            if (evaluateCondition(instruction, registerValues)) {
                int operationResult = computeOperation(instruction, registerValues);
                // Save register value if it's bigger than the currently saved one
                if (operationResult > highestHeldRegisterValue) highestHeldRegisterValue = operationResult;
            }
        }

        // Part 1 answer : max register value after each instruction is executed
        int answer = registerValues.values().stream()
                .max(Integer::compareTo)
                .orElseThrow(() -> new IllegalArgumentException("WTF is happening map get max"));
        
        System.out.println("Part 1 answer = " + answer);
        System.out.println("Part 2 answer = " + highestHeldRegisterValue);
    }

    public static boolean evaluateCondition(Instruction instruction, Map<String, Integer> registerValues) {
        Condition condition = instruction.getCondition();

        registerValues.merge(condition.getConditionRegister(), 0, Integer::sum);

        int registerValue = registerValues.get(condition.getConditionRegister());
        int conditionValue = condition.getConditionValue();

        // TODO return switch
        switch (condition.getConditionComparator()) {
            case EQUAL -> {
                return registerValue == conditionValue;
            }
            case DIFFERENT -> {
                return registerValue != conditionValue;
            }
            case MORE_THAN -> {
                return registerValue > conditionValue;
            }
            case LESS_THAN -> {
                return registerValue < conditionValue;
            }
            case MORE_THAN_EQUAL -> {
                return registerValue >= conditionValue;
            }
            case LESS_THAN_EQUAL -> {
                return registerValue <= conditionValue;
            }
            default -> throw new IllegalArgumentException("WTF is happening on this condition : " + condition);
        }
    }

    public static int computeOperation(Instruction instruction, Map<String, Integer> registerValues) {
        Operation operation = instruction.getOperation();

        String operationRegister = operation.getOperationRegister();
        registerValues.merge(operationRegister, 0, Integer::sum);

        int operationValue = operation.getOperationValue();
        switch (operation.getOperationType()) {
            case INCREMENT -> registerValues.merge(operationRegister, operationValue, Integer::sum);
            case DECREMENT -> registerValues.merge(operationRegister, operationValue, (a, b) -> a - b);
            default -> throw new IllegalArgumentException("WTF is happening on this operation : " + operation);
        }

        return registerValues.get(operationRegister);
    }
}
