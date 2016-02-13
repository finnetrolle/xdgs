package ru.finnetrolle.xdgs.storage.service;

import org.springframework.stereotype.Component;
import ru.finnetrolle.xdgs.face.dto.internal.entity.UserDto;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by finnetrolle on 14.02.2016.
 */
@Component
public class AuthStorageService {

    private final Map<String, UserDto> users = new ConcurrentHashMap<>();

    public UserDto add(String username, String password) throws Exception {
        UserDto user = users.get(username);
        if (user != null) {
            throw new Exception();
        } else {
            user = new UserDto(username, password);
            users.put(username, user);
            return user;
        }
    }

    public UserDto get(String username, String password) throws Exception {
        if (users.containsKey(username)) {
            UserDto dto = users.get(username);
            if (dto.getPasswordHash().equals(password)) {
                return dto;
            }
        }
        throw new Exception();
    }

}
