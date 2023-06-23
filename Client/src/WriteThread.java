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
            PrintWriter out = new PrintWriter(this.clientSocket.getOutputStream());

            boolean notExited = true;

            while (notExited) {

                outgoingMessage = send.nextLine();

                switch (outgoingMessage) {

                    case "ping" -> System.out.println(getPing());

                    case "Exit" -> notExited = false;

                    default -> sendMessage(outgoingMessage,out);
                }
            }


            Message turnOffMessage = new Message("Offline", null,null,client);

            out.println(turnOffMessage);
            out.close();

        } catch (IOException IO) {
            System.out.println(IO.getMessage());
        }
    }

    private void sendMessage(String outgoingMessage, PrintWriter out) throws IOException {

        LocalTime nowTime = LocalTime.now();
        LocalDate nowDate = LocalDate.now();

        Message msg = new Message(outgoingMessage, nowTime, nowDate, this.client );

        out.println(msg);
    }

    private long getPing() throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        PrintWriter outputStream = new PrintWriter(clientSocket.getOutputStream());

        long pingStart = currentTimeMillis();

        outputStream.println(new Message());

        reader.readLine();

        long pingFinish = currentTimeMillis();

        reader.close();
        outputStream.close();

        return pingFinish - pingStart;
    }
}
