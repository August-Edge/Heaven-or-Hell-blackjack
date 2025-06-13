package no.uib.inf101.Blackjack;

import javax.swing.JFrame;
import no.uib.inf101.Blackjack.Controller.BlackjsControl;
import no.uib.inf101.Blackjack.View.BlackjackView;

/**
 * Hello world!
 */
public class Main {
  public static final String WINDOW_TITLE = "BlackJack";

  public static void main(String[] args) {
    JFrame frame = new JFrame(WINDOW_TITLE);
    BlackjackView blackjackView = new BlackjackView();
    
    frame.setResizable(false);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setContentPane(blackjackView);
    frame.pack();
    frame.setVisible(true);
    
    BlackjsControl controller = new BlackjsControl(blackjackView);
  }
}
