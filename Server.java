import java.net.Socket;
import java.net.ServerSocket;
import java.io.IOException; 
import java.io.DataInputStream; 
import java.io.DataOutputStream;

class DealerServer extends ServerSocket {
    public Socket reciever;
    public DataInputStream dis;
    public DataOutputStream dos; 

    DealerServer(int PORT) throws IOException {
        super(PORT);
        reciever = accept();
        dis = new DataInputStream(reciever.getInputStream()); 
        dos = new DataOutputStream(reciever.getOutputStream());
    }
    public void write(String s) throws IOException { //write to player
        dos.writeUTF(s);
        dos.flush(); 
    }

    public String read() throws IOException { //read from player
        return dis.readUTF();
    }
}

class Server {
    public static void main(String[] args) throws IOException {      
        DealerServer myDealer = new DealerServer(12345);
        System.out.println("client connected");
        myDealer.write("login");
        System.out.println(myDealer.read());
        myDealer.close();
    }
}