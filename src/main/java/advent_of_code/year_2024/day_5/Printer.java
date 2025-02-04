package advent_of_code.year_2024.day_5;

import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
public class Printer {
    private Set<OrderingRule> orderingRules;
    private List<UpdateInstruction> updateInstructions;

    public int sumOfMiddlePageNumberOfCorrectlyOrderedUpdateInstructions() {
        return this.updateInstructions.stream()
                .filter(updateInstruction ->
                        updateInstruction.isCorrectlyOrdered(this.getOrderingRulesForUpdateInstruction(updateInstruction))
                )
                .map(UpdateInstruction::getMiddlePageNumber)
                .reduce(0, Integer::sum);
    }

    private Set<OrderingRule> getOrderingRulesForUpdateInstruction(UpdateInstruction updateInstruction) {
        List<Integer> pagesToUpdateNumbers = updateInstruction.getPagesToUpdateNumbers();
        return this.orderingRules.stream()
                .filter(orderingRule ->
                        pagesToUpdateNumbers.contains(orderingRule.getPageNumberToProcessFirst()) &&
                                pagesToUpdateNumbers.contains(orderingRule.getPageNumberToProcessAfter())
                )
                .collect(Collectors.toSet());
    }
}
