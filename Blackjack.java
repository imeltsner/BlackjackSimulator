import java.io.IOException;
import java.util.Arrays;

//A program to play a game of blackjack computer vs computer
public class Blackjack {   
    public static void main(String[] args) {
        String IpAddress = args[0];
        String IpPort = args[1];
        String command = new String();
        Game game = new Game(IpAddress, IpPort);
        Player myPlayer = new Player();
        while (true) {
            try { //get command from dealer
                command = game.read();
            }
            catch (IOException e) {
                continue;
            }
            String[] commandArray = myPlayer.processCommand(command);
            if (commandArray[0].equals("login")) { //login
                try {
                    game.write("imeltsner:isaac");
                }
                catch (IOException e) {
                    System.out.println("ERROR");
                }
            } else if (commandArray[0].equals("bet")) { //place bet
                if (commandArray.length == 3) { //no cards seen -> bet 1
                    try {
                        game.write("bet:1");
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
                        game.write("bet:" + myBet);
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
                    game.write(myAction);
                }
                catch (IOException e) {
                    System.out.println("ERROR");
                }
                
            } else if (commandArray[0].equals("status")) {
                System.out.println(command);
            } else if (commandArray[0].equals("done")) { //end game
                System.out.println(commandArray[1]);
                game.endGame();
                break;
            }
        }
    }
}