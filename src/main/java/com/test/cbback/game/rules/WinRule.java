package com.test.cbback.game.rules;

import com.test.cbback.config.TimerSingleton;
import com.test.cbback.controller.response.NomicsResponse;
import com.test.cbback.model.Bet;
import com.test.cbback.model.Game;
import com.test.cbback.service.BetService;
import com.test.cbback.service.GameService;
import com.test.cbback.service.NomicsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class WinRule implements RuleGame {
    @Autowired
    private GameService gameService;

    @Autowired
    private BetService betService;

    @Autowired
    private NomicsService nomicsService;

    @Autowired
    private TimerSingleton timerSingleton;

    @Override
    public boolean validate() {
        Game currentGame = this.gameService.getCurrentGame();

        if (currentGame != null) {
            currentGame.setFinalValue(this.timerSingleton.getInitValue());
            currentGame = this.gameService.saveGame(currentGame);

            List<Bet> bets = this.betService.getBetsByGame(currentGame.getId());

            if (bets.size() > 1) {
                List<Bet> winners = this.processGame(bets, currentGame);
                // send notification
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }

    }

    private List<Bet> processGame(List<Bet> bets, Game game) {
        List<Bet> winners = new ArrayList<>();
        Double minValue = this.minValue(bets, game);
        for (Bet bet : bets) {
            Double value = Math.abs(game.getFinalValue() - bet.getBetValue());
            if (value.doubleValue() == minValue.doubleValue()) {
                winners.add(bet);
            }
        }
        this.saveWinners(winners);
        return winners;
    }

    private Double minValue(List<Bet> bets, Game game) {
        Double minValue = game.getFinalValue();
        for (Bet bet : bets) {
            Double value = Math.abs(game.getFinalValue() - bet.getBetValue());

            if (value <= minValue) {
                minValue = value;
            }
        }
        return minValue;
    }

    private void saveWinners(List<Bet> bets) {
        for (Bet bet : bets) {
            bet.setWinner(true);
            this.betService.save(bet);
        }
    }
}
