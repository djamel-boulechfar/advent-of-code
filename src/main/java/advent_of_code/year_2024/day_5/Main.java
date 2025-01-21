package advent_of_code.year_2024.day_5;

import advent_of_code.utils.MyFileReader;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        List<String> input = MyFileReader.readFileAndReturnStringList("src/main/java/advent_of_code/year_2024/day_5/input.txt");

        Set<OrderingRule> orderingRules = input.subList(0, input.indexOf("")).stream()
                .map(OrderingRule::new)
                .collect(Collectors.toSet());
        List<UpdateInstruction> updateInstructions = input.subList(orderingRules.size() + 1, input.size()).stream()
                .map(UpdateInstruction::new)
                .toList();

        Printer printer = new Printer(orderingRules, updateInstructions);

        System.out.println("Part 1 answer = " + printer.sumOfMiddlePageNumberOfCorrectlyOrderedUpdateInstructions());
    }
}
