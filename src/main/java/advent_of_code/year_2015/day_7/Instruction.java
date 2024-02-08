package advent_of_code.year_2015.day_7;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Data
public class Instruction {
    private String firstValue;
    private Operation operation;
    private String secondValue;
    private String target;

    private static final Pattern SET_PATTERN = Pattern.compile("(?<value>\\w+) -> (?<target>.+)");

    private static final Pattern NOT_PATTERN = Pattern.compile("NOT (?<value>\\w+) -> (?<target>\\w+)");

    private static final Pattern BINARY_OPERATION_PATTERN = Pattern.compile("(?<firstValue>\\w+) (?<operator>\\w+) (?<secondValue>\\w+) -> (?<target>\\w+)");

    public Instruction(String input) {
        Matcher setMatcher = SET_PATTERN.matcher(input);
        Matcher notMatcher = NOT_PATTERN.matcher(input);
        Matcher binaryOperationMatcher = BINARY_OPERATION_PATTERN.matcher(input);

        if (setMatcher.matches()) {
            this.firstValue = setMatcher.group("value");
            this.operation = Operation.SET;
            this.target = setMatcher.group("target");
        } else if (notMatcher.matches()) {
            this.firstValue = notMatcher.group("value");
            this.operation = Operation.NOT;
            this.target = notMatcher.group("target");
        } else if (binaryOperationMatcher.matches()) {
            this.firstValue = binaryOperationMatcher.group("firstValue");
            this.operation = Operation.fromValue(binaryOperationMatcher.group("operator"));
            this.secondValue = binaryOperationMatcher.group("secondValue");
            this.target = binaryOperationMatcher.group("target");
        } else {
            throw new IllegalArgumentException("Impossible de créer une Instruction : " + input);
        }
    }
}
