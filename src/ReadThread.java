import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ReadThread extends Thread{
    private String name = "User A";

    private Socket client;

    public ReadThread(Socket client) {
        this.client = client;
    }

    @Override
    public void run() {
        try {
            System.out.println(this.name + " Connected To The Server On Port " + client.getPort());
            BufferedReader bufferIn = new BufferedReader(new InputStreamReader(client.getInputStream()));
            while (true) {
                String incomingMessage = bufferIn.readLine();
                if (incomingMessage.equals("Finish")) break;
                System.out.println(incomingMessage);
            }
        } catch (IOException IO) {
            System.out.println("Error in Connection");
        }
    }
}
