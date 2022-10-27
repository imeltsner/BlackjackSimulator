public class Card {
	//member variables
	String value = new String(); //card value (2-10, Jack, Queen, King, Ace)
	String suit = new String(); //card suit (Spades, Hearts, Diamonds, Clubs)

	//int and string constructor
	Card(int setValue, String setSuit) {
		//set card value
		if (setValue < 2 || setValue > 14) { //error handling
			value = "0";
			suit = "ERROR";
		} else if (setValue <= 10) { //number cards
			value = String.valueOf(setValue);
		} else if (setValue == 11) { //face cards
			value = "Jack";
		} else if (setValue == 12) {
			value = "Queen"; 
		} else if (setValue == 13) {
			value = "King";
		} else {
			value = "Ace";
		}

		//set card suit
		String suitSetter = setSuit.toLowerCase().split("")[0]; //first character of input
		if (suitSetter.equals("s")) {
			suit = "Spades";
		} else if (suitSetter.equals("h")) {
			suit = "Hearts";
		} else if (suitSetter.equals("c")) {
			suit = "Clubs";
		} else if (suitSetter.equals("d")) {
			suit = "Diamonds";
		} else { //error handling
			value = "0";
			suit = "ERROR";
		}
	}
    
    public String print() { //prints card value and suit
		String cardVal = new String();
		if (value.length() <= 2) {
			cardVal = value;
		} else {
			String valLetters[] = value.split("");
			cardVal = valLetters[0];
		}
		String suitLetters[] = suit.split("");
		String cardSuit = suitLetters[0];
		return (cardVal + cardSuit);
	}
}