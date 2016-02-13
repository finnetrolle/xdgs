package ru.finnetrolle.xdgs.face.dto.internal.communication;

import ru.finnetrolle.xdgs.face.dto.internal.entity.UserDto;

/**
 * Created by finnetrolle on 14.02.2016.
 */
public class AuthResponseDto {

    public enum Result {
        SUCCESS,
        FAILED_BAD_AUTH_PAIR,
        FAILED_ALREADY_EXISTS
    }

    private Result result;
    private UserDto userDto;

    public AuthResponseDto(Result result, UserDto userDto) {
        this.result = result;
        this.userDto = userDto;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public UserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }

    public AuthResponseDto() {

    }
}
