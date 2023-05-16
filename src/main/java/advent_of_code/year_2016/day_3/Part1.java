package advent_of_code.year_2016.day_3;

import advent_of_code.utils.MyFileReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Part1 {
    public static void main(String[] args) throws IOException {
        List<String> inputs = MyFileReader.readFileAndReturnStringList("C:\\DEV\\Java\\advent-of-code\\src\\main\\java\\advent_of_code\\year_2016\\day_3\\input.txt");

        // Retrait des espaces multiples
        for (int i = 0; i < inputs.size(); i++) inputs.set(i, inputs.get(i).trim().replaceAll(" +", " "));

        List<Triangle> triangles = createTriangles(inputs);

        // Compte les triangles possibles
        long possibleTrianglesCount = triangles.stream().filter(triangle -> triangle.isPossible()).count();

        System.out.println("Triangles possibles : " + possibleTrianglesCount);
    }

    public static List<Triangle> createTriangles(List<String> inputs) {
        List<Triangle> triangles = new ArrayList<>();

        for (String input: inputs) {
            String[] sides = input.split(" ");
            triangles.add(
                new Triangle(
                    Integer.parseInt(sides[0]),
                    Integer.parseInt(sides[1]),
                    Integer.parseInt(sides[2])
                )
            );
        }

        return triangles;
    }
}
