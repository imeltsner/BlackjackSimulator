//A class representing a human blackack player
class Player {
    //member variables
    private int totalMoney;
    private int handValue;
    private boolean softTotal;
    
    //constructor
    Player() {
        totalMoney = 500;
        handValue = 0;
        softTotal = false;
    }
    
    //class methods
    public String[] getMyCards(String cards) {
        //creates array of single characters representing card values
        String[] myCards = cards.split(":");
        String[] myHand = new String[myCards.length]; 
        for (int i = 0; i < myCards.length; i++) {
            myHand[i] = myCards[i].split("")[0];
        }
        return myHand;
    }
    private int getCardValue(String card) {
        //calculates the value of a non-ace card
        String firstChar = card.split("")[0];
        if (firstChar.equals("K") || firstChar.equals("Q") || firstChar.equals("J") || firstChar.equals("1")) {
            return 10;
        } else if (!firstChar.equals("A")) {
            return Integer.parseInt(firstChar);
        } else {
            return 11;
        }
    }
    public int calculateHandValue(String cards) {
        //takes an array of cards and returns the sum of all the card values
        int totalValue = 0;
        int numAces = 0;
        boolean hasAce = false;
        String[] cardValues  = getMyCards(cards);
        //check if any cards are aces
        for (String c : cardValues) { 
            if (c.equals("A")) {
                hasAce = true;
                numAces += 1;
            }
        }
        //no aces in hand
        if (!hasAce) {
            softTotal = false;
            for (String c : cardValues) {
                totalValue += getCardValue(c);
            }
            return totalValue;
        } 
        //has aces -> find optimal value for ace
        else {
            if (cardValues.length == 2) {
                softTotal = true;
            } 
            int noAceTotal = 0;
            for (String c : cardValues) { //calculate hand total without ace
                if (c.equals("A")) {
                    continue;
                } else {
                    noAceTotal += getCardValue(c);
                }
            }
            //pick best value for ace based on rest of hand
            if (numAces == 1) {
                if ((noAceTotal + 11) <= 21) {
                    return noAceTotal + 11;
                } else {
                    return noAceTotal + 1;
                }
            } else {
                if ((noAceTotal + 11 + (numAces - 1)) <= 21) {
                    return noAceTotal + 11 + (numAces - 1);
                } else {
                    return noAceTotal + numAces;
                }
            }
        }
    }
    private int countCards(String[] cards) {
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

    public int placeBet(String[] cards) {
        //place bet based on hi low strategy
        //return bet value
        int trueCount = countCards(cards);
        if (trueCount <= 2 || (totalMoney  <= trueCount - 1)) {
            totalMoney -= 1;
            return 1;
        } else {
            totalMoney -= trueCount - 1;
            return trueCount - 1;
        }
    }

    public String action(String myCards, String dealerCard) {
        //determines player action based on myCards and dealerCard
        //code logic based on guide from https://www.blackjackapprenticeship.com/blackjack-strategy-charts/
        String[] myHand = getMyCards(myCards);
        int dealerCardValue = getCardValue(dealerCard);
        boolean hasHit = false;
        if (myHand.length > 2) {
            hasHit = true;
        }
        //when to split pairs
        if (myHand.length == 2 && (myHand[0].equals(myHand[1]))) {
            if (myHand[0].equals("A") || myHand[0].equals("8")) {
                return "split";
            } else if (myHand[0].equals("9")) {
                if (dealerCardValue <= 9 && dealerCardValue != 7) {
                    return "split";
                } else {
                    return "stand";
                }
            } else if (getCardValue(myHand[0]) == 10) {
                return "stand";
            } else if (myHand[0].equals("7") || myHand[0].equals("3") || myHand[0].equals("2")) {
                if (dealerCardValue <= 7) {
                    return "split";
                }
                else {
                    return "hit";
                }
            } else if (myHand[0].equals("6")) {
                if (dealerCardValue <= 6) {
                    return "split";
                } else {
                    return "hit";
                }
            } else if (myHand[0].equals("5")) {
                if (dealerCardValue <= 9) {
                    return "double";
                } else {
                    return "hit";
                }
            } else if (myHand[0].equals("4")) {
                if (dealerCardValue == 5 || dealerCardValue == 6) {
                    return "split";
                } else {
                    return "hit";
                }
            }
        }
        //no pair
        else {
            handValue = calculateHandValue(myCards);
            //no ace in hand
            if (!softTotal) {
                if (handValue >= 17) {
                    return "stand";
                } else if (handValue >= 13) {
                    if (dealerCardValue <= 6 && !hasHit) {
                        return "stand";
                    } else {
                        return "hit";
                    }
                } else if (handValue == 12) {
                    if (dealerCardValue >= 4 && dealerCardValue <= 6) {
                        return "stand";
                    } else {
                        return "hit";
                    }
                } else if (handValue == 11) {
                    if (!hasHit) {
                        return "double";
                    } else {
                        return "hit";
                    }
                    
                } else if (handValue == 10) {
                    if (dealerCardValue <= 9 && !hasHit) {
                        return "double";
                    } else {
                        return "hit";
                    }
                } else if (handValue == 9) {
                    if (dealerCardValue >= 3 && dealerCardValue <= 6) {
                        if (!hasHit) {
                            return "double";
                        } else {
                            return "hit";
                        }
                    }
                } else if (handValue <= 8) {
                    return "hit";
                } else {
                    return "stand";
                }
            } 
            //ace in hand
            else {
                if (handValue == 20) {
                    return "stand";
                } else if (handValue == 19) {
                    if (dealerCardValue == 6 && !hasHit) {
                        return "double";
                    } else {
                        return "stand";
                    }
                } else if (handValue == 18) {
                    if (dealerCardValue <= 6 && !hasHit) {
                        return "double";
                    } else if (dealerCardValue >= 9) {
                        return "hit";
                    } else {
                        return "stand";
                    }
                } else if (handValue == 17) {
                    if (dealerCardValue >= 3 && dealerCardValue <= 6 && !hasHit) {
                        return "double";
                    } else {
                        return "hit";
                    }
                } else if (handValue == 16 || handValue == 15) {
                    if (dealerCardValue >= 4 && dealerCardValue <= 6 && !hasHit) {
                        return "double";
                    } else {
                        return "hit";
                    }
                } else {
                    if ((dealerCardValue == 5 || dealerCardValue == 6) && !hasHit) {
                        return "double";
                    } else {
                        return "hit";
                    }
                }
            }
        }
        return "stand";
    }
    public String[] processCommand(String command) {
        String[] splitCommand = command.split(":");
        return splitCommand;
    }
    public void subtractMoney(int amount) {
        totalMoney -= amount;
    }
}