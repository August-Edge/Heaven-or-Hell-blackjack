package no.uib.inf101.sample;



import org.junit.jupiter.api.Test;

import no.uib.inf101.Blackjack.Game.BetModel;
import no.uib.inf101.Blackjack.Game.BetModelInterface;

import static org.junit.jupiter.api.Assertions.*;

public class TestBetModel {

    @Test
    public void testIncreaseBet() {
        BetModelInterface betModel = new BetModel();
        betModel.increaseBet();
        assertEquals(5, betModel.getBet(), "After increasing the bet, the bet should be 5");
    }

    @Test
    public void testDealWithDevil() {
        BetModelInterface betModel = new BetModel();
        betModel.dealWithDevil();
        assertEquals(1500, betModel.getSaldoInt(), "After dealing with the devil, the bet should be 1500");
    }

    @Test
    public void testBargainWithDevil() {
        BetModelInterface betModel = new BetModel();
        betModel.bargainWithDevil();
        assertEquals(-1000, betModel.getSaldoInt(), "After bargaining with the devil, the bet should be -1000");
    }

    @Test
    public void testDecreaseBet() {
        BetModelInterface betModel = new BetModel();
        betModel.increaseBet();
        betModel.increaseBet();
        betModel.decreaseBet();
        assertEquals(5, betModel.getBet(), "After increasing the bet twice and decreasing it once, the bet should be 5");
    }


    @Test
    public void testAllInn() {
        BetModelInterface betModel = new BetModel();
        betModel.allInn();
        assertEquals(500, betModel.getBet(), "After going all in, the bet should be 500");
    }
}

