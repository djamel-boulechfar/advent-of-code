package djamel.year_2020.day_3;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Area {
    private List<String> treeMap;

    public int countEncounteredTreesWithGivenSlope(Slope slope) {
        int treeCount = 0;

        // Position actuelle dans la pente
        int downPosition = 0;
        int rightPosition = 0;

        // Tant qu'on est pas arrivé en bas de la pente
        while (downPosition < this.treeMap.size()) {
            // On descend du nombre de pas indiqué par l'inclinaison donnée
            downPosition += slope.getDown();

            // Si on n'est pas plus bas que la fin de la pente
            if (downPosition < this.treeMap.size()) {
                // On récupère la rangée d'arbre correspondante à l'altitude actuelle dans la pente
                String treeLine = this.treeMap.get(downPosition);

                // On se déplace vers la droite du nombre de pas indiqué par l'inclinaison donnée
                rightPosition += slope.getRight();

                // Comme le motif de la rangée d'arbres se répète à l'infini, on calcule l'index de la position qu'il
                // faut vérifier en utilisant le modulo
                int indexToCheck = rightPosition % treeLine.length();

                char c = treeLine.charAt(indexToCheck);

                if (c == Obstacle.TREE.value) treeCount++;
            }
        }

        return treeCount;
    }
}
