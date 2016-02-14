package ru.finnetrolle.xdgs.face.dto.external.communication;

import ru.finnetrolle.xdgs.face.dto.external.dictionary.Command;

/**
 * Created by finnetrolle on 14.02.2016.
 */
public class LongPollingResponseDto {

    private String sessionId;
    private Command result;

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public Command getResult() {
        return result;
    }

    public void setResult(Command result) {
        this.result = result;
    }

    public LongPollingResponseDto() {
    }

    public LongPollingResponseDto(String sessionId, Command result) {

        this.sessionId = sessionId;
        this.result = result;
    }
}
