import java.io.*;
import java.net.Socket;
import java.util.*;

public class CommunicationHandler extends Thread {
    private final Socket socket;

    private ArrayList<Thread> threads;
    private static long socketCount;

    private final long socketID;

    public CommunicationHandler(Socket socket, ArrayList<Thread> threads) {
        this.socket = socket;
        socketID = ++socketCount;
        this.threads = threads;
    }

    @Override
    public void run() {
        try {
            BufferedReader bufferIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String message;
            //---------------
            while (true) {
                message = bufferIn.readLine();
                if (message.equals("Exit")) break;
                else {
                    if (message.isBlank()) System.out.println("User Left the Chat");
                    System.out.println("Says " + message);
                    for (Thread thread : threads) {
                        if (thread != Thread.currentThread()) {
                            PrintWriter PWriter = new PrintWriter(socket.getOutputStream(), true);
                            PWriter.println(message);
                        }
                    }
                    System.out.printf("User %d: %s\n" ,socketID , message);
                }
            }
            System.out.println("Client Disconnected...");
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}