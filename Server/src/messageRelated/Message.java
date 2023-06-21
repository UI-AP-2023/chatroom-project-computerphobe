package messageRelated;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Message {
    private String text;
    private LocalTime messageTime;
    private LocalDate messageDate;
    private Client sender;

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

    public String getText() {
        return text;
    }

    public LocalTime getMessageTime() {
        return messageTime;
    }

    public LocalDate getMessageDate() {
        return messageDate;
    }

    public Client getSender() {
        return sender;
    }
    @Override
    public String toString() {
        return "[" + sender.getUsername() + "] : " + text + "\n\t" +
                messageTime.format(DateTimeFormatter.ofPattern("%H:%m")) + "\t" +
                messageDate.format(DateTimeFormatter.ofPattern("%d %b"));
    }
}
