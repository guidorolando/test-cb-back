package com.test.cbback.ws;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.cbback.config.EnvConfig;
import com.test.cbback.config.TimerSingleton;
import com.test.cbback.controller.request.RegistryGame;
import com.test.cbback.controller.response.NomicsResponse;
import com.test.cbback.game.FreeGame;
import com.test.cbback.service.GameService;
import com.test.cbback.service.NomicsService;
import com.test.cbback.ws.response.Bet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import java.util.Date;
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

    @Scheduled(fixedRate = 30000)
    public void sendMessage() {
        Date currentDate = new Date();
        NomicsResponse nomicsResponse = this.nomicsService.getValue()[0];
        this.timerSingleton.setDate(currentDate);
        this.timerSingleton.setInitValue(nomicsResponse.getPrice());
        System.out.println("111111111111111111");
        this.freeGame.end();
        this.freeGame.start();
        template.convertAndSend(SENDING_URL, this.timerSingleton.getDate().getTime());
    }

    private String buildNextMessage() {
        ObjectMapper objectMapper = new ObjectMapper();

        String message = "Test " + counter.getAndIncrement();
        System.out.println("Send message " + message);
        return message;
    }

    @MessageMapping("/registry")
    @SendTo("/topic/players")
    public String send(final RegistryGame registryGame) throws Exception {
        return registryGame.getEmail();
    }
}
