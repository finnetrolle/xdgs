package ru.finnetrolle.xdgs.storage.transformer;

import ru.finnetrolle.xdgs.face.dto.internal.entity.UserDto;
import ru.finnetrolle.xdgs.storage.model.entity.User;

import java.util.function.Function;

/**
 * Created by finnetrolle on 14.02.2016.
 */
public interface Mimic {

    interface Entity {
        Function<User, UserDto> user = x -> new UserDto(x.getName(), x.getPass());
    }

}
