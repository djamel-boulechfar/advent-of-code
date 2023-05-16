package advent_of_code.year_2020.day_3;

public enum Obstacle {
    NOTHING('.'),
    TREE('#');

    public final char value;

    Obstacle(char value) {
        this.value = value;
    }
}
