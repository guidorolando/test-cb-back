package com.test.cbback.service;

import com.test.cbback.model.Bet;
import com.test.cbback.model.Game;
import com.test.cbback.model.User;
import org.springframework.stereotype.Service;

@Service
public class BetService {

    public Bet registryBet(User user, Game game, Double betValue) {
        Bet bet = new Bet();
        bet.setGame(game);
        bet.setUser(user);
        bet.setWinner(Boolean.FALSE);
        bet.setBetValue(betValue);
        return bet;
    }
}
