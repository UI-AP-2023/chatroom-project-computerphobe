package message_client;

import java.util.ArrayList;

public class Client {
    private final String username;

    private final ArrayList<Message> unreadMessages = new ArrayList<>();

    public Client(String username) {

        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public ArrayList<Message> getUnreadMessages() {
        return unreadMessages;
    }
}
