package advent_of_code.year_2024.day_6;

import advent_of_code.utils.MyFileReader;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        List<String> input = MyFileReader.readFileAndReturnStringList("src/main/java/advent_of_code/year_2024/day_6/input.txt");

        Map<Position, Character> map = new HashMap<>();
        int y = 0;
        for (String line : input) {
            int x = 0;
            for (char character : line.toCharArray()) {
                map.put(new Position(x, y), character);
                x++;
            }
            y++;
        }

        Position startingPosition = map.entrySet().stream()
                .filter(entry -> '^' == entry.getValue())
                .findFirst()
                .map(Map.Entry::getKey)
                .orElseThrow();
        Guard guard = new Guard(startingPosition);

        Position nextPosition = guard.getCurrentPosition().getNextPosition(guard.getCurrentDirection());
        while (map.containsKey(nextPosition)) {
            if (map.get(nextPosition) == '#') {
                guard.turnNinetyDegree();
            } else if (map.get(nextPosition) == '.' || map.get(nextPosition) == '^') {
                guard.move();
            }

            nextPosition = guard.getCurrentPosition().getNextPosition(guard.getCurrentDirection());
        }

        System.out.println("Part 1 answer : " + guard.getVisitedPositions().size());
    }
}
