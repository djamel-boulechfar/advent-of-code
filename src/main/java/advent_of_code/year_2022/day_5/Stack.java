package advent_of_code.year_2022.day_5;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Stack {
    private int id;
    private List<Character> crates;

    public Stack(int id) {
        this.id = id;
        this.crates = new ArrayList<>();
    }

    // Renvoie la lettre de la boîte du haut de la pile
    public Character getTopCrateLetter() {
        return this.crates.get(this.crates.size() - 1);
    }

    // Supprime la boîte du haut de la pile et renvoie sa lettre
    public Character pickTopCrate() {
        Character crateLetter = this.getTopCrateLetter();
        this.crates.remove(this.crates.size() - 1);
        return crateLetter;
    }

    public void addCrate(Character crateLetter) {
        this.crates.add(crateLetter);
    }
}
