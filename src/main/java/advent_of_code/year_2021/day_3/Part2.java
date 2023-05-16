package advent_of_code.year_2021.day_3;

import advent_of_code.utils.MyFileReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Part2 {
    public static void main(String[] args) throws IOException {
        List<String> inputs = MyFileReader.readFileAndReturnStringList("src/main/java/djamel/year_2021/day_3/input.txt");

        // Longueur des binaires
        int binaryLength = inputs.get(0).length();

        List<String> oxygenGeneratorRateCandidates = new ArrayList<>(inputs);

        int i = 0;

        while (i < binaryLength && oxygenGeneratorRateCandidates.size() > 1) {
            char mostCommonBit = Part1.mostCommonBitOfColumn(inputs, i);

            final int finalI = i;
            oxygenGeneratorRateCandidates.removeIf(candidate -> candidate.charAt(finalI) != mostCommonBit);

            i++;
        }
    }

    // Renvoie le bit le plus fréquent d'une colonne donnée sur la liste d'inputs donnée
    public static char mostCommonOrUncommonBitOfColumn(List<String> inputs, int columnNumber, boolean mostCommon, char resultIfEqual) {
        int zeroCount = (int) inputs.stream()
                .filter(input -> input.charAt(columnNumber) == '0')
                .count();

        int oneCount = (int) inputs.stream()
                .filter(input -> input.charAt(columnNumber) == '1')
                .count();

        if (zeroCount == oneCount) return resultIfEqual;

        if (mostCommon) return zeroCount > oneCount ? '0' : '1';

        return '1';
    }
}
