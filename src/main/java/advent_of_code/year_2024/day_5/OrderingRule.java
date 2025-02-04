package advent_of_code.year_2024.day_5;

import lombok.Data;

@Data
public class OrderingRule {
    private int pageNumberToProcessFirst;
    private int pageNumberToProcessAfter;

    public OrderingRule(String orderingRuleString) {
        String[] pagesNumbersStrings = orderingRuleString.split("\\|");

        if (pagesNumbersStrings.length != 2) {
            throw new IllegalArgumentException("WTF : unable to parse OrderingRule from following String : " + orderingRuleString);
        }

        this.pageNumberToProcessFirst = Integer.parseInt(pagesNumbersStrings[0].trim());
        this.pageNumberToProcessAfter = Integer.parseInt(pagesNumbersStrings[1].trim());
    }
}
