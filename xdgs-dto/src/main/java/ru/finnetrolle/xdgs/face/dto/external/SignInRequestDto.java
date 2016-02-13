package ru.finnetrolle.xdgs.face.dto.external;

import java.util.Objects;

/**
 * Created by finnetrolle on 13.02.2016.
 */
public class SignInRequestDto {

    private String username;
    private String password;

    public SignInRequestDto() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public SignInRequestDto(String username, String password) {

        this.username = username;
        this.password = password;
    }

    @Override
    public String toString() {
        return "SignInRequestDto{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SignInRequestDto)) return false;
        SignInRequestDto that = (SignInRequestDto) o;
        return Objects.equals(username, that.username) &&
                Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password);
    }
}
