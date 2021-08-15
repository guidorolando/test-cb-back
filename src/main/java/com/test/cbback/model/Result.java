package com.test.cbback.model;

import javax.persistence.*;

@Entity
@Table(name = "result")
public class Result {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Game game;

    private Long userValue;

    private Boolean winner;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Long getUserValue() {
        return userValue;
    }

    public void setUserValue(Long userValue) {
        this.userValue = userValue;
    }

    public Boolean getWinner() {
        return winner;
    }

    public void setWinner(Boolean winner) {
        this.winner = winner;
    }
}
