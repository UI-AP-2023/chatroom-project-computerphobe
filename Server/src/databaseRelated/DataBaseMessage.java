package databaseRelated;

import messageRelated.Message;

import java.sql.ResultSet;
import java.util.ArrayList;

public class DataBaseMessage {

    private Message theMessage;

    public Boolean insertMessage() {

        try {

            String sqlCmd = String.format
                    ("INSERT INTO messages (MessageText,Date,Time,Sender) " +
                                    "values (%s,%tY-%tm-%td,%tH:%tM:%tS,%s)",
                            this.theMessage.getText(),
                            this.theMessage.getMessageDate(), this.theMessage.getMessageDate(),
                            this.theMessage.getMessageDate(), this.theMessage.getMessageTime(),
                            this.theMessage.getMessageTime(), this.theMessage.getMessageTime(),
                            theMessage.getSender());

            MySQLConnection sql = new MySQLConnection();

            return sql.executeSQL(sqlCmd);

        } catch (Exception ex) {
            System.out.println("Error in Sending The Message To the Data Base!");
            return false;
        }
    }

    public ArrayList<String> readMessage() {

        String sqlCmd = ("Select * From messages");

        MySQLConnection sql = new MySQLConnection();

        try (ResultSet rs = sql.executeQuery(sqlCmd)) {

            ArrayList<String> olderMessages = new ArrayList<>();
            while (rs.next()) {

                olderMessages.add(new Message(rs.getString("MessageText"),
                        rs.getTime("Time").toLocalTime(), rs.getDate("Date").toLocalDate(),
                        rs.getString("Sender")).formatter());

            }
            return olderMessages;

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            System.out.println("Error in Selecting Data From Data Base!");
        }
        return null;
    }

    public void setTheMessage(Message theMessage) {
        this.theMessage = theMessage;
    }
}
