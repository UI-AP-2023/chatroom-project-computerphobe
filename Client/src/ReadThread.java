import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ReadThread extends Thread{

    private final Socket clientSocket;

    public ReadThread(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {

        try {

            String name = "User A";
            System.out.println(name + " Connected To The Server On Port " + clientSocket.getPort());
            BufferedReader bufferIn = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            //=----------------------------------


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
