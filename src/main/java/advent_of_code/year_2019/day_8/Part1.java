package advent_of_code.year_2019.day_8;

import advent_of_code.utils.MyFileReader;

import java.io.IOException;
import java.util.List;

public class Part1 {
    private static final int IMAGE_WIDTH = 25;
    private static final int IMAGE_HEIGHT = 6;
    private static final int LAYER_PIXEL_COUNT = IMAGE_WIDTH * IMAGE_HEIGHT;

    public static void main(String[] args) throws IOException {
        List<Integer> numbers = MyFileReader.readFileAndReturnIntList("src/main/java/advent_of_code/year_2019/day_8/input.txt", "");

        Image image = new Image();

        Layer layer = new Layer();
        for (int i = 0; i < numbers.size(); i++) {
            int columnIndex = i % LAYER_PIXEL_COUNT;
            int rowIndex = i / LAYER_PIXEL_COUNT;


            layer.getPixels().add(new Pixel(numbers.get(i)));

            // + 1 car on veut les itérations et pas l'index
            if ((i + 1) % LAYER_PIXEL_COUNT == 0) {
                image.getLayers().add(layer);
                layer = new Layer();
            }
        }

        Layer layerWithFewestZeros = image.rowWithFewestZeros();

        long result = layerWithFewestZeros.countPixelWithGivenValue(1) * layerWithFewestZeros.countPixelWithGivenValue(2);

        System.out.println("Part 1 answer = " + result);
    }
}
