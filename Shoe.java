import java.util.ArrayList;
import java.util.Random;

//a class to create a blackjack shoe of decks with n number of decks
public class Shoe{
    private ArrayList<Card> shoe;
    Shoe() {
        shoe = new ArrayList<Card>();
    }
    public class Deck {
        //creates a standard deck of 52 cards
        private ArrayList<Card> deck;
        Deck() {
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
        }

        public Card getCard(int index) {
            return deck.get(index);
        }
    } //end class deck

    //shoe class methods
    public ArrayList<Card> createShoe(int numDecks) {
        Deck myDeck = new Deck();
        shoe = new ArrayList<Card>();
        for (int i = 0; i < numDecks; i++) {
            for (int j = 0; j < 52; j++) {
                shoe.add(myDeck.getCard(j));
            }
        }
        return shoe;
    }
    public void shuffle() {
        //fischer-yates shuffle algorithm
        shoe = createShoe(2);
        Random randomInt = new Random();
        for (int i = shoe.size() - 1; i > 0; i--) {
            int swap = randomInt.nextInt(i+1);
            Card temp = shoe.get(swap);
            shoe.set(swap, shoe.get(i));
            shoe.set(i, temp);
        }
    }
    public ArrayList<Card> deal(int numCards) {
        ArrayList<Card> hand = new ArrayList<Card>();
        for (int i = 0; i < numCards; i++) {
            Card card = shoe.get(0);
            hand.add(card);
            shoe.remove(0);
        }
        return hand;
    }
    public void printShoe() {
        for (int i = 0; i < shoe.size(); i++) {
            System.out.print(shoe.get(i).print() + " ");
        }
        System.out.println();
        System.out.println("There are " + shoe.size() + " cards in the shoe");
    }
    public static void main(String[] args) {
        Shoe myShoe = new Shoe();
        myShoe.createShoe(2);
        myShoe.shuffle();
        myShoe.printShoe();
        myShoe.deal(2);
        myShoe.printShoe();
        myShoe.deal(2);
        myShoe.printShoe();
        myShoe.shuffle();
        myShoe.printShoe();
    }
}
