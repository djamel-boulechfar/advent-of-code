package advent_of_code.year_2017.day_9;

import advent_of_code.utils.MyFileReader;

import java.io.IOException;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) throws IOException {
        String input = MyFileReader.readFileAndReturnStringList("src/main/java/advent_of_code/year_2017/day_9/input.txt").get(0);

        Group initialGroup = Pattern.compile("\\{.*\\}").matcher(input).results()
                .map(MatchResult::group)
                .findFirst()
                .map(Group::new)
                .get();

        initialGroup.computeGroup(1);
    }
}
