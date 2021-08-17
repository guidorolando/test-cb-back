package com.test.cbback.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EnvConfig {

    @Value("${env.play.time}")
    private Long time;

    @Value("${env.play.warning.time}")
    private Long warningTime;

    @Value("${nomics.url}")
    private String nomicsUrl;

    @Value("${nomics.token}")
    private String nomicsToken;

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public Long getWarningTime() {
        return warningTime;
    }

    public void setWarningTime(Long warningTime) {
        this.warningTime = warningTime;
    }

    public String getNomicsToken() {
        return nomicsToken;
    }

    public void setNomicsToken(String nomicsToken) {
        this.nomicsToken = nomicsToken;
    }

    public String getNomicsUrl() {
        return nomicsUrl;
    }

    public void setNomicsUrl(String nomicsUrl) {
        this.nomicsUrl = nomicsUrl;
    }
}
