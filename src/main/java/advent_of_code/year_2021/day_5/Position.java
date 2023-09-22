package advent_of_code.year_2021.day_5;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Position {
    private int x;
    private int y;

    public Position(String input) {
        String[] positionStrings = input.split(",");
        if (positionStrings.length == 2) {
            this.x = Integer.parseInt(positionStrings[0]);
            this.y = Integer.parseInt(positionStrings[1]);
        } else {
            throw new IllegalArgumentException("Impossible d'utiliser la chaîne '" + input + "' pour créer une Position.");
        }
    }
}
