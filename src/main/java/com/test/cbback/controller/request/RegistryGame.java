package com.test.cbback.controller.request;

public class RegistryGame {
    private String email;
    private Double betValue;
    private Long gameId;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Double getBetValue() {
        return betValue;
    }

    public void setBetValue(Double betValue) {
        this.betValue = betValue;
    }

    public Long getGameId() {
        return gameId;
    }

    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }
}
