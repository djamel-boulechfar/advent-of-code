package advent_of_code.year_2015.day_4;

import org.apache.commons.codec.digest.DigestUtils;

public class Main {
    public static void main(String[] args) {
        String input = "iwrupvqb";

        // Solution naive
        int part1Answer = findLowestNumberForHashToStartWithNTimesGivenCharacter(input, "0", 5);
        if (part1Answer != -1) System.out.println("Réponse partie 1 : " + part1Answer);
        else System.out.println("Pas de solution trouvée pour la partie 1.");

        int part2Answer = findLowestNumberForHashToStartWithNTimesGivenCharacter(input, "0", 6);
        if (part2Answer != -1) System.out.println("Réponse partie 2 : " + part2Answer);
        else System.out.println("Pas de solution trouvée pour la partie 2.");
    }

    private static int findLowestNumberForHashToStartWithNTimesGivenCharacter(String input, String startCharacter, int n) {
        int i = 0;
        while (i < Integer.MAX_VALUE) {
            String secretKey = input + i;

            String hash = DigestUtils.md5Hex(secretKey);

            if (hash.startsWith(startCharacter.repeat(n))) return i;

            i++;
        }

        return -1;
    }
}
