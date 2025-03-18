package advent_of_code.year_2016.day_9;

import advent_of_code.exceptions.WTFException;
import advent_of_code.utils.MyFileReader;

import java.io.IOException;
import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) throws IOException {
        String input = MyFileReader.readFileAndReturnStringList("src/main/java/advent_of_code/year_2016/day_9/input.txt").get(0);

        StringBuilder stringBuilder = new StringBuilder();
        int index = 0;
        while (index < input.length()) {
            // String without already computed parts
            String workingString = input.substring(index);

            char firstCharacter = workingString.charAt(0);

            // If current character is the beginning of a marker
            if ('(' == firstCharacter) {
                // Get the marker
                String marker = Pattern.compile("\\(\\d+x\\d+\\)").matcher(workingString).results()
                        .map(MatchResult::group)
                        .findFirst()
                        .orElseThrow(() -> new WTFException("Unable to find marker."));

                // Get marker numbers
                // First one is the length of the string to be repeated
                // Second one is the number of times it has to be repeated
                List<Integer> numbers = Pattern.compile("\\d+").matcher(marker).results()
                        .map(MatchResult::group)
                        .map(Integer::parseInt)
                        .toList();

                // Get the string to repeat, beginning after the end of the marker
                String stringToRepeat = workingString.substring(marker.length(), marker.length() + numbers.get(0));

                // Add the repeated string to the result
                stringBuilder.append(stringToRepeat.repeat(Math.max(0, numbers.get(1))));

                // Update the index to continue after the string to repeat
                index += marker.length() + numbers.get(0);
            } else {
                // Else, if current character is not a marker beginning, just add it to the result
                stringBuilder.append(firstCharacter);
                index++;
            }
        }

        System.out.println(stringBuilder.length());
    }
}
