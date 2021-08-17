package com.test.cbback.service;

import com.test.cbback.config.EnvConfig;
import com.test.cbback.config.TimerSingleton;
import com.test.cbback.model.Game;
import com.test.cbback.model.TypeGame;
import com.test.cbback.repository.GameRepository;
import com.test.cbback.repository.TypeGameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class GameService {

    @Autowired
    private NomicsService nomicsService;

    @Autowired
    private TimerSingleton timerSingleton;

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private TypeGameRepository typeGameRepository;

    @Autowired
    private EnvConfig envConfig;

    public Game start() {
        Game freeGame = new Game();
        freeGame.setInitialValue(this.timerSingleton.getInitValue());
        freeGame.setTypeGame(this.getFreeGame());
        freeGame.setStatus(Boolean.TRUE);
        freeGame.setFinalValue(0.0);
        freeGame.setInitTime(this.timerSingleton.getDate().getTime());
        freeGame.setEndTime(this.generateEndTime());

        return this.gameRepository.save(freeGame);
    }

    public Game saveGame(Game game) {
        return this.gameRepository.save(game);
    }

    public Game findById(Long id) {
        return this.gameRepository.findById(id).get();
    }


    public void endGames() {
        this.gameRepository.updateGameStatus(Boolean.FALSE);
    }

    private TypeGame getFreeGame() {
        return this.typeGameRepository.findById(1l).get();
    }

    private Long generateEndTime() {
        Date initDate = this.timerSingleton.getDate();
        initDate.setTime(initDate.getTime() + envConfig.getTime());
        return initDate.getTime();
    }

    public Game getCurrentGame() {
        return this.gameRepository.findByStatus(Boolean.TRUE);
    }
}
