package com.test.cbback.config;

import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class TimerSingleton {

    private Date date = new Date();

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
