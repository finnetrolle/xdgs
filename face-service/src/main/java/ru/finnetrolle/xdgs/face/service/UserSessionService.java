package ru.finnetrolle.xdgs.face.service;

import org.springframework.stereotype.Component;
import ru.finnetrolle.xdgs.face.model.UserSession;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by finnetrolle on 13.02.2016.
 */

@Component
public class UserSessionService {

    private final ConcurrentHashMap<String, UserSession> sessions = new ConcurrentHashMap<>();

    private final static long SESSION_TIMEOUT = 60000;

    public Optional<UserSession> getSession(String sessionId) {
        UserSession session = sessions.get(sessionId);
        if (session != null) {
            session.touch();
        }
        return Optional.ofNullable(session);
    }

    public String addSession(String username) {
        UserSession session = UserSession.apply(username);
        sessions.put(session.getSessionId(), session);
        return session.getSessionId();
    }

    public void tick() {
        long current = System.currentTimeMillis();
        sessions.values().stream()
                .filter(x -> current - x.getLastRequest() > SESSION_TIMEOUT)
                .forEach(x -> sessions.remove(x.getSessionId()));
    }

}
