import message_client.Message;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

import static java.lang.System.currentTimeMillis;

public class WriteThread extends Thread {
    private final String name = "User A";
    private final Socket clientSocket;
    BufferedWriter writer;

    public WriteThread(Socket clientSocket) throws IOException {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {

        try {

            String outgoingMessage = "";
            Scanner send = new Scanner(System.in);

            boolean isExit = false;

            while (!isExit) {

                writer = new BufferedWriter(new OutputStreamWriter(this.clientSocket.getOutputStream()));

                if (outgoingMessage.isEmpty())
                    outgoingMessage = "Ping";

                else outgoingMessage = send.nextLine();

                switch (outgoingMessage) {

                    case "Ping" -> System.out.println(getPing());

                    case "Exit" -> isExit = true;

                    default -> writer.write(new Message(outgoingMessage,name).sendingFormatter());
                }
            }

        } catch (IOException IO) {

            System.out.println(IO.getMessage());
            System.out.println("Error in Connection In Write Thread");
        }
    }

    private String getPing() throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        PrintWriter writer = new PrintWriter(clientSocket.getOutputStream());

        long pingStart = currentTimeMillis();

        writer.println("");

        reader.readLine();

        long pingFinish = currentTimeMillis();

        reader.close();
        writer.close();

        return String.format("%3d", (pingFinish - pingStart));
    }
}
