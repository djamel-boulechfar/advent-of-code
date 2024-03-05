package advent_of_code.year_2017.day_7;

import advent_of_code.utils.MyFileReader;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Part1 {
    public static void main(String[] args) throws IOException {
        List<String> input = MyFileReader.readFileAndReturnStringList("src/main/java/advent_of_code/year_2017/day_7/input.txt");

        Map<String, Tower> towersByIdMap = input.stream()
                .map(Tower::new)
                .collect(Collectors.toMap(Tower::getId, Function.identity()));

        towersByIdMap.entrySet().stream()
                .filter(towerEntry -> towerEntry.getValue().getNextTowerIds() != null)
                .forEach(towerEntry -> setNextTowersPrevTowerId(towerEntry, towersByIdMap));

        Tower currentTower = towersByIdMap.entrySet().iterator().next().getValue();

        while (currentTower.getPrevTowerId().isPresent()) {
            currentTower = towersByIdMap.get(currentTower.getPrevTowerId().get());
        }

        System.out.println("Answer = " + currentTower.getId());
    }

    private static void setNextTowersPrevTowerId(Map.Entry<String, Tower> towerEntry, Map<String, Tower> towersById) {
        Tower currentTower = towerEntry.getValue();

        for (String nextTowerId: currentTower.getNextTowerIds()) {
            Tower subTower = towersById.get(nextTowerId);

            if (subTower != null) {
                subTower.setPrevTowerId(Optional.of(towerEntry.getKey()));
            }
        }
    }
}
