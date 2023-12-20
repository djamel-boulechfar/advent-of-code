package advent_of_code.year_2023.day_12;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;

@Data
public class SpringRow {
    private List<SpringState> springsStates;
    private List<Integer> groupsSizes;

    public SpringRow(String input) {
        String[] splitInput = input.split(" ");

        if (splitInput.length == 2) {
            String springStatesString = splitInput[0];
            this.springsStates = new ArrayList<>();
            for (char c : springStatesString.toCharArray()) {
                this.springsStates.add(SpringState.fromValue(c));
            }

            String groupSizesString = splitInput[1];
            this.groupsSizes = Pattern.compile("\\d+").matcher(groupSizesString).results()
                    .map(MatchResult::group)
                    .map(Integer::parseInt)
                    .toList();
        } else {
            throw new IllegalArgumentException("Unable to create SpringRow with following input : " + input);
        }
    }
}
