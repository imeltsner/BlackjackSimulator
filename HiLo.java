public class HiLo extends Player {
    HiLo() {
        super();
    }

    public int countCards(String[] cards) {
        //implements high low card counting strategy
        //return true count representing ratio of high cards to low cards
        //if true count <=1, odds not favorable
        //if true count >1, odds favorable
        int runningCount = 0;
        int trueCount = 0;
        int cardValue = 0;
        long decksLeft = Math.round((double) (364 - cards.length) / (double) 52);
        for (String card : cards) {
            cardValue = getCardValue(card);
            if (cardValue == 10 || cardValue == 11) {
                runningCount -= 1;
            } else if (cardValue <= 6) {
                runningCount += 1;
            } else {
                runningCount += 0;
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
