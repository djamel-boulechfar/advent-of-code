package advent_of_code.year_2024.day_7;

import java.util.List;
import java.util.Optional;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;

public class Equation {
    private int leftTerm;
    private List<Integer> rightTerms;

    public Equation(String equationString) {
        List<Integer> numbers = Pattern.compile("\\d+").matcher(equationString).results()
                .map(MatchResult::group)
                .map(Integer::parseInt)
                .toList();

        leftTerm = numbers.get(0);
        rightTerms = numbers.subList(1, numbers.size());
    }
}
