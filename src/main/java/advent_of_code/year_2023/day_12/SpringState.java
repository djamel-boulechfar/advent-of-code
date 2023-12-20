package advent_of_code.year_2023.day_12;

public enum SpringState {
    OPERATIONAL('.'),
    DAMAGED('#'),
    UNKNOWN('?');

    public final char value;

    private SpringState(char c) {
        this.value = c;
    }

    public static SpringState fromValue(char c) {
        for (SpringState springState : values()) {
            if (springState.value == c) {
                return springState;
            }
        }
        return null;
    }
}
