package ru.finnetrolle.xdgs.storage.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.finnetrolle.xdgs.face.dto.internal.communication.AuthRequestDto;
import ru.finnetrolle.xdgs.face.dto.internal.communication.AuthResponseDto;
import ru.finnetrolle.xdgs.storage.service.AuthStorageService;

/**
 * Created by finnetrolle on 14.02.2016.
 */
@Component
public class Consumers {

    @Autowired
    AuthStorageService storage;

    @RabbitListener(queues = "${rabbit.queue.auth.signin}")
    public AuthResponseDto signIn(AuthRequestDto dto) {
        try {
            return new AuthResponseDto(AuthResponseDto.Result.SUCCESS, storage.get(dto.getUsername(), dto.getPassword()));
        } catch (Exception e) {
            return new AuthResponseDto(AuthResponseDto.Result.FAILED_BAD_AUTH_PAIR, null);
        }
    }

    @RabbitListener(queues = "${rabbit.queue.auth.signup}")
    public AuthResponseDto signUp(AuthRequestDto dto) {
        try {
            return new AuthResponseDto(AuthResponseDto.Result.SUCCESS, storage.add(dto.getUsername(), dto.getPassword()));
        } catch (Exception e) {
            return new AuthResponseDto(AuthResponseDto.Result.FAILED_ALREADY_EXISTS, null);
        }
    }

}
