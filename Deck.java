import java.util.Random;
import java.util.ArrayList;

public class Deck {
    //creates a standard deck of 52 cards
	ArrayList<Card> deckOfCards;
	Deck() {
		deckOfCards = new ArrayList<Card>();
    }
    ArrayList<Card> createDeck() {
        deckOfCards = new ArrayList<Card>();
        int counter = 0;
		int assignVal = 2;
		while (counter < 13) { //space cards 2-Ace
			deckOfCards.add(new Card(assignVal, "s"));
			assignVal++;
			counter++;
	}
		assignVal = 2;
		while (counter < 26) { //heart cards 2-Ace
			deckOfCards.add(new Card(assignVal, "h"));
			assignVal++;
			counter++;
	}
		assignVal = 2;
		while (counter < 39) { //club cards 2-Ace
			deckOfCards.add(new Card(assignVal, "c"));
			assignVal++;
			counter++;
	}
		assignVal = 2;
		while (counter < 52) { //diamond cards 2-Ace
			deckOfCards.add(new Card(assignVal, "d"));
			assignVal++;
			counter++;
		}
        return deckOfCards;
    }

    public void shuffle() {
        //fischer-yates shuffle algorithm
        deckOfCards = createDeck();
        Random randomInt = new Random();
        for (int i = deckOfCards.size() - 1; i > 0; i--) {
            int swap = randomInt.nextInt(i+1);
            Card temp = deckOfCards.get(swap);
            deckOfCards.set(swap, deckOfCards.get(i));
            deckOfCards.set(i, temp);
        }
    }
    /*(public Card[] deal(int numCards) {

    }*/

    public void printDeck() {
        for (int i = 0; i < 52; i++) {
            System.out.print(deckOfCards.get(i).print() + " ");
        }
    }
    
    public static void main(String[] args) {
        Deck myDeck = new Deck();
        myDeck.createDeck();
        myDeck.printDeck();
        System.out.println();
        myDeck.shuffle();
        myDeck.printDeck();
        System.out.println();
        myDeck.shuffle();
        myDeck.printDeck();
    }
}