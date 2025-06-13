package no.uib.inf101.sample;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import no.uib.inf101.Blackjack.Deck.Card;

public class CardTest {



    @Test
    public void testGetIntValueOfCard() {
        Card card = new Card("A", null);
        assertEquals(11, card.getIntValueOfCard());

        card = new Card("K", null);
        assertEquals(10, card.getIntValueOfCard());

        card = new Card("5", null);
        assertEquals(5, card.getIntValueOfCard());
    }

    @Test
    public void testGetImagePath() {
        Card card = new Card("A", "C");
        assertEquals("src\\main\\java\\no\\uib\\inf101\\Blackjack\\resources\\cards\\A-C.png", card.getImagePath());

        card = new Card("K", "S");
        assertEquals("src\\main\\java\\no\\uib\\inf101\\Blackjack\\resources\\cards\\K-S.png", card.getImagePath());

        card = new Card("5", "D");
        assertEquals("src\\main\\java\\no\\uib\\inf101\\Blackjack\\resources\\cards\\5-D.png", card.getImagePath());
    }
}