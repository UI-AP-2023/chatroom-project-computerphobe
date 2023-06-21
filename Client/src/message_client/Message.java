package message_client;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Message {
    private final String text;
    private static long staticMessagePriority = 1;
    private long messagePriority;
    private final LocalTime messageTime;
    private final LocalDate messageDate;
    private final Client sender;


    //Server Message constructor
    public Message(String text, long messagePriority,
                   LocalTime messageTime, LocalDate messageDate, Client sender) {

        this.text = text;
        this.messagePriority = staticMessagePriority++;
        this.messageTime = messageTime;
        this.messageDate = messageDate;
        this.sender = sender;
    }

    //Client Message constructor
    public Message(String text, LocalTime messageTime, LocalDate messageDate, Client sender) {

        this.text = text;
        this.messageTime = messageTime;
        this.messageDate = messageDate;
        this.sender = sender;
    }

    public Message () {

        this.text = "";
        this.messageTime = LocalTime.now();
        this.messageDate = LocalDate.now();
        this.sender = null;
    }

    @Override
    public String toString() {
        return "[" + sender.getUsername() + "] : " + text + "\n\t" +
                messageTime.format(DateTimeFormatter.ofPattern("%H:%m")) + "\t" +
                messageDate.format(DateTimeFormatter.ofPattern("%d %b"));
    }

    public long getMessagePriority() {
        return messagePriority;
    }
}