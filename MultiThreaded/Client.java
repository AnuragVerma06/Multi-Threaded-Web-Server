import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.InetAddress;


public class Client{
public Runnable getRunnable(){
    return new Runnable() {
        @Override
        public void run() {
            int port = 8010;
            try{
                InetAddress address = InetAddress.getByName("localhost");
                Socket socket = new Socket(address, port);
                try(
                    PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                ) {
                    out.println("Hello from client!"+socket.getLocalSocketAddress());
                    String line = in.readLine();
                    System.out.println("Server response: " + line);
                } catch(IOException e){
                    e.printStackTrace();
                }

            }catch(IOException e){
                e.printStackTrace();
            }
        }
    };
}

    public static void main(String[] args) {
        Client client = new Client();
        for(int i=0; i<=100; i++){
            try{
                Thread thread = new Thread(client.getRunnable());
                thread.start();
            }catch(Exception ex){
                return;

            }
        }
    }
    
}
