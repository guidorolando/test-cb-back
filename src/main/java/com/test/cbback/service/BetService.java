package com.test.cbback.service;

import com.test.cbback.model.Bet;
import com.test.cbback.model.Game;
import com.test.cbback.model.User;
import com.test.cbback.repository.BetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BetService {

    @Autowired
    private BetRepository betRepository;

    public Bet registryBet(User user, Game game, Double betValue) {
        Bet bet = new Bet();
        bet.setGame(game);
        bet.setUser(user);
        bet.setWinner(Boolean.FALSE);
        bet.setBetValue(betValue);
        return this.betRepository.save(bet);
    }

    public List<Bet> getBetsByGame(Long gameId) {
        return this.betRepository.findByGameId(gameId);
    }

    public void save(Bet bet) {
        this.betRepository.save(bet);
    }
}
