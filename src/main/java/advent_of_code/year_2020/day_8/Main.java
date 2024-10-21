package advent_of_code.year_2020.day_8;

import advent_of_code.utils.MyFileReader;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        List<String> input = MyFileReader.readFileAndReturnStringList("src/main/java/advent_of_code/year_2020/day_8/input.txt");

        List<Operation> operations = input.stream()
                .map(Operation::new)
                .toList();

        ExecutionResult executionResult = Operation.computeOperations(operations);

        System.out.println("Part 1 answer = " + executionResult.accumulator());

        int accumulatorAfterCorrection = Operation.correctAndComputeOperations(operations);

        System.out.println("Part 2 answer = " + accumulatorAfterCorrection);
    }
}
