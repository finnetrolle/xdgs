package ru.finnetrolle.xdgs.face.dto.internal.entity;

/**
 * Created by finnetrolle on 14.02.2016.
 */
public class UserDto {

    private String username;
    private String passwordHash;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public UserDto() {

    }

    public UserDto(String username, String passwordHash) {

        this.username = username;
        this.passwordHash = passwordHash;
    }
}
