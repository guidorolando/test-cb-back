package com.test.cbback.model;

import javax.persistence.*;

@Entity
@Table(name = "game")
public class Game {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private Boolean status;
    private Long finalValue;

    @ManyToOne
    private TypeGame typeGame;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public TypeGame getTypeGame() {
        return typeGame;
    }

    public void setTypeGame(TypeGame typeGame) {
        this.typeGame = typeGame;
    }

    public Long getFinalValue() {
        return finalValue;
    }

    public void setFinalValue(Long finalValue) {
        this.finalValue = finalValue;
    }
}
