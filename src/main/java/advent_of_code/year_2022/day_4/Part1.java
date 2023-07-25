package advent_of_code.year_2022.day_4;

import advent_of_code.utils.MyFileReader;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Part1 {
    public static void main(String[] args) throws IOException {
        List<String> inputs = MyFileReader.readFileAndReturnStringList("src/main/java/advent_of_code/year_2022/day_4/input.txt");

        List<Pair<Elf, Elf>> elvesPairs = generateElvesPairs(inputs);

        long fullyOverlappedAssignmentsCount = getFullyOverlappedCount(elvesPairs);

        System.out.println(fullyOverlappedAssignmentsCount);
    }

    private static List<Pair<Elf, Elf>> generateElvesPairs(List<String> inputs) {
        List<Pair<Elf, Elf>> elvesPairs = new ArrayList<>();
        for (String input : inputs) {
            String[] elvesAssignments = input.split(",");

            String[] firstElfSections = elvesAssignments[0].split("-");
            String[] secondElfSections = elvesAssignments[1].split("-");

            Elf firstElf = new Elf(Integer.parseInt(firstElfSections[0]), Integer.parseInt(firstElfSections[1]));
            Elf secondElf = new Elf(Integer.parseInt(secondElfSections[0]), Integer.parseInt(secondElfSections[1]));

            elvesPairs.add(new ImmutablePair<>(firstElf, secondElf));
        }
        return elvesPairs;
    }

    private static long getFullyOverlappedCount(List<Pair<Elf, Elf>> elvesPairs) {
        return elvesPairs.stream()
                .filter(elvesPair -> {
                    Elf firstElf = elvesPair.getLeft();
                    Elf secondElf = elvesPair.getRight();
                    return firstElf.sectionsRangeFullyContainsOtherElfSectionsRange(secondElf)
                            || secondElf.sectionsRangeFullyContainsOtherElfSectionsRange(firstElf);
                })
                .count();
    }
}
