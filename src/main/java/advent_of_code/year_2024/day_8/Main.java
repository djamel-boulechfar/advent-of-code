package advent_of_code.year_2024.day_8;

import advent_of_code.utils.MyFileReader;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        List<String> input = MyFileReader.readFileAndReturnStringList("src/main/java/advent_of_code/year_2024/day_8/input.txt");

        int maxX = input.get(0).length();
        int maxY = input.size();

        Map<Character, Set<Position>> antennasPerPositions = new HashMap<>();

        for (int y = 0; y < input.size(); y++) {
            String line = input.get(y);

            for (int x = 0; x < line.length(); x++) {
                char character = line.charAt(x);

                if ('.' != character) {
                    antennasPerPositions.putIfAbsent(character, new HashSet<>());

                    Set<Position> antennaPositions = antennasPerPositions.get(character);

                    antennaPositions.add(new Position(x, y));
                }
            }
        }

        Set<Position> antinodesPositions = new HashSet<>();
        for (Set<Position> antennasPositions : antennasPerPositions.values()) {
            for (Position antennaPosition : antennasPositions) {
                Set<Position> antennasPositionWithoutCurrentAntenna = antennasPositions.stream()
                        .filter(otherAntennaPosition -> !otherAntennaPosition.equals(antennaPosition))
                        .collect(Collectors.toSet());

                for (Position otherAntennaPosition : antennasPositionWithoutCurrentAntenna) {
                    Position symetricalPosition = antennaPosition.getSymetricalPosition(otherAntennaPosition);

                    if (0 <= symetricalPosition.getX() && maxX > symetricalPosition.getX() && 0 <= symetricalPosition.getY() && maxY > symetricalPosition.getY()) {
                        antinodesPositions.add(symetricalPosition);
                    }
                }
            }
        }

        System.out.println("Part 1 answer = " + antinodesPositions.size());

        Set<Position> part2AntinodesPositions = new HashSet<>();
        for (Set<Position> antennasPositions : antennasPerPositions.values()) {
            for (Position antennaPosition : antennasPositions) {
                Set<Position> antennasPositionWithoutCurrentAntenna = antennasPositions.stream()
                        .filter(otherAntennaPosition -> !otherAntennaPosition.equals(antennaPosition))
                        .collect(Collectors.toSet());

                for (Position otherAntennaPosition : antennasPositionWithoutCurrentAntenna) {
                    Difference difference = antennaPosition.getDifferenceWith(otherAntennaPosition);

                    Position nextPositionPositive = new Position(antennaPosition.getX() + difference.dx(), antennaPosition.getY() + difference.dy());
                    while (0 <= nextPositionPositive.getX() && maxX > nextPositionPositive.getX() && 0 <= nextPositionPositive.getY() && maxY > nextPositionPositive.getY()) {
                        part2AntinodesPositions.add(nextPositionPositive);
                        nextPositionPositive = new Position(nextPositionPositive.getX() + difference.dx(), nextPositionPositive.getY() + difference.dy());
                    }

                    Position nextPositionNegative = new Position(antennaPosition.getX() - difference.dx(), antennaPosition.getY() - difference.dy());
                    while (0 <= nextPositionNegative.getX() && maxX > nextPositionNegative.getX() && 0 <= nextPositionNegative.getY() && maxY > nextPositionNegative.getY()) {
                        part2AntinodesPositions.add(nextPositionNegative);
                        nextPositionNegative = new Position(nextPositionNegative.getX() - difference.dx(), nextPositionNegative.getY() - difference.dy());
                    }
                }
            }
        }

        System.out.println("Part 2 answer = " + part2AntinodesPositions.size());
    }
}
