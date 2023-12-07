package advent_of_code.year_2023.day_7.Part2;

public enum NewRuleCard {
    J('J'),
    TWO('2'),
    THREE('3'),
    FOUR('4'),
    FIVE('5'),
    SIX('6'),
    SEVEN('7'),
    EIGHT('8'),
    NINE('9'),
    T('T'),
    Q('Q'),
    K('K'),
    A('A');

    public final Character value;

    private NewRuleCard(Character c) {
        this.value = c;
    }

    public static NewRuleCard fromValue(Character c) {
        for (NewRuleCard card : values()) {
            if (card.value.equals(c)) {
                return card;
            }
        }
        return null;
    }
}
