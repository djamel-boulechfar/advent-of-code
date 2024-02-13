package advent_of_code.year_2016.day_7;

import advent_of_code.utils.MyFileReader;

import java.io.IOException;
import java.util.List;

public class Part1 {
    public static void main(String[] args) throws IOException {
        List<String> input = MyFileReader.readFileAndReturnStringList("src/main/java/advent_of_code/year_2016/day_7/input.txt");

        long answer = input.stream()
                .map(IpAddress::new)
                .filter(IpAddress::supportsTls)
                .count();

        System.out.println("Réponse : " + answer);
    }
}
