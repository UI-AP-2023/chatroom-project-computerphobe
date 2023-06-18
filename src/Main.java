import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        System.out.println("Waiting...");
        ArrayList<Thread> usersThreads = new ArrayList<>();
        try (ServerSocket server = new ServerSocket(4000)) {
            while(true)
            {
                Socket clientSocket = server.accept();
                System.out.println("User Connected!");
                //----------
                CommunicationHandler handler = new CommunicationHandler(clientSocket,usersThreads);
                handler.start();
            }
        } catch (IOException IO) {
            System.out.println("Error in Connection!");
        }
    }
}