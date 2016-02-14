package ru.finnetrolle.xdgs.face.dto.external.communication;

/**
 * Created by finnetrolle on 14.02.2016.
 */
public class LongPollingRequestDto {

    private String sessionId;

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
}
