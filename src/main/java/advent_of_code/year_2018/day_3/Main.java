package advent_of_code.year_2018.day_3;

import advent_of_code.utils.MyFileReader;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        List<String> inputs = MyFileReader.readFileAndReturnStringList("src/main/java/djamel/year_2018/day_3/input.txt");

        List<Claim> claims = inputsToClaims(inputs);

        Map<Position, Integer> fabric = new HashMap<>();

        claims.forEach(claim -> claim.claimFabricArea(fabric));

        // Partie 1
        long result = getOverlapCount(fabric);

        System.out.println("Overlaps : " + result);

        // Partie 2
        int id = idOfNonOverlappingClaim(fabric, claims);

        System.out.println(id);
    }

    public static List<Claim> inputsToClaims(List<String> inputs) {
        return inputs.stream()
                .map(Claim::inputToClaim)
                .toList();
    }

    public static long getOverlapCount(Map<Position, Integer> fabric) {
        return fabric.entrySet().stream()
                .filter(entry -> entry.getValue().equals(-1))
                .count();
    }

    public static int idOfNonOverlappingClaim(Map<Position, Integer> fabric, List<Claim> claims) {
        for (Claim claim : claims) {
            int coveredSquares = (int) fabric.entrySet().parallelStream()
                    .filter(entry -> entry.getValue() == claim.getId())
                    .count();
            if (coveredSquares == claim.getWidth() * claim.getHeight()) return claim.getId();
        }
        return -1;
    }
}
