package com.test.cbback.game;

import com.test.cbback.config.EnvConfig;
import com.test.cbback.controller.request.RegistryGame;
import com.test.cbback.game.rules.JoinRule;
import com.test.cbback.game.rules.ProcessGame;
import com.test.cbback.model.Bet;
import com.test.cbback.model.Game;
import com.test.cbback.model.User;
import com.test.cbback.service.BetService;
import com.test.cbback.service.GameService;
import com.test.cbback.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class FreeGame implements ModeGame {

    @Autowired
    private GameService gameService;

    @Autowired
    private UserService userService;

    @Autowired
    private BetService betService;

    @Autowired
    private EnvConfig envConfig;

    @Autowired
    private ProcessGame processGame;

    @Autowired
    private JoinRule joinRule;

    private Game game;

    @Override
    public Game start() {
        Game currentGame = this.gameService.getCurrentGame();
        if(currentGame != null) {
            this.game = currentGame;
            return currentGame;
        } else {
            this.game = this.gameService.start();
            return game;
        }
    }


    @Override
    public void process() {
        this.processGame.play();
    }

    @Override
    public void end() {
        this.gameService.endGames();
    }

    @Override
    public boolean join(RegistryGame registryGame) {

        User user = this.userService.findByEmail(registryGame.getEmail());
        if(user == null) {
            user = this.userService.createUser(registryGame.getEmail());
        }
        Game game = this.gameService.findById(registryGame.getGameId());
        this.joinRule.setGame(game);
        this.joinRule.setUser(user);
        System.out.println(joinRule.validate());
        if(joinRule.validate()) {
            Bet bet = this.betService.registryBet(user, game, registryGame.getBetValue());
            return true;
        } else {
            return false;
        }
    }

    public List<User> getWinners() {
        if(game == null) {
            return new ArrayList<>();
        }
        return this.betService.getWinners(this.game.getId());
    }

    public Game getGame() {
        return this.game;
    }
}
