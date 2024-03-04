package advent_of_code.year_2017.day_7;

import advent_of_code.utils.MyFileReader;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class Part1 {
    public static void main(String[] args) throws IOException {
        List<String> input = MyFileReader.readFileAndReturnStringList("src/main/java/advent_of_code/year_2017/day_7/input.txt");

        Map<String, Tower> towers = input.stream()
                .map(Tower::new)
                .collect(Collectors.toMap(Tower::getId, tower -> tower));

        for (Map.Entry<String, Tower> entry : towers.entrySet()) {
            Tower currentTower = entry.getValue();

            if (currentTower.getNext() != null) {
                for (String next: currentTower.getNext()) {
                    Tower subTower = towers.get(next);

                    if (subTower != null) {
                        subTower.setPrev(Optional.of(entry.getKey()));
                    }
                }
            }
        }

        Tower currentTower = towers.entrySet().iterator().next().getValue();

        while (currentTower.getPrev().isPresent()) {
            currentTower = towers.get(currentTower.getPrev().get());
        }

        System.out.println("Answer = " + currentTower.getId());
    }
}
