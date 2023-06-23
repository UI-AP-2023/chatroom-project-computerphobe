import messageRelated.Client;
import messageRelated.Message;

import java.net.Socket;
import java.sql.ResultSet;

public class DataBaseMessage {

    Message theMessage;
    Client client;
    private Socket clientSocket;

    public DataBaseMessage(Message theMessage, Socket clientSocket) {
        this.theMessage = theMessage;
        this.clientSocket = clientSocket;
    }

    Boolean insertMessage() {
        Client sender = theMessage.getSender();
        try {
            String sqlCmd = String.format
                    ("INSERT INTO messages (MessagePriority,MessageText,Date,Time,Sender) " +
                                    "values (%d,%s,%tY-%tm-%td,%tH:%tM:%tS,%s)",
                            this.theMessage.getMessagePriority(), this.theMessage.getText(),
                            this.theMessage.getMessageDate(), this.theMessage.getMessageDate(),
                            this.theMessage.getMessageDate(), this.theMessage.getMessageTime(),
                            this.theMessage.getMessageTime(), this.theMessage.getMessageTime(),
                            sender.getUsername());
            MySQLConnection sql = new MySQLConnection();
            return sql.executeSQL(sqlCmd);
        } catch (Exception ex) {
            System.out.println("Error in Sending The messageRelated.Message To the Data Base!");
            return false;
        }
    }

    Message readMessage() {
        Client receiver = this.client;
        String sqlCmd = String.format
                ("Select * From messages WHERE MessagePriority > %d", receiver.getLastMessageNumber());
        MySQLConnection sql = new MySQLConnection();
        ResultSet rs = sql.executeQuery(sqlCmd);
        if (rs == null) System.out.println("Error in Connecting to Data Base!\n\tPlease Check Your Connection.");
        else {
            try {

                while (rs.next()) {

                    Message newMessage = new Message(rs.getString("MessageText"),
                            rs.getLong("MessagePriority"), rs.getTime("Time").toLocalTime()
                            , rs.getDate("Date").toLocalDate(), rs.getObject("Sender", Client.class));
                    receiver.getUnreadMessages().add(newMessage);

                }

            } catch (Exception ex) {
                System.out.println("Error in Selecting Data From Data Base!");
            }
        }
    }
}
