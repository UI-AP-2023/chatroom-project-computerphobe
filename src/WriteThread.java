import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class WriteThread extends Thread {

    private Socket client;

    public WriteThread(Socket client) {
        this.client = client;
    }

    @Override
    public void run() {
        try {
            String outgoingMessage;
            Scanner send = new Scanner(System.in);
            PrintWriter Pout = new PrintWriter(client.getOutputStream(),true);

            while (true) {
                outgoingMessage = send.nextLine();
                if (outgoingMessage.equals("Exit")) break;
                Pout.println(outgoingMessage);
            }
            Pout.println("User turned Off!");
            Pout.close();
        } catch (IOException IO) {
            System.out.println("Error in Connection");
        }
    }
}
