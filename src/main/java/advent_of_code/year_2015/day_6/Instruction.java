package advent_of_code.year_2015.day_6;

import lombok.Data;

import java.util.regex.MatchResult;
import java.util.regex.Pattern;

@Data
public class Instruction {
    private Action action;
    private Position startPosition;
    private Position endPosition;

    public Instruction(String input) {
        // Récupération de l'action à exécuter
        String[] words = input.split(" ");
        if (words[0].equals("toggle")) {
            this.action = Action.TOGGLE;
        } else if (words[0].equals("turn") && words[1].equals("on")) {
            this.action = Action.TURN_ON;
        } else if (words[0].equals("turn") && words[1].equals("off")) {
            this.action = Action.TURN_OFF;
        } else {
            throw new IllegalArgumentException("Impossible de récupérer l'action de l'instruction pour l'input : \"" + input + "\"");
        }

        // Récupération des coordonnées sur lesquelles effectuer cette action
        int[] coordinates = Pattern.compile("\\d+").matcher(input).results()
                .map(MatchResult::group)
                .mapToInt(Integer::parseInt)
                .toArray();
        if (coordinates.length == 4) {
            this.startPosition = new Position(coordinates[0], coordinates[1]);
            this.endPosition = new Position(coordinates[2], coordinates[3]);
        } else {
            throw new IllegalArgumentException("Impossible de récupérer les coordonnées de l'instruction pour l'input : \"" + input + "\"");
        }
    }
}
