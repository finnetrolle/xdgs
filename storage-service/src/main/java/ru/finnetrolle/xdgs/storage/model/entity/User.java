package ru.finnetrolle.xdgs.storage.model.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by finnetrolle on 14.02.2016.
 */

@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "login")
    private String name;

    @Column(name = "password_hash")
    private String pass;

    @Column(name = "active")
    private Boolean active;

    @Column(name = "email")
    private String email;

    @Column(name = "loses")
    private Integer loses;

    @Column(name = "points")
    private Integer points;

    @Column(name = "wins")
    private Integer wins;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getLoses() {
        return loses;
    }

    public void setLoses(int loses) {
        this.loses = loses;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }


}
