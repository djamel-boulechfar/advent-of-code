package advent_of_code.year_2021.day_5;

import advent_of_code.utils.MyFileReader;

import java.io.IOException;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        // Récupération de l'input sous forme de liste de String
        List<String> inputs = MyFileReader.readFileAndReturnStringList("src/main/java/advent_of_code/year_2021/day_5/input.txt");

        long answer = inputs.stream()
                // Construction d'une liste d'HydrothermalVent à partir des inputs grâce au constructeur prévu à cet effet
                .map(HydrothermalVent::new)
                // Filtrage des HydrothermalVents qui sont en diagonale (à commenter pour la partie 2)
                .filter(HydrothermalVent::isInStraightLine)
                // Récupération de toutes les Position de chaque HydrothermalVent sous forme de liste
                .map(HydrothermalVent::getLinePositions)
                // Création d'une unique liste contenant toutes les Position de toutes les HydrothermalVent
                .flatMap(List::stream)
                // Crée une map ayant pour clé une Position et pour valeur son nombre d'occurrences dans la liste précédente
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        Collectors.counting()
                ))
                // Filtre les entrées de la map dont la valeur est supérieure à 1, ce qui correspond aux positions sur
                // lesquelles plusieurs HydrothermalVent passent
                .values().stream()
                .filter(value -> value > 1)
                .count();

        System.out.println("Nombre de zones à éviter : " + answer);
    }
}
