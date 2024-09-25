package advent_of_code.year_2020.day_8;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.Collections;
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
}
