package advent_of_code.year_2019.day_8;

import advent_of_code.utils.MyFileReader;

import java.io.IOException;
import java.util.List;

public class Main {
    private static final int IMAGE_WIDTH = 25;
    private static final int IMAGE_HEIGHT = 6;
    private static final int LAYER_PIXEL_COUNT = IMAGE_WIDTH * IMAGE_HEIGHT;

    public static void main(String[] args) throws IOException {
        List<Integer> numbers = MyFileReader.readFileAndReturnIntList("src/main/java/advent_of_code/year_2019/day_8/input.txt", "");

        int layerCount = numbers.size() / LAYER_PIXEL_COUNT;

        // IMAGE INITIALIZATION

        Image image = new Image();
        for (int layerIndex = 0; layerIndex < layerCount; layerIndex++) {
            Layer layer = new Layer();

            for (int rowIndex = 0; rowIndex < IMAGE_HEIGHT; rowIndex++) {
                for (int columnIndex = 0; columnIndex < IMAGE_WIDTH; columnIndex++) {
                    int pixelValueIndex = (layerIndex * LAYER_PIXEL_COUNT) + (rowIndex * IMAGE_WIDTH) + columnIndex;
                    layer.getPixels().add(new Pixel(columnIndex, rowIndex, numbers.get(pixelValueIndex)));
                }
            }

            image.getLayers().add(layer);
        }

        // PART 1

        Layer layerWithFewestZeros = image.rowWithFewestZeros();

        long result = layerWithFewestZeros.countPixelWithGivenValue(1) * layerWithFewestZeros.countPixelWithGivenValue(2);

        System.out.println("Part 1 answer = " + result);

        // PART 2

        Layer finalLayer = new Layer();
        for (int rowIndex = 0; rowIndex < IMAGE_HEIGHT; rowIndex++) {
            for (int columnIndex = 0; columnIndex < IMAGE_WIDTH; columnIndex++) {
                for (Layer currentLayer : image.getLayers()) {
                    Pixel layerPixel = currentLayer.getPixelAt(columnIndex, rowIndex);
                    if (2 != layerPixel.getValue()) {
                        finalLayer.getPixels().add(layerPixel);
                    }
                }
            }
        }

        System.out.println("\nPart 2 answer :\n");
        for (int rowIndex = 0; rowIndex < IMAGE_HEIGHT; rowIndex++) {
            for (int columnIndex = 0; columnIndex < IMAGE_WIDTH; columnIndex++) {
                switch (finalLayer.getPixelAt(columnIndex, rowIndex).getValue()) {
                    case 0 -> System.out.print("   ");
                    case 1 -> System.out.print(" ⬜ ");
                    default -> System.out.print("   ");
                }
            }
            System.out.print("\n");
        }
    }
}
