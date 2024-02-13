package advent_of_code.year_2016.day_7;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

@Data
public class IpAddress {
    private List<String> supernetSequences;
    private List<String> hypernetSequences;

    public IpAddress(String line) {
        this.supernetSequences = new ArrayList<>();
        this.hypernetSequences = new ArrayList<>();

        List<String> words = Pattern.compile("\\w+").matcher(line).results()
                .map(MatchResult::group)
                .toList();

        if (words.isEmpty()) {
            throw new IllegalArgumentException("Unable to create IpAddress from line : " + line);
        }

        this.supernetSequences = IntStream
                .range(0, words.size())
                .filter(i -> i % 2 == 0)
                .mapToObj(words::get)
                .toList();

        this.hypernetSequences = IntStream
                .range(0, words.size())
                .filter(i -> i % 2 == 1)
                .mapToObj(words::get)
                .toList();
    }

    public boolean supportsTls() {
        return this.hasAbbaInSequences(this.supernetSequences) && !this.hasAbbaInSequences(this.hypernetSequences);
    }

    private boolean hasAbbaInSequences(List<String> sequences) {
        return sequences.stream()
                .anyMatch(this::sequenceContainsAbba);
    }

    private boolean sequenceContainsAbba(String sequence) {
        for (int i = 0; i < sequence.length() - 3; i++) {
            if (sequence.charAt(i) != sequence.charAt(i + 1) &&
                    sequence.charAt(i) == sequence.charAt(i + 3) &&
                    sequence.charAt(i + 1) == sequence.charAt(i + 2)) {
                return true;
            }
        }
        return false;
    }
}
