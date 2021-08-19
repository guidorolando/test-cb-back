package com.test.cbback.game.rules;

import com.test.cbback.model.Bet;
import com.test.cbback.model.Game;
import com.test.cbback.service.BetService;
import com.test.cbback.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PreconditionRule implements RuleGame{

    @Autowired
    private BetService betService;

    @Autowired
    private GameService gameService;

    @Override
    public boolean validate() {
        Game currentGame = this.gameService.getCurrentGame();

        if (currentGame == null) {
            return false;
        }
        List<Bet> bets = this.betService.getBetsByGame(currentGame.getId());

        if (bets.size() > 1) {
            return true;
        }
        return false;
    }
}
