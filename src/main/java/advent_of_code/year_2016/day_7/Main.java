package advent_of_code.year_2016.day_7;

import advent_of_code.utils.MyFileReader;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        List<String> input = MyFileReader.readFileAndReturnStringList("src/main/java/advent_of_code/year_2016/day_7/input.txt");

        List<IpAddress> ipAddresses = input.stream()
                .map(IpAddress::new)
                .toList();

        long part1Answer = ipAddresses.stream()
                .filter(IpAddress::supportsTls)
                .count();

        System.out.println("Réponse partie 1 : " + part1Answer);

        long part2Answer = ipAddresses.stream()
                .filter(IpAddress::supportsSsl)
                .count();

        System.out.println("Réponse partie 2 : " + part2Answer);
    }
}
