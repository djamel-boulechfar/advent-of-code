package advent_of_code.year_2020.day_8;

public enum OperationType {
    ACC("acc"),
    JMP("jmp"),
    NOP("nop");

    private final String value;

    OperationType(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    public static OperationType fromValue(String value) {
        for (OperationType operationType : OperationType.values()) {
            if (operationType.value.equals(value)) {
                return operationType;
            }
        }
        throw new IllegalArgumentException("No operation type found for value " + value);
    }
}
