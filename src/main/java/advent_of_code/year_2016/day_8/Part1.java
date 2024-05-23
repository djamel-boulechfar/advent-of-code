package advent_of_code.year_2016.day_8;

import advent_of_code.utils.MyFileReader;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part1 {
    private final static int SCREEN_WIDTH = 50;
    private final static int SCREEN_HEIGHT = 6;
    private final static Pattern RECT_PATTERN = Pattern.compile("rect (?<width>\\d+)x(?<height>\\d+)");
    private final static Pattern ROTATE_PATTERN = Pattern.compile("rotate (?<target>(column|row)) ([xy])=(?<firstNumber>\\d+) by (?<secondNumber>\\d+)");

    public static void main(String[] args) throws IOException {
        List<String> input = MyFileReader.readFileAndReturnStringList("src/main/java/advent_of_code/year_2016/day_8/input.txt");

        Set<Position> positions = new HashSet<>();

        for (String line : input) {
            Matcher rectMatcher = RECT_PATTERN.matcher(line);
            if (rectMatcher.matches()) {
                int x = Integer.parseInt(rectMatcher.group("width"));
                int y = Integer.parseInt(rectMatcher.group("height"));

                for (int i = 0; i < x; i++) {
                    for (int j = 0; j < y; j++) {
                        positions.add(new Position(i, j));
                    }
                }
            } else {
                Matcher rotateMatcher = ROTATE_PATTERN.matcher(line);
                if (rotateMatcher.matches()) {
                    String target = rotateMatcher.group("target");
                    int index = Integer.parseInt(rotateMatcher.group("firstNumber"));
                    int shift = Integer.parseInt(rotateMatcher.group("secondNumber"));

                    switch (target) {
                        case "column" -> positions.stream().filter(p -> p.getX() == index).forEach(p -> p.setX(p.getX() + x));
                    }
                } else {
                    throw new IllegalArgumentException("Unable to process line : \"" + line + "\".");
                }
            }
        }
    }
}
