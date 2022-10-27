import java.util.Random;
import java.util.ArrayList;

public class Deck {
    //creates a standard deck of 52 cards
	ArrayList<Card> deck;
	Deck() {
		deck = new ArrayList<Card>();
    }
    ArrayList<Card> createDeck() {
        deck = new ArrayList<Card>();
        int counter = 0;
		int assignVal = 2;
		while (counter < 13) { //space cards 2-Ace
			deck.add(new Card(assignVal, "s"));
			assignVal++;
			counter++;
	}
		assignVal = 2;
		while (counter < 26) { //heart cards 2-Ace
			deck.add(new Card(assignVal, "h"));
			assignVal++;
			counter++;
	}
		assignVal = 2;
		while (counter < 39) { //club cards 2-Ace
			deck.add(new Card(assignVal, "c"));
			assignVal++;
			counter++;
	}
		assignVal = 2;
		while (counter < 52) { //diamond cards 2-Ace
			deck.add(new Card(assignVal, "d"));
			assignVal++;
			counter++;
		}
        return deck;
    }

    public void shuffle() {
        //fischer-yates shuffle algorithm
        deck = createDeck();
        Random randomInt = new Random();
        for (int i = deck.size() - 1; i > 0; i--) {
            int swap = randomInt.nextInt(i+1);
            Card temp = deck.get(swap);
            deck.set(swap, deck.get(i));
            deck.set(i, temp);
        }
    }
    public ArrayList<Card> deal(int numCards) {
        ArrayList<Card> hand = new ArrayList<Card>();
        for (int i = 0; i < numCards; i++) {
            Card card = deck.get(0);
            hand.add(card);
            deck.remove(0);
        }
        return hand;
    }

    public void printDeck() {
        for (int i = 0; i < 52; i++) {
            System.out.print(deck.get(i).print() + " ");
        }
    }
    
    public static void main(String[] args) {
        Deck myDeck = new Deck();
        myDeck.createDeck();
        myDeck.shuffle();
        ArrayList<Card> myHand = myDeck.deal(2);
        for (Card card : myHand) {
            System.out.println(card.print());
        }
    }
}