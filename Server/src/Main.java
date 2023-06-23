import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Main {

    static ArrayList<Thread> usersThreads = new ArrayList<>();

    public static void main(String[] args) {

        System.out.println("Waiting...");


        try (ServerSocket server = new ServerSocket(4000)) {

            //noinspection InfiniteLoopStatement
            while(true)
            {
                Socket clientSocket = server.accept();

                System.out.println("User Connected to Server On Port " + clientSocket.getPort());

                //--------------------------------------------------

                CommunicationHandler handler = new CommunicationHandler(clientSocket);
                handler.start();
            }

        } catch (Exception IO) {
            System.out.println(IO.getMessage());
        }
    }
}