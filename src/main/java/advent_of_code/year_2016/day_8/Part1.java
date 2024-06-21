package advent_of_code.year_2016.day_8;

import advent_of_code.utils.MyFileReader;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part1 {
    private static final int SCREEN_WIDTH = 50;
    private static final int SCREEN_HEIGHT = 6;
    private static final Pattern RECT_PATTERN = Pattern.compile("rect (?<width>\\d+)x(?<height>\\d+)");
    private static final Pattern ROTATE_PATTERN = Pattern.compile("rotate (?<target>(column|row)) ([xy])=(?<firstNumber>\\d+) by (?<secondNumber>\\d+)");

    public static void main(String[] args) throws IOException {
        List<String> input = MyFileReader.readFileAndReturnStringList("src/main/java/advent_of_code/year_2016/day_8/input.txt");

        Set<Position> litPixelsPositions = computeLitPixelsPositions(input);

        System.out.println("Part 1 answer = " + litPixelsPositions.size() + "\n");
        System.out.println("==================================================\n");

        System.out.println("Part 2 answer =\n");
        System.out.println(drawLitPixels(litPixelsPositions));
    }

    private static Set<Position> computeLitPixelsPositions(List<String> input) {
        Set<Position> litPixelsPositions = new HashSet<>();

        for (String line : input) {
            Matcher rectMatcher = RECT_PATTERN.matcher(line);
            if (rectMatcher.matches()) {
                litPixelsPositions.addAll(computeRectangle(rectMatcher));
            } else {
                Matcher rotateMatcher = ROTATE_PATTERN.matcher(line);
                if (rotateMatcher.matches()) {
                    String target = rotateMatcher.group("target");
                    int index = Integer.parseInt(rotateMatcher.group("firstNumber"));
                    int shift = Integer.parseInt(rotateMatcher.group("secondNumber"));

                    switch (target) {
                        case "row" -> litPixelsPositions.stream().filter(p -> p.getY() == index).forEach(p -> p.setX((p.getX() + shift) % SCREEN_WIDTH));
                        case "column" -> litPixelsPositions.stream().filter(p -> p.getX() == index).forEach(p -> p.setY((p.getY() + shift) % SCREEN_HEIGHT));
                        default -> throw new IllegalArgumentException("WTF is happening : " + line);
                    }
                } else {
                    throw new IllegalArgumentException("Unable to process line : \"" + line + "\".");
                }
            }
        }

        return litPixelsPositions;
    }

    private static Set<Position> computeRectangle(Matcher matcher) {
        Set<Position> rectanglePixels = new HashSet<>();

        int width = Integer.parseInt(matcher.group("width"));
        int height = Integer.parseInt(matcher.group("height"));

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                rectanglePixels.add(new Position(i, j));
            }
        }

        return rectanglePixels;
    }

    private static String drawLitPixels(Set<Position> litPixelsPositions) {
        StringBuilder builder = new StringBuilder();

        for (int row = 0; row < SCREEN_HEIGHT; row++) {
            for (int column = 0; column < SCREEN_WIDTH; column++) {
                int finalColumn = column;
                int finalRow = row;
                builder.append(litPixelsPositions.stream().anyMatch(p -> p.getX() == finalColumn && p.getY() == finalRow) ? " # " : "   ");
            }
            builder.append("\n");
        }

        return builder.toString();
    }
}
