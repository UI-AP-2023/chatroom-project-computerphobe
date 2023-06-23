package messageRelated;

import messageRelated.Client;
import messageRelated.MessageType;

import java.time.LocalDate;
import java.time.LocalTime;

public class Message {
    private String text;
//    private MessageType messageType;
    private long messagePriority;
    private LocalTime messageTime;
    private LocalDate messageDate;
    private Client sender;

    public Message(String text, long messagePriority,
                   LocalTime messageTime, LocalDate messageDate, Client sender) {
        this.text = text;
        this.messagePriority = messagePriority;
        this.messageTime = messageTime;
        this.messageDate = messageDate;
        this.sender = sender;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public long getMessagePriority() {
        return messagePriority;
    }

    public void setMessagePriority(long messagePriority) {
        this.messagePriority = messagePriority;
    }

    public LocalTime getMessageTime() {
        return messageTime;
    }

    public void setMessageTime(LocalTime messageTime) {
        this.messageTime = messageTime;
    }

    public LocalDate getMessageDate() {
        return messageDate;
    }

    public void setMessageDate(LocalDate messageDate) {
        this.messageDate = messageDate;
    }

    public Client getSender() {
        return sender;
    }

    public void setSender(Client sender) {
        this.sender = sender;
    }
}
