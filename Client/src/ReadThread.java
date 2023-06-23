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

        try(BufferedReader bufferIn = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {

            String name = "User A";
            System.out.println(name + " Connected To The Server On Port " + clientSocket.getPort());


            //=----------------------------------


            while (true) {

                String incomingMessage = bufferIn.readLine();

                if (incomingMessage.equals("Finish")) break;

                System.out.println(incomingMessage);
            }

        } catch (IOException IO) {
//            IO.printStackTrace();
            System.out.println("Error in Connection");
        }
    }
}
