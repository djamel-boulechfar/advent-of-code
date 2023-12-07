package advent_of_code.year_2023.day_7.Part1;

import advent_of_code.utils.MyFileReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Part1 {
    public static void main(String[] args) throws IOException {
        List<String> inputs = MyFileReader.readFileAndReturnStringList("src/main/java/advent_of_code/year_2023/day_7/input.txt");

        List<Hand> hands = new ArrayList<>(inputs.stream()
                .map(Hand::new)
                .toList());

        hands.sort(new HandComparator());

        int result = 0;

        for (int i = 1; i < hands.size() + 1; i++) {
            Hand currentHand = hands.get(i - 1);
            result += currentHand.getBid() * i;
        }

        System.out.println("Résultat : " + result);
    }
}
