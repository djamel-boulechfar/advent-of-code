package advent_of_code.year_2023.day_7.Part1;

public enum Card {
    TWO('2'),
    THREE('3'),
    FOUR('4'),
    FIVE('5'),
    SIX('6'),
    SEVEN('7'),
    EIGHT('8'),
    NINE('9'),
    T('T'),
    J('J'),
    Q('Q'),
    K('K'),
    A('A');

    public final Character value;

    private Card(Character c) {
        this.value = c;
    }

    public static Card fromValue(Character c) {
        for (Card card : values()) {
            if (card.value.equals(c)) {
                return card;
            }
        }
        return null;
    }
}
