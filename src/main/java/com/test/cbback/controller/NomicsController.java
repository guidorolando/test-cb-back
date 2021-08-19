package com.test.cbback.controller;

import com.test.cbback.controller.response.NomicsResponse;
import com.test.cbback.service.NomicsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "nomics")
public class NomicsController {

    @Autowired
    private NomicsService nomicsService;

    @GetMapping
    public NomicsResponse getValue() {
        return this.nomicsService.getValue()[0];
    }

}
