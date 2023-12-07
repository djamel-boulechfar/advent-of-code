package advent_of_code.year_2023.day_7.Part1;

import advent_of_code.year_2023.day_7.Part1.Hand;

import java.util.Comparator;

public class HandComparator implements Comparator<Hand> {
    @Override
    public int compare(Hand hand1, Hand hand2) {
        if (!hand1.getType().equals(hand2.getType())) {
            return hand1.getType().compareTo(hand2.getType());
        } else {
            return hand1.compareTo(hand2);
        }
    }
}
