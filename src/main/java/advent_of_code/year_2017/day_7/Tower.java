package advent_of_code.year_2017.day_7;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Data
public class Tower {
    private String id;
    private int weight;
    private Optional<String> prev;
    private List<String> next;

    public Tower(String line) {
        Matcher matcher = Pattern.compile("(?<id>\\w+) \\((?<weight>\\d+)\\)( -> (?<subTowers>.*))?").matcher(line);

        if (!matcher.matches()) {
            throw new IllegalArgumentException("Unable to create Tower with line : " + line);
        }

        this.id = matcher.group("id");
        this.weight = Integer.parseInt(matcher.group("weight"));
        this.prev = Optional.empty();
        this.next = new ArrayList<>();

        String subTowers = matcher.group("subTowers");
        if (subTowers != null) {
            this.next = Pattern.compile("\\w+").matcher(subTowers).results()
                    .map(MatchResult::group)
                    .toList();
        }
    }
}
