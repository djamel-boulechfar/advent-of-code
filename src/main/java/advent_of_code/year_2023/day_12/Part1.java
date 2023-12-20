package advent_of_code.year_2023.day_12;

import advent_of_code.utils.MyFileReader;

import java.io.IOException;
import java.util.List;

public class Part1 {
    public static void main(String[] args) throws IOException {
        List<String> inputs = MyFileReader.readFileAndReturnStringList("src/main/java/advent_of_code/year_2023/day_12/input.txt");

        List<SpringRow> springRows = inputs.stream()
                .map(SpringRow::new)
                .toList();

        for (int i = 0; i < 10; i++) {
            System.out.println(springRows.get(i));
        }
    }
}
