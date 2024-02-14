package advent_of_code.year_2016.day_7;

import lombok.Data;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Data
public class IpAddress {
    private List<String> supernetSequences;
    private List<String> hypernetSequences;

    public IpAddress(String line) {
        // Get each word of line
        List<String> words = Pattern.compile("\\w+").matcher(line).results()
                .map(MatchResult::group)
                .toList();

        if (words.isEmpty()) {
            throw new IllegalArgumentException("Unable to create IpAddress from line : " + line);
        }

        // IPV7 has the following pattern : "supernet_sequence[hypernet_sequence]supernet_sequence" that can be repeated
        // -> Every word at an even index is a supernet sequence and every word at an odd index is an hypernet sequence

        // Get each word that is at an even index in list -> corresponds to every supernet sequences
        this.supernetSequences = IntStream
                .range(0, words.size())
                .filter(i -> i % 2 == 0)
                .mapToObj(words::get)
                .toList();

        // Get each word that is at an odd index in list -> corresponds to every hypernet sequences
        this.hypernetSequences = IntStream
                .range(0, words.size())
                .filter(i -> i % 2 == 1)
                .mapToObj(words::get)
                .toList();
    }

    // = TLS ===========================================================================================================

    /**
     * Checks if IP address supports TLS, i.e. if any of its supernet sequences contains an ABBA and none of its
     * hypernet sequences does.
     * @return true if IP address supports TLS, otherwise false.
     */
    public boolean supportsTls() {
        return this.hasAbbaInSequences(this.supernetSequences) && !this.hasAbbaInSequences(this.hypernetSequences);
    }

    /**
     * Checks if at least one of given sequences contains an ABBA subsequence.
     * @param sequences Sequences to check.
     * @return true if at least one of the given sequences contains an ABBA, otherwise false.
     */
    private boolean hasAbbaInSequences(List<String> sequences) {
        return sequences.stream()
                .anyMatch(this::sequenceContainsAbba);
    }

    /**
     * Checks if given sequence contains an ABBA subsequence.
     * @param sequence Sequence to check.
     * @return true if given sequence contains an ABBA, otherwise false.
     */
    private boolean sequenceContainsAbba(String sequence) {
        for (int i = 0; i < sequence.length() - 3; i++) { // Loop until 4 characters left
            // Checks if character at index i and its three following characters form an ABBA
            if (sequence.charAt(i) != sequence.charAt(i + 1) && // Checks that first and second characters are different (ABxx)
                    sequence.charAt(i) == sequence.charAt(i + 3) && // Checks that first and last characters are the same (AxxA)
                    sequence.charAt(i + 1) == sequence.charAt(i + 2)) { // Checks that second and third characters are the same (xBBx)
                return true;
            }
        }
        return false;
    }

    // = SSL ===========================================================================================================

    /**
     * Checks if IP address supports SSL, i.e. if any of its hypernet sequences contains a BAB of any ABA contained in
     * one of its supernet sequences.
     * @return true if IP address supports SSL, otherwise false.
     */
    public boolean supportsSsl() {
        Set<String> supernetSequencesAbaList = this.getAllAbaInSupernetSequences();

        return this.hypernetSequences.stream()
                .anyMatch(hypernetSequence -> this.sequenceContainsAtLeastOneBab(hypernetSequence, supernetSequencesAbaList));
    }

    /**
     * Get a set of every ABA contained in IP address supernet sequences.
     * @return A set of every ABA contained in IP address supernet sequences
     */
    private Set<String> getAllAbaInSupernetSequences() {
        return this.supernetSequences.stream()
                .map(this::getAllAbaInSequence)
                .flatMap(Set::stream)
                .collect(Collectors.toSet());
    }

    /**
     * Get a set of every ABA subsequences contained in given sequence.
     * @param sequence Sequence to search ABA subsequences in.
     * @return A set of every ABA contained in given sequence.
     */
    private Set<String> getAllAbaInSequence(String sequence) {
        Set<String> result = new HashSet<>();

        for (int i = 0; i < sequence.length() - 2; i++) { // Loop until 3 characters left
            // Checks if character at index i and its two following characters form an ABA :
            // If first and second characters are different (ABx) and first and last characters are the same (AxA)
            if (sequence.charAt(i) != sequence.charAt(i + 1) && sequence.charAt(i) == sequence.charAt(i + 2)) {
                // Add corresponding String to result set
                String aba = String.valueOf(sequence.charAt(i)) +
                        sequence.charAt(i + 1) +
                        sequence.charAt(i);
                result.add(aba);
            }
        }

        return result;
    }

    /**
     * Checks if given sequence contains a BAB corresponding to one of the given ABA set.
     * @param sequence Sequence to check.
     * @param abaList Set containing all ABA to search BAB from.
     * @return true if sequence contains at least one BAB corresponding to any ABA in given set, otherwise false.
     */
    private boolean sequenceContainsAtLeastOneBab(String sequence, Set<String> abaList) {
        return abaList.stream()
                .map(aba -> String.valueOf(aba.charAt(1)) + aba.charAt(0) + aba.charAt(1))
                .anyMatch(sequence::contains);
    }
}
