package advent_of_code.year_2018.day_2;

import advent_of_code.utils.MyFileReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        List<String> ids = MyFileReader.readFileAndReturnStringList("C:\\DEV\\Java\\advent-of-code\\src\\main\\java\\djamel\\year_2018\\day_2\\input.txt");

        int twoLettersCount = 0;
        int threeLettersCount = 0;

        for (int i = 0; i < ids.size(); i++) {
            String id = ids.get(i);
            boolean twoLettersFound = false;
            boolean threeLettersFound = false;
            List<Character> checkedLetters = new ArrayList<>();

            for (int j = 0; j < id.length(); j++) {
                char letter = id.charAt(j);
                if (!checkedLetters.contains(letter)) {
                    checkedLetters.add(letter);
                    int count = (int) id.chars().filter(c -> c == letter).count();
                    if (count == 2 && !twoLettersFound) {
                        twoLettersCount++;
                        twoLettersFound = true;
                    }
                    if (count == 3 && !threeLettersFound) {
                        threeLettersCount++;
                        threeLettersFound = true;
                    }
                }
            }
        };

        System.out.println(twoLettersCount * threeLettersCount);
    }
}
