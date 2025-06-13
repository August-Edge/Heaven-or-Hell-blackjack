package no.uib.inf101.Blackjack.Game;

/**
 * The BetModelInterface represents the interface for managing bets in a blackjack game.
 */
public interface BetModelInterface {

    /**
     * Increases the current bet by 5.
     */
    void increaseBet();

    /**
     * Increases saldo by 1000.
     */
    void dealWithDevil();

    /**
     * Decreases saldo by 1500.
     */
    void bargainWithDevil();

    /**
     * Decreases the current bet by 5.
     */
    void decreaseBet();

    /**
     * Removes amount bet from saldo.
     */
    void setBet();

    /**
     * Returns the current bet.
     *
     * @return the current bet
     */
    int getBet();

    /**
     * Places all the remaining saldo as a bet.
     */
    void allInn();

    /**
     * Returns the current bet as a string.
     *
     * @return the current bet as a string
     */
    String getStringBet();

    /**
     * Doubles the current bet and removes money from saldo accordingly.
     */
    void doubleDown();

    /**
     * Returns the current balance as a string.
     *
     * @return the current balance as a string
     */
    String getSaldo();

    /**
     * Returns the current balance as an integer.
     *
     * @return the current balance as an integer
     */
    int getSaldoInt();

    /**
     * Resets the current bet to zero.
     */
    void resetBet();

    /**
     * Updates the balance with the given prize amount.
     *
     * @param prize the prize amount to be added to the balance
     */
    void winnings(int prize);

}
