package no.uib.inf101.Blackjack.Game;

import java.util.ArrayList;

import no.uib.inf101.Blackjack.Deck.Card;
import no.uib.inf101.Blackjack.Deck.CardFactory;
import no.uib.inf101.Blackjack.Deck.RandomCardFactory;
import no.uib.inf101.Blackjack.View.CardPicker;

/**
 * The `BlackjackModel` class represents the model component of the Blackjack game.
 * It manages the game state, player and dealer hands, card picking, and determines the winner.
 * The class also keeps track of the player's bet and the state of the player's soul.
 */
public class BlackjackModel {
    CardFactory cardFactory = new RandomCardFactory();
    CardPicker cardCreator = new CardPicker(cardFactory);
    ArrayList<Card> dealerHand;
    ArrayList<Card> playerHand;
    int dealerSum;
    int playerSum;
    GameState gameState;
    String winner;
    int playerAceCount;
    int dealerAceCount;
    BetModelInterface betModel = new BetModel();
    SoulState soulState;

    public void gameBegin(){
        gameState = gameState.PRE_GAME;
    }
    /**
     * Sets up a new deck for the game.
     * This method initializes the game state, dealer and player hands, sums, ace counts,
     * creates and shuffles a new deck of cards, prints the deck, resets the winner,
     * and resets the bet.
     */
    public void setDeck(){
    gameState = gameState.MAIN_MENU;
    dealerHand = new ArrayList<Card>();
    playerHand = new ArrayList<Card>();
    dealerSum = 0;
    playerSum = 0;
    playerAceCount = 0;
    dealerAceCount = 0;
    cardFactory.createDeck();
    cardFactory.shuffleCards();
    winner = "none";
    betModel.resetBet();

    }
    public void gameOver(){
        gameState = gameState.GAME_OVER;
    }

    public void startSoul(){
        soulState = soulState.NORMAL;
    }
    public void sellSoul(){
        soulState = soulState.EVIL;
    }
    public SoulState getSoul(){
        return soulState;
    }
    /**
     * Starts the dealer's round in the blackjack game.
     * This method is responsible for dealing cards to the dealer until their hand
     * reaches a sum of 17 or higher. It also handles the case where the dealer's
     * hand exceeds 21 and adjusts the value of any Ace cards accordingly.
     * If the dealer's hand exceeds 21 and there are no Ace cards to adjust, the
     * dealer is considered busted.
     * Finally, it sets the game state to END_GAME.
     */
    public void startDealer(){
        gameState = gameState.DEALER_ROUND;
        while (dealerSum < 17) {
        Card currentCard = cardCreator.getFirstCard();
        if (currentCard.getValueOfCard() == "A"){
            dealerAceCount += 1;}
        dealerHand.add(currentCard);
        dealerSum += currentCard.getIntValueOfCard();
        if (dealerSum > 21 && dealerAceCount > 0){
            reduceDealerAce();
        }
        }
        if (dealerSum > 21 && dealerAceCount == 0) {
            dealerBusted();
        }
        gameState = gameState.END_GAME;
    }

    /**
     * Adds a card to the player's hand and updates the player's sum.
     * If the player's sum exceeds 21 and there are aces in the hand,
     * reduces the value of the aces to prevent busting.
     * If the player's sum exceeds 21 and there are no aces in the hand,
     * triggers the playerBusted method.
     */
    public void pullCardHit(){
        Card currentCard = cardCreator.getFirstCard();
        if (currentCard.getValueOfCard() == "A"){
            playerAceCount += 1;
        }
        playerHand.add(currentCard);
        playerSum += currentCard.getIntValueOfCard();
        if (playerSum > 21 && playerAceCount > 0){
            reducePlayerAce();
        }
        else if (playerSum > 21 && playerAceCount == 0) {
            playerBusted();
        }
    }

    /**
     * Pulls two cards for the dealer and updates the dealer's hand and sum.
     * If the sum exceeds 21 and the dealer has an Ace, the Ace value is reduced.
     */
    public void pullCardsDealer(){
        Card currentCard = cardCreator.getFirstCard();
        if (currentCard.getValueOfCard() == "A"){
            dealerAceCount += 1;
        }
        dealerHand.add(currentCard);
        dealerSum += currentCard.getIntValueOfCard();
      
        currentCard = cardCreator.getFirstCard();
        if (currentCard.getValueOfCard() == "A"){
            dealerAceCount += 1;
        }
        dealerHand.add(currentCard);
        dealerSum += currentCard.getIntValueOfCard();
        if (dealerSum > 21 && dealerAceCount > 0){
            reduceDealerAce();
        }
      }
    /**
     * Pulls two cards for the player and updates the player's hand and sum.
     * If an Ace is pulled, it increases the playerAceCount.
     * If the player's sum exceeds 21 and there are Aces in the hand, it reduces the playerAceCount.
     */
      public void pullCardsPlayer(){
        Card currentCard = cardCreator.getFirstCard();
        if (currentCard.getValueOfCard() == "A"){
            playerAceCount += 1;
        }
        playerHand.add(currentCard);
        playerSum += currentCard.getIntValueOfCard();
      
        currentCard = cardCreator.getFirstCard();
        if (currentCard.getValueOfCard() == "A"){
            playerAceCount += 1;
        }
        playerHand.add(currentCard);
        playerSum += currentCard.getIntValueOfCard();
        if (playerSum > 21 && playerAceCount > 0){
            reducePlayerAce();
        }
      }

    public ArrayList<Card> getDealerHand(){return dealerHand;}

    public ArrayList<Card> getPlayerHand(){return playerHand;}

    public int getPlayerSum(){return playerSum;}

    public int getDealerSum(){return dealerSum;}

    public GameState getGameState(){return gameState;}

    public BetModelInterface getBetModel(){return betModel;}

    private void playerBusted(){
        winner = "The Dealer has won";
        gameState = gameState.END_GAME;
    }

    private void dealerBusted(){
        winner = "You win!";
        betModel.winnings(betModel.getBet()*2);
        gameState = gameState.END_GAME;
    }

    /**
     * Determines if the player has won the game.
     * 
     * @return true if the player's sum is greater than the dealer's sum, false otherwise.
     */
    public boolean didPlayerWin(){
        return playerSum > dealerSum;
    }

    /**
     * Determines the winner of the blackjack game based on the player's and dealer's hand sums.
     * Updates the winner variable and adjusts the player's winnings accordingly.
     */
    public void findWinner(){
    if (winner == "none"){
        if (dealerSum == playerSum){
            winner = "Draw";
            betModel.winnings(betModel.getBet());
        }
        else if (didPlayerWin()){
            winner = "You win!";
            betModel.winnings(betModel.getBet()*2);
        }
        else {
            winner = "The Dealer has won";
        }}
        }

    public String getWinner(){
        return winner;
    }

    /**
     * Reduces the value of an Ace card in the player's hand by 10.
     * If the player has no Ace cards, this method does nothing.
     */
    public void reducePlayerAce(){
        if (playerAceCount > 0){
            playerSum -= 10;
            playerAceCount -= 1;
        }
    }

    /**
     * Reduces the dealer's ace count and updates the dealer's sum by subtracting 10.
     * If the dealer has no aces, the method does nothing.
     */
    public void reduceDealerAce(){
        if (dealerAceCount > 0){
            dealerSum -= 10;
            dealerAceCount -= 1;
        }
    }
    /**
     * Starts a new round for the player in the blackjack game.
     * Sets the game state to PLAYER_ROUND.
     */
    public void startPlayerRound(){
        gameState = gameState.PLAYER_ROUND;
    }

    /**
     * Sets the winner to "You win!", calculates the winnings based on the current bet, and updates the game state to END_GAME.
     */
    public void instantWin(){
        winner = "You win!";
        betModel.winnings(betModel.getBet()*3);
        gameState = gameState.END_GAME;
    }

    public void getBlessed(){
        soulState = soulState.BLESSED;
    }
}
