import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ReadThread extends Thread{
    private String name;

    private Socket client;

    public ReadThread(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        try (Socket client = new Socket("localhost",4000)) {
            BufferedReader bufferIn = new BufferedReader(new InputStreamReader(client.getInputStream()));
            while (true) {
                String incomingMessage = bufferIn.readLine();
                if (incomingMessage.isEmpty()) break;
                System.out.println(incomingMessage);
            }
        } catch (IOException IO) {
            System.out.println("Error in Connection");
        }
    }
}
