package advent_of_code.year_2019.day_8;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Image {
    private List<Layer> layers;

    public Image() {
        this.layers = new ArrayList<>();
    }

    public Layer rowWithFewestZeros() {
        Layer result = layers.get(0);

        for (Layer layer : this.layers) {
            if (layer.countPixelWithGivenValue(0) < result.countPixelWithGivenValue(0)) {
                result = layer;
            }
        }

        return result;
    }
}
