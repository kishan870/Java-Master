package dev.lpa;

import java.util.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Card[] cardArray = new Card[13];
        Card aceOfHearts = Card.getFaceCard(Card.Suit.HEART, "A");

        Arrays.fill(cardArray, aceOfHearts);
        Card.printDeck(Arrays.asList(cardArray), "Ace of Hearts", 1);

//        Card twoOfClubs = Card.getNumericCard(Card.Suit.CLUB, 2);
//        Arrays.fill(cardArray, twoOfClubs);
//        Card.printDeck(Arrays.asList(cardArray), "Two of Clubs", 1);

        //Using Collections fill method
        System.out.println("-".repeat(10));
        System.out.println("Exploring collection methods");
        List<Card> cards = new ArrayList<>(52);
        Collections.fill(cards, aceOfHearts);
        System.out.println(cards);
        System.out.println("Cards size: " + cards.size());

        List<Card> acesOfHearts = Collections.nCopies(13, aceOfHearts);
        Card.printDeck(acesOfHearts, "Ace of Hearts", 1);

        Card kingOfClubs = Card.getFaceCard(Card.Suit.CLUB, "K");
        List<Card> kingsOfClubs = Collections.nCopies(13, kingOfClubs);
        Card.printDeck(kingsOfClubs, "King of Clubs", 1);
        System.out.println("-".repeat(10));

        Collections.addAll(cards, cardArray);
        Collections.addAll(cards, cardArray);
        Card.printDeck(cards, "Collection with Aces added", 2);

        Collections.copy(cards, kingsOfClubs);
        Card.printDeck(cards, "Cards collection with kings of clubs copied",
                2);

        List<Card> deck = Card.getStandardDeck();
        Card.printDeck(deck);

        System.out.println("Shuffling the deck");
        Collections.shuffle(deck);
        Card.printDeck(deck);

        Collections.reverse(deck);
        Card.printDeck(deck, "Reversing the deck", 4);

        var sortingAlgorithm = Comparator.comparing(Card::rank)
                .thenComparing(Card::suit);
        Collections.sort(deck, sortingAlgorithm);
        Card.printDeck(deck, "Sorted by rank, suit", 4);

        Collections.reverse(deck);
        Card.printDeck(deck, "Sorted and reversed by rank, suit", 4);

        List<Card> kingsDeck = deck.subList(4,8);
        Card.printDeck(kingsDeck, "Deck of kings", 1);

        List<Card> tensDeck = deck.subList(16, 20);
        Card.printDeck(tensDeck, "Deck of tens", 1);

        Collections.shuffle(deck);
        int subListIndex = Collections.indexOfSubList(deck, tensDeck);
        System.out.println("sublist index for tens: " + subListIndex);
        System.out.println("Contains: " + deck.containsAll(tensDeck));

        boolean disJoint = Collections.disjoint(deck, tensDeck);
        System.out.println("Disjoint: " + disJoint);

        boolean disJoint2 = Collections.disjoint(kingsDeck, tensDeck);
        System.out.println("Disjoint: " + disJoint2);

        deck.sort(sortingAlgorithm);
        Card tenOfHearts = Card.getNumericCard(Card.Suit.HEART, 10);
        int foundIndex = Collections.binarySearch(deck, tenOfHearts, sortingAlgorithm);
        System.out.println("Found Index on binary search: " + foundIndex);

    }
}