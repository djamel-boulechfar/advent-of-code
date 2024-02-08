package advent_of_code.year_2015.day_7;

public enum Operation {
    SET("SET"),
    AND("AND"),
    OR("OR"),
    NOT("NOT"),
    LSHIFT("LSHIFT"),
    RSHIFT("RSHIFT");

    public final String value;

    private Operation(String value) {
        this.value = value;
    }

    public static Operation fromValue(String value) {
        for (Operation operation : values()) {
            if (operation.value.equals(value)) {
                return operation;
            }
        }
        return null;
    }
}
