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
    private Optional<String> prevTowerId;
    private List<String> nextTowerIds;

    public Tower(String line) {
        Matcher matcher = Pattern.compile("(?<id>\\w+) \\((?<weight>\\d+)\\)( -> (?<subTowers>.*))?").matcher(line);

        if (!matcher.matches()) {
            throw new IllegalArgumentException("Unable to create Tower with line : " + line);
        }

        this.id = matcher.group("id");
        this.weight = Integer.parseInt(matcher.group("weight"));
        this.prevTowerId = Optional.empty();
        this.nextTowerIds = new ArrayList<>();

        String subTowers = matcher.group("subTowers");
        if (subTowers != null) {
            this.nextTowerIds = Pattern.compile("\\w+").matcher(subTowers).results()
                    .map(MatchResult::group)
                    .toList();
        }
    }
}
