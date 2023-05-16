package advent_of_code.year_2015.day_3;

import advent_of_code.utils.MyFileReader;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Main {
    private static int[][] housesGrid;
    private static int gridWidth;
    private static int gridHeight;

    public static void main(String[] args) throws IOException {
        // Lecture de l'input
        List<String> directions = MyFileReader.readFileCharsAndReturnStringList("C:\\DEV\\Java\\advent-of-code\\src\\main\\java\\djamel\\year_2015\\day_3\\input.txt");

        // Initialisation de la grille
        gridWidth = directions.size() * 2;
        gridHeight = directions.size() * 2;
        housesGrid = new int[gridWidth][gridHeight];
        for (int[] row: housesGrid) Arrays.fill(row, 0);

        Santa santa = new Santa(housesGrid);
        // Livraison du premier cadeau dans la première maison
        santa.deliverPresent();

        // Partie 1
        for (int i = 0; i < directions.size(); i++) {
            santa.move(directions.get(i));
            santa.deliverPresent();
        }

        // Partie 2
//        Santa roboSanta = new Santa(housesGrid);
//        // Livraison du premier cadeau dans la première maison
//        roboSanta.deliverPresent();
//        for (int i = 0; i < directions.size(); i += 2) {
//            // Déplacement de Santa et livraison de son cadeau
//            santa.move(directions.get(i));
//            santa.deliverPresent();
//            // Déplacement de Robot Santa et livraison de son cadeau
//            roboSanta.move(directions.get(i + 1));
//            roboSanta.deliverPresent();
//        }

        // Affichage de la réponse
        int answer = countHousesWithAtLeastOnePresent();
        System.out.println("Réponse : " + answer);
    }

    public static int countHousesWithAtLeastOnePresent() {
        int res = 0;
        for (int line = 0; line < gridWidth; line++) {
            for (int column = 0; column < gridHeight; column++) {
                if (housesGrid[line][column] > 0) res++;
            }
        }
        return res;
    }
}
