package advent_of_code.year_2023.day_7.Part2;

import advent_of_code.utils.MyFileReader;
import advent_of_code.year_2023.day_7.Part1.Hand;
import advent_of_code.year_2023.day_7.Part1.HandComparator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Part2 {
    public static void main(String[] args) throws IOException {
        List<String> inputs = MyFileReader.readFileAndReturnStringList("src/main/java/advent_of_code/year_2023/day_7/input.txt");

        List<NewRuleHand> hands = new ArrayList<>(inputs.stream()
                .map(NewRuleHand::new)
                .toList());

        hands.sort(new NewRuleHandComparator());

        int result = 0;

        for (int i = 1; i < hands.size() + 1; i++) {
            NewRuleHand currentHand = hands.get(i - 1);
            System.out.println(currentHand);
            result += currentHand.getBid() * i;
        }

        System.out.println("Résultat : " + result);
    }
}
