package no.uib.inf101.Blackjack.View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import no.uib.inf101.Blackjack.Deck.Card;
import no.uib.inf101.Blackjack.Game.GameState;
import no.uib.inf101.Blackjack.Game.SoulState;
import no.uib.inf101.Blackjack.Game.BlackjackModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class BlackjackView extends JPanel {

  SoulState soulState;
  GameState gameState;
  String font;
  JFrame frame;
  int cardWidth = 130;
  int cardHeight = 182;
  int boardWidth = 800;
  int boardHeight = 800;
  int dealerSum;
  int playerSum;
  BlackjackModel model = new BlackjackModel();
  Color boxColor;

  public BlackjackView() {

    this.setPreferredSize(new Dimension(boardWidth, boardHeight));
    model.startSoul();
    model.setDeck();
    model.pullCardsDealer();
    model.pullCardsPlayer();

  }

  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D) g;
    drawBackground(g2);
    if (model.getGameState() != gameState.GAME_OVER && model.getSoul() != soulState.BLESSED) {
      if (model.getSoul() == soulState.NORMAL) {
        makeGood();
      }
      if (model.getGameState() == gameState.MAIN_MENU) {
        drawMainMenu(g2);
      }
      if (model.getGameState() == gameState.PRE_GAME) {
        drawBetRound(g2);
        drawBetControls(g2);
      } else if (model.getGameState() != gameState.PRE_GAME && model.getGameState() != gameState.MAIN_MENU) {
        drawSaldoAndBet(g2);
        drawControls(g2);
        drawDealerCard(g);
        drawPlayerCard(g);
        if (model.getGameState() == gameState.END_GAME) {
          if (model.getSoul() == soulState.EVIL) {
            makeEvil();
            drawControls(g2);
            drawSaldoAndBet(g2);
            if (model.getBetModel().getSaldoInt() > 1005) {
              drawBargain(g2);
            } else if (model.getBetModel().getSaldoInt() == 0) {
              drawBackground(g2);
              drawGameOver(g2);
            }
          } else if (model.getBetModel().getSaldoInt() < 200) {
            drawDeal(g2);
          } else if (model.getBetModel().getSaldoInt() > 2000) {
            drawHeavenDeal(g2);
          }
          if (model.getGameState() != gameState.GAME_OVER) {
            drawEndGame(g2);
          }
        }
      }
    }
  }

  private void drawMainMenu(Graphics2D g2) {
    Rectangle2D box = new Rectangle2D.Double(270, 75, 270, 120);
    g2.setColor(new Color(150, 200, 250));
    g2.fill(box);
    g2.setColor(Color.BLACK);
    g2.setFont(new Font("Times new roman", Font.PLAIN, 30));
    Inf101Graphics.drawCenteredString(g2, "HEAVEN OR HELL", 405, 90);
    Inf101Graphics.drawCenteredString(g2, "BLACKJACK", 405, 130);
    Inf101Graphics.drawCenteredString(g2, "Press 'S' to start", 405, 170);
  }

  private void makeGood() {
    boxColor = new Color(150, 200, 250);
    font = "Times new roman";
  }

  private void makeEvil() {
    boxColor = new Color(250, 0, 0);
    font = "Chiller";
  }

  private void drawSaldoAndBet(Graphics2D g2) {
    Rectangle2D box = new Rectangle2D.Double(600, 50, 150, 85);
    g2.setColor(boxColor);
    g2.fill(box);
    g2.setColor(Color.BLACK);
    g2.setFont(new Font(font, Font.PLAIN, 30));
    Inf101Graphics.drawCenteredString(g2, "Saldo: " + model.getBetModel().getSaldo(), 675, 70);
    Inf101Graphics.drawCenteredString(g2, "Bet: " + model.getBetModel().getBet(), 675, 110);
  }

  private void drawControls(Graphics2D g2) {
    Rectangle2D box = new Rectangle2D.Double(505, 150, 280, 130);
    g2.setColor(boxColor);
    g2.fill(box);
    g2.setColor(Color.BLACK);
    g2.setFont(new Font(font, Font.PLAIN, 30));
    Inf101Graphics.drawCenteredString(g2, "Use 'Space' to hit", 645, 170);
    Inf101Graphics.drawCenteredString(g2, "Use 'Enter' to stand", 645, 210);
    Inf101Graphics.drawCenteredString(g2, "Use 'd' to double down", 645, 250);
  }

  private void drawBetControls(Graphics2D g2) {
    Rectangle2D box = new Rectangle2D.Double(225, 110, 390, 170);
    g2.setColor(boxColor);
    g2.fill(box);
    g2.setColor(Color.BLACK);
    g2.setFont(new Font(font, Font.PLAIN, 30));
    Inf101Graphics.drawCenteredString(g2, "Use 'a' to go all inn", 420, 130);
    Inf101Graphics.drawCenteredString(g2, "Use UP key to increase bet", 420, 170);
    Inf101Graphics.drawCenteredString(g2, "Use DOWN key to decrease bet", 420, 210);
    Inf101Graphics.drawCenteredString(g2, "Use 'f' key to set bet", 420, 250);
  }

  public void drawBackground(Graphics2D g2) {
    if (model.getSoul() == soulState.BLESSED) {
      BufferedImage photo = Inf101Graphics.loadImageFromResources("gamewin.jpg");
      Inf101Graphics.drawImage(g2, photo, 0, 0, 0.8);
    } else if (model.getSoul() == soulState.NORMAL && model.getGameState() != gameState.MAIN_MENU) {
      BufferedImage photo = Inf101Graphics.loadImageFromResources("currentBlackjack.jpg");
      Inf101Graphics.drawImage(g2, photo, 0, 0, 0.9);
    } else if (model.getSoul() == soulState.EVIL && model.getGameState() != gameState.GAME_OVER) {
      BufferedImage photo = Inf101Graphics.loadImageFromResources("Satan3.jpg");
      Inf101Graphics.drawImage(g2, photo, 0, 0, 0.7);
    } else if (model.getGameState() == gameState.MAIN_MENU) {
      BufferedImage photo = Inf101Graphics.loadImageFromResources("mainmenu.jpg");
      Inf101Graphics.drawImage(g2, photo, 0, 0, 0.8);
    } else {
      BufferedImage photo = Inf101Graphics.loadImageFromResources("gameover.jpg");
      Inf101Graphics.drawImage(g2, photo, 0, 0, 0.8);
    }
  }

  public void drawDealerCard(Graphics g) {

    if (model.getGameState() == gameState.DEALER_ROUND || model.getGameState() == gameState.END_GAME) {
      for (int handIndex = 0; handIndex < model.getDealerHand().size(); handIndex++) {

        ImageIcon images = getImageCard(model.getDealerHand().get(handIndex));
        Image image = images.getImage();
        g.drawImage(image, 40 + (20) * handIndex, 40, cardWidth, cardHeight, null);
      }
    } else {
      ImageIcon images = new ImageIcon("src\\main\\java\\no\\uib\\inf101\\Blackjack\\resources\\cards\\BACK.png");
      Image image = images.getImage();
      g.drawImage(image, 40, 40, cardWidth, cardHeight, null);

      for (int handIndex = 1; handIndex < model.getDealerHand().size(); handIndex++) {

        images = getImageCard(model.getDealerHand().get(handIndex));
        image = images.getImage();
        g.drawImage(image, 30 + cardWidth + (20) * handIndex, 40, cardWidth, cardHeight, null);
      }
    }
  }

  public void drawPlayerCard(Graphics g) {

    for (int handIndex = 0; handIndex < model.getPlayerHand().size(); handIndex++) {

      ImageIcon images = getImageCard(model.getPlayerHand().get(handIndex));
      Image image = images.getImage();
      g.drawImage(image, 40 + (20) * handIndex, 590, cardWidth, cardHeight, null);
    }
  }

  ImageIcon getImageCard(Card card) {
    String cardImagePath;
    cardImagePath = card.getImagePath();
    ImageIcon imageIcon = new ImageIcon(cardImagePath);
    return imageIcon;
  }

  public BlackjackModel getModel() {
    return model;
  }

  void drawEndGame(Graphics2D g2) {
    Rectangle2D box = new Rectangle2D.Double(205, 290, 390, 210);
    g2.setColor(boxColor);
    g2.fill(box);
    g2.setColor(Color.BLACK);
    g2.setFont(new Font(font, Font.PLAIN, 40));
    Inf101Graphics.drawCenteredString(g2, model.getWinner(), 400, 320);
    Inf101Graphics.drawCenteredString(g2, "Saldo is " + model.getBetModel().getSaldo(), 400, 370);
    Inf101Graphics.drawCenteredString(g2, "Press 'r' to gamble more", 400, 420);
    Inf101Graphics.drawCenteredString(g2, ";)", 400, 470);
  }

  void drawDeal(Graphics2D g2) {
    Rectangle2D boxx = new Rectangle2D.Double(440, 540, 310, 220);
    g2.setColor(new Color(250, 0, 0));
    g2.fill(boxx);
    g2.setColor(Color.BLACK);
    g2.setFont(new Font("Chiller", Font.PLAIN, 40));
    Inf101Graphics.drawCenteredString(g2, "Sell your soul for", 600, 590);
    Inf101Graphics.drawCenteredString(g2, "another chance at", 600, 640);
    Inf101Graphics.drawCenteredString(g2, "luck by pressing 's'", 600, 690);
  }

  void drawBetRound(Graphics2D g2) {
    Rectangle2D box = new Rectangle2D.Double(275, 300, 250, 250);
    g2.setColor(boxColor);
    g2.fill(box);
    g2.setColor(Color.BLACK);
    g2.setFont(new Font(font, Font.PLAIN, 40));
    Inf101Graphics.drawCenteredString(g2, "Decide bet:", 400, 350);
    Inf101Graphics.drawCenteredString(g2, "Saldo is " + model.getBetModel().getSaldo(), 400, 450);
    Inf101Graphics.drawCenteredString(g2, "Bet is " + model.getBetModel().getStringBet(), 400, 500);
  }

  void drawBargain(Graphics2D g2) {
    Rectangle2D boxx = new Rectangle2D.Double(440, 540, 310, 220);
    g2.setColor(new Color(250, 0, 0));
    g2.fill(boxx);
    g2.setColor(Color.BLACK);
    g2.setFont(new Font("Chiller", Font.PLAIN, 40));
    Inf101Graphics.drawCenteredString(g2, "Buy soul back for", 600, 590);
    Inf101Graphics.drawCenteredString(g2, "1500 by pressing 'b'", 600, 640);
    Inf101Graphics.drawCenteredString(g2, "to get another life", 600, 690);
  }

  void drawHeavenDeal(Graphics2D g2) {
    Rectangle2D boxx = new Rectangle2D.Double(410, 520, 380, 240);
    g2.setColor(new Color(188, 143, 143));
    g2.fill(boxx);
    g2.setColor(new Color(245, 222, 179));
    g2.setFont(new Font("Times new roman", Font.PLAIN, 35));
    Inf101Graphics.drawCenteredString(g2, "Would you like a spot", 600, 560);
    Inf101Graphics.drawCenteredString(g2, "in heaven perchance?", 600, 610);
    Inf101Graphics.drawCenteredString(g2, "press 'y' to accept", 600, 660);
    Inf101Graphics.drawCenteredString(g2, "price 3000 non refundable", 600, 710);
  }

  void drawGameOver(Graphics2D g2) {
    model.gameOver();
    Rectangle2D box = new Rectangle2D.Double(0, 0, 800, 800);
    g2.setColor(new Color(188, 143, 143));
    g2.fill(box);
    g2.setColor(new Color(245, 222, 179));
    g2.setFont(new Font("Times new roman", Font.PLAIN, 30));
    Inf101Graphics.drawCenteredString(g2, "YOU FAILED", 405, 130);
    Inf101Graphics.drawCenteredString(g2, "Dont gamble kids..", 405, 170);
    Inf101Graphics.drawCenteredString(g2, "press anything to see your future", 405, 210);
    Inf101Graphics.drawCenteredString(g2, "if you continue on this path...", 405, 250);
  }
}
