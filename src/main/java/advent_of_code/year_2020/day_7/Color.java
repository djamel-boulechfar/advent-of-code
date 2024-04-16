package advent_of_code.year_2020.day_7;

import lombok.Data;

@Data
public class Color {
    private String adjective;
    private String color;

    public Color(String line) {
        String[] words = line.split(" ");

        if (words.length < 2) {
            throw new IllegalArgumentException("Unable to create color with following line : " + line);
        }

        this.adjective = words[0];
        this.color = words[1];
    }
}
