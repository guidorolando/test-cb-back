package com.test.cbback.ws;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.cbback.config.TimerSingleton;
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
    public RegistryController(SimpMessagingTemplate template) {
        this.template = template;
    }

    @Scheduled(fixedRate = 50000)
    public void sendMessage() {
        Date j = new Date();
        this.timerSingleton.setDate(j);
        System.out.println("111111111111111111");
        System.out.println(j.getTime());
        System.out.println(this.timerSingleton.getDate().getTime());
        template.convertAndSend(SENDING_URL, this.buildNextMessage());
    }

    private String buildNextMessage() {
        ObjectMapper objectMapper = new ObjectMapper();

        String message = "Test " + counter.getAndIncrement();
        System.out.println("Send message " + message);
        return message;
    }

    @MessageMapping("/registry")
    public Object send(final Bet bet) throws Exception {
        return bet.getEmail();
    }

    @SubscribeMapping("/topic/initTime")
    @SendTo("/topic/initTime")
    public String onSubscribe() {
        System.out.println("Subs ");
        return "SUBSCRIBED: ";
    }
    @MessageMapping("/initTime")
    @SendTo("/topic/initTime")
    public Long time() throws Exception {
        return this.timerSingleton.getDate().getTime();
    }
}
