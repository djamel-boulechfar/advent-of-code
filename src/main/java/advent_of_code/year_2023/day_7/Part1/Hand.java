package advent_of_code.year_2023.day_7.Part1;

import advent_of_code.year_2023.day_7.HandType;
import lombok.Data;

import java.util.*;

@Data
public class Hand {
    private List<Card> cards;
    private int bid;
    private HandType type;

    public Hand(String input) {
        String[] splitInput = input.split(" ");

        if (splitInput.length == 2) {
            // Récupération des caractères correspondant aux cartes
            this.cards = new ArrayList<>();
            for (Character c: splitInput[0].toCharArray()) {
                this.cards.add(Card.fromValue(c));
            }

            // Récupération du pari
            this.bid = Integer.parseInt(splitInput[1]);

            this.type = this.compareHandType();
        } else {
            throw new IllegalArgumentException("Impossible de créer une main à partir de l'input suivant : " + input);
        }
    }

    public HandType compareHandType() {
        Map<Card, Integer> cardOccurrences = new HashMap<>();

        for (Card card: this.cards) {
            cardOccurrences.merge(card, 1, Integer::sum);
        }

        return switch (cardOccurrences.size()) {
            case 1 -> HandType.FIVE_OF_A_KIND;
            case 2 -> this.determineFullHouseOrFourOfAKind(cardOccurrences);
            case 3 -> this.determineTwoPairOrThreeOfAKind(cardOccurrences);
            case 4 -> HandType.ONE_PAIR;
            case 5 -> HandType.HIGH_CARD;
            default -> throw new IllegalArgumentException("Nombre de cartes trop élevées.");
        };
    }

    public HandType determineFullHouseOrFourOfAKind(Map<Card, Integer> cardOccurrences) {
        int max = cardOccurrences.values().stream().max(Integer::compareTo).orElseThrow(() -> new NoSuchElementException("Pas de maximum, ça c'est du message d'exception de qualité."));
        if (max == 4) {
            return HandType.FOUR_OF_A_KIND;
        } else {
            return HandType.FULL_HOUSE;
        }
    }

    public HandType determineTwoPairOrThreeOfAKind(Map<Card, Integer> cardOccurrences) {
        int max = cardOccurrences.values().stream().max(Integer::compareTo).orElseThrow(() -> new NoSuchElementException("Pas de maximum, ça c'est du message d'exception de qualité."));
        if (max == 3) {
            return HandType.THREE_OF_A_KIND;
        } else {
            return HandType.TWO_PAIR;
        }
    }

    public int compareTo(Hand other) {
        if (this.cards.size() != other.cards.size()) {
            throw new IllegalArgumentException("Les deux mains n'ont pas la même taille, impossible de les comparer.");
        }

        for (int i = 0; i < this.cards.size(); i++) {
            Card card = this.cards.get(i);
            Card otherCard = other.cards.get(i);

            int comparisonResult = card.compareTo(otherCard);

            if (comparisonResult != 0) {
                return comparisonResult;
            }
        }

        return 0;
    }
}
