package no.uib.inf101.Blackjack.Game;

public class BetModel implements BetModelInterface {
    // Increase saldo if necessary to get an easier chance at winning the game
    int saldo = 5000;
    int bet = 0;

    public void increaseBet(){
        if (saldo >= bet + 5){
        bet += 5;}
    }

    public void dealWithDevil(){
        saldo += 1000;
    }

    public void bargainWithDevil(){
        saldo -= 1500;
    }

    public void decreaseBet(){
        if (checkBet()){
        bet -= 5;}
    }

    public void setBet(){
        saldo -= bet;
    }

    public int getBet(){
        return bet;
    }

    public void allInn(){
        bet = saldo;
    }

    public String getStringBet(){return String.valueOf(bet);}

    private boolean checkBet(){
        return (saldo >= bet) && (bet > 0);
    }

    public void doubleDown(){
        saldo -= bet;
        bet = bet * 2;
    }

    public String getSaldo(){return String.valueOf(saldo);}

    public int getSaldoInt(){return saldo;}

    public void resetBet(){bet = 0;}

    public void winnings(int prize){
        saldo += prize;
    }
}
