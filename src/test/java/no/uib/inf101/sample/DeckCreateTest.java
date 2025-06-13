package no.uib.inf101.sample;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import no.uib.inf101.Blackjack.Deck.Card;
import no.uib.inf101.Blackjack.Deck.CardFactory;
import no.uib.inf101.Blackjack.Deck.RandomCardFactory;

public class DeckCreateTest {
    @Test
    public void testCreateDeck() {
        CardFactory factory = new RandomCardFactory();
        ArrayList<Card> deck = factory.createDeck();
    
        // Assert that the deck size is 52
        assertEquals(52, deck.size());
    
        // Assert that each card has a valid symbol and value
        String[] symbols = new String[] { "C", "S", "D", "H" };
        String[] values = new String[] { "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A" };
    
        for (Card card : deck) {
            assertTrue(Arrays.asList(symbols).contains(card.getSymbolOfCard()));
            assertTrue(Arrays.asList(values).contains(card.getValueOfCard()));
        }
    }
}


