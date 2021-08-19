package com.test.cbback.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.cbback.kafka.message.BetRequest;
import com.test.cbback.model.Bet;
import com.test.cbback.model.Game;
import com.test.cbback.model.User;
import com.test.cbback.service.GameService;
import com.test.cbback.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageKafkaService {

    @Autowired
    private UserService userService;

    private final Producer producer;

    @Autowired
    MessageKafkaService(Producer producer) {
        this.producer = producer;
    }


    @Autowired
    private GameService gameService;
    public void sendMessage(List<Bet> winners) {
        for(Bet bet : winners) {
            User user = bet.getUser();
            Game game = bet.getGame();
            BetRequest betRequest = new BetRequest();
            betRequest.setEmail(user.getEmail());
            betRequest.setBetValue(bet.getBetValue());
            betRequest.setNomicsValue(game.getFinalValue());
            producer.sendMessage(this.buildMessage(betRequest));

        }
    }

    private String buildMessage(BetRequest betRequest) {
        ObjectMapper objectMapper = new ObjectMapper();
        String result = "";
        try {
            result = objectMapper.writeValueAsString(betRequest);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return result;
    }
}
