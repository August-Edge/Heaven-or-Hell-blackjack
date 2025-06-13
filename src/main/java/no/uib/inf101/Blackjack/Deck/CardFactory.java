package no.uib.inf101.Blackjack.Deck;

import java.util.ArrayList;

/**
 * The CardFactory interface represents a factory for creating and managing a deck of cards.
 */
public interface CardFactory {

    ArrayList<Card> getDeck();

    /**
     * Creates a new deck of cards.
     *
     * @return an ArrayList of Card objects representing the deck
     */
    ArrayList<Card> createDeck();

    /**
     * Shuffles the cards in the deck.
     */
    ArrayList<Card> shuffleCards();


    /**
     * Prints the deck of cards.
     */
    void printDeck();

    void removeCard();

    
}
