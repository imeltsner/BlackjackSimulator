import java.net.Socket;
import java.net.UnknownHostException;
import java.io.IOException; 
import java.io.DataInputStream; 
import java.io.DataOutputStream;

//A class to connect to the dealer program and instantiate a new game
public class Game {
    DataInputStream dis;
    DataOutputStream dos;
    Socket socket;
    Game(String Ip, String Port) { //connect to dealer
        try {
            socket = new Socket(Ip, Integer.valueOf(Port)); 
            dis = new DataInputStream(socket.getInputStream()); 
            dos = new DataOutputStream(socket.getOutputStream());
        }
        catch (UnknownHostException e) {
            System.err.println("Unknown host: " + e);
        }
        catch (IOException e) {
            System.err.println("IO Excepiton: " + e);
        }
        
    }
    public void write(String s) throws IOException { //write to dealer
        dos.writeUTF(s);
        dos.flush(); 
    }
    public String read() throws IOException { //read from dealer
        return dis.readUTF();
    }
    public void endGame() { //end game
        try{
            socket.close();
        }
        catch (IOException e) {
            System.out.println("ERROR");
        }
    }
}
