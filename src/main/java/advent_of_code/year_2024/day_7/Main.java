package advent_of_code.year_2024.day_7;

import advent_of_code.utils.MyFileReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        List<String> input = MyFileReader.readFileAndReturnStringList("src/main/java/advent_of_code/year_2024/day_7/input.txt");

        List<Equation> equations = input.stream()
                .map(Equation::new)
                .toList();
    }
}
