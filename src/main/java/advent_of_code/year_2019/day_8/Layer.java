package advent_of_code.year_2019.day_8;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Layer {
    private List<Pixel> pixels;

    public Layer() {
        this.pixels = new ArrayList<>();
    }

    public long countPixelWithGivenValue(int value) {
        return this.pixels.stream()
                .filter(pixel -> value == pixel.getValue())
                .count();
    }
}
