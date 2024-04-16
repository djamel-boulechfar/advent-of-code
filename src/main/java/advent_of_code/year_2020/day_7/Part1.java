package advent_of_code.year_2020.day_7;

import advent_of_code.utils.MyFileReader;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part1 {
    public static void main(String[] args) throws IOException {
        List<String> input = MyFileReader.readFileAndReturnStringList("src/main/java/advent_of_code/year_2020/day_7/input.txt");

        Map<Color, Bag> bagPerColorMap = new HashMap<>();

        for (String line: input) {
            String containerColorString = StringUtils.substringBefore(line, " bags");
            String rest = StringUtils.substringAfter(line, "contain ");

            Color color = new Color(containerColorString);
            Bag container = bagPerColorMap.putIfAbsent(color, new Bag(color));

            if (!"no other bags".equals(rest)) {
                List<String> contained = Pattern.compile("((?<quantity>\\d+) (?<color>(\\w+ )+)bag(s)?([,\\.]))").matcher(rest).results()
                        .map(MatchResult::group)
                        .toList();

                contained.forEach(c -> {
                    System.out.println(c);

                    Matcher matcher = Pattern.compile("(?<quantity>\\d+) (?<color>(\\w+ )+)bag(s)?[,\\.]").matcher(c);
                    String containedColorString = matcher.group("color");
                    Color containedColor = new Color(containedColorString);
                    Bag containedBag = bagPerColorMap.putIfAbsent(containedColor, new Bag(containedColor));

                    int quantity = Integer.parseInt(matcher.group("quantity"));
                    container.getContainableBagsByQuantity().put(containedBag, quantity);
                });
            }
        }

        System.out.println(bagPerColorMap);
    }
}
