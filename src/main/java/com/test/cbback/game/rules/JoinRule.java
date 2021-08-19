package com.test.cbback.game.rules;

import com.test.cbback.config.EnvConfig;
import com.test.cbback.model.Game;
import com.test.cbback.model.User;
import com.test.cbback.service.BetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
@Component
public class JoinRule implements RuleGame{

    private Game game;
    private User user;

    @Autowired
    private BetService betService;

    @Autowired
    private EnvConfig envConfig;

    @Override
    public boolean validate() {
        Date now = new Date();
        if(now.getTime() < this.calculateTime() && this.validateUserBet()) {
            return true;
        } else {
            return false;
        }
    }

    private Long calculateTime() {
        return this.game.getEndTime() - this.envConfig.getWarningTime();
    }

    private boolean validateUserBet() {
        boolean find = this.betService.searchByUserAndGame(this.user, this.game);
        return !find;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
