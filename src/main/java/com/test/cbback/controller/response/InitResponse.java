package com.test.cbback.controller.response;

public class InitResponse {
    private NomicsResponse nomicsResponse;
    private Long initTime;

    public NomicsResponse getNomicsResponse() {
        return nomicsResponse;
    }

    public void setNomicsResponse(NomicsResponse nomicsResponse) {
        this.nomicsResponse = nomicsResponse;
    }

    public Long getInitTime() {
        return initTime;
    }

    public void setInitTime(Long initTime) {
        this.initTime = initTime;
    }
}
