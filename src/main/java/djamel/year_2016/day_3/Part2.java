package djamel.year_2016.day_3;

import djamel.utils.MyFileReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Part2 {
    public static void main(String[] args) throws IOException {
        List<String> inputs = MyFileReader.readFileAndReturnStringList("C:\\DEV\\Java\\advent-of-code\\src\\main\\java\\djamel\\year_2016\\day_3\\input.txt");

        // Retrait des espaces multiples
        for (int i = 0; i < inputs.size(); i++) inputs.set(i, inputs.get(i).trim().replaceAll(" +", " "));

        List<Integer> integers = linesToIntegers(inputs);

        List<Triangle> triangles = createTriangles(integers);

        // Compte les triangles possibles
        long possibleTrianglesCount = triangles.stream().filter(triangle -> triangle.isPossible()).count();

        System.out.println("Triangles possibles : " + possibleTrianglesCount);
    }

    public static List<Integer> linesToIntegers(List<String> inputs) {
        List<Integer> integers = new ArrayList<>();

        for (String input: inputs) {
            String[] numbers = input.split(" ");
            for (String number: numbers) integers.add(Integer.parseInt(number));
        }

        return integers;
    }

    public static List<Triangle> createTriangles(List<Integer> integers) {
        List<Triangle> triangles = new ArrayList<>();
        // De 9 en 9 pour traiter 3 colonnes de taille 3 par itération
        for (int i = 0; i < integers.size(); i += 9) {
            // Création d'un triangle pour chaque colonne
            for (int j = 0; j < 3; j++) {
                // Décalage de i pour chaque colonne
                int newI = i + j;

                if (newI + 3 < integers.size() && newI + 6 < integers.size()) {
                    triangles.add(
                            new Triangle(
                                    integers.get(newI),
                                    integers.get(newI + 3),
                                    integers.get(newI + 6)
                            )
                    );
                }
            }

        }

        return triangles;
    }
}
