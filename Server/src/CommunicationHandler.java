import messageRelated.Message;

import java.io.*;
import java.net.Socket;

public class CommunicationHandler extends Thread {
    private Message theMessage;
    private final Socket socket;
    private static long socketCount;
    private final long socketID;

    private final DataBaseMessage db = new DataBaseMessage();
    private  final ObjectOutputStream objectOut;


    public CommunicationHandler(Socket socket) throws IOException {
        this.socket = socket;
        socketID = ++socketCount;
        objectOut = new ObjectOutputStream(new BufferedOutputStream(socket.getOutputStream()));
    }


    @Override
    public void run() {

        try {
            ObjectInputStream objectIn = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));


            //---------------

            boolean isExit = false;

            while (!isExit) {

                theMessage = (Message) objectIn.readObject();

                switch (theMessage.getText()) {

                    case "Exit" -> isExit = true;

                    case "" -> objectOut.writeObject(theMessage);

                    default ->  {

                        if (theMessage.getText().equals("Offline")) {


                            for (CommunicationHandler ch : Main.usersThreads) {

                                if (ch != Thread.currentThread()) {
                                    ch.objectOut.writeObject(theMessage);
                                }

                            }

                            break;
                        }

                        db.setTheMessage(theMessage);

                        if (db.insertMessage()) {

                            for (CommunicationHandler ch : Main.usersThreads) {

                                if (ch != Thread.currentThread()) {
                                    ch.objectOut.writeObject(theMessage);
                                }

                            }

                        }

                        else System.out.println("Error in Inserting The Message to The Data Base!");

                    }
                }

                    System.out.printf("User %d: %s\n", socketID, theMessage.getText());
            }
            System.out.println("Client Disconnected...");

            socket.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
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