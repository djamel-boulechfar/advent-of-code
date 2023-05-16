package advent_of_code.year_2021.day_3;

import advent_of_code.utils.MyFileReader;

import java.io.IOException;
import java.util.List;

public class Part1 {
    public static void main(String[] args) throws IOException {
        List<String> inputs = MyFileReader.readFileAndReturnStringList("src/main/java/advent_of_code/year_2021/day_3/input.txt");

        // Longueur des binaires
        int binaryLength = inputs.get(0).length();

        // Construction de gamma à partir des bits les plus fréquents de chaque colonne
        StringBuilder gammaBuilder = new StringBuilder();
        for (int i = 0; i < binaryLength; i++) gammaBuilder.append(mostCommonBitOfColumn(inputs, i));
        String gamma = gammaBuilder.toString();

        // Récupération d'epsilon à partir de l'inverse de gamma
        String epsilon = invertBinary(gamma);

        // Conversion de gamme et epsilon en int et multiplication des valeurs
        int decimalGamma = Integer.parseInt(gamma, 2);
        int decimalEpsilon = Integer.parseInt(epsilon, 2);
        int result = decimalGamma * decimalEpsilon;

        System.out.println("gamma * epsilon = " + result);

        int zer = 22;

        System.out.println("ouais : " + ~zer);
    }

    // Renvoie le bit le plus fréquent d'une colonne donnée sur la liste d'inputs donnée
    public static char mostCommonBitOfColumn(List<String> inputs, int columnNumber) {
        int zeroCount = (int) inputs.stream()
                .filter(input -> input.charAt(columnNumber) == '0')
                .count();

        int oneCount = (int) inputs.stream()
                .filter(input -> input.charAt(columnNumber) == '1')
                .count();

        return zeroCount > oneCount ? '0' : '1';
    }

    // Renvoie l'inverse du binaire donné, tout 0 devient un 1 et tout 1 devient un 0
    public static String invertBinary(String binary) {
        // Conversion en tableau de char pour pouvoir traiter chaque caractère
        char[] chars = binary.toCharArray();

        // Inversion de chaque caractère
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '0') chars[i] = '1';
            else if (chars[i] == '1') chars[i] = '0';
        }

        return String.valueOf(chars);
    }
}