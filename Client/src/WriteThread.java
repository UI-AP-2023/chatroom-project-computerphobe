import message_client.Client;
import message_client.Message;

import java.io.*;
import java.net.Socket;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;
import static java.lang.System.currentTimeMillis;

public class WriteThread extends Thread {

    private final Socket clientSocket;
    private final Client client = new Client("User A");

    public WriteThread(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {

        try {

            String outgoingMessage;
            Scanner send = new Scanner(System.in);
            ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(this.clientSocket.getOutputStream()));

            boolean notExited = true;

            while (notExited) {

                outgoingMessage = send.nextLine();

                switch (outgoingMessage) {

                    case "ping" -> System.out.println(getPing());

                    case "Exit" -> notExited = false;

                    default -> sendMessage(outgoingMessage);
                }
            }


            Message turnOffMessage = new Message("Offline", null,null,client);

            out.writeObject(turnOffMessage);
            out.close();

        } catch (IOException IO) {
            System.out.println("Error in Connection");
        }
    }

    private void sendMessage(String outgoingMessage) throws IOException {

        ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(this.clientSocket.getOutputStream()));

        LocalTime nowTime = LocalTime.now();
        LocalDate nowDate = LocalDate.now();

        Message msg = new Message(outgoingMessage, nowTime, nowDate, this.client );

        out.writeObject(msg);
        out.close();
    }

    private long getPing() throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        ObjectOutputStream outputStream = new ObjectOutputStream(new BufferedOutputStream(clientSocket.getOutputStream()));

        long pingStart = currentTimeMillis();

        outputStream.writeObject(new Message());

        reader.readLine();

        long pingFinish = currentTimeMillis();

        reader.close();
        outputStream.close();

        return pingFinish - pingStart;
    }
}
