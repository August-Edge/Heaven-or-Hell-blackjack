package no.uib.inf101.Blackjack.Deck;

/**
 * Represents a playing card.
 */
public class Card {
    private String value;
    private String symbol;

    /**
     * Constructs a card with the specified value and symbol.
     *
     * @param value  the value of the card
     * @param symbol the symbol of the card
     */
    public Card(String value, String symbol) {
        this.value = value;
        this.symbol = symbol;
    }

    /**
     * Returns the value of the card.
     *
     * @return the value of the card
     */
    public String getValueOfCard() {
        return value;
    }

    /**
     * Returns the symbol of the card.
     *
     * @return the symbol of the card
     */
    public String getSymbolOfCard() {
        return symbol;
    }

    /**
     * Returns the code of the card in the format "value-symbol".
     *
     * @return the code of the card
     */
    public String getCardCode() {
        return value + "-" + symbol;
    }

    /**
     * Returns the integer value of the card.
     * Face cards (J, Q, K, A) have specific values.
     * J, Q, and K have a value of 10, and A has a value of 11.
     * Other cards have their numeric value.
     *
     * @return the integer value of the card
     */
    public int getIntValueOfCard() {
        if ("JQKA".contains(value)) {
            if (value.equals("A")) {
                return 11;
            }
            return 10;
        }
        return Integer.parseInt(value);
    }

    /**
     * Returns the image path of the card.
     *
     * @return the image path of the card
     */
    public String getImagePath() {
        return "src\\main\\java\\no\\uib\\inf101\\Blackjack\\resources\\cards\\" + getCardCode() + ".png";
    }
}
 
