package advent_of_code.year_2018.day_7;

import java.util.Comparator;

public class StepComparator implements Comparator<Step> {
    @Override
    public int compare(Step step1, Step step2) {
        return step1.getLetter().compareTo(step2.getLetter());
    }
}
