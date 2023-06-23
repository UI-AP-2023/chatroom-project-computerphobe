import message_client.Message;

import java.io.*;
import java.net.Socket;

public class ReadThread extends Thread {
    private final String name = "User A";
    private final Socket clientSocket;
    private Message receivedMessage;

    public ReadThread(Socket clientSocket) throws IOException {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {

        try {

            System.out.println(name + " Connected To The Server On Port " + clientSocket.getPort());

            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            //----------------------------------

            while (true) {
                String incomingMessage = reader.readLine();
                if (incomingMessage.contains("No Old Messages")) {
                    System.out.println(incomingMessage);
                }
                else {
                    receivingFormatter(incomingMessage);
                    System.out.println(receivedMessage);
                }
            }

        } catch (Exception ex) {

            System.out.println(ex.getMessage());
            System.out.println("Error in Connection In Read Thread");
        }
    }

    private void receivingFormatter(String incomingMessage) {
        receivedMessage = new Message(incomingMessage);
    }
}
