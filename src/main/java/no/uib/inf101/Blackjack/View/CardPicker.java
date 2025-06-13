package no.uib.inf101.Blackjack.View;

import java.util.ArrayList;

import no.uib.inf101.Blackjack.Deck.Card;
import no.uib.inf101.Blackjack.Deck.CardFactory;

/**
 * The CardPicker class represents a card picker that retrieves cards from a deck.
 */
public class CardPicker {
    String cardImagePath;
    CardFactory cardFactory;

    /**
     * Constructs a CardPicker object with the specified CardFactory.
     *
     * @param cardFactory the CardFactory used to create and manage the deck of cards
     */
    public CardPicker(CardFactory cardFactory){
        this.cardFactory = cardFactory;
    }
    
    /**
     * Retrieves the first card from the deck and removes it from the deck.
     *
     * @return the first card from the deck
     */
    public Card getFirstCard() {
        ArrayList<Card> deck = cardFactory.getDeck();
        Card card = deck.get(0);
        cardFactory.removeCard();
        return card;
    }
    
}
