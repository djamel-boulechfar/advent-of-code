package advent_of_code.year_2021.day_8;

import lombok.Data;

@Data
public class DigitPattern {
    private String top;
    private String topLeft;
    private String topRight;
    private String middle;
    private String bottomLeft;
    private String bottomRight;
    private String bottom;
}
