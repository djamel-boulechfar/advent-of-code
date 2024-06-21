package advent_of_code.year_2017.day_8;

import lombok.Data;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Data
public class Operation {
    private String operationRegister;
    private OperationType operationType;
    private int operationValue;

    public Operation(String string) {
        // TODO : remplacer type operation par w+ et parse + throw si besoin
        Matcher matcher = Pattern.compile("(?<operationRegister>\\w+) (?<operationType>(" + OperationType.INCREMENT.getValue() + "|" + OperationType.DECREMENT.getValue() + ")) (?<operationValue>((-?)\\d+))").matcher(string);

        if (!matcher.matches()) {
            throw new IllegalArgumentException("Unable to create Operation with string : " + string);
        }

        this.operationRegister = matcher.group("operationRegister");
        this.operationType = OperationType.fromValue(matcher.group("operationType"));
        this.operationValue = Integer.parseInt(matcher.group("operationValue"));
    }
}
