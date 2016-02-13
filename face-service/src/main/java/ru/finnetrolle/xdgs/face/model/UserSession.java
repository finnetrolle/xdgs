package ru.finnetrolle.xdgs.face.model;

import java.util.UUID;

/**
 * Created by finnetrolle on 13.02.2016.
 */
public class UserSession {

    private String sessionId;
    private String username;
    private long lastRequest;
    private long connected;

    public UserSession(String username) {
        this.username = username;
        this.sessionId = UUID.randomUUID().toString();
        long timestamp = System.currentTimeMillis();
        this.lastRequest = timestamp;
        this.connected = timestamp;
    }

    public static UserSession apply(String username) {
        return new UserSession(username);
    }

    public void touch() {
        this.lastRequest = System.currentTimeMillis();
    }

    public String getSessionId() {
        return sessionId;
    }

    public String getUsername() {
        return username;
    }

    public long getLastRequest() {
        return lastRequest;
    }

    public long getConnected() {
        return connected;
    }
}
