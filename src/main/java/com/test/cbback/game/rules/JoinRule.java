package com.test.cbback.game.rules;

import java.util.Date;

public class JoinRule implements RuleGame{

    private Long iniTime;
    private Long endTime;
    private Long warningTime;

    public JoinRule(Long initTime, Long endTime, Long warningTime){
        this.iniTime = initTime;
        this.endTime = endTime;
        this.warningTime = warningTime;
    }

    @Override
    public boolean validate() {
        Date now = new Date();
        if(now.getTime() < this.calculateTime()) {
            return true;
        } else {
            return false;
        }
    }

    private Long calculateTime() {
        return this.endTime - this.warningTime;
    }
}
