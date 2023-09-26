package advent_of_code.year_2022.day_5;

import advent_of_code.utils.MyFileReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Part1 {
    public static void main(String[] args) throws IOException {
        List<String> inputs = MyFileReader.readFileAndReturnStringList("src/main/java/advent_of_code/year_2022/day_5/input.txt");

        // Récupération des lignes qui correspondent à l'état de départ des piles
        List<String> startStacks = new ArrayList<>();
        int index = 0;
        while (!inputs.get(index).isBlank()) {
            startStacks.add(inputs.get(index));
            index++;
        }

        List<Stack> stacks = initStacks(startStacks);

        // Récupération de la liste des mouvements à effectuer (reste de la liste)
        List<String> moves = inputs.subList(index + 1, inputs.size());

        // Execution de la procédure de déplacement des boîtes
        computeRearrangementProcedure(stacks, moves);

        // Récupération de chaque lettre des boîtes en haut de pile et création d'une châine de caractère
        String answer = stacks.stream()
                .map(Stack::getTopCrateLetter)
                .toList().stream()
                .map(Object::toString)
                .collect(Collectors.joining());

        System.out.println("Réponse : " + answer);
    }

    /**
     * Initialise la liste des piles de boîtes.
     * @param inputs Ensemble d'inputs représentant l'état de base des piles.
     * @return Une liste de pile initialisée avec les boîtes dans chacune d'entre elles.
     */
    public static List<Stack> initStacks(List<String> inputs) {
        // Récupération de la liste des numéros de piles
        String idsLine = inputs.get(inputs.size() - 1);

        // Création d'une pile pour chaque numéro
        List<Stack> stacks = Pattern.compile("\\d").matcher(idsLine).results()
                .map(MatchResult::group)
                .map(Integer::parseInt)
                .map(Stack::new)
                .toList();

        // Pour chaque ligne représentant les piles, du bas vers le haut
        for (int i = inputs.size() - 2; i >= 0; i--) {
            // Récupération de la ligne courante
            String currentInput = inputs.get(i);
            // "Curseur" indiquant la position actuelle dans la chaîne de caractères
            int charIndex = 0;
            // Pour chaque pile existante
            for (Stack stack : stacks) {
                // Récupération de 3 caractères censés représenter une boîte
                String currentStackCharacters = currentInput.substring(charIndex, charIndex + 3);
                // Si la chaîne récupérée n'est pas vide et qu'elle est sous la forme [char], ajout d'une boîte à la pile
                // courante
                if (!currentStackCharacters.isBlank() && currentStackCharacters.matches("\\[[A-Za-z]\\]")) {
                    stack.getCrates().add(currentStackCharacters.charAt(1));
                }
                // Déplacement du curseur jusqu'au début de la prochaine boîte
                charIndex += 4;
            }
        }

        return stacks;
    }

    /**
     * Effectue un ensemble d'instructions de déplacement de boîtes pour un ensemble de piles.
     * @param stacks Ensemble des piles de boîtes.
     * @param moves Ensemble des instructions de déplacement.
     */
    public static void computeRearrangementProcedure(List<Stack> stacks, List<String> moves) {
        // Pour chaque input représentant un déplacement
        for (String move : moves) {
            // Récupération des nombres de l'input
            int[] digits = getMoveInputDigits(move);

            // Si on a bien les 3 nombres requis
            if (digits.length == 3) {
                // Appel à la méthode de déplacement avec les bonnes informations
                int numberOfCratesToMove = digits[0];
                int sourceStackId = digits[1];
                int destinationStackId = digits[2];
                moveCratesFromAStackToAnother(numberOfCratesToMove, sourceStackId, destinationStackId, stacks);
            } else {
                // Sinon on renvoie une erreur
                throw new IllegalArgumentException("Impossible d'effectuer cette étape de la procédure : " + move);
            }
        }
    }

    /**
     *
     * @param moveInput Chaîne correspondant au déplacement à effectuer.
     * @return Un tableau contenant les 3 nombres présents dans la chaîne.
     */
    public static int[] getMoveInputDigits(String moveInput) {
        return Pattern.compile("\\d+").matcher(moveInput).results().toList().stream()
                .map(MatchResult::group)
                .mapToInt(Integer::parseInt)
                .toArray();
    }

    /**
     * Déplace des boîtes d'une pile à une autre.
     * @param numberOfCratesToMove Nombre de boîtes à déplacer.
     * @param sourceStackId ID de la pile source.
     * @param destinationStackId ID de la pile destination.
     * @param stacks Liste des piles.
     */
    public static void moveCratesFromAStackToAnother(int numberOfCratesToMove, int sourceStackId, int destinationStackId, List<Stack> stacks) {
        // Tentative de récupération des piles correspondant aux IDs donnés
        Stack sourceStack = stacks.stream().filter(stack -> stack.getId() == sourceStackId).findFirst().orElse(null);
        Stack destinationStack = stacks.stream().filter(stack -> stack.getId() == destinationStackId).findFirst().orElse(null);

        // Si les deux piles ont été trouvées, on effectue le déplacement de la quantité de boîtes demandée
        if (sourceStack != null && destinationStack != null) {
            for (int i = 0; i < numberOfCratesToMove; i++) {
                Character pickedCrateLetter = sourceStack.pickTopCrate();
                destinationStack.addCrate(pickedCrateLetter);
            }
        }

        // Sinon on renvoie une erreur pour chaque pile non trouvée
        if (sourceStack == null) {
            throw new NoSuchElementException("Impossible de trouver une pile avec l'ID " + sourceStackId);
        }
        if (destinationStack == null) {
            throw new NoSuchElementException("Impossible de trouver une pile avec l'ID " + destinationStackId);
        }
    }
}
