package advent_of_code.year_2015.day_6;

import advent_of_code.utils.MyFileReader;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Part1 {
    public static void main(String[] args) throws IOException {
        List<String> inputs = MyFileReader.readFileAndReturnStringList("src/main/java/advent_of_code/year_2015/day_6/input.txt");

        // Création de la liste d'instructions avec le constructeur prévu à l'effet
        List<Instruction> instructions = inputs.stream().map(Instruction::new).toList();

        // Initialisation d'une map liant chaque position à un état d'éclairage
        Map<Position, Light> positionsLightsMap = computePositionsLightsMap(instructions);

        long answer = positionsLightsMap.values().stream()
                .filter(light -> light.getState().equals(LightState.ON))
                .count();

        System.out.println("Réponse : " + answer);
    }

    // Génère une map ayant pour clé une position et pour valeur une lumière dont l'état est déterminé par l'ensemble
    // des instructions
    public static Map<Position, Light> computePositionsLightsMap(List<Instruction> instructions) {
        Map<Position, Light> positionsLightsMap = new HashMap<>();

        // Parcours de chaque instruction et setting de chaque entrée de la map en fonction de l'action
        for (Instruction instruction: instructions) {
            // Récupération du "sens" de parcours de chaque coordonnée de l'instruction
            int xIncrement = instruction.getStartPosition().getX() < instruction.getEndPosition().getX() ? 1 : -1;
            int yIncrement = instruction.getStartPosition().getY() < instruction.getEndPosition().getY() ? 1 : -1;

            // Parcours sur la coordonnée X puis la coordonnée Y
            int currentX = instruction.getStartPosition().getX();
            // Tant qu'on est pas arrivé à la position finale (+ l'incrément pour inclure la dernière position)
            while (currentX != instruction.getEndPosition().getX() + xIncrement) {
                int currentY = instruction.getStartPosition().getY();

                while (currentY != instruction.getEndPosition().getY() + yIncrement) {
                    setPositionLightState(currentX, currentY, instruction.getAction(), positionsLightsMap);
                    currentY += yIncrement;
                }

                currentX += xIncrement;
            }
        }

        return positionsLightsMap;
    }

    // Set l'état d'une lumière à une position donnée en fonction de son état actuel et de l'action à effectuer
    public static void setPositionLightState(int x, int y, Action action, Map<Position, Light> positionsLightsMap) {
        Position position = new Position(x, y);

        // Si la position n'a pas encore été renseignée dans la map, on l'initialise avec une lumière éteinte
        if (!positionsLightsMap.containsKey(position)) {
            positionsLightsMap.put(position, new Light(LightState.OFF));
        }

        // Récupération de la lumière à la position donnée
        Light light = positionsLightsMap.get(position);

        // Modification de l'état de la lumière en fonction de l'action à effectuer
        switch (action) {
            case TURN_ON:
                light.setState(LightState.ON);
                break;
            case TURN_OFF:
                light.setState(LightState.OFF);
                break;
            case TOGGLE:
                light.toggle();
                break;
            default:
                throw new IllegalArgumentException("Action inconnue : " + action);
        }
    }
}
