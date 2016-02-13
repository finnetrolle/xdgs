package ru.finnetrolle.xdgs.face.service;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.finnetrolle.xdgs.face.dto.internal.entity.UserDto;
import ru.finnetrolle.xdgs.face.exception.ServiceLayerException;
import ru.finnetrolle.xdgs.face.rabbitmq.producers.AuthProducer;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * Created by finnetrolle on 13.02.2016.
 */

@Component
public class AuthService {

    @Autowired
    private AuthProducer authProducer;

    public void addUser(String username, String passwordNoHash) throws ServiceLayerException {
        authProducer.signUp(username, DigestUtils.sha256Hex(passwordNoHash));
    }

    public boolean checkAuth(String username, String passwordNoHash) throws ServiceLayerException {
        return (authProducer.signIn(username, DigestUtils.sha256Hex(passwordNoHash)) != null);
    }

}
