package com.test.cbback.game;

import com.test.cbback.controller.request.RegistryGame;
import com.test.cbback.model.Bet;
import com.test.cbback.model.Game;
import com.test.cbback.model.User;
import com.test.cbback.service.BetService;
import com.test.cbback.service.GameService;
import com.test.cbback.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FreeGame implements ModeGame {

    @Autowired
    private GameService gameService;

    @Autowired
    private UserService userService;

    @Autowired
    private BetService betService;

    @Override
    public Game start() {
        Game currentGame = this.gameService.getCurrentGame();
        if(currentGame != null) {
            return currentGame;
        } else {
            return this.gameService.start();
        }
    }

    @Override
    public void end() {

    }

    @Override
    public Bet join(RegistryGame registryGame) {
        User user = this.userService.findByEmail(registryGame.getEmail());
        if(user == null) {
            user = this.userService.createUser(registryGame.getEmail());
        }
        Game game = this.gameService.findById(registryGame.getGameId());

        Bet bet = this.betService.registryBet(user, game, registryGame.getBetValue());

        return bet;
    }
}
