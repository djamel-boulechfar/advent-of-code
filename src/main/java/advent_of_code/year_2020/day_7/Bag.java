package advent_of_code.year_2020.day_7;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class Bag {
    private Color color;
    private Map<Bag, Integer> containableBagsByQuantity;

    public Bag(Color color) {
        this.color = color;
        this.containableBagsByQuantity = new HashMap<>();
    }
}
