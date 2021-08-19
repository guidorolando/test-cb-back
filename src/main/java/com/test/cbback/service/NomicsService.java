package com.test.cbback.service;

import com.test.cbback.config.EnvConfig;
import com.test.cbback.controller.response.NomicsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class NomicsService {

    @Autowired
    private EnvConfig envConfig;

    public NomicsResponse[] getValue() {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_JSON);
        ResponseEntity<NomicsResponse[]> exchange;

        String endpoint = this.envConfig.getNomicsUrl();
        String token = this.envConfig.getNomicsToken();
        String url = String.format(endpoint, token);

        exchange = restTemplate.getForEntity(url, NomicsResponse[].class);
        return exchange.getBody();
    }
}
