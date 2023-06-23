import databaseRelated.DataBaseMessage;
import messageRelated.Message;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Objects;

public class CommunicationHandler extends Thread {
    private String theMessage;
    private Message receivedMessage;
    private final Socket clientSocket;
    private static long socketCounter;
    private final long socketID;
    private final DataBaseMessage db = new DataBaseMessage();

    public CommunicationHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
        socketID = ++socketCounter;
    }

    @Override
    public void run() {

        try {

            System.out.println("Communication Handler Start");

            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            boolean isExit = false;

            // older messages
//            ArrayList<String> olderMessages = db.readMessage();

//            if (olderMessages == null) {
//
//                System.out.println("No Old Messages!");
//                writer.write("No Old Messages! Be The First To Text :)");
//
//            } else {
//
//                for (String message : olderMessages) {
//                    writer.write(message);
//                }
//            }

            // new messages
            while (!isExit) {

                theMessage = reader.readLine();

                if (theMessage.equals(""))
                    writer.write(theMessage);

                else receivingFormatter();

                // check if receives the messages
                System.out.println("New Message");


                switch (receivedMessage.getText()) {

                    case "Exit" -> isExit = true;

                    case "" -> writer.write(theMessage);

                    default -> {

                        if (receivedMessage.getText().equals("Offline")) {


                            for (Thread thread : Main.usersThreads) {

                                if (thread != Thread.currentThread()) {
                                    writer.write(theMessage);
                                }

                            }

                            break;
                        }

                        db.setTheMessage(receivedMessage);

                        if (db.insertMessage()) {

                            for (Thread thread : Main.usersThreads) {

                                if (thread != Thread.currentThread()) {
                                    writer.write(theMessage);
                                }

                            }

                        } else throw new Exception("Error in Inserting The Message to The Data Base!");

                    }
                }

                System.out.printf("%s on Socket %d Says: %s\n", receivedMessage.getSender(), socketID, receivedMessage.getText());
            }
            System.out.println("Client Disconnected...");

            clientSocket.close();

        } catch (
                Exception ex) {
            System.out.println(ex.getMessage());
        }

    }

    private void receivingFormatter() {
        receivedMessage = new Message(theMessage);
    }
}