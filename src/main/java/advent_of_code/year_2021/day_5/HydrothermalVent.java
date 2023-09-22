package advent_of_code.year_2021.day_5;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class HydrothermalVent {
    private Position start;
    private Position end;
    private List<Position> linePositions;

    public HydrothermalVent(String input) {
        String[] positions = input.split(" -> ");

        if (positions.length == 2) {
            this.start = new Position(positions[0]);
            this.end = new Position(positions[1]);
            this.computeLinePositions();
        } else {
            throw new IllegalArgumentException("Impossible d'utiliser la chaîne '" + input + "' pour créer une HydrothermalVent.");
        }
    }

    public boolean isHorizontal() {
        return this.start.getX() == this.end.getX();
    }

    public boolean isVertical() {
        return this.start.getY() == this.end.getY();
    }

    public boolean isInStraightLine() {
        return this.isHorizontal() || this.isVertical();
    }

    private void computeLinePositions() {
        this.linePositions = new ArrayList<>();

        // Ajout de la position de départ
        this.linePositions.add(this.start);

        // Coordonnées de départ
        int currentX = this.start.getX();
        int currentY = this.start.getY();

        // Booléens indiquant si l'on est arrivé à la valeur max de chaque coordonnée de la ligne représentant
        // l'HydrothermalVent
        boolean reachedFinalX = currentX == this.end.getX();
        boolean reachedFinalY = currentY == this.end.getY();

        // Tant qu'on a pas atteint ces deux valeurs
        while(!(reachedFinalX && reachedFinalY)) {
            // Incrémentation ou décrémentation de la coordonnée X en fonction des valeurs de départ et de fin
            if (!reachedFinalX) {
                if (this.getStart().getX() < this.getEnd().getX()) {
                    currentX++;
                } else {
                    currentX--;
                }
            }

            // De même pour la coordonnée Y
            if (!reachedFinalY) {
                if (this.getStart().getY() < this.getEnd().getY()) {
                    currentY++;
                } else {
                    currentY--;
                }
            }

            // Ajout de la nouvelle position à la liste des positions de l'HydrothermalVent
            this.linePositions.add(new Position(currentX, currentY));

            // Actualisation des booléens
            reachedFinalX = currentX == this.end.getX();
            reachedFinalY = currentY == this.end.getY();
        }
    }
}
