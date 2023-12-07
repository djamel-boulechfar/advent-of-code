package advent_of_code.year_2023.day_7.Part2;

import java.util.Comparator;

public class NewRuleHandComparator implements Comparator<NewRuleHand> {
    @Override
    public int compare(NewRuleHand newRuleHand1, NewRuleHand newRuleHand2) {
        if (!newRuleHand1.getType().equals(newRuleHand2.getType())) {
            return newRuleHand1.getType().compareTo(newRuleHand2.getType());
        } else {
            return newRuleHand1.compareTo(newRuleHand2);
        }
    }
}