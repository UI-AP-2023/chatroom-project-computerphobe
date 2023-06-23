import java.io.IOException;
import java.net.Socket;

public class Main {
    public static void main(String[] args) {
        try {

            Socket clientA = new Socket("localhost",4545);
            new ReadThread(clientA).start();
            new WriteThread(clientA).start();

        } catch (IOException ex) {

            System.out.println("I/O Error: " + ex.getMessage());
        }
    }
}