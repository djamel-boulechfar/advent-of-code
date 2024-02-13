package advent_of_code.year_2016.day_7;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class IpAddress {
    private List<String> supernetSequences;
    private List<String> hypernetSequences;

    public IpAddress(String line) {
        this.supernetSequences = new ArrayList<>();
        this.hypernetSequences = new ArrayList<>();

        StringBuilder currentSequence = new StringBuilder();

        for(char c: line.toCharArray()) {
            switch (c) {
                case '[':
                    this.supernetSequences.add(currentSequence.toString());
                    currentSequence.delete(0, currentSequence.length());
                    break;
                case ']':
                    this.hypernetSequences.add(currentSequence.toString());
                    currentSequence.delete(0, currentSequence.length());
                    break;
                default:
                    currentSequence.append(c);
                    break;
            }
        }

        this.supernetSequences.add(currentSequence.toString());
    }

    public boolean supportsTls() {
        return this.hasAbbaInSequences(this.supernetSequences) && !this.hasAbbaInSequences(this.hypernetSequences);
    }

    private boolean hasAbbaInSequences(List<String> sequences) {
        for (String sequence: sequences) {
            if (this.sequenceContainsAbba(sequence)) {
                return true;
            }
        }
        return false;
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
