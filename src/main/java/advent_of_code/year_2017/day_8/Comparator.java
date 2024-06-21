package advent_of_code.year_2017.day_8;

public enum Comparator {
    EQUAL("=="),
    DIFFERENT("!="),
    MORE_THAN(">"),
    LESS_THAN("<"),
    MORE_THAN_EQUAL(">="),
    LESS_THAN_EQUAL("<=");

    private final String value;

    Comparator(String value) {
        this.value = value;
    }

    public static Comparator fromValue(String value) {
        for (Comparator comparator : values()) {
            if (comparator.value.equals(value)) {
                return comparator;
            }
        }
        return null;
    }

    public String getValue() {
        return this.value;
    }
}
