package advent_of_code.year_2017.day_8;

import lombok.Data;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Data
public class Condition {
    private String conditionRegister;
    private Comparator conditionComparator;
    private int conditionValue;

    public Condition(String string) {
        Matcher matcher = Pattern.compile("(?<conditionRegister>\\w+) (?<conditionComparator>(" +
                Comparator.EQUAL.getValue() + "|" +
                Comparator.DIFFERENT.getValue() + "|" +
                Comparator.MORE_THAN.getValue() + "|" +
                Comparator.LESS_THAN.getValue() + "|" +
                Comparator.MORE_THAN_EQUAL.getValue() + "|" +
                Comparator.LESS_THAN_EQUAL.getValue() +
                ")) (?<conditionValue>((-?)\\d+))").matcher(string);

        if (!matcher.matches()) {
            throw new IllegalArgumentException("Unable to create Operation with string : " + string);
        }

        this.conditionRegister = matcher.group("conditionRegister");
        this.conditionComparator = Comparator.fromValue(matcher.group("conditionComparator"));
        this.conditionValue = Integer.parseInt(matcher.group("conditionValue"));
    }
}
