import messageRelated.Message;

import java.io.*;
import java.net.Socket;
import java.util.*;

public class CommunicationHandler extends Thread {
    private Message theMessage;
    private final Socket socket;
    private ArrayList<Thread> threads;
    private static long socketCount;
    private final long socketID;

    private final DataBaseMessage db = new DataBaseMessage();

    public CommunicationHandler(Socket socket, ArrayList<Thread> threads) {
        this.socket = socket;
        socketID = ++socketCount;
        this.threads = threads;
    }

    @Override
    public void run() {

        try {
            ObjectInputStream objectIn = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));

            ObjectOutputStream objectOut = new ObjectOutputStream(new BufferedOutputStream(socket.getOutputStream()));

            //---------------

            while (true) {

                theMessage = (Message) objectIn.readObject();

                if (theMessage.getText().equals("Exit")) break;

                //ping on client side
                else if (theMessage.getText().isEmpty()) objectOut.writeObject(new Message());

                else {

                    db.setTheMessage(theMessage);

                    if (db.insertMessage()) {

                        for (Thread thread : threads) {

                            if (thread != Thread.currentThread()) {
                                objectOut.writeObject(theMessage);
                            }

                        }

                    }

                    else System.out.println("Error in Inserting The Message to The Data Base!");

                }

                    System.out.printf("User %d: %s\n", socketID, theMessage.getText());
            }
            System.out.println("Client Disconnected...");

            socket.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
// Should Be Moved To The Client Side
    int getPing() throws IOException {
        int ping = 0;
        // send this for client and calculate ping
        {
            long now = System.currentTimeMillis();
            PrintWriter pw = new PrintWriter(socket.getOutputStream());
            pw.println(now);
        }
        return ping;
    }

    public Message getTheMessage() {
        return theMessage;
    }

    public void setTheMessage(Message theMessage) {
        this.theMessage = theMessage;
    }

    public Socket getSocket() {
        return socket;
    }

    public ArrayList<Thread> getThreads() {
        return threads;
    }

    public void setThreads(ArrayList<Thread> threads) {
        this.threads = threads;
    }

    public static long getSocketCount() {
        return socketCount;
    }

    public static void setSocketCount(long socketCount) {
        CommunicationHandler.socketCount = socketCount;
    }

    public long getSocketID() {
        return socketID;
    }
}