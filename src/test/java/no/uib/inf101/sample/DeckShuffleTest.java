package no.uib.inf101.sample;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import no.uib.inf101.Blackjack.Deck.Card;
import no.uib.inf101.Blackjack.Deck.CardFactory;
import no.uib.inf101.Blackjack.Deck.RandomCardFactory;

public class DeckShuffleTest {

    @Test
    public void testShuffleCards() {
        CardFactory cardFactory = new RandomCardFactory();
    
        // Create a deck of cards
        ArrayList<Card> deck = new ArrayList<Card>();
        deck.add(new Card("A","H"));
        deck.add(new Card("5","D"));
        deck.add(new Card("3","C"));
        deck.add(new Card("10","S"));
    
        // Shuffle the deck
        cardFactory.shuffleCards();
    
        // Check that the deck is shuffled
        assertNotEquals(deck, Arrays.asList(
                new Card("A","H"),
                new Card("5","D"),
                new Card("3","C"),
                new Card("10","S")));
    }
    @Test
public void testRemoveCard() {
    RandomCardFactory factory = new RandomCardFactory();
    factory.createDeck();
    assertTrue(factory.getDeck().size() == 52);
    factory.removeCard();
    assertTrue(factory.getDeck().size() == 51);
    factory.removeCard();
    assertTrue(factory.getDeck().size() == 50);
}
    
}
