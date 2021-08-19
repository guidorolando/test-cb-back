package com.test.cbback.model;

import javax.persistence.*;

@Entity
@Table(name = "game")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private Boolean status;
    private Double initialValue;
    private Double finalValue;

    private Long initTime;
    private Long endTime;

    @ManyToOne
    @JoinColumn(name="type_game_id", referencedColumnName="id")
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

    public Double getInitialValue() {
        return initialValue;
    }

    public void setInitialValue(Double initialValue) {
        this.initialValue = initialValue;
    }

    public Double getFinalValue() {
        return finalValue;
    }

    public void setFinalValue(Double finalValue) {
        this.finalValue = finalValue;
    }

    public Long getInitTime() {
        return initTime;
    }

    public void setInitTime(Long initTime) {
        this.initTime = initTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }
}
