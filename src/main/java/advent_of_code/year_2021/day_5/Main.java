package advent_of_code.year_2021.day_5;

import advent_of_code.utils.MyFileReader;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        // Récupération de l'input sous forme de liste de String
        List<String> inputs = MyFileReader.readFileAndReturnStringList("src/main/java/advent_of_code/year_2021/day_5/input.txt");

        // Construction d'une liste d'HydrothermalVent à partir des inputs grâce au constructeur prévu à cet effet
        List<HydrothermalVent> hydrothermalVents = inputs.stream().map(HydrothermalVent::new).toList();

        // Filtrage des HydrothermalVents qui sont en diagonale (à commenter pour la partie 2)
        hydrothermalVents = hydrothermalVents.stream().filter(HydrothermalVent::isInStraightLine).toList();

        // Création d'une map stockant pour chaque position le nombre d'HydrothermalVents qui y passent
        Map<Position, Integer> positionsPassages = new HashMap<>();
        for (HydrothermalVent hydrothermalVent : hydrothermalVents) {
            for (Position position : hydrothermalVent.getLinePositions()) {
                positionsPassages.merge(position, 1, Integer::sum);
            }
        }

        // Compte les positions sur lesquelles plusieurs HydrothermalVents passent
        long answer = positionsPassages.values().stream().filter(value -> value > 1).count();

        System.out.println("Nombre de zones à éviter : " + answer);
    }
}
