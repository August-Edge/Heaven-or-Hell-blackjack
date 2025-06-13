package no.uib.inf101.Blackjack.Controller;

import java.awt.event.KeyEvent;

import no.uib.inf101.Blackjack.Game.BetModelInterface;
import no.uib.inf101.Blackjack.Game.GameState;
import no.uib.inf101.Blackjack.Game.SoulState;
import no.uib.inf101.Blackjack.Game.BlackjackModel;
import no.uib.inf101.Blackjack.Sounds.testSound;
import no.uib.inf101.Blackjack.View.BlackjackView;

public class BlackjsControl implements java.awt.event.KeyListener {
    BlackjackView blackjackView;
    Boolean standControl = true;
    BlackjackModel model;
    BetModelInterface betModel;
    testSound casinoSound;
    testSound satanSound;
    testSound soldsoulSound;
    testSound soulbackSound;
    testSound gameover;
    testSound gameWin;
    testSound goodjob;
    SoulState soulState;

    public BlackjsControl(BlackjackView blackjackView) {
        this.blackjackView = blackjackView;
        blackjackView.setFocusable(true);
        blackjackView.addKeyListener(this);
        model = blackjackView.getModel();
        betModel = model.getBetModel();
        casinoSound = new testSound("Blackjackmusic.wav");
        satanSound = new testSound("satanmusic.wav");
        soldsoulSound = new testSound("soldsoulsound.wav");
        soulbackSound = new testSound("soulbacksound.wav");
        gameover = new testSound("sadmusictest.wav");
        gameWin = new testSound("heavensound.wav");
        goodjob = new testSound("goodjob.wav");

        casinoSound.loop();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (model.getGameState() != GameState.GAME_OVER) {
            if (model.getSoul() != soulState.BLESSED) {
                if (model.getGameState() == GameState.MAIN_MENU && e.getKeyCode() == KeyEvent.VK_S) {
                    model.gameBegin();
                }
                if (model.getGameState() == GameState.PRE_GAME) {
                    standControl = true;
                    if (e.getKeyCode() == KeyEvent.VK_UP) {
                        betModel.increaseBet();
                    }
                    if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                        betModel.decreaseBet();
                    }
                    if (e.getKeyCode() == KeyEvent.VK_A) {
                        betModel.allInn();
                    }
                    if (e.getKeyCode() == KeyEvent.VK_F) {
                        betModel.setBet();
                        model.startPlayerRound();
                        if (model.getPlayerSum() == 21) {
                            model.instantWin();
                        }
                    }
                }

                if (model.getGameState() == GameState.PLAYER_ROUND) {
                    if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                        if (standControl)
                            model.pullCardHit();
                    }
                    if (e.getKeyCode() == KeyEvent.VK_D && betModel.getBet() <= betModel.getSaldoInt()) {
                        standControl = false;
                        betModel.doubleDown();
                        model.pullCardHit();
                        if (model.getPlayerSum() <= 21) {
                            model.startDealer();
                            model.findWinner();
                        }
                    } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                        standControl = false;
                        model.startDealer();
                        model.findWinner();

                    }
                }
                if (model.getGameState() == GameState.END_GAME) {
                    if (e.getKeyCode() == KeyEvent.VK_S && betModel.getSaldoInt() < 200) {
                        betModel.dealWithDevil();
                        model.sellSoul();
                        casinoSound.stop();
                        satanSound.loop();
                        soldsoulSound.play();
                    }
                    if (model.getSoul() == soulState.EVIL && betModel.getSaldoInt() > 1500) {
                        if (e.getKeyCode() == KeyEvent.VK_B) {
                            betModel.bargainWithDevil();
                            model.startSoul();
                            satanSound.stop();
                            casinoSound.loop();
                            soulbackSound.play();
                        }
                    }
                    if (model.getSoul() == soulState.EVIL && betModel.getSaldoInt() == 0) {
                        satanSound.stop();
                        gameover.loop();
                    }
                    if (e.getKeyCode() == KeyEvent.VK_Y && betModel.getSaldoInt() > 3000) {
                        model.getBlessed();
                        casinoSound.stop();
                        gameWin.loop();
                        goodjob.play();
                    }
                    if (e.getKeyCode() == KeyEvent.VK_R) {
                        model.setDeck();
                        model.gameBegin();
                        model.pullCardsDealer();
                        model.pullCardsPlayer();
                    }
                }
            }
        }
        blackjackView.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
