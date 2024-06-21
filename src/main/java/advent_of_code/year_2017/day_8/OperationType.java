package advent_of_code.year_2017.day_8;

public enum OperationType {
    INCREMENT("inc"),
    DECREMENT("dec");

    private final String value;

    OperationType(String value) {
        this.value = value;
    }

    public static OperationType fromValue(String value) {
        for (OperationType operationType : values()) {
            if (operationType.value.equals(value)) {
                return operationType;
            }
        }
        return null;
    }

    public String getValue() {
        return this.value;
    }
}
