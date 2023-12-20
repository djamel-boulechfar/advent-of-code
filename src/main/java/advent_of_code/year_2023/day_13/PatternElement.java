package advent_of_code.year_2023.day_13;

import advent_of_code.year_2023.day_12.SpringState;

public enum PatternElement {
    ASH('.'),
    ROCK('#');

    public final char value;

    private PatternElement(char c) {
        this.value = c;
    }

    public static PatternElement fromValue(char c) {
        for (PatternElement patternElement : values()) {
            if (patternElement.value == c) {
                return patternElement;
            }
        }
        return null;
    }
}
