package com.test.cbback.config;

import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class TimerSingleton {

    private Date date = new Date();
    private Double initValue = 0.0;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getInitValue() {
        return initValue;
    }

    public void setInitValue(Double initValue) {
        this.initValue = initValue;
    }
}
