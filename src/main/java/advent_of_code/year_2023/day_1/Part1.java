package advent_of_code.year_2023.day_1;

import advent_of_code.utils.MyFileReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;

public class Part1 {
    public static void main(String[] args) throws IOException {
        List<String> input = MyFileReader.readFileAndReturnStringList("src/main/java/advent_of_code/year_2023/day_1/input.txt");

        int part1Answer = computePart1(input);
        int part2Answer = computePart2(input);

        System.out.println("Part 1 answer = " + part1Answer);
        System.out.println("Part 2 answer = " + part2Answer);
    }

    public static int computePart1(List<String> input) {
        List<Integer> calibrationValues = new ArrayList<>();

        for (String line: input) {
            List<String> numbersStrings = Pattern.compile("\\d").matcher(line).results()
                    .map(MatchResult::group)
                    .toList();

            String numberString = numbersStrings.get(0) + numbersStrings.get(numbersStrings.size() - 1);

            calibrationValues.add(Integer.parseInt(numberString));
        }

        return calibrationValues.stream().reduce(Integer::sum).orElse(0);
    }

    public static int computePart2(List<String> input) {
        List<Integer> calibrationValues = new ArrayList<>();

        for (String line: input) {
            List<String> numbersStrings = Pattern.compile("\\d | one | two | three | four | five | six | seven | eight | nine").matcher(line).results()
                    .map(MatchResult::group)
                    .toList();

            String firstNumber = convertLettersToNumber(numbersStrings.get(0));
            String lastNumber = convertLettersToNumber(numbersStrings.get(numbersStrings.size() - 1));

            String numberString = firstNumber + lastNumber;

            calibrationValues.add(Integer.parseInt(numberString));
        }

        return calibrationValues.stream().reduce(Integer::sum).orElse(0);
    }

    public static String convertLettersToNumber(String numberString) {
        switch (numberString) {
            case "one" -> {return "1";}
            case "two" -> {return "2";}
            case "three" -> {return "3";}
            case "four" -> {return "4";}
            case "five" -> {return "5";}
            case "six" -> {return "6";}
            case "seven" -> {return "7";}
            case "eight" -> {return "8";}
            case "nine" -> {return "9";}
            default -> {return numberString;}
        }
    }
}
