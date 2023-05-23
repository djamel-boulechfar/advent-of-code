package advent_of_code.year_2015.day_4;

import org.apache.commons.codec.digest.DigestUtils;

import java.util.NoSuchElementException;

public class Main {
    public static void main(String[] args) {
        String input = "iwrupvqb";
        
        int part1Answer = findLowestNumberForHashToStartWithNTimesGivenCharacter(input, "0", 5);
        System.out.println("Réponse partie 1 : " + part1Answer);

        int part2Answer = findLowestNumberForHashToStartWithNTimesGivenCharacter(input, "0", 6);
        System.out.println("Réponse partie 2 : " + part2Answer);
    }

    private static int findLowestNumberForHashToStartWithNTimesGivenCharacter(String input, String startCharacter, int n) {
        int i = 0;

        String searched = startCharacter.repeat(n);

        while (i < Integer.MAX_VALUE) {
            String secretKey = input + i;

            String hash = DigestUtils.md5Hex(secretKey);

            if (hash.startsWith(searched)) return i;

            i++;
        }

        throw new NoSuchElementException("Pas de solution trouvée pour l'input " + input + ", le caractère '" + startCharacter + "' et n=" + n);
    }
}
