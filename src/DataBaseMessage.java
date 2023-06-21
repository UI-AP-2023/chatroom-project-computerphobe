import messageRelated.Client;
import messageRelated.Message;

import java.sql.ResultSet;

public class DataBaseMessage {

    private Message theMessage;
    private Client receiver;

    public Boolean insertMessage() {

        Client sender = theMessage.getSender();

        try {

            String sqlCmd = String.format
                    ("INSERT INTO messages (MessageText,Date,Time,Sender) " +
                                    "values (%d,%s,%tY-%tm-%td,%tH:%tM:%tS,%s)",
                            this.theMessage.getText(),
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

    public void readMessage() {

        String sqlCmd = String.format ("Select * From messages");

        MySQLConnection sql = new MySQLConnection();

        ResultSet rs = sql.executeQuery(sqlCmd);

        if (rs == null) System.out.println("Error in Connecting to Data Base!\n\tPlease Check Your Connection.");

        else {

            try {

                while (rs.next()) {

                    Message newMessage = new Message(rs.getString("MessageText"),
                            rs.getTime("Time").toLocalTime(), rs.getDate("Date").toLocalDate(),
                            rs.getObject("Sender", Client.class));
                    receiver.getUnreadMessages().add(newMessage);

                }

            } catch (Exception ex) {
                System.out.println("Error in Selecting Data From Data Base!");
            }
        }
    }

    public Message getTheMessage() {
        return theMessage;
    }

    public void setTheMessage(Message theMessage) {
        this.theMessage = theMessage;
    }

    public Client getReceiver() {
        return receiver;
    }

    public void setReceiver(Client receiver) {
        this.receiver = receiver;
    }
}
