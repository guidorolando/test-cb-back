package com.test.cbback.service;

import com.test.cbback.model.Bet;
import com.test.cbback.model.Game;
import com.test.cbback.model.User;
import com.test.cbback.repository.BetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public boolean searchByUserAndGame(User user, Game game) {
        boolean find = false;
        Bet bet = this.betRepository.findByGameIdAndUserId(game.getId(), user.getId());
        if(bet != null) {
            find = true;
        }
        return find;
    }

    public List<User> getWinners(Long gameId) {
        List<User> userList = new ArrayList<>();
        List<Bet> betList = this.betRepository.findByGameIdAndWinner(gameId, Boolean.TRUE);
        for(Bet bet : betList) {
            userList.add(bet.getUser());
        }
        return userList;
    }
}
