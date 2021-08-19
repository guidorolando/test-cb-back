package com.test.cbback.ws;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.cbback.config.EnvConfig;
import com.test.cbback.config.TimerSingleton;
import com.test.cbback.controller.request.RegistryGame;
import com.test.cbback.controller.response.NomicsResponse;
import com.test.cbback.game.FreeGame;
import com.test.cbback.model.User;
import com.test.cbback.service.NomicsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Controller
public class RegistryController {
    private static final String SENDING_URL = "/topic/server-broadcaster";

    private final SimpMessagingTemplate template;
    private AtomicLong counter = new AtomicLong(0);
    private final Long time = 1000l;

    @Autowired
    private TimerSingleton timerSingleton;

    @Autowired
    private NomicsService nomicsService;

    @Autowired
    private FreeGame freeGame;

    @Autowired
    private EnvConfig envConfig;

    @Autowired
    public RegistryController(SimpMessagingTemplate template) {
        this.template = template;
    }

    @Scheduled(fixedRateString = "${env.play.time}")
    public void sendMessage() {
        Date currentDate = new Date();
        NomicsResponse nomicsResponse = this.nomicsService.getValue()[0];
        this.timerSingleton.setDate(currentDate);
        this.timerSingleton.setInitValue(nomicsResponse.getPrice());
        this.freeGame.process();
        List<User> winners = this.freeGame.getWinners();
        this.freeGame.end();
        this.freeGame.start();
        template.convertAndSend(SENDING_URL, this.buildNextMessage(winners));
    }

    private String buildNextMessage(List<User> users) {
        ObjectMapper objectMapper = new ObjectMapper();
        String result = "[]";
        try {
            result = objectMapper.writeValueAsString(users);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return result;
    }

    @MessageMapping("/registry")
    @SendTo("/topic/players")
    public String send(final RegistryGame registryGame) throws Exception {
        return registryGame.getEmail();
    }
}
