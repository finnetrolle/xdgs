package ru.finnetrolle.xdgs.face.rabbitmq.producers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.finnetrolle.xdgs.face.dto.internal.communication.AuthRequestDto;
import ru.finnetrolle.xdgs.face.dto.internal.communication.AuthResponseDto;
import ru.finnetrolle.xdgs.face.dto.internal.entity.UserDto;
import ru.finnetrolle.xdgs.face.exception.LoginFailedException;
import ru.finnetrolle.xdgs.face.exception.ServiceLayerException;
import ru.finnetrolle.xdgs.face.exception.UserAlreadyExistsException;

import javax.annotation.PostConstruct;

/**
 * Created by finnetrolle on 14.02.2016.
 */

@Component
public class AuthProducer {

    private final static Logger log = LoggerFactory.getLogger(AuthProducer.class);

    @Value("${rabbit.queue.auth.signin}") private String signInQueue;
    @Value("${rabbit.queue.auth.signup}") private String signUpQueue;

    @Autowired
    private AmqpTemplate rabbit;

    @PostConstruct
    public void postConstruct() {
        log.info("Auth producer started");
    }

    public UserDto signIn(String username, String password) throws ServiceLayerException {
        log.debug("Received sign in request");
        AuthRequestDto msg = new AuthRequestDto(username, password);
        log.debug("Received sign in response or timed out");
        AuthResponseDto response = (AuthResponseDto)
                rabbit.convertSendAndReceive(signInQueue, msg);
        if (response != null) {
            switch (response.getResult()) {
                case SUCCESS: return response.getUserDto();
                case FAILED_BAD_AUTH_PAIR: throw new LoginFailedException();
            }
        }
        throw new ServiceLayerException();
    }

    public UserDto signUp(String username, String password) throws ServiceLayerException {
        log.debug("Received sign up request");
        AuthRequestDto msg = new AuthRequestDto(username, password);
        log.debug("Received sign up response or timed out");
        AuthResponseDto response = (AuthResponseDto)
                rabbit.convertSendAndReceive(signUpQueue, msg);
        if (response != null) {
            switch (response.getResult()) {
                case SUCCESS: return response.getUserDto();
                case FAILED_ALREADY_EXISTS: throw new UserAlreadyExistsException();
            }
        }
        throw new ServiceLayerException();
    }

}
