import java.util.Random;

public class Deck {
    //creates a standard deck of 52 cards
	Card deckOfCards[];
	Deck() {
		deckOfCards = new Card[52];
    }
    Card[] createDeck() {
        int counter = 0;
		int assignVal = 2;
		while (counter < 13) { //space cards 2-Ace
			deckOfCards[counter] = new Card(assignVal, "s");
			assignVal++;
			counter++;
	}
		assignVal = 2;
		while (counter < 26) { //heart cards 2-Ace
			deckOfCards[counter] = new Card(assignVal, "h");
			assignVal++;
			counter++;
	}
		assignVal = 2;
		while (counter < 39) { //club cards 2-Ace
			deckOfCards[counter] = new Card(assignVal, "c");
			assignVal++;
			counter++;
	}
		assignVal = 2;
		while (counter < 52) { //diamond cards 2-Ace
			deckOfCards[counter] = new Card(assignVal, "d");
			assignVal++;
			counter++;
		}
        return deckOfCards;
    }

    public void shuffle() {
        deckOfCards = createDeck();
        Random randomInt = new Random();
        for (int i = deckOfCards.length - 1; i > 0; i--) {
            int swap = randomInt.nextInt(i+1);
            Card temp = deckOfCards[swap];
            deckOfCards[swap] = deckOfCards[i];
            deckOfCards[i] = temp;
        }
    }

    public void printDeck() {
        for (int i = 0; i < 52; i++) {
            System.out.print(deckOfCards[i].print() + " ");
        }
    }
    
    public static void main(String[] args) {
        Deck myDeck = new Deck();
        myDeck.createDeck();
        myDeck.printDeck();
        System.out.println();
        myDeck.shuffle();
        myDeck.printDeck();
    }
}