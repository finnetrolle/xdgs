package ru.finnetrolle.xdgs.storage.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.finnetrolle.xdgs.face.dto.internal.entity.UserDto;
import ru.finnetrolle.xdgs.storage.model.entity.User;
import ru.finnetrolle.xdgs.storage.model.repository.UserRepository;
import ru.finnetrolle.xdgs.storage.transformer.Mimic;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by finnetrolle on 14.02.2016.
 */
@Component
public class AuthStorageService {

    @Autowired
    private UserRepository repository;

    public UserDto add(String username, String password) throws Exception {
        User user = repository.findOne(username);
        if (user == null) {
            user = new User();
            user.setName(username);
            user.setPass(password);
            user.setActive(true);
            user = repository.save(user);
            return new UserDto(user.getName(), user.getPass());
        }
        throw new Exception();
    }

    public UserDto get(String username, String password) throws Exception {
        User user = repository.findOne(username);
        if (user != null && user.getPass().equals(password)) {
            return Mimic.Entity.user.apply(user);
        }
        throw new Exception();
    }

}
