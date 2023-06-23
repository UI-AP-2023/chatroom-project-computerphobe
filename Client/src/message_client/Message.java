package message_client;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Message {
    private final String text;
    private final LocalTime messageTime;
    private final LocalDate messageDate;
    private final String sender;

    public Message(String theMessage) {
        String[] messageComponent = theMessage.split("\\|"); // sender | text | date | time
        this.sender = messageComponent[0];
        this.text =  messageComponent[1];
        this.messageTime = LocalTime.parse(messageComponent[2]);
        this.messageDate = LocalDate.parse(messageComponent[3]);
    }

    public Message(String text, String sender) {
        this.text = text;
        this.messageTime = LocalTime.now();
        this.messageDate = LocalDate.now();
        this.sender = sender;
    }

    public String getText() {
        return text;
    }

    public LocalTime getMessageTime() {
        return messageTime;
    }

    public LocalDate getMessageDate() {
        return messageDate;
    }

    public String getSender() {
        return sender;
    }

    @Override
    public String toString() {
        return "[" + sender + "] : " + text + "\n\t" +
                messageTime.format(DateTimeFormatter.ofPattern("%H:%m")) + "\t" +
                messageDate.format(DateTimeFormatter.ofPattern("%d %h"));
    }

    public String sendingFormatter() {
        return sender + "|" + text + "|" + messageDate.format(DateTimeFormatter.ofPattern("%H:%m")) +
                "|" + messageTime.format(DateTimeFormatter.ofPattern("%d %h"));
    }
}
