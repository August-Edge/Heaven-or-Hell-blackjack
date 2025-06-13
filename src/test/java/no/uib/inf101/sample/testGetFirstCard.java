package no.uib.inf101.sample;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import no.uib.inf101.Blackjack.Deck.Card;
import no.uib.inf101.Blackjack.Deck.CardFactory;
import no.uib.inf101.Blackjack.Deck.RandomCardFactory;
import no.uib.inf101.Blackjack.View.CardPicker;

public class testGetFirstCard {
    @Test
    void testFirstCard() { // Add variable name 'testGetFirstCard'
        CardFactory cardFactory = new RandomCardFactory();
        cardFactory.createDeck();
        CardPicker cardCreator = new CardPicker(cardFactory);
        Card firstCard = cardCreator.getFirstCard();

        // Assert that the first card is not null
        assertNotNull(firstCard);

        // Assert that the card is removed from the deck
        assertFalse(cardFactory.getDeck().contains(firstCard));
    }
}