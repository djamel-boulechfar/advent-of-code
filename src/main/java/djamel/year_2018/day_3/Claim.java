package djamel.year_2018.day_3;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

@Data
@AllArgsConstructor
public class Claim {
    private int id;
    private Position position;
    private int width;
    private int height;

    @Override
    public String toString() {
        return this.id + " : (" + this.position.getX() + ", " + this.position.getY() + ") [" + this.width + ", " + this.height + "]";
    }

    public static Claim inputToClaim(String input) {
        // ID
        String idString = (StringUtils.substringBetween(input, "#", "@")).trim();
        int id = Integer.parseInt(idString);

        // Position
        String positionString = (StringUtils.substringBetween(input, "@", ":")).trim();
        String[] positionStrings = positionString.split(",");
        int x = Integer.parseInt(positionStrings[0]);
        int y = Integer.parseInt(positionStrings[1]);
        Position position = new Position(x, y);

        // Dimensions
        String dimensionsString = (StringUtils.substringAfter(input, ":")).trim();
        String[] dimensionsStrings = dimensionsString.split("x");
        int width = Integer.parseInt(dimensionsStrings[0]);
        int height = Integer.parseInt(dimensionsStrings[1]);

        return new Claim(id, position, width, height);
    }

    public void claimFabricArea(Map<Position, Integer> fabric) {
        for (int i = this.position.getX(); i < this.position.getX() + this.width; i++) {
            for (int j = this.position.getY(); j < this.getPosition().getY() + this.height; j++) {
                Position position = new Position(i, j);
                if (fabric.get(position) == null) {
                    fabric.put(position, this.id);
                } else if (fabric.get(position) != null) {
                    fabric.replace(position, -1);
                }
            }
        }
    }
}
