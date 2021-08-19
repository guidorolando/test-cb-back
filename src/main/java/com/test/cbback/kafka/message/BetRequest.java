package com.test.cbback.kafka.message;

public class BetRequest {
    private String email;
    private Double betValue;
    private Double nomicsValue;

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

    public Double getNomicsValue() {
        return nomicsValue;
    }

    public void setNomicsValue(Double nomicsValue) {
        this.nomicsValue = nomicsValue;
    }
}
