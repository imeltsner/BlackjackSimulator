import java.io.IOException;
import java.util.Arrays;

//A program to play a game of blackjack computer vs computer
public class Blackjack {   
    public static void main(String[] args) {
        String IP = args[0];
        String PORT = args[1];
        String command = new String();
        Player myPlayer = new HiLo(IP, PORT);
        while (true) {
            try { //get command from dealer
                command = myPlayer.read();
            }
            catch (IOException e) {
                continue;
            }
            String[] commandArray = myPlayer.processCommand(command);
            if (commandArray[0].equals("login")) { //login
                try {
                    myPlayer.write("imeltsner:isaac");
                }
                catch (IOException e) {
                    System.out.println("ERROR");
                }
            } else if (commandArray[0].equals("bet")) { //place bet
                if (commandArray.length == 3) { //no cards seen -> bet 1
                    try {
                        myPlayer.write("bet:1");
                        myPlayer.subtractMoney(1);
                    }
                    catch (IOException e) {
                        System.out.println("ERROR");
                    }
                } else { //some cards seen -> calculate optimal bet
                    String[] cards = Arrays.copyOfRange(commandArray, 3, commandArray.length);
                    int aBet = myPlayer.placeBet(cards);
                    String myBet = Integer.toString(aBet);
                    try {
                        myPlayer.write("bet:" + myBet);
                    }
                    catch (IOException e) {
                        System.out.println("ERROR");
                    }
                }
            } else if (commandArray[0].equals("play")) { //hit, stand, split, or double
                String[] cards = Arrays.copyOfRange(commandArray, 4, commandArray.length);
                String myHand = String.join(":", cards);
                String myAction = myPlayer.action(myHand, commandArray[2]);
                try {
                    System.out.println("Hand value is " + myPlayer.calculateHandValue(myHand));
                    System.out.println("My cards are " + myHand);
                    System.out.println("My action is " + myAction);
                    myPlayer.write(myAction);
                }
                catch (IOException e) {
                    System.out.println("ERROR");
                }
            } else if (commandArray[0].equals("status")) {
                System.out.println(command);
            } else if (commandArray[0].equals("done")) { //end game
                System.out.println(commandArray[1]);
                myPlayer.endGame();
                break;
            }
        }
    }
}