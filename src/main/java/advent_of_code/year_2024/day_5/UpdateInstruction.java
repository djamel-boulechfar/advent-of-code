package advent_of_code.year_2024.day_5;

import lombok.Data;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Data
public class UpdateInstruction {
    List<Integer> pagesToUpdateNumbers;

    public UpdateInstruction(String updateInstructionString) {
        this.pagesToUpdateNumbers = Arrays.stream(updateInstructionString.split(","))
                .map(Integer::parseInt)
                .toList();
    }

    public int getMiddlePageNumber() {
        return pagesToUpdateNumbers.get(pagesToUpdateNumbers.size() / 2);
    }

    public boolean isCorrectlyOrdered(Set<OrderingRule> orderingRules) {
        return orderingRules.stream()
                .allMatch(this::orderingRuleIsRespected);
    }

    private boolean orderingRuleIsRespected(OrderingRule orderingRule) {
        return this.pagesToUpdateNumbers.indexOf(orderingRule.getPageNumberToProcessFirst()) <
                this.pagesToUpdateNumbers.indexOf(orderingRule.getPageNumberToProcessAfter());
    }

    public UpdateInstruction getCorrectedUpdateInstruction(Set<OrderingRule> orderingRules) {
        for (OrderingRule orderingRule : orderingRules) {
            // TODO : coder
        }

        return null;
    }
}
