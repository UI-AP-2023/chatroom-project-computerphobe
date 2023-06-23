package messageRelated;

import java.util.ArrayList;

public class Client {
    private String username;
    private long lastMessageNumber;
    private boolean isOnline;

    private ArrayList<Message> unreadMessages = new ArrayList<>();

    public Client(String username, long lastMessageNumber, boolean isOnline) {
        this.username = username;
        this.lastMessageNumber = lastMessageNumber;
        this.isOnline = isOnline;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public long getLastMessageNumber() {
        return lastMessageNumber;
    }

    public void setLastMessageNumber(long lastMessageNumber) {
        this.lastMessageNumber = lastMessageNumber;
    }

    public boolean isOnline() {
        return isOnline;
    }

    public void setOnline(boolean online) {
        isOnline = online;
    }

    public ArrayList<Message> getUnreadMessages() {
        return unreadMessages;
    }

    public void setUnreadMessages(ArrayList<Message> unreadMessages) {
        this.unreadMessages = unreadMessages;
    }
}
