package no.uib.inf101.Blackjack.Deck;
import java.util.ArrayList;
import java.util.Collections;
public class RandomCardFactory implements CardFactory {

    ArrayList<Card> deck = new ArrayList<>();
    public ArrayList<Card> createDeck() {
        deck = new ArrayList<>();
        String[] symboles = new String[]{"C","S","D","H"};
        String[] values = new String[]{"2","3","4","5","6","7","8","9","10","J","Q","K","A"};
        for (int symbolIndex = 0; symbolIndex < symboles.length; symbolIndex++){
            for (int valueIndex = 0; valueIndex < values.length; valueIndex++) {
                Card card = new Card(values[valueIndex], symboles[symbolIndex]);
                deck.add(card);
            }
            
        }
       return deck; 
    }
    

    public ArrayList<Card> shuffleCards() {
        Collections.shuffle(deck);
        return deck;
    }

    public void printDeck(){
        for (int deckIndex = 0; deckIndex < deck.size(); deckIndex++) {
            Card currentCard = deck.get(deckIndex);
            System.out.println(currentCard.getCardCode());
        }
    }

    public ArrayList<Card> getDeck() {
        return deck;
    }
    
    public void removeCard() {
        deck.remove(0);
    }



    

}
