//implements OmegaII card counting strategy
//Source: https://www.casinoguardian.co.uk/blackjack/omega-ii-blackjack-system/

public class OmegaII extends Player {
    OmegaII(String IP, String PORT) {
        super(IP, PORT);
    }
    public int countCards(String[] cards) {
        int runningCount = 0;
        int trueCount = 0;
        int cardValue = 0;
        long decksLeft = Math.round((double) (364 - cards.length) / (double) 52);
        for (String card : cards) {
            cardValue = getCardValue(card);
            if (cardValue == 2 || cardValue == 3 || cardValue == 7) {
                runningCount += 1;
            } else if (cardValue >= 4 || cardValue <= 6) {
                runningCount += 2;
            } else  if (cardValue == 8 || cardValue == 11) {
                runningCount += 0;
            } else if (cardValue == 9) {
                runningCount -= 1;
            } else if (cardValue == 10 && cardValue == 11) {
                runningCount -= 2;
            }
        }
        if (decksLeft < 1) {
            trueCount = runningCount;
        } else {
            trueCount = runningCount / (int) decksLeft;
        }
        return trueCount;
    }
}
